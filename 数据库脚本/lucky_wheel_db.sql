/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : lucky_wheel_db

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 06/29/2017 22:14:42 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_prize`
-- ----------------------------
DROP TABLE IF EXISTS `tb_prize`;
CREATE TABLE `tb_prize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isdel` int(1) DEFAULT '0',
  `prizeName` varchar(100) DEFAULT NULL,
  `isUse` int(1) DEFAULT '0',
  `prizeRate` double(10,3) DEFAULT NULL,
  `prizePic` varchar(50) DEFAULT NULL,
  `storeId` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT '1',
  `type` int(1) DEFAULT '0' COMMENT '0其他 1 红包 2积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_qr`
-- ----------------------------
DROP TABLE IF EXISTS `tb_qr`;
CREATE TABLE `tb_qr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storeId` int(11) DEFAULT NULL,
  `isFailure` int(1) DEFAULT '0' COMMENT '是否失效 0 未失效 1 已经失效',
  `url` varchar(100) DEFAULT NULL,
  `file_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recordCode` varchar(50) DEFAULT NULL,
  `prizeId` int(11) DEFAULT NULL,
  `state` int(1) DEFAULT '0' COMMENT '0未兑换，1已兑换',
  `userId` int(11) DEFAULT NULL,
  `crateTime` varchar(50) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_store`
-- ----------------------------
DROP TABLE IF EXISTS `tb_store`;
CREATE TABLE `tb_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appID` varchar(100) DEFAULT NULL,
  `key` varchar(100) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `storeName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `isdel` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isdel` int(1) DEFAULT '0',
  `openId` varchar(50) DEFAULT NULL,
  `redEnvelope` int(11) DEFAULT '0',
  `integral` int(11) DEFAULT '0',
  `name` varchar(11) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `nikeName` varchar(100) DEFAULT NULL,
  `headImgUrl` varchar(100) DEFAULT NULL,
  `storeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
