package com.nhnacademy.student.servlet;

import com.nhnacademy.student.Command;
import com.nhnacademy.student.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;

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
//            String servletPath = resolveServlet(request.getServletPath());
//            RequestDispatcher rd = request.getRequestDispatcher(servletPath);
//            rd.include(request, response);

            //todo 실제 로직을 처리할 Command(Controller) 결정, String view = command.execute() ...
            Command command = resolveCommand(request.getServletPath(), request.getMethod());
            String view = command.execute(request, response);
            if (view.startsWith(REDIRECT_PREFIX)) {
                String redirectUrl = view.substring(REDIRECT_PREFIX.length());
                log.error("redirect-url : {}", redirectUrl);
                response.sendRedirect(redirectUrl);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(view);
                rd.include(request, response);

            }
        } catch (Exception ex) {
            //공통 error 처리
            request.setAttribute("status_code", request.getAttribute(ERROR_STATUS_CODE));
            request.setAttribute("exception_type", request.getAttribute(ERROR_EXCEPTION_TYPE));
            request.setAttribute("message", request.getAttribute(ERROR_MESSAGE));
            request.setAttribute("exception", request.getAttribute(ERROR_EXCEPTION));
            request.setAttribute("request_uri", request.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", request.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }

    private Command resolveCommand(String servletPath, String method){
        Command command = null;
        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentListController();
        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentViewController();
        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentDeleteController();
        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentUpdateFormController();
        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentUpdateController();
        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentRegisterFormController();
        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentRegisterController();
        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }
        return command;
    }
}