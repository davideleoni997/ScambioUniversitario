-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `scambio` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema scambio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema scambio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `scambio` DEFAULT CHARACTER SET utf8 ;
USE `scambio` ;

-- -----------------------------------------------------
-- Table `scambio`.`utenti`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE `scambio`.`utenti` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `nome` CHAR(20) NULL DEFAULT NULL,
  `username` CHAR(20) NOT NULL,
  `password` CHAR(20) NULL DEFAULT NULL,
  `cognome` CHAR(20) NULL DEFAULT NULL,
  `company` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`orders`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE `scambio`.`orders` (
  `idOrder` INT NOT NULL PRIMARY KEY,
  `buyer` INT(10) NOT NULL,
  `seller` INT(10) NOT NULL,
  `data` DATE NOT NULL,
  `oggetto` VARCHAR(45) NULL,
  `prezzo` INT NULL,
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
ENGINE = InnoDB;

USE `scambio` ;

-- -----------------------------------------------------
-- Table `scambio`.`insertions`
-- -----------------------------------------------------
CREATE OR REPLACE TABLE `scambio`.`insertions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` CHAR(20) NULL DEFAULT NULL,
  `descr` CHAR(200) NULL DEFAULT NULL,
  `data` DATETIME NULL DEFAULT NULL,
  `price` INT(11) NULL DEFAULT NULL,
  `image1` BLOB NULL DEFAULT NULL,
  `image2` BLOB NULL DEFAULT NULL,
  `image3` BLOB NULL DEFAULT NULL,
  `seller` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_insertions_utenti`
    FOREIGN KEY (`seller`)
    REFERENCES `scambio`.`utenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;