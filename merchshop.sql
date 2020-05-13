/*
SQLyog Community v12.4.3 (64 bit)
MySQL - 10.4.11-MariaDB : Database - merchshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`merchshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `merchshop`;

/*Table structure for table `active_orders` */

DROP TABLE IF EXISTS `active_orders`;

CREATE TABLE `active_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
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
  CONSTRAINT `active_orders_ibfk_3` FOREIGN KEY (`shippingAddressID`) REFERENCES `shipping_addresses` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `active_orders` */

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `categories` */

insert  into `categories`(`id`,`title`,`description`) values 
(1,'Одежда','Футболки,худи,куртки'),
(2,'Игрушки','конструктор,мягкие игрушки,обычные игрушки'),
(3,'Фигурки','статуэтки,коллекционные фигурки'),
(4,'Аксессуары',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `products` */

insert  into `products`(`id`,`title`,`categoryID`,`description`) values 
(1,'SPIDER-MAN TALKING ACTION FIGURE',2,'Spider-Man has plenty of fighting words to battle his enemies as this talking action figure. And if that doesn\'t work, then the friendly neighborhood super hero can use his two web slingers!'),
(2,'DEADPOOL T-SHIRT FOR ADULTDS',1,'The only thing better than this all-cotton Deadpool tee is a hot taco. What\'s not to love?'),
(3,'IRON MAN \'\' I LOVE YOU 3000\'\' T-SHIRT FOR MEN',1,'Beneath his armor, Tony Stark possesses a big heart, one that was touched when his daughter Morgan professed \'\'I love you 3000\'\' in Marvel\'s Avengers: Endgame. Iron Man\'s face appears within the text of the memorable quote'),
(4,'MARVEL STUDIOS 10TH ANNIVERSARY T-SHIRT FOR ADULTS',1,'If you\'re \'\'More than a fan\'\' of Marvel Studios (and who isn\'t?), you\'ll find this double-sided, all-cotton anniversary tee an absolute must. Help celebrate ten years of Marvel-ous movies.'),
(5,'THOR SOUND EFFECTS HAMMER',2,'Make their super hero action come alive with this Thor Hammer! Press the button for phrases, sounds, and vibration effects. Spin Thor\'s mighty Mjolnir by the loop at the bottom of the handle to hear spinning sound effects'),
(6,'LIQUID X MARVEL LOGO TEE',1,'Rep the LIQUID x MARVEL collaboration with this clean logo tee featuring the official horse head red brick lock-up: the insignia of LIQUID x MARVEL. Crisp fusion printed design keeps the design clean, crack-free and durable all on a weighted comfortable c'),
(7,'TEAM LIQUID GEOS TEE',1,'Our 2020 take on a classic Liquid look. Add this quality tee to your collection today.'),
(8,'ФИГУРКА FUNKO POP! EREDIN - THE WITCHER',3,'Виниловая игровая фигурка EREDIN станет замечательным подарком для любителей компьютерной игры The Witcher 3 Wild Hunt'),
(9,'MARVEL COMICS DUFFLE BAG',4,'Our mighty Marvel Comics duffle bag packs a punch at the gym or anywhere your adventures lead you. Reinforced straps and colorful comic book cover lining make it a super carry-all for roving avengers'),
(10,'ФИГУРКА FUNKO POP! GERALT - THE WITCHER',3,'Виниловая игровая фигурка GERALT OF RIVIA станет замечательным подарком для любителей компьютерной игры The Witcher 3 Wild Hunt'),
(11,'ФИГУРКА FUNKO POP! GROOT - GUARDIANS OF THE GALAXY 2',3,'Виниловая игровая фигурка GROOT станет замечательным подарком для любителей комиксов Marvel'),
(12,'ФИГУРКА FUNKO POP! HANZO - OVERWATCH SERIES 4',3,'Виниловая игровая фигурка HANZO станет замечательным подарком для любителей игры Overwatch'),
(13,'ФИГУРКА FUNKO POP! HARLEY QUINN - DC',3,'Виниловая игровая фигурка HARLEY QUINN станет замечательным подарком для любителей комиксов DC'),
(14,'КЕПКА ЭЛИТНАЯ СТРАЖА',4,'С такой кепкой ты всегда сможешь пояснить за шмот'),
(15,'РЮКЗАК ДОТА 2',4,'Покажи всем окружающим, что ты никогда не проигрываешь, а иногда и выигрываешь центральную линию'),
(16,'РЮКЗАК OVERWATCH',4,'Покажи всем окружающим, что ты фанат игры Overwatch от Blizzard и по совместительству говноед'),
(17,'PIDERJET VS. VENOM MECH PLAYSET BY LEGO',2,'With this Spiderjet vs. Venom Mech Playset by LEGO, Spider-Man is in danger! Venom, the evil alien, is at the controls of a deadly, gigantic, four-armed mech – and he\'s caught Spider-Man in its mechanical claws!'),
(18,'PIDER-MAN 6V TODDLER QUAD RIDE-ON TOY',2,'Your little webhead will enjoy hours of heroic fun on this 6-Volt Spider-Man Toddler Quad toy. Molded plastic webbing adds powerful style to the front of this rechargeable ride with easy push-button drive system and a maximum speed of 2 MPH'),
(19,'ФИГУРКА FUNKO POP! HAGRID WITH CAKE - HARRY POTTER',3,'Виниловая игровая фигурка Hagrid станет замечательным подарком для любителей серии HARRY POTTER'),
(20,'ФИГУРКА FUNKO POP! GRUNKLE STAN - GRAVITY FALLS',3,'Виниловая игровая фигурка GRUNKLE STAN станет замечательным подарком для любителей серии GRAVITY FALLS'),
(21,'ФИГУРКА FUNKO POP! GREEN GOBLIN - SPIDER-MAN: INTO THE SPIDER-VERSE - MARVEL',3,'Виниловая игровая фигурка GREEN GOBLIN станет замечательным подарком для любителей комиксов MARVEL'),
(22,'ФИГУРКА FUNKO POP! GLADIATOR HULK - THOR RAGNAROK',3,'Виниловая игровая фигурка GLADIATOR HULK станет замечательным подарком для любителей комиксов MARVEL');

/*Table structure for table `products_attributes` */

DROP TABLE IF EXISTS `products_attributes`;

CREATE TABLE `products_attributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productID` int(11) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `price` double NOT NULL,
  `photo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `productID` (`productID`),
  CONSTRAINT `products_attributes_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `products_attributes` */

insert  into `products_attributes`(`id`,`productID`,`color`,`size`,`price`,`photo`) values 
(1,1,NULL,NULL,900,'images/1_talkingspiderman.jpg'),
(2,2,'red','S',850,'images/2_tshirt_deadpool.jpg'),
(3,2,'red','XXL',850,'images/2_tshirt_deadpool.jpg'),
(4,2,'red','3XL',880,'images/2_tshirt_deadpool.jpg'),
(5,3,'black','M',900,'images/3_tshirt_loveyou3000.jpg'),
(6,3,'black','L',900,'images/3_tshirt_loveyou3000.jpg'),
(7,3,'black','XL',900,'images/3_tshirt_loveyou3000.jpg'),
(8,4,'black','M',1200,'images/4_tshirt_fan.jpg'),
(9,4,'black','L',1200,'images/4_tshirt_fan.jpg'),
(10,4,'black','XXL',1250,'images/4_tshirt_fan.jpg'),
(11,5,NULL,'-',1000,'images/5_thorhammer.jpg'),
(15,7,'white','L',1500,'images/7_tshirt_tlwhite.jpg'),
(16,7,'white','XL',1500,'images/7_tshirt_tlwhite.jpg'),
(17,7,'blue','L',1400,'images/7_tshirt_tlblue.jpg'),
(18,7,'blue','M',1400,'images/7_tshirt_tlblue.jpg'),
(19,8,NULL,NULL,400,'images/8_eredin.jpg'),
(20,9,NULL,NULL,2000,'images/9_marvelbag.jpg'),
(21,10,NULL,NULL,450,'images/10_witcher.jpg'),
(22,11,NULL,NULL,425,'images/11_groot.jpg'),
(23,12,NULL,NULL,450,'images/12_hanzo.jpg'),
(24,13,NULL,NULL,430,'images/13_harleyquinn.jpg'),
(25,14,NULL,NULL,650,'images/14_eliteguard.jpg'),
(26,15,NULL,NULL,499,'images/15_dota2bag.jpg'),
(27,16,NULL,NULL,1000,'images/16_owbag.jpg'),
(28,17,NULL,NULL,1000,'images/17_spidermanlego.jpg'),
(29,18,NULL,NULL,5000,'images/18_spiderbike.jpg'),
(30,19,NULL,NULL,749,'images/19_hagrid.jpg'),
(31,20,NULL,NULL,495,'images/20_grunklestan.jpg'),
(32,21,NULL,NULL,495,'images/21_greengoblin.jpg'),
(33,22,NULL,NULL,549,'images/22_hulk.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `products_in` */

insert  into `products_in`(`id`,`productAttributeID`,`amountIN`,`dateIN`) values 
(1,1,10,'2020-05-05 17:12:46'),
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
(15,19,7,'2020-05-05 17:13:24'),
(16,15,3,'2020-05-05 17:13:24'),
(17,16,1,'2020-05-05 17:13:24'),
(18,17,2,'2020-05-05 17:13:24'),
(19,18,4,'2020-05-05 17:13:24'),
(20,20,8,'2020-05-05 17:13:24'),
(21,20,2,'2020-05-08 15:18:30');

/*Table structure for table `products_orders` */

DROP TABLE IF EXISTS `products_orders`;

CREATE TABLE `products_orders` (
  `activeOrderID` int(11) DEFAULT NULL,
  `productsAttributesID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `currentPrice` double DEFAULT NULL,
  KEY `productAttributesID` (`productsAttributesID`),
  KEY `activeOrderID` (`activeOrderID`),
  CONSTRAINT `products_orders_ibfk_1` FOREIGN KEY (`productsAttributesID`) REFERENCES `products_attributes` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `products_orders_ibfk_2` FOREIGN KEY (`activeOrderID`) REFERENCES `active_orders` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `products_out` */

