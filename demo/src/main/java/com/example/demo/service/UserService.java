
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveUser() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> retrieveUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> retrieveUser(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    public User CreateUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id,User user){

        Optional<User> userOptional = userRepository.findById(id);
        
        if(userOptional.isPresent()){
            return userOptional;
        }
        user.setId(id);
        return Optional.of(userRepository.save(user));

    }

    public boolean  deleteUser(Long id){
        try{
            userRepository.deleteById(id);
        return true;
        }
        catch(EmptyResultDataAccessException e){
            return false;
        }

    }

}