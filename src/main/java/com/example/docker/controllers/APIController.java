package com.example.docker.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/data/")
@RestController
public class APIController {
	
	@PostMapping("/signUp")
	public String msg (){
		return "Hello-User";
	}

}
