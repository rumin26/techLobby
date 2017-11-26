import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class SalesChart extends HttpServlet {
	
		


	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		//loadDataFromXML();
	
		LinkedHashMap<String, ArrayList<Object>> phones;
		phones= MySqlDataStoreUtilities.getPhones();
		
		LinkedHashMap<String, ArrayList<Object>> watches;
		watches= MySqlDataStoreUtilities.getWatches();
		
		LinkedHashMap<String, ArrayList<Object>> laptops;
		laptops= MySqlDataStoreUtilities.getLaptops();
		
		LinkedHashMap<String, ArrayList<Object>> speakers;
		speakers= MySqlDataStoreUtilities.getSpeakers();
		
		LinkedHashMap<String, ArrayList<Object>> headphones;
		headphones= MySqlDataStoreUtilities.getHeadphones();

		LinkedHashMap<String, ArrayList<Object>> storages;
		storages= MySqlDataStoreUtilities.getStorages();
		
		LinkedHashMap<String, ArrayList<Object>> acc;
		acc= MySqlDataStoreUtilities.getAccessories();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>techLobby</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		out.println("<script type=\"text/javascript\">");
		out.println("google.charts.load('current', {'packages':['corechart']});");
		
		
		out.println("google.charts.setOnLoadCallback(drawChart);");
		out.println("google.charts.setOnLoadCallback(drawLaptopChart);");
		out.println("google.charts.setOnLoadCallback(drawWatchChart);");
		out.println("google.charts.setOnLoadCallback(drawSpeakerChart);");
		out.println("google.charts.setOnLoadCallback(drawHeadphoneChart);");
		out.println("google.charts.setOnLoadCallback(drawExternalStorageChart);");
		out.println("google.charts.setOnLoadCallback(drawAccessoryChart);");
		
		////-----------For Phones
		out.println("function drawChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :phones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
	   
		}
        // Set chart options
        out.println("var options = {'title':'Phones Sales','width':600,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div_phones'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		////--------For Laptops
		out.println("function drawLaptopChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :laptops.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
	   
		}
        // Set chart options
        out.println("var options = {'title':'Laptops Sales','width':600,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div_laptops'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		
		////--------For Smart Watches
		out.println("function drawWatchChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :watches.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
	   
		}
        // Set chart options
        out.println("var options = {'title':'Smart Watches Sales','width':600,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div_watches'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		////--------For Speakers
		out.println("function drawSpeakerChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :speakers.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
	   
		}
        // Set chart options
        out.println("var options = {'title':'Speakers Sales','width':1000,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div_speakers'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		////--------For Headphones
		out.println("function drawHeadphoneChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :headphones.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
			
	   
		}
        // Set chart options
        out.println("var options = {'title':'Headphones Sales','width':900,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		////--------For External Storage
		out.println("function drawExternalStorageChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :storages.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
	   
		}
        // Set chart options
        out.println("var options = {'title':'External Storages Sales','width':900,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div_external'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		
		////--------For Accessory
		out.println("function drawAccessoryChart() {");

        // Create the data table.
		
        out.println("var data = new google.visualization.DataTable();");
		
		out.println("data.addColumn('string', 'Product Name'); data.addColumn('number', 'Sales');");
		for(Map.Entry<String, ArrayList<Object>> m :acc.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				String name = (String)values.get(1);
				float price = (Float)values.get(4);
				int quantity = (Integer)values.get(6);
				int qty = 100 - quantity;
			//System.out.println(qty);
			//System.out.println(s.getPrice());
			
				double sales = Math.floor(price * qty * 100.0) / 100.0 ;
			
				out.println(" data.addRows([['"+name+"', "+sales+"]]);");
	   
		}
        // Set chart options
        out.println("var options = {'title':'Accessories Sales','width':900,'height':600};");
		

        // Instantiate and draw our chart, passing in some options.
        out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div_accessory'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		////-----------------------------////
		
		out.println("</script>");
		
		out.println("</head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">tech<span>Lobby</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"SalesServlet\">Sales Report</a></li>");
		out.println("<li><a href=\"SalesChart\">Bar Chart</a></li>");
		out.println("<li><a href=\"DailySalesServlet\">Daily Sales</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");

		out.println("<div id=\"chart_div_phones\"></div>");
		out.println("<div id=\"chart_div_laptops\"></div>");
		out.println("<div id=\"chart_div_watches\"></div>");
		out.println("<div id=\"chart_div_speakers\"></div>");
		out.println("<div id=\"chart_div\"></div>");
		out.println("<div id=\"chart_div_external\"></div>");
		out.println("<div id=\"chart_div_accessory\"></div>");
		

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
	}
}
