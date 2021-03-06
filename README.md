Imanb爱漫吧动漫网
====
1.项目介绍
---
利用闲暇时间整合自己学习的一些内容，做了一个动漫资源网站。动漫图片素材都来源于其他网站，不做任何商业用途，如有侵权，立刻删除。

项目初期编写前端页面用的是Bootstrap框架和Jquery，后期搭建web项目使用SSM框架进行整合并使用Thymleaf模板引擎进行页面渲染。

2.项目结构
---
项目由用户、漫画、章节、订阅、浏览历史、评论、点赞几个模块组成。

数据库文件：[Imanb.sql](imanb.sql)

前端代码（Thymleaf版）：[Imanb前端代码（模板引擎版）](src/main/webapp/WEB-INF/templates)

前端代码（js/css等）：[Imanb前端代码（js/css）](src/main/webapp/static)

前端代码部分（Html版）：[Imanb前端代码（html版）](https://github.com/LarimarTyl/Imanb)

配置文件：[配置文件](src/main/resource)

后端代码：[后端代码](src/main/java)

数据库文件：[Imanb.sql](imanb.sql)

3.技术架构
---
* BootStrap
* Jquery
* Thymleaf
* Spring、SpringMvc、Mybatis
* maven
* Mysql

4.页面展示
---
**前端页面**

![主页](show/index.png "主页")

![漫画列表](show/list.png "漫画列表")

![漫画详情](show/detail.png "漫画详情")

![章节详情](show/detailInfo.png "章节详情")

![更新历史](show/update.png "更新历史")

![用户中心](show/userInfo.png "用户中心")

![用户回复](show/userRevert.png "用户回复")

![用户订阅](show/userOrder.png "用户订阅")

![用户历史](show/userHistory.png "用户历史")
-------------------
**管理后台页面**

![用户管理](show/userAdmin.png "用户管理")

![漫画管理](show/comicAdmin.png "漫画管理")

![订阅管理](show/orderAdmin.png "订阅管理")

![章节管理](show/detailAdmin.png "章节管理")

![评论管理](show/commentAdmin.png "评论管理")

![历史管理](show/historyAdmin.png "历史管理")

5.主要功能展示
---
### 用户登录

先判断验证码是否正确，再判断用户账号密码是否正确，在判断用户是否激活（邮箱激活业务待完成）<br>
注册功能类似 就不多演示

用户登录效果图
![登录功能](show/login.gif "登录功能")

### 添加章节（漫画更新)

漫画更新了章节后，与该漫画相关的漫画信息（更新最新章节），订阅信息（通知用户更新），浏览历史（通知用户更新）等信息都应该更新

后台页面效果图
一开始，该漫画的订阅和浏览历史的状态都是已读更新，添加新章节后原状态更新为已更新状态
![添加章节](show/updateDetail.gif "添加章节")

并且漫画相关信息也更新
![添加章节后漫画信息更新](show/updateDetail.png "添加章节后漫画信息更新")

### 浏览历史更新

漫画更新了章节后,用户如果订阅了该漫画或者有该漫画的阅读记录，在个人中心的订阅栏中的未读更新和浏览历史中的已有更新会显示更新的漫画及章节信息<br>
当用户阅读完最新章节后，订阅的已有更新变为已读更新，浏览历史的已有更新浏览历史进入全部历史记录中
![更新](show/read.gif "更新")
当用户登录并浏览章节信息时（不登录不会记录浏览历史），会检索用户的浏览历史，如果已存在该漫画的浏览历史，则更新浏览历史浏览时间和章节更新为即使时间，如果当前章节时最新章节则阅读状态更新为已读更新。<br>
如果用户不存在该浏览历史则新建浏览历史记录，再判断是否是最新章节记录浏览历史状态。
![浏览历史更新](show/history.gif "浏览历史更新")

### 订阅漫画

已经订阅过的漫画不能再次订阅
![订阅漫画](show/addComic.gif "订阅漫画")

### 继续阅读

用户可以在订阅信息中继续阅读订阅漫画 可以在浏览历史中继续阅读上一次阅读的章节（定位到章节）
![继续阅读](show/readUpdate.gif "继续阅读")

### 添加漫画
![添加漫画](show/addComic.gif "添加漫画")

### 删除历史取消订阅
![删除历史取消订阅](show/delHistory.gif "删除历史取消订阅")

### 点赞评论
![点赞评论](show/like.gif "点赞评论")

### 分类搜索
![分类搜索](show/byType.gif "分类搜索")




