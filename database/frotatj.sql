# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.6.5-m8
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2012-10-30 22:13:49
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for frotatj
CREATE DATABASE IF NOT EXISTS `frotatj` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `frotatj`;


# Dumping structure for table frotatj.abastecimento
CREATE TABLE IF NOT EXISTS `abastecimento` (
  `idabastecimento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `veiculo_idveiculo` int(10) unsigned NOT NULL,
  `km_odometro` int(10) unsigned DEFAULT NULL,
  `data_2` date DEFAULT NULL,
  PRIMARY KEY (`idabastecimento`),
  KEY `abastecimento_FKIndex1` (`veiculo_idveiculo`),
  CONSTRAINT `abastecimento_ibfk_1` FOREIGN KEY (`veiculo_idveiculo`) REFERENCES `veiculo` (`idveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.fornecedor
CREATE TABLE IF NOT EXISTS `fornecedor` (
  `idfornecedor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `cnpj` varchar(200) DEFAULT NULL,
  `fone1` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fone2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idfornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.marca
CREATE TABLE IF NOT EXISTS `marca` (
  `idmarca` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.modelo
CREATE TABLE IF NOT EXISTS `modelo` (
  `idmodelo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marca_idmarca` int(10) unsigned NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idmodelo`),
  KEY `modelo_FKIndex1` (`marca_idmarca`),
  CONSTRAINT `modelo_ibfk_1` FOREIGN KEY (`marca_idmarca`) REFERENCES `marca` (`idmarca`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.motorista
CREATE TABLE IF NOT EXISTS `motorista` (
  `idmotorista` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `unidade_idunidade` int(10) unsigned NOT NULL,
  `matricula` varchar(200) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idmotorista`),
  KEY `motorista_FKIndex1` (`unidade_idunidade`),
  CONSTRAINT `motorista_ibfk_1` FOREIGN KEY (`unidade_idunidade`) REFERENCES `unidade` (`idunidade`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.servico
CREATE TABLE IF NOT EXISTS `servico` (
  `idservico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario_idusuario` int(10) unsigned NOT NULL,
  `motorista_idmotorista` int(10) unsigned NOT NULL,
  `tipo_servico_idtipo_servico` int(10) unsigned NOT NULL,
  `veiculo_idveiculo` int(10) unsigned NOT NULL,
  `fornecedor_idfornecedor` int(10) unsigned NOT NULL,
  `data_2` datetime DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `nro_orcamento` varchar(100) DEFAULT NULL,
  `nf_ticket` int(10) unsigned DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `km` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idservico`),
  KEY `servico_FKIndex1` (`fornecedor_idfornecedor`),
  KEY `servico_FKIndex2` (`veiculo_idveiculo`),
  KEY `servico_FKIndex3` (`tipo_servico_idtipo_servico`),
  KEY `servico_FKIndex4` (`motorista_idmotorista`),
  KEY `servico_FKIndex5` (`usuario_idusuario`),
  CONSTRAINT `servico_ibfk_1` FOREIGN KEY (`fornecedor_idfornecedor`) REFERENCES `fornecedor` (`idfornecedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `servico_ibfk_2` FOREIGN KEY (`veiculo_idveiculo`) REFERENCES `veiculo` (`idveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `servico_ibfk_3` FOREIGN KEY (`tipo_servico_idtipo_servico`) REFERENCES `tipo_servico` (`idtipo_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `servico_ibfk_4` FOREIGN KEY (`motorista_idmotorista`) REFERENCES `motorista` (`idmotorista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `servico_ibfk_5` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.tipo_servico
CREATE TABLE IF NOT EXISTS `tipo_servico` (
  `idtipo_servico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idtipo_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.tipo_servico_modelo
CREATE TABLE IF NOT EXISTS `tipo_servico_modelo` (
  `tipo_servico_idtipo_servico` int(10) unsigned NOT NULL,
  `modelo_idmodelo` int(10) unsigned NOT NULL,
  `km` int(10) unsigned DEFAULT NULL,
  `tempo` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`tipo_servico_idtipo_servico`,`modelo_idmodelo`),
  KEY `tipo_servico_has_modelo_FKIndex1` (`tipo_servico_idtipo_servico`),
  KEY `tipo_servico_has_modelo_FKIndex2` (`modelo_idmodelo`),
  CONSTRAINT `tipo_servico_modelo_ibfk_1` FOREIGN KEY (`tipo_servico_idtipo_servico`) REFERENCES `tipo_servico` (`idtipo_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_servico_modelo_ibfk_2` FOREIGN KEY (`modelo_idmodelo`) REFERENCES `modelo` (`idmodelo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.tipo_servico_veiculo
CREATE TABLE IF NOT EXISTS `tipo_servico_veiculo` (
  `veiculo_idveiculo` int(10) unsigned NOT NULL,
  `tipo_servico_idtipo_servico` int(10) unsigned NOT NULL,
  `situacao` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`veiculo_idveiculo`,`tipo_servico_idtipo_servico`),
  KEY `servico_has_veiculo_FKIndex1` (`tipo_servico_idtipo_servico`),
  KEY `servico_has_veiculo_FKIndex2` (`veiculo_idveiculo`),
  CONSTRAINT `tipo_servico_veiculo_ibfk_1` FOREIGN KEY (`tipo_servico_idtipo_servico`) REFERENCES `tipo_servico` (`idtipo_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_servico_veiculo_ibfk_2` FOREIGN KEY (`veiculo_idveiculo`) REFERENCES `veiculo` (`idveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.unidade
CREATE TABLE IF NOT EXISTS `unidade` (
  `idunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `matricula` varchar(50) DEFAULT NULL,
  `senha` varchar(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.


# Dumping structure for table frotatj.veiculo
CREATE TABLE IF NOT EXISTS `veiculo` (
  `idveiculo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `motorista_idmotorista` int(10) unsigned NOT NULL,
  `unidade_idunidade` int(10) unsigned NOT NULL,
  `modelo_idmodelo` int(10) unsigned NOT NULL,
  `placa` varchar(10) DEFAULT NULL,
  `renavan` varchar(20) DEFAULT NULL,
  `chassi` varchar(50) DEFAULT NULL,
  `odometro` int(10) unsigned DEFAULT NULL,
  `situacao` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idveiculo`),
  KEY `veiculo_FKIndex1` (`modelo_idmodelo`),
  KEY `veiculo_FKIndex2` (`unidade_idunidade`),
  KEY `veiculo_FKIndex3` (`motorista_idmotorista`),
  KEY `veiculo_FKIndex4` (`unidade_idunidade`),
  CONSTRAINT `veiculo_ibfk_1` FOREIGN KEY (`modelo_idmodelo`) REFERENCES `modelo` (`idmodelo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `veiculo_ibfk_2` FOREIGN KEY (`unidade_idunidade`) REFERENCES `unidade` (`idunidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `veiculo_ibfk_3` FOREIGN KEY (`motorista_idmotorista`) REFERENCES `motorista` (`idmotorista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `veiculo_ibfk_4` FOREIGN KEY (`unidade_idunidade`) REFERENCES `unidade` (`idunidade`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
