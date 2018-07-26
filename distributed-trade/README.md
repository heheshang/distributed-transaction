
#### pay_order 数据库
```sql
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : pay_order

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-26 17:45:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seq_table
-- ----------------------------
DROP TABLE IF EXISTS `seq_table`;
CREATE TABLE `seq_table` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `CURRENT_VALUE` bigint(20) NOT NULL DEFAULT '1000000002',
  `INCREMENT` smallint(6) NOT NULL DEFAULT '1',
  `REMARK` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seq_table
-- ----------------------------
INSERT INTO `seq_table` VALUES ('BANK_ORDER_NO_SEQ', '1000259610', '1', '交易--银行订单号');
INSERT INTO `seq_table` VALUES ('TRX_NO_SEQ', '1000259610', '1', '交易--支付流水号');

-- ----------------------------
-- Table structure for trade_payment_order
-- ----------------------------
DROP TABLE IF EXISTS `trade_payment_order`;
CREATE TABLE `trade_payment_order` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态(参考枚举:OrderStatusEnum)',
  `product_name` varchar(300) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(30) NOT NULL COMMENT '商户订单号',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `merchant_name` varchar(100) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(100) NOT NULL COMMENT '商户编号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_date` date DEFAULT NULL COMMENT '下单日期',
  `order_ip` varchar(50) DEFAULT NULL COMMENT '下单IP(客户端IP,在网关页面获取)',
  `order_referer_url` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `return_url` varchar(600) DEFAULT NULL COMMENT '页面回调通知URL',
  `notify_url` varchar(600) DEFAULT NULL COMMENT '后台异步通知URL',
  `cancel_reason` varchar(600) DEFAULT NULL COMMENT '订单撤销原因',
  `order_period` smallint(6) DEFAULT NULL COMMENT '订单有效期(单位分钟)',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '支付备注',
  `trx_type` varchar(30) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付方式类型编号',
  `pay_type_name` varchar(100) DEFAULT NULL COMMENT '支付方式类型名称',
  `fund_into_type` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `field1` varchar(500) DEFAULT NULL,
  `field2` varchar(500) DEFAULT NULL,
  `field3` varchar(500) DEFAULT NULL,
  `field4` varchar(500) DEFAULT NULL,
  `field5` varchar(500) DEFAULT NULL,
  `trx_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_KEY_2` (`merchant_order_no`,`merchant_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='roncoo pay 龙果支付 支付订单表';

-- ----------------------------
-- Records of trade_payment_order
-- ----------------------------

-- ----------------------------
-- Table structure for trade_payment_record
-- ----------------------------
DROP TABLE IF EXISTS `trade_payment_record`;
CREATE TABLE `trade_payment_record` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(30) DEFAULT NULL COMMENT '状态(参考枚举:PaymentRecordStatusEnum)',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `product_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `trx_no` varchar(50) NOT NULL COMMENT '支付流水号',
  `bank_order_no` varchar(50) DEFAULT NULL COMMENT '银行订单号',
  `bank_trx_no` varchar(50) DEFAULT NULL COMMENT '银行流水号',
  `merchant_name` varchar(300) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(50) NOT NULL COMMENT '商家编号',
  `payer_user_no` varchar(50) DEFAULT NULL COMMENT '付款人编号',
  `payer_name` varchar(60) DEFAULT NULL COMMENT '付款人名称',
  `payer_pay_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方支付金额',
  `payer_fee` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方手续费',
  `payer_account_type` varchar(30) DEFAULT NULL COMMENT '付款方账户类型(参考账户类型枚举:AccountTypeEnum)',
  `receiver_user_no` varchar(15) DEFAULT NULL COMMENT '收款人编号',
  `receiver_name` varchar(60) DEFAULT NULL COMMENT '收款人名称',
  `receiver_pay_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方支付金额',
  `receiver_fee` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方手续费',
  `receiver_account_type` varchar(30) DEFAULT NULL COMMENT '收款方账户类型(参考账户类型枚举:AccountTypeEnum)',
  `order_ip` varchar(30) DEFAULT NULL COMMENT '下单IP(客户端IP,从网关中获取)',
  `order_referer_url` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `plat_income` decimal(20,6) DEFAULT NULL COMMENT '平台收入',
  `fee_rate` decimal(20,6) DEFAULT NULL COMMENT '费率',
  `plat_cost` decimal(20,6) DEFAULT NULL COMMENT '平台成本',
  `plat_profit` decimal(20,6) DEFAULT NULL COMMENT '平台利润',
  `return_url` varchar(600) DEFAULT NULL COMMENT '页面回调通知URL',
  `notify_url` varchar(600) DEFAULT NULL COMMENT '后台异步通知URL',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `pay_success_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `trx_type` varchar(30) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付方式类型编号',
  `pay_type_name` varchar(100) DEFAULT NULL COMMENT '支付方式类型名称',
  `fund_into_type` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `field1` varchar(500) DEFAULT NULL,
  `field2` varchar(500) DEFAULT NULL,
  `field3` varchar(500) DEFAULT NULL,
  `field4` varchar(500) DEFAULT NULL,
  `field5` varchar(500) DEFAULT NULL,
  `bank_return_msg` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';

-- ----------------------------
-- Records of trade_payment_record
-- ----------------------------

```
#### pay_product数据库

```sql 

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : pay_product

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-26 17:48:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seq_table
-- ----------------------------
DROP TABLE IF EXISTS `seq_table`;
CREATE TABLE `seq_table` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `CURRENT_VALUE` bigint(20) NOT NULL DEFAULT '1000000002',
  `INCREMENT` smallint(6) NOT NULL DEFAULT '1',
  `REMARK` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seq_table
-- ----------------------------
INSERT INTO `seq_table` VALUES ('ACCOUNT_NO_SEQ', '1000000007', '1', '账户--账户编号');
INSERT INTO `seq_table` VALUES ('BANK_ORDER_NO_SEQ', '1000259610', '1', '交易--银行订单号');
INSERT INTO `seq_table` VALUES ('POINT_ACCOUNT_NO_SEQ', '1000000002', '1', '积分--积分账户编号');
INSERT INTO `seq_table` VALUES ('TRX_NO_SEQ', '1000259610', '1', '交易--支付流水号');
INSERT INTO `seq_table` VALUES ('USER_NO_SEQ', '1000000007', '1', '用户--用户编号');

-- ----------------------------
-- Table structure for tcc_pay_product
-- ----------------------------
DROP TABLE IF EXISTS `tcc_pay_product`;
CREATE TABLE `tcc_pay_product` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `status` varchar(36) NOT NULL,
  `product_code` varchar(50) NOT NULL COMMENT '支付产品编号',
  `product_name` varchar(200) NOT NULL COMMENT '支付产品名称',
  `audit_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付产品表';

-- ----------------------------
-- Records of tcc_pay_product
-- ----------------------------
INSERT INTO `tcc_pay_product` VALUES ('5ac23e853c454edcb2bf51624bdbc194', '2016-07-19 17:53:12', '2016-07-19 17:58:21', '0', 'ACTIVE', 'RonCooPay', '龙果支付', 'YES');
INSERT INTO `tcc_pay_product` VALUES ('7e496b1206714527863fa2cf3f836db5', '2016-07-19 18:51:38', '2016-07-21 16:39:58', '0', 'ACTIVE', 'RC', 'RC', 'YES');
INSERT INTO `tcc_pay_product` VALUES ('ec7502bbf1894cb69ede121433594285', '2016-09-17 18:08:50', '2016-09-17 18:09:16', '0', 'UNACTIVE', 'BBB', 'BBB', 'NO');

-- ----------------------------
-- Table structure for tcc_pay_way
-- ----------------------------
DROP TABLE IF EXISTS `tcc_pay_way`;
CREATE TABLE `tcc_pay_way` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT 'version',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `pay_way_code` varchar(50) NOT NULL COMMENT '支付方式编号',
  `pay_way_name` varchar(100) NOT NULL COMMENT '支付方式名称',
  `pay_type_code` varchar(50) NOT NULL COMMENT '支付类型编号',
  `pay_type_name` varchar(100) NOT NULL COMMENT '支付类型名称',
  `pay_product_code` varchar(50) DEFAULT NULL COMMENT '支付产品编号',
  `status` varchar(36) NOT NULL COMMENT '状态(100:正常状态,101非正常状态)',
  `sorts` int(11) DEFAULT '1000' COMMENT '排序(倒序排序,默认值1000)',
  `pay_rate` double NOT NULL COMMENT '商户支付费率',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付方式';

-- ----------------------------
-- Records of tcc_pay_way
-- ----------------------------
INSERT INTO `tcc_pay_way` VALUES ('1431d5a280fc4c51afcfbca9f32822d8', '0', '2016-07-19 17:53:52', null, 'WEIXIN', '微信', 'SCANPAY', '扫码支付', 'RonCooPay', 'ACTIVE', null, '0.8');
INSERT INTO `tcc_pay_way` VALUES ('3ee734a9ed68405c89ead08683cad6c7', '0', '2016-09-17 18:09:05', '2016-09-17 18:09:10', 'TEST_PAY', '测试模拟支付', 'TEST_PAY', '测试模拟支付', 'BBB', 'UNACTIVE', null, '9');
INSERT INTO `tcc_pay_way` VALUES ('a4af58b75b3e40c4bd38ac647e27800f', '0', '2016-07-19 18:51:52', null, 'TEST_PAY', '测试模拟支付', 'TEST_PAY', '测试模拟支付', 'RC', 'ACTIVE', null, '0.8');
INSERT INTO `tcc_pay_way` VALUES ('c5cdda3d8f1e47bca9ac96ffb2ea17b1', '0', '2016-07-19 17:54:16', '2016-07-19 17:54:25', 'ALIPAY', '支付宝', 'DIRECT_PAY', '即时到账', 'RonCooPay', 'ACTIVE', null, '0.8');
INSERT INTO `tcc_pay_way` VALUES ('db522b1446ff4dc2bd6fcd73bc1f3423', '0', '2016-07-21 16:39:51', null, 'TEST_PAY_HTTP_CLIENT', '测试模拟httpclient支付', 'TEST_PAY_HTTP_CLIENT', '测试模拟httpclient支付', 'RC', 'ACTIVE', null, '0.8');
INSERT INTO `tcc_pay_way` VALUES ('dcda9a88435e47b3b6d24df2c6358be1', '0', '2016-07-19 18:52:41', null, 'ALIPAY', '支付宝', 'DIRECT_PAY', '即时到账', 'RC', 'ACTIVE', null, '0.8');
INSERT INTO `tcc_pay_way` VALUES ('ff242c5f9c3e4ea7a857860e57f6ddd4', '0', '2016-07-19 18:52:29', null, 'WEIXIN', '微信', 'SCANPAY', '扫码支付', 'RC', 'ACTIVE', null, '0.8');

-- ----------------------------
-- Table structure for tcc_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tcc_user_info`;
CREATE TABLE `tcc_user_info` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `status` varchar(36) NOT NULL,
  `user_no` varchar(50) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `account_no` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_Key_2` (`account_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='该表用来存放用户的基本信息';

-- ----------------------------
-- Records of tcc_user_info
-- ----------------------------
INSERT INTO `tcc_user_info` VALUES ('0101ff47cd1f43a5b6a52144e9a78e30', '2016-07-24 20:20:26', 'ACTIVE', '88882016072400000006', 'hpt', '99992016072400000006');
INSERT INTO `tcc_user_info` VALUES ('0764455933ce43a093bbe0f60867ee91', '2016-07-22 15:56:26', 'ACTIVE', '88882016072200000005', 'Along', '99992016072200000005');
INSERT INTO `tcc_user_info` VALUES ('18fee8f2fafe4251a5aa2e95e30dc126', '2016-07-21 01:06:51', 'ACTIVE', '88882016072100000004', 'wusc', '99992016072100000004');
INSERT INTO `tcc_user_info` VALUES ('64b098ee76574c5681710b47d10bcd64', '2016-07-24 20:21:45', 'ACTIVE', '88882016072400000007', 'leslie', '99992016072400000007');
INSERT INTO `tcc_user_info` VALUES ('c48f5ac8024a4547878b5708b311215c', '2016-07-19 16:55:09', 'ACTIVE', '88882016071900000003', 'roncoo', '99992016071900000003');

-- ----------------------------
-- Table structure for tcc_user_pay_config
-- ----------------------------
DROP TABLE IF EXISTS `tcc_user_pay_config`;
CREATE TABLE `tcc_user_pay_config` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(36) NOT NULL,
  `audit_status` varchar(45) DEFAULT NULL,
  `is_auto_sett` varchar(36) NOT NULL DEFAULT 'NO',
  `product_code` varchar(50) NOT NULL COMMENT '支付产品编号',
  `product_name` varchar(200) NOT NULL COMMENT '支付产品名称',
  `user_no` varchar(50) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `risk_day` int(11) DEFAULT NULL,
  `pay_key` varchar(50) DEFAULT NULL,
  `fund_into_type` varchar(50) DEFAULT NULL,
  `pay_secret` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付设置表';

-- ----------------------------
-- Records of tcc_user_pay_config
-- ----------------------------
INSERT INTO `tcc_user_pay_config` VALUES ('2a7f8f1c8ebf4379a42a6f88b8b8bc56', '2016-07-22 15:57:40', null, '0', null, 'ACTIVE', 'YES', 'NO', 'RC', 'RC', '88882016072100000004', 'wusc', '1', '8ba459f55ff04f39b0128a3cb4a48f2b', 'PLAT_RECEIVES', '3e2ca2eb1f024570b241d65eb4832c37');
INSERT INTO `tcc_user_pay_config` VALUES ('3eef704ce6ef4f27b39f8b9ba1af71e4', '2016-07-24 20:22:48', null, '0', null, 'ACTIVE', 'YES', 'NO', 'RC', 'RC', '88882016072400000007', 'leslie', '1', '93d1ea2f9f3b448994f39d6efc7746ef', 'PLAT_RECEIVES', 'fad7ea79810c4af7a973c091aa7c6143');
INSERT INTO `tcc_user_pay_config` VALUES ('8d5a1884969a4bf68dc9c19b7a0bdc18', '2016-07-22 15:56:57', null, '0', null, 'ACTIVE', 'YES', 'NO', 'RC', 'RC', '88882016072200000005', 'Along', '1', 'abcf900288114d5fa7fde764966eb2ff', 'PLAT_RECEIVES', 'd94d7c2d56eb4b06828cf746c8be17e6');
INSERT INTO `tcc_user_pay_config` VALUES ('c101c24326554b848f0f497234f129d7', '2016-07-19 17:59:38', '2016-07-21 16:40:17', '0', null, 'ACTIVE', 'YES', 'NO', 'RC', 'RC', '88882016071900000003', 'roncoo', '1', '4c52295065654407b42797cda80dd07d', 'PLAT_RECEIVES', '6606353e0dab4f7b9a723f2d3e3276b7');
INSERT INTO `tcc_user_pay_config` VALUES ('e510d10eed13497d9fafb492688d09d3', '2016-07-24 20:23:11', null, '0', null, 'ACTIVE', 'YES', 'NO', 'RC', 'RC', '88882016072400000006', 'hpt', '1', 'ca6577dff6d647ac882dfb405ceda21e', 'PLAT_RECEIVES', '1b8da6c9b7544856955fcff6bf920f84');

-- ----------------------------
-- Table structure for tcc_user_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `tcc_user_pay_info`;
CREATE TABLE `tcc_user_pay_info` (
  `id_` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(36) NOT NULL,
  `app_id` varchar(50) NOT NULL,
  `app_sectet` varchar(100) DEFAULT NULL,
  `merchant_id` varchar(50) DEFAULT NULL,
  `app_type` varchar(50) DEFAULT NULL,
  `user_no` varchar(50) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `partner_key` varchar(100) DEFAULT NULL,
  `pay_way_code` varchar(50) NOT NULL COMMENT '支付方式编号',
  `pay_way_name` varchar(100) NOT NULL COMMENT '支付方式名称',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='该表用来存放用户开通的第三方支付信息';

-- ----------------------------
-- Records of tcc_user_pay_info
-- ----------------------------
INSERT INTO `tcc_user_pay_info` VALUES ('04a257f258f74d4eb8a2182b627a199f', '2016-07-24 20:22:48', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072400000007', 'leslie', '', 'WEIXIN', '微信');
INSERT INTO `tcc_user_pay_info` VALUES ('1834040201214f9a8ca0557045fc7c72', '2016-07-24 20:23:11', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072400000006', 'hpt', '', 'ALIPAY', '支付宝');
INSERT INTO `tcc_user_pay_info` VALUES ('45ecd39829cb4c009ad9c5a1153cbd57', '2016-07-22 15:56:57', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072200000005', 'Along', '', 'WEIXIN', '微信');
INSERT INTO `tcc_user_pay_info` VALUES ('4821fb3f63db4f58a0fc75a91a38bc04', '2016-07-22 15:57:40', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072100000004', 'wusc', '', 'WEIXIN', '微信');
INSERT INTO `tcc_user_pay_info` VALUES ('4ee1f39ecb0544e792ef5d5df6ec6fb2', '2016-07-24 20:23:11', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072400000006', 'hpt', '', 'WEIXIN', '微信');
INSERT INTO `tcc_user_pay_info` VALUES ('52bca84256154c588d2952b34a68dad4', '2016-07-22 15:56:57', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072200000005', 'Along', '', 'ALIPAY', '支付宝');
INSERT INTO `tcc_user_pay_info` VALUES ('8a23a87d6afb425cb8974110bb015f06', '2016-07-22 15:57:40', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072100000004', 'wusc', '', 'ALIPAY', '支付宝');
INSERT INTO `tcc_user_pay_info` VALUES ('8f3064039e8241029b71be1b8a9be7ce', '2016-07-19 17:59:38', '2016-07-21 16:40:17', '0', null, 'ACTIVE', '', null, '', null, '88882016071900000003', 'roncoo', '', 'ALIPAY', '支付宝');
INSERT INTO `tcc_user_pay_info` VALUES ('981cd025a111452cafb9c103c5df0f9d', '2016-07-24 20:22:48', null, '0', null, 'ACTIVE', '', null, '', null, '88882016072400000007', 'leslie', '', 'ALIPAY', '支付宝');
INSERT INTO `tcc_user_pay_info` VALUES ('d6ecaedb883149a28356d2510b711793', '2016-07-19 17:59:38', '2016-07-21 16:40:17', '0', null, 'ACTIVE', '', null, '', null, '88882016071900000003', 'roncoo', '', 'WEIXIN', '微信');

```