
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.io.*;
/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SAXParserForProducts extends DefaultHandler {

 Phone phone;
 Laptop laptop;
 SmartWatch watch;
 Speaker speaker;
 Headphone headphone;
 ExternalStorage externalstorage;
 Accessory accessory; 
 
 HashMap<String,Phone> phones;
 HashMap<String,Laptop> laptops;
 HashMap<String,SmartWatch> smartwatches;
 HashMap<String,Speaker> speakers;
 HashMap<String,Headphone> headphones;
 HashMap<String,ExternalStorage> externalstorages;
 HashMap<String,Accessory> accessories;
 
 ArrayList<Object> products;

 String phonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/PhoneCatalog.xml";
 String smartwatchesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/SmartWatchCatalog.xml";
 String laptopsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/LaptopCatalog.xml";
 String speakerXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/SpeakerCatalog.xml";
 String headphonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/HeadphoneCatalog.xml";
 String externalstorageXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/ExternalStorageCatalog.xml";
 String accessoriesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment1/src/AccessoryCatalog.xml";



    String elementValueRead;

 int x=1;
 int y=1;
 int z=1;
 int s=1;
 int h=1;
 int e=1;
 int a=1;

    public ArrayList<Object> loadDataStore() {
        
 phones = new HashMap<String,Phone>();
 laptops = new HashMap<String,Laptop>();
 smartwatches = new HashMap<String,SmartWatch>();
 speakers = new HashMap<String,Speaker>();
 headphones = new HashMap<String,Headphone>();
 externalstorages = new HashMap<String, ExternalStorage>();
 accessories = new HashMap<String,Accessory>();

 products = new ArrayList<Object>();

 parseDocument(phonesXmlFileName);
 parseDocument(smartwatchesXmlFileName);
 parseDocument(laptopsXmlFileName);
 parseDocument(speakerXmlFileName);
 parseDocument(headphonesXmlFileName);
 parseDocument(externalstorageXmlFileName);
 parseDocument(accessoriesXmlFileName);
 //printphones();
 //printTablets();
 //printLaptops();

 products.add(phones);
 products.add(laptops);
 products.add(smartwatches);
 products.add(speakers);
 products.add(headphones);
 products.add(externalstorages);
 products.add(accessories);
 
 return products;
    }

    private void parseDocument(String xmlFileName) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
 File f = new File(xmlFileName);
 parser.parse(f, this);
            //parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


 public void printPhones()
{
 for(Map.Entry<String,Phone> m :phones.entrySet())
{
 System.out.println(m.getKey());
 Phone s = m.getValue();
 System.out.println("\t Id : "+s.getId());
 System.out.println("\t Company : "+s.getCompany());
 System.out.println("\t Price : "+s.getPrice());
}
}
 public void printSmartWatches()
{
 for(Map.Entry<String,SmartWatch> m :smartwatches.entrySet())
{
 System.out.println(m.getKey());
 SmartWatch t = m.getValue();
 System.out.println("\t Id : "+t.getId());
 System.out.println("\t Company : "+t.getCompany());
 System.out.println("\t Price : "+t.getPrice());
}
}
 public void printLaptops()
{
 for(Map.Entry<String,Laptop> m :laptops.entrySet())
{
 System.out.println(m.getKey());
 Laptop s = m.getValue();
 System.out.println("\t Id : "+s.getId());
 System.out.println("\t Company : "+s.getCompany());
 System.out.println("\t Price : "+s.getPrice());
}
}

 public void printSpeakers()
{
 for(Map.Entry<String,Speaker> m :speakers.entrySet())
{
 System.out.println(m.getKey());
 Speaker s = m.getValue();
 System.out.println("\t Id : "+s.getId());
 System.out.println("\t Company : "+s.getCompany());
 System.out.println("\t Price : "+s.getPrice());
}
}

 public void printHeadphones()
{
 for(Map.Entry<String,Headphone> m :headphones.entrySet())
{
 System.out.println(m.getKey());
 Headphone s = m.getValue();
 System.out.println("\t Id : "+s.getId());
 System.out.println("\t Company : "+s.getCompany());
 System.out.println("\t Price : "+s.getPrice());
}
}

 public void printExternalStorages()
{
 for(Map.Entry<String,ExternalStorage> m :externalstorages.entrySet())
{
 System.out.println(m.getKey());
 ExternalStorage s = m.getValue();
 System.out.println("\t Id : "+s.getId());
 System.out.println("\t Company : "+s.getCompany());
 System.out.println("\t Price : "+s.getPrice());
}
}
public void printAccessories()
	{
		System.out.println("Inside printAccessories");
		for(Map.Entry<String,Accessory> m :accessories.entrySet())
		{
			System.out.println(m.getKey());
			Accessory s = m.getValue();
			System.out.println("\t Id : "+s.getId());
			System.out.println("\t Company : "+s.getCompany());
			System.out.println("\t Price : "+s.getPrice());
		}	
	}

////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////



    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("phone")) {
            phone = new Phone();
            phone.setId(attributes.getValue("id"));
            phone.setRetailer(attributes.getValue("retailer"));
 //System.out.println("phone element start");
        }


 if (elementName.equalsIgnoreCase("smartwatch")) {
            watch = new SmartWatch();
            watch.setId(attributes.getValue("id"));
            watch.setRetailer(attributes.getValue("retailer"));
        }

 if (elementName.equalsIgnoreCase("laptop")) {
            laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
            laptop.setRetailer(attributes.getValue("retailer"));
        }

 if (elementName.equalsIgnoreCase("speaker")) {
            speaker = new Speaker();
            speaker.setId(attributes.getValue("id"));
            speaker.setRetailer(attributes.getValue("retailer"));
        }
 if (elementName.equalsIgnoreCase("headphone")) {
            headphone = new Headphone();
            headphone.setId(attributes.getValue("id"));
            headphone.setRetailer(attributes.getValue("retailer"));
        }
 if (elementName.equalsIgnoreCase("externalstorage")) {
            externalstorage = new ExternalStorage();
            externalstorage.setId(attributes.getValue("id"));
            externalstorage.setRetailer(attributes.getValue("retailer"));
        }
