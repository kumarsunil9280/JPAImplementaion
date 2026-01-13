package com.example.docker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.docker.entity.UserAuth;


public interface AuthUserRepository extends JpaRepository<UserAuth, Long> {
	 Optional<UserAuth> findByUsername(String username);

}
