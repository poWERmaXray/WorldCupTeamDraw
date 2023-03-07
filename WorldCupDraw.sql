/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.28-log : Database - football_team
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`football_team` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_520_ci */;

USE `football_team`;

/*Table structure for table `teams` */

DROP TABLE IF EXISTS `teams`;

CREATE TABLE `teams` (
  `country_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country_name` varchar(40) COLLATE utf8_unicode_520_ci NOT NULL,
  `country_continent` varchar(40) COLLATE utf8_unicode_520_ci NOT NULL,
  `country_rank` int(11) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

/*Data for the table `teams` */

insert  into `teams`(`country_id`,`country_name`,`country_continent`,`country_rank`) values 
(1,'Saudi Arabia','Asia',4),
(2,'Korea','Asia',4),
(3,'Panama','NorthAmerica',4),
(4,'Morocco','Africa',4),
(5,'Japan','Asia',4),
(6,'Australia','Oceania',4),
(7,'Nigeria','Africa',4),
(8,'Serbia','Europe',4),
(9,'Iran','Asia',3),
(10,'Senegal','Africa',3),
(11,'Egypt','Africa',3),
(12,'Tunisia','Africa',3),
(13,'Sweden','Europe',3),
(14,'Costa Rica','NorthAmerica',3),
(15,'Iceland','Europe',3),
(16,'Denmark','Europe',3),
(17,'Croatia','Europe',2),
(18,'Uruguay','SouthAmerica',2),
(19,'Mexico','NorthAmerica',2),
(20,'Columbia','SouthAmerica',2),
(21,'England','Europe',2),
(22,'Switzerland','Europe',2),
(23,'Peru','SouthAmerica',2),
(24,'Spain','Europe',2),
(25,'France','Europe',1),
(26,'Poland','Europe',1),
(27,'Belgium','Europe',1),
(28,'Argentina','SouthAmerica',1),
(29,'Portugal','Europe',1),
(30,'Brazil','SouthAmerica',1),
(31,'Germany','Europe',1),
(32,'Russia','Europe',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
