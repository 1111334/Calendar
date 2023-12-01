package com.example.demo.service;


import com.example.demo.model.Calendar;
import com.example.demo.model.User;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalendarRepository calendarRepository;


    public void insertUser(User user) {
        userRepository.save(user);
    }


    public List<User> viewUser() {
        return userRepository.findAll();
    }

    public void putUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }




}




