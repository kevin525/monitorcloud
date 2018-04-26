/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : monitorcloud_init

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-04-24 14:03:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `asop_app_system`
-- ----------------------------
DROP TABLE IF EXISTS `asop_app_system`;
CREATE TABLE `asop_app_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appIp` varchar(255) DEFAULT NULL,
  `appName` varchar(255) DEFAULT NULL,
  `appNetStatus` varchar(255) DEFAULT NULL,
  `appOrder` int(11) NOT NULL,
  `appStatus` varchar(255) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `dataBaseIp` varchar(255) DEFAULT NULL,
  `dataBaseName` varchar(255) DEFAULT NULL,
  `dataBaseNetStatus` varchar(255) DEFAULT NULL,
  `dataBaseType` varchar(255) DEFAULT NULL,
  `environment` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `dataBaseIns` varchar(255) DEFAULT NULL,
  `dataBasePwd` varchar(255) DEFAULT NULL,
  `dataBaseUser` varchar(255) DEFAULT NULL,
  `lastCheckDate` varchar(255) DEFAULT NULL,
  `checkCount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_app_system
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_database`
-- ----------------------------
DROP TABLE IF EXISTS `asop_database`;
CREATE TABLE `asop_database` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appOrder` int(11) NOT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `dataBaseIns` varchar(255) DEFAULT NULL,
  `dataBaseIp` varchar(255) DEFAULT NULL,
  `dataBaseName` varchar(255) DEFAULT NULL,
  `dataBaseNetStatus` varchar(255) DEFAULT NULL,
  `dataBasePwd` varchar(255) DEFAULT NULL,
  `dataBaseType` varchar(255) DEFAULT NULL,
  `dataBaseUser` varchar(255) DEFAULT NULL,
  `environment` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `lastCheckDate` varchar(255) DEFAULT NULL,
  `checkCount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_database
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_doc_not_receive`
-- ----------------------------
DROP TABLE IF EXISTS `asop_doc_not_receive`;
CREATE TABLE `asop_doc_not_receive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` varchar(255) DEFAULT NULL,
  `docId` varchar(255) DEFAULT NULL,
  `docNum` varchar(255) DEFAULT NULL,
  `docTitle` varchar(255) DEFAULT NULL,
  `docYear` varchar(255) DEFAULT NULL,
  `freeflowId` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `receiveUser` varchar(255) DEFAULT NULL,
  `sendUser` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_doc_not_receive
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_error_log`
-- ----------------------------
DROP TABLE IF EXISTS `asop_error_log`;
CREATE TABLE `asop_error_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appId` bigint(20) NOT NULL,
  `errorDetail` longtext,
  `errorName` varchar(255) DEFAULT NULL,
  `errorTime` varchar(255) DEFAULT NULL,
  `errorTitle` varchar(255) DEFAULT NULL,
  `errorType` varchar(255) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `serverIp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_error_log
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_inform`
-- ----------------------------
DROP TABLE IF EXISTS `asop_inform`;
CREATE TABLE `asop_inform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `monitorId` varchar(255) DEFAULT NULL,
  `monitorType` varchar(255) DEFAULT NULL,
  `sendDate` varchar(255) DEFAULT NULL,
  `sendStatus` varchar(255) DEFAULT NULL,
  `readStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_inform
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_mail`
-- ----------------------------
DROP TABLE IF EXISTS `asop_mail`;
CREATE TABLE `asop_mail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `mailOrder` int(11) NOT NULL,
  `monitorId` varchar(255) DEFAULT NULL,
  `monitorType` varchar(255) DEFAULT NULL,
  `receiveAddress` varchar(255) DEFAULT NULL,
  `sendAddress` varchar(255) DEFAULT NULL,
  `sendDate` varchar(255) DEFAULT NULL,
  `sendStatus` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_mail
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_middleware`
-- ----------------------------
DROP TABLE IF EXISTS `asop_middleware`;
CREATE TABLE `asop_middleware` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkCount` int(11) NOT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `environment` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `lastCheckDate` varchar(255) DEFAULT NULL,
  `middlewareOrder` int(11) NOT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `remark` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_middleware
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_project`
-- ----------------------------
DROP TABLE IF EXISTS `asop_project`;
CREATE TABLE `asop_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `isOnline` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `projectOrder` int(11) NOT NULL,
  `projectStatus` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `personLiableId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_project
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_server`
-- ----------------------------
DROP TABLE IF EXISTS `asop_server`;
CREATE TABLE `asop_server` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `availableHDSzie` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `environment` varchar(255) DEFAULT NULL,
  `hardDiskSize` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `memorySize` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `osName` varchar(255) DEFAULT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `serverOrder` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `lastCheckDate` varchar(255) DEFAULT NULL,
  `checkCount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_server
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_sms`
-- ----------------------------
DROP TABLE IF EXISTS `asop_sms`;
CREATE TABLE `asop_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `isUse` varchar(255) DEFAULT NULL,
  `phoneNums` varchar(255) DEFAULT NULL,
  `recordId` bigint(20) NOT NULL,
  `sendDate` varchar(255) DEFAULT NULL,
  `sendLoginName` varchar(255) DEFAULT NULL,
  `smsOrder` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `sendStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6880 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_sms
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_tomcat`
-- ----------------------------
DROP TABLE IF EXISTS `asop_tomcat`;
CREATE TABLE `asop_tomcat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appOrder` int(11) NOT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `environment` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `managerPassword` varchar(255) DEFAULT NULL,
  `managerRole` varchar(255) DEFAULT NULL,
  `managerUrl` varchar(255) DEFAULT NULL,
  `managerUserName` varchar(255) DEFAULT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `tomcatStatus` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `webAppName` varchar(255) DEFAULT NULL,
  `lastCheckDate` varchar(255) DEFAULT NULL,
  `checkCount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_tomcat
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_warning`
-- ----------------------------
DROP TABLE IF EXISTS `asop_warning`;
CREATE TABLE `asop_warning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` varchar(255) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `isNormal` varchar(255) DEFAULT NULL,
  `isSendEmail` varchar(255) DEFAULT NULL,
  `isSendInform` varchar(255) DEFAULT NULL,
  `isSendMessage` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `minute` varchar(255) DEFAULT NULL,
  `modifyDate` varchar(255) DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `warningDesc` varchar(255) DEFAULT NULL,
  `warningName` varchar(255) DEFAULT NULL,
  `warningType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_warning
