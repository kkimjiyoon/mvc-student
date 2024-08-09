package com.nhnacademy.student;

import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.service.UserService;
import com.nhnacademy.student.util.InjectUtil;

public class DiTest {
    public static void main(String[] args) {
        UserService userService = InjectUtil.getObject(UserService.class);
        User user = new User("marco1",10);
        userService.addUser(user);
        System.out.println(userService.getUser("marco1"));
    }
}
