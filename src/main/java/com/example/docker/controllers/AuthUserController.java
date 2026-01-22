package com.example.docker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.docker.config.JwtUtil;
import com.example.docker.dto.AuthRequest;
import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.UserDTO;
import com.example.docker.entity.UserAuth;
import com.example.docker.service.UserService;




@RequestMapping("/auth")
@RestController
public class AuthUserController {

	@Autowired
	UserService userService;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
	
    	@PostMapping("/saveAuthUser")
	    public ResponseEntity<ResponseDTO> saveAuthUser(@RequestBody UserDTO userDto) {
	        ResponseDTO response = userService.saveAuthUser(userDto);
	        return ResponseEntity.ok(response);
	    }

	    
	    @GetMapping("/getAuthUser")
	    public ResponseEntity<List<UserAuth>> getAuthUser() {
	        List<UserAuth> response = userService.getAuthUser();
	        return ResponseEntity.ok().body(response);
	    }
	    
	    @PostMapping("/login")
	    public String login(@RequestBody AuthRequest request) {
	  
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                request.getUsername(), request.getPassword())
	        );

	        return jwtUtil.generateToken(request.getUsername());
	    }
}
