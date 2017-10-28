import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/indexses" })
public class indexses extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public indexses()
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
        String s=request.getParameter("val");
        if(s==null || s.trim().equals(""))
        {
            out.print("Please enter id");
            return;
        }
        BusinessFirm b = new BusinessFirm();
        b.fetchCustomer();
        int n = b.getCustomerNumber();
        Vector<Customer> v = b.getCustomerList();
        s.toLowerCase();
        for (int i=0;i<n ;i++ )
        {
            if(v.get(i).getName().startsWith(s))
            {
                out.print(v.get(i).getName());
            }
        }
    }
}
