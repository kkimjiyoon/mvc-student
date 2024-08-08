package com.nhnacademy.student.controller;

import com.nhnacademy.student.Command;
import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Gender gender = null;

        if(Objects.nonNull(request.getParameter("gender"))){
            gender = Gender.valueOf(request.getParameter("gender"));
        }

        Integer age = null;
        if(Objects.nonNull(request.getParameter("age"))){
            age = Integer.parseInt(request.getParameter("age"));
        }

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }

        Student student = new Student(id,name,gender,age);
        studentRepository.update(student);

        return "redirect:/student/view.do?id=" + student.getId();
    }
}
