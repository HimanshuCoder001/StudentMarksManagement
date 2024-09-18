import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class SearchStudent extends HttpServlet
{
	private Connection con;
	public void init()
	{
		System.out.println("init con");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","HimanshuGupta","him123");
		}catch(Exception e){}
	}
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		//String name1= req.getParameter("StName");
		String id1= req.getParameter("StId");
		
		PrintWriter out= res.getWriter();
		res.setContentType("text/html");
		try
		{
			//System.out.println("connect");
			PreparedStatement ps= con.prepareStatement("select * from students where id=?");

			//ps.setString(1, name1);
			ps.setString(1, id1);
			ResultSet rs= ps.executeQuery();
			if(rs.next())
			{
			/*-----getting marks-------*/
            String name1= rs.getString("name");
			String cen= rs.getString("center");
			int hin= rs.getInt("hindi");
			int eng= rs.getInt("english");
			int mat= rs.getInt("math");
			int sci= rs.getInt("science");
			int sst= rs.getInt("sst");
			int comp= rs.getInt("computer");
			
			/*-----------find percentage----------*/
			int total= hin+eng+mat+sci+sst+comp;
			float average= total/6;
			
			/*---------------HTML-------------*/
			/*-------print marks-------*/
			/*--------CSS------------*/
			out.println("<html><head><style>");
			out.println("body { background: linear-gradient(#141e30, #243b55);}");
			out.println("th, td { border: 2px solid white; padding: 10px; text-align: center;}");
			out.println("thead { background-color: lightgrey;}");
			out.println("tbody { background-color: lightblue;}");
			out.println("h4 { color:white; padding: 0 8px 5px 0;}");
			out.println("h3 { color:white; margin: 0 40% 5px;}");
		
			out.println("</style></head><body>");
			
			/*------------HTML------------*/
			out.println("<h4>Student Name:    " + name1 + "</h4>");
			
			out.println("<h4>Student ID:      " + id1 + "</h4>");
			
			out.println("<h4>  Center:     " + cen + "</h4>");
			
			out.println("<table style=\"border: 2px solid white; padding: 10px; border-collapse: collapse; width: 70%;\"><thead>");
			out.println("<tr><th>SUBJECTS</th>");
			out.println("<th>Hindi</th>");
			out.println("<th>English</th>");
			out.println("<th>Math</th>");
			out.println("<th>Science</th>");
			out.println("<th>SST</th>");
			out.println("<th>Computer</th></tr></thead>");
			out.println("<tbody><tr><th>MARKS</th>");
			out.println("<td>"+hin+"</td>");
			out.println("<td>"+eng+"</td>");
			out.println("<td>"+mat+"</td>");
			out.println("<td>"+sci+"</td>");
			out.println("<td>"+sst+"</td>");
			out.println("<td>"+comp+"</td>");
			out.println("</tbody></table>");
			out.println("</body></html>");
			out.println("<br>");
			if(average>=35)
			{
				out.println("<h3 style= text-align :center; margin-left: 40%;>RESULT : "+ average + " % <br> Passed</h3>");
			}
			else{
				out.println("<h3>RESULT :" + average + "%<br>  Failed</h3>");
			}
			out.println("<a href='search.html' style= background:#fff; border: solid 1px: lightgrey; padding: 5px; margin: 8px; font-size:10px;>check another</a>");
				out.println("<br>");
				
				out.println("<a href= 'index.html' style= background:#fff; border: solid 1px: lightgrey; font-size:10px; padding: 5px; margin: 5px;>HOME</a>");
					
				
			}else{
				out.println("<h2>Student not found!");
				out.println("<a href='search.html' style= background:#fff; border: solid 1px: lightgrey; font-size:10px;>Search Again</a>");
				out.println("<br>");
				
				out.println("<a href= 'index.html' style= background:#fff; border: solid 1px: lightgrey; font-size:10px; margin: 3px;>HOME</a>");
			}
			ps.close();
			System.out.println("ps close");
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