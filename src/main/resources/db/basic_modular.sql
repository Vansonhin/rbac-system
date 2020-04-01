SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_basic_permission;
DROP TABLE IF EXISTS tb_basic_modular;
DROP TABLE IF EXISTS tb_basic_user;
DROP TABLE IF EXISTS tb_basic_role;




/* Create Tables */

-- 模块表
CREATE TABLE tb_basic_modular
(
	-- 模块编号
	modular_id int NOT NULL AUTO_INCREMENT COMMENT '模块编号',
	-- 模块名称
	modular_name varchar(200) COMMENT '模块名称',
	-- 模块排序
	modular_sort int COMMENT '模块排序',
	PRIMARY KEY (modular_id)
) COMMENT = '模块表' DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


-- 权限表
CREATE TABLE tb_basic_permission
(
	-- 权限编号
	permission_id int NOT NULL AUTO_INCREMENT COMMENT '权限编号',
	-- 模块编号
	modular_id int COMMENT '模块编号',
	-- 权限名称
	permission_name varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限名称',
	-- 权限路径
	permission_action varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限路径',
	-- 权限标识符
	permission_word varchar(50) COMMENT '权限标识符',
	-- 权限的父权限，如果为0，是菜单
	permission_parent int COMMENT '权限的父权限',
	PRIMARY KEY (permission_id)
) COMMENT = '权限表' DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


-- 角色表
CREATE TABLE tb_basic_role
(
	-- 角色编号
	role_id int NOT NULL AUTO_INCREMENT COMMENT '角色编号',
	-- 角色名
	role_name varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '角色名',
	role_permissions varchar(1000) COMMENT '权限字符串',
	PRIMARY KEY (role_id)
) COMMENT = '角色表' DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


-- 后台用户表
CREATE TABLE tb_basic_user
(
	-- 用户编号
	user_id int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
	-- 用户名
	user_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户名',
	-- 密码
	user_password varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '密码',
	-- 用户账号
	user_account varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户账号',
	-- 状态,1表示可用，非1，禁用
	user_status int COMMENT '状态,1表示可用',
	-- 创建日期
	user_create_date datetime COMMENT '创建日期',
	-- 角色编号
	role_id int COMMENT '角色编号',
	PRIMARY KEY (user_id),
	UNIQUE (user_account)
) COMMENT = '后台用户表' DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;







