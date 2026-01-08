package com.example.docker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.docker.dao.UserDao;
import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.UserDTO;
import com.example.docker.entity.Users;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public ResponseDTO saveUser(UserDTO userDto) {

		
		ResponseDTO dto = new ResponseDTO();

		Users user = new Users();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		Long userId = userDao.saveUser(user);

		if (userId != null) {
			dto.setId(userId);
			dto.setMsg("Successfully Saved!");
		} else {
			dto.setId(null);
			dto.setMsg("Error While Saving");
		}
		
		return dto;
	}

}
