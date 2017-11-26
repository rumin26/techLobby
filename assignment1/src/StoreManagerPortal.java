import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class StoreManagerPortal extends HttpServlet {
	
	ArrayList<Object> products;
	HashMap<String, Phone> phones;
	HashMap<String, Laptop> laptops;
	HashMap<String, SmartWatch> smartwatches;
	HashMap<String, Headphone> headphones;
	HashMap<String, ExternalStorage> externalstorages;
	HashMap<String, Speaker> speakers;
	HashMap<String, Accessory> accessories;
	
	
	SAXParserForProducts sp = new SAXParserForProducts();
	
	void loadDataFromXML()
	{
		try{
			products = sp.loadDataStore();
			
			phones = (HashMap<String,Phone>)products.get(0);
		smartwatches = (HashMap<String, SmartWatch>)products.get(2);
		laptops = (HashMap<String, Laptop>)products.get(1);
		speakers = (HashMap<String, Speaker>)products.get(3);
		headphones = (HashMap<String, Headphone>)products.get(4);
		externalstorages = (HashMap<String, ExternalStorage>)products.get(5);
		accessories = (HashMap<String, Accessory>)products.get(6);
			
		}catch(Exception E){
			System.out.println("Exception");
		}
	}

	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
     
		loadDataFromXML();
	 
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
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"AddProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"InventoryServlet\">Inventory</a></li>");
		out.println("<li><a href=\"SalesServlet\">Sales Report</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Product List</h3>");
		out.println("<fieldset>");
		
		//----- Printing Smartphones
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Phones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
		/*for(Map.Entry<String,Phone> m : phones.entrySet())
		{
			Phone s = m.getValue();
			String id=s.getId();
		}*/
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
				
			//String retailer=s.getRetailer();
			/*String image=s.getImage();
			String name=s.getName();
			String company=s.getCompany();
			String condition=s.getCondition();
			String color=s.getColor();
			float price=s.getPrice();
			int quantity = s.getQty();
			String sale = s.getSale();
			String rebate = s.getRebate();
			float original = s.getOriginal();*/
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='Phone'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='Phone'>");
			
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing SmartWatch
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Smart Watch</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
				
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='Smart Watch'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='Smart Watch'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		//-----Printing Laptops
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Laptops</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='Laptop'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='Laptop'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing Speaker
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Speakers</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='Speaker'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='Speaker'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing Headphones
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Headphones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='Headphone'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='Headphone'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		//-----Printing ExternalStorage
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">External Storages</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='External Storage'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='External Storage'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		//----- Printing Accessories
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Accessories</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
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
				String sale = (String)values.get(8);
				float original = (Float)values.get(9);
				String rebate = (String)values.get(10);
			
			out.println("<form action='/assignment1/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='Accessory'>");
			//out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment1/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			//out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='quantity' value='"+quantity+"'>");
			out.println("<input type='hidden' name='sale' value='"+sale+"'>");
			out.println("<input type='hidden' name='rebate' value='"+rebate+"'>");
			out.println("<input type='hidden' name='original' value='"+original+"'>");
			out.println("<input type='hidden' name='type' value='Accessory'>");
			
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
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


