package com.springbiblio.controllers;

import com.springbiblio.entities.UserEntity;
import com.springbiblio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }
    @GetMapping("/getList")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        Optional<UserEntity> userEntityOptional = userService.getUserById(id);
        if (userEntityOptional.isPresent()){
            return ResponseEntity.ok(userEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable Long id,@RequestBody UserEntity user){
        Optional<UserEntity> userEntityOptional = userService.updateUserById(id,user);
        if (userEntityOptional.isPresent()){
            return ResponseEntity.ok(userEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<UserEntity> deleteUserById(@PathVariable Long id){
        Optional<UserEntity> userEntityOptional = userService.deleteUserById(id);
        if (userEntityOptional.isPresent()){
            return ResponseEntity.ok(userEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
