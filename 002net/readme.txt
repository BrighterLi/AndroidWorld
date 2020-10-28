Cookie;Session;Token;网关；基本的网络数据请求方式与数据解析；上传下载；网络图片；重定向；域名；User-Agent;

https://blog.csdn.net/u012195214/article/details/78889602
Java获取本机外网ip地址的方法:https://blog.csdn.net/qq_35348457/article/details/78734482
根据客户端IP地址，通过DNS解析合适的服务器IP：https://blog.csdn.net/yangzhengqui/article/details/81529973
《网络万用表》
DNS服务——搭建企业内网DNS服务器的作用:https://blog.csdn.net/weixin_34184158/article/details/94235613?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.nonecase

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

4 Glide
(1)、代码有人维护，不至于出现问题，项目组都搞不定的时候问题无法解决。（ImageLoader已没人维护了）
(2)、代码简洁，可读性很好。（Fresco是一个非常优秀的库，但是配置稍显麻烦，同时代码风格读起来有些生疏）
(3)、功能强大（400多k的包，包含很多功能，例如：像加载Gif图片就是Picasso做不到的）

5 dns
HTTPS如何防止DNS欺骗？:https://blog.csdn.net/weixin_33874713/article/details/91715130
DNS如何工作？
如果您想访问www.example.com，您的浏览器需要找到该特定Web服务器的IP地址。它首先要查询系统的hosts文件，
这是一个文本文件，其中包含任何域名的IP地址，这些域名被有效地“硬编码”到该系统中。但是，大多数时候该网址不在
系统的hosts文件中，那您的浏览器将询问DNS服务器（可能是一个在您组织网络上的解析器，也可能是由ISP运营或由Google、
OpenDNS等组织运营的外部公共解析器）。
简而言之，如果解析器最近被要求提供该域名的IP地址，它将在其缓存中包含该信息，并将直接提供该信息。但是如果它没有在
缓存中（或者因为它之前从未被要求提供该信息，或者因为信息已“过期”并从缓存中删除），那么它会将请求引用到根服务器，
可以告诉解析器在哪里获取有关.com顶级域的信息。
然后它会询问服务器从哪里获取有关example.com的信息，然后转到称为权威服务器的服务器上，该服务器将为其提供example.com
域中任何服务器的IP地址（例如Web和电子邮件服务器）。
什么是DNS欺骗
DNS欺骗就是攻击者冒充域名服务器的一种欺骗行为。原理：如果可以冒充域名服务器，然后将查询的IP地址设为攻击者的IP地址，
这样的话用户上网就只能看到攻击者的主页，而想要取得的example.com网站主页了，这就是DNS欺骗的基本原理。
DNS欺骗就是利用DNS协议设计时的一个非常重要的安全缺陷。首先欺骗者向目标机器发送构造的APR应答包，ARP欺骗成功后，
嗅探到对方发出的DNS请求数据包，分析数据包取得ID和端口号后，向目标发送自己构造后的一个DNS返回包，对方收到DNS应答
包，发现ID和端口号全部正确，即把返回数据包中的额域名和对应的IP地址保存近DNS缓存表中，而后来当真实的DNS应答包返回
时则被丢弃。
HTTPS如何防止DNS欺骗？
有效的HTTPS证书表示，该服务器在获取证书时就已经由受信任的证书颁发机构（如：沃通CA）验证域名所有权。为了确保攻击者
无法使用DNS欺骗将用户引导到http://可以截获流量的普通连接，网站可以使用HTTP严格传输安全（HSTS）来指示浏览器始终要
求其域的HTTPS连接。这意味着想要成功欺骗DNS解析的攻击者，还必须创建有效的HTTPS连接。这使得DNS欺骗与攻击HTTPS一样具
有挑战性和昂贵性。
如果攻击者欺骗DNS但不破坏HTTPS，则用户将从其浏览器收到明显的警告消息，以防止他们访问可能的恶意站点。如果该站点使
用HSTS，则访问者将无法选择忽略并单击警告。

HTTPS和HSTS协同工作以保护域免受DNS欺骗。
6 HttpDns
什么是HTTPDNS跟随阿里的httpdns demo一步一步了解httpdns:https://www.jianshu.com/p/7289607510a4
HTTPDNS使用HTTP协议进行域名解析，代替现有基于UDP的DNS协议，域名解析请求直接发送到阿里云的HTTPDNS
服务器，从而绕过运营商的Local DNS，能够避免Local DNS造成的域名劫持问题和调度不精准问题。
