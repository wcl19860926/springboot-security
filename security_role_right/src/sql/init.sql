CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `userCode` varchar(50) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `sys_role_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL,
  `roleId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`),
  KEY `idx_roleId` (`roleId`)
) ENGINE=InnoDB ;

CREATE TABLE `sys_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(30) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;
CREATE TABLE `sys_permission_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleId` int(11) unsigned NOT NULL,
  `permissionId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_role2` (`roleId`),
  KEY `idx_permission` (`permissionId`)
) ENGINE=InnoDB ;



INSERT INTO `sys_user` (`id`, `username`, `password`)
VALUES
	(1,'admin','6d789d4353c72e4f625d21c6b7ac2982'),
	(2,'user','36f1cab655c5252fc4f163a1409500b8');


INSERT INTO `sys_role` (`id`, `name` ,`code`)
VALUES
	(2,'ROLE_USER','ROLE_USER' ),
	(3,'ROLE_ADMIN','ROLE_ADMIN');

INSERT INTO `sys_role_user` (`id`, `userId`, `roleId`)
VALUES
	(6,1,3),
	(7,2,2);


INSERT INTO `sys_permission` (`id`, `code` ,`name`, `url`, `description` )
VALUES
	(1,'ROLE_HOME','首页权限','/',NULL),
	(2,'ROLE_ADMIN','管理员权限','/admin',NULL),
	(3,'ROLE_USER','用户权限','/user',NULL);

INSERT INTO `sys_permission_role` (`id`, `roleId`, `permissionId`)
VALUES
	(10,2,1),
	(11,2,3),
	(12,3,1),
	(13,3,2),
	(15,2,2);











