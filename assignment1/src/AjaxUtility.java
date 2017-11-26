import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class AjaxUtility {
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase", "root", "admin");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public static ArrayList<Object> getProductsFromMySQL()
	{
		ArrayList<Object> products = new ArrayList<Object>();
		
		HashMap<String, Phone> phones= new HashMap<String, Phone>();
		HashMap<String, Laptop> laptops= new HashMap<String, Laptop>();
		HashMap<String, SmartWatch> smartwatches= new HashMap<String, SmartWatch>();
		HashMap<String, Speaker> speakers= new HashMap<String, Speaker>();
		HashMap<String, Headphone> headphones = new HashMap<String, Headphone>();
		HashMap<String, ExternalStorage> externalstorages = new HashMap<String, ExternalStorage>();
		HashMap<String, Accessory> accessories= new HashMap<String, Accessory>();
		
		HashMap<String, Product> productsMap= new HashMap<String, Product>();
		
		Phone phone;
		Laptop laptop;
		SmartWatch smartwatch;
		Speaker speaker;
		Headphone headphone;
		ExternalStorage externalstorage;
		Accessory accessory; 
		Product product;
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM products");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				Integer id1 = rs.getInt("id");
				String id = id1.toString();
				String type = rs.getString("type");
				String retailer = rs.getString ("retailer");
				String image = rs.getString ("image");
				String name = rs.getString ("name");
				String company = rs.getString ("company");
				Float price = rs.getFloat("price");
				String color = rs.getString ("color");
				
				product = new Product();
					
				product.setType(type);
				product.setId(id);
				product.setRetailer(retailer);
				product.setImage(image);
				product.setName(name);
				product.setCompany(company);
				product.setPrice(price);
				product.setColor(color);
					
				productsMap.put(name, product);
				
				if(type.equals("Phone"))
				{
					phone = new Phone();
					
					phone.setId(id);
					phone.setRetailer(retailer);
					phone.setImage(image);
					phone.setName(name);
					phone.setCompany(company);
					phone.setPrice(price);
					phone.setColor(color);
					
					phones.put(name, phone);
				}

				if(type.equals("Smart Watch"))
				{
					smartwatch = new SmartWatch();
					
					smartwatch.setId(id);
					smartwatch.setRetailer(retailer);
					smartwatch.setImage(image);
					smartwatch.setName(name);
					smartwatch.setCompany(company);
					smartwatch.setPrice(price);
					smartwatch.setColor(color);
					
					smartwatches.put(name, smartwatch);
				}	   
				
				if(type.equals("Laptop"))
				{
					laptop = new Laptop();
					
					laptop.setId(id);
					laptop.setRetailer(retailer);
					laptop.setImage(image);
					laptop.setName(name);
					laptop.setCompany(company);
					laptop.setPrice(price);
					laptop.setColor(color);
					
					laptops.put(name, laptop);
				}
				
				if(type.equals("Speaker"))
				{
					speaker = new Speaker();
					
					speaker.setId(id);
					speaker.setRetailer(retailer);
					speaker.setImage(image);
					speaker.setName(name);
					speaker.setCompany(company);
					speaker.setPrice(price);
					speaker.setColor(color);
					
					speakers.put(name, speaker);
				}
				
				if(type.equals("Headphone"))
				{
					headphone = new Headphone();
					
					headphone.setId(id);
					headphone.setRetailer(retailer);
					headphone.setImage(image);
					headphone.setName(name);
					headphone.setCompany(company);
					headphone.setPrice(price);
					headphone.setColor(color);
					
					headphones.put(name, headphone);
				}
				
				if(type.equals("External Storage"))
				{
					externalstorage = new ExternalStorage();
					
					externalstorage.setId(id);
					externalstorage.setRetailer(retailer);
					externalstorage.setImage(image);
					externalstorage.setName(name);
					externalstorage.setCompany(company);
					externalstorage.setPrice(price);
					externalstorage.setColor(color);
					
					externalstorages.put(name, externalstorage);
				}
				
				if(type.equals("Accessory"))
				{
					accessory = new Accessory();
					
					accessory.setId(id);
					accessory.setRetailer(retailer);
					accessory.setImage(image);
					accessory.setName(name);
					accessory.setCompany(company);
					accessory.setPrice(price);
					accessory.setColor(color);
					
					accessories.put(name, accessory);
				}
				
			}
			
			products.add(phones);
			products.add(laptops);
			products.add(smartwatches);
			products.add(speakers);
			products.add(headphones);
			products.add(externalstorages);
			products.add(accessories);
			products.add(productsMap);
			
			//System.out.println("ProductsMap: "+productsMap);
			
			/*
			for(Map.Entry<String,Product> m :productsMap.entrySet())
			{
				Product p = m.getValue();
				
				System.out.println("Product: " + p.getName());
			}
			*/
			
			rs.close ();
			s.close ();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	public static void main(String args[]){
		
		//AjaxUtility.getProductsFromMySQL();
		
	}
	
}


