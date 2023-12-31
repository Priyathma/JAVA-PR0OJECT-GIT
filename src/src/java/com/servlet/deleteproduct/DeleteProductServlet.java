package com.servlet.deleteproduct;

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

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

    private static final String DELETE_QUERY = "DELETE FROM product WHERE id = ?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (PrintWriter pw = res.getWriter()) {
            res.setContentType("text/html");

            String productId = req.getParameter("id");

            Connection con = null;
            PreparedStatement ps = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smdb", "root", "SQL123");
                ps = con.prepareStatement(DELETE_QUERY);

                ps.setString(1, productId);

                int count = ps.executeUpdate();

                if (count == 0) {
                    pw.println("<p>Product deletion failed. Product ID not found.</p>");
                } else {
                    pw.println("<p>Product deletion is successful</p>");
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
            pw.println("<li>Product ID to delete: " + productId + "</li>");
            pw.println("</ul>");
        }
    }
}
