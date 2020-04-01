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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='模块表';

-- 正在导出表  springsecurity-rbac.tb_basic_modular 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_basic_modular` DISABLE KEYS */;
INSERT INTO `tb_basic_modular` (`modular_id`, `modular_name`, `modular_sort`) VALUES
	(1, '系统管理模块', 0),
	(2, '业务管理模块', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 正在导出表  springsecurity-rbac.tb_basic_permission 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `tb_basic_permission` DISABLE KEYS */;
INSERT INTO `tb_basic_permission` (`permission_id`, `modular_id`, `permission_name`, `permission_action`, `permission_word`, `permission_parent`) VALUES
	(1, 1, '后台用户管理', '/userList', 'USER_LIST', 0),
	(2, 1, '角色管理', '/roleList', 'ROLE_LIST', 0),
	(3, 1, '权限管理', '/permissionList', 'PERMISSIOIN_LIST', 0),
	(4, 1, '模块管理', '/modularList', 'MODULAR_LIST', 0),
	(5, 1, '修改密码', '/settings', 'SETTING', 0),
	(6, 2, '客户管理', '/customerList', 'CUSTOMER_LIST', 0);
/*!40000 ALTER TABLE `tb_basic_permission` ENABLE KEYS */;

-- 导出  表 springsecurity-rbac.tb_basic_role 结构
CREATE TABLE IF NOT EXISTS `tb_basic_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(200) DEFAULT NULL COMMENT '角色名',
  `role_permissions` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 正在导出表  springsecurity-rbac.tb_basic_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_basic_role` DISABLE KEYS */;
INSERT INTO `tb_basic_role` (`role_id`, `role_name`, `role_permissions`) VALUES
	(1, '配置管理员', '1,2,3,4,5,6');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 正在导出表  springsecurity-rbac.tb_basic_user 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tb_basic_user` DISABLE KEYS */;
INSERT INTO `tb_basic_user` (`user_id`, `user_name`, `user_password`, `user_account`, `user_status`, `user_create_date`, `role_id`) VALUES
	(1, '管理员', '{bcrypt}$2a$10$CDwRxVcz2M8gXyf7S9rGbOCnAlbw86OHLb8XsRWXLkr.bx7wvOxpq', 'admin', 1, '2019-11-23 15:28:05', 1);
/*!40000 ALTER TABLE `tb_basic_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