-- ----------------------------
INSERT INTO `asop_warning` VALUES ('1', '3', '2018-04-12 11:09:40', '0', null, null, '1', null, '0', null, '2018-04-12 11:09:40', '1', null, '服务器告警规则', 'server');
INSERT INTO `asop_warning` VALUES ('2', '3', '2018-04-12 11:09:35', '0', '1', null, '1', null, '0', '13', '2018-04-12 11:09:35', '1', null, '应用系统告警规则', 'system');
INSERT INTO `asop_warning` VALUES ('3', '3', '2018-04-12 11:09:28', '0', '1', null, '1', null, '0', '10', '2018-04-12 11:09:28', '1', null, '数据库告警规则', 'database');
INSERT INTO `asop_warning` VALUES ('4', '3', '2018-04-11 18:05:24', '0', '1', null, '1', null, '0', '7', '2018-04-11 18:05:24', '1', null, '中间件告警规则', 'middleware');
INSERT INTO `asop_warning` VALUES ('5', null, '2017-12-15 09:36:36', '0', null, null, null, '1', '2', null, '2017-12-15 09:36:36', '1', null, '111', 'middleware');
INSERT INTO `asop_warning` VALUES ('6', null, '2017-12-15 09:37:07', '0', null, null, null, '1', '2', '22', '2017-12-15 09:37:07', '1', null, '222', 'middleware');

