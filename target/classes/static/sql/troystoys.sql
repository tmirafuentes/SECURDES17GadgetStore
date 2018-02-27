CREATE DATABASE IF NOT EXISTS `troys_toys`;
USE `troys_toys`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
	`userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`email` varchar(50) NOT NULL,
	`firstName` varchar(25) NOT NULL,	
	`lastName` varchar(25) NOT NULL,
	`mailingAddress` varchar(150) NOT NULL,
	`birthday` date NOT NULL,
	`mobileNumber` varchar(25) NOT NULL,
	PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
	`userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`email` varchar(50) NOT NULL,
	`firstName` varchar(25) NOT NULL,	
	`lastName` varchar(25) NOT NULL,
	PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `brandId` int(11) unsigned AUTO_INCREMENT NOT NULL,
  `brandName` varchar(100) NOT NULL,
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
	`productId` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`productName` varchar(100) NOT NULL,
	`productPrice` double(6, 2) NOT NULL,
	`productQuantity` int(5) NOT NULL,
	`productDescription` text NOT NULL,
	`productBrand` int(11) unsigned DEFAULT NULL,
	PRIMARY KEY (`productId`),
	FOREIGN KEY (`productBrand`) REFERENCES `brand` (`brandId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `product_rating`
--

-- DROP TABLE IF EXISTS `product_rating`;
-- CREATE TABLE `product_rating` (
--	`product_id` int(11) unsigned NOT NULL,
--	`customer_id` int(11) unsigned NOT NULL,
--	`rating` int(1) NOT NULL,
--	PRIMARY KEY (`product_id`, `customer_id`),
--	KEY `fk_product_customer_idx` (`customer_id`),
--	CONSTRAINT `fk_productrating_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE,
--	CONSTRAINT `fk_productrating_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
	`transaction_id` int(11) unsigned NOT NULL,
	`customer_id` int(11) unsigned NOT NULL,
	`product_id` int(11) unsigned NOT NULL,
	`quantity` int(5) NOT NULL,
	`totalAmount` double(7, 2) NOT NULL,
	`timestamp` date NOT NULL,
	`status` boolean NOT NULL,
	PRIMARY KEY (`transaction_id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`product_id`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;