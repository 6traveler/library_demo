本项目使用了外部jar包(gson)，如果项目无法运行可能是由于当前电脑配置的tomcat中没有这个包，需要自己手动导入：
+ 拷贝WEB-INF/lib中的gson-2.8.6.jar
+ 粘贴到自己配置的tomcat的lib文件夹中中，例如apache-tomcat-9.0.55/lib

如果javax.servlet出现报红的情况，需要在WEB-INF/lib文件夹下手动导入javax的jar包。



**idea内部步骤**

- 1、gson包导入：File->Project Structure->Libraries->➕java文件选定导入gson相关的jar包

- 1、servlet依赖丢失问题：File->Project Structure->Libraries->➕java文件选定导入的servlet相关的jar包
- 2、tomcat配置问题：参考[idea配置tomcat_普通网友的博客-CSDN博客_idea添加tomcat](https://blog.csdn.net/web15085181368/article/details/123633396)
- 3、配置tomcat的时候需要注意deployment->Application context应该配置成"/"，"/"后面不需要加任何东西
