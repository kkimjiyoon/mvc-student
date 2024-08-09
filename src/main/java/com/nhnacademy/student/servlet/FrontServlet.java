package com.nhnacademy.student.servlet;

import com.nhnacademy.student.ControllerFactory;
import com.nhnacademy.student.controller.Command;
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
    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        controllerFactory = (ControllerFactory) getServletContext().getAttribute("controllerFactory");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            Command command = (Command) controllerFactory.getBean(request.getMethod(), request.getServletPath());
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
}