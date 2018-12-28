CREATE TABLE IF NOT EXISTS `Med_personal` (
  `idMed_personal` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idMed_personal`))
;


CREATE TABLE IF NOT EXISTS `Patient` (
  `idPatient` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `isDischarged` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idPatient`))
;


CREATE TABLE IF NOT EXISTS `Diagnosys` (
  `idDiagnosys` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1024) NULL,
  `Doctor_idDoctor` INT NOT NULL,
  `Patient_idPatient` INT NOT NULL,
  `time` DATETIME NULL,
  `isHealthy` TINYINT(1) NULL,
  PRIMARY KEY (`idDiagnosys`),
  CONSTRAINT `fk_Diagnosys_Doctor`
    FOREIGN KEY (`Doctor_idDoctor`)
    REFERENCES `Med_personal` (`idMed_personal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Diagnosys_Patient1`
    FOREIGN KEY (`Patient_idPatient`)
    REFERENCES `Patient` (`idPatient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE TABLE IF NOT EXISTS `Prescription` (
  `idPrescription` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(255) NULL,
  `Patient_idPatient` INT NOT NULL,
  `time` DATETIME NULL,
  `Diagnosys_idDiagnosys` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `isDone` TINYINT(1) NULL,
  PRIMARY KEY (`idPrescription`),
  CONSTRAINT `fk_Prescription_Patient1`
    FOREIGN KEY (`Patient_idPatient`)
    REFERENCES `Patient` (`idPatient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prescription_Diagnosys1`
    FOREIGN KEY (`Diagnosys_idDiagnosys`)
    REFERENCES `Diagnosys` (`idDiagnosys`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE TABLE IF NOT EXISTS `Med_personal_Prescription` (
  `idMed_personal_Prescription` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(255) NULL,
  `Doctor_idDoctor` INT NOT NULL,
  `Prescription_idPrescription` INT NOT NULL,
  PRIMARY KEY (`idMed_personal_Prescription`),
  CONSTRAINT `fk_Doctor_Prescription_Doctor1`
    FOREIGN KEY (`Doctor_idDoctor`)
    REFERENCES `Med_personal` (`idMed_personal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Doctor_Prescription_Prescription1`
    FOREIGN KEY (`Prescription_idPrescription`)
    REFERENCES `Prescription` (`idPrescription`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;