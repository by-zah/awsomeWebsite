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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`merchshop` /*!40100 DEFAULT CHARACTER SET cp1251 */;

USE `merchshop`;

/*Table structure for table `active_orders` */

DROP TABLE IF EXISTS `active_orders`;

CREATE TABLE `active_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `productOrder` int(11) DEFAULT NULL,
  `shippingMethodID` int(11) DEFAULT NULL,
  `shippingAddressID` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `currentStatus` varchar(50) DEFAULT NULL,
  `datePlaced` date DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`userID`),
  KEY `shippingMethod` (`shippingMethodID`),
  KEY `shippingAddressID` (`shippingAddressID`),
  CONSTRAINT `shippingAddressID` FOREIGN KEY (`shippingAddressID`) REFERENCES `shipping_addresses` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `shippingMethod` FOREIGN KEY (`shippingMethodID`) REFERENCES `shipping_methods` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `user` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

/*Data for the table `active_orders` */

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=cp1251;

/*Data for the table `categories` */

insert  into `categories`(`id`,`title`,`description`) values 
(1,'Футболка','один из самых популярных элементов одежды'),
(2,'Кружка','предмет, который используется в качестве хранилища для жидкости перед ее употребленим'),
(3,'Брелки','аксессуар, украшение, выполненное в виде подвески на цепочке, браслете, кольце для ключей и другое. Обычно используется в декоративных и рекламных целях.'),
(4,'Игрушки','воссоздавая реальные и воображаемые предметы, образы, игрушка служит целям умственного, нравственного, эстетического и физического воспитания ребёнка, помогая ему познавать окружающий мир, приучая к целенаправленной, осмысленной деятельности и способствуя'),
(5,'Статуэтки','небольшая скульптура, выполненная из дерева, кости, глины, камня, металла и других материалов, изображающая антропоморфные образы, фигуры животных, неодушевлённые и абстрактные предметы.');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL,
  `vendorID` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category` (`categoryID`),
  KEY `vendor` (`vendorID`),
  CONSTRAINT `category` FOREIGN KEY (`categoryID`) REFERENCES `categories` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `vendor` FOREIGN KEY (`vendorID`) REFERENCES `vendors` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=cp1251;

/*Data for the table `product` */

insert  into `product`(`id`,`title`,`categoryID`,`vendorID`,`description`) values 
(1,'Футболка \"MARVEL\"',1,1,'ну футболка, у всех она есть, заполните сами если что. Я хз'),
(2,'Фигурка \"Кайло Рен\"',5,2,'лера бы купила'),
(3,'Брелок на ключи \"Ведьмачий меч\"',3,4,'махакамская сталь, определенно');

/*Table structure for table `products_attributes` */

DROP TABLE IF EXISTS `products_attributes`;

CREATE TABLE `products_attributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productID` int(11) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `size` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `amountAvailable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product` (`productID`),
  CONSTRAINT `product` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=cp1251;

/*Data for the table `products_attributes` */

insert  into `products_attributes`(`id`,`productID`,`color`,`size`,`price`,`photo`,`amountAvailable`) values 
(1,1,'Красный','L',1000,NULL,5),
(2,1,'Белый','XL',1100,NULL,3),
(3,2,'-','-',500,NULL,1),
(4,3,'-','-',200,NULL,2),
(5,1,'Желтый','M',1050,NULL,10);

/*Table structure for table `products_orders` */

DROP TABLE IF EXISTS `products_orders`;

CREATE TABLE `products_orders` (
  `productOrderID` int(11) DEFAULT NULL,
  `productsAttributesID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `currentPrice` double DEFAULT NULL,
  KEY `productAttribute` (`productsAttributesID`),
  CONSTRAINT `productAttribute` FOREIGN KEY (`productsAttributesID`) REFERENCES `products_attributes` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

/*Data for the table `products_orders` */

/*Table structure for table `shipping_addresses` */

DROP TABLE IF EXISTS `shipping_addresses`;

CREATE TABLE `shipping_addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(30) DEFAULT NULL,
  `region` varchar(30) DEFAULT NULL,
  `street` varchar(30) DEFAULT NULL,
  `building` varchar(30) DEFAULT NULL,
  `index` varchar(30) DEFAULT NULL,
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
  `title` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=cp1251;

/*Data for the table `shipping_methods` */

insert  into `shipping_methods`(`id`,`title`,`phone`) values 
(1,'Новая почта','0800500609'),
(2,'Укр почта','0800300545'),
(3,'Meest Express','0800501651'),
(4,'Justin','0800301661');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `paymentMethod` varchar(50) DEFAULT NULL,
  `contactNumber` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=cp1251;

/*Data for the table `users` */

insert  into `users`(`id`,`login`,`password`,`email`,`paymentMethod`,`contactNumber`) values 
(1,'tvoyamamka','123456','tvoyamamka@gmail.com','visa 4601','+380395556741'),
(2,'tvoibatya','654321','tvoibatya@gmail.com','mastercard 3228','+380109455546'),
(3,'dimarostov','pythonlove','liciy_ne_prigovor@yahoo.com','-','+380395556410'),
(4,'lazurikDB','sqltop','nokoronaviruspls@gmail.com','mastercard 1337','+380400655592');

/*Table structure for table `vendors` */

DROP TABLE IF EXISTS `vendors`;

CREATE TABLE `vendors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=cp1251;

/*Data for the table `vendors` */

insert  into `vendors`(`id`,`title`,`description`) values 
(1,'Marvel','Вселенная Marvel увлекательна и противоречива. Сочетание мистики, науки и фантастических идей породило на свет расы мутантов, пришельцев, вампиров, оборотней, демонов и богов. Сегодня за их вечной схваткой с силами зла наблюдают поклонники по всему миру. '),
(2,'DC','Вселенная DC увлекательна и противоречива. Сочетание мистики, науки и фантастических идей породило на свет расы мутантов, пришельцев, вампиров, оборотней, демонов и богов. Сегодня за их вечной схваткой с силами зла наблюдают поклонники по всему миру. DC C'),
(3,'Star Wars','киноэпопея в жанре космическая опера, включающая в себя 11 художественных фильмов (9 эпизодов основной саги, 2 фильма «историй»), а также анимационные сериалы, мультфильмы, телефильмы, книги, комиксы, видеоигры, игрушки и прочие произведения, созданные в '),
(4,'Witcher','Культовая серия романов польского писателя Анджея Сапковского рассказывает об охотниках за монстрами, которые долгие годы тренируют свое тело и развивают сверхъестественные способности, чтобы в дальнейшем оттачивать свои навыки в битвах со свирепыми чудов');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
