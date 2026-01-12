package com.example.docker.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.docker.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.docker.dao.UserDao;
import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.UserDTO;
import com.example.docker.entity.Users;


@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

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

    public List<ResponseDTO> saveBulkUser(List<UserDTO> userDto) {
        var userList = userDto.stream()
                .map(dto -> {
                    Users user = new Users();
                    user.setEmail(dto.getEmail());
                    user.setName(dto.getName());
                    user.setPassword(dto.getPassword());
                    return user;
                })
                .toList();

        List<Long> userId = userDao.saveBulkUser(userList);

        var respDTOList = new ArrayList<ResponseDTO>();


        if (userId.size()>0) {
            for(Long id:userId){
                ResponseDTO dto = new ResponseDTO();
                dto.setId(id);
                dto.setMsg("Successfully Saved!");
                respDTOList.add(dto);
            }

        } else {
            ResponseDTO dto = new ResponseDTO();
            dto.setId(null);
            dto.setMsg("Error While Saving !");
            respDTOList.add(dto);
        }

        return respDTOList;
    }

    public List<Users> getUserList() {
        return userRepository.findAll( Sort.by(
                Sort.Order.asc("id")
        ));
    }

    @Cacheable(value = "users", key = "#id")
    public Optional<Users> getUserById(Long id) {
    	System.out.println("DB Called");
        return userRepository.findById(id);
    }

	public Page<Users> getPaginatedUser(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
	    return userRepository.findAll(pageable);
	}

}
