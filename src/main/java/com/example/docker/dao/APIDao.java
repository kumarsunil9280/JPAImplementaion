package com.example.docker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.docker.entity.SignUpEntity;

import jakarta.transaction.Transactional;

@Repository
public class APIDao {
	
	@Autowired
	SignUpJPARepository signUpJPARepository;

	@Transactional
	public Long signUp(SignUpEntity signUpEntity) {
		signUpJPARepository.save(signUpEntity);
		return signUpEntity.getId();
	}

}
