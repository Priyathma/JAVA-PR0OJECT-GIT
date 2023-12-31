package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    
    private static final String INSERT_QUERY = "INSERT INTO customer (firstname, lastname, email, dob, mobileno, password) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        try (PrintWriter pw = res.getWriter()) {
            
            res.setContentType("text/hmtl");
           
            String firstName = req.getParameter("firstname");
            String lastName = req.getParameter("lastname");
            String email = req.getParameter("email");
            String dob = req.getParameter("date");
            String mobile = req.getParameter("tel");
            String password = req.getParameter("password");
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
            try (final Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smdb","root","SQL123");
                    final PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
                
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, dob);
                ps.setString(5, mobile);
                ps.setString(6, password);
                
                int count = ps.executeUpdate();

                if (count == 0) {
                    pw.println("Registration failed");
                } else {
                    pw.println("Registration is successful");
                }
            } catch (SQLException se) {
                pw.println(se.getMessage());
                se.printStackTrace();
            } catch (Exception e) {
                pw.println(e.getMessage());
                e.printStackTrace();
            }
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
