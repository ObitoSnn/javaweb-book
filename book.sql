SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `sales` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `img_path` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('1', 'java从入门到放弃', '迪迦', '80.00', '10008', '98', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('2', '数据结构与算法', '迪迦', '78.50', '9', '98', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('3', '怎样拐跑别人的媳妇', '迪迦', '68.00', '100003', '48', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('4', '木虚肉盖饭', '迪迦', '16.00', '1002', '48', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('5', 'C++编程思想', '迪迦', '45.50', '14', '95', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('6', '蛋炒饭', '迪迦', '9.90', '12', '53', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('7', '赌神', '迪迦', '66.50', '125', '535', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('8', 'Java编程思想', '迪迦', '99.50', '47', '36', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('9', 'JavaScript从入门到精通', '迪迦', '9.90', '85', '95', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('10', 'cocos2d-x游戏编程入门', '迪迦', '49.00', '52', '62', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('11', 'C语言程序设计', '迪迦', '28.00', '52', '74', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('12', 'Lua语言程序设计', '迪迦', '51.50', '48', '82', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('13', '西游记', '迪迦', '12.00', '20', '9998', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('14', '水浒传', '迪迦', '33.05', '22', '88', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('15', '操作系统原理', '迪迦', '133.05', '122', '188', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('16', '数据结构 java版', '迪迦', '173.15', '21', '81', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('17', 'UNIX高级环境编程', '迪迦', '99.15', '210', '810', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('18', 'javaScript高级编程', '迪迦', '69.15', '210', '810', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('19', '大话设计模式', '迪迦', '89.15', '20', '99', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('20', '人月神话', '迪迦', '88.15', '22', '78', 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` varchar(50) NOT NULL,
  `total_price` decimal(11,2) NOT NULL,
  `status` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `total_price` decimal(11,2) NOT NULL,
  `order_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', 'admin@gmail.com');
INSERT INTO `t_user` VALUES ('2', 'root', '123', 'root@gmail.com');