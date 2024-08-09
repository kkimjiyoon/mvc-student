package com.nhnacademy.student.controller;

import com.nhnacademy.student.annotation.RequestMapping;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        studentRepository.deleteById(id);

        return "redirect:/student/list.do";
    }
}
