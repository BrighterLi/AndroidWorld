1 Adapter
就是把一些数据在View上显示。可以看作是界面数据绑定的一种理解。
Adapter是连接后端数据和前端显示的适配器接口，是数据和UI（View）之间一个重要的纽带。
主要在View上显示【一般是listview】，它所操纵的数据一般都是一些比较复杂的数据，如数组，链表，数据库，集合等。适配器就像显示器，把复杂的东西按人可以接受的方式来展现。

2 Android常用的Adapter
Android之Adapter用法总结：https://www.cnblogs.com/devinzhang/archive/2012/01/20/2328334.html
BaseAdapter是一个抽象类，继承它需要实现较多的方法，所以也就具有较高的灵活性；
ArrayAdapter支持泛型操作，最为简单，只能展示一行字。
SimpleAdapter有最好的扩充性，可以自定义出各种效果。
SimpleCursorAdapter可以适用于简单的纯文字型ListView，它需要Cursor的字段和UI的id对应起来。如需要实现更复杂的UI也可以重写其他方法。可以认为是SimpleAdapter对数据库的简单结合，可以方便地把数据库的内容以列表的形式展示出来。

ArrayAdapter
列表的显示需要三个元素：
a．ListVeiw 用来展示列表的View。
b．适配器 用来把数据映射到ListView上的中介。
c．数据    具体的将被映射的字符串，图片，或者基本组件。
