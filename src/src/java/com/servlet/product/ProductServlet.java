package com.servlet.product;

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
import javax.servlet.http.Part;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {


    private static final String INSERT_QUERY = "INSERT INTO product (name, details, price, picture) VALUES (?, ?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (PrintWriter pw = res.getWriter()) {
            res.setContentType("text/html");

            String productName = req.getParameter("name");
            String productDetails = req.getParameter("details");
            String productPrice = req.getParameter("price");

            // Assuming you are using Apache Commons FileUpload for handling file uploads
            Part filePart = req.getPart("picture");
            String fileName = filePart.getSubmittedFileName();

            Connection con = null;
            PreparedStatement ps = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smdb", "root", "SQL123");
                ps = con.prepareStatement(INSERT_QUERY);

                ps.setString(1, productName);
                ps.setString(2, productDetails);
                ps.setString(3, productPrice);
                ps.setString(4, fileName);

                int count = ps.executeUpdate();

                if (count == 0) {
                    pw.println("<p>Product insertion failed</p>");
                } else {
                    pw.println("<p>Product insertion is successful</p>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                pw.println("<p>Exception: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    pw.println("<p>SQLException during close: " + e.getMessage() + "</p>");
                }
            }

            pw.println("<p>Debug Information:</p>");
            pw.println("<ul>");
            pw.println("<li>Product Name: " + productName + "</li>");
            pw.println("<li>Product Details: " + productDetails + "</li>");
            pw.println("<li>Product Price: " + productPrice + "</li>");
            pw.println("<li>File Name: " + fileName + "</li>");
            pw.println("</ul>");

        }
    }
}
