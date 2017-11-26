-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: techlobbydatabase
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accessory`
--

DROP TABLE IF EXISTS `accessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessory` (
  `accessoryImage` varchar(100) DEFAULT NULL,
  `accessoryName` varchar(100) DEFAULT NULL,
  `accessoryCompany` varchar(100) DEFAULT NULL,
  `accessoryCondition` varchar(100) DEFAULT NULL,
  `accessoryPrice` float DEFAULT NULL,
  `accessoryColor` varchar(100) DEFAULT NULL,
  `accessoryQuantity` int(11) DEFAULT NULL,
  `accessoryRebate` varchar(100) DEFAULT NULL,
  `accessorySale` varchar(40) DEFAULT NULL,
  `accessoryOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessory`
--

LOCK TABLES `accessory` WRITE;
/*!40000 ALTER TABLE `accessory` DISABLE KEYS */;
INSERT INTO `accessory` VALUES ('images/gearVr.jpg','Gear VR','Samsung','New',59.99,'White',98,'Yes','Yes',109.99,'techLobby'),('images/appleacc.jpg','Apple Mobile Gimbal','Apple','New',99.99,'Grey',99,'No','No',99.99,'techLobby'),('images/appleacc1.jpg','iPhone7 Plus Case','Apple','New',19.99,'Blue',97,'No','No',19.99,'techLobby'),('images/lgacc1.jpg','LG Armband Case','LG','New',29.99,'Black',100,'No','No',29.99,'techLobby'),('images/motoacc1.jpg','Motorola Car Charger','Motorola','New',10.99,'Black',100,'No','Yes',20.99,'techLobby'),('images/motoacc2.jpg','Motorola Smart Watch Charger','Motorola','New',19.99,'Black',100,'Yes','No',19.99,'techLobby'),('images/fastCharge.jpg','Samsung Wireless Charger','Samsung','New',39.99,'Black',99,'No','Yes',49.99,'techLobby'),('images/samacc1.jpg','Gear 360 Tripod','Samsung','New',49.99,'Black',100,'No','No',49.99,'techLobby');
/*!40000 ALTER TABLE `accessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_login_details`
--

DROP TABLE IF EXISTS `admin_login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_login_details` (
  `emailAddress` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_login_details`
--

