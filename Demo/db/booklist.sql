-- 初始化 booklist 数据库及表结构
CREATE DATABASE IF NOT EXISTS `booklist` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `booklist`;

-- 创建图书表，包含示例数据所需的字段
DROP TABLE IF EXISTS `booklist`;
CREATE TABLE `booklist` (
    `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '图书编号',
    `book_name` VARCHAR(128) NOT NULL COMMENT '书名',
    `author` VARCHAR(64) NOT NULL COMMENT '作者',
    `count` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
    `price` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '单价',
    `isbn` VARCHAR(32) DEFAULT NULL,
    `content` TEXT,
    `img` VARCHAR(255) DEFAULT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书列表';

-- 插入四本初始图书
INSERT INTO `booklist` (book_name, author, count, price, isbn, content, img) VALUES
('红楼梦', '曹雪芹', 57, 40.68, '9787802506077', '中国古典四大名著之一，描绘贾府兴衰与人物悲欢。', '/img/红楼梦.jpg'),
('西游记', '吴承恩', 62, 42.81, '9787532512003', '讲述唐僧师徒西天取经，降妖伏魔的奇幻旅程。', '/img/西游记.jpg'),
('三国演义', '罗贯中', 53, 41.25, '9787510136740', '以三国历史为背景，塑造魏蜀吴诸多英雄人物。', '/img/三国演义.jpg'),
('水浒传', '施耐庵', 55, 45.18, '9787020015016', '梁山好汉反抗欺压、聚义水泊梁山的史诗故事。', '/img/水浒传.jpg');

