create database javatask default character set utf8mb4;

CREATE TABLE `test`
(
    `id`   int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name` varchar(10)      NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='java 作业练习';