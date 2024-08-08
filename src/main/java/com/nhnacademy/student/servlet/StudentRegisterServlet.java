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
        //todo  /student/register.jsp forward 합니다.

        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo null check

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");

        if (Objects.nonNull(id) && Objects.nonNull(name) && Objects.nonNull(gender) && Objects.nonNull(ageStr)) {
            try {
                int age = Integer.parseInt(ageStr);
                Student student = new Student(id, name, Gender.valueOf(gender), age);
                studentRepository.save(student);

                resp.sendRedirect("/student/view?id=" + id);
            } catch (IllegalArgumentException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
        }
    }
}
