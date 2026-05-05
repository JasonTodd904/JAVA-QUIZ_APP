package com.myapp.quiz_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myapp.quiz_app.dto.AuthResponse;
import com.myapp.quiz_app.model.User;
import com.myapp.quiz_app.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   /*
    public String registerUser(String username,String password)
    {
        Optional<User> existingUser=userRepository.findByUsername(username);
        if(existingUser.isPresent())
            return "Already Registered";
        User user=new User(username,password,"USER");
        userRepository.save(user);
        return "REGISTERED SUCCESFULLY!!!";
    }

    public String loginUser(String username,String password)
    {
        Optional<User> userOptional=userRepository.findByUsername(username);//calls repository and checks
        if(userOptional.isEmpty())
            return "USER NOT FOUND";

        //user is a object of User type hence we can simply use user class methods such as get password and all
        User user = userOptional.get();//take the user name inside Optional container and set user to that name

        if(!user.getPassword().equals(password))
            return "INVALID PASSWORD";
        return ("Login SUCCESSFUL\nRole:"+user.getRole());
    }*/


    public AuthResponse registerUser(String username, String password) 
    {
        //if user already exists
        if (userRepository.findByUsername(username).isPresent()) {
            return new AuthResponse(false, "Username already exists", null);
        }

        //new user
        User user = new User(username, password, "USER");
        userRepository.save(user);

        return new AuthResponse(true, "User registered successfully", "USER");
    }

    public AuthResponse loginUser(String username, String password) 
    {
        //user may or may not exist
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) 
            return new AuthResponse(false, "User not found", null);

        User user = userOptional.get();

        if (!user.getPassword().equals(password))
            return new AuthResponse(false, "Invalid password", null);

        return new AuthResponse(true, "Login successful", user.getRole());
    }
}
