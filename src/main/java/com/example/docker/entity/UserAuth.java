package com.example.docker.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@JsonPropertyOrder({ "userAuthId", "username", "password", "createdAt", "updatedAt" })
@Entity
@Table(name = "user_auth",schema = "audit")
public class UserAuth {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name="user_auth_id")
	    private Long userAuthId;

	    @Column(name="user_name", nullable = false, length = 100)
	    private String username;


	    @JsonIgnore // 🔐 DO NOT expose password
	    @Column(name="password",nullable = false)
	    private String password;

	    @JsonProperty("createdAt")
	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @JsonProperty("updatedAt")
	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    /* ---------- JPA Callbacks ---------- */

	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }

		public Long getUserAuthId() {
			return userAuthId;
		}

		public void setUserAuthId(Long userAuthId) {
			this.userAuthId = userAuthId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	    
	    
}
