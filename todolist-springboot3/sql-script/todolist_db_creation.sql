DROP SCHEMA IF EXISTS `TodoList` ;
CREATE SCHEMA IF NOT EXISTS `TodoList` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `TodoList` ;

-- -----------------------------------------------------
-- Table `TodoList`.`todo_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TodoList`.`todo_items` ;

CREATE  TABLE IF NOT EXISTS `TodoList`.`todo_items` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `task` VARCHAR(100) NOT NULL ,
  `is_completed` TINYINT(1) NOT NULL, 
  PRIMARY KEY (`id`))  
ENGINE = InnoDB;