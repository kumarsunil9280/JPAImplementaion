package com.example.docker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.docker.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}