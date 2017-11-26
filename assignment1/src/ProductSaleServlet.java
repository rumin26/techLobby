import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class ProductSaleServlet extends HttpServlet {
	
	

	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
     
		//loadDataFromXML();
	 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String productType = "";
		productType = request.getParameter("productType");
		
		String type="";
		productType = request.getParameter("type");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"InventoryServlet\">Inventory</a></li>");
		out.println("<li><a href=\"InventoryChart\">Bar Chart Analysis</a></li>");
		out.println("<li><a href=\"ProductSaleServlet\">Products On Sale</a></li>");
		out.println("<li><a href=\"ProductsRebateServlet\">Products With Manufacturer Sales</a></li>");
		//out.println("<li><a href=\"SalesServlet\">Sales Report</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Products On Sale</h3>");
		out.println("<fieldset>");
		
		//----- Printing Smartphones
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Phones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");;
		
		LinkedHashMap<String, ArrayList<Object>> salePhones;
		salePhones = MySqlDataStoreUtilities.getSalePhones();
		for(Map.Entry<String, ArrayList<Object>> m :salePhones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				out.println("</tr>");
				
			}
		
			
		
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing SmartWatch
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Laptops</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> saleLaptops;
		saleLaptops = MySqlDataStoreUtilities.getSaleLaptops();
		for(Map.Entry<String, ArrayList<Object>> m :saleLaptops.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				
				out.println("</tr>");
				
			}
			
			

		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		//-----Printing Laptops
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Smart Watches</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> saleWatches;
		saleWatches = MySqlDataStoreUtilities.getSaleWatches();
		for(Map.Entry<String, ArrayList<Object>> m :saleWatches.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing Speaker
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Speakers</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> saleSpeakers;
		saleSpeakers = MySqlDataStoreUtilities.getSaleSpeakers();
		for(Map.Entry<String, ArrayList<Object>> m :saleSpeakers.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing Headphones
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Headphones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> saleHeadphones;
		saleHeadphones = MySqlDataStoreUtilities.getSaleHeadphones();
		for(Map.Entry<String, ArrayList<Object>> m :saleHeadphones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing ExternalStorage
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">External Storages</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> saleStorages;
		saleStorages = MySqlDataStoreUtilities.getSaleStorages();
		for(Map.Entry<String, ArrayList<Object>> m :saleStorages.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		//----- Printing Accessories
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Accessories</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td><td><b>Original Price</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> saleAccessories;
		saleAccessories = MySqlDataStoreUtilities.getSaleAccessories();
		for(Map.Entry<String, ArrayList<Object>> m :saleAccessories.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				float original = (Float)values.get(3);
				
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("<td>"+"$"+original+"</td>");
				out.println("</tr>");
				
			}
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


