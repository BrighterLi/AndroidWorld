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
https单向认证和双向认证即SSL单向认证和双向认证
双向认证 SSL 协议要求服务器和用户双方都有证书。单向认证 SSL 协议不需要客户拥有CA证书。
一般Web应用都是采用SSL单向认证的，原因很简单，用户数目广泛，且无需在通讯层对用户身份进行验证，一般都在应用逻辑层来保证用户的合法登入。但如果是企业应用对接，情况就不一样，
可能会要求对客户端(相对而言)做身份验证。这时就需要做SSL双向认证。

(4) app与后台交互之间的几种安全认证机制
移动 APP 端与服务器端用户身份认证的安全方案:https://blog.csdn.net/joeyon1985/article/details/45949717
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


8 网络相关知识
(1) Cookie、Session、Token、JWT
还分不清 Cookie、Session、Token、JWT？一篇文章讲清楚: https://blog.csdn.net/cqcre/article/details/104935369
1）会话：会话是指一个终端用户与交互系统进行通讯的过程，比如从输入账户密码进入操作系统到退出操作系统就是一个会话过程。会话较多用于网络上，TCP的三次握手就创建了一个会话，TCP关闭连接就是关闭会话。
Session代表服务器与浏览器的一次会话过程，这个过程是连续的，也可以时断时续的。在Servlet中，当JSP页面没有显式禁止session的时候，在打开浏览器第一次请求该jsp的时候，服务器会自动为其创建一个session，
并赋予其一个sessionID，发送给客户端的浏览器。以后客户端接着请求本应用中其他资源的时候，会自动在请求头上添加:(Cookie:JSESSIONID=客户端第一次拿到的session ID)。这样，服务器端在接到请求时候，就会收到session ID，
并根据ID在内存中找到之前创建的session对象，提供给请求使用。
2)Cookie
HTTP 是无状态的协议（对于事务处理没有记忆能力，每次客户端和服务端会话完成时，服务端不会保存任何会话信息）：每个请求都是完全独立的，服务端无法确认当前访问者的身份信息，
无法分辨上一次的请求发送者和这一次的发送者是不是同一个人。所以服务器与浏览器为了进行会话跟踪（知道是谁在访问我），就必须主动的去维护一个状态，
这个状态用于告知服务端前后两个请求是否来自同一浏览器。而这个状态需要通过 cookie 或者 session 去实现。
cookie 存储在客户端： cookie 是服务器发送到用户浏览器并保存在本地的一小块数据，它会在浏览器下次向同一服务器再发起请求时被携带并发送到服务器上。
cookie 是不可跨域的： 每个 cookie 都会绑定单一的域名，无法在别的域名下获取使用，一级域名和二级域名之间是允许共享使用的（靠的是 domain）。
3)Session
session是另外一种记录服务器和客户端会话状态的机制
session是基于cookie实现的， session存储在服务器端，sessionId会被存储到客户端的cookie中。
session 认证流程：
用户第一次请求服务器的时候，服务器根据用户提交的相关信息，创建对应的 Session
请求返回时将此 Session 的唯一标识信息 SessionID 返回给浏览器
浏览器接收到服务器返回的 SessionID 信息后，会将此信息存入到 Cookie 中，同时 Cookie 记录此 SessionID 属于哪个域名
当用户第二次访问服务器的时候，请求会自动判断此域名下是否存在 Cookie 信息，如果存在自动将 Cookie 信息也发送给服务端，服务端会从 Cookie 中获取 SessionID，
再根据 SessionID 查找对应的 Session 信息，如果没有找到说明用户没有登录或者登录失效，如果找到 Session 证明用户已经登录可执行后面操作。
SessionID 是连接 Cookie 和 Session 的一道桥梁，大部分系统也是根据此原理来验证用户登录状态。
4)Cookie 和 Session 的区别
安全性： Session 比 Cookie 安全，Session 是存储在服务器端的，Cookie 是存储在客户端的。
存取值的类型不同：Cookie 只支持存字符串数据，想要设置其他类型的数据，需要将其转换成字符串，Session 可以存任意数据类型。
有效期不同： Cookie 可设置为长时间保持，比如我们经常使用的默认登录功能，Session 一般失效时间较短，客户端关闭（默认情况下）或者 Session 超时都会失效。
存储大小不同： 单个 Cookie 保存的数据不能超过 4K，Session 可存储数据远高于 Cookie，但是当访问量过多，会占用过多的服务器资源。
5)Access Token（令牌）
访问资源接口（API）时所需要的资源凭证
简单 token 的组成： uid(用户唯一的身份标识)、time(当前时间的时间戳)、sign（签名，token 的前几位以哈希算法压缩成的一定长度的十六进制字符串）
特点：服务端无状态化、可扩展性好;支持移动端设备;安全;支持跨程序调用
token的身份验证流程：
客户端使用用户名跟密码请求登录
服务端收到请求，去验证用户名与密码
验证成功后，服务端会签发一个 token 并把这个 token 发送给客户端
客户端收到 token 以后，会把它存储起来，比如放在 cookie 里或者 localStorage 里
客户端每次向服务端请求资源的时候需要带着服务端签发的 token
服务端收到请求，然后去验证客户端请求里面带着的 token ，如果验证成功，就向客户端返回请求的数据

