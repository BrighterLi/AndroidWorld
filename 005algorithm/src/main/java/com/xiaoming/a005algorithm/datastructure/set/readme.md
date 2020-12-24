1 数据集合
(1)列表

2 区别
(1)HashMap、Hashtable、ConcurrentHashMap的原理与区别
ConcurrentHashMap是使用了锁分段技术来保证线程安全的。
锁分段技术：首先将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。