-- ----------------------------
-- Table structure for `asop_warning_send`
-- ----------------------------
DROP TABLE IF EXISTS `asop_warning_send`;
CREATE TABLE `asop_warning_send` (
  `warningSendId` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `warningSendName` varchar(255) DEFAULT NULL,
  `warningSendShortName` varchar(255) DEFAULT NULL,
  `warningSendValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`warningSendId`),
  KEY `FK83C6959714C02F93` (`id`),
  CONSTRAINT `FK83C6959714C02F93` FOREIGN KEY (`id`) REFERENCES `asop_warning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_warning_send
-- ----------------------------

-- ----------------------------
-- Table structure for `asop_warn_log`
-- ----------------------------
DROP TABLE IF EXISTS `asop_warn_log`;
CREATE TABLE `asop_warn_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `modifyDate` varchar(255) DEFAULT NULL,
  `monitorId` varchar(255) DEFAULT NULL,
  `monitorName` varchar(255) DEFAULT NULL,
  `monitorType` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `warnCount` varchar(255) DEFAULT NULL,
  `warnDesc` varchar(255) DEFAULT NULL,
  `warnName` varchar(255) DEFAULT NULL,
  `warnTime` varchar(255) DEFAULT NULL,
  `warnWay` varchar(255) DEFAULT NULL,
  `personLiableId` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asop_warn_log
-- ----------------------------

-- ----------------------------
-- Table structure for `ops_check_record`
-- ----------------------------
DROP TABLE IF EXISTS `ops_check_record`;
CREATE TABLE `ops_check_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` varchar(255) DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `environment` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `personLiable` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ops_check_record
-- ----------------------------
INSERT INTO `ops_check_record` VALUES ('1', '2017-12-05 14:01:40', '1', '1', '0', '运维记录', '管理员', '');
INSERT INTO `ops_check_record` VALUES ('2', '2017-12-05 14:03:03', '1', '1', '0', '运维记录', '管理员', '');
INSERT INTO `ops_check_record` VALUES ('3', '2017-12-05 14:05:39', '1', '1', '0', '运维记录', '管理员', '');
INSERT INTO `ops_check_record` VALUES ('4', '2017-12-05 14:06:35', '1', '1', '0', '运维记录', '管理员', '');

-- ----------------------------
-- Table structure for `ops_check_record_info`
-- ----------------------------
DROP TABLE IF EXISTS `ops_check_record_info`;
CREATE TABLE `ops_check_record_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkRecordId` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54B9CA9844D7849C` (`modelId`),
  CONSTRAINT `FK54B9CA9844D7849C` FOREIGN KEY (`modelId`) REFERENCES `ops_check_record_model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ops_check_record_info
-- ----------------------------
INSERT INTO `ops_check_record_info` VALUES ('1', '1', '16', '0');
INSERT INTO `ops_check_record_info` VALUES ('2', '1', '17', '0');
INSERT INTO `ops_check_record_info` VALUES ('3', '1', '18', '0');
INSERT INTO `ops_check_record_info` VALUES ('4', '1', '26', '0');
INSERT INTO `ops_check_record_info` VALUES ('5', '1', '27', '0');
INSERT INTO `ops_check_record_info` VALUES ('6', '1', '20', '0');
INSERT INTO `ops_check_record_info` VALUES ('7', '1', '21', '0');
INSERT INTO `ops_check_record_info` VALUES ('8', '1', '22', '0');
INSERT INTO `ops_check_record_info` VALUES ('9', '1', '23', '0');
INSERT INTO `ops_check_record_info` VALUES ('19', '2', '16', '0');
INSERT INTO `ops_check_record_info` VALUES ('20', '2', '17', '0');
INSERT INTO `ops_check_record_info` VALUES ('21', '2', '18', '0');
INSERT INTO `ops_check_record_info` VALUES ('22', '2', '26', '0');
INSERT INTO `ops_check_record_info` VALUES ('23', '2', '27', '0');
INSERT INTO `ops_check_record_info` VALUES ('24', '2', '20', '0');
INSERT INTO `ops_check_record_info` VALUES ('25', '2', '21', '0');
INSERT INTO `ops_check_record_info` VALUES ('26', '2', '22', '0');
INSERT INTO `ops_check_record_info` VALUES ('27', '2', '23', '0');
INSERT INTO `ops_check_record_info` VALUES ('28', '3', '16', '0');
INSERT INTO `ops_check_record_info` VALUES ('29', '3', '17', '0');
INSERT INTO `ops_check_record_info` VALUES ('30', '3', '18', '0');
INSERT INTO `ops_check_record_info` VALUES ('31', '3', '26', '0');
INSERT INTO `ops_check_record_info` VALUES ('32', '3', '27', '0');
INSERT INTO `ops_check_record_info` VALUES ('33', '3', '20', '0');
INSERT INTO `ops_check_record_info` VALUES ('34', '3', '21', '0');
INSERT INTO `ops_check_record_info` VALUES ('35', '3', '22', '0');
INSERT INTO `ops_check_record_info` VALUES ('36', '3', '23', '0');
INSERT INTO `ops_check_record_info` VALUES ('37', '4', '17', '0');
INSERT INTO `ops_check_record_info` VALUES ('38', '4', '18', '0');
INSERT INTO `ops_check_record_info` VALUES ('39', '4', '26', '0');
INSERT INTO `ops_check_record_info` VALUES ('40', '4', '27', '0');
INSERT INTO `ops_check_record_info` VALUES ('41', '4', '20', '0');
INSERT INTO `ops_check_record_info` VALUES ('42', '4', '21', '0');
INSERT INTO `ops_check_record_info` VALUES ('43', '4', '22', '0');
INSERT INTO `ops_check_record_info` VALUES ('44', '4', '23', '0');
INSERT INTO `ops_check_record_info` VALUES ('45', '4', '16', '1');

-- ----------------------------
-- Table structure for `ops_check_record_model`
-- ----------------------------
DROP TABLE IF EXISTS `ops_check_record_model`;
CREATE TABLE `ops_check_record_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` varchar(255) DEFAULT NULL,
  `fatherId` bigint(20) NOT NULL,
  `modelOrder` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nodeValue` int(11) NOT NULL,
  `ownerId` bigint(20) NOT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `isUse` varchar(255) DEFAULT NULL,
  `ownerName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ops_check_record_model
-- ----------------------------
INSERT INTO `ops_check_record_model` VALUES ('15', '2018-02-06 16:53:44', '0', '1', '系统功能检查', '1', '1', 'xtgnjc', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('16', '2017-12-04 17:46:42', '15', '2', '内网门户检查', '2', '1', 'nwmhjc', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('17', '2017-12-04 17:46:45', '15', '3', '待办桌面检查', '2', '1', 'dbzmjc', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('18', '2017-12-04 17:47:02', '15', '4', '党政信息检查', '2', '1', 'dzxxjc', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('19', '2017-12-04 17:47:16', '0', '5', '服务器检查', '1', '1', 'fwqjc', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('20', '2017-12-04 17:47:20', '19', '6', '10.171.251.36检查', '2', '1', '10.171.251.36', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('21', '2017-12-04 17:47:27', '19', '7', '10.171.251.70检查', '2', '1', '10.171.251.70', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('22', '2017-12-04 17:47:32', '19', '7', '10.171.251.80检查', '2', '1', '80', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('23', '2017-12-04 17:47:36', '0', '8', '数据库检查', '1', '1', 'sjkjc', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('24', '2017-12-04 17:44:27', '0', '1', '测试12', '1', '1', 'cs1', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('26', '2017-12-04 17:55:36', '24', '3', '测试22', '2', '1', 'cs22', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('27', '2017-12-04 17:46:57', '24', '3', '测试33', '2', '1', 'cs33', '0', '管理员');
INSERT INTO `ops_check_record_model` VALUES ('28', '2017-12-04 17:46:03', '0', '1', '巡检项1', '1', '4', 'xjx1', '1', '张梦琦');
INSERT INTO `ops_check_record_model` VALUES ('29', '2017-12-04 17:46:06', '28', '2', '巡检项11', '2', '4', 'xjx11', '1', '张梦琦');
INSERT INTO `ops_check_record_model` VALUES ('30', '2017-12-04 17:46:09', '28', '3', '巡检项111', '2', '4', 'xjx111', '1', '张梦琦');
INSERT INTO `ops_check_record_model` VALUES ('31', '2017-12-04 17:46:11', '0', '4', '巡检项2', '1', '4', 'xjx2', '1', '张梦琦');
INSERT INTO `ops_check_record_model` VALUES ('32', '2017-12-04 17:54:59', '31', '5', '巡检项22', '2', '4', 'xjx22', '1', '张梦琦');

-- ----------------------------
-- Table structure for `sys_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `loginLogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `logCreateTime` datetime DEFAULT NULL,
  `loginDate` varchar(255) DEFAULT NULL,
  `loginIp` varchar(255) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `loginOutDate` datetime DEFAULT NULL,
  `personName` varchar(255) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`loginLogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `opLogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `opContent` varchar(255) DEFAULT NULL,
  `opDate` varchar(255) DEFAULT NULL,
  `opType` varchar(255) DEFAULT NULL,
  `personName` varchar(255) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`opLogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_person`
-- ----------------------------
DROP TABLE IF EXISTS `sys_person`;
CREATE TABLE `sys_person` (
  `personId` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `displayAllSys` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `isLeader` int(11) NOT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `personAddress` varchar(255) DEFAULT NULL,
  `personEmail` varchar(255) DEFAULT NULL,
  `personName` varchar(100) DEFAULT NULL,
  `personOffice` varchar(255) DEFAULT NULL,
  `personOfficeTel` varchar(255) DEFAULT NULL,
  `personOrderNum` int(11) NOT NULL,
  `personState` int(11) NOT NULL,
  `personTel` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_person
-- ----------------------------
INSERT INTO `sys_person` VALUES ('1', '2017-10-23 00:00:00', '0', null, null, null, '0', null, '0', null, null, '管理员', null, null, '0', '1', '18335162949', null, '0');
INSERT INTO `sys_person` VALUES ('9', '2018-04-11 15:13:36', '1', null, null, null, '1', null, '1', null, null, '崔永佳', null, null, '1', '1', '18335162949', null, '0');

-- ----------------------------
-- Table structure for `sys_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `resourcesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `nodeValue` int(11) NOT NULL,
  `resoucesCode` varchar(255) DEFAULT NULL,
  `resoucesImg` varchar(255) DEFAULT NULL,
  `resourcesName` varchar(255) DEFAULT NULL,
  `resourcesOrderNum` int(11) NOT NULL,
  `resourcesPid` bigint(20) NOT NULL,
  `resourcesState` int(11) NOT NULL,
  `resourcesUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resourcesId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '1', 'ywjk', null, '监控管理', '10', '0', '1', 'jsp/daily/serverMonitoring.jsp');