if (elementName.equalsIgnoreCase("accessory")) {
            accessory = new Accessory();
            accessory.setId(attributes.getValue("id"));
            accessory.setRetailer(attributes.getValue("retailer"));
        }


    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
//Fetching values of smartphones
  
        if (element.equals("phone")) {
 phones.put("S"+x, phone);
 x++;

     return;
        }
        if (element.equalsIgnoreCase("phoneImage")) {
            phone.setImage(elementValueRead);

     return;
        }
        if (element.equalsIgnoreCase("phoneName")) {
            phone.setName(elementValueRead);

     return;
        }
 if (element.equalsIgnoreCase("phoneCompany")) {
            phone.setCompany(elementValueRead);

     return;
        }
 if (element.equalsIgnoreCase("phoneCondition")) {
            phone.setCondition(elementValueRead);

     return;
        }
 if(element.equalsIgnoreCase("phonePrice")){
            phone.setPrice(Float.parseFloat(elementValueRead));

     return;
        }
        if(element.equalsIgnoreCase("phoneColor")){
           phone.setColor(elementValueRead);
   
     return;
        }
 if(element.equalsIgnoreCase("phoneDescription")){
            phone.setDescription(elementValueRead);
     return;
        }
		
if(element.equalsIgnoreCase("phoneQuantity")){
            phone.setQty(Integer.parseInt(elementValueRead));
     return;
        }
 
  if(element.equalsIgnoreCase("phoneRebate")){
            phone.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("phoneRebateName")){
            phone.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("phoneOriginal")){
            phone.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("phoneSale")){
            phone.setSale(elementValueRead);
     return;
        }
