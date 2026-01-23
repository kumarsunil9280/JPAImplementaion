package com.example.docker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.SignUpDTO;
import com.example.docker.service.APIService;

@RequestMapping("/api/data/")
@RestController
public class APIController {
	
	@Autowired
	private APIService apiService;
	
	@GetMapping("/signUp")
	public ResponseEntity<String> signUpGet() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }
	
	    
	
	@PostMapping("/signUp")
	 public ResponseEntity<ResponseDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        ResponseDTO response = apiService.signUp(signUpDTO);
        return ResponseEntity.ok(response);
    }

}
