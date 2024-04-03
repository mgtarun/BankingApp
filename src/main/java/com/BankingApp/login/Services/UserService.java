package com.BankingApp.login.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApp.login.Models.UserModel;
import com.BankingApp.login.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public ResponseEntity<String> signUp(UserModel userModel) {
        
        try {
            UserModel user = userRepository.findByUserName(userModel.getUserName());
            if(user==null){
                String hashedPassword = PasswordUtil.encodePassword(userModel.getPassword());
            userModel.setPassword(hashedPassword);
            userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.OK).body("Signup Successful");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
            }
            
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    
    public boolean authenticate(String userName,String password)
    {
        UserModel userModel = userRepository.findByUserName(userName);
        if (userModel==null) {
            return false;
        }
        else
        {
            return PasswordUtil.checkPassword(password,userModel.getPassword());
        }
    } 
}