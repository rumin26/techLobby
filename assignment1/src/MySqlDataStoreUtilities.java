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

public class MySqlDataStoreUtilities {
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase", "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void insertCustomer(String firstName, String lastName, String emailId, String password, String phoneNumber)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO registration(firstName,lastName,emailId, password, phoneNumber) " + "VALUES (?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,emailId);
			pst.setString(4,password);
			pst.setString(5,phoneNumber);
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,Customer> getCustomers()
	{
		Customer customer = null;
		HashMap<String,Customer> customers = new HashMap<String, Customer>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT firstName, lastName, emailId, password, phoneNumber FROM registration");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String firstName = rs.getString("firstName");
				String lastName = rs.getString ("lastName");
				String emailId = rs.getString ("emailId");
				String password = rs.getString ("password");
				String phoneNumber = rs.getString ("phoneNumber");
			   
				customer = new Customer(firstName, lastName, emailId, password, phoneNumber);
				customers.put(emailId, customer);
			}
			//System.out.println("Customers: "+customers);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public static HashMap<String,String> getAdminCredentials()
	{
		HashMap<String,String> admins = new HashMap<String, String>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT emailAddress, password FROM admin_login_details");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String emailAddress = rs.getString("emailAddress");
				String password = rs.getString ("password");
			   
				admins.put(emailAddress, password);
			}
			//System.out.println("Customers: "+customers);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return admins;
	}
	
	private static void insertCustomersFromSerializedFile()
	{
		PopulateCustomersHashmap pch;
		Customer customer = null;
		HashMap<String,Customer> customers = new HashMap<String, Customer>();
		pch = new PopulateCustomersHashmap();
		
		customers = pch.getCustomerHashMap();
		//System.out.println("Customers: "+customers);
		for(Map.Entry<String,Customer> m :customers.entrySet())
		{
			Customer c = m.getValue();
			//System.out.println("Customer: "+c);
			insertCustomer(c.getfirstName(), c.getlastName(), c.getemailId(), c.getpassword(), c.getPhoneNumber());
			
		}
		
	}
	
	public static void insertOrderItem(String itemType, String itemName, String orderId, float itemPrice, int itemQty, String orderDate, String deliveryDate, String customerEmailId, String deliveryAddress, String date_field)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String getQty =  ("SELECT SUM(itemQty) as sumItemQty FROM order_item WHERE itemName=? GROUP BY itemName;");
			PreparedStatement s = conn.prepareStatement(getQty);
			s.setString(1,itemName);
			s.execute();
			
			ResultSet rs = s.getResultSet();
			Integer sumItemQty=0;
			
			while (rs.next ())
			{
				//String itemName = rs.getString("itemName");
				//Float itemPrice = rs.getFloat ("itemPrice");
				sumItemQty = rs.getInt ("sumItemQty");
				
			}
			rs.close ();
			s.close ();
			
			if(sumItemQty == 0)
			{
				sumItemQty = 99;
			}
			else
			{
				sumItemQty = 100 - sumItemQty-1;
			}
			
			String insertIntoOrderItemQuery = "INSERT INTO order_item(orderId,itemType,itemName,itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress, availQty, date_field) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
			pst.setString(1,orderId);
			pst.setString(2, itemType);
			pst.setString(3,itemName);
			pst.setFloat(4,itemPrice);
			pst.setInt(5,itemQty);
			pst.setString(6,orderDate);
			pst.setString(7,deliveryDate);
			pst.setString(8,customerEmailId);
			pst.setString(9,deliveryAddress);
			pst.setInt(10,sumItemQty);
			pst.setString(11,date_field);
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertOrderTotal(String orderId, String orderDate, String deliveryDate, float totalAmount, String customerEmailId, String deliveryAddress)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertIntoOrderTotalQuery = "INSERT INTO order_total(orderId, orderDate, deliveryDate, totalAmount, customerEmailId, deliveryAddress) " + "VALUES (?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,orderId);
			pst.setString(2,orderDate);
			pst.setString(3,deliveryDate);
			pst.setFloat(4,totalAmount);
			pst.setString(5,customerEmailId);
			pst.setString(6,deliveryAddress);
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<String,OrderItem> getOrderItems()
	{
		OrderItem orderItem = null;
		HashMap<String,OrderItem> orderItems = new HashMap<String, OrderItem>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT orderId, itemType, itemName, itemId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress FROM order_item");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String orderId = rs.getString("orderId");
				String itemType = rs.getString ("itemType");
				Integer itemId = rs.getInt ("itemId");
				String itemName = rs.getString ("itemName");
				Float itemPrice = rs.getFloat ("itemPrice");
				Integer itemQty = rs.getInt ("itemQty");
				String orderDate = rs.getString("orderDate");
				String deliveryDate = rs.getString ("deliveryDate");
				String customerEmailId = rs.getString ("customerEmailId");
				String deliveryAddress = rs.getString ("deliveryAddress");
			   
				orderItem = new OrderItem(orderId, itemType, itemName, itemId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress);
				orderItems.put(customerEmailId+orderId+itemName, orderItem);
			}
			//System.out.println("OrderItems: "+orderItems);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return orderItems;
	}
	
	public static void deleteOrderItem(String orderId, String itemName, String customerEmailId)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertIntoOrderTotalQuery = "DELETE FROM order_item where orderId=? and itemName=?";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,orderId);
			pst.setString(2,itemName);
			//pst.setString(3,deliveryDate);
		
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static LinkedHashMap<String, ArrayList<Object>> getTop5SoldProducts()
	{
		LinkedHashMap<String, ArrayList<Object>> top5SoldProducts = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT itemName, itemPrice, SUM(itemQty) as sumItemQty FROM order_item GROUP BY itemName order by sumItemQty desc limit 5;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String itemName = rs.getString("itemName");
				Float itemPrice = rs.getFloat ("itemPrice");
				Integer sumItemQty = rs.getInt ("sumItemQty");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(itemName);
				itemArray.add(itemPrice);
				itemArray.add(sumItemQty);
				
				top5SoldProducts.put(itemName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return top5SoldProducts;
		
	}
	
	public static LinkedHashMap<String, ArrayList<Object>> getSoldProductsSales()
	{
		LinkedHashMap<String, ArrayList<Object>> soldProductsSales = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT itemName, itemPrice, SUM(itemQty) as sumItemQty, round(SUM(itemPrice),2) as sumItemPrice FROM order_item GROUP BY itemName order by sumItemQty desc;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String itemName = rs.getString("itemName");
				Float itemPrice = rs.getFloat ("itemPrice");
				Integer sumItemQty = rs.getInt ("sumItemQty");
				Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(itemName);
				itemArray.add(itemPrice);
				itemArray.add(sumItemQty);
				itemArray.add(totalSales);
				
				
				soldProductsSales.put(itemName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return soldProductsSales;
		
	}
	
	////Fetch Rebate Products
	public static LinkedHashMap<String, ArrayList<Object>> getRebatePhones()
	{
		LinkedHashMap<String, ArrayList<Object>> rebatePhones = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT phoneName, phonePrice, phoneCompany FROM phones WHERE phoneRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String phoneName = rs.getString("phoneName");
				Float phonePrice = rs.getFloat ("phonePrice");
				String phoneCompany = rs.getString ("phoneCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(phoneName);
				itemArray.add(phonePrice);
				itemArray.add(phoneCompany);
				//itemArray.add(totalSales);
				
				
				rebatePhones.put(phoneName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebatePhones;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getRebateLaptops()
	{
		LinkedHashMap<String, ArrayList<Object>> rebateLaptops = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT laptopName, laptopPrice, laptopCompany FROM laptops WHERE laptopRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String laptopName = rs.getString("laptopName");
				Float laptopPrice = rs.getFloat ("laptopPrice");
				String laptopCompany = rs.getString ("laptopCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(laptopName);
				itemArray.add(laptopPrice);
				itemArray.add(laptopCompany);
				//itemArray.add(totalSales);
				
				
				rebateLaptops.put(laptopName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebateLaptops;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getRebateWatches()
	{
		LinkedHashMap<String, ArrayList<Object>> rebateWatches = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT watchName, watchPrice, watchCompany FROM watches WHERE watchRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String watchName = rs.getString("watchName");
				Float watchPrice = rs.getFloat ("watchPrice");
				String watchCompany = rs.getString ("watchCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(watchName);
				itemArray.add(watchPrice);
				itemArray.add(watchCompany);
				//itemArray.add(totalSales);
				
				
				rebateWatches.put(watchName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebateWatches;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getRebateSpeakers()
	{
		LinkedHashMap<String, ArrayList<Object>> rebateSpeakers = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT speakerName, speakerPrice, speakerCompany FROM speakers WHERE speakerRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String speakerName = rs.getString("speakerName");
				Float speakerPrice = rs.getFloat ("speakerPrice");
				String speakerCompany = rs.getString ("speakerCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(speakerName);
				itemArray.add(speakerPrice);
				itemArray.add(speakerCompany);
				//itemArray.add(totalSales);
				
				
				rebateSpeakers.put(speakerName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebateSpeakers;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getRebateHeadphones()
	{
		LinkedHashMap<String, ArrayList<Object>> rebateHeadphones = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT headphoneName, headphonePrice, headphoneCompany FROM headphones WHERE headphoneRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String headphoneName = rs.getString("headphoneName");
				Float headphonePrice = rs.getFloat ("headphonePrice");
				String headphoneCompany = rs.getString ("headphoneCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(headphoneName);
				itemArray.add(headphonePrice);
				itemArray.add(headphoneCompany);
				//itemArray.add(totalSales);
				
				
				rebateHeadphones.put(headphoneName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebateHeadphones;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getRebateAccessories()
	{
		LinkedHashMap<String, ArrayList<Object>> rebateAccessories = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT accessoryName, accessoryPrice, accessoryCompany FROM accessory WHERE accessoryRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String accessoryName = rs.getString("accessoryName");
				Float accessoryPrice = rs.getFloat ("accessoryPrice");
				String accessoryCompany = rs.getString ("accessoryCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(accessoryName);
				itemArray.add(accessoryPrice);
				itemArray.add(accessoryCompany);
				//itemArray.add(totalSales);
				
				
				rebateAccessories.put(accessoryName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebateAccessories;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getRebateStorages()
	{
		LinkedHashMap<String, ArrayList<Object>> rebateStorages = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT externalstorageName, externalstoragePrice, externalstorageCompany FROM storages WHERE externalstorageRebate = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String externalstorageName = rs.getString("externalstorageName");
				Float externalstoragePrice = rs.getFloat ("externalstoragePrice");
				String externalstorageCompany = rs.getString ("externalstorageCompany");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(externalstorageName);
				itemArray.add(externalstoragePrice);
				itemArray.add(externalstorageCompany);
				//itemArray.add(totalSales);
				
				
				rebateStorages.put(externalstorageName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rebateStorages;
		
	}
	
	
	////Fetch Products on Sale
	public static LinkedHashMap<String, ArrayList<Object>> getSalePhones()
	{
		LinkedHashMap<String, ArrayList<Object>> salePhones = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT phoneName, phonePrice, phoneCompany,phoneOriginal FROM phones WHERE phoneSale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String phoneName = rs.getString("phoneName");
				Float phonePrice = rs.getFloat ("phonePrice");
				String phoneCompany = rs.getString ("phoneCompany");
				Float phoneOriginal = rs.getFloat("phoneOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(phoneName);
				itemArray.add(phonePrice);
				itemArray.add(phoneCompany);
				itemArray.add(phoneOriginal);
				//itemArray.add(totalSales);
				
				
				salePhones.put(phoneName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return salePhones;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSaleLaptops()
	{
		LinkedHashMap<String, ArrayList<Object>> saleLaptops = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT laptopName, laptopPrice, laptopCompany, laptopOriginal FROM laptops WHERE laptopSale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String laptopName = rs.getString("laptopName");
				Float laptopPrice = rs.getFloat ("laptopPrice");
				String laptopCompany = rs.getString ("laptopCompany");
				Float laptopOriginal = rs.getFloat("laptopOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(laptopName);
				itemArray.add(laptopPrice);
				itemArray.add(laptopCompany);
				itemArray.add(laptopOriginal);
				//itemArray.add(totalSales);
				
				
				saleLaptops.put(laptopName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return saleLaptops;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSaleWatches()
	{
		LinkedHashMap<String, ArrayList<Object>> saleWatches = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT watchName, watchPrice, watchCompany, watchOriginal FROM watches WHERE watchSale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String watchName = rs.getString("watchName");
				Float watchPrice = rs.getFloat ("watchPrice");
				String watchCompany = rs.getString ("watchCompany");
				Float watchOriginal = rs.getFloat("watchOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(watchName);
				itemArray.add(watchPrice);
				itemArray.add(watchCompany);
				itemArray.add(watchOriginal);
				//itemArray.add(totalSales);
				
				
				saleWatches.put(watchName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return saleWatches;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSaleSpeakers()
	{
		LinkedHashMap<String, ArrayList<Object>> saleSpeakers = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT speakerName, speakerPrice, speakerCompany, speakerOriginal FROM speakers WHERE speakerSale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String speakerName = rs.getString("speakerName");
				Float speakerPrice = rs.getFloat ("speakerPrice");
				String speakerCompany = rs.getString ("speakerCompany");
				Float speakerOriginal = rs.getFloat("speakerOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(speakerName);
				itemArray.add(speakerPrice);
				itemArray.add(speakerCompany);
				itemArray.add(speakerOriginal);
				//itemArray.add(totalSales);
				
				
				saleSpeakers.put(speakerName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return saleSpeakers;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSaleHeadphones()
	{
		LinkedHashMap<String, ArrayList<Object>> saleHeadphones = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT headphoneName, headphonePrice, headphoneCompany, headphoneOriginal FROM headphones WHERE headphoneSale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String headphoneName = rs.getString("headphoneName");
				Float headphonePrice = rs.getFloat ("headphonePrice");
				String headphoneCompany = rs.getString ("headphoneCompany");
				Float headphoneOriginal = rs.getFloat("headphoneOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(headphoneName);
				itemArray.add(headphonePrice);
				itemArray.add(headphoneCompany);
				itemArray.add(headphoneOriginal);
				//itemArray.add(totalSales);
				
				
				saleHeadphones.put(headphoneName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return saleHeadphones;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSaleAccessories()
	{
		LinkedHashMap<String, ArrayList<Object>> saleAccessories = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT accessoryName, accessoryPrice, accessoryCompany, accessoryOriginal FROM accessory WHERE accessorySale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String accessoryName = rs.getString("accessoryName");
				Float accessoryPrice = rs.getFloat ("accessoryPrice");
				String accessoryCompany = rs.getString ("accessoryCompany");
				Float accessoryOriginal = rs.getFloat("accessoryOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(accessoryName);
				itemArray.add(accessoryPrice);
				itemArray.add(accessoryCompany);
				itemArray.add(accessoryOriginal);
				//itemArray.add(totalSales);
				
				
				saleAccessories.put(accessoryName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return saleAccessories;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSaleStorages()
	{
		LinkedHashMap<String, ArrayList<Object>> saleStorages = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT externalstorageName, externalstoragePrice, externalstorageCompany, externalstorageOriginal FROM storages WHERE externalstorageSale = 'Yes';");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String externalstorageName = rs.getString("externalstorageName");
				Float externalstoragePrice = rs.getFloat ("externalstoragePrice");
				String externalstorageCompany = rs.getString ("externalstorageCompany");
				Float externalstorageOriginal = rs.getFloat("externalstorageOriginal");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(externalstorageName);
				itemArray.add(externalstoragePrice);
				itemArray.add(externalstorageCompany);
				itemArray.add(externalstorageOriginal);
				//itemArray.add(totalSales);
				
				
				saleStorages.put(externalstorageName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return saleStorages;
		
	}
	
	
	public static LinkedHashMap<String, ArrayList<Object>> getDailySales()
	{
		LinkedHashMap<String, ArrayList<Object>> dailySales = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT date_field, round(SUM(itemPrice),2) as sumPrice FROM order_item GROUP BY date_field;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String date_field = rs.getString("date_field");
				Float totalSales = rs.getFloat("sumPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(date_field);
				itemArray.add(totalSales);
				
				
				
				dailySales.put(date_field, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dailySales;
		
	}
	
	
	
	public static void updateOrderItem(String itemName, String orderId, float itemPrice, int itemQty, String orderDate, String deliveryDate, String customerEmailId, String deliveryAddress)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertIntoOrderItemQuery = "Update order_item set itemName=?, itemPrice=?, itemQty=?, deliveryDate=?, deliveryAddress=? where orderId=?";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
			pst.setString(1,itemName);
			pst.setFloat(2,itemPrice);
			pst.setInt(3,itemQty);
			pst.setString(4,deliveryDate);
			pst.setString(5,deliveryAddress);
			pst.setString(6,orderId);
			
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void updateSoldItem(String itemType, String itemName, int availQty)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			if(itemType.equals("Phones"))
			{
				String insertIntoOrderItemQuery = "Update phones set phoneQuantity=? where phoneName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			else if(itemType.equals("Laptops"))
			{
				String insertIntoOrderItemQuery = "Update laptops set laptopQuantity=? where laptopName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			else if(itemType.equals("SmartWatches"))
			{
				String insertIntoOrderItemQuery = "Update watches set watchQuantity=? where watchName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			else if(itemType.equals("Speakers"))
			{
				String insertIntoOrderItemQuery = "Update speakers set speakerQuantity=? where speakerName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			else if(itemType.equals("Headphones"))
			{
				String insertIntoOrderItemQuery = "Update headphones set headphoneQuantity=? where headphoneName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			else if(itemType.equals("ExternalStorages"))
			{
				String insertIntoOrderItemQuery = "Update storages set externalstorageQuantity=? where externalStorageName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			else if(itemType.equals("Accessories"))
			{
				String insertIntoOrderItemQuery = "Update accessory set accessoryQuantity=? where accessoryName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
				pst.setInt(1,availQty);
				pst.setString(2,itemName);
						
			
				pst.execute();
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		
		//HashMap<String,Customer> customers = new HashMap<String, Customer>();
		//customers = MySqlDataStoreUtilities.getCustomers();
		//System.out.println("Customers: "+customers);
		
		//MySqlDataStoreUtilities.getOrderItems();
		//MySqlDataStoreUtilities.deleteOrderItem("B#226329", "iPad Air 1", "adil@gmail.com");
		//MySqlDataStoreUtilities.updateOrderItem();
		
	}
	
	
	////////////////////Get all Products
	public static LinkedHashMap<String, ArrayList<Object>> getPhones()
	{
		LinkedHashMap<String, ArrayList<Object>> phones = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM phones;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String phoneImage = rs.getString("phoneImage");
				String phoneName = rs.getString("phoneName");
				String phoneCompany = rs.getString ("phoneCompany");
				String phoneCondition = rs.getString("phoneCondition");
				Float phonePrice = rs.getFloat ("phonePrice");
				String phoneColor = rs.getString("phoneColor");
				int phoneQuantity = rs.getInt("phoneQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("phoneSale");
				Float original = rs.getFloat("phoneOriginal");
				String rebate = rs.getString("phoneRebate");
				
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(phoneImage);
				itemArray.add(phoneName);
				itemArray.add(phoneCompany);
				itemArray.add(phoneCondition);
				itemArray.add(phonePrice);
				itemArray.add(phoneColor);
				itemArray.add(phoneQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				
				//itemArray.add(totalSales);
				
				
				phones.put(phoneName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return phones;
		
	}
	
	public static LinkedHashMap<String, ArrayList<Object>> getLaptops()
	{
		LinkedHashMap<String, ArrayList<Object>> laptops = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM laptops;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String laptopImage = rs.getString("laptopImage");
				String laptopName = rs.getString("laptopName");
				String laptopCompany = rs.getString ("laptopCompany");
				String laptopCondition = rs.getString("laptopCondition");
				Float laptopPrice = rs.getFloat ("laptopPrice");
				String laptopColor = rs.getString("laptopColor");
				int laptopQuantity = rs.getInt("laptopQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("laptopSale");
				Float original = rs.getFloat("laptopOriginal");
				String rebate = rs.getString("laptopRebate");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(laptopImage);
				itemArray.add(laptopName);
				itemArray.add(laptopCompany);
				itemArray.add(laptopCondition);
				itemArray.add(laptopPrice);
				itemArray.add(laptopColor);
				itemArray.add(laptopQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				
				
				laptops.put(laptopName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return laptops;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getWatches()
	{
		LinkedHashMap<String, ArrayList<Object>> watches = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * From watches;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String watchImage = rs.getString("watchImage");
				String watchName = rs.getString("watchName");
				String watchCompany = rs.getString ("watchCompany");
				String watchCondition = rs.getString("watchCondition");
				Float watchPrice = rs.getFloat ("watchPrice");
				String watchColor = rs.getString("watchColor");
				int watchQuantity = rs.getInt("watchQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("watchSale");
				Float original = rs.getFloat("watchOriginal");
				String rebate = rs.getString("watchRebate");
				
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(watchImage);
				itemArray.add(watchName);
				itemArray.add(watchCompany);
				itemArray.add(watchCondition);
				itemArray.add(watchPrice);
				itemArray.add(watchColor);
				itemArray.add(watchQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				
				//itemArray.add(totalSales);
				
				
				watches.put(watchName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return watches;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getSpeakers()
	{
		LinkedHashMap<String, ArrayList<Object>> speakers = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM speakers;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String speakerImage = rs.getString("speakerImage");
				String speakerName = rs.getString("speakerName");
				String speakerCompany = rs.getString ("speakerCompany");
				String speakerCondition = rs.getString("speakerCondition");
				Float speakerPrice = rs.getFloat ("speakerPrice");
				String speakerColor = rs.getString("speakerColor");
				int speakerQuantity = rs.getInt("speakerQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("speakerSale");
				Float original = rs.getFloat("speakerOriginal");
				String rebate = rs.getString("speakerRebate");
				
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(speakerImage);
				itemArray.add(speakerName);
				itemArray.add(speakerCompany);
				itemArray.add(speakerCondition);
				itemArray.add(speakerPrice);
				itemArray.add(speakerColor);
				itemArray.add(speakerQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				
				
				//itemArray.add(totalSales);
				
				
				speakers.put(speakerName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return speakers;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getHeadphones()
	{
		LinkedHashMap<String, ArrayList<Object>> headphones = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM headphones;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String headphoneImage = rs.getString("headphoneImage");
				String headphoneName = rs.getString("headphoneName");
				String headphoneCompany = rs.getString ("headphoneCompany");
				String headphoneCondition = rs.getString("headphoneCondition");
				Float headphonePrice = rs.getFloat ("headphonePrice");
				String headphoneColor = rs.getString("headphoneColor");
				int headphoneQuantity = rs.getInt("headphoneQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("headphoneSale");
				Float original = rs.getFloat("headphoneOriginal");
				String rebate = rs.getString("headphoneRebate");
				
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(headphoneImage);
				itemArray.add(headphoneName);
				itemArray.add(headphoneCompany);
				itemArray.add(headphoneCondition);
				itemArray.add(headphonePrice);
				itemArray.add(headphoneColor);
				itemArray.add(headphoneQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				
				//itemArray.add(totalSales);
				
				
				headphones.put(headphoneName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return headphones;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getAccessories()
	{
		LinkedHashMap<String, ArrayList<Object>> accessories = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM accessory;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String accessoryImage = rs.getString("accessoryImage");
				String accessoryName = rs.getString("accessoryName");
				String accessoryCompany = rs.getString ("accessoryCompany");
				String accessoryCondition = rs.getString("accessoryCondition");
				Float accessoryPrice = rs.getFloat ("accessoryPrice");
				String accessoryColor = rs.getString("accessoryColor");
				int accessoryQuantity = rs.getInt("accessoryQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("accessorySale");
				Float original = rs.getFloat("accessoryOriginal");
				String rebate = rs.getString("accessoryRebate");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(accessoryImage);
				itemArray.add(accessoryName);
				itemArray.add(accessoryCompany);
				itemArray.add(accessoryCondition);
				itemArray.add(accessoryPrice);
				itemArray.add(accessoryColor);
				itemArray.add(accessoryQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				
				//itemArray.add(totalSales);
				
				
				accessories.put(accessoryName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return accessories;
		
	}
	public static LinkedHashMap<String, ArrayList<Object>> getStorages()
	{
		LinkedHashMap<String, ArrayList<Object>> storages = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techLobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM storages;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String externalstorageImage = rs.getString("externalstorageImage");
				String externalstorageName = rs.getString("externalstorageName");
				String externalstorageCompany = rs.getString ("externalstorageCompany");
				String externalstorageCondition = rs.getString("externalstorageCondition");
				Float externalstoragePrice = rs.getFloat ("externalstoragePrice");
				String externalstorageColor = rs.getString("externalstorageColor");
				int externalstorageQuantity = rs.getInt("externalstorageQuantity");
				String retailer = rs.getString ("retailer");
				String sale = rs.getString("externalStorageSale");
				Float original = rs.getFloat("externalstorageOriginal");
				String rebate = rs.getString("externalstorageRebate");
				//Float totalSales = rs.getFloat("sumItemPrice");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(externalstorageImage);
				itemArray.add(externalstorageName);
				itemArray.add(externalstorageCompany);
				itemArray.add(externalstorageCondition);
				itemArray.add(externalstoragePrice);
				itemArray.add(externalstorageColor);
				itemArray.add(externalstorageQuantity);
				itemArray.add(retailer);
				itemArray.add(sale);
				itemArray.add(original);
				itemArray.add(rebate);
				//itemArray.add(totalSales);
				
				
				storages.put(externalstorageName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return storages;
		
	}
	
	public static void insertAllProductsToMySQLFromXML()
	{
		ArrayList<Object> products;
		HashMap<String, Phone> phones;
		HashMap<String, Laptop> laptops;
		HashMap<String, SmartWatch> smartwatches;
		HashMap<String, Speaker> speakers;
		HashMap<String, Headphone> headphones;
		HashMap<String, ExternalStorage> externalstorages;
		HashMap<String, Accessory> accessories;
	
		SAXParserForProducts sp = new SAXParserForProducts();
		products = sp.loadDataStore();
		
		phones = (HashMap<String,Phone>)products.get(0);
		laptops = (HashMap<String, Laptop>)products.get(1);
		smartwatches = (HashMap<String, SmartWatch>)products.get(2);
		speakers = (HashMap<String, Speaker>)products.get(3);
		headphones = (HashMap<String, Headphone>)products.get(4);
		externalstorages = (HashMap<String, ExternalStorage>)products.get(5);
		accessories = (HashMap<String, Accessory>)products.get(6);
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			
			for(Map.Entry<String,Phone> m :phones.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Phone";
				Phone s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			
			for(Map.Entry<String,SmartWatch> m :smartwatches.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Smart Watch";
				SmartWatch s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Laptop> m :laptops.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Laptop";
				Laptop s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Speaker> m :speakers.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Speaker";
				Speaker s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Accessory> m :accessories.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Accessory";
				Accessory s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Headphone> m :headphones.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Headphone";
				Headphone s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			for(Map.Entry<String,ExternalStorage> m :externalstorages.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "External Storage";
				ExternalStorage s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				pst.setString(9,s.getCondition());
				pst.setInt(10,s.getQty());
				pst.setString(11,s.getSale());
				pst.setString(12,s.getRebate());
				pst.setFloat(13,s.getOriginal());
				
				pst.execute();
			}
			
			conn.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public static void deleteAllProductsFromTableMySQL()
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String safeUpdates = "SET SQL_SAFE_UPDATES=0; ;";
			Statement stmt1 = conn.createStatement();
			stmt1.execute(safeUpdates);
			
			String deleteFromProducts = "delete from products;";
			Statement stmt = conn.createStatement();
			stmt.execute(deleteFromProducts);
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertProductInMySQL(String id, String productType, String retailer, String imagePath, String productName,
												String company, String condition, Float price, String color, int quantity, String sale, String rebate, Float original)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color, itemCondition, quantity, sale, rebate, original) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(insertProducts);
			
			ps.setString(1,id);
			ps.setString(2,productType);
			ps.setString(3,retailer);
			ps.setString(4,imagePath);
			ps.setString(5,productName);
			ps.setString(6,company);
			ps.setFloat(7,price);
			ps.setString(8,color);
			ps.setString(9,condition);
			ps.setInt(10,quantity);
			ps.setString(11,sale);
			ps.setString(12,rebate);
			ps.setFloat(13,original);
			
			ps.execute();
			
			///////////////////////////////////////////////////////////////
			if(productType.equals("Phone"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO phones(phoneImage, phoneName, phoneCompany, phoneCondition, phonePrice, phoneColor,phoneQuantity, phoneRebate, phoneSale, phoneOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
			
			pst.execute();
				
			}
			else if(productType.equals("Laptop"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO laptops(laptopImage, laptopName, laptopCompany, laptopCondition, laptopPrice, laptopColor,laptopQuantity, laptopRebate, laptopSale, laptopOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Smart Watch"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO watches(watchImage, watchName, watchCompany, watchCondition, watchPrice, watchColor,watchQuantity, watchRebate, watchSale, watchOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Speaker"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO speakers(speakerImage, speakerName, speakerCompany, speakerCondition, speakerPrice, speakerColor,speakerQuantity, speakerRebate, speakerSale, speakerOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Headphone"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO headphones(headphoneImage, headphoneName, headphoneCompany, headphoneCondition, headphonePrice, headphoneColor,headphoneQuantity, headphoneRebate, headphoneSale, headphoneOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
						
			
				pst.execute();
				
			}
			else if(productType.equals("External Storage"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO storages(externalstorageImage, externalstorageName, externalstorageCompany, externalstorageCondition, externalstoragePrice, externalstorageColor,externalstorageQuantity, externalstorageRebate, externalstorageSale, externalstorageOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Accessory"))
			{
				String insertIntoOrderItemQuery = "INSERT INTO accessory(accessoryImage, accessoryName, accessoryCompany, accessoryCondition, accessoryPrice, accessoryColor,accessoryQuantity, accessoryRebate, accessorySale, accessoryOriginal,retailer) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
		
			
			pst.setString(1,imagePath);
			pst.setString(2,productName);
			pst.setString(3,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(4,condition);
			pst.setInt(7,quantity);
			pst.setString(8,sale);
			pst.setString(9,rebate);
			pst.setFloat(10,original);
			pst.setString(11,retailer);
						
			
				pst.execute();
				
			}
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void deleteProductFromMySQL(String productName, String type)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertIntoOrderTotalQuery1 = "DELETE FROM products where name=?;";
			PreparedStatement ps = conn.prepareStatement(insertIntoOrderTotalQuery1);
			
			ps.setString(1,productName);
			
			ps.execute();
			
			if(type.equals("Phone"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM phones where phoneName=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			}
			
			else if(type.equals("Smart Watch"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM watches where watchName=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			}
			
			else if(type.equals("Laptop"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM laptops where laptopName=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			}
			
			else if(type.equals("Speaker"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM speakers where speakerName=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			}
			
			else if(type.equals("Headphone"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM headphones where headphoneName=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			}
			
			else if(type.equals("External Storage"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM storages where externalStorageName=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			}
			else if(type.equals("Accessory"))
			{
				String insertIntoOrderTotalQuery = "DELETE FROM accessory where accessoryName=?;";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
				pst.setString(1,productName);
			
				pst.execute();
			}
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void updateProductFromMySQL(String itemName, String productType, String retailer, String imagePath, String productName,
												String company, String condition, Float price, String color, int quantity, String sale, String rebate, Float original)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techlobbydatabase?autoReconnect=true&useSSL=false", "root", "admin");
			
			String insertIntoOrderItemQuery1 = "Update products set retailer=?, image=?, name=?, company=?, price=?, color=?, itemCondition=?, quantity=?,sale=?,rebate=?,original=? where name=?";
			PreparedStatement ps = conn.prepareStatement(insertIntoOrderItemQuery1);
			
			ps.setString(1,retailer);
			ps.setString(2,imagePath);
			ps.setString(3,productName);
			ps.setString(4,company);
			ps.setFloat(5,price);
			ps.setString(6,color);
			ps.setString(7,condition);
			ps.setInt(8,quantity);
			ps.setString(9,sale);
			ps.setString(10,rebate);
			ps.setFloat(11,original);
			
			ps.setString(12,itemName);
			
			ps.execute();
			
			/////
			
			if(productType.equals("Phone"))
			{
				String insertIntoOrderItemQuery = "Update phones set retailer=?, phoneImage=?, phoneName=?, phoneCompany=?, phonePrice=?, phoneColor=?, phoneCondition=?, phoneQuantity=?,phoneSale=?,phoneRebate=?,phoneOriginal=? where phoneName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Laptop"))
			{
				String insertIntoOrderItemQuery = "Update laptops set retailer=?, laptopImage=?, laptopName=?, laptopCompany=?, laptopPrice=?, laptopColor=?, laptopCondition=?, laptopQuantity=?,laptopSale=?,laptopRebate=?,laptopOriginal=? where laptopName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Smart Watch"))
			{
				String insertIntoOrderItemQuery = "Update watches set retailer=?, watchImage=?, watchName=?, watchCompany=?, watchPrice=?, watchColor=?, watchCondition=?, watchQuantity=?,watchSale=?,watchRebate=?,watchOriginal=? where watchName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Speaker"))
			{
				String insertIntoOrderItemQuery = "Update speakers set retailer=?, speakerImage=?, speakerName=?, speakerCompany=?, speakerPrice=?, speakerColor=?, speakerCondition=?, speakerQuantity=?,speakerSale=?,speakerRebate=?,speakerOriginal=? where speakerName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Headphone"))
			{
				String insertIntoOrderItemQuery = "Update headphones set retailer=?, headphoneImage=?, headphoneName=?, headphoneCompany=?, headphonePrice=?, headphoneColor=?, headphoneCondition=?, headphoneQuantity=?,headphoneSale=?,headphoneRebate=?,headphoneOriginal=? where headphoneName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			else if(productType.equals("External Storage"))
			{
				String insertIntoOrderItemQuery = "Update storages set retailer=?, externalstorageImage=?, externalstorageName=?, externalstorageCompany=?, externalstoragePrice=?, externalstorageColor=?, externalstorageCondition=?, externalstorageQuantity=?,externalstorageSale=?,externalstorageRebate=?,externalstorageOriginal=? where externalStorageName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			else if(productType.equals("Accessory"))
			{
				String insertIntoOrderItemQuery = "Update accessory set retailer=?, accessoryImage=?, accessoryName=?, accessoryCompany=?, accessoryPrice=?, accessoryColor=?, accessoryCondition=?, accessoryQuantity=?,accessorySale=?,accessoryRebate=?,accessoryOriginal=? where accessoryName=?";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
				
				
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			pst.setString(7,condition);
			pst.setInt(8,quantity);
			pst.setString(9,sale);
			pst.setString(10,rebate);
			pst.setFloat(11,original);
			pst.setString(12,itemName);
						
			
				pst.execute();
				
			}
			
			
			
			/////
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Object> getProductsFromMySQL()
	{
		ArrayList<Object> products = new ArrayList<Object>();
		
		HashMap<String, Phone> phones= new HashMap<String, Phone>();
		HashMap<String, Laptop> laptops= new HashMap<String, Laptop>();
		HashMap<String, SmartWatch> watches= new HashMap<String, SmartWatch>();
		HashMap<String, Speaker> speakers= new HashMap<String, Speaker>();
		HashMap<String, Headphone> headphones= new HashMap<String, Headphone>();
		HashMap<String, ExternalStorage> externalstorages= new HashMap<String, ExternalStorage>();
		HashMap<String, Accessory> accessories= new HashMap<String, Accessory>();
		
		HashMap<String, Product> productsMap= new HashMap<String, Product>();
		
		Phone phone;
		Laptop laptop;
		SmartWatch watch;
		Speaker speaker;
		Headphone headphone;
		ExternalStorage storage;
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
				
				int quantity = rs.getInt("quantity");
				String sale = rs.getString("sale");
				Float original = rs.getFloat("original");
				String rebate = rs.getString("rebate");
				String condition = rs.getString("itemCondition");
				
				
				product = new Product();
					
				product.setType(type);
				product.setId(id);
				product.setRetailer(retailer);
				product.setImage(image);
				product.setName(name);
				product.setCompany(company);
				product.setPrice(price);
				product.setColor(color);
				product.setOriginal(original);
				product.setQty(quantity);
				product.setSale(sale);
				product.setRebate(rebate);
				product.setCondition(condition);
				
				
					
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
					watch = new SmartWatch();
					
					watch.setId(id);
					watch.setRetailer(retailer);
					watch.setImage(image);
					watch.setName(name);
					watch.setCompany(company);
					watch.setPrice(price);
					watch.setColor(color);
					
					watches.put(name, watch);
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
					storage = new ExternalStorage();
					
					storage.setId(id);
					storage.setRetailer(retailer);
					storage.setImage(image);
					storage.setName(name);
					storage.setCompany(company);
					storage.setPrice(price);
					storage.setColor(color);
					
					externalstorages.put(name, storage);
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
			products.add(watches);
			products.add(laptops);
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

}


