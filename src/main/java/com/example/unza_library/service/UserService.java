package com.example.unza_library.service;


import com.example.unza_library.dto.UserDto;
import com.example.unza_library.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByCmpNumber(String cmpNumber);

}