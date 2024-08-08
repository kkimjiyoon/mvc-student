package com.nhnacademy.student.servlet;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        request.setAttribute("student", student);

//        RequestDispatcher rd = request.getRequestDispatcher("/student/register.jsp");
//        rd.forward(request, response);

        request.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        String gender = request.getParameter("gender");
//        String ageStr = request.getParameter("age");
//
//        if (Objects.nonNull(id) && Objects.nonNull(name) && Objects.nonNull(gender) && Objects.nonNull(ageStr)) {
//            try {
//                int age = Integer.parseInt(ageStr);
//                Student student = new Student(id, name, Gender.valueOf(gender), age);
//                studentRepository.update(student);
//
//                response.sendRedirect("/student/view?id=" + id);
//            } catch (IllegalArgumentException e) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
//            }
//        } else {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
//        }

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

        // resp.sendRedirect("/student/view?id=" + student.getId());
        //todo view attribute 설정 -  redirect
        request.setAttribute("view", "redirect:/student/view.do?id=" + student.getId());
    }
}