LOCK TABLES `admin_login_details` WRITE;
/*!40000 ALTER TABLE `admin_login_details` DISABLE KEYS */;
INSERT INTO `admin_login_details` VALUES ('rumin@gmail.com','Welcome123');
/*!40000 ALTER TABLE `admin_login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `headphones`
--

DROP TABLE IF EXISTS `headphones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `headphones` (
  `headphoneImage` varchar(100) DEFAULT NULL,
  `headphoneName` varchar(100) DEFAULT NULL,
  `headphoneCompany` varchar(100) DEFAULT NULL,
  `headphoneCondition` varchar(100) DEFAULT NULL,
  `headphonePrice` float DEFAULT NULL,
  `headphoneColor` varchar(100) DEFAULT NULL,
  `headphoneQuantity` int(11) DEFAULT NULL,
  `headphoneRebate` varchar(100) DEFAULT NULL,
  `headphoneSale` varchar(40) DEFAULT NULL,
  `headphoneOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `headphones`
--

LOCK TABLES `headphones` WRITE;
/*!40000 ALTER TABLE `headphones` DISABLE KEYS */;
INSERT INTO `headphones` VALUES ('images/sonyear1.jpg','XB450AP EXTRA BASS Headphone','Sony','New',149.99,'Black',100,'Yes','No',149.99,'techLobby'),('images/samear1.jpg','New OEM Original Samsung headphone','Samsung','New',69.99,'White',100,'No','No',69.99,'techLobby'),('images/skull1.jpg','Uproar Wireless headphone','Skull Candy','New',149.99,'White',99,'Yes','No',149.99,'techLobby'),('images/skull2.jpg','INK_D 2 Earphones','Skull Candy','New',19.99,'Black',100,'No','Yes',29.99,'techLobby'),('images/sonyear2.jpg','Sony MDR-EX150 In-Ear Headphone','Sony','New',29.99,'Black',100,'No','Yes',39.99,'techLobby'),('images/beatsear1.jpg','Beats Solo2 Luxe edition On-ear','Beats','New',199.99,'Sky Blue',99,'Yes','No',199.99,'techLobby'),('images/samear2.jpg','Level On Wireless Pro Headphone','Samsung','New',99.99,'White',100,'No','No',99.99,'techLobby'),('images/beatsear2.jpg','Powerbeats 2','Beats','New',159.99,'Sky Blue',98,'No','No',159.99,'techLobby'),('images/appleear1.jpg','Apple Earpods','Apple','New',59.99,'White',100,'No','No',59.99,'techLobby'),('images/appleear2.jpg','Apple Airpods','Apple','New',119.99,'White',100,'No','No',119.99,'techLobby');
/*!40000 ALTER TABLE `headphones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laptops`
--

DROP TABLE IF EXISTS `laptops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laptops` (
  `laptopImage` varchar(100) DEFAULT NULL,
  `laptopName` varchar(100) DEFAULT NULL,
  `laptopCompany` varchar(100) DEFAULT NULL,
  `laptopCondition` varchar(100) DEFAULT NULL,
  `laptopPrice` float DEFAULT NULL,
  `laptopColor` varchar(100) DEFAULT NULL,
  `laptopQuantity` int(11) DEFAULT NULL,
  `laptopRebate` varchar(100) DEFAULT NULL,
  `laptopSale` varchar(40) DEFAULT NULL,
  `laptopOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laptops`
--

LOCK TABLES `laptops` WRITE;
/*!40000 ALTER TABLE `laptops` DISABLE KEYS */;
INSERT INTO `laptops` VALUES ('images/macbookAir.jpg','Macbook Air','Apple','New',849,'Silver',96,'Yes','No',849,'techLobby'),('images/notebook5.jpg','Notebook 5','Samsung','New',859.99,'Black',99,'No','Yes',900.99,'techLobby'),('images/xps13.jpg','XPS 13','Dell','New',1799.99,'Black',99,'Yes','No',1799.99,'techLobby'),('images/envy.jpg','Envy 17t','HP','New',1199.99,'Black',97,'No','No',1199.99,'techLobby'),('images/hp360.jpg','HP Spectre 360','HP','New',1399.99,'Black',99,'No','Yes',1499.99,'techLobby'),('images/notebook9.jpg','Notebook 9','Samsung','New',1119.99,'Silver',98,'Yes','Yes',1499.99,'techLobby'),('images/macbookpro.jpg','Macbook Pro','Apple','New',2099.99,'Silver',98,'No','No',2099.99,'techLobby'),('images/xps15.jpg','XPS 15','Dell','New',1999.99,'Black',100,'No','No',1999.99,'techLobby'),('images/legion.jpg','Legion Y520','Lenovo','Refurbished',950.99,'Black',99,'No','No',950.99,'techLobby');
/*!40000 ALTER TABLE `laptops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `orderId` varchar(40) DEFAULT NULL,
  `itemType` varchar(40) DEFAULT NULL,
  `itemName` varchar(40) DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL,
  `itemPrice` float DEFAULT NULL,
  `itemQty` int(11) DEFAULT NULL,
  `orderDate` varchar(40) DEFAULT NULL,
  `deliveryDate` varchar(40) DEFAULT NULL,
  `customerEmailId` varchar(40) DEFAULT NULL,
  `deliveryAddress` varchar(50) DEFAULT NULL,
  `availQty` int(11) DEFAULT NULL,
  `date_field` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES ('B#208477','Phones','Moto E4 Plus',NULL,199,1,'10/26/2017 01:02:23','11/09/2017','kane@gmail.com','8854 La Crosse',99,'10/26/2017'),('B#481941','Laptops','Macbook Air',NULL,849,1,'10/26/2017 01:02:57','11/09/2017','kane@gmail.com','8854 La Crosse',99,'10/26/2017'),('B#443804','Phones','iPhone7 Plus',NULL,899,1,'10/26/2017 01:03:44','11/09/2017','kane@gmail.com','8854 La Crosse',99,'10/26/2017'),('B#247442','SmartWatches','Asus ZenWatch 3',NULL,159.99,1,'10/26/2017 01:04:30','11/09/2017','jesus@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#235774','Phones','iPhone7 Plus',NULL,899,1,'10/26/2017 01:05:05','11/09/2017','jesus@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#169481','Laptops','Envy 17t',NULL,1199.99,1,'10/26/2017 01:05:37','11/09/2017','jesus@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#169474','Headphones','Powerbeats 2',NULL,159.99,1,'10/26/2017 01:06:30','11/09/2017','silva@gmail.com','8854 La Crosse',99,'10/26/2017'),('B#50074','SmartWatches','iWatch2',NULL,299.99,1,'10/26/2017 01:07:11','11/09/2017','silva@gmail.com','8854 La Crosse',99,'10/26/2017'),('B#232728','Phones','iPhone7 Plus',NULL,899,1,'10/26/2017 01:07:48','11/09/2017','silva@gmail.com','8854 La Crosse',98,'10/26/2017'),('B#438388','Phones','Galaxy Note 8',NULL,930,1,'10/26/2017 01:08:50','11/09/2017','messi@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#101152','ExternalStorages','32GB Prime microSD Memory Card',NULL,8.99,1,'10/26/2017 01:09:27','11/09/2017','messi@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#463505','Laptops','Macbook Pro',NULL,2099.99,1,'10/26/2017 01:09:59','11/09/2017','messi@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#300066','SmartWatches','Asus ZenWatch 2',NULL,139.99,1,'10/26/2017 01:10:34','11/09/2017','messi@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#528317','Accessories','Apple Mobile Gimbal',NULL,99.99,1,'10/26/2017 01:26:55','11/09/2017','messi@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#324387','Phones','Galaxy Note 8',NULL,930,1,'10/26/2017 01:27:29','11/09/2017','messi@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#432023','Phones','iPhone7 Plus',NULL,899,1,'10/26/2017 01:51:55','11/09/2017','pep@gmail.com','8854 La Crosse',96,'10/26/2017'),('B#306001','Phones','iPhone7 Plus',NULL,899,1,'10/26/2017 01:59:43','11/09/2017','sane@gmail.com','2901 S Kiing Dr',95,'10/26/2017'),('B#552554','Laptops','Notebook 5',NULL,859.99,1,'10/26/2017 02:02:50','11/09/2017','stones@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#365866','Phones','Galaxy Note 8',NULL,930,1,'10/26/2017 02:03:31','11/09/2017','stones@gmail.com','2901 S King Dr',97,'10/26/2017'),('B#248627','SmartWatches','Asus ZenWatch 3',NULL,159.99,1,'10/26/2017 02:04:15','11/09/2017','stones@gmail.com','2901 S King Dr',98,'10/26/2017'),('B#23255','Accessories','Gear VR',NULL,59.99,1,'10/26/2017 02:05:06','11/09/2017','stones@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#415537','Headphones','Uproar Wireless headphone',NULL,149.99,1,'10/26/2017 02:05:45','11/09/2017','stones@gmail.com','2901 S King Dr',99,'10/26/2017'),('B#109839','Phones','Galaxy Note 8',NULL,930,1,'10/27/2017 23:59:06','11/10/2017','sergio@gmail.com','2901 S King Dr',96,'10/27/2017'),('B#136570','Laptops','HP Spectre 360',NULL,1399.99,1,'10/27/2017 23:59:39','11/10/2017','sergio@gmail.com','2901 S King Dr',99,'10/27/2017'),('B#275181','SmartWatches','Moto 360',NULL,199.99,1,'10/28/2017 02:16:15','11/11/2017','walker@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#26575','Phones','iPhone7',NULL,799,1,'10/28/2017 02:16:56','11/11/2017','walker@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#34080','Accessories','iPhone7 Plus Case',NULL,19.99,1,'10/28/2017 02:17:44','11/11/2017','walker@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#170586','ExternalStorages','SanDisk CZ60 32GB',NULL,9.99,1,'10/28/2017 02:18:32','11/11/2017','walker@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#78504','Phones','Moto E4 Plus',NULL,199,1,'10/28/2017 02:34:26','11/11/2017','mendy@gmail.com','2901 S King Dr',98,'10/28/2017'),('B#78805','Accessories','Gear VR',NULL,59.99,1,'10/28/2017 02:44:30','11/11/2017','pep@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#382947','Laptops','XPS 13',NULL,1799.99,1,'10/28/2017 02:45:29','11/11/2017','pep@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#34792','SmartWatches','Gear S2',NULL,99.99,1,'10/28/2017 02:46:34','11/11/2017','kevin@gmail.com','2901 S King Dr',99,'10/28/2017'),('B#490615','Speakers','LG FH2: 50W LOUDR',NULL,99.99,1,'10/28/2017 02:47:31','11/11/2017','kevin@gmail.com','2901 S King Dr',99,'10/28/2017'),('B#350482','Headphones','Powerbeats 2',NULL,159.99,1,'10/28/2017 02:48:42','11/11/2017','kevin@gmail.com','2901 S King Dr',98,'10/28/2017'),('B#204618','Phones','HTC 10',NULL,559,1,'10/28/2017 02:54:43','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#489408','Laptops','Notebook 9',NULL,1119.99,1,'10/28/2017 02:58:04','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#476527','Phones','Galaxy Note 8',NULL,930,1,'10/28/2017 11:39:05','11/11/2017','messi@gmail.com','8854 La Crosse',95,'10/28/2017'),('B#476527','Laptops','Macbook Pro',NULL,2099.99,1,'10/28/2017 11:39:05','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#2951','Phones','LG V30',NULL,549,1,'10/28/2017 11:40:51','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#2951','Speakers','Beats Pill',NULL,199.99,1,'10/28/2017 11:40:51','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#38326','Speakers','Beats Pill',NULL,199.99,1,'10/28/2017 11:56:33','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#38326','Laptops','Envy 17t',NULL,1199.99,1,'10/28/2017 11:56:33','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#489832','SmartWatches','iWatch2',NULL,299.99,1,'10/28/2017 12:35:28','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#489832','Speakers','Wirless Bluetooth Speaker',NULL,149.99,1,'10/28/2017 12:35:28','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#313231','Laptops','Macbook Air',NULL,849,1,'10/28/2017 14:10:23','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#313231','Phones','iPhone7',NULL,799,1,'10/28/2017 14:10:23','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#118416','Accessories','Samsung Wireless Charger',NULL,39.99,1,'10/28/2017 14:17:15','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#118416','Speakers','Radian360 Wifi/Bluetooth Speaker',NULL,149.99,1,'10/28/2017 14:17:15','11/11/2017','messi@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#373178','Accessories','iPhone7 Plus Case',NULL,19.99,1,'10/28/2017 15:02:08','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#373178','ExternalStorages','SanDisk CZ60 32GB',NULL,9.99,1,'10/28/2017 15:02:08','11/11/2017','messi@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#180818','Accessories','iPhone7 Plus Case',NULL,19.99,1,'10/28/2017 16:47:07','11/11/2017','ruminshah007@gmail.com','8854 La Crosse',97,'10/28/2017'),('B#180818','Headphones','Beats Solo2 Luxe edition On-ear',NULL,199.99,1,'10/28/2017 16:47:07','11/11/2017','ruminshah007@gmail.com','8854 La Crosse',99,'10/28/2017'),('B#329378','Laptops','Macbook Air',NULL,849,1,'10/28/2017 17:29:52','11/11/2017','ruminshah007@gmail.com','8854 La Crosse',97,'10/28/2017'),('B#329378','Speakers','Wirless Bluetooth Speaker',NULL,149.99,1,'10/28/2017 17:29:52','11/11/2017','ruminshah007@gmail.com','8854 La Crosse',98,'10/28/2017'),('B#461022','Laptops','Legion Y520',NULL,950.99,1,'10/29/2017 00:52:15','11/12/2017','jesus@gmail.com','2901 S King Dr',99,'10/29/2017'),('B#461022','Phones','iPhone X',NULL,999,1,'10/29/2017 00:52:15','11/12/2017','jesus@gmail.com','2901 S King Dr',99,'10/29/2017'),('B#378347','Phones','Galaxy S8',NULL,599,1,'10/30/2017 01:12:14','11/13/2017','pep@gmail.com','8854 La Crosse',99,'10/30/2017'),('B#378347','Phones','iPhone7 Plus',NULL,899,1,'10/30/2017 01:12:14','11/13/2017','pep@gmail.com','8854 La Crosse',94,'10/30/2017'),('B#111276','Laptops','Notebook 9',NULL,1119.99,1,'10/30/2017 01:14:43','11/13/2017','messi@gmail.com','2901 S King Dr',98,'10/30/2017'),('B#111276','SmartWatches','iWatch2',NULL,299.99,1,'10/30/2017 01:14:43','11/13/2017','messi@gmail.com','2901 S King Dr',97,'10/30/2017'),('B#21576','Phones','iPhone X',NULL,999,1,'10/30/2017 01:20:43','11/13/2017','messi@gmail.com','8854 La Crosse',98,'10/30/2017'),('B#21576','Phones','iPhone7',NULL,799,1,'10/30/2017 01:20:43','11/13/2017','messi@gmail.com','8854 La Crosse',97,'10/30/2017'),('B#135478','Laptops','Macbook Air',NULL,849,1,'10/30/2017 01:22:19','11/13/2017','messi@gmail.com','8854 La Crosse',96,'10/30/2017'),('B#135478','SmartWatches','iWatch3',NULL,399.99,1,'10/30/2017 01:22:19','11/13/2017','messi@gmail.com','8854 La Crosse',99,'10/30/2017'),('B#341859','Speakers','Beats Pill',NULL,199.99,1,'10/30/2017 01:24:45','11/13/2017','messi@gmail.com','8854 La Crosse',97,'10/30/2017'),('B#341859','Laptops','Envy 17t',NULL,1199.99,1,'10/30/2017 01:24:45','11/13/2017','messi@gmail.com','8854 La Crosse',97,'10/30/2017'),('B#452895','Phones','Galaxy S8',NULL,599,1,'10/30/2017 01:58:40','11/13/2017','messi@gmail.com','2901 S King Dr',98,'10/30/2017'),('B#495841','Phones','Galaxy Note 8',NULL,930,1,'10/30/2017 02:00:10','11/13/2017','messi@gmail.com','8854 La Crosse',94,'10/30/2017'),('B#289178','Products','JBL Pulse Splashproof Bluetooth Speaker',NULL,149.99,1,'11/09/2017 21:06:58','11/23/2017','kane@gmail.com','8854 La Crosse Ave',99,'11/09/2017'),('B#61275','Products','JBL Pulse Splashproof Bluetooth Speaker',NULL,149.99,1,'11/09/2017 21:11:08','11/23/2017','kane@gmail.com','8854 La Crosse Ave',98,'11/09/2017');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_total`
--

DROP TABLE IF EXISTS `order_total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_total` (
  `orderId` varchar(40) DEFAULT NULL,
  `orderDate` varchar(40) DEFAULT NULL,
  `deliveryDate` varchar(40) DEFAULT NULL,
  `totalAmount` float DEFAULT NULL,
  `customerEmailId` varchar(40) DEFAULT NULL,
  `deliveryAddress` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_total`
--

LOCK TABLES `order_total` WRITE;
/*!40000 ALTER TABLE `order_total` DISABLE KEYS */;
INSERT INTO `order_total` VALUES ('B#208477','10/26/2017 01:02:23','11/09/2017',199,'kane@gmail.com','8854 La Crosse'),('B#481941','10/26/2017 01:02:57','11/09/2017',849,'kane@gmail.com','8854 La Crosse'),('B#443804','10/26/2017 01:03:44','11/09/2017',899,'kane@gmail.com','8854 La Crosse'),('B#247442','10/26/2017 01:04:30','11/09/2017',159.99,'jesus@gmail.com','2901 S King Dr'),('B#235774','10/26/2017 01:05:05','11/09/2017',899,'jesus@gmail.com','2901 S King Dr'),('B#169481','10/26/2017 01:05:37','11/09/2017',1199.99,'jesus@gmail.com','2901 S King Dr'),('B#169474','10/26/2017 01:06:30','11/09/2017',159.99,'silva@gmail.com','8854 La Crosse'),('B#50074','10/26/2017 01:07:11','11/09/2017',299.99,'silva@gmail.com','8854 La Crosse'),('B#232728','10/26/2017 01:07:48','11/09/2017',899,'silva@gmail.com','8854 La Crosse'),('B#438388','10/26/2017 01:08:50','11/09/2017',930,'messi@gmail.com','2901 S King Dr'),('B#101152','10/26/2017 01:09:27','11/09/2017',8.99,'messi@gmail.com','2901 S King Dr'),('B#463505','10/26/2017 01:09:59','11/09/2017',2099.99,'messi@gmail.com','2901 S King Dr'),('B#300066','10/26/2017 01:10:34','11/09/2017',139.99,'messi@gmail.com','2901 S King Dr'),('B#528317','10/26/2017 01:26:55','11/09/2017',99.99,'messi@gmail.com','2901 S King Dr'),('B#324387','10/26/2017 01:27:29','11/09/2017',930,'messi@gmail.com','2901 S King Dr'),('B#315896','10/26/2017 01:36:37','11/09/2017',899,'pep@gmail.com','8854 La Crosse'),('B#432023','10/26/2017 01:51:55','11/09/2017',899,'pep@gmail.com','8854 La Crosse'),('B#306001','10/26/2017 01:59:43','11/09/2017',899,'sane@gmail.com','2901 S Kiing Dr'),('B#552554','10/26/2017 02:02:50','11/09/2017',859.99,'stones@gmail.com','2901 S King Dr'),('B#365866','10/26/2017 02:03:31','11/09/2017',930,'stones@gmail.com','2901 S King Dr'),('B#248627','10/26/2017 02:04:15','11/09/2017',159.99,'stones@gmail.com','2901 S King Dr'),('B#23255','10/26/2017 02:05:06','11/09/2017',59.99,'stones@gmail.com','2901 S King Dr'),('B#415537','10/26/2017 02:05:45','11/09/2017',149.99,'stones@gmail.com','2901 S King Dr'),('B#109839','10/27/2017 23:59:06','11/10/2017',930,'sergio@gmail.com','2901 S King Dr'),('B#136570','10/27/2017 23:59:39','11/10/2017',1399.99,'sergio@gmail.com','2901 S King Dr'),('B#275181','10/28/2017 02:16:15','11/11/2017',199.99,'walker@gmail.com','8854 La Crosse'),('B#26575','10/28/2017 02:16:56','11/11/2017',799,'walker@gmail.com','8854 La Crosse'),('B#34080','10/28/2017 02:17:44','11/11/2017',19.99,'walker@gmail.com','8854 La Crosse'),('B#170586','10/28/2017 02:18:32','11/11/2017',9.99,'walker@gmail.com','8854 La Crosse'),('B#78504','10/28/2017 02:34:26','11/11/2017',199,'mendy@gmail.com','2901 S King Dr'),('B#78805','10/28/2017 02:44:30','11/11/2017',59.99,'pep@gmail.com','8854 La Crosse'),('B#382947','10/28/2017 02:45:29','11/11/2017',1799.99,'pep@gmail.com','8854 La Crosse'),('B#34792','10/28/2017 02:46:34','11/11/2017',99.99,'kevin@gmail.com','2901 S King Dr'),('B#490615','10/28/2017 02:47:31','11/11/2017',99.99,'kevin@gmail.com','2901 S King Dr'),('B#350482','10/28/2017 02:48:42','11/11/2017',159.99,'kevin@gmail.com','2901 S King Dr'),('B#525486','10/28/2017 02:49:21','11/11/2017',19.99,'kevin@gmail.com','2901 S King Dr'),('B#204618','10/28/2017 02:54:43','11/11/2017',559,'messi@gmail.com','8854 La Crosse'),('B#489408','10/28/2017 02:58:04','11/11/2017',1119.99,'messi@gmail.com','8854 La Crosse'),('B#476527','10/28/2017 11:39:05','11/11/2017',3029.99,'messi@gmail.com','8854 La Crosse'),('B#2951','10/28/2017 11:40:51','11/11/2017',748.99,'messi@gmail.com','8854 La Crosse'),('B#38326','10/28/2017 11:56:33','11/11/2017',1399.98,'messi@gmail.com','8854 La Crosse'),('B#489832','10/28/2017 12:35:28','11/11/2017',449.98,'messi@gmail.com','8854 La Crosse'),('B#313231','10/28/2017 14:10:23','11/11/2017',1648,'messi@gmail.com','8854 La Crosse'),('B#118416','10/28/2017 14:17:15','11/11/2017',189.98,'messi@gmail.com','8854 La Crosse'),('B#373178','10/28/2017 15:02:08','11/11/2017',29.98,'messi@gmail.com','8854 La Crosse'),('B#180818','10/28/2017 16:47:07','11/11/2017',219.98,'ruminshah007@gmail.com','8854 La Crosse'),('B#329378','10/28/2017 17:29:52','11/11/2017',998.99,'ruminshah007@gmail.com','8854 La Crosse'),('B#461022','10/29/2017 00:52:15','11/12/2017',1949.99,'jesus@gmail.com','2901 S King Dr'),('B#378347','10/30/2017 01:12:14','11/13/2017',1498,'pep@gmail.com','8854 La Crosse'),('B#111276','10/30/2017 01:14:43','11/13/2017',1419.98,'messi@gmail.com','2901 S King Dr'),('B#21576','10/30/2017 01:20:43','11/13/2017',1798,'messi@gmail.com','8854 La Crosse'),('B#135478','10/30/2017 01:22:19','11/13/2017',1248.99,'messi@gmail.com','8854 La Crosse'),('B#341859','10/30/2017 01:24:45','11/13/2017',1399.98,'messi@gmail.com','8854 La Crosse'),('B#452895','10/30/2017 01:58:40','11/13/2017',599,'messi@gmail.com','2901 S King Dr'),('B#495841','10/30/2017 02:00:10','11/13/2017',930,'messi@gmail.com','8854 La Crosse'),('B#289178','11/09/2017 21:06:58','11/23/2017',149.99,'kane@gmail.com','8854 La Crosse Ave'),('B#61275','11/09/2017 21:11:08','11/23/2017',149.99,'kane@gmail.com','8854 La Crosse Ave');
/*!40000 ALTER TABLE `order_total` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones` (
  `phoneImage` varchar(100) DEFAULT NULL,
  `phoneName` varchar(100) DEFAULT NULL,
  `phoneCompany` varchar(100) DEFAULT NULL,
  `phoneCondition` varchar(100) DEFAULT NULL,
  `phonePrice` float DEFAULT NULL,
  `phoneColor` varchar(100) DEFAULT NULL,
  `phoneQuantity` int(11) DEFAULT NULL,
  `phoneRebate` varchar(100) DEFAULT NULL,
  `phoneSale` varchar(40) DEFAULT NULL,
  `phoneOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES ('images/iPhone7.jpg','iPhone7','Apple','Refurbished',799,'Black',97,'No','No',799,'techLobby'),('images/lgv30.jpg','LG V30','LG','New',549,'Black',99,'Yes','Yes',649.99,'techLobby'),('images/motoe4.jpg','Moto E4 Plus','Motorola','New',199,'Grey',98,'Yes','No',199,'techLobby'),('images/iphone7plus.jpg','iPhone7 Plus','Apple','New',899,'Black',94,'No','No',899,'techLobby'),('images/note8.jpg','Galaxy Note 8','Samsung','New',930,'Black',94,'No','No',930,'techLobby'),('images/galaxyS8.jpg','Galaxy S8','Samsung','New',599,'Black',98,'No','Yes',799.99,'techLobby'),('images/htc10.jpg','HTC 10','HTC','New',559,'Black',99,'Yes','No',559,'techLobby'),('images/iphoneX.jpg','iPhone X','Apple','New',999,'Black',98,'No','No',999,'techLobby'),('images/iphone7.jpg','Galaxy Note 9','Samsung','New',999.99,'White',100,'No','Yes',1199.99,'techLobby');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `retailer` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  `itemCondition` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sale` varchar(40) DEFAULT NULL,
  `rebate` varchar(40) DEFAULT NULL,
  `original` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (5,'Phone','techLobby','images/motoe4.jpg','Moto E4 Plus','Motorola',199,'Grey','New',98,'No','Yes',199),(6,'Phone','techLobby','images/iphone7plus.jpg','iPhone7 Plus','Apple',899,'Black','New',95,'No','No',899),(7,'Phone','techLobby','images/note8.jpg','Galaxy Note 8','Samsung',930,'Black','New',95,'No','No',930),(8,'Phone','techLobby','images/galaxyS8.jpg','Galaxy S8','Samsung',599,'Black','New',100,'Yes','No',799.99),(9,'Phone','techLobby','images/htc10.jpg','HTC 10','HTC',559,'Black','New',99,'No','Yes',559),(10,'Phone','techLobby','images/iphoneX.jpg','iPhone X','Apple',999,'Black','New',100,'No','No',999),(1,'Phone','techLobby','images/iPhone7.jpg','iPhone7','Apple',799,'Black','New',99,'No','No',799),(4,'Phone','techLobby','images/lgv30.jpg','LG V30','LG',549,'Black','New',99,'Yes','Yes',649.99),(25,'Smart Watch','techLobby','images/moto360.jpg','Moto 360','Motorola',199.99,'Black','Refurbished',99,'No','No',199.99),(26,'Smart Watch','techLobby','images/iwatch3.jpg','iWatch3','Apple',399.99,'Black','New',100,'No','No',399.99),(28,'Smart Watch','techLobby','images/asus1.jpg','Asus ZenWatch 3','Asus',159.99,'Copper','New',98,'No','No',159.99),(29,'Smart Watch','techLobby','images/asus2.jpg','Asus ZenWatch 2','Asus',139.99,'Sea Green','New',99,'Yes','No',149.99),(21,'Smart Watch','techLobby','images/iwatch2.jpg','iWatch2','Apple',299.99,'Gold','New',98,'No','Yes',299.99),(22,'Smart Watch','techLobby','images/s2gear.jpg','Gear S2','Samsung',99.99,'Dark Gray','New',99,'Yes','No',119.99),(23,'Smart Watch','techLobby','images/lgwatch1.jpg','LG Watch Sport','LG',199.99,'Grey','New',100,'Yes','No',219.99),(11,'Laptop','techLobby','images/macbookAir.jpg','Macbook Air','Apple',849,'Silver','New',99,'No','Yes',849),(12,'Laptop','techLobby','images/notebook5.jpg','Notebook 5','Samsung',859.99,'Black','New',99,'Yes','No',900.99),(13,'Laptop','techLobby','images/xps13.jpg','XPS 13','Dell',1799.99,'Black','New',99,'No','Yes',1799.99),(15,'Laptop','techLobby','images/envy.jpg','Envy 17t','HP',1199.99,'Black','New',98,'No','No',1199.99),(16,'Laptop','techLobby','images/hp360.jpg','HP Spectre 360','HP',1399.99,'Black','New',99,'Yes','No',1499.99),(17,'Laptop','techLobby','images/notebook9.jpg','Notebook 9','Samsung',1119.99,'Silver','New',99,'Yes','Yes',1499.99),(18,'Laptop','techLobby','images/macbookpro.jpg','Macbook Pro','Apple',2099.99,'Silver','New',98,'No','No',2099.99),(19,'Laptop','techLobby','images/xps15.jpg','XPS 15','Dell',1999.99,'Black','New',100,'No','No',1999.99),(20,'Laptop','techLobby','images/legion.jpg','Legion Y520','Lenovo',950.5,'Black','Refurbished',100,'No','No',950.5),(33,'Speaker','techLobby','images/lgspeaker1.jpg','LG FH2: 50W LOUDR','LG',99.99,'Black','New',99,'No','No',99.99),(34,'Speaker','techLobby','images/lgspeaker2.jpg','LG NP1540 Wirless Speaker','LG',119.99,'Black','New',100,'Yes','No',129.99),(35,'Speaker','techLobby','images/sony2.jpg','Google Assistnant Built-in Wirless Speaker','Sony',129.99,'White','New',100,'No','Yes',129.99),(36,'Speaker','techLobby','images/beatsspeaker1.jpg','Beats Pill','Beats',199.99,'Black','New',98,'No','Yes',199.99),(40,'Speaker','techLobby','images/jbl2.jpg','JBL Pulse Splashproof Bluetooth Speaker','JBL',149.99,'Black','New',100,'No','No',149.99),(37,'Speaker','techLobby','images/samspeaker2.jpg','Level Box Pro','Samsung',99.99,'Black','New',100,'Yes','No',119.99),(38,'Speaker','techLobby','images/beatsspeaker2.jpg','Beats Beatbox','Beats',159.99,'Red','New',100,'No','No',159.99),(39,'Speaker','techLobby','images/jbl1.jpg','JBL Charge 3','JBL',139.99,'Black','New',100,'No','Yes',139.99),(31,'Speaker','techLobby','images/sony1.jpg','Wirless Bluetooth Speaker','Sony',149.99,'Black','New',99,'No','Yes',149.99),(32,'Speaker','techLobby','images/samspeaker1.jpg','Radian360 Wifi/Bluetooth Speaker','Samsung',149.99,'Black','New',100,'Yes','No',159.99),(61,'Accessory','techLobby','images/gearVr.jpg','Gear VR','Samsung',59.99,'White','New',98,'Yes','Yes',109.99),(63,'Accessory','techLobby','images/appleacc.jpg','Apple Mobile Gimbal','Apple',99.99,'Grey','New',99,'No','No',99.99),(64,'Accessory','techLobby','images/appleacc1.jpg','iPhone7 Plus Case','Apple',19.99,'Blue','New',99,'No','No',19.99),(66,'Accessory','techLobby','images/lgacc1.jpg','LG Armband Case','LG',29.99,'Black','New',100,'No','No',29.99),(67,'Accessory','techLobby','images/motoacc1.jpg','Motorola Car Charger','Motorola',10.99,'Black','New',100,'Yes','No',20.99),(68,'Accessory','techLobby','images/motoacc2.jpg','Motorola Smart Watch Charger','Motorola',19.99,'Black','New',100,'No','Yes',19.99),(69,'Accessory','techLobby','images/fastCharge.jpg','Samsung Wireless Charger','Samsung',39.99,'Black','New',100,'Yes','No',49.99),(70,'Accessory','techLobby','images/samacc1.jpg','Gear 360 Tripod','Samsung',49.99,'Black','New',100,'No','No',49.99),(48,'Headphone','techLobby','images/beatsear2.jpg','Powerbeats 2','Beats',159.99,'Sky Blue','New',98,'No','No',159.99),(49,'Headphone','techLobby','images/appleear1.jpg','Apple Earpods','Apple',59.99,'White','New',100,'No','No',59.99),(50,'Headphone','techLobby','images/appleear2.jpg','Apple Airpods','Apple',119.99,'White','New',100,'No','No',119.99),(41,'Headphone','techLobby','images/sonyear1.jpg','XB450AP EXTRA BASS Headphone','Sony',149.99,'Black','New',100,'No','Yes',149.99),(42,'Headphone','techLobby','images/samear1.jpg','New OEM Original Samsung headphone','Samsung',69.99,'White','New',100,'No','No',69.99),(43,'Headphone','techLobby','images/skull1.jpg','Uproar Wireless headphone','Skull Candy',149.99,'White','New',99,'No','Yes',149.99),(44,'Headphone','techLobby','images/skull2.jpg','INK_D 2 Earphones','Skull Candy',19.99,'Black','New',100,'Yes','No',29.99),(45,'Headphone','techLobby','images/sonyear2.jpg','Sony MDR-EX150 In-Ear Headphone','Sony',29.99,'Black','New',100,'Yes','No',39.99),(46,'Headphone','techLobby','images/beatsear1.jpg','Beats Solo2 Luxe edition On-ear','Beats',199.99,'Sky Blue','New',100,'No','Yes',199.99),(47,'Headphone','techLobby','images/samear2.jpg','Level On Wireless Pro Headphone','Samsung',99.99,'White','New',100,'No','No',99.99),(56,'External Storage','techLobby','images/mem2.jpg','SDXC card 64 GB Transcend Ultimate Class 10','Transcend',19.99,'Red','New',99,'Yes','No',29.99),(57,'External Storage','techLobby','images/mem3.jpg','32GB Prime microSD Memory Card','PNY',8.99,'Grey','New',99,'Yes','No',19.99),(59,'External Storage','techLobby','images/hd2.jpg','2.5 external hard drive 2 TB Western Digital Elements Black USB 3.0','Western Digital',159.99,'Black','New',100,'No','Yes',159.99),(60,'External Storage','techLobby','images/hd3.jpg','SEAGATE Backup Plus Portable Hard Drive - 1 TB','SEAGATE',119.99,'Black','New',100,'Yes','No',149.99),(51,'External Storage','techLobby','images/flash1.jpg','SanDisk CZ60 32GB','SanDisk',9.99,'Black','New',99,'Yes','No',12.99),(52,'External Storage','techLobby','images/flash2.jpg','SanDisk Cruzer Glide CZ60 128GB','SanDisk',29.99,'Black','New',100,'No','No',29.99),(54,'External Storage','techLobby','images/flash4.jpg','USB 3.0 Flash Drive DUO 128GB','Samsung',28.99,'Silver','New',100,'No','No',28.99),(55,'External Storage','techLobby','images/mem1.jpg','SanDisk 64GB Ultra UHS-I microSDXC Memory SD','SanDisk',19.99,'Black','New',100,'No','No',19.99),(43681,'Phone','techLobby','images/iphone7.jpg','Galaxy Note 9','Samsung',999.99,'White','New',100,'Yes','No',1199.99);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `firstName` varchar(40) DEFAULT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `emailId` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `phoneNumber` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('Rumin','Shah','ruminshah007@gmail.com','Welcome123','7735163183'),('David','Silva','silva@gmail.com','Welcome123','7735163183'),('Kevin','De Bruyne','kevin@gmail.com','Welcome123','7735163183'),('Raheem','Sterling','raheem@gmail.com','Welcome123','8479471234'),('Leroy','Sane','sane@gmail.com','Welcome123','7735163183'),('Bernardo','Silva','bernardo@gmail.com','Welcome123','7735163183'),('Vincent','Kompany','vinny@gmail.com','Welcome123','7735163183'),('John','Stones','stones@gmail.com','Welcome123','7735163183'),('Sergio','Aguero','sergio@gmail.com','Welcome123','7735163183'),('Kyle','Waker','walker@gmail.com','Welcome123','7735163183'),('Joe','Hart','joe@gmail.com','Welcome123','7735163183'),('Gabriel','Jesus','jesus@gmail.com','Welcome123','7735163183'),('Ederson','Moraes','moraes@gmail.com','Welcome123','7735163183'),('Lionel','Messi','messi@gmail.com','Welcome123','7735163183'),('Alexis','Sanchez','alexis@gmail.com','Welcome123','7735163183'),('Benjamin','Mendy','mendy@gmail.com','Welcome123','7735163183'),('Claudio','Bravo','bravo@gmail.com','Welcome123','7735163183'),('Pep','Guardiola','pep@gmail.com','Welcome123','7735163183'),('Oliver','Giroud','giroud@gmail.com','Welcome123','7735163183'),('Harry','Kane','kane@gmail.com','Welcome123','7735163183');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speakers`
--

DROP TABLE IF EXISTS `speakers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speakers` (
  `speakerImage` varchar(100) DEFAULT NULL,
  `speakerName` varchar(100) DEFAULT NULL,
  `speakerCompany` varchar(100) DEFAULT NULL,
  `speakerCondition` varchar(100) DEFAULT NULL,
  `speakerPrice` float DEFAULT NULL,
  `speakerColor` varchar(100) DEFAULT NULL,
  `speakerQuantity` int(11) DEFAULT NULL,
  `speakerRebate` varchar(100) DEFAULT NULL,
  `speakerSale` varchar(40) DEFAULT NULL,
  `speakerOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speakers`
--

LOCK TABLES `speakers` WRITE;
/*!40000 ALTER TABLE `speakers` DISABLE KEYS */;
INSERT INTO `speakers` VALUES ('images/sony1.jpg','Wirless Bluetooth Speaker','Sony','New',149.99,'Black',98,'Yes','No',149.99,'techLobby'),('images/samspeaker1.jpg','Radian360 Wifi/Bluetooth Speaker','Samsung','New',149.99,'Black',99,'No','Yes',159.99,'techLobby'),('images/lgspeaker1.jpg','LG FH2: 50W LOUDR','LG','New',99.99,'Black',99,'No','No',99.99,'techLobby'),('images/lgspeaker2.jpg','LG NP1540 Wirless Speaker','LG','New',119.99,'Black',100,'No','Yes',129.99,'techLobby'),('images/sony2.jpg','Google Assistnant Built-in Wirless Speaker','Sony','New',129.99,'White',100,'Yes','No',129.99,'techLobby'),('images/beatsspeaker1.jpg','Beats Pill','Beats','New',189.99,'Black',97,'No','Yes',199.99,'techLobby'),('images/samspeaker2.jpg','Level Box Pro','Samsung','New',99.99,'Black',100,'No','Yes',119.99,'techLobby'),('images/beatsspeaker2.jpg','Beats Beatbox','Beats','New',159.99,'Red',100,'No','No',159.99,'techLobby'),('images/jbl1.jpg','JBL Charge 3','JBL','New',139.99,'Black',100,'Yes','No',139.99,'techLobby'),('images/jbl2.jpg','JBL Pulse Splashproof Bluetooth Speaker','JBL','New',149.99,'Black',100,'No','No',149.99,'techLobby');
/*!40000 ALTER TABLE `speakers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storages`
--

DROP TABLE IF EXISTS `storages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storages` (
  `externalstorageImage` varchar(100) DEFAULT NULL,
  `externalstorageName` varchar(100) DEFAULT NULL,
  `externalstorageCompany` varchar(100) DEFAULT NULL,
  `externalstorageCondition` varchar(100) DEFAULT NULL,
  `externalstoragePrice` float DEFAULT NULL,
  `externalstorageColor` varchar(100) DEFAULT NULL,
  `externalstorageQuantity` int(11) DEFAULT NULL,
  `externalstorageRebate` varchar(100) DEFAULT NULL,
  `externalstorageSale` varchar(40) DEFAULT NULL,
  `externalstorageOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storages`
--

LOCK TABLES `storages` WRITE;
/*!40000 ALTER TABLE `storages` DISABLE KEYS */;
INSERT INTO `storages` VALUES ('images/flash1.jpg','SanDisk CZ60 32GB','SanDisk','New',9.99,'Black',98,'No','Yes',12.99,'techLobby'),('images/flash2.jpg','SanDisk Cruzer Glide CZ60 128GB','SanDisk','New',29.99,'Black',100,'No','No',29.99,'techLobby'),('images/flash4.jpg','USB 3.0 Flash Drive DUO 128GB','Samsung','New',28.99,'Silver',100,'No','No',28.99,'techLobby'),('images/mem1.jpg','SanDisk 64GB Ultra UHS-I microSDXC Memory SD','SanDisk','New',19.99,'Black',100,'No','No',19.99,'techLobby'),('images/mem2.jpg','SDXC card 64 GB Transcend Ultimate Class 10','Transcend','New',19.99,'Red',99,'No','Yes',29.99,'techLobby'),('images/mem3.jpg','32GB Prime microSD Memory Card','PNY','New',8.99,'Grey',99,'No','Yes',19.99,'techLobby'),('images/hd2.jpg','2.5 external hard drive 2 TB Western Digital Elements Black USB 3.0','Western Digital','New',159.99,'Black',100,'Yes','No',159.99,'techLobby'),('images/hd3.jpg','SEAGATE Backup Plus Portable Hard Drive - 1 TB','SEAGATE','New',119.99,'Black',100,'No','Yes',149.99,'techLobby');
/*!40000 ALTER TABLE `storages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watches`
--

DROP TABLE IF EXISTS `watches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watches` (
  `watchImage` varchar(100) DEFAULT NULL,
  `watchName` varchar(100) DEFAULT NULL,
  `watchCompany` varchar(100) DEFAULT NULL,
  `watchCondition` varchar(100) DEFAULT NULL,
  `watchPrice` float DEFAULT NULL,
  `watchColor` varchar(100) DEFAULT NULL,
  `watchQuantity` int(11) DEFAULT NULL,
  `watchRebate` varchar(100) DEFAULT NULL,
  `watchSale` varchar(40) DEFAULT NULL,
  `watchOriginal` float DEFAULT NULL,
  `retailer` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watches`
--

LOCK TABLES `watches` WRITE;
/*!40000 ALTER TABLE `watches` DISABLE KEYS */;
INSERT INTO `watches` VALUES ('images/iwatch2.jpg','iWatch2','Apple','New',299.99,'Gold',97,'Yes','No',299.99,'techLobby'),('images/s2gear.jpg','Gear S2','Samsung','New',109.99,'Dark Gray',99,'Yes','Yes',119.99,'techLobby'),('images/lgwatch1.jpg','LG Watch Sport','LG','New',199.99,'Grey',100,'No','Yes',219.99,'techLobby'),('images/moto360.jpg','Moto 360','Motorola','Refurbished',199.99,'Black',99,'No','No',199.99,'techLobby'),('images/iwatch3.jpg','iWatch3','Apple','New',399.99,'Black',99,'No','No',399.99,'techLobby'),('images/asus1.jpg','Asus ZenWatch 3','Asus','New',159.99,'Copper',98,'No','No',159.99,'techLobby'),('images/asus2.jpg','Asus ZenWatch 2','Asus','New',139.99,'Sea Green',99,'No','Yes',149.99,'techLobby');
/*!40000 ALTER TABLE `watches` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-10  2:20:40
