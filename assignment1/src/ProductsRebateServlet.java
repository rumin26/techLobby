import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class ProductsRebateServlet extends HttpServlet {
	
	
	
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
		out.println("<li><a href=\"ProductsRebateServlet\">Products With Manufacturer Rebates</a></li>");
		//out.println("<li><a href=\"SalesServlet\">Sales Report</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Products With Manufacturer Rebates</h3>");
		out.println("<fieldset>");
		
		//----- Printing Smartphones
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Phones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebatePhones;
		rebatePhones = MySqlDataStoreUtilities.getRebatePhones();
		for(Map.Entry<String, ArrayList<Object>> m :rebatePhones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("</tr>");
				
			}
		
			
		
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing SmartWatch
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Laptops</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebateLaptops;
		rebateLaptops = MySqlDataStoreUtilities.getRebateLaptops();
		for(Map.Entry<String, ArrayList<Object>> m :rebateLaptops.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("</tr>");
				
			}
			
			

		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		//-----Printing Laptops
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Smart Watches</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebateWatches;
		rebateWatches = MySqlDataStoreUtilities.getRebateWatches();
		for(Map.Entry<String, ArrayList<Object>> m :rebateWatches.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing Speaker
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Speakers</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebateSpeakers;
		rebateSpeakers = MySqlDataStoreUtilities.getRebateSpeakers();
		for(Map.Entry<String, ArrayList<Object>> m :rebateSpeakers.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing Headphones
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Headphones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebateHeadphones;
		rebateHeadphones = MySqlDataStoreUtilities.getRebateHeadphones();
		for(Map.Entry<String, ArrayList<Object>> m :rebateHeadphones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing ExternalStorage
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">External Storages</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebateStorages;
		rebateStorages = MySqlDataStoreUtilities.getRebateStorages();
		for(Map.Entry<String, ArrayList<Object>> m :rebateStorages.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
				out.println("</tr>");
				
			}
			
			
		
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		//----- Printing Accessories
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Accessories</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Company</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> rebateAccessories;
		rebateAccessories = MySqlDataStoreUtilities.getRebateAccessories();
		for(Map.Entry<String, ArrayList<Object>> m :rebateAccessories.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(0);
				float price = (Float)values.get(1);
				String company = (String)values.get(2);
				out.println("<td>"+name+"</td>");
				out.println("<td>"+"$"+price+"</td>");
				out.println("<td>"+company+"</td>");
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


