UTF-8

1 各种算法
数据加密，是一门历史悠久的技术，指通过 加密算法和加密 密钥将明文转变为密文，而解密则是通过解密算法和解密密钥将密文恢复为明文。
它的核心是密码学。数据加密目前仍是 计算机系统对信息进行保护的一种最可靠的办法。它利用 密码技术对信息进行加密，实现 信息隐蔽，
从而起到保护信息的安全的作用。
(1)MD5  摘要算法
1)md5是一种信息摘要算法,还有另外一种叫法：指纹。
它可以从一个字符串或一个文件中按照一定的规则生成一个特殊的字符串（这个特殊的字符串就被称之为摘要，
我理解就是从文件中摘一些信息片段加工而来），并且一个文件所对应的MD5摘要是固定的，当文件内容变化后，其MD5值也会不一样
（虽然理论上来说也有可能会一样，但概率极小），因此，在应用中经常使用MD5值来验证一段数据有没有被篡改。
比如，在数据的发送方将原始数据生成出MD5值，然后把原始数据连同其MD5值一起传给接收方，接收该收到数据后，
先将原始数据用MD5算法生成摘要信息，然后再将此摘要信息与发送方发过来的摘要信息进行比较，如果一致就认为原始数据没有被修改，
否则原始数据就是被修改过了。
常见的摘要算法包括：md、sha这两类。md包括md2、md4、md5；sha包括sha1、sha224、sha256、sha384、sha512。
2)ava中实现md5加密，有三种方式
https://www.cnblogs.com/-beyond/p/10575078.html
使用jdk内置的方法实现实现md5加密
使用bc方式实现md5加密
使用Cc方式实现md5加密

(2) AES
高级加密标准（Advanced Encryption Standard，AES）
AES是一个对称分组密码算法
AES最常见的有3种方案，用以适应不同的场景要求，分别是AES-128、AES-192和AES-256。

(3) SHA 摘要算法
1)sha类加密算法有多种，共两大类，一类是sha1，另一类包含多种加密算法：sha224、sha256、sha384、sha512，这些统称为sha2。
其中sha1加密后的长度是160byte，sha2加密之后的密文长度和shaXxx的数字相同，比如sha256加密之后，密文长度为256byte。
2)java中实现sha1加密，有三种方式
https://www.cnblogs.com/-beyond/p/10575078.html
使用jdk内置的方法实现实现md5加密
使用bc方式实现md5加密
使用Cc方式实现md5加密

(4) DES
des对称加密，是一种比较传统的加密方式，其加密运算、解密运算使用的是同样的密钥，信息的发送者和信息的接收者在进行信息的传输与处理时，必须共同持有该密码（称为对称密码）
DES采用了64位的分组长度和56位的密钥长度

(5)Base64
1)是什么
Base64是网络上最常见的用于传输8Bit字节码的编码方式之一，Base64就是一种基于64个可打印字符来表示二进制数据的方法。
Base64编码是从二进制到字符的过程，可用于在HTTP环境下传递较长的标识信息。采用Base64编码具有不可读性，需要解码后才能阅读。
2)为什么用
Base64编码是从二进制值到某些特定字符的编码，这些特定字符一共64个，所以称作Base64。
为什么不直接传输二进制呢？比如图片，或者字符，既然实际传输时它们都是二进制字节流。而且即使Base64编码过的字符串最终也是二进制（通常是UTF-8编码，兼容ASCII编码）在网络上传输的，那么用4/3倍带宽传输数据的Base64究竟有什么意义？
真正的原因是二进制不兼容。某些二进制值，在一些硬件上，比如在不同的路由器，老电脑上，表示的意义不一样，做的处理也不一样。同样，一些老的软件，网络协议也有类似的问题。
但是万幸，Base64使用的64个字符，经ASCII/UTF-8编码后在大多数机器，软件上的行为是一样的。

用记事本打开exe、jpg、pdf这些文件时，我们都会看到一大堆乱码，因为二进制文件包含很多无法显示和打印的字符，所以，如果要让记事本这样的文本处理软件能处理二进制数据，就需要一个二进制到字符串的转换方法。Base64是一种最常见的二进制编码方法。
有些情况下传输不可见字符不方便。比如一个纯文本协议，二进制中可能会出现被当做控制字符处理的部分。这样引起传输失败。

3)应用场景
Base64编码可用于在HTTP环境下传递较长的标识信息。
一个xml当中包含另一个xml数据，此时如果将xml数据直接写入显然不合适，将xml进行适当编码存入较为方便，事实上xml当中的字符一般都是可见字符（0-127之间），但是由于中文的存在，可能存在不可见字符，直接将字符打印在外层xml的数据中显然不合理，那么怎么办呢？
可以使用base64进行编码，然后存入xml，解码反之
其实还有个办法，将byte的值写在xml当中，空格或者，分开，这样也可以将byte数据传入，不过这样更浪费空间，并且不易保存.

4)java中使用
import java.util.Base64;
  对于标准的Base64：
  加密为字符串使用Base64.getEncoder().encodeToString();
  加密为字节数组使用Base64.getEncoder().encode();
  解密使用Base64.getDecoder().decode();
  对于URL安全或MIME的Base64，只需将上述getEncoder()getDecoder()更换为getUrlEncoder()getUrlDecoder()
  或getMimeEncoder()和getMimeDecoder()即可。

2 Demo
java 实现各种加密（MD5 ，SHA-1,SHA-256）: https://blog.csdn.net/qq_33113141/article/details/51858629?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
Java数据加密(MD5,sha1,sha256)：https://blog.csdn.net/chain_fei/article/details/77822830
java各种加密方法实现:https://blog.csdn.net/Q563573095/article/details/79625836?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control

3 各种概念
(1)对称加密非对称加密

4 相关需求
(1) 金融备案整改
之前是一整个密钥存在so库，然后so加固，密钥要改成分段存储，MD5改成sha256,分段加密。