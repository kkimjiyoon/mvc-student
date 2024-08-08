package com.nhnacademy.student.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    private static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
    private static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    private static final String ERROR_MESSAGE = "javax.servlet.error.message";
    private static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    private static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("status_code", request.getAttribute(ERROR_STATUS_CODE));
        request.setAttribute("exception_type", request.getAttribute(ERROR_EXCEPTION_TYPE));
        request.setAttribute("message", request.getAttribute(ERROR_MESSAGE));
        request.setAttribute("exception", request.getAttribute(ERROR_EXCEPTION));
        request.setAttribute("request_uri", request.getAttribute(ERROR_REQUEST_URI));

        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }
}
