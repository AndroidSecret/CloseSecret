# 心情APP

------

## 1、项目背景与需求

> * 近年来，国内网民数量不断增长；到2016年，中国移动网民已达到6.5亿
> * 随着移动互联网的快速发展，移动互联网改变了人们的社交习惯，智能手机的快速普及，社交APP开发的出现满足了人们的社交需求。
> * 当前主流的社交软件市场是一片红海，有基于熟人社交的微信，也有基于生人社交和信息传播的微博，还有垂直领域的陌陌之类。
> * 拥有庞大流量、活跃的社交平台，其能量无疑是巨大的，而其中大部分的蛋糕被这些发展许久的公司所占据。
> * 社交领域的核心是人和关系，心情是一款基于熟人的匿名社交，目标用户主要有两类：发布者：在匿名的状态下更轻松地表达，宣泄心事，纾解压力
观察者：人的窥私欲，好奇心
> * 匿名能够激发用户更多的创作，产生更多不同的见解
> * 匿名也可能会扩大用户的阴暗面，而采用手机联系人作为朋友圈是作为用户言行的一定约束

## 2、基本功能

### 1、登录
用户登陆使用手机号和短信验证码的方式登陆
登陆成功后客户端可以获取到登陆标识（token），客户端可以通过token保持相对长时间的访问服务器的权限。

### 2、上传手机联系人
采用MD5加密手机联系人及用户手机号，然后上传到服务端。上传联系人的目的是为了建立朋友圈。

### 3、发表
用户可以发表心情和评论

### 4、查看心情
用户可以查看自己发表的心情；也可以查看联系人的心情

### 5、通信
采用HTTP，上传参数使用传统的URL参数方式进行上传，返回值采用json

## 3、界面

如图是APP的主界面，注册通过短信验证码

<div align=center><img src="https://raw.githubusercontent.com/windarain/picture/master/final/1.png" width="200" height="400" /></div>  

如图是项目的主页面，用户登录后就能看到朋友发的心情了

<div align=center><img src="https://raw.githubusercontent.com/windarain/picture/master/final/2.png" width="200" height="400" /></div>

用户可以发表心情

<div align=center><img src="https://raw.githubusercontent.com/windarain/picture/master/final/3.png" width="200" height="400" /></div>

用户之间可以通过评论进行互动

<div align=center><img
src="https://raw.githubusercontent.com/windarain/picture/master/final/4.png" width="200" height="400" /></div>
