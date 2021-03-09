package com.boydhcl.getProductByID;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.boydhcl.getProductByID.DBConnector.*;

/**
 * Servlet implementation class GetProductByID
 */
@WebServlet("/GetProductByID")
public class GetProductByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductByID() {
        super();
        // TODO Auto-generated constructor stub
    }
    
//    protected void service(HttpServletRequest resuest, HttpServletResponse response) throws ServletException, IOException {
//    	PrintWriter out = response.getWriter();
//    	out.print("Hello World, service method");
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    		// TODO Auto-generated method stub
		String product_id = request.getParameter("product_id");
		PrintWriter out = response.getWriter();
		try {
			DBConnector db = new DBConnector("jdbc:mysql://localhost:3306", "root", "");
			Statement stmt = db.getConnection().createStatement();
			out.print("Search performed. Matches listed below.<br>");
			ResultSet resultSet = stmt.executeQuery(""
					+"SELECT * FROM products.products "
					+"WHERE product_id = " + product_id + ";");
			if(resultSet.next()) {
				out.print("<br>Your search returned a result:");
				out.println("<table><tr><th>Product ID</th><th>Product Name</th><th>Price</th><tr>");
				out.print("<tr><td>"+ resultSet.getString("product_id")+ "</td><td>" + resultSet.getString("name")+ "</td><td>" + resultSet.getString("price") + "</td><tr></table>");
				
			}
			else {
				out.print("<br>Your search did not return any results.");
			}
		}
		catch(Exception e) {
			out.print("<h1> :( </h1>There was a problem connecting with the database.\nPlease make sure you have MySQL running on port 3306 and that you have a user with the name \"root\" and password \"\"");
			out.print("<br>" + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
    	out.print("Unintended post operation. Please use the form at the parent url.");
    	
	}

}
