package com.example.docker.dto;

public class SignUpDTO {

    private String username;
    private String email;
    private String password;
    private String firstName;   
    private String lastName;    
    private boolean flag;       

    // Constructors
    public SignUpDTO() {}

    public SignUpDTO(String username, String email, String password,String firstName, String lastName, boolean flag) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flag = flag;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public boolean isFlag() { return flag; }
    public void setFlag(boolean flag) { this.flag = flag; }
}
