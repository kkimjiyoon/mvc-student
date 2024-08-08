//package com.nhnacademy.student.listener;
//
//import com.nhnacademy.student.domain.Gender;
//import com.nhnacademy.student.domain.Student;
//import com.nhnacademy.student.repository.MapStudentRepository;
//import com.nhnacademy.student.repository.StudentRepository;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.util.Random;
//
//@WebListener
//public class WebApplicationListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContext context = sce.getServletContext();
//
//        StudentRepository studentRepository = new MapStudentRepository();
//
//        for (int i = 1; i <= 10; i++) {
//
//            Random random = new Random();
//
//            int age = random.nextInt(11) + 20;
//
//            Student student = new Student("student" + i, "아카데미" + i, getRandomGender(), age);
//            studentRepository.save(student);
//        }
//        context.setAttribute("studentRepository", studentRepository);
//    }
//
//    public static Gender getRandomGender() {
//        Random random = new Random();
//        int pick = random.nextInt(Gender.values().length);
//        return Gender.values()[pick];
//    }
//}
