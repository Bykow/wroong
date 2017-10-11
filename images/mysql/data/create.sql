CREATE SCHEMA `wroong`;

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'adminpw';
GRANT SELECT, INSERT, UPDATE, DELETE ON `wroong`.* TO 'admin'@'%';

USE `wroong`;

/* Database table creation */
CREATE TABLE IF NOT EXISTS `tweets` (
	id INT(4) NOT NULL AUTO_INCREMENT,
	content TEXT NOT NULL,
    created_on DATE NOT NULL,
	PRIMARY KEY (id)
);
