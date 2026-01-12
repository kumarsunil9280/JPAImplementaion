package com.example.docker.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.UserDTO;
import com.example.docker.entity.Users;
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

    @PostMapping("/saveBulkUser")
    public ResponseEntity<List<ResponseDTO>> saveBulkUser(@RequestBody List<UserDTO> userDto) {
        List<ResponseDTO> response = userService.saveBulkUser(userDto);
        return ResponseEntity.ok(response);
    }

    // Pagination
    @GetMapping("/getPaginatedUser")
    public Page<Users> getUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        return userService.getPaginatedUser(page, size);
    }
    @GetMapping("/getUser")
    public ResponseEntity<List<Users>> getUser() {
        List<Users> response = userService.getUserList();
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable Long id) {
        Optional<Users> response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }


}