import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class DailySalesServlet extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"SalesServlet\">Sales Report</a></li>");
		out.println("<li><a href=\"SalesChart\">Bar Chart</a></li>");
		out.println("<li><a href=\"DailySalesServlet\">Daily Sales</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Daily Sales</h3>");
		out.println("<fieldset>");
		
		//----- Printing Products
		
		out.println("<div id=\"body\"><article>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Date</b></td><td><b>Total Sales</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> dailySales;
		dailySales = MySqlDataStoreUtilities.getDailySales();
		
		String date_field;
		float totalSales;
		
		//float sales;
		for(Map.Entry<String, ArrayList<Object>> m :dailySales.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				date_field = (String)values.get(0);
				totalSales = (Float)values.get(1);
								
				out.println("<tr><td>"+date_field+"</td>");
				out.println("<td>"+"$"+totalSales+"</td></tr>");
			}
			
		
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}
