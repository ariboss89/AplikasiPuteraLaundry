-- MySQL dump 10.16  Distrib 10.1.40-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: laundry1.0
-- ------------------------------------------------------
-- Server version	10.1.40-MariaDB

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
INSERT INTO `belanja` VALUES ('T-0003','admin','2019-05-17','Kispray','K-02','Detergen',10,5000,50000,''),('T-0004','admin','2019-06-01','Tes01','K-03','Tes03',1,5000,5000,'');
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
INSERT INTO `data_pelanggan` VALUES ('P-02','INDRO','YES','164643453234'),('P-01','DONO','TES','123131231');
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
) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_laundry`
--

LOCK TABLES `detail_laundry` WRITE;
/*!40000 ALTER TABLE `detail_laundry` DISABLE KEYS */;
INSERT INTO `detail_laundry` VALUES (68,'T0000003','C-05','test',10000,1,10000,''),(62,'T0000002','C-01','Cuci+Setrika',6000,2,12000,''),(56,'T0000001','C-03','Karpet',50000,1,50000,''),(64,'T0000002','C-03','Karpet',50000,1,50000,'');
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
INSERT INTO `jenis` VALUES ('C-01','Cuci+Setrika',6000),('C-02','Setrika',4000),('C-03','Karpet',50000),('C-04','hgh',2237),('C-05','test',10000);
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
INSERT INTO `kategori` VALUES ('K-01','Gaji Karyawan',''),('K-02','Detergen','Rinso'),('K-03','Tes03',''),('K-04','Tes04','');
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
INSERT INTO `transaksi` VALUES ('T0000002','2019-05-20','INDRO','Diantar','2019-05-18','CANCEL',54000,0,0),('T0000003','2019-05-20','DONO','Diantar','2019-05-18','Selesai',10000,10000,0),('T0000001','2019-05-19','DONO','Diantar','2019-05-18','Proses',50000,0,0);
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
INSERT INTO `user_manage` VALUES ('U01','rere','827ccb0eea8a706c4c34a16891f84e7b',1,1,0,0,1,0),('U02','admin','21232f297a57a5a743894a0e4a801fc3',1,1,1,1,1,1),('U03','didit','827ccb0eea8a706c4c34a16891f84e7b',1,1,1,1,0,0);
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

-- Dump completed on 2019-06-03 21:07:38
