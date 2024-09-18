import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class UpdateStudent2 extends HttpServlet
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
		
		HttpSession session= req.getSession();
		String stName= (String)session.getAttribute("name");
		String stId= (String)session.getAttribute("id");
		        
				
				String name1= req.getParameter("StName");
				String center1= req.getParameter("center");
		        int hin= Integer.parseInt(req.getParameter("hindi"));
		        int eng= Integer.parseInt(req.getParameter("english"));
	            int math= Integer.parseInt(req.getParameter("math"));
	            int sci= Integer.parseInt(req.getParameter("science"));
	            int sst= Integer.parseInt(req.getParameter("sst"));
		        int comp= Integer.parseInt(req.getParameter("computer"));
		try{
			PreparedStatement ps = con.prepareStatement("UPDATE students SET name=?, center=?, hindi=?, english=?, math=?, science=?, sst=?, computer=? WHERE name=? AND id=?");
			
			    ps.setString(1, name1);
                ps.setString(2, center1);
                ps.setInt(3, hin);
                ps.setInt(4, eng);
                ps.setInt(5, math);
                ps.setInt(6, sci);
                ps.setInt(7, sst);
                ps.setInt(8, comp);
                ps.setString(9, stName); 
                ps.setString(10, stId); 
			
			    int i= ps.executeUpdate();
				
				if(i==1)
				{
					out.println("<h1 style=color:green; text-align:center;>Updated Successfully..</h1>");
				    out.println("<br><br>");
				
				    out.println("<a href='index.html' style= border:solid green 1px; font-size:15px bold; color:white; background-color:blue; text-align:center;>Home</a>");
					ps.close();
					con.close();
				}
				
				else{
				out.println("<h1> failed to upload</h1>");
				out.println("<br>");
				out.println("<a href='index.html' style= border:solid green 1px; font-size:15px; color:white; background-color:blue; text-align:center;>Home</a>");
				}
				
		      }
		        catch(Exception e){}	
	}
}