**启动服务器上的xxl-job:**
    （已经不用了）docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://rm-uf6e68msur97jt733po.mysql.rds.aliyuncs.com:3306/production-coms-cockpit?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai --spring.datasource.username=pro_comscockpit --spring.datasource.password=H7bciFr5Yq2Se8y --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver" -p 8081:8080 -v /tmp/xxl-job:/data/applogs --name xxl-job-admin  -d xuxueli/xxl-job-admin:2.2.0
**查看启动日志：**
    docker logs x +  table键
**Cron表达式可以访问http://cron.qqe2.com/自定义生成**

测试环境的xxl-job地址：http://1.116.201.94/:8089/xxl-job-admin/toLogin  测试对应的数据库
生产环境的xxl-job地址: http://47.117.41.149:8081/xxl-job-admin/toLogin     生产对应的数据库
账号：admin    密码：123456

报表和定时部署在不同的服务器需要给定时开放回调的端口号：默认是9999