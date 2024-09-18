import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class DeleteStudent extends HttpServlet
{
	private Connection con;
	public void init()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","HimanshuGupta","him123");
		}catch(Exception e){}
	}
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		String name1= req.getParameter("StName");
		String id1= req.getParameter("StId");
		try
		{
		PreparedStatement ps= con.prepareStatement("delete from students where name=? and id=?");
		
		ps.setString(1, name1);
		ps.setString(2, id1);
		
		int i= ps.executeUpdate();
		if(i==1)
			{
				out.println("<h1 style=color:green; text-align:center;>Deleted Successfully..</h1>");
				out.println("<br><br>");
				out.println("<a href='delete.html' style= border: solid green 1px; font-size:15px; text-align:center;>Delete another Student</a>");
				out.println("<br><br>");
				
				out.println("<a href='index.html' style= border:solid green 1px; font-size:15px bold; color:white; background-color:blue; text-align:center;>Home</a>");
		    }
			else{
				out.println("<h1> failed to delete</h1>");
				out.println("<br>");
				out.println("<a href='index.html' style= border:solid green 1px; font-size:15px; color:white; background-color:blue; text-align:center;>Home</a>");
				}
				
				ps.close();
		}catch(SQLException e){}
		
	}
	public void destroy()
	{
		try{
			
			con.close();
		}catch(Exception e){}
		
		System.out.println("destroy");
	}
}