INSERT INTO `sys_resources` VALUES ('2', '1', 'xtwtcl', null, '系统问题处理', '60', '0', '1', 'jsp/problem/leaderSignOne.jsp');
INSERT INTO `sys_resources` VALUES ('3', '1', 'xqwh', null, '需求维护', '2', '0', '0', 'jsp/requirement/requireManage.jsp');
INSERT INTO `sys_resources` VALUES ('4', '2', 'fwqyxjk', null, '服务器管理', '2', '1', '1', 'jsp/daily/serverMonitoring.jsp');
INSERT INTO `sys_resources` VALUES ('5', '2', 'yyxtyxjk', null, '应用系统管理', '3', '1', '1', 'jsp/daily/systemState.jsp');
INSERT INTO `sys_resources` VALUES ('9', '2', 'qpyc', null, '领导只签批一次公文', '5', '2', '1', 'jsp/problem/leaderSignOne.jsp');
INSERT INTO `sys_resources` VALUES ('10', '2', 'qpdc', null, '领导签批多次公文', '6', '2', '1', 'jsp/problem/leaderSignMore.jsp');
INSERT INTO `sys_resources` VALUES ('11', '2', 'ldsbddw', null, '领导收不到公文', '7', '2', '1', 'jsp/problem/docNotReceive.jsp');
INSERT INTO `sys_resources` VALUES ('12', '2', 'oawjzhrz', null, 'OA文件转换日志', '8', '2', '1', 'http://10.171.251.70:8080/asopdc/');
INSERT INTO `sys_resources` VALUES ('13', '2', 'xqgl', null, '需求管理', '9', '3', '0', 'jsp/requirement/requireManage.jsp');
INSERT INTO `sys_resources` VALUES ('14', '2', 'xtgx', null, '系统更新', '10', '3', '0', 'jsp/requirement/systemUpdate.jsp');
INSERT INTO `sys_resources` VALUES ('15', '1', 'xmgl', null, '项目管理', '4', '0', '0', 'jsp/project/onlineSystem.jsp');
INSERT INTO `sys_resources` VALUES ('16', '2', 'sxxt', null, '上线系统', '11', '15', '0', 'jsp/project/onlineSystem.jsp');
INSERT INTO `sys_resources` VALUES ('17', '1', 'fwqgl', null, '服务器管理', '4', '0', '0', 'jsp/server/formalServer.jsp');
INSERT INTO `sys_resources` VALUES ('18', '2', 'zshjfwq', null, '正式环境服务器', '12', '17', '0', 'jsp/server/formalServer.jsp');
INSERT INTO `sys_resources` VALUES ('19', '2', 'wsxxt', null, '未上线系统', '13', '15', '0', 'jsp/project/offlineSystem.jsp');
INSERT INTO `sys_resources` VALUES ('20', '2', 'xtdj', null, '系统登记', '14', '15', '0', 'jsp/project/registerSystem.jsp');
INSERT INTO `sys_resources` VALUES ('21', '2', 'yshjfwq', null, '演示环境服务器', '15', '17', '0', 'jsp/server/demonServer.jsp');
INSERT INTO `sys_resources` VALUES ('22', '2', 'qtfwq', null, '其他服务器', '16', '17', '0', 'jsp/server/otherServer.jsp');
INSERT INTO `sys_resources` VALUES ('23', '1', 'xtgl', null, '系统管理', '70', '0', '1', 'jsp/user/user.jsp');
INSERT INTO `sys_resources` VALUES ('24', '2', 'yhgl', null, '用户管理', '17', '23', '1', 'jsp/user/user.jsp');
INSERT INTO `sys_resources` VALUES ('25', '2', 'jsgl', null, '角色管理', '18', '23', '1', 'jsp/user/role/role.jsp');
INSERT INTO `sys_resources` VALUES ('26', '2', 'qxgl', null, '权限管理', '19', '23', '1', 'jsp/user/privilege/privilege.jsp');
INSERT INTO `sys_resources` VALUES ('27', '2', 'cdgl', null, '菜单管理', '20', '23', '1', 'jsp/user/menu/menuResources.jsp');
INSERT INTO `sys_resources` VALUES ('28', '2', 'xgmm', null, '修改密码', '21', '23', '1', 'jsp/user/editPwd.jsp');
INSERT INTO `sys_resources` VALUES ('29', '2', 'zdxgl', null, '字典项管理', '21', '23', '1', 'jsp/user/dict/dictList.jsp');
INSERT INTO `sys_resources` VALUES ('30', '1', 'kfcs', null, '开发测试', '6', '0', '0', 'jsp/test/test.jsp');
INSERT INTO `sys_resources` VALUES ('31', '2', 'yhlb', null, '用户列表', '23', '30', '1', 'jsp/test/test.jsp');
INSERT INTO `sys_resources` VALUES ('33', '1', 'ywjsc', null, '运维驾驶舱', '1', '0', '1', 'jsp/monitor');
INSERT INTO `sys_resources` VALUES ('34', '1', 'gjgl', null, '告警管理', '20', '0', '1', 'jsp/warn');
INSERT INTO `sys_resources` VALUES ('35', '2', 'gjgzsz', null, '告警规则设置', '1', '34', '1', 'jsp/warning/warningset/warninglist.jsp');
INSERT INTO `sys_resources` VALUES ('36', '2', 'gjrz', null, '告警日志', '2', '34', '1', 'jsp/warning/warningLog/warningLog.jsp');
INSERT INTO `sys_resources` VALUES ('37', '1', 'ywrz', null, '运维日志', '50', '0', '1', 'ywrz');
INSERT INTO `sys_resources` VALUES ('38', '2', 'xjjl', null, '巡检记录', '1', '37', '1', 'jsp/operationalLog/checkRecord/checkRecord.jsp');
INSERT INTO `sys_resources` VALUES ('39', '2', 'ywrz2', null, '运维日志', '2', '37', '1', 'jsp/operationalLog/maintainRecord.jsp');
INSERT INTO `sys_resources` VALUES ('40', '2', 'ywyb', null, '运维月报', '3', '37', '1', 'jsp/operationalLog/maintainStatics.jsp');
INSERT INTO `sys_resources` VALUES ('41', '2', 'sjkyxjk', null, '数据库管理', '4', '1', '1', 'jsp/daily/database/database.jsp');
INSERT INTO `sys_resources` VALUES ('42', '2', 'zjjyxjk', null, '中间件管理', '5', '1', '1', 'jsp/daily/middleware/middleware.jsp');
INSERT INTO `sys_resources` VALUES ('43', '2', 'jkqk', null, '驾驶舱', '1', '33', '1', 'jsp/cockpit/cockpit.jsp');
INSERT INTO `sys_resources` VALUES ('44', '2', 'ywgl', null, '监控台', '2', '33', '1', 'jsp/cockpit/operation.jsp');
INSERT INTO `sys_resources` VALUES ('45', '2', 'gxsq', null, '更新申请', '12', '37', '1', 'jsp/operationalLog/applyUpdate.jsp');
INSERT INTO `sys_resources` VALUES ('46', '2', 'cwrz', null, '错误日志', '2', '34', '1', 'jsp/warning/errorLog/errorlog.jsp');
INSERT INTO `sys_resources` VALUES ('47', '1', 'gjtz', null, '告警通知', '40', '0', '1', 'gjtz');
INSERT INTO `sys_resources` VALUES ('48', '2', 'dxxtz', null, '短消息通知', '1', '47', '1', 'jsp/warningNote/inform/inform.jsp');
INSERT INTO `sys_resources` VALUES ('49', '2', 'dxtz', null, '短信通知', '10', '47', '1', 'jsp/warningNote/sms/sms.jsp');
INSERT INTO `sys_resources` VALUES ('50', '2', 'yjtz', null, '邮件通知', '30', '47', '1', 'jsp/warningNote/mail/mail.jsp');
INSERT INTO `sys_resources` VALUES ('51', '2', 'yct', null, '异常统计', '12', '33', '1', 'jsp/cockpit/exception.jsp');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleCreatTime` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `roleState` int(11) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '2017-10-23 09:48:49', '管理员', '1');
INSERT INTO `sys_role` VALUES ('6', '2018-04-11 15:13:22', '系统工程师', '1');

-- ----------------------------
-- Table structure for `sys_role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rPrivState` int(11) NOT NULL,
  `roId` bigint(20) DEFAULT NULL,
  `rsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7612814E869C562F` (`roId`),
  KEY `FK7612814E591A5440` (`rsId`),
  CONSTRAINT `FK7612814E591A5440` FOREIGN KEY (`rsId`) REFERENCES `sys_resources` (`resourcesId`),
  CONSTRAINT `FK7612814E869C562F` FOREIGN KEY (`roId`) REFERENCES `sys_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('32', '1', '1', '1');
