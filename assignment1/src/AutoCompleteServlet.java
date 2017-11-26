import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Iterator;

public class AutoCompleteServlet extends HttpServlet {

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
		
		System.out.println(productsMap);
		
		}catch(Exception E){
		System.out.println("Exception");
		}
	}


    private ServletContext context;

    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
				
		loadDataFromMySQL();

        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
           // context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator it = productsMap.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                    Product product = (Product) productsMap.get(id);

                    if ( // targetId matches product name
                         product.getName().toLowerCase().startsWith(targetId) ||
                         // targetId matches product company
                         product.getCompany().toLowerCase().startsWith(targetId)
                         )
					 {

                        sb.append("<product>");
                        sb.append("<id>" + product.getId() + "</id>");
                        sb.append("<name>" + product.getName() + "</name>");
                        sb.append("<company>" + product.getCompany() + "</company>");
                        sb.append("</product>");
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

            // put the target composer in the request scope to display 
            //if ((targetId != null) && productsMap.containsKey(targetId.trim())) {
                request.setAttribute("product", productsMap.get(targetId));
				request.setAttribute("productName", targetId);
                context.getRequestDispatcher("/ShowSearchedProductServlet").forward(request, response);
				//context.getRequestDispatcher("/Home.jsp").forward(request, response);
            //}
        }
    }
}
