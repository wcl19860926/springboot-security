DROP TABLE IF EXISTS `Sys_permission`;

CREATE TABLE `Sys_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `Sys_permission` (`id`, `name`, `description`, `url`, `pid`)
VALUES
	(1,'ROLE_HOME','index','/',NULL),
	(2,'ROLE_ADMIN','admin','/admin',NULL),
	(3,'ROLE_USER','user','/user',NULL);

/*!40000 ALTER TABLE `Sys_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Sys_permission_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Sys_permission_role`;

CREATE TABLE `Sys_permission_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) unsigned NOT NULL,
  `permission_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role2` (`role_id`),
  KEY `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `Sys_permission_role` (`id`, `role_id`, `permission_id`)
VALUES
	(10,2,1),
	(11,2,3),
	(12,3,1),
	(13,3,2),
	(15,2,2);





CREATE TABLE `Sys_Role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `Sys_Role` (`id`, `name`)
VALUES
	(2,'ROLE_USER'),
	(3,'ROLE_ADMIN');


CREATE TABLE `Sys_Role_User` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Sys_User_id` int(11) unsigned NOT NULL,
  `Sys_Role_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`Sys_User_id`),
  KEY `role` (`Sys_Role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `Sys_Role_User` (`id`, `Sys_User_id`, `Sys_Role_id`)
VALUES
	(6,1,3),
	(7,2,2);


CREATE TABLE `Sys_User` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `Sys_User` (`id`, `username`, `password`)
VALUES
	(1,'admin','6d789d4353c72e4f625d21c6b7ac2982'),
	(2,'user','36f1cab655c5252fc4f163a1409500b8');
