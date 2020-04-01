-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.22-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 springsecurity-rbac 的数据库结构
CREATE DATABASE IF NOT EXISTS `springsecurity-rbac` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `springsecurity-rbac`;

-- 导出  表 springsecurity-rbac.tb_basic_modular 结构
CREATE TABLE IF NOT EXISTS `tb_basic_modular` (
  `modular_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块编号',
  `modular_name` varchar(200) DEFAULT NULL COMMENT '模块名称',
  `modular_sort` int(11) DEFAULT NULL COMMENT '模块排序',
  PRIMARY KEY (`modular_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='模块表';

-- 正在导出表  springsecurity-rbac.tb_basic_modular 的数据：~22 rows (大约)
/*!40000 ALTER TABLE `tb_basic_modular` DISABLE KEYS */;
INSERT INTO `tb_basic_modular` (`modular_id`, `modular_name`, `modular_sort`) VALUES
	(1, '系统管理模块', 0),
	(2, '业务管理模块', 1),
	(33, '测试模块', 2);
/*!40000 ALTER TABLE `tb_basic_modular` ENABLE KEYS */;

-- 导出  表 springsecurity-rbac.tb_basic_permission 结构
CREATE TABLE IF NOT EXISTS `tb_basic_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `modular_id` int(11) DEFAULT NULL COMMENT '模块编号',
  `permission_name` varchar(200) DEFAULT NULL COMMENT '权限名称',
  `permission_action` varchar(1024) DEFAULT NULL COMMENT '权限路径',
  `permission_word` varchar(50) DEFAULT NULL COMMENT '权限标识符',
  `permission_parent` int(11) DEFAULT NULL COMMENT '权限的父权限，如果为0，是菜单',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 正在导出表  springsecurity-rbac.tb_basic_permission 的数据：~28 rows (大约)
/*!40000 ALTER TABLE `tb_basic_permission` DISABLE KEYS */;
INSERT INTO `tb_basic_permission` (`permission_id`, `modular_id`, `permission_name`, `permission_action`, `permission_word`, `permission_parent`) VALUES
	(1, 1, '后台用户管理', '/user/userList/0', 'USER_LIST', 0),
	(2, 1, '角色管理', '/role/roleList/0', 'ROLE_LIST', 0),
	(3, 1, '权限管理', '/permission/permissionList/0', 'PERMISSIOIN_LIST', 0),
	(4, 1, '模块管理', '/modular/modularList/0', 'MODULAR_LIST', 0),
	(5, 1, '修改密码', '/settings', 'SETTING', 0),
	(6, 1, '后台用户管理-增加', '/user/userAdd', 'USER_ADD', 1),
	(7, 1, '后台用户管理-编辑', '/user/userEdit', 'USER_EDIT', 1),
	(8, 1, '后台用户管理-删除', '/user/userRemove', 'USER_REMOVE', 1),
	(9, 1, '角色管理-增加', '/role/roleAdd', 'ROLE_ADD', 2),
	(10, 1, '角色管理-编辑', '/role/roleEdit', 'ROLE_EDIT', 2),
	(11, 1, '角色管理-删除', '/role/roleRemove', 'ROLE_REMOVE', 2),
	(12, 1, '权限管理-增加', '/permission/permissionAdd', 'PERMISSION_ADD', 3),
	(13, 1, '权限管理-编辑', '/permission/permissionEdit', 'PERMISSION_EDIT', 3),
	(14, 1, '权限管理-删除', '/permission/permissionRemove', 'PERMISSION_REMOVE', 3),
	(15, 1, '模块管理-增加', '/modular/modularAdd', 'MODULAR_ADD', 4),
	(16, 1, '模块管理-编辑', '/modular/modularEdit', 'MODULAR_LIST', 4),
	(17, 1, '模块管理-删除', '/modular/modularRemove', 'MODULAR_REMOVE', 4),
	(18, 1, '后台用户管理-To增加', '/user/userToAdd', 'USER_TO_ADD', 1),
	(19, 1, '后台用户管理-To编辑', '/user/userToEdit', 'USER_TO_EDIT', 1),
	(20, 1, '角色管理-To增加', '/role/roleToAdd', 'ROLE_TO_ADD', 2),
	(21, 1, '角色管理-To编辑', '/role/roleToEdit', 'ROLE_TO_EDIT', 2),
	(22, 1, '权限管理-To增加', '/permission/permissionToAdd', 'PERMISSION_TO_ADD', 3),
	(23, 1, '权限管理-To编辑', '/permission/permissionToEdit', 'PERMISSION_TO_EDIT', 3),
	(24, 1, '模块管理-To增加', '/modular/modularToAdd', 'MODULAR_TO_ADD', 4),
	(25, 1, '模块管理-To编辑', '/modular/modularToEdit', 'MODULAR_TO_EDIT', 4),
	(27, 1, '模块批量删除', '/modular/removeAllByIds', 'MODULAR_REMOVE_ALL', 4),
	(28, 1, '后台用户批量删除', '/user/removeAllByIds', 'USER_REMOVE_ALL', 1),
	(30, 2, '订单管理', '/order/orderList', 'ORDER_LIST', 0),
	(31, 33, '测试权限', '/test/testList', 'TEST_LIST', 0),
	(32, 1, '修改密码-提交', '/settingsSubmit', 'SETTING_SUBMIT', 5);
/*!40000 ALTER TABLE `tb_basic_permission` ENABLE KEYS */;

-- 导出  表 springsecurity-rbac.tb_basic_role 结构
CREATE TABLE IF NOT EXISTS `tb_basic_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(200) DEFAULT NULL COMMENT '角色名',
  `role_permissions` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 正在导出表  springsecurity-rbac.tb_basic_role 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `tb_basic_role` DISABLE KEYS */;
INSERT INTO `tb_basic_role` (`role_id`, `role_name`, `role_permissions`) VALUES
	(1, '配置管理员', '1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 30, 31'),
	(2, '业务员', '1,2,3,4'),
	(3, '审核员', '4,5,6,7'),
	(4, '测试角色', '30'),
	(5, '测试角色123', NULL),
	(6, '测试角色111', NULL),
	(7, '测试角色12367', '1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 30'),
	(8, '测试角色', '31');
/*!40000 ALTER TABLE `tb_basic_role` ENABLE KEYS */;

-- 导出  表 springsecurity-rbac.tb_basic_user 结构
CREATE TABLE IF NOT EXISTS `tb_basic_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(500) DEFAULT NULL COMMENT '密码',
  `user_account` varchar(100) DEFAULT NULL COMMENT '用户账号',
  `user_status` int(11) DEFAULT NULL COMMENT '状态,1表示可用，非1，禁用',
  `user_create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 正在导出表  springsecurity-rbac.tb_basic_user 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `tb_basic_user` DISABLE KEYS */;
INSERT INTO `tb_basic_user` (`user_id`, `user_name`, `user_password`, `user_account`, `user_status`, `user_create_date`, `role_id`) VALUES
	(1, '管理员', '{bcrypt}$2a$10$So2vKyIV5iHz45IKUuZs/eWxJDDYtJsauWkwgC0KOsI1ernLnqOT6', 'admin', 1, '2019-11-23 15:28:05', 1),
	(2, '张三', '{bcrypt}$2a$10$rU3pRnhqR0ayZINtiVmbveDN2n0.g/UP4cZ36s8UuBL9Lnzk3Lba2', 'zhangsan', 1, '2020-02-15 09:53:16', 2),
	(3, '李四', '{bcrypt}$2a$10$pJkrXHbPMxbM8.6v0Xvby.AMqeBr1m96nyAm.QxWJI4mRP3dodFQi', 'lisi', 0, '2020-02-15 09:54:26', 3),
	(5, '测试用户', '{bcrypt}$2a$10$kDxN4VI7vPbxcX4520HGLee2oTljspkLnPx4aVKe0TgqFTwSHcS6S', 'test', 1, '2020-02-18 08:56:40', 8);
/*!40000 ALTER TABLE `tb_basic_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
