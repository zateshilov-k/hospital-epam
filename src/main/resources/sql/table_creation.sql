CREATE TABLE IF NOT EXISTS `medical_personal` (
  `personal_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`personal_id`))
;


CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `is_discharged` TINYINT(1) NOT NULL,
  PRIMARY KEY (`patient_id`))
;


CREATE TABLE IF NOT EXISTS `diagnosis` (
  `diagnosis_id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1024) NULL,
  `personal_id` INT NOT NULL,
  `patient_id` INT NOT NULL,
  `time` DATETIME NULL,
  `is_healthy` TINYINT(1) NULL,
  PRIMARY KEY (`diagnosis_id`),
  CONSTRAINT `diagnosis_personal_constraint`
    FOREIGN KEY (`personal_id`)
    REFERENCES `medical_personal` (`personal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `diagnosis_patient_constraint`
    FOREIGN KEY (`patient_id`)
    REFERENCES `patient` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE TABLE IF NOT EXISTS `prescription` (
  `prescription_id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `patient_id` INT NOT NULL,
  `time` DATETIME NULL,
  `diagnosis_id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `is_done` TINYINT(1) NULL,
  PRIMARY KEY (`prescription_id`),
  CONSTRAINT `prescription_patient_constraint`
    FOREIGN KEY (`patient_id`)
    REFERENCES `patient` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `prescription_diagnosis_constraint`
    FOREIGN KEY (`diagnosis_id`)
    REFERENCES `diagnosis` (`diagnosis_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


CREATE TABLE IF NOT EXISTS `medical_personal_prescription` (
  `personal_prescription_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) NULL,
  `personal_id` INT NOT NULL,
  `prescription_id` INT NOT NULL,
  PRIMARY KEY (`personal_prescription_id`),
  CONSTRAINT `medical_personal_constraint`
    FOREIGN KEY (`personal_id`)
    REFERENCES `medical_personal` (`personal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `prescription_personal_constraint`
    FOREIGN KEY (`prescription_id`)
    REFERENCES `prescription` (`prescription_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;