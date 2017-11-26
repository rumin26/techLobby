import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AddToCart extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html;charset=UTF-8");
		
        HttpSession session = request.getSession();
		
		
        Cart thisCart;
        thisCart = (Cart) session.getAttribute("cart");
		
        if(thisCart == null){
          thisCart = new Cart();
          session.setAttribute("cart", thisCart);
        }
		
		//Get parameters from contentServlet
        //String name = request.getParameter("name");
		String productType = request.getParameter("productType");
		System.out.println(productType);
		String productName = request.getParameter("productName");
        Float price = Float.parseFloat(request.getParameter("price"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		String image = request.getParameter("image");
		Integer availqty = Integer.parseInt(request.getParameter("availqty"));
		
        thisCart.addToCart(productName, productName, price, quantity, image, productType,availqty);
        session.setAttribute("cart", thisCart);
		
		RequestDispatcher rd = request.getRequestDispatcher("ViewCartServlet");
		rd.forward(request,response);
	
}
}
