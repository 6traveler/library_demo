- 该项目为maven项目，使用了maven仓库，加载项目后需要将文件更改为自己的电脑文件
  - 打开File->settings
  - 步骤见文件内readme.png
- 如果报java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver错误参考：[java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver_CodeJiao的博客-CSDN博客](https://blog.csdn.net/I_r_o_n_M_a_n/article/details/117373845)
- 当更改idea代码浏览器页面不更新时参考：[idea web项目中out(maven中是target)目录更新不同步，导致访问404_性感程序员的博客-CSDN博客_idea target不同步](https://blog.csdn.net/qq_40447680/article/details/108762511)。rebulid过后需要点击File->Project Structure->Modules->➕新建一个web->Ok即可

- 关于编码过滤器问题，需要配置tomcat中文乱码情况（统一编码格式）：

[(1条消息) 解决tomcat 静态页面(html)中文乱码终极篇_iBuDongIt的博客-CSDN博客](https://blog.csdn.net/iBuDongIt/article/details/89492517?spm=1001.2101.3001.6650.9&utm_medium=distribute.pc_relevant.none-task-blog-2~default~BlogCommendFromBaidu~Rate-9-89492517-blog-78221517.pc_relevant_multi_platform_whitelistv3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~BlogCommendFromBaidu~Rate-9-89492517-blog-78221517.pc_relevant_multi_platform_whitelistv3&utm_relevant_index=10)
