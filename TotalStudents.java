import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class TotalStudents extends HttpServlet {
    private Connection con;

    public void init() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HimanshuGupta", "him123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as total_students FROM Students");
            PreparedStatement ps1 = con.prepareStatement("SELECT id, name FROM Students");
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            out.println("<html><body>");
            if (rs.next()) {
                int totalStudents = rs.getInt("total_students");
                out.println("<h1 style='color: red; text-align: center;'>Total number of students: " + totalStudents + "</h1>");
                out.println("<br>");
            }
            out.println("<h2>Student List:</h2>");
            while (rs1.next()) {
                int id1 = rs1.getInt("ID");
                String name1 = rs1.getString("NAME");
                out.println("<p>ID: " + id1 + ", Name: " + name1 + "</p>");
            }
            out.println("<a href='index.html' style='background-color: lightgrey; border: solid 1px lightgrey; padding: 10px; font-size: 10px; margin: 3px;'>HOME</a>");
            out.println("</body></html>");
            rs.close();
            ps.close();
            rs1.close();
            ps1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
