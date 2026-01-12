package com.example.docker.dao;

import org.springframework.stereotype.Repository;

import com.example.docker.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UserDao {

	@PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long saveUser(Users user) {
        return user.getId();            // ID generated after persist
	}

}
