import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class InventoryServlet extends HttpServlet {
	


	
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
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Product List</h3>");
		out.println("<fieldset>");
		
		//----- Printing Smartphones
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Phones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> phones;
		phones= MySqlDataStoreUtilities.getPhones();
		
		for(Map.Entry<String, ArrayList<Object>> m :phones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='phone'>");
		
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
			
		}
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing SmartWatch
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Smart Watch</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> watches;
		watches= MySqlDataStoreUtilities.getWatches();
			
			//System.out.println(phones);
			
		for(Map.Entry<String, ArrayList<Object>> m :watches.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='smartwatch'>");
			
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		//-----Printing Laptops
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Laptops</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> laptops;
		laptops= MySqlDataStoreUtilities.getLaptops();
			
			//System.out.println(phones);
			
		for(Map.Entry<String, ArrayList<Object>> m :laptops.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='laptop'>");
			
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing Speaker
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Speakers</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
	
			
			
		LinkedHashMap<String, ArrayList<Object>> speakers;
		speakers= MySqlDataStoreUtilities.getSpeakers();
			
			//System.out.println(phones);
			
		for(Map.Entry<String, ArrayList<Object>> m :speakers.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='speaker'>");
			
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing Headphones
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Headphones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> headphones;
		headphones= MySqlDataStoreUtilities.getHeadphones();
			
			//System.out.println(phones);
			
		for(Map.Entry<String, ArrayList<Object>> m :headphones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='headphone'>");
			
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing ExternalStorage
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">External Storages</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> storages;
		storages= MySqlDataStoreUtilities.getStorages();
			
			//System.out.println(phones);
			
		for(Map.Entry<String, ArrayList<Object>> m :storages.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='externalstorage'>");
			
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		//----- Printing Accessories
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Accessories</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity Available</b></td></tr>");
		
		LinkedHashMap<String, ArrayList<Object>> acc;
		acc= MySqlDataStoreUtilities.getAccessories();
			
			//System.out.println(phones);
			
		for(Map.Entry<String, ArrayList<Object>> m :acc.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				String image = (String)values.get(0);
				String name = (String)values.get(1);
				String company = (String)values.get(2);
				String condition = (String)values.get(3);
				float price = (Float)values.get(4);
				String color = (String)values.get(5);
				int quantity = (Integer)values.get(6);
				String retailer = (String)values.get(7);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='accessory'>");
			
			out.println("<td>"+name+"</td>");
			out.println("<td>"+"$"+price+"</td>");
			out.println("<td>"+quantity+"</td>");
			out.println("</tr>");
			out.println("</form>");
			
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


