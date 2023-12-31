package com.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jni.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String SELECT_QUERY = "SELECT * FROM customer WHERE email = ? AND password = ?";
    private static final String WELCOME_MESSAGE = "Login successful. Welcome, %s!";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (PrintWriter pw = res.getWriter()) {
            res.setContentType("text/html");

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smdb", "root", "SQL123");
                    PreparedStatement ps = con.prepareStatement(SELECT_QUERY)) {

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String dob = rs.getString("dob");
                    String mobileno = rs.getString("mobileno");

                    HttpSession session = req.getSession(true);
                    session.setAttribute("email", email);
                    session.setAttribute("firstname", firstname);
                    session.setAttribute("lastname", lastname);
                    session.setAttribute("dob", dob);
                    session.setAttribute("mobileno", mobileno);

                    String welcomeMessage = String.format(WELCOME_MESSAGE, firstname);
                    pw.println(welcomeMessage);

                    // Redirect to the profile page
                    res.sendRedirect("myprofile.jsp");
                } else if ("admin@gmail.com".equals(email) && "admin".equals(password)) {
                    // Create a user object for admin
                    User adminUser = new User();
                    //adminUser.setName("Admin");

                    // Get session from request
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userobj", adminUser);

                    // Redirect to the admin page
                    res.sendRedirect("admin.html");
                } else {
                    pw.println("Login failed...<br>Please check your email and password.");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect to the profile page
        resp.sendRedirect("myprofile.jsp");
    }
}
