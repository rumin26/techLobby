import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class UpdateSoldServlet extends HttpServlet {
	
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
		out.println("<header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2><h1>Thank You For Shopping With Us!!</h1></header>");
		//out.println("<nav><ul>");
		//out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		//out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		//out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		out.println("<table>");
		out.println("<tr><td><form method = 'post' action =\"LoggedInHomeServlet\">");
				out.println("<input class = 'formbutton' type = 'submit' name = '' value = 'Contine Shopping >>'>");
				//out.println("<input type='hidden' name='productType' value='"+itemType+"'>");
				//out.println("<input type='hidden' name='productName' value='"+itemName+"'>");
				//out.println("<input type='hidden' name='availqty' value='"+availqty+"'>");
				out.println("</form></td></tr>");
		out.println("</table>");
		
		//----------------------------------------------------------
	  
		String productType = request.getParameter("productType");
		String productName = request.getParameter("productName");
		
		
		
		//System.out.println(totalQty);
		//System.out.println(productName);
		//System.out.println(productType);
		
		
		
		
		if(productType.equals("Phones"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								   Node node = list.item(j);
									
									
								   if ("phoneImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("phoneName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("phoneCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("phoneCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("phonePrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("phoneColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								   if ("phoneQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									  // System.out.println(availQty);
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>phone with id: " +id+" Updated Successfully<p>");
	  
		}
		
		if(productType.equals("SmartWatches"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("watchImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("watchName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("watchCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("watchCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("watchPrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("watchColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								   if ("watchQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									  // System.out.println(availQty);
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>Watch with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Laptops"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("laptopImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("laptopName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("laptopCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("laptopCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("laptopPrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("laptopColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								   if ("laptopQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									  // System.out.println(availQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>Laptop with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Speakers"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("speakerImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("speakerName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("speakerCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("speakerCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("speakerPrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("speakerColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								    if ("speakerQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									  // System.out.println(availQty);
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>speaker with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Headphones"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("headphoneImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("headphoneName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("headphoneCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("headphoneCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("headphonePrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("headphoneColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								   if ("headphoneQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									  // System.out.println(availQty);
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>headphone with id: " +id+" Updated Successfully<p>");
		}
		if(productType.equals("ExternalStorages"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("externalstorageImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("externalstorageName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("externalstorageCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("externalstorageCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("externalstoragePrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("externalstorageColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								   if ("externalstorageQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									  // System.out.println(availQty);
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>externalstorage with id: " +id+" Updated Successfully<p>");
		}
		if(productType.equals("Accessories"))
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
							if(productName.equals(el.getAttributes().getNamedItem("name").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								   Node node = list.item(j);

								   if ("accessoryImage".equals(node.getNodeName())) {
									//node.setTextContent(imagePath);
								   }
								   if ("accessoryName".equals(node.getNodeName())) {
									//node.setTextContent(productName);
								   }
								   if ("accessoryCompany".equals(node.getNodeName())) {
									//node.setTextContent(company);
								   }
								   if ("accessoryCondition".equals(node.getNodeName())) {
									//node.setTextContent(condition);
								   }
								   if ("accessoryPrice".equals(node.getNodeName())) {
									//node.setTextContent(price);
								   }
								   if ("accessoryColor".equals(node.getNodeName())) {
									//node.setTextContent(color);
								   }
								   if ("accessoryQuantity".equals(node.getNodeName())) {
									   //int availQty = Integer.parseInt(node.getNodeValue());
									  // System.out.println(availQty);
									String availQty = request.getParameter("availqty");
									int totalQty = Integer.parseInt(availQty)-1;
									//MySqlDataStoreUtilities.updateSoldItem(productType, productName, totalQty);
									node.setTextContent(Integer.toString(totalQty));
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
			
			//out.println("<p>Accessory with id: " +id+" Updated Successfully<p>");
	  
		}
		
		
		
	}
			
}


