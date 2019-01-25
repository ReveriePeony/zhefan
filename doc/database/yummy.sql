/*
Navicat MySQL Data Transfer

Source Server         : 47.107.248.96
Source Server Version : 100137
Source Host           : 47.107.248.96:3306
Source Database       : yummy

Target Server Type    : MYSQL
Target Server Version : 100137
File Encoding         : 65001

Date: 2019-01-25 10:18:33
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='区域';

-- ----------------------------
-- Records of t_area
-- ----------------------------

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
  `dishes_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜品图片',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-下架 1-上架',
  `sold_out` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否售罄 0-false 1-true',
  `serial_no` int(3) NOT NULL DEFAULT '0' COMMENT '序号',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `dishes_price` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '菜价格',
  `recommend` tinyint(1) DEFAULT '0' COMMENT '推荐(1 = true，0 = false)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜品';

-- ----------------------------
-- Records of t_dishes
-- ----------------------------
INSERT INTO `t_dishes` VALUES ('1007', '1000', '1000', '1007', '辣子鸡', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730384347_y.jpg', '2018-12-25 17:33:04', '管理员', '1000', '1', '1', '0', null, '120.00', '0');
INSERT INTO `t_dishes` VALUES ('1008', '1000', '1000', '1007', '麻婆豆腐', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730398846_y.jpg', '2018-12-25 17:33:18', '管理员', '1000', '1', '1', '0', null, '155.00', '0');
INSERT INTO `t_dishes` VALUES ('1009', '1000', '1000', '1008', '酸菜鱼', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730475355_y.jpg', '2018-12-25 17:34:35', '管理员', '1000', '1', '0', '0', null, '55.00', '0');
INSERT INTO `t_dishes` VALUES ('1010', '1000', '1000', '1009', '番茄炒蛋', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730524375_y.jpg', '2018-12-25 17:35:24', '管理员', '1000', '1', '0', '0', null, '120.00', '0');
INSERT INTO `t_dishes` VALUES ('1011', '1000', '1000', '1010', '东北饺子', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730535774_y.jpg', '2018-12-25 17:35:35', '管理员', '1000', '1', '0', '0', null, '44.00', '0');
INSERT INTO `t_dishes` VALUES ('1012', '1000', '1000', '1011', '猪鞭炒牛鞭', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730558889_y.jpg', '2018-12-25 17:35:58', '管理员', '1000', '1', '0', '0', null, '135.00', '0');
INSERT INTO `t_dishes` VALUES ('1014', '1000', '1000', '1013', '红枣炖猪蹄', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545730584385_y.jpg', '2018-12-25 17:36:24', '管理员', '1000', '1', '0', '0', null, '23.00', '0');
INSERT INTO `t_dishes` VALUES ('1015', '1000', '1000', '1007', '川菜4', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546077514711_y.jpg', '2018-12-29 17:58:34', '管理员', '1000', '1', '0', '0', null, '156.00', '0');

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
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1022 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜品类型';

-- ----------------------------
-- Records of t_dishes_class
-- ----------------------------
INSERT INTO `t_dishes_class` VALUES ('1007', '1000', '1000', '川菜', '2018-12-19 17:09:34', '管理员', '1000', '1', '1', null);
INSERT INTO `t_dishes_class` VALUES ('1008', '1000', '1000', '湘菜', '2018-12-19 17:09:43', '管理员', '1000', '1', '2', null);
INSERT INTO `t_dishes_class` VALUES ('1009', '1000', '1000', '粤菜', '2018-12-19 17:09:51', '管理员', '1000', '1', '3', null);
INSERT INTO `t_dishes_class` VALUES ('1010', '1000', '1000', '东北菜', '2018-12-19 17:10:02', '管理员', '1000', '1', '4', null);
INSERT INTO `t_dishes_class` VALUES ('1011', '1000', '1000', '小胖买的菜', '2018-12-19 17:10:10', '管理员', '1000', '1', '5', null);
INSERT INTO `t_dishes_class` VALUES ('1012', '1000', '1000', '曾总的菜', '2018-12-19 17:10:21', '管理员', '1000', '1', '6', null);
INSERT INTO `t_dishes_class` VALUES ('1013', '1000', '1000', '女生菜', '2018-12-19 17:10:43', '管理员', '1000', '1', '7', null);
INSERT INTO `t_dishes_class` VALUES ('1021', '1000', '1000', '云南菜', '2018-12-29 15:30:27', '管理员', '1000', '1', '8', '云南菜');

-- ----------------------------
-- Table structure for t_gerent
-- ----------------------------
DROP TABLE IF EXISTS `t_gerent`;
CREATE TABLE `t_gerent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT 'NON' COMMENT '名称',
  `nick` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '昵称',
  `avatar` varchar(500) COLLATE utf8mb4_bin NOT NULL DEFAULT '/defaultHead.jpg' COMMENT '头像',
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT 'E10ADC3949BA59ABBE56E057F20F883E' COMMENT '密码',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `tel` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0-注销 1-正常',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `role_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_gerent_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商家(后台)账号';

-- ----------------------------
-- Records of t_gerent
-- ----------------------------
INSERT INTO `t_gerent` VALUES ('1000', 'admin', '管理员', '/defaultHead.jpg', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-10-30 09:01:51', '110', 'admin', '1', '1', '1000', '管理员');
INSERT INTO `t_gerent` VALUES ('1001', 'peony', '牡丹', '/defaultHead.jpg', 'E10ADC3949BA59ABBE56E057F20F883E', '2018-12-30 09:01:51', '120', '', '0', '1', '1000', '管理员');

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
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单(URL)';

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `order_number` varchar(30) COLLATE utf8mb4_bin NOT NULL DEFAULT 'NON' COMMENT '订单号',
  `price` double(10,2) DEFAULT '0.00' COMMENT '价格',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '(下单)创建时间',
  `process_time` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态时间',
  `book` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '下单者名称',
  `book_id` int(11) NOT NULL COMMENT '下单者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-待取餐 1-已取消 2-已完成',
  `area_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '区域',
  `table_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '桌号',
  `being` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-false，1-true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `count` int(5) NOT NULL DEFAULT '1' COMMENT '数量',
  `price` double(10,2) DEFAULT '0.00' COMMENT '价格',
  `dishes_type_name` varchar(20) COLLATE utf8mb4_bin DEFAULT '未知' COMMENT '菜品类型名称',
  `dishes_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '菜品名称',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '(下单)创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单详情';

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单(URL)';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gerent_id` int(11) NOT NULL COMMENT '商家(后台)账号ID',
  `shop_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '店铺名称',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `longitude` double(6,3) DEFAULT NULL COMMENT '经度',
  `latitude` double(6,3) DEFAULT NULL COMMENT '纬度',
  `shop_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `creator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态 0-注销 1-正常',
  `serial_no` int(3) NOT NULL DEFAULT '0' COMMENT '序号',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `shop_tel` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺手机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺';

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES ('1000', '1000', '测试店', '广东省惠州市惠城区桥东街道东湖三街88-39号东湖花园第1区', '114.427', '23.081', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546482249682_y.jpg', '2018-12-18 16:20:39', '管理员', '1000', '1', '0', '备注', '18826243088');
INSERT INTO `t_shop` VALUES ('1001', '1000', '伟秋的家', '广东省惠州市龙门县西林路165号(近百担二路)丘奀草药凉茶(百担二路)', '0.000', '0.000', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546482399519_y.jpg', '2018-12-18 16:25:13', '管理员', '1000', '1', '0', '备注', '18826243055');
INSERT INTO `t_shop` VALUES ('1002', '1000', '测试店1', '地狱', '0.000', '0.000', 'uploadupload/a9b7ba70783b617e9998dc4dd82eb3c5/1545122377427_y.jpg', '2018-12-18 16:40:44', '管理员', '1000', '0', '1', '备注', null);
INSERT INTO `t_shop` VALUES ('1003', '1000', '测试店1', '地狱', '0.000', '0.000', 'uploadupload/a9b7ba70783b617e9998dc4dd82eb3c5/1545122797778_y.jpg', '2018-12-18 16:47:47', '管理员', '1000', '0', '1', '备注', null);
INSERT INTO `t_shop` VALUES ('1004', '1000', '测试店5', '广东省惠州市惠城区桥东街道东湖三街88-39号东湖花园第1区', '114.427', '23.081', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546496837952_y.jpg', '2018-12-18 16:48:05', '管理员', '1000', '1', '1', '备注', '18824793246');
INSERT INTO `t_shop` VALUES ('1005', '1000', '测试店6', '地狱', '0.000', '0.000', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545122377427_y.jpg', '2018-12-18 16:48:35', '管理员', '1000', '0', '1', '备注', null);
INSERT INTO `t_shop` VALUES ('1006', '1000', '测试店6', '地狱', '0.000', '0.000', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1545122377427_y.jpg', '2018-12-18 16:48:52', '管理员', '1000', '0', '1', '备注', null);
INSERT INTO `t_shop` VALUES ('1007', '1000', '测试1', '广东省惠州市惠城区桥东街道东湖三街88-39号东湖花园第1区', '114.427', '23.081', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546496186630_y.jpg', '2019-01-03 14:16:26', '管理员', '1000', '0', '0', '点点滴滴', '18826243154');
INSERT INTO `t_shop` VALUES ('1008', '1000', '阿达说的', '广东省惠州市惠城区桥东街道东湖三街88-39号东湖花园第1区', '114.427', '23.081', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546496233357_y.jpg', '2019-01-03 14:17:13', '管理员', '1000', '0', '0', '123', '18826243055');
INSERT INTO `t_shop` VALUES ('1009', '1000', '测试', '北京市北京市东城区建国门内大街9号北京国际饭店', null, null, 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546671434010_y.jpg', '2019-01-05 14:57:14', '管理员', '1000', '1', '0', null, '18819486286');
INSERT INTO `t_shop` VALUES ('1010', '1000', '测试数据', '北京市北京市东城区建国门内大街9号北京国际饭店', null, null, 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546671817884_y.jpg', '2019-01-05 15:03:38', '管理员', '1000', '1', '0', null, '18819478658');
INSERT INTO `t_shop` VALUES ('1011', '1000', '测试1', '北京市北京市东城区建国门内大街9号北京国际饭店', '116.429', '39.910', 'upload/a9b7ba70783b617e9998dc4dd82eb3c5/1546671935801_y.jpg', '2019-01-05 15:05:36', '管理员', '1000', '1', '0', null, '18819478658');

-- ----------------------------
-- Table structure for t_table
-- ----------------------------
DROP TABLE IF EXISTS `t_table`;
CREATE TABLE `t_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL COMMENT '商家(后台)店铺ID',
  `table_type_id` int(11) NOT NULL COMMENT '餐桌类型ID',
  `area_id` int(11) DEFAULT NULL COMMENT '区域ID',
  `table_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT 'NON' COMMENT '餐桌名称(桌号)',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='餐桌';

-- ----------------------------
-- Records of t_table
-- ----------------------------
INSERT INTO `t_table` VALUES ('1002', '1000', '1000', null, '3', '1000', '2018-12-28 15:15:45', null);
INSERT INTO `t_table` VALUES ('1003', '1000', '1000', null, '3', '1000', '2018-12-28 15:16:17', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='餐桌类型';

-- ----------------------------
-- Records of t_table_type
-- ----------------------------
INSERT INTO `t_table_type` VALUES ('1000', '1000', '母鸡', '1000', '2018-11-29 11:01:02');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '无名' COMMENT '名称',
  `avatar` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT 'E10ADC3949BA59ABBE56E057F20F883E' COMMENT '密码',
  `creation_time` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0-注销 1-正常',
  `openid` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 用户的唯一标识',
  `nickname` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 用户昵称',
  `sex` tinyint(1) NOT NULL COMMENT '微信授权信息 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `province` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 用户填写的省份',
  `city` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 用户填写的城市',
  `country` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 国家',
  `headimgurl` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 用户头像',
  `privilege` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '微信授权信息 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商家(后台)账号 - 微信信息';

-- ----------------------------
-- Records of t_weixin_business
-- ----------------------------
