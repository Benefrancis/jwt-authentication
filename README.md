# jwt-authentication
Spring Boot JWT Authentication em banco de dados relacional.



#Pré - requisito:

MariaDB instalado e rodando:

Execute o arquivo database/database.sql para gerar o banco de dados.

Pode utilizar o Heidi SQL para executar o script:


<code>
	CREATE DATABASE IF NOT EXISTS `jwt-user`;
</code>

<code>
	USE `jwt-user`;
</code>


<code>
CREATE TABLE IF NOT EXISTS `tb_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;



INSERT IGNORE INTO `tb_authority` (`id`, `nome`) VALUES
	(1, 'CONSULTAR'),
	(2, 'ALTERAR'),
	(3, 'EXCLUIR');

</code>

<code>

CREATE TABLE IF NOT EXISTS `tb_perfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NOME` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;


INSERT IGNORE INTO `tb_perfil` (`id`, `nome`) VALUES
	(1, 'BASICO'),
	(2, 'GRAVAR');
	
</code>


<code>

CREATE TABLE IF NOT EXISTS `tb_perfil_authority` (
  `perfil_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FKkh8uxy179t9cu80vh9lddi5d9` (`authority_id`),
  KEY `FKpfse4vkvv57ydqww5uc2ca6ix` (`perfil_id`),
  CONSTRAINT `FKkh8uxy179t9cu80vh9lddi5d9` FOREIGN KEY (`authority_id`) REFERENCES `tb_authority` (`id`),
  CONSTRAINT `FKpfse4vkvv57ydqww5uc2ca6ix` FOREIGN KEY (`perfil_id`) REFERENCES `tb_perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



INSERT IGNORE INTO `tb_perfil_authority` (`perfil_id`, `authority_id`) VALUES
	(1, 1),
	(2, 2),
	(2, 3);

</code>


<code>


CREATE TABLE IF NOT EXISTS `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME_EMAIL` (`username`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;



INSERT IGNORE INTO `tb_user` (`id`, `email`, `password`, `username`) VALUES
	(1, 'benefrancis@gmail.com', '$2a$12$CkZBeSB7ifjE8b0ezPPiW.tcBEDw.ahN84TbawSIpO9feg5rwthdu', 'benefrancis');

</code>


<code>

CREATE TABLE IF NOT EXISTS `tb_user_perfil` (
  `user_id` bigint(20) NOT NULL,
  `perfil_id` bigint(20) NOT NULL,
  KEY `FKbhfhnq45g4f72p27tjkt0lvs5` (`perfil_id`),
  KEY `FK7vc8i3wfixf2ig4tl1346ntk7` (`user_id`),
  CONSTRAINT `FK7vc8i3wfixf2ig4tl1346ntk7` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKbhfhnq45g4f72p27tjkt0lvs5` FOREIGN KEY (`perfil_id`) REFERENCES `tb_perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



INSERT IGNORE INTO `tb_user_perfil` (`user_id`, `perfil_id`) VALUES (1, 2);


</code>




![DER](docs/database/der.png)





Tudo pronto! Já pode rodar a aplicação.

Run project with Spring Boot App.



#RODANDO A APLICAÇÃO



##Autenticando:


Após rodar a aplicação, abra o postman e crie uma Requisição POST para 

http://localhost:8080/api/auth/signin

com o body:

{
    "username": "benefrancis",
    "password": "123456"
}




![Postman-Autenticando](docs/postman/autenticando.png)




##Conferindo o conteúdo do token

Acesse o site jwt.io

e cole o token gerando:

eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZW5lZnJhbmNpcyIsImlhdCI6MTY1NTAwMzA2NCwiZXhwIjoxNjU1MDc5MzY0LCJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImJlbmVmcmFuY2lzIiwiZW1haWwiOiJiZW5lZnJhbmNpc0BnbWFpbC5jb20iLCJlbmFibGVkIjp0cnVlLCJhdXRob3JpdGllcyI6W3siaWQiOjIsImF1dGhvcml0eSI6IkFMVEVSQVIifSx7ImlkIjozLCJhdXRob3JpdHkiOiJFWENMVUlSIn1dLCJhY2NvdW50Tm9uRXhwaXJlZCI6dHJ1ZSwiY3JlZGVudGlhbHNOb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlfX0.gY3-1tfNv9smn_W2vWT-oBhBCcX2n_cXebJyzdr67xG4vgMjchihXzegz_ILXWRKKi1P6oVupMWiS3PuRzIO3g



![JWT-io](docs/postman/jwt.png)




##Acessando os Recursos:
Para ver os dados do usuário autenticado:

faça uma requisição do tipo GET contendo o Authentication token gerado pela requisição anterior para:

localhost:8080/api/test/user


![Postman-usuario](docs/postman/usuario.png)