每一次请求都需要携带 token，需要把 token 放到 HTTP 的 Header 里
基于 token 的用户认证是一种服务端无状态的认证方式，服务端不用存放 token 数据。用解析 token 的计算时间换取 session 的存储空间，从而减轻服务器的压力，减少频繁的查询数据库
token 完全由应用管理，所以它可以避开同源策略
6)Refresh Token
refresh token 是专用于刷新 access token 的 token。如果没有 refresh token，也可以刷新 access token，但每次刷新都要用户输入登录用户名与密码，会很麻烦。有了 refresh token，
可以减少这个麻烦，客户端直接用 refresh token 去更新 access token，无需用户进行额外的操作。

7)Token 和 Session 的区别
Session 是一种记录服务器和客户端会话状态的机制，使服务端有状态化，可以记录会话信息。而 Token 是令牌，访问资源接口（API）时所需要的资源凭证。Token 使服务端无状态化，不会存储会话信息。
Session 和 Token 并不矛盾，作为身份认证 Token 安全性比 Session 好，因为每一个请求都有签名还能防止监听以及重放攻击，而 Session 就必须依赖链路层来保障通讯安全了。
如果你需要实现有状态的会话，仍然可以增加 Session 来在服务器端保存一些状态。

8)JWT
资料：阮一峰老师的 JSON Web Token 入门教程
JSON Web Token（简称 JWT）是目前最流行的跨域认证解决方案。

9)网关
这样讲API网关，你应该能明白了吧！：https://www.sohu.com/a/341757994_463994
1什么是 API 网关：
网关一词最早出现在网络设备，比如两个相互独立的局域网之间通过路由器进行通信，中间的路由被称之为网关。
任何一个应用系统如果需要被其他系统调用，就需要暴露 API，这些 API 代表着一个一个的功能点。
如果两个系统中间通信，在系统之间加上一个中介者协助 API 的调用，这个中介者就是 API 网关。
API 网关可以放在两个系统之间，同时也可以放在客户端与服务端之间。
2为何要使用 API 网关：
网关作为系统的唯一入口，也就是说，进入系统的所有请求都需要经过 API 网关。
当系统外部的应用或者客户端访问系统的时候，都会遇到这样的情况：
系统要判断它们的权限
如果传输协议不一致，需要对协议进行转换
如果调用水平扩展的服务，需要做负载均衡
一旦请求流量超出系统承受的范围，需要做限流操作
针对每个请求以及回复，系统会记录响应的日志
也就是说，只要是涉及到对系统的请求，并且能够从业务中抽离出来的功能，都有可能在网关上实现。
3API 网关服务定位
可以服务于哪些系统或者客户端
API 网关拥有处理请求的能力，从定位来看分为 5 类：
①面向 WebApp，这部分的系统以网站和 H5 应用为主。通过前后端分离的设计，将大部分的业务功能都放在了后端，前面的 Web App 只展示页面的内容。
②MobileApp，这里的 Mobile 指的是 iOS 和 Android，设计思路和 WebApp 基本相同。
区别是 API 网关需要做一些移动设备管理的工作（MDM）。例如：设备的注册，激活，使用，淘汰等，全生命周期的管理。
由于移动设备的特殊性，导致了我们在考虑移动设备请求的时候，需要考虑请求，设备，使用者之间的关系。
③面向合作伙伴的 OpenAPI，通常系统会给合作伙伴提供接口。这些接口会全部开放或者部分开发，在有条件限制（时间，流量）的情况下给合作伙伴访问。因此需要更多考虑 API 网关的流量和安全以及协议转换的管理。
④企业内部可扩展 API，给企业内部的其他部门或者项目使用，也可以作为中台输出的一部分，支持其他系统。这里需要更多地考虑划分功能边界，认证和授权问题。
⑤面向 IOT 设备，会接收来自 IOT 设备的请求，特别是工业传感器等设备。这里需要考虑协议转换和数据过滤。


