# techLobby

techLobby is a servlet-based web application that will allow customers to place orders online from techLobby website for smart portables which has user categories of Store Manager, Customers, and Salesmen.

Customer can place an order online, check the status of the order, or cancel the order. The Store Manager can Add/Delete/Update products and Salesman can create Customer accounts and can Add/Delete/Update customers’ orders.

Used MySQL for storing account information, customer transaction, and order updates while product reviews are stored in NoSQL database (MongoDB).

Used Google Charts API to show bar chart for Sales and Inventory Reports for Store Manager.

Used Ajax and JavaScript for implementing search auto completion feature where users can search products.

Added Deal Match feature where Python script was used to generate text file containing products matching with deals from the Best Buy Twitter account.

--> To Install & Run the application:
1. Copy the "assignment1" folder and paste it into the "webapps" folder of tomcat directory.
2. All the servlets are mentioned in the deployment descriptor file web.xml
3. Start the MongoDb Server first. Then start the MySql server. After bothe the database servers are started,
then start the Tomcat server.
4. Run the application by writing localhost/assignment1/HomeServlet in the browser.

-----------------

--> Store Manager and Salesman login credentials:

The username and password for the StoreManager and Salesman are stored in MySql table called admin_login_details.

Email id: rumin@gmail.com
Password: Welcome123

------------------

--> Import techlobbydatabase.sql in your MySql to see the created database.

--> MongoDB Database name: CustomerReviews | Collection name: myReviews
	
--> Trending functionality has been provided in the left navigation bar

--> All the code for MySQL is in the class MySqlDataStoreUtilities

--> All the code for MongoDB is in the class MongoDBDataStoreUtilities

--> All AutoComplete feature code for MySQL is in the class AjaxUtility.java

--> All DealMatch code is in the class DealMatches.java

--> BestBuyDealMatches.pynb Python Script and DealMatches.txt are in the root directory of the application.
