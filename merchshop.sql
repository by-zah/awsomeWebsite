/*
SQLyog Community v12.4.3 (64 bit)
MySQL - 10.4.11-MariaDB : Database - merchshopdb
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`merchshopdb` /*!40100 DEFAULT CHARACTER SET cp1251 */;

USE `merchshopdb`;

/*Table structure for table `active_orders` */

DROP TABLE IF EXISTS `active_orders`;

CREATE TABLE `active_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `productsOrderID` int(11) DEFAULT NULL,
  `paymentMethod` varchar(40) DEFAULT NULL,
  `shippingMethodID` int(11) DEFAULT NULL,
  `shippingAddressID` int(11) DEFAULT NULL,
  `totalProductPrice` double DEFAULT NULL,
  `deliveryPrice` double DEFAULT NULL,
  `discountAmount` double DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `datePlaced` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userID` (`userID`),
  KEY `shippingMethodID` (`shippingMethodID`),
  KEY `shippingAddressID` (`shippingAddressID`),
  CONSTRAINT `active_orders_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `active_orders_ibfk_2` FOREIGN KEY (`shippingMethodID`) REFERENCES `shipping_methods` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `active_orders_ibfk_3` FOREIGN KEY (`shippingAddressID`) REFERENCES `shipping_methods` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

/*Data for the table `active_orders` */

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=cp1251;

/*Data for the table `categories` */

insert  into `categories`(`id`,`title`,`description`) values 
(1,'Одежда','Футболки,худи,куртки'),
(2,'Игрушки','фигурки,статуэтки,конструктор'),
(3,'Аксессуары','сумки,рюкзаки');

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryID` (`categoryID`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `categories` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=cp1251;

/*Data for the table `products` */

insert  into `products`(`id`,`title`,`categoryID`,`description`) values 
(1,'SPIDER-MAN TALKING ACTION FIGURE',2,'Spider-Man has plenty of fighting words to battle his enemies as this talking action figure. And if that doesn\'t work, then the friendly neighborhood super hero can use his two web slingers!'),
(2,'DEADPOOL T-SHIRT FOR ADULTDS',1,'The only thing better than this all-cotton Deadpool tee is a hot taco. What\'s not to love?'),
(3,'IRON MAN \'\' I LOVE YOU 3000\'\' T-SHIRT FOR MEN',1,'Beneath his armor, Tony Stark possesses a big heart, one that was touched when his daughter Morgan professed \'\'I love you 3000\'\' in Marvel\'s Avengers: Endgame. Iron Man\'s face appears within the text of the memorable quote'),
(4,'MARVEL STUDIOS 10TH ANNIVERSARY T-SHIRT FOR ADULTS',1,'If you\'re \'\'More than a fan\'\' of Marvel Studios (and who isn\'t?), you\'ll find this double-sided, all-cotton anniversary tee an absolute must. Help celebrate ten years of Marvel-ous movies.'),
(5,'THOR SOUND EFFECTS HAMMER',2,'Make their super hero action come alive with this Thor Hammer! Press the button for phrases, sounds, and vibration effects. Spin Thor\'s mighty Mjolnir by the loop at the bottom of the handle to hear spinning sound effects'),
(6,'LIQUID X MARVEL LOGO TEE',1,'Rep the LIQUID x MARVEL collaboration with this clean logo tee featuring the official horse head red brick lock-up: the insignia of LIQUID x MARVEL. Crisp fusion printed design keeps the design clean, crack-free and durable all on a weighted comfortable c'),
(7,'TEAM LIQUID GEOS TEE',1,'Our 2020 take on a classic Liquid look. Add this quality tee to your collection today.'),
(8,'Funko Pop Чубакка серии \"Звёздные войны\"',2,''),
(9,'MARVEL COMICS DUFFLE BAG',3,'Our mighty Marvel Comics duffle bag packs a punch at the gym or anywhere your adventures lead you. Reinforced straps and colorful comic book cover lining make it a super carry-all for roving avengers');

/*Table structure for table `products_attributes` */

DROP TABLE IF EXISTS `products_attributes`;

CREATE TABLE `products_attributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productID` int(11) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productID` (`productID`),
  CONSTRAINT `products_attributes_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=cp1251;

/*Data for the table `products_attributes` */

insert  into `products_attributes`(`id`,`productID`,`color`,`size`,`price`,`photo`) values 
(1,1,'-','-',900,'images/1_talkingspiderman.jpg'),
(2,2,'-','S',850,'images/2_deadpoolTshirt.jpg'),
(3,2,'-','XXL',850,'images/2_deadpoolTshirt.jpg'),
(4,2,'-','3XL',880,'images/2_deadpoolTshirt.jpg'),
(5,3,'-','M',900,'images/3_loveyou3000.jpg'),
(6,3,'-','L',900,'images/3_loveyou3000.jpg'),
(7,3,'-','XL',900,'images/3_loveyou3000.jpg'),
(8,4,'-','M',1200,'images/4_10thAnniversary.jpg'),
(9,4,'-','L',1200,'images/4_10thAnniversary.jpg'),
(10,4,'-','XXL',1250,'images/4_10thAnniversary.jpg'),
(11,5,'-','-',1000,'images/5_thorHammer.jpg'),
(12,6,'Dark Red','XL',1200,'images/6_tlxmarvel.jpg'),
(13,6,'Blue','L',1100,'images/6_tlxmarvel.jpg'),
(14,6,'Blue','XL',1100,'images/6_tlxmarvel.jpg'),
(15,7,'White','L',1500,'images/7_tlwhite.jpg'),
(16,7,'White','XL',1500,'images/7_tlwhite.jpg'),
(17,7,'Blue','L',1400,'images/7_tlblue.jpg'),
(18,7,'Blue','M',1400,'images/7_tlblue.jpg'),
(19,8,'-','-',400,'images/8_chubaka.jpg'),
(20,9,'-','-',2000,'images/9_marvelbag.jpg');

/*Table structure for table `products_in` */

DROP TABLE IF EXISTS `products_in`;

CREATE TABLE `products_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productAttributeID` int(11) DEFAULT NULL,
  `amountIN` int(11) DEFAULT NULL,
  `dateIN` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productAttributeID` (`productAttributeID`),
  CONSTRAINT `products_in_ibfk_1` FOREIGN KEY (`productAttributeID`) REFERENCES `products_attributes` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=cp1251;

/*Data for the table `products_in` */

insert  into `products_in`(`id`,`productAttributeID`,`amountIN`,`dateIN`) values 
(1,1,10,'2020-05-05 17:12:45'),
(2,2,2,'2020-05-05 17:12:56'),
(3,3,4,'2020-05-05 17:13:09'),
(4,4,3,'2020-05-05 17:13:24'),
(5,5,2,'2020-05-05 17:13:24'),
(6,6,1,'2020-05-05 17:13:24'),
(7,7,4,'2020-05-05 17:13:24'),
(8,8,2,'2020-05-05 17:13:24'),
(9,9,2,'2020-05-05 17:13:24'),
(10,10,3,'2020-05-05 17:13:24'),
(11,11,10,'2020-05-05 17:13:24'),
(12,12,4,'2020-05-05 17:13:24'),
(13,13,3,'2020-05-05 17:13:24'),
(14,14,2,'2020-05-05 17:13:24'),
(15,19,7,'2020-05-05 17:13:24'),
(16,15,3,'2020-05-05 17:13:24'),
(17,16,1,'2020-05-05 17:13:24'),
(18,17,2,'2020-05-05 17:13:24'),
(19,18,4,'2020-05-05 17:13:24'),
(20,20,8,'2020-05-05 17:13:24');

/*Table structure for table `products_orders` */

DROP TABLE IF EXISTS `products_orders`;

CREATE TABLE `products_orders` (
  `id` int(11) DEFAULT NULL,
  `productsAttributesID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `currentPrice` double DEFAULT NULL,
  KEY `productAttributesID` (`productsAttributesID`),
  CONSTRAINT `products_orders_ibfk_1` FOREIGN KEY (`productsAttributesID`) REFERENCES `products_attributes` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

/*Data for the table `products_orders` */

/*Table structure for table `products_out` */

DROP TABLE IF EXISTS `products_out`;

CREATE TABLE `products_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productAttributeID` int(11) DEFAULT NULL,
  `amountOUT` int(11) DEFAULT NULL,
  `dateOUT` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productAttributeID` (`productAttributeID`),
  CONSTRAINT `products_out_ibfk_1` FOREIGN KEY (`productAttributeID`) REFERENCES `products_attributes` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

/*Data for the table `products_out` */

/*Table structure for table `shipping_addresses` */

DROP TABLE IF EXISTS `shipping_addresses`;

CREATE TABLE `shipping_addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(30) DEFAULT NULL,
  `region` varchar(30) DEFAULT NULL,
  `street` varchar(30) DEFAULT NULL,
  `building` varchar(10) DEFAULT NULL,
  `index` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=cp1251;

/*Data for the table `shipping_addresses` */

insert  into `shipping_addresses`(`id`,`city`,`region`,`street`,`building`,`index`) values 
(1,'Кривой Рог','Днепропетровская область','Кармелюка','19','50048'),
(2,'Тернополь','Тернопольская область','Будного','34','60071'),
(3,'Ивано-Франковск','Ивано-Франковская область','Грушевського','22','80026'),
(4,'Винница','Винницкая область','Юности','59','20055');

/*Table structure for table `shipping_methods` */

DROP TABLE IF EXISTS `shipping_methods`;

CREATE TABLE `shipping_methods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=cp1251;

/*Data for the table `shipping_methods` */

insert  into `shipping_methods`(`id`,`title`,`phone`) values 
(1,'Новая почта','0800500609'),
(2,'Укр почта','0800300545'),
(3,'Meest Express','0800501651'),
(4,'Justin','0800301661'),
(5,'Самовывоз','-');

/*Table structure for table `track_orders` */

DROP TABLE IF EXISTS `track_orders`;

CREATE TABLE `track_orders` (
  `activeOrderID` int(11) NOT NULL,
  `orderStatus` varchar(30) DEFAULT NULL,
  `dateDelivered` datetime DEFAULT NULL,
  PRIMARY KEY (`activeOrderID`),
  CONSTRAINT `track_orders_ibfk_1` FOREIGN KEY (`activeOrderID`) REFERENCES `active_orders` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

/*Data for the table `track_orders` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `mailingEnabled` tinyint(1) DEFAULT 0,
  `contactNumber` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=cp1251;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`password`,`mailingEnabled`,`contactNumber`) values 
(1,'tvoyamamka@gmail.com','123456',0,'+380395556741'),
(2,'tvoibatya@gmail.com','654321',0,'+380109455546'),
(3,'liciy_ne_prigovor@yahoo.com','pythonlove',0,'+380395556410'),
(4,'nokoronaviruspls@gmail.com','sqltop',0,'+380400655592');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
