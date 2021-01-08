/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.32 : Database - third_party
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`third_party` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `third_party`;

/*Table structure for table `t_cron` */

DROP TABLE IF EXISTS `t_cron`;

CREATE TABLE `t_cron` (
  `cron_id` varchar(32) NOT NULL COMMENT '定时任务表主键',
  `cron_name` varchar(200) DEFAULT NULL COMMENT '定时任务名',
  `cron_service_type` varchar(100) DEFAULT NULL COMMENT '定时任务业务类型',
  `cron_rule` varchar(200) DEFAULT NULL COMMENT '定时任务规则',
  `cron_create_time` varchar(20) DEFAULT NULL COMMENT '定时任务创建时间',
  `cron_modify_time` varchar(20) DEFAULT NULL COMMENT '定时任务修改时间',
  PRIMARY KEY (`cron_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_notify_config` */

DROP TABLE IF EXISTS `t_notify_config`;

CREATE TABLE `t_notify_config` (
  `id` varchar(32) NOT NULL COMMENT '通知配置表主键',
  `type` varchar(50) NOT NULL COMMENT '通知类型',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否启用 0:否 1:是',
  `config_data` longtext COMMENT '配置参数(json数据对象)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付方式参数表';

/*Table structure for table `t_sms_log` */

DROP TABLE IF EXISTS `t_sms_log`;

CREATE TABLE `t_sms_log` (
  `id` varchar(32) NOT NULL COMMENT '短信发送表日志',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `biz_id` varchar(100) DEFAULT NULL COMMENT '短信发送回执ID,用于查询具体的发送状态',
  `code` varchar(50) DEFAULT NULL COMMENT '请求状态码,返回OK或者其他错误码',
  `message` varchar(200) DEFAULT NULL COMMENT '状态码的描述',
  `request_id` varchar(100) DEFAULT NULL COMMENT '请求ID',
  `state` varchar(1) DEFAULT '0' COMMENT '处理状态 0:未处理 1:已处理',
  `send_date` varchar(20) DEFAULT NULL COMMENT '短信发送日期和时间',
  `receive_date` varchar(20) DEFAULT NULL COMMENT '短信接收日期和时间',
  `template_code` varchar(50) DEFAULT NULL COMMENT '短信模板ID',
  `content` varchar(512) DEFAULT NULL COMMENT '短信内容',
  `status` varchar(2) DEFAULT NULL COMMENT '短信发送状态 1:等待回执 2:发送失败 3:发送成功',
  `err_code` varchar(50) DEFAULT NULL COMMENT '运营商短信状态码',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_system_config` */

DROP TABLE IF EXISTS `t_system_config`;

CREATE TABLE `t_system_config` (
  `id` varchar(32) NOT NULL COMMENT '系统配置表主键',
  `resource_domain` varchar(100) DEFAULT NULL COMMENT '资源文件域名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_third_party_config` */

DROP TABLE IF EXISTS `t_third_party_config`;

CREATE TABLE `t_third_party_config` (
  `id` varchar(32) NOT NULL COMMENT '第三方服务配置表主键',
  `config_key` varchar(100) DEFAULT NULL COMMENT '键',
  `config_value` varchar(1024) DEFAULT NULL COMMENT '值',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_third_party_dic` */

DROP TABLE IF EXISTS `t_third_party_dic`;

CREATE TABLE `t_third_party_dic` (
  `id` varchar(32) NOT NULL COMMENT '第三方服务字典表主键',
  `name` varchar(100) DEFAULT NULL COMMENT '第三方服务名称',
  `logo` varchar(100) DEFAULT NULL COMMENT '第三方服务logo',
  `key` varchar(100) DEFAULT NULL COMMENT '第三方服务key',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_tyc_company` */

DROP TABLE IF EXISTS `t_tyc_company`;

CREATE TABLE `t_tyc_company` (
  `company_id` varchar(32) NOT NULL COMMENT '天眼查企业表主键',
  `reg_number` varchar(100) DEFAULT NULL COMMENT '注册号',
  `reg_status` varchar(50) DEFAULT NULL COMMENT '经营状态',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `reg_capital` varchar(50) DEFAULT NULL COMMENT '注册资本',
  `company_type` int(2) DEFAULT NULL COMMENT '公司类型 1:公司 2:香港公司 3:社会组织 4:律所 5:事业单位 6:基金会',
  `name` varchar(100) DEFAULT NULL COMMENT '公司名',
  `id` bigint(20) DEFAULT NULL COMMENT '公司ID',
  `org_number` varchar(50) DEFAULT NULL COMMENT '组织机构代码',
  `type` int(1) DEFAULT NULL COMMENT '法人类型 1:个人 2:公司',
  `base` varchar(50) DEFAULT NULL COMMENT '省份简称',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `legal_person_name` varchar(50) DEFAULT NULL COMMENT '法人',
  `staff_num_range` varchar(100) DEFAULT NULL COMMENT '人员规模',
  `from_time` bigint(20) DEFAULT NULL COMMENT '经营开始时间(毫秒数)',
  `to_time` bigint(20) DEFAULT NULL COMMENT '经营结束时间(毫秒数)',
  `bond_name` varchar(20) DEFAULT NULL COMMENT '股票名',
  `bond_num` varchar(20) DEFAULT NULL COMMENT '股票号',
  `is_micro_ent` int(1) DEFAULT NULL COMMENT '是否是小微企业 0:不是 1:是',
  `used_bond_name` varchar(20) DEFAULT NULL COMMENT '股票曾用名',
  `percentile_score` int(11) DEFAULT NULL COMMENT '企业评分(万分制)',
  `reg_institute` varchar(100) DEFAULT NULL COMMENT '登记机关',
  `reg_location` varchar(100) DEFAULT NULL COMMENT '注册地址',
  `industry` varchar(100) DEFAULT NULL COMMENT '行业',
  `approved_time` bigint(20) DEFAULT NULL COMMENT '核准时间(毫秒数)',
  `social_staff_num` int(11) DEFAULT NULL COMMENT '参保人数',
  `tags` varchar(100) DEFAULT NULL COMMENT '企业标签',
  `tax_number` varchar(100) DEFAULT NULL COMMENT '纳税人识别号',
  `business_scope` varchar(2056) DEFAULT NULL COMMENT '经营范围',
  `property3` varchar(100) DEFAULT NULL COMMENT '英文名',
  `alias` varchar(100) DEFAULT NULL COMMENT '简称',
  `estiblish_time` bigint(20) DEFAULT NULL COMMENT '成立日期(毫秒数)',
  `update_times` bigint(20) DEFAULT NULL COMMENT '更新时间(毫秒数)',
  `bond_type` varchar(2) DEFAULT NULL COMMENT '股票类型',
  `actual_capital` varchar(50) DEFAULT NULL COMMENT '实收注册资金',
  `company_org_type` varchar(200) DEFAULT NULL COMMENT '企业类型',
  `reg_capital_currency` varchar(10) DEFAULT NULL COMMENT '注册资本币种 人民币 美元 欧元 等',
  `actual_capital_currency` varchar(10) DEFAULT NULL COMMENT '	实收注册资本币种 人民币 美元 欧元 等',
  `revoke_date` bigint(20) DEFAULT NULL COMMENT '吊销日期(毫秒数)',
  `revoke_reason` varchar(200) DEFAULT NULL COMMENT '吊销原因',
  `cancel_date` bigint(20) DEFAULT NULL COMMENT '注销日期(毫秒数)',
  `cancel_reason` varchar(200) DEFAULT NULL COMMENT '注销原因',
  `history_names` varchar(1024) DEFAULT NULL COMMENT '曾用名',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_tyc_company_holder` */

DROP TABLE IF EXISTS `t_tyc_company_holder`;

CREATE TABLE `t_tyc_company_holder` (
  `holder_id` varchar(32) NOT NULL COMMENT '天眼查企业股东表主键',
  `company_id` bigint(20) DEFAULT NULL COMMENT '企业ID(天眼查企业ID)',
  `name` varchar(50) DEFAULT NULL COMMENT '股东名',
  `alias` varchar(50) DEFAULT NULL COMMENT '简称',
  `id` bigint(20) DEFAULT NULL COMMENT '股东ID(天眼查股东ID)',
  `logo` varchar(150) DEFAULT NULL COMMENT 'logo',
  `type` int(2) DEFAULT NULL COMMENT '1:公司 2:人 3:其它',
  `capital` varchar(2048) DEFAULT NULL COMMENT '认缴',
  `capitalActl` varchar(2048) DEFAULT NULL COMMENT '实缴',
  PRIMARY KEY (`holder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