INSERT INTO `sys_role_resources` VALUES ('33', '1', '1', '4');
INSERT INTO `sys_role_resources` VALUES ('34', '1', '1', '5');
INSERT INTO `sys_role_resources` VALUES ('38', '2', '1', '2');
INSERT INTO `sys_role_resources` VALUES ('39', '2', '1', '9');
INSERT INTO `sys_role_resources` VALUES ('40', '2', '1', '10');
INSERT INTO `sys_role_resources` VALUES ('41', '2', '1', '11');
INSERT INTO `sys_role_resources` VALUES ('42', '2', '1', '12');
INSERT INTO `sys_role_resources` VALUES ('43', '2', '1', '3');
INSERT INTO `sys_role_resources` VALUES ('44', '2', '1', '13');
INSERT INTO `sys_role_resources` VALUES ('45', '2', '1', '14');
INSERT INTO `sys_role_resources` VALUES ('46', '2', '1', '15');
INSERT INTO `sys_role_resources` VALUES ('47', '2', '1', '16');
INSERT INTO `sys_role_resources` VALUES ('48', '2', '1', '19');
INSERT INTO `sys_role_resources` VALUES ('49', '2', '1', '20');
INSERT INTO `sys_role_resources` VALUES ('50', '2', '1', '17');
INSERT INTO `sys_role_resources` VALUES ('51', '2', '1', '18');
INSERT INTO `sys_role_resources` VALUES ('52', '2', '1', '21');
INSERT INTO `sys_role_resources` VALUES ('53', '2', '1', '22');
INSERT INTO `sys_role_resources` VALUES ('54', '1', '1', '23');
INSERT INTO `sys_role_resources` VALUES ('55', '1', '1', '24');
INSERT INTO `sys_role_resources` VALUES ('56', '1', '1', '25');
INSERT INTO `sys_role_resources` VALUES ('57', '1', '1', '26');
INSERT INTO `sys_role_resources` VALUES ('58', '1', '1', '27');
INSERT INTO `sys_role_resources` VALUES ('59', '1', '1', '28');
INSERT INTO `sys_role_resources` VALUES ('60', '2', '1', '29');
INSERT INTO `sys_role_resources` VALUES ('61', '1', '1', '30');
INSERT INTO `sys_role_resources` VALUES ('62', '2', '1', '31');
INSERT INTO `sys_role_resources` VALUES ('157', '1', '1', '33');
INSERT INTO `sys_role_resources` VALUES ('158', '1', '1', '34');
INSERT INTO `sys_role_resources` VALUES ('159', '1', '1', '35');
INSERT INTO `sys_role_resources` VALUES ('160', '1', '1', '36');
INSERT INTO `sys_role_resources` VALUES ('161', '2', '1', '37');
INSERT INTO `sys_role_resources` VALUES ('162', '2', '1', '38');
INSERT INTO `sys_role_resources` VALUES ('163', '2', '1', '39');
INSERT INTO `sys_role_resources` VALUES ('164', '2', '1', '40');
INSERT INTO `sys_role_resources` VALUES ('165', '1', '1', '41');
INSERT INTO `sys_role_resources` VALUES ('166', '1', '1', '42');
INSERT INTO `sys_role_resources` VALUES ('167', '1', '1', '43');
INSERT INTO `sys_role_resources` VALUES ('168', '1', '1', '44');
INSERT INTO `sys_role_resources` VALUES ('169', '2', '1', '45');
INSERT INTO `sys_role_resources` VALUES ('170', '1', '1', '46');
INSERT INTO `sys_role_resources` VALUES ('171', '1', '1', '47');
INSERT INTO `sys_role_resources` VALUES ('172', '1', '1', '48');
INSERT INTO `sys_role_resources` VALUES ('173', '1', '1', '49');
INSERT INTO `sys_role_resources` VALUES ('174', '1', '1', '50');
INSERT INTO `sys_role_resources` VALUES ('194', '1', '1', '51');
INSERT INTO `sys_role_resources` VALUES ('195', '1', '6', '33');
INSERT INTO `sys_role_resources` VALUES ('196', '1', '6', '43');
INSERT INTO `sys_role_resources` VALUES ('197', '1', '6', '44');
INSERT INTO `sys_role_resources` VALUES ('198', '1', '6', '51');
INSERT INTO `sys_role_resources` VALUES ('199', '1', '6', '1');
INSERT INTO `sys_role_resources` VALUES ('200', '1', '6', '4');
INSERT INTO `sys_role_resources` VALUES ('201', '1', '6', '5');
INSERT INTO `sys_role_resources` VALUES ('202', '1', '6', '41');
INSERT INTO `sys_role_resources` VALUES ('203', '1', '6', '42');
INSERT INTO `sys_role_resources` VALUES ('204', '1', '6', '34');
INSERT INTO `sys_role_resources` VALUES ('205', '1', '6', '35');
INSERT INTO `sys_role_resources` VALUES ('206', '1', '6', '36');
INSERT INTO `sys_role_resources` VALUES ('207', '1', '6', '46');
INSERT INTO `sys_role_resources` VALUES ('208', '1', '6', '47');
INSERT INTO `sys_role_resources` VALUES ('209', '1', '6', '48');
INSERT INTO `sys_role_resources` VALUES ('210', '1', '6', '49');
INSERT INTO `sys_role_resources` VALUES ('211', '1', '6', '50');
INSERT INTO `sys_role_resources` VALUES ('212', '2', '6', '37');
INSERT INTO `sys_role_resources` VALUES ('213', '2', '6', '38');
INSERT INTO `sys_role_resources` VALUES ('214', '2', '6', '39');
INSERT INTO `sys_role_resources` VALUES ('215', '2', '6', '40');
INSERT INTO `sys_role_resources` VALUES ('216', '2', '6', '45');
INSERT INTO `sys_role_resources` VALUES ('217', '2', '6', '2');
INSERT INTO `sys_role_resources` VALUES ('218', '2', '6', '9');
INSERT INTO `sys_role_resources` VALUES ('219', '2', '6', '10');
INSERT INTO `sys_role_resources` VALUES ('220', '2', '6', '11');
INSERT INTO `sys_role_resources` VALUES ('221', '2', '6', '12');
INSERT INTO `sys_role_resources` VALUES ('222', '2', '6', '23');
INSERT INTO `sys_role_resources` VALUES ('223', '2', '6', '24');
INSERT INTO `sys_role_resources` VALUES ('224', '2', '6', '25');
INSERT INTO `sys_role_resources` VALUES ('225', '2', '6', '26');
INSERT INTO `sys_role_resources` VALUES ('226', '2', '6', '27');
INSERT INTO `sys_role_resources` VALUES ('227', '2', '6', '28');
INSERT INTO `sys_role_resources` VALUES ('228', '2', '6', '29');
INSERT INTO `sys_role_resources` VALUES ('229', '2', '6', '31');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `createUserId` bigint(20) NOT NULL,
  `isManager` int(11) DEFAULT NULL,
  `loginName` varchar(100) DEFAULT NULL,
  `loginPwd` varchar(100) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `modifyUserId` bigint(20) NOT NULL,
  `perId` bigint(20) DEFAULT NULL,
  `userOrder` int(11) NOT NULL,
  `userState` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2017-10-23 09:42:23', '1', '2', 'admin', '123456', null, '1', '1', '0', '1');
INSERT INTO `sys_user` VALUES ('9', null, '1', null, 'cuiyongjia', '123456', null, '1', '9', '1', '1');

-- ----------------------------
-- Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `urid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rId` bigint(20) DEFAULT NULL,
  `uRState` int(11) NOT NULL,
  `usId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`urid`),
  KEY `FKACB42DFB86688F24` (`rId`),
  CONSTRAINT `FKACB42DFB86688F24` FOREIGN KEY (`rId`) REFERENCES `sys_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1', '1', '1');
INSERT INTO `sys_user_roles` VALUES ('9', '6', '1', '9');
