/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : repair

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 14/05/2021 20:45:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员ID',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('11232', 'e10adc3949ba59abbe56e057f20f883e', 'admin');
INSERT INTO `admin` VALUES ('11234', 'e10adc3949ba59abbe56e057f20f883e', 'system');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sexual` int(11) NULL DEFAULT 0 COMMENT '性别：0为男1为女，默认为男',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12970348601', '梁毅滨', 'e10adc3949ba59abbe56e057f20f883e', 0, '1297034862', '123123');
INSERT INTO `user` VALUES ('134566', '11232', 'e10adc3949ba59abbe56e057f20f883e', 0, 'maxinhangdoit@163.com', '13619278232');
INSERT INTO `user` VALUES ('1345665', '11232', 'e10adc3949ba59abbe56e057f20f883e', 0, 'maxinhangdoit@163.com', '13619278232');
INSERT INTO `user` VALUES ('15251104218', '谢土飞(15251104218)', 'e10adc3949ba59abbe56e057f20f883e', 0, '', '');
INSERT INTO `user` VALUES ('15251104224', '温小辉', 'e10adc3949ba59abbe56e057f20f883e', 0, '15251104224', '15251104224');
INSERT INTO `user` VALUES ('41709310110', '11232', 'e10adc3949ba59abbe56e057f20f883e', 1, 'maxinhangdoit@163.com', '13619278232');
INSERT INTO `user` VALUES ('41709310111', '11232', 'e10adc3949ba59abbe56e057f20f883e', 1, 'maxinhangdoit@163.com', '13619278232');
INSERT INTO `user` VALUES ('41709310115', '马新航', 'e10adc3949ba59abbe56e057f20f883e', 0, 'maxinhangdoit@163.com', '13619278232');
INSERT INTO `user` VALUES ('41709310116', 'maxinhang2', 'e10adc3949ba59abbe56e057f20f883e', 0, 'maxinhangdoit@163.com', '13186379659');
INSERT INTO `user` VALUES ('41709310118', '11232', 'e10adc3949ba59abbe56e057f20f883e', 0, 'maxinhangdoit@163.com', '13186379659');
INSERT INTO `user` VALUES ('41709310119', '11232', 'e10adc3949ba59abbe56e057f20f883e', 1, 'maxinhangdoit@163.com', '13619278232');
INSERT INTO `user` VALUES ('41709310120', 'maxinhang', 'e10adc3949ba59abbe56e057f20f883e', 0, 'maxinhangdoit@163.com', '13619278232');

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工种',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职业表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES (1, '电工');
INSERT INTO `profession` VALUES (4, '水工');


-- ----------------------------
-- Table structure for technician
-- ----------------------------
DROP TABLE IF EXISTS `technician`;
CREATE TABLE `technician`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修人员ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `profession_id` int(11) NOT NULL COMMENT '职业id',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_PROFESSIONID`(`profession_id`) USING BTREE,
  CONSTRAINT `FK_PROFESSIONID` FOREIGN KEY (`profession_id`) REFERENCES `profession` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维修人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of technician
