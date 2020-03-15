# exemplo-aeronaves
Projeto exemplo API em JAVA 

Projeto desenvolvido em JAVA 8 e Spring Boot 2.5.

## Instalação

### Banco de dados
O projeto utiliza MySQL, com o seguinte schema:
```sql
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: aircraft_api_example
-- ------------------------------------------------------
-- Server version	8.0.19

--
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;

CREATE TABLE `aircraft` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `year` varchar(4) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sold` tinyint DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
Para simplificar o teste foi criado apenas o schema para a seguinte credencial:
```
user = root
password = admin
```
### Servidor
Servidor utilizado para hospedar o projeto em testes foi Apache Tomcat 9, disponível no link:

[32-bit/64-bit Windows Service Installer](http://mirror.nbtelecom.com.br/apache/tomcat/tomcat-9/v9.0.31/bin/apache-tomcat-9.0.31.exe)

O projeto manteve a configuração padrão, utilizando a porta 8080.

### Projeto Front-end
Também foi desenvolvida uma UI que se comunica com a API, ela está disponível no repositório:

[https://github.com/roma153/exemplo-aeronaves-front-end](https://github.com/roma153/exemplo-aeronaves-front-end)

Para testá-la, basta copiar os arquivos do projeto  dentro da pasta ``` webapps ``` dentro do servidor Tomcat.
