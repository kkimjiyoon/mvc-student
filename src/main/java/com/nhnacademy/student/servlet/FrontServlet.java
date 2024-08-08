package com.nhnacademy.student.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            // 실제 요청 처리할 servlet 을 결정
            String servletPath = resolveServlet(request.getServletPath());
            RequestDispatcher rd = request.getRequestDispatcher(servletPath);
            rd.include(request, response);

            // 실제 요청을 처리한 servlet 이 'view' 라는 request 속성값으로 view 를 전달해 줌.
            String view = (String) request.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                String redirectUrl = view.substring(REDIRECT_PREFIX.length());
                log.error("redirect-url : {}", redirectUrl);
                response.sendRedirect(redirectUrl);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
                requestDispatcher.include(request, response);

            }
        } catch (Exception ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;

        if ("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        } else if("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        } else if ("/student/view.do".equals(servletPath)) {
            processingServlet = "/student/view";
        } else if ("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        } else if ("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        }

        return processingServlet;
    }
}