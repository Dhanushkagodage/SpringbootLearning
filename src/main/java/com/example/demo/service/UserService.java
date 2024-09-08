package com.example.demo.service;


import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    public UserDTO getUserById(Integer userId) {
        User user = userRepository.getUserById(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO addUser(UserDTO userDTO) {
       userRepository.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public String deleteUser(UserDTO userDTO) {
        userRepository.delete(modelMapper.map(userDTO, User.class));
        return "User deleted";
    }

    public String deletedUser(Integer userId) {
        userRepository.deleteById(userId);
        return "User deleted";
    }
}
