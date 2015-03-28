-- http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#howto-intialize-a-database-using-spring-jdbc
-- check (`user_level` in ('normal','admin','admin','Spammer'))
SET NAMES utf8;

CREATE TABLE `pligg_users` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_login` varchar(32) NOT NULL DEFAULT '',
  `user_level` varchar(16) NOT NULL DEFAULT 'normal',
  `user_modification` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_pass` varchar(64) NOT NULL DEFAULT '',
  `user_email` varchar(128)  NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login` (`user_login`),
  UNIQUE KEY `user_email` (`user_email`)
 
) ;
