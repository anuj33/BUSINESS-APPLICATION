import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
@WebServlet(urlPatterns = { "/index" })
public class index extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public index()
    {
        ;;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		PrintWriter out = response.getWriter();
        String s=request.getParameter("val");
        if(s==null || s.trim().equals(""))
        {
            out.print("Please enter id");
            return;
        }
         BusinessFirm b = new BusinessFirm();
        System.out.println("HURRAAY HURRAY KJDjdfkwbvkjw" + s);
         b.fetchCustomer();
         int n = b.getCustomerNumber();
         Vector<Customer> v = b.getCustomerList();
         s.toLowerCase();
         for (int i=0;i<n ;i++ )
         {
             if(v.get(i).getName().startsWith(s))
             {
                 out.print(v.get(i).getName() + "<br>");
             }
         }
//        String[] str = {"anurag","akash","arindam","anuj"};
//        for (int i=0;i<4 ;i++ ) {
//            if(str[i].startsWith(s))
//            {
//                out.print(str[i]+"<br>");
//            }
//        }
    }
}
