import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class UpdateStudent extends HttpServlet
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
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		String name1= req.getParameter("StName");
		String id1= req.getParameter("StId");
		
		
		try{
			PreparedStatement ps= con.prepareStatement("select * from students where name=? and id=?");
			
			ps.setString(1, name1);
			ps.setString(2, id1);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next())
			{
				HttpSession session= req.getSession();
				session.setAttribute("name",name1 );
				session.setAttribute("id", id1);
				
				RequestDispatcher rd= req.getRequestDispatcher("/update1.html");	
		        out.println("<h4 style=color:white; padding: 0 8px 5px 0;>Student is valid  </h4>\n");
				out.println("<br>");
				out.println("<h4 style=color:white; padding: 0 8px 5px 0;> Name  : "+  name1  +"</h4>\n");
				out.println("<br>");
				out.println("<h4 style=color:white; padding: 0 8px 5px 0;> ID  : "+   id1   +"</h4>\n");
				
				rd.include(req,res);
				
		
			    ps.close();
		   }
	}catch(Exception e){}
}

}