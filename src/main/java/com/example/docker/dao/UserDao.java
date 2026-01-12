package com.example.docker.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.docker.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UserDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    UserRepository userRepository ;

	
	// Save Single User
    @Transactional
    public Long saveUser(Users user) {
        //entityManager.persist(user);   // JPA SAVE
    	userRepository.save(user);
        return user.getId();            // ID generated after persist
        
    }

    // Save Bulk User
    @Transactional
	public List<Long> saveBulkUser(List<Users> userList) {
		List<Long> ids = new ArrayList<>();

        for (Users user : userList) {
            Users merged = entityManager.merge(user); // insert or update
            ids.add(merged.getId());
        }
        return ids;
	}

}
