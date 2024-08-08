package com.nhnacademy.student;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Random;
import java.util.Set;

public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        StudentRepository studentRepository = new MapStudentRepository();

        for (int i = 1; i <= 10; i++) {

            Random random = new Random();

            int age = random.nextInt(11) + 20;

            Student student = new Student("student" + i, "아카데미" + i, getRandomGender(), age);
            studentRepository.save(student);
        }
        servletContext.setAttribute("studentRepository", studentRepository);
    }

    public static Gender getRandomGender() {
        Random random = new Random();
        int pick = random.nextInt(Gender.values().length);
        return Gender.values()[pick];
    }
}
