package com.ases.api.service;

import com.ases.api.ErrorsHandlers.AlreadyExistsException;
import com.ases.api.contracts.UserDAO;
import com.ases.api.dtos.LoginDTO;
import com.ases.api.dtos.UserRegistrationDTO;
import com.ases.api.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String login(LoginDTO loginDto) {
        UserModel user = userDAO.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }


        return generateToken(user);
    }

    public String register(UserRegistrationDTO userDto) {
        Optional<UserModel> existingUser = userDAO.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new AlreadyExistsException("User with this email already exists");
        }
        UserModel userModel = new UserModel(userDto);
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        return userDAO.save(userModel);
    }

    private String generateToken(UserModel user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .claim("userId", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }
}
