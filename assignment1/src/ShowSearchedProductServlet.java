import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ShowSearchedProductServlet extends HttpServlet
{
	ArrayList<Object> products;
	HashMap<String, Phone> phones;
	HashMap<String, Laptop> laptops;
	HashMap<String, SmartWatch> smartwatches;
	HashMap<String, Speaker> speakers;
	HashMap<String, Headphone> headphones;
	HashMap<String, ExternalStorage> externalstorages;
	HashMap<String, Accessory> accessories;

	
	HashMap<String, Product> productsMap = new HashMap<String, Product>();
	
	public void init()
	{
		//loadDataFromMySQL();
	}
	
	void loadDataFromMySQL()
	{
		try{
		products = AjaxUtility.getProductsFromMySQL();
		
		phones = (HashMap<String,Phone>)products.get(0);
		smartwatches = (HashMap<String, SmartWatch>)products.get(2);
		laptops = (HashMap<String, Laptop>)products.get(1);
		speakers = (HashMap<String, Speaker>)products.get(3);
		headphones = (HashMap<String, Headphone>)products.get(4);
		externalstorages = (HashMap<String, ExternalStorage>)products.get(5);
		accessories = (HashMap<String, Accessory>)products.get(6);
		
		productsMap = (HashMap<String, Product>)products.get(7);
		
		}catch(Exception E){
		System.out.println("Exception");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		loadDataFromMySQL();
		
		HttpSession session = request.getSession(false);
		String fname = null;
		
		if(session != null)
		{
			fname=(String)session.getAttribute("firstName");
		}
		
		
		String productName = (String)request.getAttribute("productName");
		String productType = "Products";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		
		
		if(fname != null && !fname.isEmpty() && session != null)
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
		else
		{
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
			out.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}
		
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Phones\">Phones</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=SmartWatches\">Smart Watches</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Speakers\">Speakers</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Headphones\">Headphones</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=ExternalStorages\">External Storages</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Accessories\">Accessories</a></li>");
		
		if(fname != null && !fname.isEmpty() && session != null)
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
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
				
		if(productType.equals("Products"))
		{
			out.println("<article><h3>Searched Product</h3></article>");
			out.println("<article class=\"expanded\">");
			
			for(Map.Entry<String,Product> m :productsMap.entrySet()){
				
				Product s = m.getValue();
				
				String name = s.getName();
				
				if (name != null) {
					name = name.trim().toLowerCase();
				}
				
				//System.out.println("productName: "+productName);
				//System.out.println("s.getName(): "+s.getName());
				if(productName.equals(name))
				{
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
					out.println("New");
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
						out.println("<input type='hidden' name='productType' value='"+productType+"'>");
						out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
						out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
						out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
						out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
						out.println("<input type='hidden' name='quantity' value='"+1+"'>");
						out.println("<input type='hidden' name='availqty' value='"+100+"'>");
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
			
		}
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