//Fetching values of Smart Watches

 if (element.equals("smartwatch")) {
 smartwatches.put("T"+y, watch);
 y++;
     return;
        }
        if (element.equalsIgnoreCase("watchImage")) {
            watch.setImage(elementValueRead);
     return;
        }
        if (element.equalsIgnoreCase("watchName")) {
            watch.setName(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("watchCompany")) {
            watch.setCompany(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("watchCondition")) {
            watch.setCondition(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("watchPrice")){
            watch.setPrice(Float.parseFloat(elementValueRead));
     return;
        }
        if(element.equalsIgnoreCase("watchColor")){
           watch.setColor(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("watchDescription")){
            watch.setDescription(elementValueRead);
 //watches.put("T", watch);
     return;
        }
		if(element.equalsIgnoreCase("watchQuantity")){
            watch.setQty(Integer.parseInt(elementValueRead));
     return;
        }
 if(element.equalsIgnoreCase("watchRebate")){
            watch.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("watchRebateName")){
            watch.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("watchOriginal")){
            watch.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("watchSale")){
            watch.setSale(elementValueRead);
     return;
        }
  
// Fetching values of Laptops

 if (element.equals("laptop")) {
 laptops.put("L"+z, laptop);
 z++;
     return;
        }
        if (element.equalsIgnoreCase("laptopImage")) {
            laptop.setImage(elementValueRead);
     return;
        }
        if (element.equalsIgnoreCase("laptopName")) {
            laptop.setName(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("laptopCompany")) {
            laptop.setCompany(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("laptopCondition")) {
            laptop.setCondition(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("laptopPrice")){
            laptop.setPrice(Float.parseFloat(elementValueRead));
     return;
        }
        if(element.equalsIgnoreCase("laptopColor")){
           laptop.setColor(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("laptopDescription")){
            laptop.setDescription(elementValueRead);
 //laptops.put("L", laptop);
     return;
        }
		if(element.equalsIgnoreCase("laptopQuantity")){
            laptop.setQty(Integer.parseInt(elementValueRead));
     return;
        }
 if(element.equalsIgnoreCase("laptopRebate")){
            laptop.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("laptopRebateName")){
            laptop.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("laptopOriginal")){
            laptop.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("laptopSale")){
            laptop.setSale(elementValueRead);
     return;
        }


// Fetching values of Speakers



 if (element.equals("speaker")) {
 speakers.put("S"+s, speaker);
 s++;
     return;
        }
        if (element.equalsIgnoreCase("speakerImage")) {
            speaker.setImage(elementValueRead);
     return;
        }
        if (element.equalsIgnoreCase("speakerName")) {
            speaker.setName(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("speakerCompany")) {
            speaker.setCompany(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("speakerCondition")) {
            speaker.setCondition(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("speakerPrice")){
            speaker.setPrice(Float.parseFloat(elementValueRead));
     return;
        }
        if(element.equalsIgnoreCase("speakerColor")){
           speaker.setColor(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("speakerDescription")){
            speaker.setDescription(elementValueRead);
 //speakers.put("Tv", speaker);
     return;
        }
 if(element.equalsIgnoreCase("speakerQuantity")){
            speaker.setQty(Integer.parseInt(elementValueRead));
     return;
        }
if(element.equalsIgnoreCase("speakerRebate")){
            speaker.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("speakerRebateName")){
            speaker.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("speakerOriginal")){
            speaker.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("speakerSale")){
            speaker.setSale(elementValueRead);
     return;
        }


if (element.equals("headphone")) {
 headphones.put("H"+h, headphone);
 h++;
     return;
        }
        if (element.equalsIgnoreCase("headphoneImage")) {
            headphone.setImage(elementValueRead);
     return;
        }
        if (element.equalsIgnoreCase("headphoneName")) {
            headphone.setName(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("headphoneCompany")) {
            headphone.setCompany(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("headphoneCondition")) {
            headphone.setCondition(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("headphonePrice")){
            headphone.setPrice(Float.parseFloat(elementValueRead));
     return;
        }
        if(element.equalsIgnoreCase("headphoneColor")){
           headphone.setColor(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("headphoneDescription")){
            headphone.setDescription(elementValueRead);
 //speakers.put("Tv", speaker);
     return;
        }
		if(element.equalsIgnoreCase("headphoneQuantity")){
            headphone.setQty(Integer.parseInt(elementValueRead));
     return;
        }
 if(element.equalsIgnoreCase("headphoneRebate")){
            headphone.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("headphoneRebateName")){
            headphone.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("headphoneOriginal")){
            headphone.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("headphoneSale")){
            headphone.setSale(elementValueRead);
     return;
        }
		
		
if (element.equals("externalstorage")) {
 externalstorages.put("E"+e, externalstorage);
 e++;
     return;
        }
        if (element.equalsIgnoreCase("externalstorageImage")) {
            externalstorage.setImage(elementValueRead);
     return;
        }
        if (element.equalsIgnoreCase("externalstorageName")) {
            externalstorage.setName(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("externalstorageCompany")) {
            externalstorage.setCompany(elementValueRead);
     return;
        }
 if (element.equalsIgnoreCase("externalstorageCondition")) {
            externalstorage.setCondition(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("externalstoragePrice")){
            externalstorage.setPrice(Float.parseFloat(elementValueRead));
     return;
        }
        if(element.equalsIgnoreCase("externalstorageColor")){
           externalstorage.setColor(elementValueRead);
     return;
        }
 if(element.equalsIgnoreCase("externalstorageDescription")){
            externalstorage.setDescription(elementValueRead);
 //speakers.put("Tv", speaker);
     return;
        }
		if(element.equalsIgnoreCase("externalstorageQuantity")){
            externalstorage.setQty(Integer.parseInt(elementValueRead));
     return;
        }
		if(element.equalsIgnoreCase("externalstorageRebate")){
            externalstorage.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("externalstorageRebateName")){
            externalstorage.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("externalstorageOriginal")){
            externalstorage.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("externalstorageSale")){
            externalstorage.setSale(elementValueRead);
     return;
        }
		
		
		
 if (element.equals("accessory")) {
			accessories.put("A"+a, accessory);
			a++;
			
	    return;
        }
        if (element.equalsIgnoreCase("accessoryImage")) {
            accessory.setImage(elementValueRead);
		
	    return;
        }
        if (element.equalsIgnoreCase("accessoryName")) {
            accessory.setName(elementValueRead);
			
	    return;
        }
		if (element.equalsIgnoreCase("accessoryCompany")) {
            accessory.setCompany(elementValueRead);
			
	    return;
        }
		if (element.equalsIgnoreCase("accessoryCondition")) {
            accessory.setCondition(elementValueRead);
			
	    return;
        }
		if(element.equalsIgnoreCase("accessoryPrice")){
            accessory.setPrice(Float.parseFloat(elementValueRead));
			
	    return;
        }
        if(element.equalsIgnoreCase("accessoryColor")){
           accessory.setColor(elementValueRead);
		  
	    return;
        }
		if(element.equalsIgnoreCase("accessoryDescription")){
            accessory.setDescription(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("accessoryQuantity")){
            accessory.setQty(Integer.parseInt(elementValueRead));
     return;
        }
		if(element.equalsIgnoreCase("accessoryRebate")){
            accessory.setRebate(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("accessoryRebateName")){
            accessory.setRebateName(elementValueRead);
     return;
        }
		if(element.equalsIgnoreCase("accessoryOriginal")){
            accessory.setOriginal(Float.parseFloat(elementValueRead));
			
	    return;
        }
		if(element.equalsIgnoreCase("accessorySale")){
            accessory.setSale(elementValueRead);
     return;
        }
		
		
		
	}

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
 //System.out.println(elementValueRead);
    }


 public static void main(String[] args) {
  SAXParserForProducts sp = new SAXParserForProducts();
         sp.loadDataStore();
  //sp.printphones;
    }


}
