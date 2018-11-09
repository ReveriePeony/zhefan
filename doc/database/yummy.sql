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
  `tel` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0-注销 1-正常',
  `role_id` int(11) COMMENT '角色ID',
  `role_name` varchar(20) COLLATE utf8mb4_bin COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '商家(后台)账号';

-- ----------------------------
-- Records of t_gerent
-- ----------------------------
INSERT INTO `t_gerent` VALUES ('1000', 'admin', '/defaultHead.jpg', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-10-30 09:01:51', '110', 'admin', '1', '1', '1000', '管理员');

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
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(11) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '角色';

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(11) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名',
  `menu_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '菜单类型 0-菜单 1-URL',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '菜单(URL)';

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '菜单(URL)';

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
  `shop_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '店铺名称',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `longitude` double(6,3) COMMENT '经度',
  `latitude` double(6,3) COMMENT '纬度',
  `shop_img` varchar(20) COLLATE utf8mb4_bin COMMENT '图片',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-注销 1-正常',
  `serial_no` int(3) NOT NULL DEFAULT '0' COMMENT '序号',
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
  `dishes_class_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '菜品类型名称',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-注销 1-正常',
  `serial_no` int(3) NOT NULL DEFAULT '0' COMMENT '序号',
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
  `dishes_name` varchar(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '菜品名称',
  `dishes_img` varchar(500) COLLATE utf8mb4_bin COMMENT '菜品图片',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-下架 1-上架',
  `sold_out` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否售罄 0-false 1-true',
  `serial_no` int(3) NOT NULL DEFAULT '0' COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '菜品';

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `order_number` varchar(30) COLLATE utf8mb4_bin NOT NULL DEFAULT 'NON' COMMENT '订单号',
  `price` double(8,2) DEFAULT '0.0' COMMENT '价格',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '(下单)创建时间',
  `process_time` varchar(20) COLLATE utf8mb4_bin COMMENT '状态时间',
  `book` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '下单者名称',
  `book_id` int(11) NOT NULL COMMENT '下单者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-待取餐 1-已取消 2-已完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '订单';

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `count` int(5) NOT NULL DEFAULT '1' COMMENT '数量',
  `price` double(8,2) DEFAULT '0.0' COMMENT '单价',
  `dishes_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '菜品名称',
  `area_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '区域',
  `table_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '桌号',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '(下单)创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '订单详情';

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `area_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '区域名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '区域';


-- ----------------------------
-- Table structure for t_table_type
-- ----------------------------
DROP TABLE IF EXISTS `t_table_type`;
CREATE TABLE `t_table_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `table_type_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '餐桌类型名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '餐桌类型';


-- ----------------------------
-- Table structure for t_table
-- ----------------------------
DROP TABLE IF EXISTS `t_table`;
CREATE TABLE `t_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `table_type_id` int(11) NOT NULL COMMENT '餐桌类型ID',
  `area_id` int(11) NOT NULL COMMENT '区域ID',
  `table_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT 'NON' COMMENT '餐桌名称(桌号)',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '餐桌';




















