
### 数据库结构
```sql

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : gateway

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-26 17:42:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tcc_gateway_record
-- ----------------------------
DROP TABLE IF EXISTS `tcc_gateway_record`;
CREATE TABLE `tcc_gateway_record` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `field1` varchar(200) DEFAULT NULL COMMENT '备注字段1',
  `field2` varchar(200) DEFAULT NULL COMMENT '备注字段2',
  `field3` varchar(200) DEFAULT NULL COMMENT '备注字段3',
  `notify_url` varchar(200) NOT NULL COMMENT '异步通知地址',
  `order_date` date NOT NULL COMMENT '订单日期',
  `order_ip` varchar(255) DEFAULT NULL COMMENT '订单Ip地址',
  `order_no` varchar(32) NOT NULL COMMENT '商户订单号',
  `order_period` int(11) DEFAULT NULL COMMENT '订单有效期',
  `order_price` varchar(255) NOT NULL COMMENT '订单金额',
  `order_time` datetime NOT NULL COMMENT '订单日期',
  `pay_key` varchar(255) NOT NULL COMMENT '企业支付KEY',
  `pay_way_code` varchar(255) NOT NULL COMMENT '支付方式编码 支付宝: ALIPAY  微信:WEIXIN',
  `product_name` varchar(50) NOT NULL COMMENT '商品名称',
  `return_url` varchar(255) DEFAULT NULL COMMENT '页面通知返回url',
  `sign` varchar(255) NOT NULL COMMENT '签名',
  `field4` varchar(200) DEFAULT NULL,
  `field5` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_index` (`order_no`,`pay_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```