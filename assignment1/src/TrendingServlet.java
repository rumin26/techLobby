import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TrendingServlet extends HttpServlet
{
	
	public void init()
	{
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost Start: ");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		String fname = null;
		
		if(session != null)
		{
			fname=(String)session.getAttribute("firstName");
		}
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		
		
		if(fname != null && !fname.isEmpty() && session != null)
		{
			out.println("<body><div id=\"container1\"><header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2>");
			out.println("</header>");
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(fname);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else
		{
			out.println("<body><div id=\"container\"><header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2>");
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
		
//------------------------------------------------------------------------------------------------------------------------------------------
		
	//Body starts
	
		out.println("<div id=\"body\"><section id=\"content\">");
		
		String trendType = request.getParameter("trendType");
		
		//Top 5 most like products code here
		if(trendType.equals("topFiveLiked"))
		{
			LinkedHashMap<String, Double> top5LikedProducts;
			top5LikedProducts = MongoDBDataStoreUtilities.getTop5LikedProducts();
			
			out.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
			out.println("<table>");
			out.println("<tr><td><b>Product Name</b></td><td><b>Average Rating</b></td></tr>");
			
			for(Map.Entry<String, Double> m :top5LikedProducts.entrySet())
			{
				String key = m.getKey();
				Double value = m.getValue();
				
				out.println("<tr><td>"+key+"</td>");
				out.println("<td>"+value+"</td></tr>");
			}
			
			out.println("</table>");
		}
		
		//Top 5 zip codes code
		if(trendType.equals("topFiveZipCodes"))
		{
			LinkedHashMap<String, Integer> top5ZipCodes;
			top5ZipCodes = MongoDBDataStoreUtilities.getTop5ZipCodes();
			
			out.println("<h3 align='center'>Top 5 Zip Codes Where Maximum Number Of Products Sold</h3>");
			out.println("<table>");
			out.println("<tr><td><b>Zip Code</b></td><td><b>Products Sold In This Zip Code</b></td></tr>");
			
			for(Map.Entry<String, Integer> m :top5ZipCodes.entrySet())
			{
				String key = m.getKey();
				Integer value = m.getValue();
				
				out.println("<tr><td>"+key+"</td>");
				out.println("<td>"+value+"</td></tr>");
			}
			
			out.println("</table>");
		}
		
		//Top 5 sold Products code
		if(trendType.equals("topFiveSold"))
		{
			LinkedHashMap<String, ArrayList<Object>> top5SoldProducts;
			top5SoldProducts = MySqlDataStoreUtilities.getTop5SoldProducts();
			
			String itemName;
			float itemPrice;
			int itemQty;
			
			out.println("<h3 align='center'>Top 5 Sold Products</h3>");
			out.println("<table>");
			out.println("<tr><td><b>Product Name</b></td><td><b>Product Price</b></td><td><b>Quantity Sold</b></td></tr>");
			
			for(Map.Entry<String, ArrayList<Object>> m :top5SoldProducts.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				itemName = (String)values.get(0);
				itemPrice = (Float)values.get(1);
				itemQty = (Integer)values.get(2);
				
				out.println("<tr><td>"+itemName+"</td>");
				out.println("<td>"+itemPrice+"</td>");
				out.println("<td>"+itemQty+"</td></tr>");
			}
			
			out.println("</table>");
			
		}
		
		out.println("</section>");
		
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
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}