-- ----------------------------
INSERT INTO `technician` VALUES ('007', 'liu', 'e10adc3949ba59abbe56e057f20f883e', '13158862491', 4, 'maxinhangdoit@163.com');
INSERT INTO `technician` VALUES ('111', '马新航', 'e10adc3949ba59abbe56e057f20f883e', '13619278232', 1, 'maxinhangdoit@163.com');
INSERT INTO `technician` VALUES ('2', 'xiaohui', 'e10adc3949ba59abbe56e057f20f883e', '13447834475', 1, '282777859@qq.com');
INSERT INTO `technician` VALUES ('3', 'xiaobin', 'e10adc3949ba59abbe56e057f20f883e', '13480398447', 1, '282777859@qq.com');
INSERT INTO `technician` VALUES ('4', 'Ben', 'e10adc3949ba59abbe56e057f20f883e', '13420329897', 1, '1297034860282777859@qq.com');
INSERT INTO `technician` VALUES ('5', 'Mary', 'e10adc3949ba59abbe56e057f20f883e', '18424543647', 1, '282777859@qq.com');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '报修单状态：0为被用户删除，1为未安排检修，2为已安排检修，3为待同意取消，\r\n  4为已同意取消，5为待验收，6为已验收，默认为被用户删除',
  `detail` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题详情',
  `place` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发生故障的物业的地点',
  `picture_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现场照片',
  `submit_time` datetime(0) NOT NULL COMMENT '提交报修单的时间',
  `user_id` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交该报修单的用户的编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_USERID`(`user_id`) USING BTREE,
  CONSTRAINT `FK_USERID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报修单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (19, 2, '天花板漏水', '25栋705', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-09-26 15:12:54', '15251104224');
INSERT INTO `repair` VALUES (20, 1, ' 天花板漏水', '25栋705室', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-09-26 15:14:36', '15251104224');
INSERT INTO `repair` VALUES (21, 2, ' 天花板漏水', '25栋705', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-09-28 15:31:13', '15251104224');
INSERT INTO `repair` VALUES (25, 1, ' 凳子坏了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-09-29 22:46:23', '15251104224');
INSERT INTO `repair` VALUES (27, 0, ' 天花板漏水', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-03 17:25:42', '15251104218');
INSERT INTO `repair` VALUES (30, 0, ' 电脑又坏了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 20:41:42', '15251104224');
INSERT INTO `repair` VALUES (31, 0, ' 电脑坏坏的', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 20:43:58', '15251104224');
INSERT INTO `repair` VALUES (32, 0, ' 电脑坏坏的', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 20:49:38', '15251104224');
INSERT INTO `repair` VALUES (33, 0, ' 被子坏了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 20:51:03', '15251104224');
INSERT INTO `repair` VALUES (34, 0, ' 被子又坏了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 20:55:32', '15251104224');
INSERT INTO `repair` VALUES (35, 0, ' 管道漏水了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 20:56:49', '15251104224');
INSERT INTO `repair` VALUES (36, 0, ' 地板爆裂', '25栋705', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-10 23:18:05', '15251104224');
INSERT INTO `repair` VALUES (37, 0, ' 天花板爆炸', '25栋619', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-11 10:49:29', '15251104224');
INSERT INTO `repair` VALUES (38, 0, ' 柜子坏了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-11 11:03:38', '15251104218');
INSERT INTO `repair` VALUES (41, 0, ' 灯管坏了', '25栋', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-11 21:01:46', '15251104224');
INSERT INTO `repair` VALUES (42, 0, '花洒漏水很严重，都裂开了，需要换一个花洒', '', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-11 21:03:50', '15251104224');
INSERT INTO `repair` VALUES (43, 0, ' 天花板漏水，有很多灰尘掉下来', '25栋705', 'https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF', '2018-10-11 22:47:46', '15251104224');
INSERT INTO `repair` VALUES (44, 1, ' 灯管不亮了', '2号楼A413宿舍', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/14/001605bcfe764ef9aaf34ba10436c6b2000.jpg', '2021-04-14 15:22:24', '15251104224');
INSERT INTO `repair` VALUES (45, 0, ' 垃圾', '2号楼A413宿舍', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/14/424e347e36554a469282271a570c79d6src=http___gss0.baidu.com_94o3dSag_xI4khGko9WTAnF6hhy_zhidao_pic_item_bba1cd11728b471049731910c5cec3fdfd03238a.jpg&refer=http___gss0.baidu.jpg', '2021-04-14 15:29:50', '15251104224');
INSERT INTO `repair` VALUES (46, 0, '天花板漏水', '', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/20/4d394f442a0c44e4b07b70ce3a2e57a3', '2021-04-20 10:28:58', '15251104224');
INSERT INTO `repair` VALUES (47, 0, ' 凳子坏了', '25栋', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/20/b53780bd4a7c461ea8ce172d674fe36e', '2021-04-20 10:32:43', '15251104224');
INSERT INTO `repair` VALUES (48, 0, ' 被子又坏了', '25栋', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/20/4fe699a370074c158a2c4626bcf90d10', '2021-04-20 10:36:26', '15251104224');
INSERT INTO `repair` VALUES (50, 0, '', '', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/20/88035cce78a74926a6fa24ea12ae3eca', '2021-04-20 11:14:28', '15251104224');
INSERT INTO `repair` VALUES (51, 0, '', '', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/20/d2ad6a9b60074309b6a82626db0cdbd1', '2021-04-20 11:19:41', '15251104224');
INSERT INTO `repair` VALUES (52, 0, '', '', NULL, '2021-04-20 11:24:44', '15251104224');
INSERT INTO `repair` VALUES (53, 0, '的', '的', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/20/2d464d11104d47309cb4a7b13e7a9665jio.jpg', '2021-04-20 15:13:07', '15251104224');
INSERT INTO `repair` VALUES (54, 0, '空调坏了', '2#A413', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/05/11/c895fadab0354ce487e851d464eb25f9wx877cbc11239517e6.o6zAJsyNnP8-1iFAnONdqfzg1WZs.Mzcl5MTPuUHn80c56d6d5a740c37bd16ee3f3a626f27.png', '2021-05-11 15:21:51', '134566');
INSERT INTO `repair` VALUES (55, 0, '空调坏了', '2#A413', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/05/11/c895fadab0354ce487e851d464eb25f9wx877cbc11239517e6.o6zAJsyNnP8-1iFAnONdqfzg1WZs.Mzcl5MTPuUHn80c56d6d5a740c37bd16ee3f3a626f27.png', '2021-05-11 15:21:57', '134566');


-- ----------------------------
-- Table structure for maintenance
-- ----------------------------
DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE `maintenance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `repair_id` int(11) NOT NULL COMMENT '该维修记录对应的报修单编号',
  `technician_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修人员的编号',
  `start_time` datetime(0) NOT NULL COMMENT '维修开始的时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '维修结束的时间',
  `picture_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修完成后现场照片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_REPAIRID`(`repair_id`) USING BTREE,
  INDEX `FK_TECHNICIANID`(`technician_id`) USING BTREE,
  CONSTRAINT `FK_REPAIRID` FOREIGN KEY (`repair_id`) REFERENCES `repair` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_TECHNICIANID` FOREIGN KEY (`technician_id`) REFERENCES `technician` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维修记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintenance
-- ----------------------------
INSERT INTO `maintenance` VALUES (12, 19, '2', '2018-10-11 23:27:00', '2018-10-11 23:27:00', '84df5cf96a80016e074dda01e05bd2f5');
INSERT INTO `maintenance` VALUES (22, 20, '3', '2021-04-10 11:55:25', NULL, NULL);
INSERT INTO `maintenance` VALUES (23, 21, '2', '2021-04-13 10:20:07', '2021-04-15 09:13:52', 'https://xpu-repair.oss-cn-beijing.aliyuncs.com/2021/04/15/802b3f51db0c42f4abf464121e0f1915ball.jpg');
INSERT INTO `maintenance` VALUES (24, 25, '3', '2021-04-16 11:14:35', NULL, NULL);

-- ----------------------------
-- Table structure for urgentrepair
-- ----------------------------
DROP TABLE IF EXISTS `urgentrepair`;
CREATE TABLE `urgentrepair`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态：0为待查看，1为已查看，2为被用户取消，默认为待查看',
  `repair_id` int(11) NOT NULL COMMENT '该催单对应的报修单编号',
  `user_id` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起该催单的用户的编号',
  `create_time` datetime(0) NOT NULL COMMENT '催单的创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_REPAIRID1`(`repair_id`) USING BTREE,
  INDEX `FK_USERID1`(`user_id`) USING BTREE,
  CONSTRAINT `FK_REPAIRID1` FOREIGN KEY (`repair_id`) REFERENCES `repair` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_USERID1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '催单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of urgentrepair
-- ----------------------------
INSERT INTO `urgentrepair` VALUES (7, 0, 7, '15251104224', '2018-09-26 14:19:08');
INSERT INTO `urgentrepair` VALUES (8, 0, 8, '15251104224', '2018-09-26 14:16:20');
INSERT INTO `urgentrepair` VALUES (9, 0, 13, '15251104224', '2018-09-26 14:19:23');
INSERT INTO `urgentrepair` VALUES (10, 0, 19, '15251104224', '2018-09-28 15:30:41');
INSERT INTO `urgentrepair` VALUES (13, 0, 38, '15251104218', '2018-10-11 11:06:25');
INSERT INTO `urgentrepair` VALUES (16, 0, 40, '15251104224', '2018-10-11 16:43:55');
INSERT INTO `urgentrepair` VALUES (19, 0, 42, '15251104224', '2018-10-11 21:34:08');
INSERT INTO `urgentrepair` VALUES (20, 0, 36, '15251104224', '2018-10-11 22:46:56');
INSERT INTO `urgentrepair` VALUES (21, 0, 27, '15251104218', '2018-10-11 23:41:06');
INSERT INTO `urgentrepair` VALUES (22, 1, 20, '15251104224', '2021-04-13 14:26:46');
INSERT INTO `urgentrepair` VALUES (23, 1, 20, '15251104224', '2021-04-13 14:27:17');
INSERT INTO `urgentrepair` VALUES (24, 1, 20, '15251104224', '2021-04-13 14:27:21');
INSERT INTO `urgentrepair` VALUES (25, 1, 20, '15251104224', '2021-04-13 14:33:39');
INSERT INTO `urgentrepair` VALUES (26, 1, 20, '15251104224', '2021-04-13 14:34:19');
INSERT INTO `urgentrepair` VALUES (27, 1, 20, '15251104224', '2021-04-13 14:36:51');
INSERT INTO `urgentrepair` VALUES (28, 1, 20, '15251104224', '2021-04-13 14:47:42');
INSERT INTO `urgentrepair` VALUES (29, 1, 21, '15251104224', '2021-04-13 14:50:20');
INSERT INTO `urgentrepair` VALUES (30, 1, 20, '15251104224', '2021-04-14 15:43:48');
INSERT INTO `urgentrepair` VALUES (31, 1, 20, '15251104224', '2021-04-14 15:50:23');
INSERT INTO `urgentrepair` VALUES (32, 1, 20, '15251104224', '2021-05-12 11:57:48');




