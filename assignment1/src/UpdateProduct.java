import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class UpdateProduct extends HttpServlet {
	
	ArrayList<Object> products;
	HashMap<String, Phone> phones;
	HashMap<String, Laptop> laptops;
	HashMap<String, SmartWatch> smartwatches;
	HashMap<String, Headphone> headphones;
	HashMap<String, ExternalStorage> externalstorages;
	HashMap<String, Speaker> speakers;
	HashMap<String, Accessory> accessories;
	
	SAXParserForProducts sp = new SAXParserForProducts();
	
	String phonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/PhoneCatalog.xml";
 String smartwatchesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/SmartWatchCatalog.xml";
 String laptopsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/LaptopCatalog.xml";
 String speakerXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/SpeakerCatalog.xml";
 String headphonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/HeadphoneCatalog.xml";
 String externalstorageXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/ExternalStorageCatalog.xml";
	String accessoriesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/AccessoryCatalog.xml";
	
	
	//String itemName;
	
	
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

	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
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
		
		//----------------------------------------------------------
		
		String productType = request.getParameter("type");
		//String id = request.getParameter("id");
		String retailer = request.getParameter("retailer");
		String imagePath = request.getParameter("image");
		String productName = request.getParameter("productName");
		String company = request.getParameter("company");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		String quantity = request.getParameter("quantity");
		String sale = request.getParameter("sale");
		String rebate = request.getParameter("rebate");
		String originalPrice = request.getParameter("original");
		//System.out.println("type: "+productType);
		Float pricee = Float.parseFloat(price);
		int quant = Integer.parseInt(quantity);
		Float original = Float.parseFloat(originalPrice);

		String itemName = request.getParameter("productName");
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Update product</h3>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		
		
		if(productType.equals("Phone"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Phone"+"</p>");
			out.println("<input type='hidden' name='type' value='Phone'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");

			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Smart Watch"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Smart Watch"+"</p>");
			out.println("<input type='hidden' name='type' value='Smart Watch'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Laptop"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Laptop"+"</p>");
			out.println("<input type='hidden' name='type' value='Laptop'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Speaker"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Speaker"+"</p>");
			out.println("<input type='hidden' name='type' value='Speaker'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Headphone"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Headphone"+"</p>");
			out.println("<input type='hidden' name='type' value='Headphone'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("External Storage"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"External Storage"+"</p>");
			out.println("<input type='hidden' name='type' value='External Storage'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Accessory"))
		{
			out.println("<form action='/assignment1/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Accessory"+"</p>");
			out.println("<input type='hidden' name='type' value='Accessory'>");
			//out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><label>Quantity:</label><input name=\"quantity\" type=\"text\" value='"+quantity+"' /></p>");
		out.println("<p><label>On Sale:</label></p>");
		out.println("<select name='sale'><option name='sale' value='No' selected>No</option><option name='sale' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Rebates:</label></p>");
		out.println("<select name='rebate'><option name='rebate' value='No' selected>No</option><option name='rebate' value='Yes'>Yes</option>");
		out.println("</select></p>");
		out.println("<p><label>Original Price:</label><input name=\"original\" type=\"text\" value='"+original+"'/></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
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
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		//----------------------------------------------------------
	  
		////
		String itemName = request.getParameter("itemName");
		System.out.println(itemName);
		String productType = request.getParameter("type");
		String retailer = request.getParameter("retailer");
		String imagePath = request.getParameter("imagePath");
		String productName = request.getParameter("productName");
		String company = request.getParameter("company");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		String quantity = request.getParameter("quantity");
		String sale = request.getParameter("sale");
		String rebate = request.getParameter("rebate");
		String originalPrice = request.getParameter("original");
		//System.out.println("type: "+productType);
		Float pricee = Float.parseFloat(price);
		int quant = Integer.parseInt(quantity);
		Float original = Float.parseFloat(originalPrice);

		
		////
		MySqlDataStoreUtilities.updateProductFromMySQL(itemName, productType, retailer, imagePath, productName, company, condition, pricee, color,quant,sale,rebate,original);

		out.println("<p>Product Updated Successfully<p>");
	  
		/*if(productType.equals("Phone"))
		{
			try
			{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(phonesXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								   Node node = list.item(j);

								   if ("phoneImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("phoneName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("phoneCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("phoneCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("phonePrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("phoneColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(phonesXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>phone with id: " +id+" Updated Successfully<p>");
	  
		}
		
		if(productType.equals("Smart Watch"))
		{
			try
			{
				System.out.println("Inside smartwatch if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(smartwatchesXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("watchImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("watchName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("watchCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("watchCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("watchPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("watchColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(smartwatchesXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Watch with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Laptop"))
		{
			try
			{
				//System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(laptopsXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("laptopImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("laptopName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("laptopCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("laptopCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("laptopPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("laptopColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(laptopsXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Laptop with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Speaker"))
		{
			try
			{
				//System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(speakerXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("speakerImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("speakerName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("speakerCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("speakerCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("speakerPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("speakerColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(speakerXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>speaker with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Headphone"))
		{
			try
			{
				//System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(headphonesXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("headphoneImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("headphoneName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("headphoneCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("headphoneCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("headphonePrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("headphoneColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(headphonesXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>headphone with id: " +id+" Updated Successfully<p>");
		}
		if(productType.equals("External Storage"))
		{
			try
			{
				//System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(externalstorageXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("externalstorageImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("externalstorageName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("externalstorageCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("externalstorageCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("externalstoragePrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("externalstorageColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(externalstorageXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>externalstorage with id: " +id+" Updated Successfully<p>");
		}
		if(productType.equals("Accessory"))
		{
			try
			{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(accessoriesXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								   Node node = list.item(j);

								   if ("accessoryImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("accessoryName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("accessoryCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("accessoryCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("accessoryPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("accessoryColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(accessoriesXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Accessory with id: " +id+" Updated Successfully<p>");
	  
		}*/
		
		
		
	}
			
}


