

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet(urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String name = request.getParameter("name");
		String username = request.getParameter("name");
		String firmname = request.getParameter("name");
		String bankDetails = request.getParameter("name");
		String phoneno = request.getParameter("name");

		String gstn = request.getParameter("gstn");
		String address= request.getParameter("tinn");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String extra = request.getParameter("extra");
		
		
		Register r = new Register(username,password,firmname,gstn,address,bankDetails,phoneno,email,1,true,true,true,true);
                response.sendRedirect("index.html");
                r.addFirm();


	}

}
