package com.example.servletdemo;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("myName");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.print("<html><head><title> Welcome to the Web World </title></head><body>");
        out.println("<h2> username is :" + username +"</h2> \n");
        out.println("<h2> password is :" + password + "</h2> \n");
        out.println("</body></html>");


    }

    public void destroy() {
    }
}