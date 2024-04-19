package com.springbiblio.services;

import com.springbiblio.entities.BookEntity;
import com.springbiblio.entities.UserEntity;
import com.springbiblio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user){
        return userRepository.save(user);
    }
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<UserEntity> getUserById(Long id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){
            return userEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<UserEntity> updateUserById(Long id, UserEntity user){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){
            userEntityOptional.get().setName(user.getName());
            userEntityOptional.get().setTakenBooks(user.getTakenBooks());
            return userEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<UserEntity> deleteUserById(Long id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){
            userRepository.deleteById(userEntityOptional.get().getId());
            return userEntityOptional;
        }else {
            return Optional.empty();
        }
    }

}
