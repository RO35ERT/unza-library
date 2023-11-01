package com.example.unza_library.service;


import com.example.unza_library.dto.UserDto;
import com.example.unza_library.entity.Role;
import com.example.unza_library.entity.User;
import com.example.unza_library.repository.RoleRepository;
import com.example.unza_library.repository.UserRepository;
import com.example.unza_library.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);
        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        User user = new User();
        user.setCompNumber(userDto.getCmpNumber());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByCmpNumber(String cmpNumber) {
        return userRepository.findByCompNumber(cmpNumber);
    }



}