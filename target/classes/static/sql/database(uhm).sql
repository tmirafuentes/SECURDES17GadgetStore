CREATE DATABASE  IF NOT EXISTS `troys_toys` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `troys_toys`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: troys_toys
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthdate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mail_address` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_description` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` double NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `product_brand` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
--INSERT INTO `product` VALUES (6,'An Aspire Z1 all-in-one has everything you need, including DVD drives and 10-point multi-touch screens.','Acer Aspire AZ1-602 18.5-in Non-Touch Intel Celeron/4GB/1TB/Windows 10 All in One Desktop',21999,10,'Acer','Desktop'),(7,'ASUS VivoStick is a pocket-sized Windows PC that gives you desktop-like computing.','Asus VivoStick PC (TS10) Intel Atom x5-Z8350/2GB/32GB/Windows 10',8120,20,'Asus','Desktop'),(8,'With the latest 4th generation Intel Core processors experience new levels of multitasking performance.','Asus K31AD-PH001s, Intel Core i3-4160, 4GB Memory, 1TB HDD, Windows 8.1',24984,10,'Asus','Desktop'),(9,'The HP Pavilion All-In-One brings style and performance to the center of your home.','HP Pavilion 24-b217d 23.8-in FHD Touch Intel Core i3-7100T/4GB/1TB/Windows 10 AIO Desktop',43990,5,'HP','Desktop'),(10,'Intel HD Graphics / 11.6“ 1366 x 768, 16:9 aspect ratio, LED-backlit TFT LCD / Windows 10','Acer TravelMate TMB116-M-C4HA 11.6-in Intel Celeron Quad Core N3150/2GB/500GB/Windows 10',12990,30,'Acer','Laptop'),(11,'1,500 mAh / Single Core 1.2GHz / Dual Camera (3MP Rear + VGA Front) / 4GB Internal Memory + 512MB RAM','Samsung Galaxy V 3G 4-inch Android Kitkat 4.4.2, Dual SIM',3490,20,'Samsung','Mobile'),(12,'4-inch TFT capacitive touchscreen / Spreadtrum SC8830 Quad-core 1.2 GHz / 756MB / 8GB/ 5 MP, LED flash, Secondary VGA / Android OS, v5.1 (Lollipop)','Samsung Galaxy J1 Mini 4-inch Quad-core 1.2 GHz/756MP/8GB/5MP Camera/Android 5.1',3990,10,'Samsung','Mobile'),(13,'3GB RAM, 32GB, Qualcomm MSM8940, Octacore, 8.0 Inch 1920x1200 IPS Screen, Quad Speaker Certified by Harman Kardon Audio, Android 7.0, 4800mAh','Huawei Mediapad M3 lite',13490,5,'Huawei','Tablet'),(14,'Qualcomm Snapdragon 425 1.4GHz Quad-core / 2GB / 16GB / Front 5MP / Rear 8MP / 5-inch qHD Display / 2500mAh Battery','Vivo Y53 (Crown Gold/Space Gray/Matte Black) 5-in qHD Quad-core/2GB/16GB/8MP & 5MP/Android 6.0 Dual SIM',5990,10,'Vivo','Mobile'),(15,'16GB Storage / 7.9-inch Retina Display / 5MP & 1.2MP Camera / Dual-core 1.3 GHz Cyclone (ARM v8-based) / PowerVR G6430 (quad-core graphics) / iOS 8.1','Apple iPad Mini 3 16GB Space Gray with Touch ID',17999,5,'Apple','Tablet'),(16,'64GB / 10.5‑inch iPad Pro / A10X Fusion chip with 64‑bit architecture / Embedded M10 coprocessor / 12-megapixel camera / 7-megapixel FaceTime HD Camera / iOS 10','Apple 10.5-inch iPad Pro Wi-Fi 64GB (Space Gray, Rose Gold, Gold, Silver)',36900,10,'Apple','Tablet'),(17,'Intel HD Graphics 620 integrated GPU / 13.5” PixelSense Display w/ 10-point multi-touch / Windows 10 Pro','Microsoft Surface Book 2 13.5-in PixelSense Touch 7th Gen. Intel Core i5-7300U/8GB/256GB/Win10 Pro',99999,10,'Microsoft','Laptop'),(18,'Intel Celeron N3350 Processor 1.10 GHz (2M Cache, up to 2.4 GHz / 2GB / 500GB / Intel® Integrated Graphics / 11.6 HD TN AG (SLIM) / Windows 10','Lenovo IdeaPad 120S-11IAP (Denim Blue/Mineral Grey) 11.6-in HD Celeron N3350/2GB/500GB/Windows 10',14988,10,'Lenovo','Laptop'),(19,'AMD A8-4555M / 2GB DDR3 / 500GB HDD / WINDOWS 8 / 14inch WXGA LED / WIFI 802.11BGN / BLUETOOTH / 4CELL','Lenovo S405 (5935-9092 Grey) AMD A8-4555M/2GB/500GB/14-inch/Win 8',19999,50,'Lenovo','Laptop');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `total_amount` double NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK_btane64ls5uag9j1yuq0yepow` (`customer_id`),
  KEY `FK_h789x4fbd41rl47r5um82go6u` (`product_id`),
  CONSTRAINT `FK_btane64ls5uag9j1yuq0yepow` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`user_id`),
  CONSTRAINT `FK_h789x4fbd41rl47r5um82go6u` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-02  1:42:29
