package com.nhnacademy.student.service;

import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.annotation.Autowired;
import com.nhnacademy.student.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String userName) {
        return userRepository.findByName(userName);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
