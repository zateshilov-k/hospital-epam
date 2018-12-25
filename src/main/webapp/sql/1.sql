CREATE TABLE IF NOT EXISTS `Med_personal` (
  `idMed_personal` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `creds` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMed_personal`))
;