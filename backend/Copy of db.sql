SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 ;
USE `test` ;

-- -----------------------------------------------------
-- Table `sam_solutions`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`users` (
  `users_login` VARCHAR(45) NOT NULL ,
  `users_pass` VARCHAR(40) NOT NULL ,
  `users_FN` VARCHAR(45) NULL DEFAULT NULL ,
  `users_LN` VARCHAR(45) NULL DEFAULT NULL ,
  `users_role` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`users_login`) )
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sam_solutions`.`tasks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`tasks` (
  `tasks_id_task` INT(11) NOT NULL AUTO_INCREMENT ,
  `tasks_title` VARCHAR(300) NOT NULL ,
  `tasks_text` TEXT NOT NULL ,
  `tasks_path` TEXT NOT NULL ,
  `tasks_status` VARCHAR(45) NOT NULL ,
  `tasks_end_time` DATETIME NOT NULL ,
  `tasks_closed` INT(1) NOT NULL ,
  `tasks_users_login` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`tasks_id_task`) ,
  INDEX `fk_tasks_users1` (`tasks_users_login` ASC) ,
  CONSTRAINT `fk_tasks_users1`
    FOREIGN KEY (`tasks_users_login` )
    REFERENCES `test`.`users` (`users_login` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sam_solutions`.`meetings`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`meetings` (
  `meetings_id_meeting` INT(11) NOT NULL AUTO_INCREMENT ,
  `meetings_title` VARCHAR(300) NOT NULL ,
  `meetings_description` TEXT NULL DEFAULT NULL ,
  `meetings_date` DATETIME NOT NULL ,
  `meetings_users_login` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`meetings_id_meeting`) ,
  INDEX `fk_meetings_users` (`meetings_users_login` ASC) ,
  CONSTRAINT `fk_meetings_users`
    FOREIGN KEY (`meetings_users_login` )
    REFERENCES `test`.`users` (`users_login` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sam_solutions`.`users_has_meetings`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`users_has_meetings` (
  `users_has_meetings_login` VARCHAR(45) NOT NULL ,
  `users_has_meetings_id_meeting` INT(11) NOT NULL ,
  PRIMARY KEY (`users_has_meetings_login`, `users_has_meetings_id_meeting`) ,
  INDEX `fk_users_has_meetings_meetings1` (`users_has_meetings_id_meeting` ASC) ,
  INDEX `fk_users_has_meetings_users1` (`users_has_meetings_login` ASC) ,
  CONSTRAINT `fk_users_has_meetings_users1`
    FOREIGN KEY (`users_has_meetings_login` )
    REFERENCES `test`.`users` (`users_login` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_meetings_meetings1`
    FOREIGN KEY (`users_has_meetings_id_meeting` )
    REFERENCES `test`.`meetings` (`meetings_id_meeting` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
