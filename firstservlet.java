import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class firstservlet extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("Name");
                String people=request.getParameter("People");
                String date=request.getParameter("Date");
                String message=request.getParameter("Message");
                
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/catering","root","");
                    PreparedStatement st=con.prepareStatement("insert into reserve values(?,?,?,?)");
                    st.setString(1,name);
                    st.setString(2,people);
                    st.setString(3,date);
                    st.setString(4,message);
                    int result=st.executeUpdate();
                    if(result==1)
                    {
                       out.println("success");   
                       RequestDispatcher rd=request.getRequestDispatcher("index.html");
                       rd.forward(request, response);
                    }
                    else
                    {
                        out.print("failed");
                    }
                    
                }
                catch(Exception e)
                {
                    out.print("error");
                }
		
	}
}