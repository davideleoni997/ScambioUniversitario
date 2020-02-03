-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema scambio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema scambio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `scambio` DEFAULT CHARACTER SET utf8 ;
USE `scambio` ;

-- -----------------------------------------------------
-- Table `scambio`.`admins`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE IF NOT EXISTS `scambio`.`admins` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(20) NULL DEFAULT NULL,
  `pass` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scambio`.`utenti`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE IF NOT EXISTS `scambio`.`utenti` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `nome` CHAR(20) NULL DEFAULT NULL,
  `username` CHAR(20) NOT NULL,
  `password` CHAR(20) NULL DEFAULT NULL,
  `cognome` CHAR(20) NULL DEFAULT NULL,
  `company` TINYINT(1) NULL DEFAULT NULL,
  `logo` LONGBLOB NULL DEFAULT NULL,
  `matricola` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scambio`.`insertions`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE IF NOT EXISTS `scambio`.`insertions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` CHAR(20) NULL DEFAULT NULL,
  `descr` CHAR(200) NULL DEFAULT NULL,
  `data` DATETIME NULL DEFAULT NULL,
  `price` INT(11) NULL DEFAULT NULL,
  `image1` LONGBLOB NULL DEFAULT NULL,
  `image2` LONGBLOB NULL DEFAULT NULL,
  `image3` LONGBLOB NULL DEFAULT NULL,
  `seller` INT(11) NOT NULL,
  `sold` TINYINT(1) NULL DEFAULT 0,
  `university` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `subject` VARCHAR(45) NULL DEFAULT NULL,
  `book` TINYINT(4) NULL DEFAULT NULL,
  `notes` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_insertions_utenti` (`seller` ASC),
  CONSTRAINT `fk_insertions_utenti`
    FOREIGN KEY (`seller`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scambio`.`messages`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE IF NOT EXISTS `scambio`.`messages` (
  `sender` INT(11) NOT NULL,
  `to` INT(11) NOT NULL,
  `desc` VARCHAR(45) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `da` (`sender` ASC),
  INDEX `a` (`to` ASC),
  CONSTRAINT `a`
    FOREIGN KEY (`to`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `da`
    FOREIGN KEY (`sender`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scambio`.`orders`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE IF NOT EXISTS `scambio`.`orders` (
  `idOrder` INT(11) NOT NULL AUTO_INCREMENT,
  `buyer` INT(10) NOT NULL,
  `seller` INT(10) NOT NULL,
  `data` DATE NOT NULL,
  `oggetto` VARCHAR(45) NULL DEFAULT NULL,
  `prezzo` INT(11) NULL DEFAULT NULL,
  `pagato` TINYINT(4) NULL DEFAULT 0,
  PRIMARY KEY (`idOrder`),
  INDEX `compratore` (`buyer` ASC),
  INDEX `venditore` (`seller` ASC),
  CONSTRAINT `compratore`
    FOREIGN KEY (`buyer`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `venditore`
    FOREIGN KEY (`seller`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scambio`.`reports`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE IF NOT EXISTS `scambio`.`reports` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `insId` INT(11) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `reportFrom` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user` (`reportFrom` ASC),
  INDEX `insertion` (`insId` ASC),
  CONSTRAINT `insertion`
    FOREIGN KEY (`insId`)
    REFERENCES `scambio`.`insertions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user`
    FOREIGN KEY (`reportFrom`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

USE `scambio` ;

-- -----------------------------------------------------
-- procedure compra
-- -----------------------------------------------------

DELIMITER $$
USE `scambio`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `compra`(IN buyer INT,IN seller INT,IN data DATETIME,IN inserzione INT,OUT res INT)
BEGIN
SET @res =0;
SET @sold =0;
START TRANSACTION;

Select sold INTO @sold FROM insertions where id = inserzione;
IF (@sold =1) THEN
COMMIT;
SIGNAL SQLSTATE '70004' SET MYSQL_ERRNO = 30001, message_text = 'Comprato\n'; 
END IF;

Update insertions set sold = 1 where id = inserzione;

COMMIT;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
