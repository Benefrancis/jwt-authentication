-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.6.7-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para jwt-user
CREATE DATABASE IF NOT EXISTS `jwt-user` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `jwt-user`;

-- Copiando estrutura para tabela jwt-user.tb_authority
CREATE TABLE IF NOT EXISTS `tb_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela jwt-user.tb_authority: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_authority` DISABLE KEYS */;
INSERT IGNORE INTO `tb_authority` (`id`, `nome`) VALUES
	(1, 'CONSULTAR'),
	(2, 'ALTERAR'),
	(3, 'EXCLUIR');
/*!40000 ALTER TABLE `tb_authority` ENABLE KEYS */;

-- Copiando estrutura para tabela jwt-user.tb_perfil
CREATE TABLE IF NOT EXISTS `tb_perfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NOME` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela jwt-user.tb_perfil: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_perfil` DISABLE KEYS */;
INSERT IGNORE INTO `tb_perfil` (`id`, `nome`) VALUES
	(1, 'BASICO'),
	(2, 'GRAVAR');
/*!40000 ALTER TABLE `tb_perfil` ENABLE KEYS */;

-- Copiando estrutura para tabela jwt-user.tb_perfil_authority
CREATE TABLE IF NOT EXISTS `tb_perfil_authority` (
  `perfil_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FKkh8uxy179t9cu80vh9lddi5d9` (`authority_id`),
  KEY `FKpfse4vkvv57ydqww5uc2ca6ix` (`perfil_id`),
  CONSTRAINT `FKkh8uxy179t9cu80vh9lddi5d9` FOREIGN KEY (`authority_id`) REFERENCES `tb_authority` (`id`),
  CONSTRAINT `FKpfse4vkvv57ydqww5uc2ca6ix` FOREIGN KEY (`perfil_id`) REFERENCES `tb_perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela jwt-user.tb_perfil_authority: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_perfil_authority` DISABLE KEYS */;
INSERT IGNORE INTO `tb_perfil_authority` (`perfil_id`, `authority_id`) VALUES
	(1, 1),
	(2, 2),
	(2, 3);
/*!40000 ALTER TABLE `tb_perfil_authority` ENABLE KEYS */;

-- Copiando estrutura para tabela jwt-user.tb_user
CREATE TABLE IF NOT EXISTS `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME_EMAIL` (`username`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela jwt-user.tb_user: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT IGNORE INTO `tb_user` (`id`, `email`, `password`, `username`) VALUES
	(1, 'benefrancis@gmail.com', '$2a$12$CkZBeSB7ifjE8b0ezPPiW.tcBEDw.ahN84TbawSIpO9feg5rwthdu', 'benefrancis');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

-- Copiando estrutura para tabela jwt-user.tb_user_perfil
CREATE TABLE IF NOT EXISTS `tb_user_perfil` (
  `user_id` bigint(20) NOT NULL,
  `perfil_id` bigint(20) NOT NULL,
  KEY `FKbhfhnq45g4f72p27tjkt0lvs5` (`perfil_id`),
  KEY `FK7vc8i3wfixf2ig4tl1346ntk7` (`user_id`),
  CONSTRAINT `FK7vc8i3wfixf2ig4tl1346ntk7` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKbhfhnq45g4f72p27tjkt0lvs5` FOREIGN KEY (`perfil_id`) REFERENCES `tb_perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela jwt-user.tb_user_perfil: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_user_perfil` DISABLE KEYS */;
INSERT IGNORE INTO `tb_user_perfil` (`user_id`, `perfil_id`) VALUES
	(1, 2);
/*!40000 ALTER TABLE `tb_user_perfil` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
