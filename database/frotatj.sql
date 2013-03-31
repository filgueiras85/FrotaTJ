CREATE DATABASE IF NOT EXISTS `frotatj`;
USE `frotatj`;


CREATE TABLE IF NOT EXISTS `abastecimento` (
  `idabastecimento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `veiculo_idveiculo` int(10) unsigned NOT NULL,
  `km_odometro` int(10) unsigned DEFAULT NULL,
  `data_2` date DEFAULT NULL,
  PRIMARY KEY (`idabastecimento`),
  KEY `abastecimento_FKIndex1` (`veiculo_idveiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

INSERT INTO `abastecimento` (`idabastecimento`, `veiculo_idveiculo`, `km_odometro`, `data_2`) VALUES
	(1, 1, 500, '2012-01-01'),
	(2, 2, 5000, '2012-02-01'),
	(3, 1, 1100, '2012-01-20'),
	(4, 3, 10100, '2012-03-01'),
	(5, 4, 200, '2012-04-01'),
	(6, 4, 500, '2012-04-20'),
	(7, 4, 1500, '2012-04-30'),
	(8, 4, 8000, '2012-05-10'),
	(9, 1, 4000, '2012-03-10'),
	(10, 3, 12000, '2012-11-02'),
	(11, 16, 100, '2013-02-25'),
	(12, 16, 201, '2013-02-26'),
	(13, 16, 215, '2013-02-26'),
	(14, 16, 220, '2013-02-26'),
	(15, 1, 9000, '2013-02-26'),
	(16, 1, 7500, '2013-02-26'),
	(17, 1, 8000, '2013-02-26'),
	(18, 1, 9000, '2013-02-26'),
	(19, 1, 9000, '2013-02-24'),
	(20, 1, 9000, '2013-02-25'),
	(21, 1, 9000, '2013-02-26'),
	(22, 1, 9000, '2013-02-26'),
	(23, 1, 9999, '2013-02-11'),
	(24, 1, 9900, '2013-02-26'),
	(25, 1, 9000, '2013-02-26'),
	(26, 16, 4200, '2013-02-26'),
	(27, 15, 4400, '2013-02-27'),
	(28, 15, 5100, '2013-03-12');

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `idfornecedor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `cnpj` varchar(200) DEFAULT NULL,
  `fone1` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fone2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idfornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `fornecedor` (`idfornecedor`, `nome`, `cnpj`, `fone1`, `email`, `fone2`) VALUES
	(1, 'Casa do Óleo', '111111', '22222', 'aaaa@aaa', '33333'),
	(2, 'Posto da Vovó', '121212', '343434', 'sac@vovo', '6757675');


CREATE TABLE IF NOT EXISTS `marca` (
  `idmarca` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


INSERT INTO `marca` (`idmarca`, `nome`) VALUES
	(1, 'Fiat'),
	(2, 'Renault'),
	(3, 'dsfsdf'),
	(5, 'dfsf'),
	(6, '345345'),
	(7, '1111'),
	(8, '3333'),
	(9, '3333');

CREATE TABLE IF NOT EXISTS `modelo` (
  `idmodelo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marca_idmarca` int(10) unsigned NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idmodelo`),
  KEY `modelo_FKIndex1` (`marca_idmarca`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `modelo` (`idmodelo`, `marca_idmarca`, `nome`) VALUES
	(1, 1, 'Uno mille'),
	(2, 2, 'Clio');


CREATE TABLE IF NOT EXISTS `motorista` (
  `idmotorista` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `unidade_idunidade` int(10) unsigned NOT NULL,
  `matricula` varchar(200) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idmotorista`),
  KEY `motorista_FKIndex1` (`unidade_idunidade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



INSERT INTO `motorista` (`idmotorista`, `unidade_idunidade`, `matricula`, `nome`) VALUES
	(1, 1, '1', 'James'),
	(2, 1, '2', 'Almir'),
	(3, 2, '1111', '111111');


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
  KEY `servico_FKIndex5` (`usuario_idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;



INSERT INTO `servico` (`idservico`, `usuario_idusuario`, `motorista_idmotorista`, `tipo_servico_idtipo_servico`, `veiculo_idveiculo`, `fornecedor_idfornecedor`, `data_2`, `valor`, `nro_orcamento`, `nf_ticket`, `descricao`, `km`) VALUES
	(1, 1, 1, 1, 1, 1, '2012-07-29 00:00:00', 999, '888', 777, 'teste', 5000),
	(2, 1, 1, 1, 2, 1, '2012-08-30 00:00:00', 999, '888', 777, 'teste', 15000),
	(3, 1, 1, 1, 3, 1, '2012-09-30 00:00:00', 999, '888', 777, 'teste', 20000),
	(4, 1, 1, 1, 4, 1, '2012-11-05 00:00:00', 999, '888', 777, 'teste', 40000),
	(5, 1, 1, 1, 5, 1, '2012-06-15 00:00:00', 999, '888', 777, 'teste', 50000),
	(6, 1, 1, 1, 6, 1, '2012-04-01 00:00:00', 999, '888', 777, 'teste', 65000),
	(7, 1, 2, 2, 4, 2, '2012-12-01 00:00:00', 887, '889898', 7877, 'teste', 41000),
	(9, 1, 1, 1, 12, 1, '2013-02-24 00:00:01', 777.77, '981012', 902332, 'dsdsdjhk', 5000),
	(10, 1, 1, 1, 1, 1, '2013-02-25 00:00:01', 98.88, '03803', 9039, '29-ee', 8000),
	(11, 1, 1, 1, 1, 1, '2013-02-26 00:00:01', 88.88, '88998', 9898, '9898', 9000),
	(12, 1, 1, 1, 1, 1, '2013-02-26 00:00:01', 22.22, '32932', 9230, '9203902', 9100),
	(13, 1, 1, 1, 12, 1, '2013-02-27 00:00:01', 95.66, '2165', 216984, 'teste', 5200),
	(14, 1, 1, 2, 12, 2, '2013-02-27 00:00:01', 88.66, '32165', 65498, 'teste', 4800),
	(15, 1, 1, 1, 15, 1, '2013-02-27 00:00:01', 99.99, '999', 9090, '9090', 4500),
	(16, 1, 1, 2, 15, 1, '2013-02-27 00:00:01', 101.01, '010310301', 903902, '9029201', 4501),
	(17, 1, 1, 2, 16, 2, '2013-03-04 00:00:01', 87.87, '9879', 928098, 'teste9999', 4900),
	(18, 1, 1, 2, 1, 2, '2013-03-04 00:00:01', 44.55, '9487', 8787, 'teste282828', 3500),
	(19, 1, 1, 2, 10, 1, '2013-03-04 00:00:01', 33.44, '98798', 98798, 'teste27726', 3200),
	(20, 1, 1, 1, 11, 1, '2013-03-11 00:00:01', 134.55, '9890', 45645, 'testeee', 5000),
	(21, 1, 2, 2, 11, 2, '2013-03-11 00:00:01', 44.55, '987', 9898, 'testeeee', 5000),
	(22, 1, 1, 1, 10, 1, '2013-03-12 00:00:01', 99.99, '9990', 909, '9090', 5100),
	(23, 1, 1, 1, 1, 1, '2013-03-12 00:00:01', 39.31, '39201', 902390, '903290', 5100),
	(24, 1, 1, 2, 8, 1, '2013-03-13 00:00:01', 76.65, '9798', 98743029, 'testee', 4500);


CREATE TABLE IF NOT EXISTS `tipo_servico` (
  `idtipo_servico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idtipo_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;



INSERT INTO `tipo_servico` (`idtipo_servico`, `nome`) VALUES
	(1, 'Troca de Óleo'),
	(2, 'Troca Filtro Ar'),
	(3, 'Geometria e Balanceamento'),
	(4, 'Higienização do AC'),
	(5, 'rtytry');


CREATE TABLE IF NOT EXISTS `tipo_servico_modelo` (
  `tipo_servico_idtipo_servico` int(10) unsigned NOT NULL,
  `modelo_idmodelo` int(10) unsigned NOT NULL,
  `km` int(10) unsigned DEFAULT NULL,
  `tempo` int(10) unsigned DEFAULT NULL,
  `percentualAviso` int(11) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `data_proximo_servico` date DEFAULT NULL,
  `situacao` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tipo_servico_idtipo_servico`,`modelo_idmodelo`),
  KEY `tipo_servico_has_modelo_FKIndex1` (`tipo_servico_idtipo_servico`),
  KEY `tipo_servico_has_modelo_FKIndex2` (`modelo_idmodelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `tipo_servico_modelo` (`tipo_servico_idtipo_servico`, `modelo_idmodelo`, `km`, `tempo`, `percentualAviso`, `descricao`, `data_proximo_servico`, `situacao`) VALUES
	(1, 1, 5000, 6, 20, 'óleo 20/40', NULL, 'vermelho'),
	(2, 1, 4500, 3, 20, 'teste', NULL, 'vermelho');


CREATE TABLE IF NOT EXISTS `tipo_servico_veiculo` (
  `veiculo_idveiculo` int(10) unsigned NOT NULL,
  `tipo_servico_idtipo_servico` int(10) unsigned NOT NULL,
  `situacao` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`veiculo_idveiculo`,`tipo_servico_idtipo_servico`),
  KEY `servico_has_veiculo_FKIndex1` (`tipo_servico_idtipo_servico`),
  KEY `servico_has_veiculo_FKIndex2` (`veiculo_idveiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `tipo_servico_veiculo` (`veiculo_idveiculo`, `tipo_servico_idtipo_servico`, `situacao`) VALUES
	(1, 1, 'vazio'),
	(15, 1, 'verde'),
	(15, 2, 'verde'),
	(16, 1, 'verde'),
	(16, 2, 'verde');


CREATE TABLE IF NOT EXISTS `unidade` (
  `idunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idunidade`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



INSERT INTO `unidade` (`idunidade`, `nome`) VALUES
	(1, 'Comarca da Capital'),
	(2, 'teste');


CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `matricula` varchar(50) DEFAULT NULL,
  `senha` varchar(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;



INSERT INTO `usuario` (`idusuario`, `nome`, `matricula`, `senha`, `email`, `administrador`) VALUES
	(1, 'admin', '1', 'admin', 'admin@fean.com', 1),
	(2, 'convidado', '2', '1234', '', 0),
	(3, 'pedro', '3', '1', 'aaa', 1),
	(4, 'marangoni', '4', '12345', 'pedro911@gmail.com', 1),
	(5, 'almir', '', '', 'pora@at.com', 1),
	(7, 'almir', '22', '', 'pos', 1),
	(9, 'almir', '222', '', 'slkd', 1),
	(16, 'paçoca', '44', '321', 'pa@pa.com', 1),
	(17, 'lsdj', '333', 'dff', 'lkjsdf@ff.com', 0),
	(18, 'teste adm', '1356', '5poi', 'adm@adm.com', 1),
	(20, 'oi galera', '0808', '1234', 'oigalera@bol.com', 1),
	(21, 'anesio', '1223', '1920', 'anesio@tutopia.com', 1),
	(22, 'dj gas james', '986764', '9877', 'gas@dj.music', 1),
	(24, 'anesio', '1223', '3455', 'anesio@bol.com.br', 1),
	(25, 'anesio bbbbbbbbbb', '999999999', 'iiii', 'anesio@ufsc.br', 0);


CREATE TABLE IF NOT EXISTS `veiculo` (
  `idveiculo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `motorista_idmotorista` int(10) unsigned NOT NULL,
  `unidade_idunidade` int(10) unsigned NOT NULL,
  `modelo_idmodelo` int(10) unsigned NOT NULL,
  `placa` varchar(10) DEFAULT NULL,
  `renavan` varchar(20) DEFAULT NULL,
  `chassi` varchar(50) DEFAULT NULL,
  `odometro` int(10) unsigned DEFAULT NULL,
  `situacao` varchar(20) DEFAULT NULL,
  `km_cadastro` int(11) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  PRIMARY KEY (`idveiculo`),
  KEY `veiculo_FKIndex1` (`modelo_idmodelo`),
  KEY `veiculo_FKIndex2` (`unidade_idunidade`),
  KEY `veiculo_FKIndex3` (`motorista_idmotorista`),
  KEY `veiculo_FKIndex4` (`unidade_idunidade`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;



INSERT INTO `veiculo` (`idveiculo`, `motorista_idmotorista`, `unidade_idunidade`, `modelo_idmodelo`, `placa`, `renavan`, `chassi`, `odometro`, `situacao`, `km_cadastro`, `data_cadastro`) VALUES
	(1, 1, 1, 1, 'AAA1234', '12345', '4321', 5100, 'verde', 0, '2012-01-01 00:00:00'),
	(2, 2, 1, 1, 'BBB4321', '6543', '34535', 19500, 'vermelho', 10000, '2012-02-01 00:00:00'),
	(3, 2, 1, 1, 'CCC9999', '9898', '546535', 25500, 'vermelho', 15000, '2012-03-01 00:00:00'),
	(4, 1, 1, 1, 'DDD8888', '7676', '4343', 42000, 'vermelho', 30000, '2012-01-11 00:00:00'),
	(5, 1, 1, 1, 'CXP-0000', '1111111111', '9BW ZZZ377 VT', 53000, 'vermelho', 40000, '2012-02-22 00:00:00'),
	(6, 1, 1, 1, 'EEE2222', '7676', '4343', 66000, 'vermelho', 50000, '2012-03-30 00:00:00'),
	(7, 1, 1, 1, 'FFF2222', '7676', '4343', 3000, 'vermelho', 0, '2012-11-01 00:00:00'),
	(8, 1, 1, 1, 'GGG2222', '7676', '4343', 4500, 'vermelho', 0, '2012-11-01 00:00:00'),
	(9, 1, 1, 1, 'HHH2222', '7676', '4343', 5200, 'vermelho', 0, '2012-11-01 00:00:00'),
	(10, 1, 1, 1, 'III2222', '7676', '4343', 5100, 'verde', 0, '2012-10-11 00:00:00'),
	(11, 1, 1, 1, 'JJJ2222', '7676', '4343', 5000, 'verde', 0, '2012-06-28 00:00:00'),
	(12, 1, 1, 1, 'LLL2222', '7676', '4343', 4800, 'verde', 0, '2012-03-30 00:00:00'),
	(15, 1, 1, 1, 'HHH-2222', '91901092-1', '90329020929998889', 5100, 'verde', 0, '2013-02-23 22:49:39'),
	(16, 1, 1, 1, 'MMM-2222', '29290290-9', '90290290290290290', 4900, 'amarelo', 0, '2013-02-25 21:31:32');

