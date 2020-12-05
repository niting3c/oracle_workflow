CREATE DATABASE IF NOT EXISTS `workflow`;
USE `workflow`;

-- Create syntax for TABLE 'party'
CREATE TABLE `party` (
  `userName` varchar(255) NOT NULL DEFAULT '',
  `companyName` varchar(255) NOT NULL DEFAULT '',
  `companyId` int(11) NOT NULL,
  `address` text NOT NULL,
  `meta` tinytext,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` int(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`userName`,`companyName`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'party_status'
CREATE TABLE `party_status` (
  `userName` varchar(255) NOT NULL DEFAULT '',
  `companyName` varchar(255) NOT NULL DEFAULT '',
  `companyId` int(11) NOT NULL,
  `address` text NOT NULL,
  `meta` tinytext,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `authorizedApprovers` tinytext NOT NULL,
  `status` varchar(255) DEFAULT '',
  `approver` varchar(255) DEFAULT '',
  `updatedBy` varchar(255) DEFAULT 'SYSTEM',
  `createdBy` varchar(255) DEFAULT 'SYSTEM',
  PRIMARY KEY (`userName`,`companyName`),
  KEY `id` (`id`),
  CONSTRAINT `fk_party_status` FOREIGN KEY (`userName`, `companyName`) REFERENCES `party` (`userName`, `companyName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;