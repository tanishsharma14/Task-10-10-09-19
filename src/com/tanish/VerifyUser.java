package com.tanish;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VerifyUser")
public class VerifyUser extends HttpServlet {
	Connection con;
	public void init()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String type=request.getParameter("utype");
		
		if(type.equals("owner"))
		{
			if(userid.equals("tanish")&&password.equals("tanish123"))
			response.sendRedirect("admin.jsp");
		}else
		{
			try {
				PreparedStatement psmt=con.prepareStatement("select userid,password from users where userid=? and password=?");
				psmt.setString(1,userid);
				psmt.setString(2,password);
				
				ResultSet rs=psmt.executeQuery();
				while(rs.next())
				{
					response.sendRedirect("buyerpage.jsp");
				}
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
	}

}
























