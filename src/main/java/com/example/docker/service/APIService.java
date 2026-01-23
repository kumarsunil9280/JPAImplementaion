package com.example.docker.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.docker.dao.APIDao;
import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.SignUpDTO;
import com.example.docker.entity.SignUpEntity;

@Service
public class APIService {
	
	@Autowired
	private APIDao apiDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	//create new sign up 
	public ResponseDTO signUp(SignUpDTO signUpDTO) {
		
		SignUpEntity signUpEntity = new SignUpEntity();
		//Function<SignUpEntity,SignUpDTO> signEntity = signEntity.andThen(signUpDTO);
		
		
		BeanUtils.copyProperties(signUpDTO,signUpEntity);
		signUpEntity.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
		signUpEntity.setUsername(signUpDTO.getEmail().split("@")[0].toLowerCase().toString());

		
		Long id = apiDao.signUp(signUpEntity);
		ResponseDTO responseDTO = new ResponseDTO();
		if(id!=null) {
			responseDTO.setId(id);
			responseDTO.setMsg("Save Successfully");
		}
		return responseDTO;
	}

}
