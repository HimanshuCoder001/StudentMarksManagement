import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddStudent extends HttpServlet
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
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter out= res.getWriter();
		res.setContentType("text/html");
		
		/* ------ taking input------*/
		
		String name= req.getParameter("StName");
		String id= req.getParameter("StId");
		String cent=req.getParameter("center");
		
		/*--------marks----------*/
		int hin= Integer.parseInt(req.getParameter("hindi"));
		int eng= Integer.parseInt(req.getParameter("english"));
		int math= Integer.parseInt(req.getParameter("math"));
		int sci= Integer.parseInt(req.getParameter("science"));
		int sst= Integer.parseInt(req.getParameter("sst"));
		int comp= Integer.parseInt(req.getParameter("computer"));
		
		try{
			PreparedStatement ps= con.prepareStatement("insert into students values(?,?,?,?,?,?,?,?,?)");
			
			/*-----upload to database----*/
			ps.setString(1,name);
			ps.setString(2,id);
			ps.setString(3,cent);
			ps.setInt(4,hin);
			ps.setInt(5,eng);
			ps.setInt(6,math);
			ps.setInt(7,sci);
			ps.setInt(8,sst);
			ps.setInt(9,comp);
			
			int i= ps.executeUpdate();
			if(i==1)
			{
				out.println("<h1 style=color:green; text-align:center;>Added Successfully..</h1>");
				out.println("<br><br>");
				out.println("<a href='add.html' style= border: solid green 1px; font-size:15px; text-align:center;>Add another Student</a>");
				out.println("<br><br>");
				
				out.println("<a href='index.html' style= border:solid green 1px; font-size:15px bold; color:white; background-color:blue; text-align:center;>Home</a>");
		    }
			else{
				out.println("<h1> failed to upload</h1>");
				out.println("<br>");
				out.println("<a href='index.html' style= border:solid green 1px; font-size:15px; color:white; background-color:blue; text-align:center;>Home</a>");
				}
				
				ps.close();
				
		}
		catch(SQLException e){}
		
		/*------close connection-----*/
	}
	public void destroy()
	{
		try{
			
			con.close();
		}catch(Exception e){}
		
		System.out.println("destroy");
	}
}