package com.example.docker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.UserDTO;
import com.example.docker.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
    @PostMapping("/saveUser")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userDto) {
    	ResponseDTO response = userService.saveUser(userDto);
        return ResponseEntity.ok(response);
    }
}