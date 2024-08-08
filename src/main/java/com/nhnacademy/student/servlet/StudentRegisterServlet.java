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

@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);

        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String gender = req.getParameter("gender");
//        String ageStr = req.getParameter("age");
//
//        if (Objects.nonNull(id) && Objects.nonNull(name) && Objects.nonNull(gender) && Objects.nonNull(ageStr)) {
//            try {
//                int age = Integer.parseInt(ageStr);
//                Student student = new Student(id, name, Gender.valueOf(gender), age);
//                studentRepository.save(student);
//
////                resp.sendRedirect("/student/view?id=" + student.getId());
//                req.setAttribute("view", "/student/view?id=" + student.getId());
//            } catch (IllegalArgumentException e) {
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
//            }
//        } else {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
//        }

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        if(Objects.nonNull(req.getParameter("gender"))){
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if(Objects.nonNull(req.getParameter("age"))){
            age = Integer.parseInt(req.getParameter("age"));
        }

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }

        Student student = new Student(id,name,gender,age);
        studentRepository.save(student);

        req.setAttribute("view", "redirect:/student/view.do?id=" + student.getId());
    }
}
