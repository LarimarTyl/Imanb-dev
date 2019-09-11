/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : imanb

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 11/09/2019 17:08:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comic
-- ----------------------------
DROP TABLE IF EXISTS `comic`;
CREATE TABLE `comic`  (
  `comic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '漫画id',
  `root` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '漫画地址（获取图片文件）',
  `comicname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '漫画名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '漫画作者',
  `status` int(6) NULL DEFAULT 1 COMMENT '更新状态 1连载中 0完结 -1停更\r\n',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属类别',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属地区（国漫  其他地区 日漫）',
  `newupdate` datetime(0) NULL DEFAULT NULL COMMENT '最新更新时间',
  `newchaptername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最新更新章节',
  `introduction` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `mark` double(6, 1) NULL DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`comic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comic
-- ----------------------------
INSERT INTO `comic` VALUES (1, 'dldl', '斗罗大陆', '唐家三少、穆逢春', 1, '玄幻/穿越/热血/', '国漫', '2019-09-09 14:50:56', '第六章', '唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却来到了另一个世界，一个属于武魂的世界。名字叫斗罗大陆。这里没有魔法，没有斗气，没有武术，却有神奇的武魂。这里的每个人，在自己六岁的时候，都会在武魂殿中令武魂觉醒。武魂有动物，有植物，有器物，不同的武魂有不同的威力和作用，因而，有些武魂威力过小则被称为废武魂。它们可以辅助人们的日常生活。而其中一些特别出色的武魂却可以用来修炼，这个职业，是斗罗大陆上最为强大也是最重要的职业——魂师。', 4.6);
INSERT INTO `comic` VALUES (2, 'dpcq', '斗破苍穹', '天蚕土豆、任翔', 1, '玄幻/热血/冒险/', '国漫', '2019-08-29 00:00:00', '第三章：家族比试', '三十年河东，三十年河西，莫欺少年穷！年仅15岁的萧家废物，于此地，立下了誓言，从今以后便一步步走向斗气大陆巅峰！ 这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！ 想要知道异界的斗气在发展到巅峰之后是何种境地吗？', 4.8);
INSERT INTO `comic` VALUES (3, 'hzw', '海贼王', '尾田荣一郎', 1, '热血/搞笑/冒险/', '日漫', '2019-08-29 00:00:00', '第414话', '财富，权力，地位，曾经拥有一切的『海贼王』哥尔·D·罗杰，在临死前留下了一句话，让全世界的人们，趋之若鹜奔向大海：“想要我的财富吗？那就去找吧，我的一切都在那里，在那伟大航路！”于是越来越多的人奔向大海，驶入伟大航路，世界迎来了『大海贼时代』！其中主角蒙其D路飞就是带着自己的梦想奔向大海。有什么理由阻止一个男人为了梦想奔向大海呢？ “ONE PIECE”在故事中为“一个大秘宝”之意。故事描述男主角‘草帽’蒙其·D·路飞为了当上“海贼王”而踏上“伟大航道”及与其伙伴的经历。传说中‘海贼王’哥尔·D·罗杰在死前说出他留下了具有财富、名声、力量世界第一的宝藏“ONE PIECE”，许多人为了争夺“ONE PIECE”，争相出海，许多海贼开始树立霸权，而形成了“大海贼时代”。十年后，路飞为了要实现与因救他而断臂的四皇‘红发’香克斯的约定而出海，在遥远的路途上找寻着志同道合的伙伴，一起进入“伟大航道”，目标当上“海贼王”', 4.9);
INSERT INTO `comic` VALUES (4, 'jjdjr', '进击的巨人', '谏山创（未完成）', 0, '热血/冒险/恐怖/', '日漫', '2018-08-29 00:00:00', '第三章', '107年前（743年），世界上突然出现了人类的天敌“巨人”。面临着生存危机而残存下来的人类逃到了一个地方，盖起了三重巨大的城墙。人们在这隔绝的环境里享受了一百多年的和平，直到艾伦·耶格尔十二岁那年，60米高的“超大型巨人”突然出现，以压倒性的力量破坏城门，其后瞬间消失，巨人们成群的冲 进墙内捕食人类。艾伦亲眼看着人们以及自己的母亲被巨人吞食，怀着对巨人无法形容的憎恨，誓言杀死所有巨人。城墙崩坏的两年后，艾伦加入104期训练兵团学习和巨人战斗的技术。在训练兵团的三年，艾伦在同期训练兵里有着其他人无法比拟的强悍精神力。即使亲眼见过地狱也依然勇敢地向巨人挑战的艾伦，如愿以偿加入了向往已久的调查兵团。在他正做着到墙壁的外面去这个梦的时候，毁坏墙壁的超大巨人出现了', 4.8);
INSERT INTO `comic` VALUES (29, 'mdzs', '魔道祖师', '墨香铜臭', 1, '玄幻/仙侠/', '国漫', '2019-09-09 14:32:00', '第三章', '温氏横行，生灵涂炭。江湖仙门义士发动“射日之征”，合力讨伐温氏。“夷陵老祖”魏无羡虽在推翻温氏中立下了汗马功劳，却因修非常道且太过强大而遭人忌惮坑害，致万人唾骂，被情同手足的师弟带人端了老巢，身陨形灭…… 十三年后，魏无羡被人献舍，并再度与姑苏蓝氏蓝忘机、云梦江氏江澄等旧人相遇，前尘谜团未消，江湖疑云再起。 而这一切恩怨情仇，都要从他们少年时说起……', 5.0);
INSERT INTO `comic` VALUES (39, 'yqcr', '一拳超人', '村田雄介', 1, '冒险/热血/', '日漫', '2019-08-29 14:51:07', '第一章', '主人公埼玉原本是一名整日奔波于求职的普通人。3年前的一天偶然遇到了要对淘气少年下杀手的异变螃蟹人后，回忆起年少年时“想要成为英雄”的梦想，最终拼尽全力救下了淘气少年。之后通过拼命锻炼，埼玉终于脱胎换骨获得了最强的力量，但同时失去了头发成了光头。在独自做了一段时间英雄后，正式加入英雄协会，与众多英雄一起开始了对抗各种怪人以及恶势力的生活……', 4.6);
INSERT INTO `comic` VALUES (40, 'jstm', '绝世唐门', '唐家三少', 1, '玄幻/冒险/校园/', '国漫', '2019-08-29 14:51:40', '第一章', '唐门创立万年之后的斗罗大陆上，因妈妈离世而遭受排挤的霍雨浩愤然离开了公爵府。在前往星斗大森林的路上，他结识了了唐雅与贝贝。偶遇天梦冰蚕又使他意外获得了百万年魂环！随后，霍雨浩拜入唐门，并跟随着唐雅和贝贝来到了史莱克学院，开始了充满挑战的学院生活…… ------一代天骄霍雨浩横空出世，新一代史莱克七怪能否重振唐门，谱写一曲绝世唐门之歌？ 百万年魂兽，手握日月摘星辰的死灵圣法神，导致唐门衰落的全新魂导器体系，一切的神奇都将一一展现', 4.2);
INSERT INTO `comic` VALUES (41, 'bfxcr', '蝙蝠侠超人v2漫画', 'DC', 1, '都市/冒险/热血/', '其他漫画', '2019-08-29 14:51:52', '第一章', '蝙蝠侠超人v2漫画 ，狂笑之蝠虽然再次失败，他对DC宇宙带来的祸患缺久久无法消缺。六枚带毒的蝙蝠镖创造了六名狂笑的英雄，当昔日的战友与兄弟变成最险恶的敌人，超人与蝙蝠侠又该如何解决这一场信任危机？在合力破案之前，就连世界最佳拍档，也陷入了相互质疑的迷局……', 4.8);
INSERT INTO `comic` VALUES (42, 'rzsg', '忍者神龟', 'Mirage,Studios', 1, '搞笑/热血/', '其他漫画', '2019-09-09 16:05:00', '第一话', '蝙蝠侠与忍者神龟漫画 ，DC与IDW两大公司年度力作！当黑暗骑士遇见半壳英雄们会擦出怎样的火花？是否会有更危险的敌人与他们狭路相逢？', 4.5);
INSERT INTO `comic` VALUES (43, 'mmtgss', '秘密特工死侍', 'Marvel,Comics', 1, '都市/冒险/', '国漫', '2019-08-20 00:00:00', '第一章', '秘密特工死侍漫画 ，死侍在一次任务中，意外顶替了超级间谍彭斯的身份，混进了风险管理事务所。一边面临身份暴露的风险，一边又要执行各种危险的任务，还要乘机利用特工的信用卡套现，日子过得也是挺不容易……', 3.6);
INSERT INTO `comic` VALUES (44, 'jgl', '金刚狼', 'DC', 1, '都市/搞笑/热血/', '其他漫画', '2019-08-21 00:00:00', '第一章', '金刚狼大战刀锋战士漫画 ，两位英雄先是因为误会而大打出手，随后则共同对抗幕后真凶。这次，他们追查的是一个神秘的吸血鬼邪教，在他们的传说中有一位救世主，同时身负着变种人与吸血鬼的能力，而这样一个大魔头，则有着金刚狼的外表。', 4.2);
INSERT INTO `comic` VALUES (45, 'gmzr', '鬼灭之刃', '吾峠呼世晴', 1, '都市/冒险/', '日漫', '2019-08-29 00:00:00', '第一章', '传说太阳下山后，恶鬼出没吃人。亦有猎鬼人斩杀恶鬼、保护人们。 卖炭少年·炭治郎，他那平凡而幸福的日常生活，在家人遭到恶鬼袭击的那一天发生剧变。母亲与四个弟妹惨遭杀害，而与他一起生还的妹妹：祢豆子亦异变成凶暴的鬼。 在猎鬼人的指引下，立志成为猎鬼人的炭治郎与变成鬼却尚存理智的祢豆子二人踏上了旅程。通过艰苦的剑术修行与赌命试炼，炭治郎成为了鬼猎人组织“鬼杀队”的一员。 为了让妹妹祢豆子变回人类，为了讨伐杀害家人的恶鬼，为了斩断悲伤的连锁，少年与鬼的战斗不曾停歇。', 4.5);
INSERT INTO `comic` VALUES (46, 'brz', '博人传', '池本干雄', 1, '都市/冒险/', '日漫', '2019-09-11 18:59:58', '第五章', '《火影忍者》原作者岸本齐史监修、漫画原助手池本干雄作画、剧场版《博人传》剧本协力小太刀右京负责脚本的漫画，讲述原作的故事完结后漩涡鸣人之子漩涡博人的冒险故事。本次新系列与前作不同，以“月更”的方式从2016年5月9日起在《周刊少年Jump》进行连载。七代目火影漩涡鸣人治理的木叶村。那是经历了战争的历史之后人们讴歌和平的时代。但是鸣人与其儿子漩涡博人的关系却并不和平………', 3.2);
INSERT INTO `comic` VALUES (47, 'tsxzzds', '透视仙总在都市', '未知', 1, '玄幻/都市/', '国漫', '2019-08-29 00:00:00', '第一章', '钟马是一个毕业在即却找不到工作的普通大学生，意外得知自己被女友绿了的他彻底跌入人生低谷，想喝场闷酒又被一道天雷劈中？！本以为自己悲催的一生就要这样结束，却因祸得福，钟马获得无限能力的升级系统，从此开启自己的开挂人生！透视、瞬间移动、超强格斗……全都信手拈来！从此以后，美女云集，再也不用苦追女神反被绿，少年啊，逆袭吧', 2.8);
INSERT INTO `comic` VALUES (48, 'jssdryymm', '即使是大人也有秘密', '乔乔', 1, '言情/都市/', '日漫', '2019-08-29 00:00:00', '第一章', '即使成为大人漫画 ，大家将来想成为怎样的大人呢？', 3.9);
INSERT INTO `comic` VALUES (49, 'dy', '毒液', 'DC', 1, '言情/都市/', '其他漫画', '2019-08-07 00:00:00', '第一章', '数年前彼得帕克突然与一只被称作共生体的外星生命结合在了一起，他将这一存在视作一件战服。而当彼得意识到这件战服实际上是一个活体的有机生物，且它正让他逐渐变得易怒、好斗之时，他拒绝再次将其穿上。这让身处异星他乡的共生体感觉自己遭到了背叛，孤独之感涌上了它的心头。而在他们结合的那段时日里，共生体获得了蜘蛛侠的基因代码，这使得它能将任何与它结合之人赋予类似其首任宿主的能力：如爬墙，制造有机蛛网，它甚至能改变自身外形从而变得难以察觉。多年来共生体历经了数任宿主的更迭，其中有人格高尚的宿主也有十恶不赦的宿主。尽管它的身份一变再变从致命的守护者，到政府特工，再到银河护卫队成员，它的内在永远是迷。', 4.3);
INSERT INTO `comic` VALUES (52, 'llkbsncs', '萝莉控不死鸟传说', '松林悟', 1, '都市/搞笑/', '日漫', '2019-09-04 00:12:00', '第一话', '萝莉控不死鸟传说漫画 ，为了从BL团从世界手中拯救幼女，我带上面具成为了她唯一的英雄 鬼才萝莉控漫画家松林悟倾心之作。', 4.5);
INSERT INTO `comic` VALUES (53, 'dzz', '大主宰', '周洪滨', 1, '玄幻/冒险/热血/', '国漫', '2019-09-09 14:39:00', '第三章', '大千世界，位面交汇，万族林立，群雄荟萃，一位位来自下位面的天之至尊，在这无尽世界，演绎着令人向往的传奇，追求着那主宰之路，这世界，万道争锋，吾为大主宰！', 4.1);
INSERT INTO `comic` VALUES (54, 'hcham', '很纯很暧昧', '鱼人二代', 1, '言情/搞笑/', '国漫', '2019-09-11 22:58:56', '第五章', '一个科技改变命运的校园轻喜剧，高中生杨明本是一个标准的差生，打架斗殴逃课作弊，样样不落。面对校花的鼓励，虽然羞愧难当但也无能为力。可是一次偶然机会，他获得了一副神奇的眼镜，并且彻底改变了他的未来', 3.8);
INSERT INTO `comic` VALUES (55, 'lmmnym', '辣妹魔女与猫', '葵日向', 0, '冒险/搞笑/', '日漫', '2019-09-09 14:46:00', '第四话', '辣妹魔女与猫漫画 ，我们一起吸大猫，一起喵喵喵喵喵', 3.6);
INSERT INTO `comic` VALUES (56, 'qyz', '七原罪', '铃木央', 1, '冒险/热血/', '日漫', '2019-09-09 14:37:00', '第二话', '在古老的过去，这片大地上存在着五大截然不同的种族。传说三千年前，女神族率领着妖精族、巨人族和人类，对拥有不详毁灭力量的魔神族进行讨伐，结合四个种族的力量最终将魔神族封印，大地恢复和平。这场战争被后世称为圣战。 故事发生于布里塔利亚大陆上的里昂尼丝王国，国王预言传说的圣战将再启，王国的圣骑士们为了备战而进行军备强化，进而发动政变囚禁国王、压榨奴役国民。担忧着国家现状的第三王女伊丽莎白，将救国的希望寄托于传说中的逆贼——由七名凶恶的大罪人组成的最强骑士团七大罪，从而独自踏上寻找的旅程。注定无果的旅程中，疲惫不堪的伊丽莎白误入某个酒馆，被身为店主的金发少年救下，这名少年正是七大罪的团长梅利奥达斯。在将公主从追兵的危机中解救出来后，他们踏上了寻找昔日的同伴、拯救王国未来的旅途。', 4.5);
INSERT INTO `comic` VALUES (57, 'wdnss', '我的女上司', 'ひみつ', 1, '都市/搞笑/', '日漫', '2019-09-11 23:59:59', '第六章', '终于开始了令人充满期待的社会人生活的矢印制品公司的新人员工——三田新一，被配属到业务部的他的第一件事就是“选择自己喜欢的上司”！ 被4名可爱的上司OL追着选择当部下...！？ 忙碌又欢乐的职场喜剧开始！！！', 3.2);
INSERT INTO `comic` VALUES (59, 'zecyny', '真二次元女友', '小二', 1, '言情/穿越/', '国漫', '2019-09-11 18:56:59', '开更了', '开始了', 3.3);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论表id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `aim` int(11) NULL DEFAULT NULL COMMENT '评论目标（查找评论对应关系）',
  `comic_id` int(6) NULL DEFAULT NULL COMMENT '漫画id（对应漫画的评论）',
  `detail_id` int(6) NULL DEFAULT NULL COMMENT '章节id（对应章节的评论）',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `status` int(6) NULL DEFAULT 0 COMMENT '状态（是否回复 1已回复 0未回复 -1已读不回）',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `comment_ibfk_1`(`user_id`) USING BTREE,
  INDEX `comment_ibfk_2`(`comic_id`) USING BTREE,
  INDEX `comment_ibfk_3`(`detail_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`comic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`detail_id`) REFERENCES `detail` (`detail_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, NULL, 1, NULL, '斗罗大陆真棒！加油!!!', 0, '2019-08-20 15:24:20');
INSERT INTO `comment` VALUES (2, 2, NULL, 1, NULL, '斗罗大陆好看，三哥加油！', 0, '2019-08-20 15:24:24');
INSERT INTO `comment` VALUES (3, 2, 1, NULL, NULL, '哈哈，整挺好。', 0, '2019-08-20 15:25:46');
INSERT INTO `comment` VALUES (4, 3, NULL, 1, NULL, '看了这么久的小说，总算是出漫画了', 0, '2019-08-20 15:26:53');
INSERT INTO `comment` VALUES (5, 4, NULL, 1, NULL, '漫画画风不错，喜欢！', 0, '2019-08-20 15:28:14');
INSERT INTO `comment` VALUES (6, 4, 1, NULL, NULL, '哈哈哈一起追！', 0, '2019-08-20 15:29:33');
INSERT INTO `comment` VALUES (7, 1, 3, NULL, NULL, '可以的！', NULL, '2019-08-20 15:29:52');
INSERT INTO `comment` VALUES (8, 1, 6, NULL, NULL, '好的好的！', 0, '2019-08-20 15:30:21');
INSERT INTO `comment` VALUES (9, 3, NULL, 2, NULL, '666', 0, '2019-08-21 15:30:21');
INSERT INTO `comment` VALUES (12, 3, NULL, 2, NULL, '666', 1, '2019-08-21 15:30:21');
INSERT INTO `comment` VALUES (13, 1, 9, NULL, NULL, '在的在的', 0, '2019-08-24 12:00:00');
INSERT INTO `comment` VALUES (14, 1, NULL, 49, NULL, '测试一下', 0, '2019-09-02 00:01:00');
INSERT INTO `comment` VALUES (16, 2, 15, NULL, NULL, '123', 0, '2019-09-02 00:02:00');
INSERT INTO `comment` VALUES (27, 2, NULL, 2, NULL, '美杜莎女王他来了嗷', 0, '2019-09-10 12:48:46');
INSERT INTO `comment` VALUES (29, 2, 13, NULL, NULL, '我不知道', 0, '2019-09-10 12:49:53');
INSERT INTO `comment` VALUES (32, 2, NULL, NULL, 43, '开更了', 0, '2019-09-10 13:04:14');
INSERT INTO `comment` VALUES (43, 2, NULL, NULL, 7, '行不行', 0, '2019-09-10 13:26:54');
INSERT INTO `comment` VALUES (46, 2, NULL, NULL, 7, '好像是可以了', 0, '2019-09-10 13:32:36');
INSERT INTO `comment` VALUES (52, 2, NULL, 2, NULL, '好看哦', 0, '2019-09-10 13:39:08');
INSERT INTO `comment` VALUES (53, 2, NULL, NULL, 7, '小板凳坐好', 0, '2019-09-10 13:39:27');
INSERT INTO `comment` VALUES (54, 101, 1, NULL, NULL, '回复一下', 0, '2019-09-10 14:47:50');
INSERT INTO `comment` VALUES (55, 101, 1, NULL, NULL, '来了来了', 0, '2019-09-10 14:47:57');
INSERT INTO `comment` VALUES (56, 101, 1, NULL, NULL, '测试回复', 0, '2019-09-10 14:48:04');
INSERT INTO `comment` VALUES (57, 101, 51, NULL, NULL, '测试回复', 0, '2019-09-10 14:48:15');
INSERT INTO `comment` VALUES (58, 2, 7, NULL, NULL, 'test', 0, '2019-09-10 16:02:39');
INSERT INTO `comment` VALUES (59, 2, 7, NULL, NULL, '哈哈', 0, '2019-09-10 16:06:49');
INSERT INTO `comment` VALUES (60, 2, 9, NULL, NULL, '懂', 0, '2019-09-10 16:25:07');
INSERT INTO `comment` VALUES (61, 2, NULL, NULL, 287, '呃唐', 0, '2019-09-10 18:23:30');
INSERT INTO `comment` VALUES (62, 2, NULL, 3, NULL, '斗破苍穹', 0, '2019-09-11 16:10:32');
INSERT INTO `comment` VALUES (64, 2, 12, NULL, NULL, '别666了', 0, '2019-09-11 16:11:03');
INSERT INTO `comment` VALUES (65, 2, 12, NULL, NULL, '测试一下', 0, '2019-09-11 16:11:11');
INSERT INTO `comment` VALUES (66, 2, NULL, 3, NULL, '好看', 0, '2019-09-11 16:13:21');
INSERT INTO `comment` VALUES (67, 2, NULL, 54, NULL, '测试评论\r\n', 0, '2019-09-11 16:15:58');
INSERT INTO `comment` VALUES (68, 2, NULL, 57, NULL, '还可以', 0, '2019-09-11 23:59:59');
INSERT INTO `comment` VALUES (71, 101, 60, NULL, NULL, '测试', 0, '2019-09-11 16:59:26');
INSERT INTO `comment` VALUES (73, 101, 65, NULL, NULL, '看看', 0, '2019-09-11 17:00:05');
INSERT INTO `comment` VALUES (87, 101, 65, NULL, NULL, '测试', 0, '2019-09-11 17:04:04');

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '详情页id',
  `comic_id` int(11) NULL DEFAULT NULL COMMENT '漫画id',
  `chaptername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节目录',
  `generalize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节简介',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `detail_ibfk_1`(`comic_id`) USING BTREE,
  CONSTRAINT `detail_ibfk_1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`comic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 290 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES (7, 2, '第一章', '01', '陨落的天才', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (15, 3, '第410话', '410', '我要成为海贼王', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (16, 3, '第411话', '411', '海贼猎人索隆', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (17, 4, '第一章', '01', '巨人来了222', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (18, 4, '第二章', '02', '铠之巨人', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (19, 4, '第三章', '03', '超大型巨人', '2018-08-29 00:00:00');
INSERT INTO `detail` VALUES (20, 1, '第一章', '01', '唐三穿越（上）', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (21, 2, '第二章', '02', '休妻', '2019-09-29 00:00:00');
INSERT INTO `detail` VALUES (22, 3, '第413话', '413', '一起出海吧！', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (24, 1, '第二章', '02', '唐三穿越（中）\r\n唐三 他竟然 是？双生武魂！！！？？？？\r\n另一个武魂还是罕见的昊天锤！', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (25, 2, '第三章', '03', '拜师', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (27, 3, '第414话', '414', '一个是想成为海贼王的二哈少年，一位是被海军抓住的路痴剑豪，他们偶遇后会碰撞出什么样的火花，路飞与索隆的邂逅。', '2019-08-29 00:00:00');
INSERT INTO `detail` VALUES (35, 29, '第一章：重生', '01', '魔道祖师开更了，夷陵老祖魏无羡来了，少侠们还等什么，赶紧入座看戏了！！！！', '2019-08-08 00:00:00');
INSERT INTO `detail` VALUES (42, 52, '第一话', '01', '第一章 开更了嗷，每周五点到六点', '2019-09-04 00:00:00');
INSERT INTO `detail` VALUES (43, 57, '第一章', '01', '喜提女上司一枚', '2019-09-09 12:27:59');
INSERT INTO `detail` VALUES (44, 57, '第二章', '02', '部下的辛苦日常。', '2019-09-09 12:30:00');
INSERT INTO `detail` VALUES (45, 56, '第一话', '01', '超正统，英雄传说出发！！！！', '2019-09-09 12:31:00');
INSERT INTO `detail` VALUES (46, 55, '第一话', '01', '伟大的毛茸茸', '2019-09-09 12:31:01');
INSERT INTO `detail` VALUES (47, 54, '第一话', '01', '我叫杨明，我。。。', '2019-09-09 12:38:11');
INSERT INTO `detail` VALUES (48, 53, '第一话:激战过后', '01', '灵路少年,性格古怪的女孩，年少的约定。', '2019-09-09 12:39:01');
INSERT INTO `detail` VALUES (49, 46, '第一话', '01', '我叫博人！', '2019-09-09 12:40:00');
INSERT INTO `detail` VALUES (50, 54, '第二话', '02', '路见不平。', '2019-09-09 12:42:46');
INSERT INTO `detail` VALUES (51, 29, '第二章', '02', '我并不是大魔头', '2019-09-09 14:31:00');
INSERT INTO `detail` VALUES (52, 29, '第三章', '03', '刚重生就要饿死?', '2019-09-09 14:32:00');
INSERT INTO `detail` VALUES (53, 54, '第三章', '03', '老头是高人', '2019-09-09 14:33:00');
INSERT INTO `detail` VALUES (54, 46, '第二章', '02', '昔日的对手，如今的师傅？', '2019-09-09 14:34:00');
INSERT INTO `detail` VALUES (55, 56, '第二话', '02', '大反响的英雄传说！从这一话开始看也可以享受到乐趣哦', '2019-09-09 14:37:00');
INSERT INTO `detail` VALUES (56, 53, '第二章', '02', '时过境迁，北灵境的天才少年', '2019-09-09 14:38:43');
INSERT INTO `detail` VALUES (57, 53, '第三章', '03', '牛刀小试', '2019-09-09 14:39:00');
INSERT INTO `detail` VALUES (58, 57, '第三章', '03', '办公室的日常戏剧情节', '2019-09-09 14:40:00');
INSERT INTO `detail` VALUES (59, 57, '第四章', '04', '反应剧烈，尽是可爱上市的心跳战场。', '2019-09-09 14:42:00');
INSERT INTO `detail` VALUES (60, 55, '第二话', '02', '辣妹魔女的房间', '2019-09-09 14:44:00');
INSERT INTO `detail` VALUES (61, 55, '第三章', '03', '这次也是一口气两个', '2019-09-09 14:45:00');
INSERT INTO `detail` VALUES (62, 55, '第四话', '04', '正因为一切都很顺眼', '2019-09-09 14:46:00');
INSERT INTO `detail` VALUES (63, 1, '第三章', '03', '唐三穿越（下）', '2019-09-09 14:49:00');
INSERT INTO `detail` VALUES (64, 1, '第四章', '04', '双生武魂（上）', '2019-09-09 14:49:00');
INSERT INTO `detail` VALUES (65, 1, '第五章', '05', '双生武魂（中）', '2019-09-09 14:50:00');
INSERT INTO `detail` VALUES (66, 1, '第六章', '06', '双生武魂（下）', '2019-09-09 14:50:56');
INSERT INTO `detail` VALUES (67, 1, '第七章', '07', '大师？老师？(上)', '2019-09-09 14:50:57');
INSERT INTO `detail` VALUES (68, 1, '第八章', '08', '大师？老师？（中）', '2019-09-09 14:50:58');
INSERT INTO `detail` VALUES (69, 1, '第九章', '09', '大师？老师？（下）', '2019-09-09 14:50:59');
INSERT INTO `detail` VALUES (70, 1, '第十章', '10', '猎魂森林（上）', '2019-09-09 14:51:00');
INSERT INTO `detail` VALUES (71, 1, '第十一章 ', '11', '猎魂森林（中）', '2019-09-09 14:51:01');
INSERT INTO `detail` VALUES (72, 1, '第十二章', '12', '猎魂森林（下）', '2019-09-09 14:51:02');
INSERT INTO `detail` VALUES (73, 2, '第四章', '04', '第四章', '2019-09-09 15:01:16');
INSERT INTO `detail` VALUES (74, 2, '第五章', '05', '第五章', '2019-09-09 15:01:17');
INSERT INTO `detail` VALUES (75, 2, '第六章', '06', '第六章', '2019-09-09 15:01:18');
INSERT INTO `detail` VALUES (76, 2, '第七章', '07', '第七章', '2019-09-09 15:03:49');
INSERT INTO `detail` VALUES (77, 29, '第四章', '04', '姑苏蓝氏', '2019-09-09 14:32:00');
INSERT INTO `detail` VALUES (78, 29, '第五章', '05', '愿望到底是什么？', '2019-09-09 15:40:29');
INSERT INTO `detail` VALUES (79, 29, '第六章', '06', '引尸棋阵', '2019-09-09 15:41:05');
INSERT INTO `detail` VALUES (80, 29, '第七章', '07', '诡异男尸', '2019-09-09 15:41:21');
INSERT INTO `detail` VALUES (81, 29, '第八章', '08', '死因之谜', '2019-09-09 15:41:46');
INSERT INTO `detail` VALUES (82, 29, '第九章', '09', '看不见的东西', '2019-09-09 15:42:05');
INSERT INTO `detail` VALUES (83, 29, '第十章', '10', '真面目', '2019-09-09 15:42:24');
INSERT INTO `detail` VALUES (84, 29, '第十一章', '11', '危险境地', '2019-09-09 15:42:48');
INSERT INTO `detail` VALUES (85, 29, '第十二章', '12', '含光君', '2019-09-09 15:43:15');
INSERT INTO `detail` VALUES (86, 29, '第十三章', '13', '大梵山', '2019-09-09 15:44:04');
INSERT INTO `detail` VALUES (87, 29, '第十四章', '14', '佛脚镇', '2019-09-09 15:44:31');
INSERT INTO `detail` VALUES (88, 29, '第十五章', '15', '兰陵金氏', '2019-09-09 15:44:53');
INSERT INTO `detail` VALUES (89, 29, '第十六章', '16', '三毒圣手-江澄', '2019-09-09 15:45:34');
INSERT INTO `detail` VALUES (90, 29, '第十七章', '17', '披麻戴孝蓝忘机', '2019-09-09 15:46:15');
INSERT INTO `detail` VALUES (91, 29, '第十八章', '18', '再会', '2019-09-09 15:46:40');
INSERT INTO `detail` VALUES (92, 29, '第十九章', '19', '舞天女尊', '2019-09-09 15:47:17');
INSERT INTO `detail` VALUES (93, 29, '第二十章', '20', '天女现身', '2019-09-09 15:48:09');
INSERT INTO `detail` VALUES (94, 29, '第21章', '21', '第21章', '2019-09-09 15:56:14');
INSERT INTO `detail` VALUES (95, 29, '第22章', '22', '第22章', '2019-09-09 15:56:15');
INSERT INTO `detail` VALUES (96, 29, '第23章', '23', '第23章', '2019-09-09 15:56:16');
INSERT INTO `detail` VALUES (97, 29, '第24章', '24', '第24章', '2019-09-09 15:56:17');
INSERT INTO `detail` VALUES (98, 29, '第25章', '25', '第25章', '2019-09-09 15:56:18');
INSERT INTO `detail` VALUES (99, 29, '第26章', '26', '第26章', '2019-09-09 15:56:19');
INSERT INTO `detail` VALUES (100, 29, '第27章', '27', '第27章', '2019-09-09 15:56:20');
INSERT INTO `detail` VALUES (101, 29, '第28章', '28', '第28章', '2019-09-09 15:56:21');
INSERT INTO `detail` VALUES (102, 29, '第29章', '29', '第29章', '2019-09-09 15:56:22');
INSERT INTO `detail` VALUES (103, 29, '第30章', '30', '第30章', '2019-09-09 15:56:23');
INSERT INTO `detail` VALUES (104, 29, '第31章', '31', '第31章', '2019-09-09 15:56:24');
INSERT INTO `detail` VALUES (105, 29, '第32章', '32', '第32章', '2019-09-09 15:56:25');
INSERT INTO `detail` VALUES (106, 29, '第33章', '33', '第33章', '2019-09-09 15:56:26');
INSERT INTO `detail` VALUES (107, 29, '第34章', '34', '第34章', '2019-09-09 15:56:27');
INSERT INTO `detail` VALUES (108, 29, '第35章', '35', '第35章', '2019-09-09 15:56:28');
INSERT INTO `detail` VALUES (109, 29, '第36章', '36', '第36章', '2019-09-09 15:56:29');
INSERT INTO `detail` VALUES (110, 29, '第37章', '37', '第37章', '2019-09-09 15:56:30');
INSERT INTO `detail` VALUES (111, 29, '第38章', '38', '第38章', '2019-09-09 15:56:31');
INSERT INTO `detail` VALUES (112, 29, '第39章', '39', '第39章', '2019-09-09 15:56:32');
INSERT INTO `detail` VALUES (113, 29, '第40章', '40', '第40章', '2019-09-09 15:56:33');
INSERT INTO `detail` VALUES (114, 29, '第41章', '41', '第41章', '2019-09-09 15:56:34');
INSERT INTO `detail` VALUES (115, 29, '第42章', '42', '第42章', '2019-09-09 15:56:35');
INSERT INTO `detail` VALUES (116, 29, '第43章', '43', '第43章', '2019-09-09 15:56:36');
INSERT INTO `detail` VALUES (117, 29, '第44章', '44', '第44章', '2019-09-09 15:56:37');
INSERT INTO `detail` VALUES (118, 29, '第45章', '45', '第45章', '2019-09-09 15:56:38');
INSERT INTO `detail` VALUES (119, 29, '第46章', '46', '第46章', '2019-09-09 15:56:39');
INSERT INTO `detail` VALUES (120, 29, '第47章', '47', '第47章', '2019-09-09 15:56:40');
INSERT INTO `detail` VALUES (121, 29, '第48章', '48', '第48章', '2019-09-09 15:56:41');
INSERT INTO `detail` VALUES (122, 29, '第49章', '49', '第49章', '2019-09-09 15:56:42');
INSERT INTO `detail` VALUES (123, 29, '第50章', '50', '第50章', '2019-09-09 15:56:43');
INSERT INTO `detail` VALUES (124, 29, '第51章', '51', '第51章', '2019-09-09 15:56:44');
INSERT INTO `detail` VALUES (125, 42, '第一话', '01', '忍者神龟开更了', '2019-09-09 16:05:00');
INSERT INTO `detail` VALUES (126, 1, '第14章', '14', '第14章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (127, 1, '第15章', '15', '第15章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (128, 1, '第16章', '16', '第16章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (129, 1, '第17章', '17', '第17章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (130, 1, '第18章', '18', '第18章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (131, 1, '第19章', '19', '第19章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (132, 1, '第20章', '20', '第20章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (133, 1, '第21章', '21', '第21章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (134, 1, '第22章', '22', '第22章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (135, 1, '第23章', '23', '第23章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (136, 1, '第24章', '24', '第24章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (137, 1, '第25章', '25', '第25章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (138, 1, '第26章', '26', '第26章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (139, 1, '第27章', '27', '第27章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (140, 1, '第28章', '28', '第28章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (141, 1, '第29章', '29', '第29章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (142, 1, '第30章', '30', '第30章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (143, 1, '第31章', '31', '第31章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (144, 1, '第32章', '32', '第32章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (145, 1, '第33章', '33', '第33章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (146, 1, '第34章', '34', '第34章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (147, 1, '第35章', '35', '第35章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (148, 1, '第36章', '36', '第36章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (149, 1, '第37章', '37', '第37章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (150, 1, '第38章', '38', '第38章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (151, 1, '第39章', '39', '第39章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (152, 1, '第40章', '40', '第40章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (153, 1, '第41章', '41', '第41章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (154, 1, '第42章', '42', '第42章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (155, 1, '第43章', '43', '第43章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (156, 1, '第44章', '44', '第44章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (157, 1, '第45章', '45', '第45章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (158, 1, '第46章', '46', '第46章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (159, 1, '第47章', '47', '第47章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (160, 1, '第48章', '48', '第48章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (161, 1, '第49章', '49', '第49章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (162, 1, '第50章', '50', '第50章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (163, 1, '第51章', '51', '第51章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (164, 1, '第52章', '52', '第52章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (165, 1, '第53章', '53', '第53章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (166, 1, '第54章', '54', '第54章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (167, 1, '第55章', '55', '第55章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (168, 1, '第56章', '56', '第56章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (169, 1, '第57章', '57', '第57章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (170, 1, '第58章', '58', '第58章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (171, 1, '第59章', '59', '第59章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (172, 1, '第60章', '60', '第60章', '2019-09-09 16:45:08');
INSERT INTO `detail` VALUES (231, 2, '第8章', '8', '第8章', '2019-09-09 17:52:00');
INSERT INTO `detail` VALUES (232, 2, '第9章', '9', '第9章', '2019-09-09 17:52:01');
INSERT INTO `detail` VALUES (233, 2, '第10章', '10', '第10章', '2019-09-09 17:52:02');
INSERT INTO `detail` VALUES (234, 2, '第11章', '11', '第11章', '2019-09-09 17:52:03');
INSERT INTO `detail` VALUES (235, 2, '第12章', '12', '第12章', '2019-09-09 17:52:04');
INSERT INTO `detail` VALUES (236, 2, '第13章', '13', '第13章', '2019-09-09 17:52:05');
INSERT INTO `detail` VALUES (237, 2, '第14章', '14', '第14章', '2019-09-09 17:52:06');
INSERT INTO `detail` VALUES (238, 2, '第15章', '15', '第15章', '2019-09-09 17:52:07');
INSERT INTO `detail` VALUES (239, 2, '第16章', '16', '第16章', '2019-09-09 17:52:08');
INSERT INTO `detail` VALUES (240, 2, '第17章', '17', '第17章', '2019-09-09 17:52:09');
INSERT INTO `detail` VALUES (241, 2, '第18章', '18', '第18章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (242, 2, '第19章', '19', '第19章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (243, 2, '第20章', '20', '第20章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (244, 2, '第21章', '21', '第21章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (245, 2, '第22章', '22', '第22章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (246, 2, '第23章', '23', '第23章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (247, 2, '第24章', '24', '第24章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (248, 2, '第25章', '25', '第25章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (249, 2, '第26章', '26', '第26章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (250, 2, '第27章', '27', '第27章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (251, 2, '第28章', '28', '第28章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (252, 2, '第29章', '29', '第29章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (253, 2, '第30章', '30', '第30章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (254, 2, '第31章', '31', '第31章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (255, 2, '第32章', '32', '第32章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (256, 2, '第33章', '33', '第33章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (257, 2, '第34章', '34', '第34章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (258, 2, '第35章', '35', '第35章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (259, 2, '第36章', '36', '第36章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (260, 2, '第37章', '37', '第37章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (261, 2, '第38章', '38', '第38章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (262, 2, '第39章', '39', '第39章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (263, 2, '第40章', '40', '第40章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (264, 2, '第41章', '41', '第41章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (265, 2, '第42章', '42', '第42章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (266, 2, '第43章', '43', '第43章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (267, 2, '第44章', '44', '第44章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (268, 2, '第45章', '45', '第45章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (269, 2, '第46章', '46', '第46章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (270, 2, '第47章', '47', '第47章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (271, 2, '第48章', '48', '第48章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (272, 2, '第49章', '49', '第49章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (273, 2, '第50章', '50', '第50章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (274, 2, '第51章', '51', '第51章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (275, 2, '第52章', '52', '第52章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (276, 2, '第53章', '53', '第53章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (277, 2, '第54章', '54', '第54章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (278, 2, '第55章', '55', '第55章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (279, 2, '第56章', '56', '第56章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (280, 2, '第57章', '57', '第57章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (281, 2, '第58章', '58', '第58章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (282, 2, '第59章', '59', '第59章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (283, 2, '第60章', '60', '第60章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (284, 2, '第61章', '61', '第61章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (285, 2, '第62章', '62', '第62章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (286, 2, '第63章', '63', '第63章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (287, 2, '第64章', '64', '第64章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (288, 2, '第65章', '65', '第65章', '2019-09-09 17:52:37');
INSERT INTO `detail` VALUES (289, 57, '第五章', '05', '第五章', '2019-09-10 23:59:59');
INSERT INTO `detail` VALUES (290, 54, '第四章', '04', '第四章测试章节更新 相关内容更新', '2019-09-11 23:59:59');
INSERT INTO `detail` VALUES (291, 46, '第三章', '03', '第三章开始了', '2019-09-11 17:57:57');
INSERT INTO `detail` VALUES (292, 54, '第五章', '05', '', '2019-09-11 22:58:56');
INSERT INTO `detail` VALUES (293, 46, '第四章', '04', '第四章开始了嗷', '2019-09-11 22:58:59');
INSERT INTO `detail` VALUES (294, 46, '第五章', '05', '第五章', '2019-09-11 18:59:58');
INSERT INTO `detail` VALUES (295, 57, '第六章', '06', '第六章：我的野蛮女上司', '2019-09-11 23:59:59');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `history_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '阅读历史id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `comic_id` int(11) NULL DEFAULT NULL COMMENT '漫画id',
  `detail_id` int(11) NULL DEFAULT NULL COMMENT '章节id',
  `lastreadtime` datetime(0) NULL DEFAULT NULL COMMENT '上一次阅读时间',
  `status` int(6) NULL DEFAULT 0 COMMENT '状态 0未更新 1已有更新 -1已读更新',
  PRIMARY KEY (`history_id`) USING BTREE,
  INDEX `history_ibfk_1`(`user_id`) USING BTREE,
  INDEX `history_ibfk_3`(`detail_id`) USING BTREE,
  INDEX `comicId`(`comic_id`) USING BTREE,
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_ibfk_3` FOREIGN KEY (`detail_id`) REFERENCES `detail` (`detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_ibfk_4` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`comic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (3, 1, 4, 17, '2019-08-21 14:49:06', 1);
INSERT INTO `history` VALUES (4, 1, 2, 7, '2019-09-01 22:16:30', 1);
INSERT INTO `history` VALUES (10, 4, 3, 16, '2019-09-03 11:05:35', 1);
INSERT INTO `history` VALUES (11, 101, 2, 288, '2019-09-11 16:25:31', -1);
INSERT INTO `history` VALUES (13, 2, 2, 283, '2019-09-11 16:08:02', -1);
INSERT INTO `history` VALUES (16, 2, 3, 27, '2019-09-09 14:19:20', 0);
INSERT INTO `history` VALUES (20, 2, 54, 292, '2019-09-11 16:53:25', -1);
INSERT INTO `history` VALUES (21, 2, 55, 46, '2019-09-09 18:03:01', -1);
INSERT INTO `history` VALUES (22, 2, 57, 289, '2019-09-11 16:53:31', 1);
INSERT INTO `history` VALUES (24, 2, 42, 125, '2019-09-11 15:36:11', -1);
INSERT INTO `history` VALUES (25, 101, 46, 294, '2019-09-11 16:54:09', -1);
INSERT INTO `history` VALUES (26, 1, 1, 172, '2019-09-11 16:23:17', -1);
INSERT INTO `history` VALUES (27, 101, 54, 292, '2019-09-11 16:30:41', -1);
INSERT INTO `history` VALUES (28, 122, 1, 148, '2019-09-11 23:59:59', 0);

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `likes_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞表id',
  `comic_id` int(255) NULL DEFAULT NULL COMMENT '点赞漫画id',
  `detail_id` int(255) NULL DEFAULT NULL COMMENT '点赞章节id',
  `number` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`likes_id`) USING BTREE,
  INDEX `likes_ibfk_1`(`comic_id`) USING BTREE,
  INDEX `likes_ibfk_2`(`detail_id`) USING BTREE,
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`comic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`detail_id`) REFERENCES `detail` (`detail_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (1, 1, NULL, 100);
INSERT INTO `likes` VALUES (2, 2, NULL, 304);
INSERT INTO `likes` VALUES (3, 3, NULL, 105);
INSERT INTO `likes` VALUES (9, 4, NULL, 1);
INSERT INTO `likes` VALUES (10, 29, NULL, 10);
INSERT INTO `likes` VALUES (11, 39, NULL, 13);
INSERT INTO `likes` VALUES (12, 40, NULL, 150);
INSERT INTO `likes` VALUES (13, 41, NULL, 103);
INSERT INTO `likes` VALUES (14, 42, NULL, 207);
INSERT INTO `likes` VALUES (15, 43, NULL, 9);
INSERT INTO `likes` VALUES (16, 44, NULL, 102);
INSERT INTO `likes` VALUES (17, 45, NULL, 211);
INSERT INTO `likes` VALUES (18, 46, NULL, 101);
INSERT INTO `likes` VALUES (19, 47, NULL, 5);
INSERT INTO `likes` VALUES (43, NULL, 35, 102);
INSERT INTO `likes` VALUES (48, NULL, 27, 109);
INSERT INTO `likes` VALUES (49, NULL, 17, 1);
INSERT INTO `likes` VALUES (50, 48, NULL, 1);
INSERT INTO `likes` VALUES (51, 49, NULL, 2);
INSERT INTO `likes` VALUES (52, 52, NULL, 1);
INSERT INTO `likes` VALUES (53, 53, NULL, 1);
INSERT INTO `likes` VALUES (54, 54, NULL, 1);
INSERT INTO `likes` VALUES (55, 55, NULL, 1);
INSERT INTO `likes` VALUES (56, 56, NULL, 1);
INSERT INTO `likes` VALUES (57, 57, NULL, 2);
INSERT INTO `likes` VALUES (58, NULL, 42, 2);
INSERT INTO `likes` VALUES (59, NULL, 20, 3);
INSERT INTO `likes` VALUES (61, NULL, 292, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orders_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订阅表id',
  `comic_id` int(11) NULL DEFAULT NULL COMMENT '漫画id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `status` int(6) NOT NULL DEFAULT 0 COMMENT '状态：是否更新(0未更新 1已更新 -1已读更新）',
  PRIMARY KEY (`orders_id`) USING BTREE,
  INDEX `orders_ibfk_2`(`user_id`) USING BTREE,
  INDEX `orders_ibfk_3`(`comic_id`) USING BTREE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`comic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 1, -1);
INSERT INTO `orders` VALUES (3, 3, 1, 1);
INSERT INTO `orders` VALUES (6, 39, 3, 0);
INSERT INTO `orders` VALUES (8, 42, 102, 1);
INSERT INTO `orders` VALUES (9, 43, 107, 0);
INSERT INTO `orders` VALUES (10, 4, 3, 1);
INSERT INTO `orders` VALUES (12, 29, 118, 1);
INSERT INTO `orders` VALUES (23, 46, 101, -1);
INSERT INTO `orders` VALUES (26, 3, 2, -1);
INSERT INTO `orders` VALUES (56, 52, 2, -1);
INSERT INTO `orders` VALUES (57, 44, 2, 0);
INSERT INTO `orders` VALUES (58, 57, 122, 1);
INSERT INTO `orders` VALUES (60, 54, 2, -1);
INSERT INTO `orders` VALUES (62, 2, 2, 0);
INSERT INTO `orders` VALUES (63, 54, 101, -1);
INSERT INTO `orders` VALUES (64, 2, 122, 0);

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES (1, '玄幻');
INSERT INTO `types` VALUES (2, '言情');
INSERT INTO `types` VALUES (3, '都市');
INSERT INTO `types` VALUES (4, '冒险');
INSERT INTO `types` VALUES (5, '校园');
INSERT INTO `types` VALUES (6, '仙侠');
INSERT INTO `types` VALUES (7, '搞笑');
INSERT INTO `types` VALUES (8, '穿越');
INSERT INTO `types` VALUES (9, '热血');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `QQ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'user' COMMENT '头像图片',
  `status` int(6) NULL DEFAULT 0 COMMENT '用户状态 0未激活 1已激活',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'root', '男', '565203943@qq.com', '565203943', 'root', 'root', 1);
INSERT INTO `user` VALUES (2, 'admin', 'admin', '男', '565203943@qq.com', '565203943', '管理员', 'admin', 1);
INSERT INTO `user` VALUES (3, 'larimar', 'tyl123', '男', '565203943@qq.com', '565203943', 'larimar', 'user', -1);
INSERT INTO `user` VALUES (4, 'yue', '女', '男', '565203943@qq.com', '565203943', 'yue', 'user', 1);
INSERT INTO `user` VALUES (101, 'tyl', 'tyl123', '男', '565203943@qq.com', '565203943', 'tyl', 'user', 1);
INSERT INTO `user` VALUES (102, 'qy', 'qy123', '男', '565203943@qq.com', '565203943', '玥', 'yue', 1);
INSERT INTO `user` VALUES (103, 'rucy', '123456', '女', '565203943@qq.com', '87199243601', 'rucy', 'rucy', 1);
INSERT INTO `user` VALUES (104, 'lucy', '123456', '男', '565203943@qq.com', '45564564', 'Lucy', 'lucy', 0);
INSERT INTO `user` VALUES (107, 'test', 'test', '男', '565203943@qq.com', '565203943', '测试员', 'test', 1);
INSERT INTO `user` VALUES (108, 'qiuge', 'qiu123', '男', '565203943@qq.com', '8719924360', '球哥', 'qiuge', 1);
INSERT INTO `user` VALUES (117, 'admins', 'admin', '男', '565203943@qq.com', '8719924360', '管理员', 'admin', 0);
INSERT INTO `user` VALUES (118, 'czq', 'czq123', '女', '565203943@qq.com', '8719924360', '管理员', 'czq', 1);
INSERT INTO `user` VALUES (120, '458', '12312313', '女', '123', '8719924360', '玥', '458', 0);
INSERT INTO `user` VALUES (122, 'myroots', '12312313', '女', '13617004500', '8719924360', '12', 'myroot', 0);

SET FOREIGN_KEY_CHECKS = 1;
