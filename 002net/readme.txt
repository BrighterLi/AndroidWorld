Cookie;Session;Token;网关；基本的网络数据请求方式与数据解析；上传下载；网络图片；重定向；域名；User-Agent;

https://blog.csdn.net/u012195214/article/details/78889602
Java获取本机外网ip地址的方法:https://blog.csdn.net/qq_35348457/article/details/78734482
根据客户端IP地址，通过DNS解析合适的服务器IP：https://blog.csdn.net/yangzhengqui/article/details/81529973
《网络万用表》

Apache:HttpClient;Java:HttpURLConnection
无论是我们自己封装的网络请求类，还是第三方网络的网络的请求框架，都离不开这两个类库。

1 登录态
 (1)掉登录态/没登录态：未登录/账号掉了/账号退出了/没有获取到登录状态
 (2)实现登录态的几种方式
  https://www.jianshu.com/p/f91fb5bd75b0


2 网络相关检测
(1)网络是否正常连接(和网络已连接但是否可以正常访问不一样)
Android 中网络连接检测和使用ping检测网络是否可访问：https://blog.csdn.net/yyanjun/article/details/81197991?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
(2)获取dns
(3)获取公网出口IP
Android 获取外网IP地址：https://www.jianshu.com/p/1e3eaf887191
(4)域名解析
Android解析域名获取IP：https://blog.csdn.net/u013072976/article/details/79074687
(5)ping
Android检测网络接口访问速度，ping接口获取访问时间平均值:https://blog.csdn.net/jie_0754/article/details/88718976?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase
(6)域名拦截
Android Okhttp拦截器(修改baseurl)：https://www.csdn.net/gather_2a/MtTakgwsNTE0Ny1ibG9n.html

3 网络参数
Android获取外网和内网的IP：https://blog.csdn.net/xiechengfa/article/details/45378035

ip地址 cmd ipconfig
本地区各运营商dns
DNS 服务器 cmd nslookup
DHCP服务器 cmd ipconfig/all
默认网关
本地DNS:以10.开头，私有地址就是在互联网上不使用,而被用在局域网络中的地址
本地区联通移动电信DNS:
vpn
内网ip vs 外网ip/公网ip:
内网Dns/本地Dns vs 外网Dns/出口Dns:


查询客户出口DNS服务器地址的网址：
https://www.dns.la/tools/
http://nstool.netease.com/2222