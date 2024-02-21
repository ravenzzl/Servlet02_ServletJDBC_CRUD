package com.servlet.jdbc;

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

/**
 * Servlet implementation class ReadSerlvet
 */
@WebServlet("/ReadSerlvet")
public class ReadSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	   PreparedStatement stmt;
		public void init() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
       
    // we are doing get operation here to read values
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			stmt=con.prepareStatement("select * from user");
			ResultSet rs=stmt.executeQuery();
			PrintWriter out = response.getWriter();
			while(rs.next()) {
				out.print(rs.getString(1));
				out.print("<br/>");
				out.print(rs.getString(2));
				out.print("<br/>");
				out.print(rs.getString(3));
				out.print("<br/>");
				out.print("next set starts here");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
