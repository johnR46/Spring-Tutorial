package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getUser() {
        return userService.retrieveUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userService.retrieveUser(id);

        if (user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);

    }

    @GetMapping(params = "name")
    public List<User> getUser(@RequestParam(value = "name") String name){
        return userService.retrieveUser(name);
    }

    
    @PostMapping()
    public ResponseEntity<?> postUser(@Valid @RequestBody User body){
        User user = userService.CreateUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PutMapping(value="/{id}")
    public ResponseEntity putUser(@PathVariable Long id, @RequestBody User  body ) {
        Optional <User> user  = userService.updateUser(id, body);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }



}
