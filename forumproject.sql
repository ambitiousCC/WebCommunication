/*
 Navicat Premium Data Transfer

 Source Server         : cloud
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-9uvgorig.bj.tencentcdb.com:10186
 Source Schema         : forumproject

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/01/2021 14:16:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for art_comment_likes
-- ----------------------------
DROP TABLE IF EXISTS `art_comment_likes`;
CREATE TABLE `art_comment_likes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `like_time` datetime(0) NOT NULL,
  `comment_time` datetime(0) NOT NULL,
  `comment_main_time` datetime(0) NOT NULL,
  `comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_art_comment_likes2`(`user_id`) USING BTREE,
  INDEX `fk_art_comment_likes1`(`comment_id`) USING BTREE,
  CONSTRAINT `fk_art_comment_likes1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_art_comment_likes2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of art_comment_likes
-- ----------------------------

-- ----------------------------
-- Table structure for art_comment_main_likes
-- ----------------------------
DROP TABLE IF EXISTS `art_comment_main_likes`;
CREATE TABLE `art_comment_main_likes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `like_time` datetime(0) NOT NULL,
  `comment_main_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_art_comment_main_likes1`(`comment_main_id`) USING BTREE,
  INDEX `fk_art_comment_main_likes2`(`user_id`) USING BTREE,
  CONSTRAINT `fk_art_comment_main_likes1` FOREIGN KEY (`comment_main_id`) REFERENCES `comment_main` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_art_comment_main_likes2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of art_comment_main_likes
-- ----------------------------
INSERT INTO `art_comment_main_likes` VALUES (41, 14, 24, '2020-12-20 23:29:56', 13);
INSERT INTO `art_comment_main_likes` VALUES (42, 11, 26, '2020-12-22 18:26:30', 14);
INSERT INTO `art_comment_main_likes` VALUES (43, 10, 26, '2020-12-22 18:27:21', 11);
INSERT INTO `art_comment_main_likes` VALUES (44, 11, 26, '2020-12-22 18:27:37', 15);
INSERT INTO `art_comment_main_likes` VALUES (45, 10, 27, '2020-12-23 22:18:34', 16);
INSERT INTO `art_comment_main_likes` VALUES (46, 10, 23, '2020-12-24 00:56:29', 16);
INSERT INTO `art_comment_main_likes` VALUES (47, 15, 24, '2020-12-24 01:26:03', 17);

-- ----------------------------
-- Table structure for art_keeps
-- ----------------------------
DROP TABLE IF EXISTS `art_keeps`;
CREATE TABLE `art_keeps`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `keep_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_art_keeps1`(`article_id`) USING BTREE,
  INDEX `fk_art_keeps2`(`user_id`) USING BTREE,
  CONSTRAINT `fk_art_keeps1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_art_keeps2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of art_keeps
-- ----------------------------
INSERT INTO `art_keeps` VALUES (3, 11, 27, '2020-12-21 10:35:43');
INSERT INTO `art_keeps` VALUES (4, 11, 26, '2020-12-22 17:50:56');
INSERT INTO `art_keeps` VALUES (5, 10, 23, '2020-12-24 00:37:03');

-- ----------------------------
-- Table structure for art_likes
-- ----------------------------
DROP TABLE IF EXISTS `art_likes`;
CREATE TABLE `art_likes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `like_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_art_likes1`(`article_id`) USING BTREE,
  INDEX `fk_art_likes2`(`user_id`) USING BTREE,
  CONSTRAINT `fk_art_likes1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_art_likes2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of art_likes
-- ----------------------------
INSERT INTO `art_likes` VALUES (6, 10, 26, '2020-12-20 13:04:08');
INSERT INTO `art_likes` VALUES (7, 12, 23, '2020-12-20 17:01:02');
INSERT INTO `art_likes` VALUES (8, 12, 24, '2020-12-20 19:08:22');
INSERT INTO `art_likes` VALUES (9, 12, 29, '2020-12-20 20:37:41');
INSERT INTO `art_likes` VALUES (10, 14, 24, '2020-12-20 23:27:34');
INSERT INTO `art_likes` VALUES (11, 11, 27, '2020-12-21 10:35:40');
INSERT INTO `art_likes` VALUES (12, 10, 21, '2020-12-22 17:31:26');
INSERT INTO `art_likes` VALUES (13, 11, 21, '2020-12-22 17:31:48');
INSERT INTO `art_likes` VALUES (14, 11, 26, '2020-12-22 17:50:05');
INSERT INTO `art_likes` VALUES (15, 10, 27, '2020-12-23 22:18:17');
INSERT INTO `art_likes` VALUES (17, 10, 23, '2020-12-24 00:56:21');
INSERT INTO `art_likes` VALUES (18, 15, 24, '2020-12-24 01:25:48');
INSERT INTO `art_likes` VALUES (19, 16, 23, '2020-12-24 01:43:53');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `content_id` bigint(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `like` int(20) NULL DEFAULT NULL,
  `keep` int(20) NULL DEFAULT NULL,
  `article_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tid` tinyint(4) NULL DEFAULT NULL,
  `pid` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `fk_user`(`user_id`) USING BTREE,
  INDEX `fk_article_content`(`content_id`) USING BTREE,
  CONSTRAINT `fk_article_content` FOREIGN KEY (`content_id`) REFERENCES `content` (`content_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (10, 24, 24, '连接图床测试#2', '打工人在此', '2020-12-19 21:48:47', NULL, NULL, '//img.ambitiouscc.com/%2Farticle%2Fimgs%2F0b774d13a7fa4f67b9d45598c0882c38.png?e=1608389326&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:7Ee5hDWiJ1rGLyg7H8pekwkeGkg=', 'ambitiousCC', 1, 1);
INSERT INTO `article` VALUES (11, 26, 25, '发发', '', '2020-12-20 16:54:08', NULL, NULL, '//img.ambitiouscc.com/%2Farticle%2Fimgs%2Fd386c907bf314e6fb97848de721feff5.jpg?e=1608458047&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:FlNL_VekHfwGUK9mrV-Bs7CWy6I=', 'niuniu', 1, 1);
INSERT INTO `article` VALUES (12, 23, 26, '测试', '占一个推荐位', '2020-12-20 16:56:26', NULL, NULL, '//img.ambitiouscc.com/%2Farticle%2Fimgs%2Fe40e5493d72e43ce95665bd97d089ca5.jpg?e=1608458185&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:fZvyX0XPQ3nu1EqMmERQ_SWm5mI=', 'tyhyj', 1, 1);
INSERT INTO `article` VALUES (13, 29, 27, 'javaweb', '', '2020-12-20 20:40:59', NULL, NULL, 'http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F808200559008435383ba2a81605b23e7.jpg?e=1608471658&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:4fq4myYi6nK4gjQQQup7v2wNzC0=', 'skim', 1, 1);
INSERT INTO `article` VALUES (14, 27, 28, '震惊！！河南大学赢得一吨西兰花', '', '2020-12-20 21:37:29', NULL, NULL, 'http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F18bd00edcee346729372ba2dc61fc99e.jpg?e=1608475048&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:NvLUuKugnyk3OrruOOLAqG2JAhM=', 'xbb123456', 2, 1);
INSERT INTO `article` VALUES (15, 24, 29, '云部署测试', '测试', '2020-12-24 01:24:20', NULL, NULL, 'http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F4e0d1b6c30064b9eb28b22c046b78190.jpg?e=1608747859&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:XSItgraPgVG_QG9YOcznYCzc9A0=', 'Hibari Derek', 1, 1);
INSERT INTO `article` VALUES (16, 23, 30, '展示', '展示', '2020-12-24 01:43:34', NULL, NULL, 'http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F545d41b0d6d247c4a2f6f87d69bbe862.jpg?e=1608749013&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:9dPBvrUCQ4ZHeYxYTi9watfa73U=', 'tyhyj', 1, 1);
INSERT INTO `article` VALUES (17, 23, 31, '展示', '展示', '2020-12-24 01:45:14', NULL, NULL, 'http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F75ea10b3dbb240d48cf70858094b3eba.jpg?e=1608749114&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:vOiqPUTsq2DnI0LLhYQyGcauDug=', 'tyhyj', 1, 1);
INSERT INTO `article` VALUES (18, 27, 32, '汽水问题', '三个空瓶换一瓶汽水，问能喝到多少瓶汽水', '2020-12-24 06:49:11', NULL, NULL, 'http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F0d8a77ec2f0b4b2fb674d7afc500abfe.jpg?e=1608767350&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:fBGZV_IIvnP7OlxAaijv_d87TrA=', '徐冰冰', 1, 1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `comment_time` datetime(0) NOT NULL,
  `from_user` bigint(20) NOT NULL,
  `to_user` bigint(20) NULL DEFAULT NULL,
  `user_ico` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `from_nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `to_nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `like` bigint(20) NULL DEFAULT 0,
  `comment_main_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_art_comment`(`article_id`) USING BTREE,
  INDEX `fk_art_comment_user_ico`(`user_ico`) USING BTREE,
  CONSTRAINT `fk_art_comment` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (10, '好', 11, '2020-12-22 17:50:37', 26, 0, 'http://img.ambitiouscc.com/user%2F6587ca30299a473cb1c24ca58a3d15c1.jpeg?e=1608434119&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:vIQ5ESZB4EOLq642kKYlYmfxB7Y=', 'niuniu', 'niuniu', 0, 15);
INSERT INTO `comment` VALUES (11, '11', 10, '2020-12-22 18:27:07', 26, 0, 'http://img.ambitiouscc.com/user%2F6587ca30299a473cb1c24ca58a3d15c1.jpeg?e=1608434119&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:vIQ5ESZB4EOLq642kKYlYmfxB7Y=', 'niuniu', 'ambitiousCC', 0, 11);
INSERT INTO `comment` VALUES (12, '还有很多', 15, '2020-12-24 01:25:59', 24, 0, 'http://img.ambitiouscc.com/user%2Fico%5C992ab77cd6e345c0a8a99c8332130159.jpeg?e=1608746409&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:lA4KVafcSqH3PsptF6FFz3IeBvg=', 'Hibari Derek', 'Hibari Derek', 0, 17);
INSERT INTO `comment` VALUES (13, '123', 10, '2020-12-24 08:58:50', 24, 0, 'http://img.ambitiouscc.com/user%2Fico%5C992ab77cd6e345c0a8a99c8332130159.jpeg?e=1608746409&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:lA4KVafcSqH3PsptF6FFz3IeBvg=', 'Hibari Derek', '徐冰冰', 0, 16);

-- ----------------------------
-- Table structure for comment_main
-- ----------------------------
DROP TABLE IF EXISTS `comment_main`;
CREATE TABLE `comment_main`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `comment_main_time` datetime(0) NOT NULL,
  `user_ico` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `like` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_art_comment_main`(`article_id`) USING BTREE,
  INDEX `fk_art_user_ico`(`user_ico`) USING BTREE,
  CONSTRAINT `fk_art_comment_main` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_main
-- ----------------------------
INSERT INTO `comment_main` VALUES (11, 10, 24, '阿斯顿发生', '2020-12-19 21:49:04', 'http://img.ambitiouscc.com/user%2Fa696e383d3534e6bb952ce615cc1445f.jpeg?e=1608481619&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:prJp-4VZKPgTx7yfymnrBeanhPA=', 'ambitiousCC', 0);
INSERT INTO `comment_main` VALUES (13, 14, 24, '前排！！！', '2020-12-20 23:29:13', 'http://img.ambitiouscc.com/user%2F79ba10f92d5940f898ab4b5d4ca5a3f5.jpeg?e=1608389359&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:mTwNWpA5uxpz28u8fJ9611Dm-LU=', 'ambitiousCC', 0);
INSERT INTO `comment_main` VALUES (14, 11, 27, '<link rel=\"apple-touch-icon-precomposed\" href=\"../../../images/icon/icon.png\">', '2020-12-21 10:35:55', 'http://img.ambitiouscc.com/user%2F74204bcf47204f7197b9b09fab353e66.jpeg?e=1608474885&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:Z2MN9Gb4AfmSLvvGA47IrsSdEwM=', '徐冰冰', 0);
INSERT INTO `comment_main` VALUES (15, 11, 26, '很好！！', '2020-12-22 17:50:22', 'http://img.ambitiouscc.com/user%2F6587ca30299a473cb1c24ca58a3d15c1.jpeg?e=1608434119&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:vIQ5ESZB4EOLq642kKYlYmfxB7Y=', 'niuniu', 0);
INSERT INTO `comment_main` VALUES (16, 10, 27, '太强了！！！', '2020-12-23 22:18:30', 'http://img.ambitiouscc.com/user%2F74204bcf47204f7197b9b09fab353e66.jpeg?e=1608474885&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:Z2MN9Gb4AfmSLvvGA47IrsSdEwM=', '徐冰冰', 0);
INSERT INTO `comment_main` VALUES (17, 15, 24, '用户bug\n', '2020-12-24 01:25:45', 'http://img.ambitiouscc.com/user%2Fico%5C992ab77cd6e345c0a8a99c8332130159.jpeg?e=1608746409&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:lA4KVafcSqH3PsptF6FFz3IeBvg=', 'Hibari Derek', 0);
INSERT INTO `comment_main` VALUES (18, 18, 24, '一共有几瓶哇？', '2020-12-24 20:56:43', 'http://img.ambitiouscc.com/user%2Fico%5C992ab77cd6e345c0a8a99c8332130159.jpeg?e=1608746409&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:lA4KVafcSqH3PsptF6FFz3IeBvg=', 'Hibari Derek', 0);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `content_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `content_time` datetime(0) NOT NULL,
  `content_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`content_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (24, '<p>打工人在此</p><p><img src=\"http://img.ambitiouscc.com//article/imgs/79b28214dfbb4f5b90b3671bdc5ea760.jpg\" style=\"max-width:100%;\"><br></p>', 24, '2020-12-19 21:48:47', '8456f4d2e6a54b86907373c9846009a4');
INSERT INTO `content` VALUES (25, '<p><br></p>', 26, '2020-12-20 16:54:08', '4b849b0bac83480fbcfd7a9a6177fa8a');
INSERT INTO `content` VALUES (26, '<p>占一个推荐位<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a1/2018new_doge02_org.png\" alt=\"[doge]\" data-w-e=\"1\"></p>', 23, '2020-12-20 16:56:26', '9da1d7a3b26545cf97ab60712ddc473c');
INSERT INTO `content` VALUES (27, '<p><br></p>', 29, '2020-12-20 20:40:59', '76573c8046d74d88b82bd809584e25ea');
INSERT INTO `content` VALUES (28, '<p><img src=\"http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F55eec02c8e77485ead1042e9bea3ae9b.jpg?e=1608475016&amp;token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:kg0DcFB9oOw4ZgYCcB17ArR6MIo=\" style=\"max-width:100%;\"><br></p><p><img src=\"http://img.ambitiouscc.com/%2Farticle%2Fimgs%2F4fef651a745d4435baa0fbc4e775fd0f.jpeg?e=1608475038&amp;token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:Zla0mZmMOqfJ7h_SMIM-P2n33Ak=\" style=\"max-width:100%;\"><br></p>', 27, '2020-12-20 21:37:29', '7002e11b2c6840f69fffe2e81c44522d');
INSERT INTO `content` VALUES (29, '<p><img src=\"http://img.ambitiouscc.com/%2Farticle%2Fimgs%2Fc6c1da63ce7f4d21ad6bf7d60e9ea52a.jpg?e=1608747849&amp;token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:3SBWn-a9wncGczDvdcLXpk_FEmg=\" style=\"max-width: 100%;\">测试</p>', 24, '2020-12-24 01:24:20', '428443d20a474fb7bf7f2751c47a4b6a');
INSERT INTO `content` VALUES (30, '<p>PPT</p>', 23, '2020-12-24 01:43:34', '6fe076c131d34db29df4def329bdfbba');
INSERT INTO `content` VALUES (31, '<p>PPT</p>', 23, '2020-12-24 01:45:14', '4ab54c4c1289474a8d8ae843bb69453c');
INSERT INTO `content` VALUES (32, '<p>三个空瓶换一瓶汽水，问能喝到多少瓶汽水</p>', 27, '2020-12-24 06:49:11', 'fbec4e7775e74fa2a9be96248e761b6e');

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `en` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL,
  `bigPic` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `smallPic` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_img`(`article_id`) USING BTREE,
  CONSTRAINT `fk_img` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `age` tinyint(3) UNSIGNED NULL DEFAULT 0,
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` enum('男','女','保密') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '保密',
  `create_user_time` datetime(0) NULL DEFAULT NULL,
  `status` enum('Y','N','F') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://img.ambitiouscc.com/user/admin.jpg',
  `user_ico` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://img.ambitiouscc.com/user/admin.jpg',
  `user_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_comments` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_login` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_ico`(`user_ico`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (21, 'admin', 'admin', '3249c537ece8e361e32a09ba1dac1c18', NULL, 0, NULL, NULL, '2392537022@qq.com', '保密', '2020-12-17 07:57:30', 'Y', '1177582736f64ca087c861a8e24997db', 'http://img.ambitiouscc.com/user/admin.jpg', 'http://img.ambitiouscc.com/user/admin.jpg', NULL, NULL, '2020-12-23 23:58:09');
INSERT INTO `user` VALUES (23, 'tyhyj', 'tyhyj', '7651af7147eb7c8b1dead918afb9e956', NULL, 0, NULL, NULL, '1553716563@qq.com', '保密', '2020-12-18 12:37:44', 'Y', '147fc15b56294db19dee8fa13d095d80', 'http://img.ambitiouscc.com/user/admin.jpg', 'http://img.ambitiouscc.com/user/admin.jpg', NULL, NULL, '2020-12-20 22:11:27');
INSERT INTO `user` VALUES (24, 'ambitiousCC', 'Hibari Derek', '48500d309e1dfc02cfae3d15fcbfba33', NULL, 0, '0', '一条没有姓名的咸鱼', '1297571059@qq.com', '女', '2020-12-18 12:39:19', 'Y', '6a601a378d004cc1b4ea5d45ba6ceec6', 'http://img.ambitiouscc.com/user%2Fico%5C992ab77cd6e345c0a8a99c8332130159.jpeg?e=1608746409&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:lA4KVafcSqH3PsptF6FFz3IeBvg=', 'http://img.ambitiouscc.com/user%2Fico%5C992ab77cd6e345c0a8a99c8332130159.jpeg?e=1608746409&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:lA4KVafcSqH3PsptF6FFz3IeBvg=', 'Wubba lubba dub dub', NULL, '2020-12-21 08:07:00');
INSERT INTO `user` VALUES (25, 'cuiqindexiaohao', 'cuiqindexiaohao', '48500d309e1dfc02cfae3d15fcbfba33', NULL, 0, NULL, NULL, '1718658272@qq.com', '保密', '2020-12-18 12:52:14', 'Y', '0339c75c3bd744e1a526f9aefd22d73d', 'http://img.ambitiouscc.com/user/admin.jpg', 'http://img.ambitiouscc.com/user/admin.jpg', NULL, NULL, '2020-12-18 20:52:48');
INSERT INTO `user` VALUES (26, 'niuniu', 'niuniu', '14e1b600b1fd579f47433b88e8d85291', NULL, 0, NULL, NULL, '2473686978@qq.com', '女', '2020-12-18 14:58:05', 'Y', 'a66878a4a1b64075b07d6e9211e37243', 'http://img.ambitiouscc.com/user%2F6587ca30299a473cb1c24ca58a3d15c1.jpeg?e=1608434119&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:vIQ5ESZB4EOLq642kKYlYmfxB7Y=', 'http://img.ambitiouscc.com/user%2F6587ca30299a473cb1c24ca58a3d15c1.jpeg?e=1608434119&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:vIQ5ESZB4EOLq642kKYlYmfxB7Y=', NULL, NULL, '2020-12-23 20:32:01');
INSERT INTO `user` VALUES (27, 'xbb123456', '徐冰冰', '2fa1f661c26df548f458ed8862c3bb3b', '2020-12-20 00:00:00', 0, NULL, NULL, '932756577@qq.com', '女', '2020-12-18 15:53:49', 'Y', '6d19539c9a9e4524a1f7e839b454abdc', 'http://img.ambitiouscc.com/user%2F74204bcf47204f7197b9b09fab353e66.jpeg?e=1608474885&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:Z2MN9Gb4AfmSLvvGA47IrsSdEwM=', 'http://img.ambitiouscc.com/user%2F74204bcf47204f7197b9b09fab353e66.jpeg?e=1608474885&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:Z2MN9Gb4AfmSLvvGA47IrsSdEwM=', '今天你学习了吗？', NULL, '2020-12-21 10:35:22');
INSERT INTO `user` VALUES (29, 'skim', 'skim', '9db06bcff9248837f86d1a6bcf41c9e7', NULL, 0, NULL, NULL, '2462085546@qq.com', '女', '2020-12-19 05:40:11', 'Y', '6a88bcb0afdf4d3dbbd4c06a6e700fd4', 'http://img.ambitiouscc.com/user%2F3603693806af49c3a8b784d609f39ad2.jpeg?e=1608471199&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:iOJo26EExLScoKwCIPDkR341r8k=', 'http://img.ambitiouscc.com/user%2F3603693806af49c3a8b784d609f39ad2.jpeg?e=1608471199&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:iOJo26EExLScoKwCIPDkR341r8k=', NULL, NULL, '2020-12-20 20:35:46');
INSERT INTO `user` VALUES (30, 'Milkpaopaoo', 'Milkpaopaoo', 'c75629712990e91540f6b6e1a65283a7', NULL, 0, NULL, NULL, '1137148835@qq.com', '保密', '2020-12-22 09:43:44', 'N', 'fdef3b975c054bcaa424bf18ef2aa39f', 'http://img.ambitiouscc.com/user/admin.jpg', 'http://img.ambitiouscc.com/user/admin.jpg', NULL, NULL, NULL);
INSERT INTO `user` VALUES (31, 'huayijing', 'huayijing', '2ad61206bcd70dcba4954c60c19d80ea', NULL, 0, NULL, NULL, '2744540643@qq.com', '保密', '2020-12-24 01:19:42', 'Y', '09e08e400fbc4139ac048c19658a121e', 'http://img.ambitiouscc.com/user/admin.jpg', 'http://img.ambitiouscc.com/user/admin.jpg', NULL, NULL, NULL);
INSERT INTO `user` VALUES (32, '123456', '阿福', '14e1b600b1fd579f47433b88e8d85291', '2020-12-24 00:00:00', 0, NULL, NULL, '418601623@qq.com', '女', '2020-12-24 08:58:11', 'Y', '982f74c131e94766bc370cb290f02e32', 'http://img.ambitiouscc.com/user%2Febb76b37211343fea502339f34693615.jpeg?e=1608790872&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:AoeOVgTM6ijBMgbjgyWGPufnr9s=', 'http://img.ambitiouscc.com/user%2Febb76b37211343fea502339f34693615.jpeg?e=1608790872&token=ZYQgUMVT4cgSUOdzV__LZUOA6C4YvE2GZhXXJnDm:AoeOVgTM6ijBMgbjgyWGPufnr9s=', '大家好', NULL, NULL);
INSERT INTO `user` VALUES (33, 'caiji', 'caiji', '3d694ed9bb4e8e7cef20e3aa1775eeb0', NULL, 0, NULL, NULL, '243547642@qq.com', '保密', '2020-12-24 08:59:08', 'Y', 'a39f48733b744ef4bf87231b7fd0e9c5', 'http://img.ambitiouscc.com/user/admin.jpg', 'http://img.ambitiouscc.com/user/admin.jpg', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
