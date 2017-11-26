import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AddProduct extends HttpServlet {
	
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
		
		String type="";
		type = request.getParameter("type");
		
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
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Add Product</h3>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		
		out.println("<form action='/assignment1/AddProductToXML' method=\"post\">");
		
		out.println("<p><label>Product Type:</label>");
		out.println("<select name='productType'><option name='productType' value='Phone' selected>Phones</option><option name='productType' value='Laptop'>Laptops</option>");
		out.println("<option name='productType' value='Smart Watch'>Smart Watches</option><option name='productType' value='Speaker'>Speakers </option><option name='productType' value='Headphone'>Headphones </option><option name='productType' value='External Storage'>External Storages </option><option name='productType' value='Accessory'>Accessories </option></select></p>");
		
		out.println("<p><label>Id:</label>Will be generated automatically</p>");
		out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" /></p>");
		out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" /></p>");
		out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" /></p>");
		out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" /></p>");
		out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" /></p>");
		out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" /></p>");
		out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" /></p>");
		out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" /></p>");

		out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add Product\" type=\"submit\" /></p>");
		out.println("</form>");
		
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


