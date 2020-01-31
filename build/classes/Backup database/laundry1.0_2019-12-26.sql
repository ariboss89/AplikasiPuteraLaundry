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
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `id_kat` varchar(255) DEFAULT NULL,
  `kategori` varchar(255) DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  `qty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang`
--

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `belanja`
--

DROP TABLE IF EXISTS `belanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belanja` (
  `Idbelanja` varchar(11) NOT NULL DEFAULT '',
  `tanggal` date DEFAULT '0000-00-00',
  `nama` varchar(255) DEFAULT NULL,
  `kategori` varchar(255) DEFAULT NULL,
  `jumlah` varchar(255) DEFAULT NULL,
  `harga` varchar(255) DEFAULT NULL,
  `total` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Idbelanja`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belanja`
--

LOCK TABLES `belanja` WRITE;
/*!40000 ALTER TABLE `belanja` DISABLE KEYS */;
INSERT INTO `belanja` VALUES ('S001','2019-11-20','Rinso Cair','Gaji Karyawan','20','1000','20000'),('S002','2019-12-26','Attack Powder','Pewangi','20','1000','20000'),('S003','2019-12-26','Bayclin','Detergen','1','20000','20000');
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
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_pelanggan`
--

LOCK TABLES `data_pelanggan` WRITE;
/*!40000 ALTER TABLE `data_pelanggan` DISABLE KEYS */;
INSERT INTO `data_pelanggan` VALUES ('P-04','Fiona','Kota piring','08199187678989','Non Member'),('P-02','INDRO','Batu 8','08199134455443','Non Member'),('P-03','ANDRE','Kota piring','08199123456789','Non Member');
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
INSERT INTO `detail_laundry` VALUES (56,'T0000001','C-03','Karpet',50000,1,50000,''),(62,'T0000002','C-01','Cuci+Setrika',6000,2,12000,''),(64,'T0000002','C-03','Karpet',50000,1,50000,''),(68,'T0000003','C-05','test',10000,1,10000,'');
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
INSERT INTO `jenis` VALUES ('C-01','Cuci+Setrika',6000),('C-02','Setrika',4000),('C-03','Karpet',50000),('C-04','Celana',7000),('C-05','Blues',7000),('C-06','BonekaSedang',30000),('C-07','Jas',20000);
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
INSERT INTO `kategori` VALUES ('K-01','Gaji Karyawan',''),('K-02','Detergen',''),('K-03','Pewangi',''),('K-04','Pemutih Pakaian','');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pembayaran`
--

DROP TABLE IF EXISTS `pembayaran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pembayaran` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `idtransaksi` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `jenis` varchar(255) DEFAULT NULL,
  `jumlah` varchar(255) DEFAULT NULL,
  `totalharga` varchar(255) DEFAULT NULL,
  `tglmasuk` date DEFAULT '0000-00-00',
  `tglselesai` date DEFAULT '0000-00-00',
  `ket` varchar(255) DEFAULT NULL,
  `pengerjaan` varchar(255) DEFAULT NULL,
  `biayalaundry` varchar(255) DEFAULT NULL,
  `statuspembayaran` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pembayaran`
--

LOCK TABLES `pembayaran` WRITE;
/*!40000 ALTER TABLE `pembayaran` DISABLE KEYS */;
/*!40000 ALTER TABLE `pembayaran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pembayaranku`
--

DROP TABLE IF EXISTS `pembayaranku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pembayaranku` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `idriwayattransaksi` varchar(10) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `total` varchar(255) DEFAULT NULL,
  `bayar` varchar(255) DEFAULT NULL,
  `kembalian` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pembayaranku`
--

LOCK TABLES `pembayaranku` WRITE;
/*!40000 ALTER TABLE `pembayaranku` DISABLE KEYS */;
INSERT INTO `pembayaranku` VALUES (3,'DT-01','2019-11-24','30000','50000','20000',NULL),(4,'DT-04','2019-11-24','18000','50000','32000',NULL),(5,'DT-05','2019-11-24','30000','40000','10000','SELESAI'),(6,'DT-06','2019-11-27','30000','50000','20000','SELESAI'),(7,'DT-10','2019-12-05','30000','120000','90000','SELESAI'),(8,'DT-16','2019-12-05','66000','100000','34000','SELESAI'),(9,'DT-15','2019-12-05','30000','50000','20000','SELESAI'),(10,'DT-14','2019-12-05','66000','100000','34000','SELESAI'),(11,'DT-12','2019-12-05','74000','100000','26000','SELESAI'),(12,'DT-13','2019-12-05','54000','60000','6000','SELESAI'),(13,'DT-19','2019-12-27','60000','100000','40000','SELESAI'),(14,'DT-20','2019-12-27','166000','200000','34000','SELESAI'),(15,'DT-22','2019-12-28','54000','60000','6000','SELESAI'),(16,'DT-23','2019-12-27','130000','150000','20000','SELESAI'),(17,'DT-24','2019-12-28','80000','100000','20000','SELESAI');
/*!40000 ALTER TABLE `pembayaranku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stok`
--

DROP TABLE IF EXISTS `stok`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stok` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `nama` varchar(255) DEFAULT NULL,
  `kategori` varchar(255) DEFAULT NULL,
  `satuan` varchar(255) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `minqty` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stok`
--

LOCK TABLES `stok` WRITE;
/*!40000 ALTER TABLE `stok` DISABLE KEYS */;
INSERT INTO `stok` VALUES ('B001','Rinso Cair','Sabun','SACHET',13,20),('B002','Molto Sakura','Pewangi','SACHET',13,20),('B003','Boom','Gaji Karyawan','SACHET',120,20),('B004','Attack Powder','Detergen','SACHET',40,NULL),('B005','Parfum Aqua','Detergen','LITER',1,NULL),('B006','Bayclin','Detergen','LITER',2,NULL);
/*!40000 ALTER TABLE `stok` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksi` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `jumlahtransaksi` int(11) DEFAULT NULL,
  `totaltransaksi` varchar(255) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES ('DT-01',1,'30000','2019-11-24','Selesai'),('DT-02',1,'30000','2019-11-24','Selesai'),('DT-03',1,'30000','2019-11-23','Selesai'),('DT-04',2,'18000','2019-11-24','Selesai'),('DT-05',1,'30000','2019-11-24','Selesai'),('DT-06',2,'0','2019-12-05','BELUM SELESAI'),('DT-07',1,'0','2019-12-05','BELUM SELESAI'),('DT-08',1,'0','2019-12-05','BELUM SELESAI'),('DT-09',5,'0','2019-12-05','BELUM SELESAI'),('DT-10',1,'30000','2019-12-05','SELESAI'),('DT-11',2,'80000','2019-12-05','BELUM SELESAI'),('DT-12',2,'74000','2019-12-05','SELESAI'),('DT-13',2,'54000','2019-12-05','SELESAI'),('DT-14',2,'66000','2019-12-05','SELESAI'),('DT-15',1,'30000','2019-12-05','SELESAI'),('DT-16',2,'66000','2019-12-05','SELESAI'),('DT-18',2,'42000','2019-12-19','BELUM SELESAI'),('DT-19',2,'60000','2019-12-27','SELESAI'),('DT-20',3,'166000','2019-12-27','SELESAI'),('DT-21',3,'106000','2019-12-28','BELUM SELESAI'),('DT-22',2,'54000','2019-12-28','SELESAI'),('DT-23',2,'130000','2019-12-27','SELESAI'),('DT-24',3,'80000','2019-12-28','SELESAI');
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksiku`
--

DROP TABLE IF EXISTS `transaksiku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksiku` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `Iddetail` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `jenis` varchar(255) DEFAULT NULL,
  `jumlah` varchar(255) DEFAULT NULL,
  `totalharga` varchar(255) DEFAULT NULL,
  `tglmasuk` varchar(255) DEFAULT NULL,
  `tglselesai` date DEFAULT '0000-00-00',
  `ket` varchar(255) DEFAULT NULL,
  `pengerjaan` varchar(255) DEFAULT NULL,
  `biayalaundry` int(2) DEFAULT NULL,
  `statuspembayaran` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksiku`
--

LOCK TABLES `transaksiku` WRITE;
/*!40000 ALTER TABLE `transaksiku` DISABLE KEYS */;
INSERT INTO `transaksiku` VALUES ('T0000001','DT-10','INDRO','Cuci+Setrika','5','30000','2019-12-04','2019-12-05','Molto Sakura','Diantar',30000,'SELESAI'),('T0000002','DT-11','INDRO','Cuci+Setrika','4','24000','2019-12-04','2019-12-05','Molto Sakura','Diantar',30000,'BELUM SELESAI'),('T0000003','DT-11','INDRO','Karpet','1','50000','2019-12-04','2019-12-05','Molto Sakura','Diambil',50000,'BELUM SELESAI'),('T0000004','DT-12','INDRO','Cuci+Setrika','3','18000','2019-12-04','2019-12-05','Molto Sakura','Diambil',18000,'SELESAI'),('T0000005','DT-12','INDRO','Karpet','1','50000','2019-12-04','2019-12-05','Molto Sakura','Diantar',56000,'SELESAI'),('T0000006','DT-13','INDRO','Cuci+Setrika','3','18000','2019-12-04','2019-12-05','Molto Sakura','Diantar',24000,'SELESAI'),('T0000007','DT-13','INDRO','Cuci+Setrika','5','30000','2019-12-04','2019-12-05','Molto Sakura','Diantar',30000,'SELESAI'),('T0000008','DT-14','INDRO','Cuci+Setrika','6','36000','2019-12-04','2019-12-05','Molto Sakura','Diantar',36000,'SELESAI'),('T0000009','DT-14','INDRO','Cuci+Setrika','5','30000','2019-12-04','2019-12-05','Molto Sakura','Diantar',30000,'SELESAI'),('T0000010','DT-15','INDRO','Cuci+Setrika','5','30000','2019-12-04','2019-12-05','Molto Sakura','Diantar',30000,'SELESAI'),('T0000011','DT-16','INDRO','Cuci+Setrika','4','24000','2019-12-04','2019-12-05','Molto Sakura','Diantar',30000,'SELESAI'),('T0000012','DT-16','INDRO','Cuci+Setrika','6','36000','2019-12-04','2019-12-05','Molto Sakura','Diantar',36000,'SELESAI'),('T0000013','DT-17','INDRO','Cuci+Setrika','2','12000','2019-12-18','2019-12-19','Molto Sakura','Diantar',18000,'BELUM SELESAI'),('T0000014','DT-18','INDRO','Cuci+Setrika','2','12000','2019-12-18','2019-12-19','Molto Sakura','Diantar',18000,'BELUM SELESAI'),('T0000015','DT-18','INDRO','Cuci+Setrika','3','18000','2019-12-18','2019-12-19','Molto Sakura','Diantar',24000,'BELUM SELESAI'),('T0000016','DT-19','DONO','Cuci+Setrika','5','30000','2019-12-26','2019-12-27','Molto Sakura','Diantar',30000,'SELESAI'),('T0000017','DT-19','DONO','Cuci+Setrika','4','24000','2019-12-26','2019-12-27','Molto Sakura','Diantar',30000,'SELESAI'),('T0000018','DT-20','DONO','Cuci+Setrika','5','30000','2019-12-26','2019-12-27','Molto Sakura','Diantar',30000,'SELESAI'),('T0000019','DT-20','DONO','Karpet','2','100000','2019-12-26','2019-12-27','Molto Sakura','Diantar',106000,'SELESAI'),('T0000020','DT-20','DONO','Cuci+Setrika','4','24000','2019-12-26','2019-12-27','Molto Sakura','Diantar',30000,'SELESAI'),('T0000021','DT-21','ANDRE','Cuci+Setrika','4','24000','2019-12-26','2019-12-28','Molto Sakura','Diantar',30000,'BELUM SELESAI'),('T0000022','DT-21','ANDRE','Karpet','1','50000','2019-12-26','2019-12-28','Molto Sakura','Diantar',56000,'BELUM SELESAI'),('T0000023','DT-21','ANDRE','Blues','2','14000','2019-12-26','2019-12-28','Molto Sakura','Diantar',20000,'BELUM SELESAI'),('T0000024','DT-22','INDRO','Cuci+Setrika','3','18000','2019-12-26','2019-12-28','Molto Sakura','Diantar',24000,'SELESAI'),('T0000025','DT-22','INDRO','Karpet','2','100000','2019-12-26','2019-12-28','Molto Sakura','Diantar',30000,'SELESAI'),('T0000026','DT-23','INDRO','Karpet','2','100000','2019-12-26','2019-12-27','Molto Sakura','Diantar',106000,'SELESAI'),('T0000027','DT-23','INDRO','Cuci+Setrika','3','18000','2019-12-26','2019-12-27','Molto Sakura','Diantar',24000,'SELESAI'),('T0000028','DT-24','Fiona','Cuci+Setrika','3','18000','2019-12-26','2019-12-28','Molto Sakura','Diantar',24000,'SELESAI'),('T0000029','DT-24','Fiona','Blues','2','14000','2019-12-26','2019-12-28','Molto Sakura','Diantar',20000,'SELESAI'),('T0000030','DT-24','Fiona','BonekaSedang','1','30000','2019-12-26','2019-12-28','Molto Sakura','Diantar',36000,'SELESAI');
/*!40000 ALTER TABLE `transaksiku` ENABLE KEYS */;
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

-- Dump completed on 2019-12-26 18:39:43
