package com.nhnacademy.student.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Student {
    //아이디
    private  String id;
    //이름
    @Setter
    private  String name;
    //성별
    @Setter
    private  Gender gender;
    //나이
    @Setter
    private  int age;
    //생성일
    private LocalDateTime createdAt;

    public Student(String id, String name, Gender gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

}
