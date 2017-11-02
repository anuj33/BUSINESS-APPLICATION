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
@WebServlet(urlPatterns = { "/AutoCompleteCustName" })
public class AutoCompleteCustName extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public AutoCompleteCustName()
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
        // String[] ids = s.split("$$",1);
        // s = ids[0];
        // int firmId = Integer.parseInt(ids[1]);
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
             if(  v.get(i).getName().startsWith(s))
             {
                 out.print(v.get(i).getName() + ":");
             }
         }
    }
}
