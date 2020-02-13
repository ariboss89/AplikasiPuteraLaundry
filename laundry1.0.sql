# Host: localhost  (Version: 5.5.8)
# Date: 2020-02-05 14:00:17
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "belanja"
#

DROP TABLE IF EXISTS `belanja`;
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

#
# Data for table "belanja"
#

/*!40000 ALTER TABLE `belanja` DISABLE KEYS */;
INSERT INTO `belanja` VALUES ('S001','2019-11-20','Rinso Cair','Gaji Karyawan','20','1000','20000');
/*!40000 ALTER TABLE `belanja` ENABLE KEYS */;

#
# Structure for table "data_pelanggan"
#

DROP TABLE IF EXISTS `data_pelanggan`;
CREATE TABLE `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `kontak` varchar(50) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "data_pelanggan"
#

/*!40000 ALTER TABLE `data_pelanggan` DISABLE KEYS */;
INSERT INTO `data_pelanggan` VALUES ('P-01','DONO','TES','123131231','Member'),('P-02','INDRO','YES','164643453234','Non Member'),('P-03','ANDRE','Oi','01910102','Non Member');
/*!40000 ALTER TABLE `data_pelanggan` ENABLE KEYS */;

#
# Structure for table "jenis"
#

DROP TABLE IF EXISTS `jenis`;
CREATE TABLE `jenis` (
  `id_jenis` varchar(10) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `harga` int(10) NOT NULL,
  PRIMARY KEY (`id_jenis`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "jenis"
#

/*!40000 ALTER TABLE `jenis` DISABLE KEYS */;
INSERT INTO `jenis` VALUES ('C-01','Cuci+Setrika',6000),('C-02','Setrika',4000),('C-03','Karpet',50000),('C-04','hgh',2237),('C-05','test',10000),('C-06','Paket Hemat 1 25 Kg',157500),('C-07','Paket Hemat 2 45 Kg',285500),('C-08','Paket Hemat 3 65 Kg',409500);
/*!40000 ALTER TABLE `jenis` ENABLE KEYS */;

#
# Structure for table "kategori"
#

DROP TABLE IF EXISTS `kategori`;
CREATE TABLE `kategori` (
  `id_kat` varchar(10) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  PRIMARY KEY (`id_kat`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "kategori"
#

/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` VALUES ('K-01','Gaji Karyawan','1'),('K-02','Detergen',''),('K-03','Pewangi','');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;

#
# Structure for table "pembayaran"
#

DROP TABLE IF EXISTS `pembayaran`;
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

#
# Data for table "pembayaran"
#

/*!40000 ALTER TABLE `pembayaran` DISABLE KEYS */;
/*!40000 ALTER TABLE `pembayaran` ENABLE KEYS */;

#
# Structure for table "pembayaranku"
#

DROP TABLE IF EXISTS `pembayaranku`;
CREATE TABLE `pembayaranku` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `idriwayattransaksi` varchar(10) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `total` varchar(255) DEFAULT NULL,
  `bayar` varchar(255) DEFAULT NULL,
  `kembalian` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

#
# Data for table "pembayaranku"
#

/*!40000 ALTER TABLE `pembayaranku` DISABLE KEYS */;
INSERT INTO `pembayaranku` VALUES (13,'DT-01','2020-02-05','27000','27000','0','SELESAI');
/*!40000 ALTER TABLE `pembayaranku` ENABLE KEYS */;

#
# Structure for table "stok"
#

DROP TABLE IF EXISTS `stok`;
CREATE TABLE `stok` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `nama` varchar(255) DEFAULT NULL,
  `kategori` varchar(255) DEFAULT NULL,
  `satuan` varchar(255) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `minqty` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "stok"
#

INSERT INTO `stok` VALUES ('B001','Rinso Cair','Sabun','SACHET',1,20),('B002','Molto Sakura','Pewangi','SACHET',21,20),('B003','Boom','Gaji Karyawan','SACHET',120,20);

#
# Structure for table "transaksi"
#

DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `jumlahtransaksi` int(11) DEFAULT NULL,
  `totaltransaksi` varchar(255) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "transaksi"
#

/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES ('DT-01',1,'27000','2020-02-05','SELESAI');
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;

#
# Structure for table "transaksiku"
#

DROP TABLE IF EXISTS `transaksiku`;
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

#
# Data for table "transaksiku"
#

/*!40000 ALTER TABLE `transaksiku` DISABLE KEYS */;
INSERT INTO `transaksiku` VALUES ('T0000001','DT-01','DONO','Cuci+Setrika','5','30000','2020-02-04','2020-02-05','Molto Sakura','Diantar',27000,'SELESAI');
/*!40000 ALTER TABLE `transaksiku` ENABLE KEYS */;

#
# Structure for table "user_manage"
#

DROP TABLE IF EXISTS `user_manage`;
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

#
# Data for table "user_manage"
#

/*!40000 ALTER TABLE `user_manage` DISABLE KEYS */;
INSERT INTO `user_manage` VALUES ('U01','rere','827ccb0eea8a706c4c34a16891f84e7b',1,1,0,0,1,0),('U02','admin','21232f297a57a5a743894a0e4a801fc3',1,1,1,1,1,1),('U03','didit','827ccb0eea8a706c4c34a16891f84e7b',1,1,1,1,0,0);
/*!40000 ALTER TABLE `user_manage` ENABLE KEYS */;
