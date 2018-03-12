-- MySQL dump 10.13  Distrib 5.6.16, for Win64 (x86_64)
--
-- Host: localhost    Database: blurredpassword
-- ------------------------------------------------------
-- Server version	5.6.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blurredpassword`
--

DROP TABLE IF EXISTS `blurredpassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blurredpassword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blurredpassword`
--

LOCK TABLES `blurredpassword` WRITE;
/*!40000 ALTER TABLE `blurredpassword` DISABLE KEYS */;
INSERT INTO `blurredpassword` VALUES (1,'蓝调口琴','flyingideal','flyingideal','desc'),(2,'蓝调口琴2','flyingideal2','flyingideal2','desc2'),(3,'蓝调口琴3','flyingideal3','flyingideal3','desc3');
/*!40000 ALTER TABLE `blurredpassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) DEFAULT NULL,
  `permission_name` varchar(50) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'user:create','新建用户',1,'新建用户权限','2017-09-19 14:31:29.000000','2017-09-19 14:31:32.000000'),(2,'user:delete','删除用户',1,'删除用户权限','2017-09-19 14:32:21.000000','2017-09-19 14:32:23.000000'),(3,'user:update','修改用户',1,'修改用户权限','2017-09-19 14:33:15.000000','2017-09-19 14:33:19.000000'),(4,'user:view','查看用户',1,'查看用户权限','2017-09-19 14:56:10.000000','2017-09-19 14:56:13.000000');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin','管理员',1,'管理员角色','2017-09-19 14:34:49.000000','2017-09-19 14:34:52.000000'),(3,'role1','普通用户',1,'普通用户角色','2017-09-19 15:45:05.000000','2017-09-19 15:45:07.000000');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES (1,1,'2017-09-19 14:54:59.000000','2017-09-19 14:55:01.000000'),(1,2,'2017-09-19 14:55:09.000000','2017-09-19 14:55:11.000000'),(1,3,'2017-09-19 14:55:21.000000','2017-09-19 14:55:24.000000'),(1,4,'2017-09-19 14:56:47.000000','2017-09-19 14:56:51.000000'),(2,4,'2017-09-19 14:57:38.000000','2017-09-19 14:57:41.000000');
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_url_filter`
--

DROP TABLE IF EXISTS `t_url_filter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_url_filter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '路径',
  `roles` varchar(100) DEFAULT NULL COMMENT '访问url需要的角色',
  `permissions` varchar(100) DEFAULT NULL COMMENT '访问url需要的权限',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_url_filter`
--

LOCK TABLES `t_url_filter` WRITE;
/*!40000 ALTER TABLE `t_url_filter` DISABLE KEYS */;
INSERT INTO `t_url_filter` VALUES (1,'权限管理','/admin/**','admin','','2017-09-25 17:35:28','2017-09-25 17:35:32'),(7,'路径权限设置','/urlFilter/**','admin',NULL,'2017-09-26 11:09:27','2017-09-26 11:09:27');
/*!40000 ALTER TABLE `t_url_filter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '密码加密的salt',
  `locked` tinyint(1) unsigned DEFAULT '0' COMMENT '是否锁定',
  `source` tinyint(1) unsigned DEFAULT '0' COMMENT '用户来源，参考t_user_source',
  `source_username` varchar(100) DEFAULT NULL COMMENT '第三方登录用户名',
  `organization_id` int(20) unsigned DEFAULT NULL COMMENT '组织部门id',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','fd32e054edde3dfa430f2384370b5e7e','a522078f8b8bcccc341ba1268a8c99a6',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'admin1','fd32e054edde3dfa430f2384370b5e7e','a522078f8b8bcccc341ba1268a8c99a6',0,0,'string',0,'1@qq.com','12345678901',NULL,'2017-06-02 19:21:03.000000','2017-06-02 19:21:03.000000');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `gmt_create` timestamp(6) NULL DEFAULT NULL,
  `gmt_modified` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1,'admin','2017-09-19 06:53:33.000000','2017-09-19 06:53:36.000000'),(11,1,'admin1',NULL,NULL);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_source`
--

DROP TABLE IF EXISTS `t_user_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_source` (
  `source_id` tinyint(1) NOT NULL COMMENT '用户来源标志',
  `source_name` varchar(50) NOT NULL COMMENT '用户来源名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_source`
--

LOCK TABLES `t_user_source` WRITE;
/*!40000 ALTER TABLE `t_user_source` DISABLE KEYS */;
INSERT INTO `t_user_source` VALUES (0,'网站注册'),(1,'QQ登录'),(2,'微信登录'),(3,'支付宝登录'),(4,'新浪微博登录');
/*!40000 ALTER TABLE `t_user_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 16:09:56
