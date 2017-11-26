create database techLobbydatabase;
use techLobbydatabase;
create table registration(firstName varchar(40), lastName varchar(40), emailId varchar(40), password varchar(40), phoneNumber varchar(40));
create table order_item(orderId integer,itemName varchar(40),itemPrice double, itemQty integer, orderDate varchar(40), deliveryDate varchar(40), customerEmailId varchar(40), deliveryAddress varchar(100));
create table order_total(orderId integer, orderDate varchar(40), deliveryDate varchar(40), totalAmount double, customerEmailId varchar(40), deliveryAddress varchar(100));
