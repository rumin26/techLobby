import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class DeleteProduct extends HttpServlet {
	
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
	String accessoriesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/WEB-INF/classes/AccessoryCatalog.xml";
	
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
		
		out.println("<fieldset><div style=\"width:800px; margin-right:auto; margin-left:auto;\">");
		
		
		String productName = request.getParameter("productName");
		String type = request.getParameter("type");
		
		MySqlDataStoreUtilities.deleteProductFromMySQL(productName, type);
		
		if(type.equals("Phone"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(phonesXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("phone");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element phone = (Element)nodes.item(i);
					  // <phoneName>
					  Element phoneName = (Element)phone.getElementsByTagName("phoneName").item(0);
					  String sName = phoneName.getTextContent();
					  if (sName.equals(productName))
					  {
						 phone.getParentNode().removeChild(phone);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(phonesXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		
		if(type.equals("Smart Watch"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(smartwatchesXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("smartwatch");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element smartwatch = (Element)nodes.item(i);
					  // <watchName>
					  Element watchName = (Element)smartwatch.getElementsByTagName("watchName").item(0);
					  String sName = watchName.getTextContent();
					  if (sName.equals(productName))
					  {
						 smartwatch.getParentNode().removeChild(smartwatch);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(smartwatchesXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		if(type.equals("Laptop"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(laptopsXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("laptop");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element laptop = (Element)nodes.item(i);
					  // <laptopName>
					  Element laptopName = (Element)laptop.getElementsByTagName("laptopName").item(0);
					  String sName = laptopName.getTextContent();
					  if (sName.equals(productName))
					  {
						 laptop.getParentNode().removeChild(laptop);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(laptopsXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		
		if(type.equals("Speaker"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(speakerXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("speaker");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element speaker = (Element)nodes.item(i);
					  // <speakerName>
					  Element speakerName = (Element)speaker.getElementsByTagName("speakerName").item(0);
					  String sName = speakerName.getTextContent();
					  if (sName.equals(productName))
					  {
						 speaker.getParentNode().removeChild(speaker);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(speakerXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		if(type.equals("Headphone"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(headphonesXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("headphone");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element headphone = (Element)nodes.item(i);
					  // <headphoneName>
					  Element headphoneName = (Element)headphone.getElementsByTagName("headphoneName").item(0);
					  String sName = headphoneName.getTextContent();
					  if (sName.equals(productName))
					  {
						 headphone.getParentNode().removeChild(headphone);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(headphonesXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		if(type.equals("External Storage"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(externalstorageXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("externalstorage");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element externalstorage = (Element)nodes.item(i);
					  // <externalstorageName>
					  Element externalstorageName = (Element)externalstorage.getElementsByTagName("externalstorageName").item(0);
					  String sName = externalstorageName.getTextContent();
					  if (sName.equals(productName))
					  {
						 externalstorage.getParentNode().removeChild(externalstorage);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(externalstorageXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		if(type.equals("Accessory"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(accessoriesXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("accessory");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element accessory = (Element)nodes.item(i);
					  // <accessoryName>
					  Element accessoryName = (Element)accessory.getElementsByTagName("accessoryName").item(0);
					  String sName = accessoryName.getTextContent();
					  if (sName.equals(productName))
					  {
						 accessory.getParentNode().removeChild(accessory);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(accessoriesXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


