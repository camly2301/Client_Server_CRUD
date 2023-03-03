-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: info_song
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baihat`
--

DROP TABLE IF EXISTS `baihat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baihat` (
  `MABH` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TENBAIHAT` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CASI` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `NHACSI` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODAUBH` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `THELOAI` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAMST` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MABH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baihat`
--

LOCK TABLES `baihat` WRITE;
/*!40000 ALTER TABLE `baihat` DISABLE KEYS */;
INSERT INTO `baihat` VALUES ('10001','Thất tình','Trịnh Đình Quang','Trịnh Đình Quang','Anh đã không giữ được nhiều hạnh phúc cho em...','V-Pop','2016'),('10002','Cưới nhau đi(Yes I Do)','Bùi Anh Tuấn-Hiền Hồ','Châu Đăng Khoa','Hỡi em yêu, anh chỉ muốn nói một điều...','V-Pop','2017'),('10003','Vừng lá me bay','Như Quỳnh','Anh Việt Thanh','Nhìn lá me nhớ kỉ niệm hai chúng mình...','Bolero','2015'),('10004','Fire','BTS','BTS','K-Pop','When I wake up in my room...','2018'),('10005','Tội cho cô gái đó','Khắc Việt','Khắc Việt','Em đang cố gắng, kiếm nén ...','V-Pop','2019'),('10006','BANG BANG BANG','BIGBANG','BIGBANG','Nan kkoeeona kkaman bangwa..','K-Pop','2010'),('10007','Chúng ta của hiện tại ','Sơn Tùng','Sơn Tùng','Mùa thu mang giấc mơ...','V-Pop','2013'),('10008','Võ Đông Sơ- Bạch Thu Hà','Kim Tử Long ','Viễn Châu','Biên cương lá rơi Thu Hà em ơi..','Cải lương','2017'),('10010','My love','Westlife','Nhạc ngoại','An empty street, an empty house..','US-UK','2016'),('10011','Duyên Phận','Lệ Quyên','Thái Thịnh','Phận làm con gái chưa một lần yêu ai...','Bolero','2018'),('10012','Happy New Year','ABBA','Nhạc ngoại','No more champagne and the fireworks are through ...','US-UK','2019'),('10013','Love Yourself','Justin Bieber','Nhạc ngoại','For all the times that you rained on my parade...','US-UK','2010'),('10014','Vừng lá me bay','Lệ Quyên','Anh Việt Thanh','Nhìn lá me bay, nhớ kỉ niệm hai chúng mình...','Bolero','2012'),('10015','5 anh em trên một chiếc xe tăng','Nhiều ca sĩ','Doãn Nho','Năm anh em trên một chiếc xe tăng','Nhạc đỏ','2014'),('10016','Hát về cây lúa hôm nay','Trọng Tấn','Hoàng Vân','Tôi hát bài ca ngợi ca cây lúa và người ...','Nhạc đỏ','2015'),('10018','Xúc xắc xúc xẻ','Bé Bảo An','Nguyễn Ngọc Thiện','Xúc xắc xúc xẻ, năm mới năm mẻ...','Nhạc thiếu nhi','2019'),('10019','Áo mới Cà Mau','Dương Hồng Loan','Thanh Sơn','Nghe nói Cà Mau xa lắm, ớ cuối cùng...','Dân ca','2018'),('10020','Bèo dạt mây trôi','Anh Thơ','Dân ca Bắc Bộ','Bèo dạt mây trôi, chốn xa xôi...','Dân ca','2009'),('10021','Hãy trao cho anh','Sơn Tùng MTP','Sơn Tùng MTP','Bóng ai đó nhẹ nhàng vụt qua nơi đây...','V-Pop','2019');
/*!40000 ALTER TABLE `baihat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-18 11:14:29
