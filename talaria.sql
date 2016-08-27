CREATE DATABASE  IF NOT EXISTS `talaria` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `talaria`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: talaria
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `acctype` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `mname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'leondojima','qweqwe',1,'leondojima@hotmail.com','Leon','K','Dojima'),(2,'leondojimar','qweqwe',2,'leondojimar@hotmail.com','Leon','K','Dojima'),(3,'leondojimard','qweqwe',1,'leondojimard@hotmail.com','Leon','K','Dojima'),(4,'leondojimsfsard','qweqwe',1,'leondojasfimard@hotmail.com','Leon','K','Dojima'),(5,'admindull','qweqwe',3,'mahmen@gmail.com','Admin','L','Dull'),(7,'prodman','qweqwe',2,'prodman@proman.org','Produck','G','Manijeer'),(8,'johndoe','qweqwe',1,'johndoedoe@doe.tho','John','J.','Doe'),(9,'brobro','qweqwe',1,'brobro@bro.bro','Jimothy','L.','Bro'),(10,'jimm02','qweqwe',1,'jimmy@yahoo.com','Jim','M.','Mim'),(11,'accman','qweqwe',3,'accman@gmail.com','Accounting','M.','Manager'),(12,'produman','qweqwe',2,'produman@gmail.com','Produ','M','Man');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing_addr`
--

DROP TABLE IF EXISTS `billing_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billing_addr` (
  `accountID` int(11) DEFAULT NULL,
  `houseNo` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `subdivision` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postalCode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing_addr`
--

LOCK TABLES `billing_addr` WRITE;
/*!40000 ALTER TABLE `billing_addr` DISABLE KEYS */;
INSERT INTO `billing_addr` VALUES (9,'12','Bro','Brodivision','Brocity',NULL,'Brountry'),(10,'1','Taft','Taft','Manila',NULL,'Philippines');
/*!40000 ALTER TABLE `billing_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `accountID` int(11) NOT NULL,
  `productID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fpproducts`
--

DROP TABLE IF EXISTS `fpproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fpproducts` (
  `productID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fpproducts`
--

LOCK TABLES `fpproducts` WRITE;
/*!40000 ALTER TABLE `fpproducts` DISABLE KEYS */;
/*!40000 ALTER TABLE `fpproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `time` varchar(45) DEFAULT NULL,
  `details` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Killer Crocs',4,'If looks can kill, so can these! Made from 100% pure crocodile leather. Made in Gotham.',1299.99,'http://vignette3.wikia.nocookie.net/arkhamcity/images/0/04/Killer_Croc_(Arkham_Origins).jpg/revision/latest?cb=20160229225831'),(2,'Not Cool Shoes',2,'Not too cool for you :P',1337,'http://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2015/12/17/103250871-eminemXcarhartt_airjordan4.600x400.jpg?v=1450371134'),(3,'Save the Whales',4,'Dolphin lives matter. Save the narwhals.',0.99,'http://www.thinkgeek.com/images/products/zoom/1cf9_shark_plush_slippers.jpg'),(4,'Old School',3,'Damn Daniel. Back at it again. With them sandals.',19.8,'http://macys-o.scene7.com/is/image/MCY/products/5/optimized/3335955_fpx.tif?op_sharpen=1&wid=400&hei=489&fit=fit,1&$filterlrg$'),(5,'Cowboy Boots',1,'May or may not come with snakes in the boots.',849.99,'http://img15.deviantart.net/84fa/i/2012/363/3/6/boots_with_spurs_by_animevegas-d5pk66x.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(100) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `review` varchar(200) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'leondojima',1,'Best Crocs Ever',5),(2,'brobro',8,'Quack :(',1),(3,'jimm02',1,'I like this not really :)',4);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `productID` int(11) DEFAULT NULL,
  `amountSold` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,2),(2,1),(3,1),(4,4),(5,1),(6,1),(7,1),(8,1),(9,0);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_addr`
--

DROP TABLE IF EXISTS `shipping_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_addr` (
  `accountID` int(11) DEFAULT NULL,
  `houseNo` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `subdivision` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postalCode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_addr`
--

LOCK TABLES `shipping_addr` WRITE;
/*!40000 ALTER TABLE `shipping_addr` DISABLE KEYS */;
INSERT INTO `shipping_addr` VALUES (8,'24','Avocado St','Brink','Las Pineapples',NULL,'Chile'),(8,'24','Avocado St','Brink','Las Pineapples',NULL,NULL),(9,'12','Bro','Brodivision','Brocity',NULL,'Brountry'),(10,'1','Taft','Taft','Manila',NULL,'Philippines');
/*!40000 ALTER TABLE `shipping_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `productID` int(11) DEFAULT NULL,
  `stockAmt` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-27 19:04:40
