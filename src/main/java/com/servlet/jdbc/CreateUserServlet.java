package com.servlet.jdbc;

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

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/Create")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	PreparedStatement stmt;
    
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
			stmt=con.prepareStatement("insert into user values(?,?,?)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("uname");
		String email=req.getParameter("email");
		int bal=Integer.parseInt(req.getParameter("balance"));
		try {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setInt(3, bal);
			int rs=stmt.executeUpdate();
			PrintWriter out=res.getWriter();
			out.println(rs +"rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void destroy() {
		try {
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}











