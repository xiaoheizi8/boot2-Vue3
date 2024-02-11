/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.1
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3307
 Source Schema         : bootshop

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 11/02/2024 17:54:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `delState` smallint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES (1, 'admin', '123456', 0);
INSERT INTO `admin_info` VALUES (2, 'mym', '123456', 0);
INSERT INTO `admin_info` VALUES (3, 'sjj', '123456', 0);
INSERT INTO `admin_info` VALUES (4, 'lxf', '123456', 0);
INSERT INTO `admin_info` VALUES (5, 'ljw', '123456', 0);
INSERT INTO `admin_info` VALUES (6, 'zjc', '123456', 0);
INSERT INTO `admin_info` VALUES (7, '张三', '123123', 0);
INSERT INTO `admin_info` VALUES (8, '张三三', '123456', 0);
INSERT INTO `admin_info` VALUES (9, '李四', '123456', 0);
INSERT INTO `admin_info` VALUES (10, '王五', '123456', 0);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `arId` int(4) NOT NULL AUTO_INCREMENT,
  `aid` int(4) NULL DEFAULT NULL,
  `rid` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`arId`) USING BTREE,
  INDEX `aid`(`aid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `admin_role_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `admin_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 1, 1);
INSERT INTO `admin_role` VALUES (2, 2, 2);
INSERT INTO `admin_role` VALUES (3, 3, 2);
INSERT INTO `admin_role` VALUES (4, 4, 3);
INSERT INTO `admin_role` VALUES (5, 5, 5);
INSERT INTO `admin_role` VALUES (6, 6, 2);
INSERT INTO `admin_role` VALUES (7, 7, 4);
INSERT INTO `admin_role` VALUES (8, 8, 5);
INSERT INTO `admin_role` VALUES (9, 9, 21);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '电脑');
INSERT INTO `category` VALUES (2, '冰箱');
INSERT INTO `category` VALUES (3, '电视机');
INSERT INTO `category` VALUES (4, '洗衣机');
INSERT INTO `category` VALUES (5, '数码相机');
INSERT INTO `category` VALUES (6, 'PAD');
INSERT INTO `category` VALUES (7, '微波炉');
INSERT INTO `category` VALUES (8, '油烟机');
INSERT INTO `category` VALUES (9, '豆浆机');
INSERT INTO `category` VALUES (10, '电磁炉');
INSERT INTO `category` VALUES (11, '电烤炉');
INSERT INTO `category` VALUES (12, 'hello');
INSERT INTO `category` VALUES (13, 'world');
INSERT INTO `category` VALUES (14, 'AA');
INSERT INTO `category` VALUES (15, 'BBB');

-- ----------------------------
-- Table structure for functions
-- ----------------------------
DROP TABLE IF EXISTS `functions`;
CREATE TABLE `functions`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '功能菜单',
  `parentid` int(4) NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `isleaf` bit(1) NULL DEFAULT NULL,
  `nodeorder` int(4) NULL DEFAULT NULL,
  `iconfont` varchar(30) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of functions
-- ----------------------------
INSERT INTO `functions` VALUES (1, '电子商城管理后台', 0, NULL, b'0', 0, NULL);
INSERT INTO `functions` VALUES (2, '商品管理', 1, NULL, b'0', 1, 'iconfont icon-shangpinguanli');
INSERT INTO `functions` VALUES (3, '商品列表', 2, 'goods', b'1', 1, NULL);
INSERT INTO `functions` VALUES (4, '商品类别', 2, 'categorys', b'1', 2, NULL);
INSERT INTO `functions` VALUES (5, '订单管理', 1, NULL, b'0', 2, 'iconfont icon-single');
INSERT INTO `functions` VALUES (6, '订单列表', 5, 'orders', b'1', 1, NULL);
INSERT INTO `functions` VALUES (7, '创建订单', 5, 'createorder', b'1', 2, NULL);
INSERT INTO `functions` VALUES (8, '用户权限管理', 1, NULL, b'0', 3, 'iconfont icon-yonghuguanli');
INSERT INTO `functions` VALUES (9, '前台用户管理', 8, 'frontusers', b'1', 1, NULL);
INSERT INTO `functions` VALUES (10, '后台用户管理', 8, 'backusers', b'1', 2, NULL);
INSERT INTO `functions` VALUES (11, '角色管理', 8, 'roles', b'1', 3, NULL);

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `cid` int(4) NULL DEFAULT NULL COMMENT '商品类别',
  `brand` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品品牌',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `num` int(4) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '商品库存',
  `price` decimal(10, 0) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '商品小图',
  `intro` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品简介',
  `status` int(4) NULL DEFAULT 1 COMMENT '商品状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tid`(`cid`) USING BTREE,
  CONSTRAINT `goods_info_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES (1, '1378538', 'AppleMJVE2CH/A', 1, 'MIUI', '1378538.jpg', 0100, 0000006488, 'XIAOMI Book  13.6英寸笔记本电脑 银色(Core i7 处理器/4GB内存/128GB SSD闪存 MJVE2CH/A)', 1);
INSERT INTO `goods_info` VALUES (2, '1309456', 'ThinkPadE450C(20EH0001CD)', 1, 'ThinkPad', '1309456.jpg', 0097, 0000004199, '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', 1);
INSERT INTO `goods_info` VALUES (3, '1999938', '联想小新300经典版', 1, '联想（Lenovo）', '1999938.jpg', 0099, 0000004399, '联想（Lenovo）小新300经典版 14英寸超薄笔记本电脑（i7-6500U 4G 500G 2G独显 全高清屏 Win10）黑色', 1);
INSERT INTO `goods_info` VALUES (4, '1466274', '华硕FX50JX', 1, '华硕（ASUS）', '1466274.jpg', 0100, 0000004799, '华硕（ASUS）飞行堡垒FX50J 15.6英寸游戏笔记本电脑（i5-4200H 4G 7200转500G GTX950M 2G独显 全高清）', 1);
INSERT INTO `goods_info` VALUES (5, '1981672', '华硕FL5800', 1, '华硕（ASUS）', '1981672.jpg', 0100, 0000004999, '华硕（ASUS）FL5800 15.6英寸笔记本电脑 （i7-5500U 4G 128G SSD+1TB 2G独显 蓝牙 Win10 黑色）', 1);
INSERT INTO `goods_info` VALUES (6, '1904696', '联想G50-70M', 1, '联想（Lenovo）', '1904696.jpg', 0012, 0000003499, '联想（Lenovo）G50-70M 15.6英寸笔记本电脑（i5-4210U 4G 500G GT820M 2G独显 DVD刻录 Win8.1）金属黑', 1);
INSERT INTO `goods_info` VALUES (7, '751624', '美的BCD-206TM(E)', 2, ' 美的（Midea）', '751624.jpg', 0100, 0000001298, '美的(Midea) BCD-206TM(E) 206升 三门冰箱 节能保鲜 闪白银', 1);
INSERT INTO `goods_info` VALUES (8, '977433', '美的BCD-516WKM(E)', 2, ' 美的（Midea）', '977433.jpg', 0100, 0000003199, '美的(Midea) BCD-516WKM(E) 516升 对开门冰箱 风冷无霜 电脑控温 纤薄设计 节能静音 （泰坦银）', 1);
INSERT INTO `goods_info` VALUES (9, '1143562', '海尔BCD-216SDN', 2, ' 海尔（Haier）', '1143562.jpg', 0100, 0000001699, '海尔（Haier）BCD-216SDN 216升 三门冰箱 电脑控温 中门 宽幅变温 大冷冻能力低能耗更省钱', 1);
INSERT INTO `goods_info` VALUES (10, '1560207', '海尔BCD-258WDPM', 2, ' 海尔（Haier）', '1560207.jpg', 0100, 0000002699, '海尔（Haier）BCD-258WDPM 258升 风冷无霜三门冰箱 除菌 3D立体环绕风不风干 中门5℃~-18℃变温室', 1);
INSERT INTO `goods_info` VALUES (11, '1721668', '海信（Hisense）BCD-559WT/Q', 2, ' 海信（Hisense）', '1721668.jpg', 0100, 0000003499, '海信（Hisense）BCD-559WT/Q 559升 金色电脑风冷节能对开门冰箱', 1);
INSERT INTO `goods_info` VALUES (12, '823125', '海信BCD-211TD/E', 2, ' 海信（Hisense）', '823125.jpg', 0100, 0000001699, '海信（Hisense） BCD-211TD/E 211升 电脑三门冰箱 （亮金刚）', 1);
INSERT INTO `goods_info` VALUES (15, '8392822', '西门子510', 2, '西门子', NULL, 0010, 0000004566, '制冷快', 1);
INSERT INTO `goods_info` VALUES (16, '42212237727', '九阳（Joyoung）九阳豆浆机多功能家用豆浆机', 9, '九阳（Joyoung）', NULL, 0500, 0000000519, '九阳（Joyoung）九阳豆浆机多功能家用豆浆机破壁免滤预约1.3L大小容量12345人全自动豆浆机', 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '订单明细id',
  `oid` int(4) NULL DEFAULT NULL COMMENT '订单id',
  `gid` int(4) NULL DEFAULT NULL COMMENT '商品id',
  `quantity` int(4) NULL DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`gid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`gid`) REFERENCES `goods_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (59, 50, 1, 1);
INSERT INTO `order_detail` VALUES (60, 50, 3, 2);
INSERT INTO `order_detail` VALUES (61, 51, 4, 3);
INSERT INTO `order_detail` VALUES (62, 51, 8, 2);
INSERT INTO `order_detail` VALUES (64, 53, 1, 0);
INSERT INTO `order_detail` VALUES (67, 58, 1, 0);
INSERT INTO `order_detail` VALUES (71, 62, 2, 2);
INSERT INTO `order_detail` VALUES (72, 63, 2, 2);
INSERT INTO `order_detail` VALUES (73, 64, 1, 0);
INSERT INTO `order_detail` VALUES (74, 65, 1, 0);
INSERT INTO `order_detail` VALUES (75, 66, 1, 0);
INSERT INTO `order_detail` VALUES (76, 67, 1, 2);
INSERT INTO `order_detail` VALUES (77, 67, 3, 1);
INSERT INTO `order_detail` VALUES (78, 68, 2, 1);
INSERT INTO `order_detail` VALUES (79, 68, 4, 1);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(4) NULL DEFAULT NULL,
  `orderNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordertime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `orderprice` decimal(8, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `order_info_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (50, 3, 'DD202101040150', '已付款', '2021-01-05 00:00:00', 15286.00);
INSERT INTO `order_info` VALUES (51, 4, 'DD202101040151', '已发货', '2021-01-13 00:00:00', 20795.00);
INSERT INTO `order_info` VALUES (52, 2, 'DD202101040171', '已付款', '2021-01-06 00:00:00', 6488.00);
INSERT INTO `order_info` VALUES (53, 3, 'DD202101040175', '已付款', '2021-01-05 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (54, 3, 'DD202101040175', '已付款', '2021-01-05 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (55, 3, 'DD202101040176', '待发货', '2021-01-05 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (56, 2, 'DD202101040177', '已付款', '2021-01-05 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (57, 4, 'DD202101040178', '已付款', '2021-01-13 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (58, 3, 'DD202101040180', '已付款', '2021-01-05 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (59, 2, 'DD202101040184', '未付款', '2020-12-29 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (60, 2, 'DD202101040185', '已付款', '2021-01-06 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (61, 2, 'DD202101040187', '已付款', '2020-12-29 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (62, 2, 'DD202101040191', '未付款', '2021-01-06 00:00:00', 8398.00);
INSERT INTO `order_info` VALUES (63, 2, 'DD202101060081', '已付款', '2021-01-06 00:00:00', 8398.00);
INSERT INTO `order_info` VALUES (64, 1, 'DD202101060095', '未付款', '2021-01-04 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (65, 3, 'DD202101060097', '已付款', '2021-01-04 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (66, 2, 'DD202101060098', '已付款', '2021-01-05 00:00:00', 0.00);
INSERT INTO `order_info` VALUES (67, 2, 'DD202101070001', '待发货', '2021-01-07 00:00:00', 17375.00);
INSERT INTO `order_info` VALUES (68, 3, 'DD202104300002', '待发货', '2021-04-01 00:00:00', 8998.00);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(4) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `delState` int(4) NULL DEFAULT 0,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 0);
INSERT INTO `role` VALUES (2, '商品管理员', 0);
INSERT INTO `role` VALUES (3, '订单管理员', 0);
INSERT INTO `role` VALUES (4, '前台客户管理员', 0);
INSERT INTO `role` VALUES (5, '商品、订单管理员', 0);
INSERT INTO `role` VALUES (19, '总经理', 0);
INSERT INTO `role` VALUES (20, '总经理1', 1);
INSERT INTO `role` VALUES (21, '总经理2', 1);

-- ----------------------------
-- Table structure for role_functions
-- ----------------------------
DROP TABLE IF EXISTS `role_functions`;
CREATE TABLE `role_functions`  (
  `rid` int(4) NOT NULL,
  `fid` int(4) NOT NULL,
  PRIMARY KEY (`rid`, `fid`) USING BTREE,
  INDEX `fid`(`fid`) USING BTREE,
  INDEX `aid`(`rid`) USING BTREE,
  CONSTRAINT `role_functions_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_functions_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `functions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_functions
-- ----------------------------
INSERT INTO `role_functions` VALUES (1, 1);
INSERT INTO `role_functions` VALUES (2, 1);
INSERT INTO `role_functions` VALUES (3, 1);
INSERT INTO `role_functions` VALUES (4, 1);
INSERT INTO `role_functions` VALUES (5, 1);
INSERT INTO `role_functions` VALUES (19, 1);
INSERT INTO `role_functions` VALUES (20, 1);
INSERT INTO `role_functions` VALUES (21, 1);
INSERT INTO `role_functions` VALUES (1, 2);
INSERT INTO `role_functions` VALUES (2, 2);
INSERT INTO `role_functions` VALUES (3, 2);
INSERT INTO `role_functions` VALUES (5, 2);
INSERT INTO `role_functions` VALUES (20, 2);
INSERT INTO `role_functions` VALUES (21, 2);
INSERT INTO `role_functions` VALUES (1, 3);
INSERT INTO `role_functions` VALUES (2, 3);
INSERT INTO `role_functions` VALUES (5, 3);
INSERT INTO `role_functions` VALUES (20, 3);
INSERT INTO `role_functions` VALUES (21, 3);
INSERT INTO `role_functions` VALUES (1, 4);
INSERT INTO `role_functions` VALUES (2, 4);
INSERT INTO `role_functions` VALUES (3, 4);
INSERT INTO `role_functions` VALUES (5, 4);
INSERT INTO `role_functions` VALUES (20, 4);
INSERT INTO `role_functions` VALUES (21, 4);
INSERT INTO `role_functions` VALUES (1, 5);
INSERT INTO `role_functions` VALUES (3, 5);
INSERT INTO `role_functions` VALUES (5, 5);
INSERT INTO `role_functions` VALUES (19, 5);
INSERT INTO `role_functions` VALUES (20, 5);
INSERT INTO `role_functions` VALUES (21, 5);
INSERT INTO `role_functions` VALUES (1, 6);
INSERT INTO `role_functions` VALUES (3, 6);
INSERT INTO `role_functions` VALUES (5, 6);
INSERT INTO `role_functions` VALUES (19, 6);
INSERT INTO `role_functions` VALUES (20, 6);
INSERT INTO `role_functions` VALUES (21, 6);
INSERT INTO `role_functions` VALUES (1, 7);
INSERT INTO `role_functions` VALUES (3, 7);
INSERT INTO `role_functions` VALUES (5, 7);
INSERT INTO `role_functions` VALUES (19, 7);
INSERT INTO `role_functions` VALUES (1, 8);
INSERT INTO `role_functions` VALUES (4, 8);
INSERT INTO `role_functions` VALUES (19, 8);
INSERT INTO `role_functions` VALUES (21, 8);
INSERT INTO `role_functions` VALUES (1, 9);
INSERT INTO `role_functions` VALUES (4, 9);
INSERT INTO `role_functions` VALUES (19, 9);
INSERT INTO `role_functions` VALUES (21, 9);
INSERT INTO `role_functions` VALUES (1, 10);
INSERT INTO `role_functions` VALUES (19, 10);
INSERT INTO `role_functions` VALUES (21, 10);
INSERT INTO `role_functions` VALUES (1, 11);
INSERT INTO `role_functions` VALUES (21, 11);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realName` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regDate` date NULL DEFAULT NULL,
  `status` int(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'tom', '123456', '汤姆', '男', '四川省成都市龙泉驿', 'tom@123.com', '2023-07-14', 0);
INSERT INTO `user_info` VALUES (2, 'john', '123456', '约翰', '男', '四川省成都市龙泉驿', 'wen@135.com', '2023-07-14', 0);
INSERT INTO `user_info` VALUES (3, 'my', '123456', 'my', '男', '四川省成都市龙泉驿', 'a@135.com', '2023-09-16', 0);
INSERT INTO `user_info` VALUES (4, 'sj', '123456', 'sj', '男', '四川省成都市龙泉驿', 'b@135.com', '2023-09-16', 0);
INSERT INTO `user_info` VALUES (5, 'lxf', '123456', 'lxf', '男', '四川省成都市龙泉驿', 'c@135.com', '2023-09-16', 1);
INSERT INTO `user_info` VALUES (6, 'lj', '123456', 'lj', '女', '四川省成都市龙泉驿', 'a@135.com', '2023-09-20', 0);

-- ----------------------------
-- Procedure structure for sp_sale
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_sale`;
delimiter ;;
CREATE PROCEDURE `sp_sale`(c int)
BEGIN
    declare stmt varchar(2000);
    set @sqlstr=concat("SELECT p.id AS id, p.name AS NAME,SUM(od.num) AS total ,SUM(od.num)*price AS money
	FROM order_detail od, product_info p
	WHERE p.id=od.p_id
	GROUP BY p.id,p.name,p.price ORDER BY total DESC LIMIT 1,",c);
    prepare stmt from @sqlstr;
    execute stmt;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