9 Https
Https单向认证和双向认证：https://blog.csdn.net/zwl1584671413/article/details/81632805
扯一扯HTTPS单向认证、双向认证、抓包原理、反抓包策略:https://www.cnblogs.com/Androidmm/p/11390173.html
Android中https请求的单向认证和双向认证： https://blog.csdn.net/anshi4203351518/article/details/101966682?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
(1)Http
HyperText Transfer Protocol，超文本传输协议，是互联网上使用最广泛的一种协议，所有WWW文件必须遵循的标准。HTTP协议传输的数据都是未加密的，也就是明文的，因此使用HTTP协议传输隐私信息非常不安全。
使用TCP端口为：80
HTTP也有以下明显缺点：
通信使用明文，内容可能被窃听
不验证通信方的身份，因此有可能遭遇伪装
无法证明报文的完整性，所以有可能遭到篡改
(2)Https
Hyper Text Transfer Protocol over Secure Socket Layer，安全的超文本传输协议，网景公式设计了SSL(Secure Sockets Layer)协议用于对Http协议传输的数据进行加密，保证会话过程中的安全性。
使用TCP端口默认为443
HTTPS中的S表示SSL或者TLS，就是在原HTTP的基础上加上一层用于数据加密、解密、身份认证的安全层，即
HTTP + 加密 + 认证 + 完整性保护 = HTTPS
(3)SSL协议加密方式
SSL协议即用到了对称加密也用到了非对称加密(公钥加密)，在建立传输链路时，SSL首先对对称加密的密钥使用公钥进行非对称加密，链路建立好之后，SSL对传输内容使用对称加密。
对称加密：
速度高，可加密内容较大，用来加密会话过程中的消息
公钥加密/非对称加密:
加密速度较慢，但能提供更好的身份认证技术，用来加密对称加密的密钥
(4)单向认证
(5)双向认证
(6)公钥私钥
1)加密和认证
加密是将数据资料加密，使得非法用户即使取得加密过的资料，也无法获取正确的资料内容，所以数据加密可以保护数据，防止监听攻击。其重点在于数据的安全 性。
身份认证是用来判断某个身份的真实性，确认身份后，系统才可以依不同的身份给予不同的权限。其重点在于用户的真实性。两者的侧重点是不同的。
2)公钥私钥
公钥和私钥解释:https://blog.csdn.net/weixin_40204595/article/details/83338386
在现代密码体制中加密和解密是采用不同的密钥（公开密钥），也就是非对称密钥密码系统，每个通信方均需要两个密钥，即公钥和私钥，这两把密钥可以互为加解密。
公钥是公开的，不需要保密，而私钥是由个人自己持有，并且必须妥善保管和注意保密。
公钥私钥的原则：
一个公钥对应一个私钥。
密钥对中，让大家都知道的是公钥，不告诉大家，只有自己知道的，是私钥。
如果用其中一个密钥加密数据，则只有对应的那个密钥才可以解密。
如果用其中一个密钥可以进行解密数据，则该数据必然是对应的那个密钥进行的加密。

(7) 对称加密和非对称加密
1)对称加密 ： 加密和解密数据使用同一个密钥。这种加密方式的特点是速度很快，常见对称加密的算法有 AES；
2)非对称加密： 加密和解密使用不同的密钥，这两个密钥形成有且仅有唯一的配对，叫公钥和私钥。数据用公钥加密后必须用私钥解密，数据用私钥加密后必须用公钥解密。
一般来说私钥自己保留好，把公钥公开给别人（一般公钥不会单独出现，而是会写进证书中），让别人拿自己的公钥加密数据后发给自己，这样只有自己才能解密。
 这种加密方式的特点是速度慢，CPU 开销大，常见非对称加密算法有 RSA。

(8)CA
 CA证书是由CA（Certification Authority）机构发布的数字证书。其内容包含：电子签证机关的信息、公钥用户信息、公钥、签名和有效期。
 这里的公钥服务端的公钥，这里的签名是指：用hash散列函数计算公开的明文信息的信息摘要，然后采用CA的私钥对信息摘要进行加密，加密完的密文就是签名。
  即：证书 = 公钥 + 签名 +申请者和颁发者的信息。 客户端中因为在操作系统中就预置了CA的公钥，所以支持解密签名（因为签名使用CA的私钥加密的）

(9) HTTPS基本思路总结
HTTPS在保证数据安全传输上使用对称加密和非对称加密相结合的方式来进行的，简单来说就是通过一次非对称加密算法进行了最终通信密钥的生成、确认和交换，
然后在后续的通信过程中使用最终通信密钥进行对称加密通信。之所以不是全程非对称加密，是因为非对称加密的计算量大，影响通信效率。

(10)抓包原理
1)抓包原理
HTTPS即使安全，也是能够被抓包的，常见的抓包工具有：Charles、fildder等。
常用的HTTPS抓包方式是作为中间人，对客户端伪装成服务端，对服务端伪装成客户端。简单来说：
截获客户端的HTTPS请求，伪装成中间人客户端去向服务端发送HTTPS请求
接受服务端返回，用自己的证书伪装成中间人服务端向客户端发送数据内容。

