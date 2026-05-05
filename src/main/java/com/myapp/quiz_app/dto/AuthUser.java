package com.myapp.quiz_app.dto;

//DTO layer
//DTO is like a courier envelope It carries:username and password


//it helps by not letting user access the user.java which is the entity as it contains roles,
//id and everything else that user does not need,hence we dont need to send them
public class AuthUser {

    private String username;
    private String password;

    public AuthUser() {
    }

    //getters and setters
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
    
}
