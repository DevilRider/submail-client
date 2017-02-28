#Submail Sender Client for Java

本程序基于submail 官方sdk 改版

 * 使用jodd-http替换httpclient，同时调整部分类结构及名称
 
 * 将访问域名移至配置文件（应对切换submail api 域名切换问题）
 
 * 使用log4j 2作为日志框架
 
 * mail 包为邮件相关api接口：send、xsend、subscribe、unsubscribe
 
 * sms 包为短信相关api接口：xsend、subscribe、unsubscribe

## API 实现情况

### 邮件发送api

* Mail/send  

* Mail/xsend

### 短信发送api
* Message/xsend 