2)反抓包策略
为了防止中间人攻击，可以使用SSL-Pinning的技术来反抓包。 可以发现中间人攻击的要点的伪造了一个假的服务端证书给了客户端，客户端误以为真。
解决思路就是，客户端也预置一份服务端的证书，比较一下就知道真假了。
SSL-pinning有两种方式： 证书锁定（Certificate Pinning） 和公钥锁定（ Public Key Pinning）。
证书锁定：需要在客户端代码内置仅接受指定域名的证书，而不接受操作系统或浏览器内置的CA根证书对应的任何证书，通过这种授权方式，
保障了APP与服务端通信的唯一性和安全性，因此客户端与服务端（例如API网关）之间的通信是可以保证绝对安全。但是CA签发证书都存在有效期问题，缺点是在 ** 证书续期后需要将证书重新内置到APP中**。
公钥锁定： 提取证书中的公钥并内置到客户端中，通过与服务器对比公钥值来验证连接的正确性。制作证书密钥时，公钥在证书的续期前后都可以保持不变（即密钥对不变），
所以可以避免证书有效期问题，一般推荐这种做法。

(11) tcp三次握手，四次挥手 vs SSL协议

10 Cookie
Android下对Cookie的读写操作（附Demo）:https://blog.csdn.net/lishuai05251986/article/details/84804199
Android的cookie的接收和发送:https://yeyupiaoling.blog.csdn.net/article/details/71789740?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
Android入门——OkHttp3之Cookies管理及持久化:https://blog.csdn.net/Coding_Beginner/article/details/87289245?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-10.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-10.channel_param
PersistentCookieJar开源库: https://github.com/franmontiel/PersistentCookieJar
(1)在web端的cookie是可以通过服务器端设置保存的，默认是关闭浏览器就清除cookie的，但是可以在服务器端设置cookie的有效期，浏览器就会自动保存cookie，而在Android上是不会自动保存cookie
没有保存和重发cookie给服务器的话，是不会自动登录的
(2)cookie操作
1)Okhttp3
获取cookie:
String cookie = response.header("Set-Cookie");
保存cookie:
SharedPreferences
请求时带上cookie:
.addHeader("cookie", Utils.getCookiePreference(this));
2)GET/POST

11 Session
Cookie与Session的简单使用：https://blog.csdn.net/weixin_42808295/article/details/81290306?utm_medium=distribute.pc_relevant.none-task-blog-title-7&spm=1001.2101.3001.4242
(1)cookie技术可以将信息存储在不同的浏览器中，并且可以实现多次请求下的数据共享。但是如果传输的信息比较多的情况下，使用cookie技术会增加服务器端程序处理的难度，这时我们就可以采用session技术。
session是一种将会话数据保存到服务器端的技术。
对Tomcat而言，Session是一块在服务器开辟的内存空间，其存储结构为ConcurrentHashMap；
session是一种建立在cookie之上的通信状态保留机制，可以实现在服务端存储某个用户的一些信息。
(2)Session操作
1)GET
HttpSession session = request.getSession();
(3)Session的工作原理
服务器创建session后，将session的id以cookie的形式返回给浏览器(?)，只要浏览器不关，再去访问服务器时，就会携带着session的id，服务器发现浏览器带session的id过来，就会使用内存中与之对应的session为之服务。
(4)Session和Cookie的主要区别
Cookie是把用户的数据写给用户的浏览器，而Session技术把用户的数据写到用户自己的session中。
Session是一个对象，其属性也可以是任何类型（cookie只能设置字符串）。
Session对象由服务器创建，开发人员可以调用request对象的getSession方法得到Session对象。
(5)Session存在的问题
安全性，session劫持。
增加服务器压力，因为session是直接存储在服务器的内存中的。
session同步问题，现在一般的应用都会用到多台tomcat服务器，通过负载均衡，同一个会话有可能会被分配到不同的tomcat服务器，因此很可能出现session不一致问题。
(6)session劫持防范
其中一个解决方案就是sessionID的值只允许cookie设置，而不是通过URL重置方式设置，同时设置cookie的httponly为true,这个属性是设置是否可通过客户端脚本访问这个设置的cookie，
第一这个可以防止这个cookie被XSS读取从而引起session劫持，第二cookie设置不会像URL重置方式那么容易获取sessionID。
第二步就是在每个请求里面加上token，实现类似前面章节里面讲的防止form重复递交类似的功能，我们在每个请求里面加上一个隐藏的token，然后每次验证这个token，从而保证用户的请求都是唯一性。
还有一个解决方案就是，我们给session额外设置一个创建时间的值，一旦过了一定的时间，我们销毁这个sessionID，重新生成新的session，这样可以一定程度上防止session劫持的问题。