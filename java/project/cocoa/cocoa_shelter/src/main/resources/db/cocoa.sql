-- MySQL dump 10.13  Distrib 9.1.0, for macos14 (arm64)
--
-- Host: localhost    Database: cocoa
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bondpair`
--

DROP TABLE IF EXISTS `bondpair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bondpair` (
  `id` bigint NOT NULL COMMENT 'id',
  `category_id` bigint NOT NULL COMMENT 'category_id',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'name',
  `price` decimal(10,2) NOT NULL COMMENT 'price',
  `status` int DEFAULT NULL COMMENT 'status',
  `code` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'code',
  `description` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'description',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'image',
  `create_time` datetime NOT NULL COMMENT 'create_time',
  `update_time` datetime NOT NULL COMMENT 'update_time',
  `create_user` bigint NOT NULL COMMENT 'create_user',
  `update_user` bigint NOT NULL COMMENT 'update_user',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT 'is_deleted',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='_、name';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bondpair`
--

LOCK TABLES `bondpair` WRITE;
/*!40000 ALTER TABLE `bondpair` DISABLE KEYS */;
INSERT INTO `bondpair` VALUES (1861502733454962690,1861502528886173697,'Hola Hoop',9900.00,1,'','They are brothers!','b709e535-c8ec-4af3-9044-9a92ba2f01b7.jpg','2024-11-26 12:10:11','2024-11-26 12:10:11',1,1,0),(1861505899319816194,1861502528886173697,'Rulai&Ruyi',39900.00,1,'','Love fighting','85f094a9-4cf4-4d16-8594-b0ab60996687.jpg','2024-11-26 12:22:46','2024-11-26 12:22:46',1,1,0),(1861534962130411521,1861502571231866882,'Potato&Donut',49900.00,1,'','Very cute together','7a7833bd-c30e-400f-90a2-937466c26ed6.jpg','2024-11-26 14:18:15','2024-11-26 14:18:15',1,1,0);
/*!40000 ALTER TABLE `bondpair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bondpair_pet`
--

DROP TABLE IF EXISTS `bondpair_pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bondpair_pet` (
  `id` bigint NOT NULL COMMENT 'id',
  `bondpair_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'bondpair_id',
  `pet_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'pet_id',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'name',
  `price` decimal(10,2) DEFAULT NULL COMMENT 'price',
  `copies` int NOT NULL COMMENT 'copies',
  `sort` int NOT NULL DEFAULT '0' COMMENT 'sort',
  `create_time` datetime NOT NULL COMMENT 'create_time',
  `update_time` datetime NOT NULL COMMENT 'update_time',
  `create_user` bigint NOT NULL COMMENT 'create_user',
  `update_user` bigint NOT NULL COMMENT 'update_user',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT 'is_deleted',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='relationship';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bondpair_pet`
--

LOCK TABLES `bondpair_pet` WRITE;
/*!40000 ALTER TABLE `bondpair_pet` DISABLE KEYS */;
INSERT INTO `bondpair_pet` VALUES (1415580119052894209,'1415580119015145474','1397862198033297410','cocoa',49800.00,1,0,'2021-07-15 15:52:55','2021-07-15 15:52:55',1415576781934608386,1415576781934608386,0),(1860030877258604545,'1860030877170524161','1858699277686034434','Carlo',29900.00,1,0,'2024-11-22 10:41:33','2024-11-22 10:41:33',1859486102902050817,1859486102902050817,0),(1860030877262798849,'1860030877170524161','1858698874525339650','Monty',9900.00,1,0,'2024-11-22 10:41:34','2024-11-22 10:41:34',1859486102902050817,1859486102902050817,0),(1861502733492711425,'1861502733454962690','1861502059677773826','Hoop',7900.00,1,0,'2024-11-26 12:10:11','2024-11-26 12:10:11',1,1,0),(1861502733501100034,'1861502733454962690','1861501865150148610','Hola',3900.00,1,0,'2024-11-26 12:10:11','2024-11-26 12:10:11',1,1,0),(1861505899349176321,'1861505899319816194','1861505739525222402','Ruyi',19900.00,1,0,'2024-11-26 12:22:46','2024-11-26 12:22:46',1,1,0),(1861505899357564929,'1861505899319816194','1861505565260279809','Rulai',19900.00,1,0,'2024-11-26 12:22:46','2024-11-26 12:22:46',1,1,0),(1861534962180743170,'1861534962130411521','1861533609081495553','Donut',12500.00,1,0,'2024-11-26 14:18:15','2024-11-26 14:18:15',1,1,0),(1861534962180743171,'1861534962130411521','1861532941583179777','Potato',18900.00,1,0,'2024-11-26 14:18:15','2024-11-26 14:18:15',1,1,0);
/*!40000 ALTER TABLE `bondpair_pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT 'id',
  `type` int DEFAULT NULL COMMENT 'type',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'name',
  `sort` int NOT NULL DEFAULT '0' COMMENT 'sort',
  `create_time` datetime NOT NULL COMMENT 'create_time',
  `update_time` datetime NOT NULL COMMENT 'update_time',
  `create_user` bigint NOT NULL COMMENT 'create_user',
  `update_user` bigint NOT NULL COMMENT 'update_user',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='idx_category_name';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1861493066553212929,1,'British Shorthair',1,'2024-11-26 11:31:47','2024-11-27 15:39:48',1,1),(1861493246363025410,1,'American Shorthair',2,'2024-11-26 11:32:29','2024-11-26 17:47:08',1,1),(1861493354433462274,1,'Ragdoll',3,'2024-11-26 11:32:55','2024-11-26 11:32:55',1,1),(1861493501938745346,1,'Persian',4,'2024-11-26 11:33:30','2024-11-26 11:33:30',1,1),(1861493702627803137,1,'Mixed Breed Cats',5,'2024-11-26 11:34:18','2024-11-26 17:47:24',1,1),(1861493826707898369,1,'Other Breeds Cats',6,'2024-11-26 11:34:48','2024-11-26 17:47:34',1,1),(1861494020065312769,1,'Labrador Retriever',6,'2024-11-26 11:35:34','2024-11-26 17:48:06',1,1),(1861494075576926209,1,'Golden Retriever',7,'2024-11-26 11:35:47','2024-11-26 17:48:12',1,1),(1861494139972075521,1,'German Shepherd',8,'2024-11-26 11:36:03','2024-11-26 17:48:18',1,1),(1861494193290067970,1,'Beagle',9,'2024-11-26 11:36:15','2024-11-26 11:36:15',1,1),(1861494242300510209,1,'Poodle',10,'2024-11-26 11:36:27','2024-11-26 11:36:27',1,1),(1861494291784908801,1,'Mixed Breed Dogs',11,'2024-11-26 11:36:39','2024-11-26 17:48:30',1,1),(1861494332259942401,1,'Other Breeds Dogs',12,'2024-11-26 11:36:48','2024-11-26 17:48:43',1,1),(1861502528886173697,2,'Cats Bonded Pair',15,'2024-11-26 12:09:23','2024-11-26 17:48:58',1,1),(1861502571231866882,2,'Dogs Bonded Pair',16,'2024-11-26 12:09:33','2024-11-26 17:49:08',1,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'name',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'username',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'password',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'phone',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'sex',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'id_number',
  `status` int NOT NULL DEFAULT '1' COMMENT 'status',
  `create_time` datetime NOT NULL COMMENT 'create_time',
  `update_time` datetime NOT NULL COMMENT 'update_time',
  `create_user` bigint NOT NULL COMMENT 'create_user',
  `update_user` bigint NOT NULL COMMENT 'update_user',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='idx_username';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Miffy','admin','e10adc3949ba59abbe56e057f20f883e','13812312312','1','110101199001010047',1,'2021-05-06 17:20:07','2021-05-10 02:24:09',1,1),(1857125807831461889,'Jump','456','e10adc3949ba59abbe56e057f20f883e','17324083642','0','123456789012345679',1,'2024-11-14 10:17:51','2024-11-14 10:17:51',1,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'name',
  `category_id` bigint NOT NULL COMMENT 'category_id',
  `price` decimal(10,2) DEFAULT NULL COMMENT 'price',
  `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'code',
  `image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'image',
  `description` varchar(400) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'description',
  `status` int NOT NULL DEFAULT '1' COMMENT 'status',
  `sort` int NOT NULL DEFAULT '0' COMMENT 'sort',
  `create_time` datetime NOT NULL COMMENT 'create_time',
  `update_time` datetime NOT NULL COMMENT 'update_time',
  `create_user` bigint NOT NULL COMMENT 'create_user',
  `update_user` bigint NOT NULL COMMENT 'update_user',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT 'is_deleted',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='idx_dish_name';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (1858423958987096065,'Miley',1861493246363025410,19900.00,'','056b8a23-6068-430d-af59-e11a0ffd59ce.jpg','Very cute!',1,0,'2024-11-18 00:16:14','2024-11-26 12:25:56',1,1,0),(1858698874525339650,'Monty',1861493066553212929,9900.00,'','6b56246a-6e01-47be-8606-6e06d3aaa496.jpg','Cute but independent',1,0,'2024-11-18 18:28:39','2024-11-26 11:51:53',1,1,0),(1858699277686034434,'Carlo',1861493066553212929,29900.00,'','ab9b3d7f-a349-4036-a897-c632dc99627f.jpg','Very clingy',1,0,'2024-11-18 18:30:15','2024-11-26 11:51:43',1,1,0),(1861501865150148610,'Hola',1861493066553212929,3900.00,'','c6089f76-8889-40d7-881d-22791180dff3.jpg','Very cute!!!!',1,0,'2024-11-26 12:06:44','2024-11-26 12:06:44',1,1,0),(1861502059677773826,'Hoop',1861493066553212929,7900.00,'','0e372e4f-edf8-456c-9326-a47b73284dbf.jpg','Baby brother of Hola',1,0,'2024-11-26 12:07:31','2024-11-26 12:07:31',1,1,0),(1861505565260279809,'Rulai',1861493501938745346,19900.00,'','caca6b6c-e0a0-45ae-88e8-a26aee39212b.jpg','Big sister',1,0,'2024-11-26 12:21:27','2024-11-26 15:18:32',1,1,0),(1861505739525222402,'Ruyi',1861493702627803137,19900.00,'','48faf160-ec7d-4ecd-ae0c-272bc8b2ca56.jpg','Baby sister',1,0,'2024-11-26 12:22:08','2024-11-26 12:22:08',1,1,0),(1861529237052252161,'Puncky',1861493354433462274,29900.00,'','f66a9a07-faf0-402f-821f-7b37867d4ae9.jpg','Always seems angry, but actually not.',1,0,'2024-11-26 13:55:30','2024-11-26 13:55:30',1,1,0),(1861529413015887873,'Pumpkin',1861494075576926209,7900.00,'','6169f83b-1f5b-4c16-9e74-a4869b2aa391.jpg','Very happy',1,0,'2024-11-26 13:56:12','2024-11-26 13:56:12',1,1,0),(1861529681782693889,'Ice',1861494139972075521,2900.00,'','0cee806c-e266-4c50-ab7c-f777795a9d8c.jpg','Shy',1,0,'2024-11-26 13:57:16','2024-11-26 13:57:16',1,1,0),(1861529851303878657,'Ballon',1861494020065312769,8900.00,'','871fe457-a8e4-41ed-b242-aa7ca2ee6ec1.jpg','Loves driving',1,0,'2024-11-26 13:57:57','2024-11-26 13:57:57',1,1,0),(1861530019906510849,'Coffee',1861494193290067970,6900.00,'','58740ed8-876a-44ca-8240-8c51eef70640.jpg','Loves running',1,0,'2024-11-26 13:58:37','2024-11-26 13:58:37',1,1,0),(1861530244243054594,'Danny',1861494242300510209,12900.00,'','260af6a4-743d-416f-bbd5-2c6287641481.jpg','Loves playing with kids',1,0,'2024-11-26 13:59:30','2024-11-26 13:59:30',1,1,0),(1861530432705716225,'Phoebe',1861494242300510209,12900.00,'','f3158a20-9284-4df6-afcc-4c87f8160c00.jpg','Cute',1,0,'2024-11-26 14:00:15','2024-11-26 14:00:15',1,1,0),(1861530644740366338,'Fanta',1861494193290067970,2900.00,'','3044233a-dd3d-4e60-ac00-fd13196162d6.jpg','hahah',1,0,'2024-11-26 14:01:06','2024-11-26 14:01:06',1,1,0),(1861531598940332034,'Piper',1861493354433462274,39900.00,'','99e27c1d-6b06-48c8-8a8b-a48597074be4.jpg','Very sweet',1,0,'2024-11-26 14:04:53','2024-11-26 14:04:53',1,1,0),(1861532941583179777,'Potato',1861494075576926209,18900.00,'','005ba71e-2e42-430d-b64b-9c78dc5bc759.jpg','Loves eating',0,0,'2024-11-26 14:10:14','2024-11-26 14:10:14',1,1,0),(1861533609081495553,'Donut',1861494291784908801,12500.00,'','6ad8cd12-744a-4d6f-acc5-1e255e1af65d.jpg','Loves running',0,0,'2024-11-26 14:12:53','2024-11-26 14:12:53',1,1,0),(1861533879114981378,'Cupcake',1861494242300510209,29900.00,'','2183e81d-3f27-4c82-841c-3abd5006e607.jpg','A very big cupcake',1,0,'2024-11-26 14:13:57','2024-11-26 14:13:57',1,1,0),(1861534046895529986,'Baobao',1861494332259942401,59900.00,'','a0cf6804-03a3-4c8a-897f-50062700ca38.jpg','Loves slippers',1,0,'2024-11-26 14:14:37','2024-11-26 14:14:37',1,1,0),(1861551200793587714,'Jump',1861493826707898369,1900.00,'','4e474133-9f39-490b-8aea-6215527ce3c3.jpg','Very active!',1,0,'2024-11-26 15:22:47','2024-11-26 21:17:45',1,1,0),(1861866046365982722,'hbjb',1861493246363025410,6600.00,'','8502645c-cbcb-4fb0-a305-82e0fb7fddd6.jpg','jnklj',1,0,'2024-11-27 12:13:52','2024-11-27 12:13:52',1,1,0),(1861901271104090113,'hkkk',1861493066553212929,8800.00,'','ad0973df-042d-4d5a-a7a1-84f7b2ba2f95.jpg','kmjnkjnknk',1,0,'2024-11-27 14:33:50','2024-11-28 15:59:34',1,1,0);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_detail`
--

DROP TABLE IF EXISTS `pet_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_detail` (
  `id` bigint NOT NULL COMMENT 'id',
  `pet_id` bigint DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'name',
  `value` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'value',
  `create_time` datetime NOT NULL COMMENT 'create_time',
  `update_time` datetime NOT NULL COMMENT 'update_time',
  `create_user` bigint NOT NULL COMMENT 'create_user',
  `update_user` bigint NOT NULL COMMENT 'update_user',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT 'is_deleted',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='relationship';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_detail`
--

LOCK TABLES `pet_detail` WRITE;
/*!40000 ALTER TABLE `pet_detail` DISABLE KEYS */;
INSERT INTO `pet_detail` VALUES (1858423959075176449,1858423958987096065,'sterilization','[\"no sterilization\",\"sterilization\"]','2024-11-26 12:25:56','2024-11-26 12:25:56',1,1,0);
/*!40000 ALTER TABLE `pet_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'name',
  `phone` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'phone',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'sex',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'id_number',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'avatar',
  `status` int DEFAULT '0' COMMENT 'status',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='user information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1859486102902050817,NULL,'13345678901',NULL,NULL,NULL,1),(1859489958948016129,NULL,'13234235323',NULL,NULL,NULL,1),(1860032477196840961,NULL,'17324087343',NULL,NULL,NULL,1),(1860045277260443650,NULL,'17324081492',NULL,NULL,NULL,1),(1863007165631991810,NULL,'13345678900',NULL,NULL,NULL,1),(1863009117048700930,NULL,'19345678900',NULL,NULL,NULL,1),(1863010244536340481,NULL,'19945678900',NULL,NULL,NULL,1),(1863011307230347266,NULL,'17345678900',NULL,NULL,NULL,1),(1863011742423003137,NULL,'17345678909',NULL,NULL,NULL,1),(1863017679577710594,NULL,'13745678909',NULL,NULL,NULL,1),(1863113867085864961,NULL,'13345678909',NULL,NULL,NULL,1),(1863114995605184513,NULL,'1234567890',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-30 23:46:21
