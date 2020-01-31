-- MySQL dump 10.13  Distrib 5.1.41, for Win32 (ia32)
--
-- Host: localhost    Database: laundry1.0
-- ------------------------------------------------------
-- Server version	5.1.41

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
-- Current Database: `laundry1.0`
--

/*!40000 DROP DATABASE IF EXISTS `laundry1.0`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `laundry1.0` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `laundry1.0`;

--
-- Table structure for table `belanja`
--

DROP TABLE IF EXISTS `belanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belanja` (
  `id_trans` varchar(10) NOT NULL,
  `user` varchar(15) DEFAULT NULL,
  `tgl_belanja` date NOT NULL,
  `pengeluaran` varchar(50) NOT NULL,
  `id_kat` varchar(10) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `qty` int(10) NOT NULL,
  `harga` int(10) NOT NULL,
  `total` int(10) NOT NULL,
  `ket` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_trans`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belanja`
--

LOCK TABLES `belanja` WRITE;
/*!40000 ALTER TABLE `belanja` DISABLE KEYS */;
INSERT INTO `belanja` VALUES ('T-0003','inggrid','2019-07-10','Penghilang Noda Darah','K-01','Pemutih Pakaian',5,35000,175000,'Produk perliter'),('T-0002','inggrid','2019-07-10','Penghilang Noda Karat ','K-01','Pemutih Pakaian',5,35000,175000,'Produk perliter'),('T-0001','inggrid','2019-07-10','Penghilang Noda Minyak/Lemak','K-01','Pemutih Pakaian',10,35000,350000,'Produk perliter'),('T-0004','inggrid','2019-07-10','Bayclin1000ml','K-01','Pemutih Pakaian',12,11300,135600,'Beli perlusin'),('T-0005','inggrid','2019-07-10','So Klin Pemutih 500ml','K-01','Pemutih Pakaian',12,5500,66000,'Beli perlusin'),('T-0006','inggrid','2019-07-10','Vanish 1000ml','K-01','Pemutih Pakaian',5,30100,150500,'Perbotol'),('T-0007','inggrid','2019-07-10','Proclin Crystal Kemasan 200gr','K-01','Pemutih Pakaian',1,107000,107000,'Satu  katon isi 6 pack'),('T-0008','inggrid','2019-07-10','Lavender 600ml','K-02','Pewangi Pakaian',3,14000,42000,'Parfum pakaian'),('T-0009','inggrid','2019-07-10','Apple Fresh 600ml','K-02','Pewangi Pakaian',3,14000,42000,'Parfum pakaian'),('T-0010','inggrid','2019-07-10','Sakura 600ml','K-02','Pewangi Pakaian',2,14000,28000,'Parfum Pakaian'),('T-0011','inggrid','2019-07-10','Lemon 600ml','K-02','Pewangi Pakaian',4,15000,60000,'Parfum Pakaian'),('T-0012','inggrid','2019-07-10','Daisy 600ml','K-02','Pewangi Pakaian',5,14000,70000,'Parfum Pakaian'),('T-0013','inggrid','2019-07-10','Vanilla 1500ml','K-02','Pewangi Pakaian',3,31000,93000,'Parfum Pakaian'),('T-0014','inggrid','2019-07-10','Love Sarah 1500ml','K-02','Pewangi Pakaian',4,31000,124000,'Parfum Pakaian'),('T-0015','inggrid','2019-07-10','Frutty 1500ml','K-02','Pewangi Pakaian',2,30000,60000,'Parfum Pakaian'),('T-0016','inggrid','2019-07-10','Blue Ocean 1500ml','K-02','Pewangi Pakaian',4,28000,112000,'Parfum Pakaian'),('T-0017','inggrid','2019-07-10','Bubble Gum 1500ml','K-02','Pewangi Pakaian',3,32000,96000,'Parfum Pakaian'),('T-0018','inggrid','2019-07-10','Downy daring Bottle 900ml','K-02','Pewangi Pakaian',5,38300,191500,'Perbotol'),('T-0019','inggrid','2019-07-10','Downy Passion 900ml','K-02','Pewangi Pakaian',5,37000,185000,'Perbotol'),('T-0020','inggrid','2019-07-10','Downy Sweet Heart Pink 900ml','K-02','Pewangi Pakaian',5,42800,214000,'Perbotol'),('T-0021','inggrid','2019-07-10','Molto Floral Bliss Blue Pouch 900ml','K-02','Pewangi Pakaian',3,22000,66000,'Perpcs'),('T-0022','inggrid','2019-07-10','Molto Flower Shower Pink 900ml','K-02','Pewangi Pakaian',3,22000,66000,'Perpcs'),('T-0023','inggrid','2019-07-10','Attack Jazz 900gr','K-03','Detergen Pakaian',5,143000,715000,'Perpcs'),('T-0024','inggrid','2019-07-10','Atttack Softener 800gr','K-03','Detergen Pakaian',3,18400,55200,'Perpcs'),('T-0025','inggrid','2019-07-10','Rinso Bubuk 770gr','K-03','Detergen Pakaian',5,18400,92000,''),('T-0026','inggrid','2019-07-10','Rinso Cair 800ml','K-03','Detergen Pakaian',10,18000,180000,'Perbotol'),('T-0027','inggrid','2019-07-10','Detergen Liquid 1500ml','K-03','Detergen Pakaian',5,14000,70000,'Perbotol'),('T-0028','inggrid','2019-07-10','Detergen Matic Grade A','K-03','Detergen Pakaian',3,12000,36000,'Perbotol'),('T-0029','inggrid','2019-07-10','Pelicin Pakaian 1500ml','K-03','Detergen Pakaian',10,12000,120000,'Perbotol'),('T-0030','inggrid','2019-07-10','Shampo Karpet 1500ml','K-03','Detergen Pakaian',5,24000,120000,'Perbotol');
/*!40000 ALTER TABLE `belanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_pelanggan`
--

DROP TABLE IF EXISTS `data_pelanggan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `kontak` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_pelanggan`
--

LOCK TABLES `data_pelanggan` WRITE;
/*!40000 ALTER TABLE `data_pelanggan` DISABLE KEYS */;
INSERT INTO `data_pelanggan` VALUES ('P-04','Syamsinar','Kampung baru Gg Timbul jaya','081234567878'),('P-05','YunaRifana','Kawal','081991878786'),('P-01','InggridEldina','Batu 8 atas','081266268180'),('P-02','SriIndriani','Kota Piring','085264066162'),('P-03','EdiSetiawan','Perum Griya Indonusa Blok M','08112345678');
/*!40000 ALTER TABLE `data_pelanggan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_laundry`
--

DROP TABLE IF EXISTS `detail_laundry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_laundry` (
  `id_trans` int(10) NOT NULL AUTO_INCREMENT,
  `no_trans` varchar(10) NOT NULL,
  `id_jenis` varchar(10) NOT NULL,
  `jenis_cuci` varchar(30) NOT NULL,
  `harga` int(10) NOT NULL,
  `jml` int(10) NOT NULL,
  `total` int(10) NOT NULL,
  `ket` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_trans`)
) ENGINE=MyISAM AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_laundry`
--

LOCK TABLES `detail_laundry` WRITE;
/*!40000 ALTER TABLE `detail_laundry` DISABLE KEYS */;
INSERT INTO `detail_laundry` VALUES (68,'T0000003','C-05','test',10000,1,10000,''),(62,'T0000002','C-01','Cuci+Setrika',6000,2,12000,''),(56,'T0000001','C-03','Karpet',50000,1,50000,''),(64,'T0000002','C-03','Karpet',50000,1,50000,''),(80,'T0000004','C-07','Boneka',30000,1,30000,''),(81,'T0000004','C-05','Campuran',6000,3,18000,''),(82,'T0000004','C-06','BedCover',40000,1,40000,''),(83,'T0000005','C-02','Kaos',6000,2,12000,''),(84,'T0000005','C-04','Campuran',6000,1,6000,''),(85,'T0000005','C-12','Blazer',15000,1,15000,''),(86,'T0000005','C-19','SpreiDoubleSet',20000,2,40000,''),(87,'T0000006','C-06','BajuSafari',10000,2,20000,''),(88,'T0000006','C-08','JaketSafari',25000,1,25000,''),(89,'T0000006','C-09','JaketBiasa',15000,1,15000,''),(90,'T0000006','C-04','Campuran',6000,2,12000,''),(91,'T0000007','C-40','Setrika',6000,5,30000,''),(92,'T0000007','C-04','Campuran',6000,2,12000,''),(93,'T0000008','C-40','Setrika',6000,5,30000,''),(94,'T0000008','C-04','Campuran',6000,2,12000,''),(95,'T0000008','C-02','Kaos',6000,1,6000,''),(96,'T0000009','C-29','BonekaSedang',20000,5,100000,''),(97,'T0000009','C-27','BonekaMini',5000,2,10000,''),(98,'T0000009','C-32','Sepatu',20000,1,20000,''),(99,'T0000010','C-36','KarpetBesar',60000,1,60000,''),(100,'T0000010','C-35','KarpetSedang',45000,1,45000,'');
/*!40000 ALTER TABLE `detail_laundry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jenis`
--

DROP TABLE IF EXISTS `jenis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jenis` (
  `id_jenis` varchar(10) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `harga` int(10) NOT NULL,
  PRIMARY KEY (`id_jenis`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jenis`
--

LOCK TABLES `jenis` WRITE;
/*!40000 ALTER TABLE `jenis` DISABLE KEYS */;
INSERT INTO `jenis` VALUES ('C-01','Blouse',6000),('C-02','Kaos',6000),('C-03','Kemeja',6000),('C-04','Campuran',6000),('C-05','CelanaPanjang',8000),('C-06','BajuSafari',10000),('C-07','SetelanSafari',30000),('C-08','JaketSafari',25000),('C-09','JaketBiasa',15000),('C-10','Jas',30000),('C-11','SetelanJas',35000),('C-12','Blazer',15000),('C-13','SetelanBlazer',20000),('C-14','KainSongket',10000),('C-15','KainUlos',10000),('C-16','SpreiSingle',15000),('C-17','SpreiSingleSet',20000),('C-18','SpreiDouble',15000),('C-19','SpreiDoubleSet',20000),('C-20','Dasi',8000),('C-21','BedCoverSingle',20000),('C-22','BedCoverSingleSet',25000),('C-23','BedCoverDoubleSet',35000),('C-24','TasKecil',10000),('C-25','TasSedang',15000),('C-26','TasBesar',20000),('C-27','BonekaMini',5000),('C-28','BonekaKecil',10000),('C-29','BonekaSedang',20000),('C-30','BonekaBesar',25000),('C-31','BonekaJumbo',45000),('C-32','Sepatu',20000),('C-33','Topi',5000),('C-34','KarpetKecil',30000),('C-35','KarpetSedang',45000),('C-36','KarpetBesar',60000),('C-37','Selimut',20000),('C-38','SelimutBesar',30000),('C-39','Handuk',15000),('C-40','Setrika',6000);
/*!40000 ALTER TABLE `jenis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategori` (
  `id_kat` varchar(10) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  PRIMARY KEY (`id_kat`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategori`
--

LOCK TABLES `kategori` WRITE;
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` VALUES ('K-01','Pemutih Pakaian','Bayclin'),('K-02','Pewangi Pakaian','Molto, Downy'),('K-03','Detergen Pakaian','Rinso, Daia, Attack');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksi` (
  `no_trans` varchar(10) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `pelanggan` varchar(30) NOT NULL,
  `pengerjaan` varchar(20) NOT NULL,
  `tgl_selesai` date NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `biaya_laundry` int(10) DEFAULT NULL,
  `bayar` int(10) DEFAULT NULL,
  `kembalian` int(10) DEFAULT NULL,
  PRIMARY KEY (`no_trans`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES ('T0000002','2019-05-20','INDRO','Diantar','2019-05-18','CANCEL',54000,0,0),('T0000003','2019-05-20','DONO','Diantar','2019-05-18','Selesai',10000,10000,0),('T0000001','2019-05-19','DONO','Diantar','2019-05-18','Selesai',50000,50000,0),('T0000004','2019-07-03','SRIINDRIANI','Diambil','2019-06-30','Selesai',88000,90000,2000),('T0000005','2019-07-10','SriIndriani','Diambil','2019-07-10','Selesai',73000,80000,7000),('T0000006','2019-07-11','EdiSetiawan','Diantar','2019-07-10','Proses',72000,0,0),('T0000007','2019-07-10','InggridEldina','Diantar','2019-07-10','CANCEL',0,0,0),('T0000008','2019-07-11','InggridEldina','Diantar','2019-07-10','Selesai',48000,50000,2000),('T0000009','2019-07-11','YunaRifana','Diantar','2019-07-11','Proses',130000,0,0),('T0000010','2019-07-10','Syamsinar','Diambil','2019-07-10','Proses',105000,0,0);
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_manage`
--

DROP TABLE IF EXISTS `user_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_manage` (
  `id_user` varchar(6) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `pelanggan` int(1) NOT NULL,
  `jenis` int(1) NOT NULL,
  `belanja` int(1) NOT NULL,
  `transaksi` int(1) NOT NULL,
  `laporan` int(1) NOT NULL,
  `utility` int(1) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_manage`
--

LOCK TABLES `user_manage` WRITE;
/*!40000 ALTER TABLE `user_manage` DISABLE KEYS */;
INSERT INTO `user_manage` VALUES ('U01','rere','827ccb0eea8a706c4c34a16891f84e7b',1,1,0,0,1,0),('U02','admin','21232f297a57a5a743894a0e4a801fc3',1,1,1,1,1,1),('U03','didit','827ccb0eea8a706c4c34a16891f84e7b',1,1,1,1,0,0),('U04','Inggrid','827ccb0eea8a706c4c34a16891f84e7b',1,1,1,1,1,0);
/*!40000 ALTER TABLE `user_manage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-10 23:12:06