insert  into `products_out`(`id`,`productAttributeID`,`amountOUT`,`dateOUT`) values 
(1,20,2,'2020-05-08 15:21:16'),
(2,20,1,'2020-05-08 15:31:14');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `track_orders` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `mailingEnabled` tinyint(1) DEFAULT 0,
  `contactNumber` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`password`,`mailingEnabled`,`contactNumber`) values 
(1,'tvoyamamka@gmail.com','123456',0,'+380395556741'),
(2,'tvoibatya@gmail.com','654321',0,'+380109455546'),
(3,'liciy_ne_prigovor@yahoo.com','pythonlove',0,'+380395556410'),
(4,'nokoronaviruspls@gmail.com','sqltop',0,'+380400655592'),
(6,'takemylove314@gmail.com','test',0,'+380400655592'),
(7,'yvaweniep4elkam@gmail.com','test',0,'+380400655592');

/*Table structure for table `products_in_stock` */

DROP TABLE IF EXISTS `products_in_stock`;

/*!50001 DROP VIEW IF EXISTS `products_in_stock` */;
/*!50001 DROP TABLE IF EXISTS `products_in_stock` */;

/*!50001 CREATE TABLE  `products_in_stock`(
 `id` int(11) ,
 `in_stock` decimal(32,0) 
)*/;

/*View structure for view products_in_stock */

/*!50001 DROP TABLE IF EXISTS `products_in_stock` */;
/*!50001 DROP VIEW IF EXISTS `products_in_stock` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `products_in_stock` AS (select `products_attributes`.`id` AS `id`,sum(`products_in`.`amountIN`) AS `in_stock` from (`products_attributes` join `products_in` on(`products_attributes`.`id` = `products_in`.`productAttributeID`)) group by `products_attributes`.`id`) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
