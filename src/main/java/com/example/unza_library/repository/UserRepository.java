package com.example.unza_library.repository;


import com.example.unza_library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByCompNumber(String computerNumber);
}