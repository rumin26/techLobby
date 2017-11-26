import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class AddProductToXML extends HttpServlet {
	
	ArrayList<Object> products;
	HashMap<String,Phone> phones;
 HashMap<String,Laptop> laptops;
 HashMap<String,SmartWatch> smartwatches;
 HashMap<String,Speaker> speakers;
 HashMap<String,Headphone> headphones;
 HashMap<String,ExternalStorage> externalstorages;
	HashMap<String, Accessory> accessories;
	
	SAXParserForProducts sp = new SAXParserForProducts();
	
	String phonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/PhoneCatalog.xml";
 String smartwatchesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/SmartWatchCatalog.xml";
 String laptopsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/LaptopCatalog.xml";
 String speakerXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/SpeakerCatalog.xml";
 String headphonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/HeadphoneCatalog.xml";
 String externalstorageXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/ExternalStorageCatalog.xml";
	String accessoriesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/AccessoryCatalog.xml";
	
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
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		
		Random r = new Random();
		int Low = 1;
		int High = 50000;
		int R = r.nextInt(High-Low);
		String id = ""+R;
		
		String productType = request.getParameter("productType");
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
		
		
		
		if(retailer!=null && !retailer.equals("") && imagePath!=null && !imagePath.equals("") && productName!=null && !productName.equals("")
			&& company!=null && !company.equals("") && condition!=null && !condition.equals("") && price!=null && !price.equals("")
			&& color!=null && !color.equals(""))
		{
			
			MySqlDataStoreUtilities.insertProductInMySQL(id, productType, retailer, imagePath, productName, company, condition, pricee, color, quant, sale, rebate, original);
			
			if(productType.equals("Phone"))
			{	
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(phonesXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("phone");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("phoneImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("phoneName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element phoneCompany = document.createElement("phoneCompany");
						phoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(phoneCompany);

						Element phoneCondition = document.createElement("phoneCondition");
						phoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(phoneCondition);
						
						Element phonePrice = document.createElement("phonePrice");
						phonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(phonePrice);
						
						Element phoneColor = document.createElement("phoneColor");
						phoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(phoneColor);

						Element phoneQuant = document.createElement("phoneQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("phoneRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("phoneSale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("phoneOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);
						
						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(phonesXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
			
				out.println("<p>phone with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Laptop"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(laptopsXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("laptop");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("laptopImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("laptopName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("laptopCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("laptopCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("laptopPrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("laptopColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);
						
						Element phoneQuant = document.createElement("laptopQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("laptopRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("laptopSale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("laptopOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(laptopsXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>laptop with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Smart Watch"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(smartwatchesXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("smartwatch");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("watchImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("watchName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("watchCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("watchCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("watchPrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("watchColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);
						
						Element phoneQuant = document.createElement("watchQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("watchRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("watchSale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("watchOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);
						

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(smartwatchesXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>watch with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Speaker"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(speakerXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("speaker");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("speakerImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("speakerName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("speakerCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("speakerCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("speakerPrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("speakerColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);
						
						Element phoneQuant = document.createElement("speakerQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("speakerRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("speakerSale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("speakerOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(speakerXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>Speaker with id: " +id+" Added Successfully<p>");
			}
			if(productType.equals("Headphone"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(headphonesXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("headphone");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("headphoneImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("headphoneName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("headphoneCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("headphoneCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("headphonePrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("headphoneColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);
						
						Element phoneQuant = document.createElement("headphoneQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("headphoneRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("headphoneSale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("headphoneOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(headphonesXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>headphone with id: " +id+" Added Successfully<p>");
			}
			if(productType.equals("External Storage"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(externalstorageXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("externalstorage");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("externalstorageImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("externalstorageName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("externalstorageCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("externalstorageCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("externalstoragePrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("externalstorageColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);
						
						Element phoneQuant = document.createElement("externalstorageQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("externalstorageRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("externalstorageSale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("externalstorageOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(externalstorageXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>externalstorage with id: " +id+" Added Successfully<p>");
			}
			if(productType.equals("Accessory"))
			{	
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(accessoriesXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("accessory");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("accessoryImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("accessoryName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element accessoryCompany = document.createElement("accessoryCompany");
						accessoryCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(accessoryCompany);

						Element accessoryCondition = document.createElement("accessoryCondition");
						accessoryCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(accessoryCondition);
						
						Element accessoryPrice = document.createElement("accessoryPrice");
						accessoryPrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(accessoryPrice);
						
						Element accessoryColor = document.createElement("accessoryColor");
						accessoryColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(accessoryColor);
						
						Element phoneQuant = document.createElement("accessoryQuantity");
						phoneQuant.appendChild(document.createTextNode(quantity));
						newProduct.appendChild(phoneQuant);
						
						Element phoneRebate = document.createElement("accessoryRebate");
						phoneRebate.appendChild(document.createTextNode(rebate));
						newProduct.appendChild(phoneRebate);
						
						Element phoneSale = document.createElement("accessorySale");
						phoneSale.appendChild(document.createTextNode(sale));
						newProduct.appendChild(phoneSale);
						
						Element phoneOriginal = document.createElement("accessoryOriginal");
						phoneOriginal.appendChild(document.createTextNode(originalPrice));
						newProduct.appendChild(phoneOriginal);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(accessoriesXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
			
				out.println("<p>Accessory with id: " +id+" Added Successfully<p>");
			}
			
			
		}
		
		else
		{
			out.println("<p>Please go back and fill all details properly");
		}
		
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


