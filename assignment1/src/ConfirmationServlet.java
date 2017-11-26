import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ConfirmationServlet extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init()
	{
		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container1\"><header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2>");
		out.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(firstName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}
		
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Phones\">Phones</a></li>");
		
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=SmartWatches\">Smart Watches</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Speakers\">Speakers</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Headphones\">Headphones</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=ExternalStorages\">External Storages</a></li>");
		
		out.println("<li><a href=\"#\">Accessories</a></li>");
		
		if(firstName != null && !firstName.isEmpty())
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
		
				String shippingAddress = request.getParameter("shippingAddress");
		String fname = request.getParameter("firstName");
		String phoneNumber = request.getParameter("phoneNumber");
		String emailId = request.getParameter("emailId");
		String cardHolderName = request.getParameter("cardHolderName");
		String ccNumber = request.getParameter("ccNumber");
		String expDate = request.getParameter("expDate");
		String cvv = request.getParameter("cvv");
		
		//Float finalAmount = request.getParameter("finalAmount");
		//float finalAmount = Float.parseFloat(request.getParameter("finalAmount"));
		
		Random r = new Random();
		int Low = 1;
		int High = 572431;
		int R = r.nextInt(High-Low) + Low;
		String orderId = "B#"+R;
		
		int C = r.nextInt(High-Low);
		String confirmationNo = "C#"+R;
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = new Date();
		String orderDate = dateFormat.format(today).toString();
		String dateForm = "MM/dd/yyyy"; 
		SimpleDateFormat sd = new SimpleDateFormat(dateForm);
		

		String date_field = sd.format(today);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 14);
		Date date = cal.getTime();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		String deliverydate = sdf.format(date);
		
		Cart ekart;
        ekart = (Cart) session.getAttribute("cart");
		
		HashMap<String, List<Object>> items = ekart.getCartItems();
		String userid=(String)session.getAttribute("userid");
		
		double totalAmount=ekart.getTotalAmount();
		
		ArrayList<String> orderItems = new ArrayList<String>();
		String orderInfo;
		
		String itemName = "";
		float itemPrice;
		int itemQty;
		String itemType="";
		int availqty = 0;
		for(HashMap.Entry<String, List<Object>> entry : items.entrySet()){
			
			String key = entry.getKey();
			List<Object> values = entry.getValue();	
			
			itemName = (String)values.get(0);
			itemPrice = (Float)values.get(1);
			itemQty = (Integer)values.get(2);
			itemType = (String)values.get(4);
			availqty = (Integer)values.get(5);
			int qty = availqty -1;
			System.out.println(itemType);
			//To insert every item in MySql table order_item
			MySqlDataStoreUtilities.insertOrderItem(itemType, itemName, orderId, itemPrice, itemQty, orderDate, deliverydate, userid, shippingAddress,date_field);
			MySqlDataStoreUtilities.updateSoldItem(itemType, itemName, qty);
			
			orderInfo = (String)values.get(0);
			//System.out.println("Item: "+orderInfo);
			orderItems.add(orderInfo);
		}
		
		//This code is to insert order in MySQL Table
		//MySqlDataStoreUtilities.insertOrderItem(itemName, orderId, itemPrice, itemQty, orderDate, deliverydate);
		MySqlDataStoreUtilities.insertOrderTotal(orderId, orderDate, deliverydate, totalAmount,userid, shippingAddress);
		//Code for MySql ends here
		
		
		//This is the code to insert order with serialized method 
		System.out.println("Order Items: "+orderItems);
		System.out.println("Shipping Address: "+shippingAddress);
		order = new Order(orderId, confirmationNo, userid, orderDate, deliverydate, shippingAddress, totalAmount, orderItems);
		System.out.println("Shipping Address: "+shippingAddress);
		
		//order.setItemsHashMap(items);
		
		//System.out.println("Items Hashmap: "+order.getItemsHashMap());
		System.out.println("Order Items in Order Object: "+order.getOrderItems());
		System.out.println("User Id in Order Object: "+order.getcustomerEmailId());
		
		orders = ods.getOrderHashMap();
		System.out.println("Orders: "+orders);
		
		orders.put(order.getorderId(), order);
		ods.writeOrderHashMap(orders);
		//Code for serialized order ends here
		
	
		
		session.removeAttribute("cart");
		out.println("<h3><br><br> Thank You For Shopping With Us!! <br><br>Your Order No "+orderId+" has been Placed Succesfully. The order will be delivered by " + deliverydate +" </h3><br><br>");	
		
		//printSideBar(out);
		
		out.close();
	
}

public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
}


}
