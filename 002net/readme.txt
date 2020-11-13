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

7 APP与服务器通信时采用单向认证
(1)需求：xxxAPP与服务器通信时采用单向认证同时通过限制客户端中间代理抓包，预埋证书提升安全性
分期乐App Https验证以及CA更新维护记录:http://wiki.fenqile.com/pages/viewpage.action?pageId=47154531
1)当前xxxApp在进行金融备案改造，而资检方对于xxxApp提出一项改造：
客户端应用软件未校验服务器证书，存在中间人攻击的风险
对于这个改造，其实就是需要分期乐App满足不能中间攻击或者通过抓包工具抓包查看数据的需求。
2)方案：App在针对中间人攻击使用的方案是通过预埋服务器CA证书在安装包，然后通过预埋的证书对服务器进行验证。验证通过则正常消息通信，失败则冲断请求链接
校验服务端CA证书 避免中间人攻击
实施：在开发实施过程中，xxxApp是建立维护了一个域名SSL验证名单列表，当请求request当host命中这个域名SSL验证名单的时候，才使用预埋的CA证书进行验证。
3) 相关
Https:
HTTPS超文本传输安全协议。HTTP协议传输的数据都是未加密的，也就是明文，因此使用HTTP协议传输隐私信息非常不安全。HTTP使用80端口通讯，而HTTPS占用443端口通讯。在计算机网络上，HTTPS经由超文本传输协议（HTTP）进行通信，但利用SSL/TLS来加密数据包。HTTPS开发的主要目的，是提供对网络服务器的身份认证，保护交换数据的隐私与完整性。
SSL:
Secure Sockets Layer 安全套接字协议,及其继任者传输层安全（Transport Layer Security，TLS）是为网络通信提供安全及数据完整性的一种安全协议。TLS与SSL在传输层与应用层之间对网络连接进行加密
SSLSession:
http://jszx-jxpt.cuit.edu.cn/javaapi/javax/net/ssl/SSLSession.html
在 SSL 中，会话用来描述两个实体间正在发生的关系。每个 SSL 连接在某一时刻都会涉及一个会话，但是该会话可以同时地或相继地用在这些实体的多个连接上。
证书的类型:
实际上，我们使用的证书分很多种类型，SSL证书只是其中的一种。证书的格式是由X.509标准定义。SSL证书负责传输公钥，是一种PKI（Public Key Infrastructure，公钥基础结构）证书。 我们常见的证书根据用途不同大致有以下几种：
SSL证书，用于加密HTTP协议，也就是HTTPS。
代码签名证书，用于签名二进制文件，比如Windows内核驱动，Firefox插件，Java代码签名等等。
客户端证书，用于加密邮件。
双因素证书，网银专业版使用的USB Key里面用的就是这种类型的证书。
CA证书：
CA(Certificate Authority)是证书的签发机构，它是公钥基础设施（Public Key Infrastructure，PKI）的核心。CA是负责签发证书、认证证书、管理已颁发证书的机关。
证书的内容包括：电子签证机关的信息、公钥用户信息、公钥、权威机构的签字和有效期等等。证书的格式和验证方法普遍遵循X.509 国际标准。
域名vs主机名：
域名的范围要比主机名大。一个域名下可以有多个主机名，域名下还可以有子域名。例如，域名abc.com下，有主机server1和server2，其主机全名就是server1.abc.com和server2.abc.com。
域名、主机名与URL:
http://mail.163.com/index.html
1)http://:这个是协议，也就是HTTP超文本传输协议，也就是网页在网上传输的协议。
2）mail：这个是服务器名，代表着是一个邮箱服务器，所以是mail.
3)163.com:这个是域名，是用来定位网站的独一无二的名字。
4)mail.163.com：这个是主机名（网站名），由服务器名+域名组成。
5）/：这个是根目录，也就是说，通过网站名找到服务器，然后在服务器存放网页的根目录
6:）index.html：这个是根目录下的默认网页（当然，163的默认网页是不是这个我不知道，只是大部分的默认网页，都是index.html）
7）http://mail.163.com/index.html:这个叫做URL，统一资源定位符，全球性地址，用于定位网上的资源。
证书：
证书(Certificate，也称public-key certificate)是用某种签名算法对某些内容(比如公钥)进行数字签名后得到的、可以用来当成信任关系中介的数字凭证。证书发行机构通过发行证书告知证书使用者或实体其公钥(public-key)以及其它一些辅助信息。证书在电子商务安全交易中有着广泛的应用，证书发行机构也称CA(Certificate Authority)。
相关代码：
1)HostnameVerifier
public boolean verify(String hostname,SSLSession session)
参数：hostname-主机名
session - 到主机的连接上使用的 SSLSession
返回值：如果主机名是可接受，则返回true;

(2)加入一个Android客户端在和后端通信时，采用http协议的话，怎么做认证，就是这个请求是合法的，不是从其他地方模拟过来的？
近几年移动互联网的高速发展，智能手机的使用用户呈现爆炸性增长，手机终端上的App 种类繁多，大多数App 都需要与后台系统进行交互，交互的第一步需要进行登录认证，
过于简单的认证方式可能被破解从而造成用户信息的泄露甚至威胁着用户的财产安全。
(3) ssl双向认证和单向认证
SSL认证：单向认证与双向认证：https://www.sohu.com/a/128648513_604699
扯一扯HTTPS单向认证、双向认证、抓包原理、反抓包策略：https://www.songma.com/news/txtlist_i39807v.html
(4) app与后台交互之间的几种安全认证机制
https://www.cnblogs.com/leechenxiang/p/6385607.html
1)、HTTP简单基本认证方式
    这个是早期交互用得比较多的一种方式，主要是使用用户名和密码来交互，由于在每次的交互中，用户名和密码都会暴露给第三方，那么这么做是不可取的，风险十分大，所以这种认证方式并没有流传开来
 2)、OAuth（OAuth2）
    这个就是开放平台的概念，就像你登录第三方网站或者app的时候可以使用qq或者微信登录，那么登录后第三方可以获取你的个人信息，这就是开放授权的概念，理念是通过token来实现。
    这个token可以由你来限制时间，第三方获取你指定的信息，从而达到了一个安全认证的效果。
 3)、cookie
    这是比较常用的一种方式，很多小型网站都在使用，用户在登陆后，生成的用户信息存入cookie，这个cookie要和服务端的session来匹配，一般控制cookie在浏览器关闭的时候失效。
 4)、token机制
    用户登陆后的信息以token存入session或者redis的同时会生成一个cookie，来保存到浏览器，如果是手机端则把这个token存入其他媒介，存活时间与session（这里的session指的是单一应用的session或者分布式session，都可以）一致，如果用户在其他客户端登录后需要覆盖token，从而可以做到唯一登录，需要注意的是token在交互中存入headers中，并且在服务端拦截器中需要对这个token进行校验。这种方式可以跨域，而cookie不能跨域，所以只能适用于一些小网站。
 5)、json web token（JWT）
    ​JWT的机制和之前说的也是差不多，只不过封装了很多，并且安全性得到了一定的提高。
