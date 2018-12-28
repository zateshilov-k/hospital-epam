CREATE TABLE IF NOT EXISTS `Med_personal` (
  `personalId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`personalId`))
;


CREATE TABLE IF NOT EXISTS `Patient` (
  `patientId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `isDischarged` TINYINT(1) NOT NULL,
  PRIMARY KEY (`patientId`))
;


CREATE TABLE IF NOT EXISTS `Diagnosis` (
  `diagnosisId` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1024) NULL,
  `personalId` INT NOT NULL,
  `patientId` INT NOT NULL,
  `time` DATETIME NULL,
  `isHealthy` TINYINT(1) NULL,
  PRIMARY KEY (`diagnosisId`),
  CONSTRAINT `fk_Diagnosis_Personal`
    FOREIGN KEY (`personalId`)
    REFERENCES `Med_personal` (`personalId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Diagnosis_Patient`
    FOREIGN KEY (`patientId`)
    REFERENCES `Patient` (`patientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE TABLE IF NOT EXISTS `Prescription` (
  `prescriptionId` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `patientId` INT NOT NULL,
  `time` DATETIME NULL,
  `diagnosisId` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `isDone` TINYINT(1) NULL,
  PRIMARY KEY (`prescriptionId`),
  CONSTRAINT `fk_Prescription_Patient1`
    FOREIGN KEY (`patientId`)
    REFERENCES `Patient` (`patientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prescription_Diagnosis1`
    FOREIGN KEY (`diagnosisId`)
    REFERENCES `Diagnosis` (`diagnosisId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


CREATE TABLE IF NOT EXISTS `Med_personal_Prescription` (
  `personalPrescriptionId` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(255) NULL,
  `personalId` INT NOT NULL,
  `prescriptionId` INT NOT NULL,
  PRIMARY KEY (`personalPrescriptionId`),
  CONSTRAINT `fk_Doctor_Prescription_Doctor1`
    FOREIGN KEY (`personalId`)
    REFERENCES `Med_personal` (`personalId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Doctor_Prescription_Prescription1`
    FOREIGN KEY (`prescriptionId`)
    REFERENCES `Prescription` (`prescriptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;