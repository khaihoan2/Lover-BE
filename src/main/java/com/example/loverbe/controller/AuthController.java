package com.example.loverbe.controller;

import com.example.loverbe.model.JwtResponse;
import com.example.loverbe.model.entity.Role;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.string_constant.RoleName;
import com.example.loverbe.model.string_constant.UserAuthenticate;
import com.example.loverbe.model.string_constant.UserStatus;
import com.example.loverbe.service.jwt.JwtService;
import com.example.loverbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null ||
                user.getUsername().equals("") || user.getPassword().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String token = jwtService.createTokenLogin(authentication);
        User loggedUser = userService.findByUsername(username);
        return new ResponseEntity<>(
                new JwtResponse(loggedUser.getId(), username, token, userDetails.getAuthorities()),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        User user1 = userService.findByUsername(user.getUsername());
        if (user1 == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(3L, RoleName.ROLE_BUYER));
            user.setRoles(roles);
            user.setStatus(UserStatus.USER);
            user.setAuthenticate(UserAuthenticate.NORMAL);
            user.setJoinedAt(new Date(System.currentTimeMillis()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus("User");
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
