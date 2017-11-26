import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ContentServlet extends HttpServlet
{
	/*ArrayList<Object> products;
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
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//loadDataFromXML();
		
		HttpSession session = request.getSession();
		String fname = null;
		fname=(String)session.getAttribute("firstName");
		
		String productType = request.getParameter("productType");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		
		if(fname != null && !fname.isEmpty())
		{
			out.println("<body onload='init()'><div id=\"container1\"><header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2>");
		out.println("<form  name='autofillform1' action='autocomplete'>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(fname);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else{
			out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2>");
		out.println("<form  name='autofillform1' action='autocomplete'>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"Home.html\">Home</a></li>");
		}
		
		
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Phones\">Phones</a></li>");
		
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=SmartWatches\">Smart Watches</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Speakers\">Speakers</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Headphones\">Headphones</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=ExternalStorages\">External Storages</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Accessories\">Accessories</a></li>");

		
		//out.println("<li><a href=\"#\">Cart(0)</a></li>");
		//out.println("<li><a href=\"#\">Your Orders</a></li>");
		
		if(fname != null && !fname.isEmpty())
		{
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
			out.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"LoginServlet\">Login</a></li>");
			out.println("<li><a href=\"SignUp.html\">SignUp</a></li>");
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
		}
		//out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
		
		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		
		
		if(productType.equals("Phones"))
		{
			out.println("<article><h3>Phones</h3></article>");
			out.println("<article class=\"expanded\">");
			
			
			LinkedHashMap<String, ArrayList<Object>> phones;
			phones= MySqlDataStoreUtilities.getPhones();
			
			System.out.println(phones);
			
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
				
			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
		}
	
			/*for(Map.Entry<String,Phone> m :phones.entrySet()){
			Phone s = m.getValue();
			
			
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(s.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+s.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			
			}
		}*/
		
		if(productType.equals("SmartWatches"))
		{
			out.println("<article><h3>Smart Watches</h3></article>");
			out.println("<article class=\"expanded\">");
			
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

			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
			
			/*for(Map.Entry<String,SmartWatch> m :smartwatches.entrySet()){
			SmartWatch t = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(t.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(t.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(t.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(t.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(t.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(t.getPrice());
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ t.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+t.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+t.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+t.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+t.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+t.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+t.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+t.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ t.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+t.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+t.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+t.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+t.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+t.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+t.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+t.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ t.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+t.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+t.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+t.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+t.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ t.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+t.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+t.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+t.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+t.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+t.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+t.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+t.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			
			
			//out.println("<tr><td><a href=\"#\" class=\"button\">Add to Cart</a></td></tr></table></td></tr></table>");
			}*/
		}
		
		if(productType.equals("Laptops"))
		{
			out.println("<article><h3>Laptops</h3></article>");
			out.println("<article class=\"expanded\">");
			
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

			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
			
			/*for(Map.Entry<String,Laptop> m :laptops.entrySet()){
			Laptop l = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(l.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(l.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(l.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(l.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(l.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(l.getPrice());
		
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ l.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+l.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+l.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+l.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+l.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+l.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+l.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+l.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ l.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+l.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+l.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+l.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+l.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+l.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+l.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+l.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ l.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+l.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+l.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+l.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+l.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ l.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+l.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+l.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+l.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+l.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+l.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+l.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+l.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			
			
			//out.println("<tr><td><a href=\"#\" class=\"button\">Add to Cart</a></td></tr></table></td></tr></table>");
			}*/
		}
		
		if(productType.equals("Speakers"))
		{
			out.println("<article><h3>Speakers</h3></article>");
			out.println("<article class=\"expanded\">");
			
			
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

			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
			
			/*for(Map.Entry<String,Speaker> m :speakers.entrySet()){
			Speaker s = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(s.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+s.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}*/
		}
		
		if(productType.equals("Headphones"))
		{
			out.println("<article><h3>Headphones</h3></article>");
			out.println("<article class=\"expanded\">");
			
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

			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}

			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
			
			/*for(Map.Entry<String,Headphone> m :headphones.entrySet()){
			Headphone s = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(s.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+s.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}*/
		}
		
		if(productType.equals("ExternalStorages"))
		{
			out.println("<article><h3>External Storage</h3></article>");
			out.println("<article class=\"expanded\">");
			
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

			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}

			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
			
			/*for(Map.Entry<String,ExternalStorage> m :externalstorages.entrySet()){
			ExternalStorage s = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(s.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+s.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}*/
		}
		
		//Code for Accessories		
		if(productType.equals("Accessories"))
		{
			out.println("<article><h3>Accessories</h3></article>");
			out.println("<article class=\"expanded\">");
			
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

			if(sale.equals("Yes"))
			{				

			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\"><b>Original:");
			out.println("$"+original);
			}
			
			else
			{
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(image);
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(name);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(company);
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(color);
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(condition);
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table style=\"width:300px\"><tr><td><b>Price:");
			out.println("$"+price);
			out.println("</td><td style = \"text-decoration: line-through;\">");
			
			}
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+quantity+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ name +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+name+"'>");
				out.println("<input type='hidden' name='image' value='"+image+"'>");
				out.println("<input type='hidden' name='price' value='"+price+"'>");
				out.println("<input type='hidden' name='color' value='"+color+"'>");
				out.println("<input type='hidden' name='condition' value='"+condition+"'>");
				out.println("<input type='hidden' name='company' value='"+company+"'>");
				out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			}
			
			/*for(Map.Entry<String,Accessory> m :accessories.entrySet()){
			Accessory s = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
			out.println(s.getCompany());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			
			if(fname != null && !fname.isEmpty())
			{
				out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Write Review'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				
				out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("<input type='hidden' name='availqty' value='"+s.getQty()+"'>");
				out.println("</form></td></tr>");
				
				out.println("</table></td></tr></table>");
			}
			else
			{
				out.println("</b></td></tr>");
				out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
				out.println("<input type='hidden' name='productType' value='"+productType+"'>");
				out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
				out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
				out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
				out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
				out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
				out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
				out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
				out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				out.println("</form></td></tr>");
				out.println("</table></td></tr></table>");
			}
			
			
		}*/
		
		
	}
		out.println("</article></section>");
		//out.println("<div class=\"clear\"></div></div></div>");
		
		//Sidebar starts	
		out.println("<aside class=\"sidebar\">");
		
		out.println("<ul><li><h4>Trending</h4><ul>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveLiked\">Top 5 Liked Products</a></li>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveZipCodes\">Top 5 Zip Codes</a></li>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveSold\">Top 5 Sold Products</a></li></ul></li>");
		
		out.println("<ul><li><h4>Phones</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Phones&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Phones&companyType=Samsung\">Samsung</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Phones&companyType=HTC\">HTC</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Phones&companyType=Motorola\">Motorola</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Phones&companyType=LG\">LG</a></li></ul></li>");
		
		out.println("<ul><li><h4>Laptops</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Dell\">Dell</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=HP\">HP</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Lenovo\">Lenovo</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Samsung\">Samsung</a></li></ul></li>");
		
		out.println("<ul><li><h4>Smart Watches</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=SmartWatches&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=SmartWatches&companyType=Samsung\">Samsung</a></li><li><a href=\"CompanyWiseProductsServlet?productType=SmartWatches&companyType=LG\">LG</a></li><li><a href=\"CompanyWiseProductsServlet?productType=SmartWatches&companyType=Asus\">Asus</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=SmartWatchess&companyType=Motorola\">Motorola</a></li></ul></li>");
		
		out.println("<ul><li><h4>Speakers</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Speakers&companyType=LG\">LG</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Speakers&companyType=Sony\">Sony</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Speakers&companyType=Beats\">Beats</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Speakers&companyType=JBL\">JBL</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Speakers&companyType=Samsung\">Samsung</a></li></ul></li>");
		
		
		out.println("<ul><li><h4>Headphones</h4><ul><li><a href=\"CompanyWiseProductsServlet?productType=Headphoness&companyType=LG\">LG</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Headphoness&companyType=Samsung\">Samsung</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Headphoness&companyType=Beats\">Beats</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Headphoness&companyType=Scull Candy\">Scull Candy</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Headphoness&companyType=Sony\">Sony</a></li>");
		
		out.println("<ul><li><h4>External Storages</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=ExternalStorages&companyType=FlashDrive\">Flash Drive</a></li><li><a href=\"CompanyWiseProductsServlet?productType=ExternalStorages&companyType=HardDrive\">Hard Drive</a></li><li><a href=\"CompanyWiseProductsServlet?productType=ExternalStorages&companyType=MemoryCard\">Memory Card</a></li>");
		out.println("</ul></li>");
		
		
		out.println("</ul></li></aside><div class=\"clear\"></div></div>");
		
	//Footer starts	
		out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
		out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
		out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
		out.println("<p>&copy; techLobby 2017. by Rumin Shah</p></div></footer></div>");
		out.println("</body></html>");
		
		out.close();
	}
	
}
