
### pay_message1 数据库
```sql
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : pay_message1

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-26 17:50:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for transaction_message
-- ----------------------------
DROP TABLE IF EXISTS `transaction_message`;
CREATE TABLE `transaction_message` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `message_id` varchar(50) NOT NULL DEFAULT '' COMMENT '消息ID',
  `message_body` longtext NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(100) NOT NULL DEFAULT '' COMMENT '消费队列',
  `message_send_times` smallint(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `areadly_dead` varchar(20) NOT NULL DEFAULT '' COMMENT '是否死亡',
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `field1` varchar(200) DEFAULT NULL COMMENT '扩展字段1',
  `field2` varchar(200) DEFAULT NULL COMMENT '扩展字段2',
  `field3` varchar(200) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`),
  KEY `AK_Key_2` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transaction_message
-- ----------------------------
INSERT INTO `transaction_message` VALUES ('297eb8c4646edfe001646ee0a9f50000', '0', '张三', '李四', '2018-07-06 17:16:15', '2018-07-06 17:15:43', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4646ef7c301646ef7e6890000', '0', '张三', '李四', '2018-07-06 17:41:38', '2018-07-06 17:41:37', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4646efb9601646efbbb590000', '0', '张三', '李四', '2018-07-06 17:45:49', '2018-07-06 17:45:48', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4646efd1401646efd32380000', '2', '张三', '王五111', '2018-07-18 14:23:13', '2018-07-06 17:47:24', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222222222111', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4646f026501646f0282d10000', '0', '张三', '李四', '2018-07-06 17:53:13', '2018-07-06 17:53:13', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4646f05d601646f05f4710000', '0', '张三', '李四', '2018-07-06 17:56:59', '2018-07-06 17:56:58', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4646f091801646f093d010000', '0', '张三', '李四', '2018-07-06 18:00:34', '2018-07-06 18:00:34', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c464820427016482046b790000', '0', '张三', '李四', '2018-07-10 10:28:05', '2018-07-10 10:28:04', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c4649191ac01649191cd4f0000', '0', '张三', '李四', '2018-07-13 10:56:49', '2018-07-13 10:56:49', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222222', '111', '', '', '');
INSERT INTO `transaction_message` VALUES ('297eb8c464a219d70164a21a01e50000', '0', '张三', '李四', '2018-07-16 15:59:31', '2018-07-16 15:59:31', '', '', 'json', 'RECHARGE_QUEUE', '0', 'NO', '2222111111222', '111', '', '', '');

```