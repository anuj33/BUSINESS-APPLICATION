

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet(urlPatterns = { "/ReportServlet" })
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Authenticate() {
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
        System.out.println("Hello Servlet");
        PrintWriter writer = response.getWriter();
        int type = request.getParameter("type");
        String startString = request.getParameter("startDate");
        String endString = request.getParameter("endDate");
        HttpSession session = request.getSession();
        Date startDate = new Date();
        Date endDate = new Date();
        try{
            SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
            startDate = ft.parse(startString);
            endDate = ft.parse(endString);
        }
        SalesReport report = new SalesReport(firmId,type,startDate,endDate);
        report.createReport();
        report.createXAxis();
        report.createYAxis();
        Vector<Double> points = report.getTransactionAmount();
        int pointsSize = points.size();
        Vector<String> xAxis =  report.getXAxis();
        Vector<String> yAxis =  report.getYAxis();
        int yAxisLength = yAxis.size();
        int xAxisLength = xAxis.size();
    }
}
