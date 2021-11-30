package com.example.loverbe.controller;

import com.example.loverbe.model.JwtResponse;
import com.example.loverbe.model.Role;
import com.example.loverbe.model.User;
import com.example.loverbe.model.string_constant.RoleName;
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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String token = jwtService.createTokenLogin(authentication);
        User user1 = userService.findByUsername(username);
        return new ResponseEntity<>(new JwtResponse(user1.getId(), username, token, userDetails.getAuthorities()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User user1 = userService.findByUsername(user.getUsername());
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(new Long(1), RoleName.ROLE_ADMIN));
        user.setRoles(roles);
        if (user1 == null) {
            userService.save(user);
            return new ResponseEntity<>("thanh cong", HttpStatus.OK);
        }
        return new ResponseEntity<>("that bai", HttpStatus.CONFLICT);
    }
}
