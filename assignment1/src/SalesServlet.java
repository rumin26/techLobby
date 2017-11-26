import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class SalesServlet extends HttpServlet {

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
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Sold Products and Their Sales</h3>");
		out.println("<fieldset>");
		
		//----- Printing Products
		
		out.println("<div id=\"body\"><article>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Items Sold</b></td><td><b>Total Sales</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> soldProductsSales;
		soldProductsSales = MySqlDataStoreUtilities.getSoldProductsSales();
		
		String itemName;
		float itemPrice;
		int itemQty;
		//float sales;
		for(Map.Entry<String, ArrayList<Object>> m :soldProductsSales.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				itemName = (String)values.get(0);
				itemPrice = (Float)values.get(1);
				itemQty = (Integer)values.get(2);
				double sales = Math.floor(itemPrice * itemQty * 100.0) / 100.0 ;
				
				out.println("<tr><td>"+itemName+"</td>");
				out.println("<td>"+"$"+itemPrice+"</td>");
				out.println("<td>"+itemQty+"</td>");
				out.println("<td>"+"$"+sales+"</td></tr>");
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
