-- http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#howto-intialize-a-database-using-spring-jdbc
CREATE TABLE USER (
 id INT NOT NULL AUTO_INCREMENT,
 login VARCHAR (25),
 password VARCHAR(200),
 PRIMARY KEY(id)
);