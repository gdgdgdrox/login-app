CREATE DATABASE dxc_login_assignment;
USE dxc_login_assignment;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  `full_name` varchar(128) NOT NULL,
  PRIMARY KEY (`username`)
);

INSERT INTO `users`
(username, password, enabled, full_name)
VALUES 
("alex","{bcrypt}$2a$12$SNeLXDSFhBB5coHbStqA9.heddb.NXPTA6UO7gNFFHn/dFKl8PpKC",true,"Alex Tan Wei Ming"),
("mary", "{bcrypt}$2a$12$a7lAMduDU6FIbHJToDza5uGioC2yqX/zhO.949K4mkXtf0RlbBJjW",true,"Mary Lee Wei Ling" )
;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);

INSERT INTO `authorities`
(username, authority)
VALUES
("alex","ROLE_USER"),
("mary","ROLE_USER"),
("mary","ROLE_MANAGER")
;
