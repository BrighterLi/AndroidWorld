基本概念
Deep Link，又叫deep linking，中文翻译作深层链接。
简单地从用户体验来讲，Deep Link，就是可以让你在手机的浏览器/Google Search上点击搜索的结果，
便能直接跳转到已安装的应用中的某一个页面的技术。
对于不懂技术的运营人员来说就是一个分享功能，而从技术层面上去简单理解是实现将某APP用户带
到另外APP相对应的内容页面，实现APP之间无缝跳转！

实现原理
DeepLink用到的核心技术就是：URL SCHEMES。不论是IOS还是Android。
URL Schemes 有两个单词：

URL，我们都很清楚，http://www.apple.com 就是个 URL，也叫它链接或网址；
Schemes，表示的是一个 URL 中的一个位置——最初始的位置，即 ://之前的那段字符。
比如 http://www.apple.com 这个网址的 Schemes 是 http。
我们可以像定位一个网页一样，用一种特殊的 URL 来定位一个应用甚至应用里某个具体的功能。
而定位这个应用的，就应该这个应用的 URL 的 Schemes 部分，也就是开头儿那部分。
但是需要注意的是应用的URL Schemes 并不唯一，也就是说一个应用可以“起多个名“，
不同应用的URL Schemes也可能因为名字一样发生冲突。

Android系统级应用，有一些已经定义了URL Schemes，比如短信是 sms:、通话是tel:、email是mailto:，
在定义自己APP的URL Schemes的时候要避免跟系统应用名称一样。
与URL一样，URL Schemes也可以通过传参打开特定的APP界面。
URL：http://images.google.com/images?q=关键字
URL Schemes：weixin://dl/moments（打开微信朋友圈）

