SET GLOBAL sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
SET SESSION sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS logs;
CREATE DATABASE logs;
use logs;

-- Table structure `log`

DROP TABLE IF EXISTS `log`;

CREATE TABLE `new_schema`.`log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `ip` VARCHAR(255) NOT NULL,
  `method` VARCHAR(255) NULL,
  `response` VARCHAR(255) NULL,
  `user_agent` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);



-- Table structure for table `comment_log`

DROP TABLE IF EXISTS `comment_log`;

*CREATE TABLE `logs`.`comment_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

