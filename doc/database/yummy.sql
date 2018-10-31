/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : yummy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-10-30 09:03:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_gerent
-- ----------------------------
DROP TABLE IF EXISTS `t_gerent`;
CREATE TABLE `t_gerent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '名称',
  `avatar` varchar(500) COLLATE utf8mb4_bin NOT NULL DEFAULT '/defaultHead.jpg' COMMENT '头像',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'E10ADC3949BA59ABBE56E057F20F883E' COMMENT '密码',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0-注销 1-正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '商家(后台)账号';

-- ----------------------------
-- Records of t_gerent
-- ----------------------------
INSERT INTO `t_gerent` VALUES ('1000', 'admin', '/defaultHead.jpg', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-10-30 09:01:51', 'admin', '1', '1');

-- ----------------------------
-- Table structure for t_weixin_business
-- ----------------------------
DROP TABLE IF EXISTS `t_weixin_business`;
CREATE TABLE `t_weixin_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `app_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信appid',
  `app_secret` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信密钥',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '商家(后台)账号 - 微信信息';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '名称',
  `avatar` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'E10ADC3949BA59ABBE56E057F20F883E' COMMENT '密码',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0-注销 1-正常',
  `openid` varchar(100) NOT NULL UNIQUE COMMENT '微信授权信息 用户的唯一标识',
  `nickname` varchar(50) NOT NULL COMMENT '微信授权信息 用户昵称',
  `sex` tinyint(1) NOT NULL COMMENT '微信授权信息 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `province` varchar(20) NOT NULL COMMENT '微信授权信息 用户填写的省份',
  `city` varchar(20) NOT NULL COMMENT '微信授权信息 用户填写的城市',
  `country` varchar(20) NOT NULL COMMENT '微信授权信息 国家',
  `headimgurl` varchar(500) NOT NULL COMMENT '微信授权信息 用户头像',
  `privilege` varchar(500) NOT NULL COMMENT '微信授权信息 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户';

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `shop_name` varchar(20) COLLATE utf8mb4_bin NOT NULL UNIQUE DEFAULT '无名' COMMENT '店铺名称',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `longitude` double(6,3) COMMENT '经度',
  `latitude` double(6,3) COMMENT '纬度',
  `shop_img` varchar(20) COLLATE utf8mb4_bin COMMENT '图片',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-注销 1-正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '店铺';

-- ----------------------------
-- Table structure for t_dishes_class
-- ----------------------------
DROP TABLE IF EXISTS `t_dishes_class`;
CREATE TABLE `t_dishes_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `dishes_class_name` varchar(20) COLLATE utf8mb4_bin NOT NULL UNIQUE DEFAULT '无名' COMMENT '菜品类型名称',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-注销 1-正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '菜品类型';

-- ----------------------------
-- Table structure for t_dishes
-- ----------------------------
DROP TABLE IF EXISTS `t_dishes`;
CREATE TABLE `t_dishes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `dishes_class_id` int(11) NOT NULL COMMENT '菜品类型ID',
  `dishes_name` varchar(20) COLLATE utf8mb4_bin NOT NULL UNIQUE DEFAULT '无名' COMMENT '菜品名称',
  `dishes_img` varchar(500) COLLATE utf8mb4_bin COMMENT '菜品图片',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-下架 1-上架',
  `sold_out` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否售罄 0-false 1-true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '菜品';








