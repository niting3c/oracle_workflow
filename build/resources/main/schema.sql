CREATE DATABASE IF NOT EXISTS `workflow`;
USE `workflow`;

-- Create syntax for TABLE 'party'
CREATE TABLE IF NOT EXISTS `team` (

  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`name`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



--Create Table for developer with foreign key
CREATE TABLE `developer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `phone_number` varchar(20) NOT NULL DEFAULT '',
  `team_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`team_id`,`phone_number`),
  KEY `id` (`id`),
  CONSTRAINT `fk_dev` FOREIGN KEY (`team_id`) REFERENCES `team` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
