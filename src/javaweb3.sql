/*
Navicat MySQL Data Transfer

Source Server         : users
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : javaweb3

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2019-09-18 17:28:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for loginmember
-- ----------------------------
DROP TABLE IF EXISTS `loginmember`;
CREATE TABLE `loginmember` (
  `id` varchar(26) NOT NULL,
  `name` varchar(26) NOT NULL,
  `sex` varchar(26) NOT NULL,
  `joinTime` varchar(26) NOT NULL,
  `work` varchar(25) NOT NULL,
  `birthday` varchar(26) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `phone` varchar(25) NOT NULL,
  `signature` text,
  `password` varchar(25) NOT NULL,
  `admin` varchar(7) NOT NULL,
  PRIMARY KEY (`id`,`name`,`admin`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginmember
-- ----------------------------
INSERT INTO `loginmember` VALUES ('88995', '瑞瑞', '0', '2019-08-01', '0', '', '计科', '15555555436', '\r\n\r\n    ddddddddddd', '888', '普通成员');
INSERT INTO `loginmember` VALUES ('889959', '瑞瑞', '0', '2019-08-01', '0', '', '计科', '15555555436', '\r\n\r\n    ddddddddddd', '888', '普通成员');
INSERT INTO `loginmember` VALUES ('8899593', '瑞瑞', '0', '2019-08-01', '0', '', '计科', '15555555436', '\r\n\r\n    ddddddddddd', '888', '普通成员');
INSERT INTO `loginmember` VALUES ('8999593', '瑞瑞', '0', '2019-08-01', '0', '', '计科', '15555555436', '\r\n\r\n    ddddddddddd', '888', '普通成员');
INSERT INTO `loginmember` VALUES ('899959388', '123', '0', '', '0', '', '计科', '15555555436', '\r\n\r\n    7888', '20', '普通成员');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` varchar(26) NOT NULL,
  `name` varchar(26) NOT NULL,
  `sex` varchar(26) NOT NULL,
  `joinTime` varchar(26) NOT NULL,
  `work` varchar(25) NOT NULL,
  `birthday` varchar(26) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `phone` varchar(25) NOT NULL,
  `signature` text,
  `password` varchar(25) NOT NULL,
  `admin` varchar(7) NOT NULL,
  PRIMARY KEY (`id`,`work`,`name`,`sex`,`phone`,`admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('181543430', '林东', '1', '2019-08-08', '1', '2019-08-08', '计科4', '13650707836', '                我爱学习\r\nffffffffffffffffffff55555555577777777777777777\r\n    \r\n\r\n    ', '99fff', '管理员');
INSERT INTO `member` VALUES ('181543444', '美女', '1', '2019-08-08', '1', '2019-08-08', '高数', '13650808635', '123', '888', '');
INSERT INTO `member` VALUES ('55555555', '嘎嘎嘎嘎嘎过过过', '0', '2019-08-08', '2', '2019-08-08', '互金', '13651478965', '        \r\n\r\n    嘎嘎嘎嘎嘎gggg过过过过过过过过过\r\n\r\n    ', '55555', '');
INSERT INTO `member` VALUES ('777', 'dadagui', '0', '2019-08-08', '0', '2019-08-22', '计科', '15555555436', '\r\n\r\n    ffffffffffffffffffffffffffffffffffffff', '777', '');
INSERT INTO `member` VALUES ('7770000', '瑞瑞', '0', '2019-08-01', '0', '2019-08-08', '计科', '15555555436', '\r\n\r\n    ', '55555', '');
INSERT INTO `member` VALUES ('77755', '123', '0', '2019-08-08', '0', '2019-08-08', '计科', '15555555436', '\r\n\r\n    fffffffffff', '20', '');
INSERT INTO `member` VALUES ('8899', '瑞瑞', '0', '2019-08-01', '0', '', '计科', '15555555436', '\r\n\r\n    ddddddddddd', '888', '普通成员');
INSERT INTO `member` VALUES ('89999', '瑞瑞', '0', '2019-08-01', '0', '', '计科', '15555555436', '\r\n\r\n    ddddddddddd', '888', '普通成员');
INSERT INTO `member` VALUES ('admin', '林洋锐', '1', '2019-08-06', '2', null, '计科', '13650707836', '滴滴滴', 'admin', '管理员');

-- ----------------------------
-- Table structure for tes
-- ----------------------------
DROP TABLE IF EXISTS `tes`;
CREATE TABLE `tes` (
  `name` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tes
-- ----------------------------
INSERT INTO `tes` VALUES ('我好', '??');
INSERT INTO `tes` VALUES ('你好', '??');
INSERT INTO `tes` VALUES ('我草你还', '扫]]]地');
INSERT INTO `tes` VALUES ('444', '扫]]]地');
INSERT INTO `tes` VALUES ('dd', 'dd');
INSERT INTO `tes` VALUES ('ffuck', 'ffck');
