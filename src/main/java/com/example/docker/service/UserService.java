package com.example.docker.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.docker.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.docker.dao.AuthUserRepository;
import com.example.docker.dao.UserDao;
import com.example.docker.dto.AuthRequest;
import com.example.docker.dto.ResponseDTO;
import com.example.docker.dto.UserDTO;
import com.example.docker.entity.UserAuth;
import com.example.docker.entity.Users;


@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthUserRepository authUserRepository ;


    //Save User Data
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

    //Save Bulk User Data
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

    //Get All User List
    public List<Users> getUserList() {
        return userRepository.findAll( Sort.by(
                Sort.Order.asc("id")
        ));
    }

    //Cache use based on id
    @Cacheable(value = "users", key = "#id")
    public Optional<Users> getUserById(Long id) {
    	System.out.println("DB Called");
        return userRepository.findById(id);
    }

    // Get Paginated User based data
	public Page<Users> getPaginatedUser(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
	    return userRepository.findAll(pageable);
	}

	// Save Auth User
	public ResponseDTO saveAuthUser(UserDTO userDto) {
		 ResponseDTO dto = new ResponseDTO();

		 	UserAuth user = new UserAuth();
	        user.setUsername(userDto.getName());
	        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	        Long userId = userDao.saveAuthUser(user);
	        if (userId != null) {
	            dto.setId(userId);
	            dto.setMsg("Successfully Saved!");
	        } else {
	            dto.setId(null);
	            dto.setMsg("Error While Saving");
	        }
	        return dto;
	}

	// get all Auth user
	public List<UserAuth> getAuthUser() {
		 return authUserRepository.findAll();
	}

	
	public Boolean saveAuthUsers(AuthRequest request) {
		UserAuth user = new UserAuth();
	       
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        authUserRepository.save(user);
		return true;
	}

	
	public List<UserDTO> getUserDTO() {
		return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	
	 private UserDTO convertToDTO(Users user) {
		 UserDTO userDto = new UserDTO();
		 return userDto;
	 }

}
