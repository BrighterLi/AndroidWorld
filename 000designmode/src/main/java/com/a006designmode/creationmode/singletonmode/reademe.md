1 单例模式不管用那种方式实现，核心思想都相同
1、构造函数私有化，通过一次静态方法获取一个唯一实例
2、线程安全

最后推荐使用文中DCL方式和静态内部类的方式来创建单例模式。

2
定义
单例模式是比较常见的一种设计模式，目的是保证一个类只能有一个实例，而且自行实例化并向整个系统提供这个实例，避免频繁创建对象，节约内存。
单例模式的应用场景很多，
比如我们电脑的操作系统的回收站就是一个很好的单例模式应用，电脑上的文件、视频、音乐等被删除后都会进入到回收站中；还有计算机中的打印机也是采用单例模式设计的，一个系统中可以存在多个打印任务，但是只能有一个正在工作的任务；Web页面的计数器也是用单例模式实现的，可以不用把每次刷新都记录到数据库中。
通过回味这些应用场景，我们对单例模式的核心思想也就有了更清晰的认识，下面就开始用代码来实现。
在写单例模式的代码之前，我们先简单了解一下两个知识点，关于类的加载顺序和static关键字。
类加载顺序
类加载(classLoader)机制一般遵从下面的加载顺序
如果类还没有被加载：
先执行父类的静态代码块和静态变量初始化，静态代码块和静态变量的执行顺序跟代码中出现的顺序有关。
执行子类的静态代码块和静态变量初始化。
执行父类的实例变量初始化
执行父类的构造函数
执行子类的实例变量初始化
执行子类的构造函数
同时，加载类的过程是线程私有的，别的线程无法进入。
如果类已经被加载：
静态代码块和静态变量不在重复执行，再创建类对象时，只执行与实例相关的变量初始化和构造方法。
static关键字
一个类中如果有成员变量或者方法被static关键字修饰，那么该成员变量或方法将独立于该类的任何对象。它不依赖类特定的实例，被类的所有实例共享，只要这个类被加载，该成员变量或方法就可以通过类名去进行访问，它的作用用一句话来描述就是，不用创建对象就可以调用方法或者变量，这简直就是为单例模式的代码实现量身打造的。
下面将列举几种单例模式的实现方式，其关键方法都是用static修饰的，并且，为了避免单例的类被频繁创建对象，我们可以用private的构造函数来确保单例类无法被外部实例化。
懒汉和饿汉
在程序编写上，一般将单例模式分为两种，分别是饿汉式和懒汉式，
饿汉式：在类加载时就完成了初始化，所以类加载比较慢，但获取对象的速度快。
懒汉式：在类加载时不初始化，等到第一次被使用时才初始化。
代码实现
1、饿汉式 (可用)
public class Singleton {

    private final static Singleton INSTANCE = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return INSTANCE;
    }

}

这是比较常见的写法，在类加载的时候就完成了实例化，避免了多线程的同步问题。当然缺点也是有的，因为类加载时就实例化了，没有达到Lazy Loading (懒加载) 的效果，如果该实例没被使用，内存就浪费了。
2、普通的懒汉式 (线程不安全，不可用)
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}

这是懒汉式中最简单的一种写法，只有在方法第一次被访问时才会实例化，达到了懒加载的效果。但是这种写法有个致命的问题，就是多线程的安全问题。假设对象还没被实例化，然后有两个线程同时访问，那么就可能出现多次实例化的结果，所以这种写法不可采用。
3、同步方法的懒汉式 (可用)
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}

这种写法是对getInstance()加了锁的处理，保证了同一时刻只能有一个线程访问并获得实例，但是缺点也很明显，因为synchronized是修饰整个方法，每个线程访问都要进行同步，而其实这个方法只执行一次实例化代码就够了，每次都同步方法显然效率低下，为了改进这种写法，就有了下面的双重检查懒汉式。
4、双重检查懒汉式 (可用，推荐)
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}

这种写法用了两个if判断，也就是Double-Check，并且同步的不是方法，而是代码块，效率较高，是对第三种写法的改进。为什么要做两次判断呢？这是为了线程安全考虑，还是那个场景，对象还没实例化，两个线程A和B同时访问静态方法并同时运行到第一个if判断语句，这时线程A先进入同步代码块中实例化对象，结束之后线程B也进入同步代码块，如果没有第二个if判断语句，那么线程B也同样会执行实例化对象的操作了。
5、静态内部类 (可用，推荐)
public class Singleton {

    private Singleton() {}

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }

}

这是很多开发者推荐的一种写法，这种静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成对象的实例化。
同时，因为类的静态属性只会在第一次加载类的时候初始化，也就保证了SingletonInstance中的对象只会被实例化一次，并且这个过程也是线程安全的。
6、枚举 (可用、推荐)
public enum Singleton {
    INSTANCE;
}

这种写法在《Effective JAVA》中大为推崇，它可以解决两个问题：
1）线程安全问题。因为Java虚拟机在加载枚举类的时候会使用ClassLoader的方法，这个方法使用了同步代码块来保证线程安全。
2）避免反序列化破坏对象，因为枚举的反序列化并不通过反射实现。
好了，单例模式的几种写法就介绍到这了，最后简单总结一下单例模式的优缺点
单例模式的优缺点
优点
单例类只有一个实例，节省了内存资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能；
单例模式可以在系统设置全局的访问点，优化和共享数据，例如前面说的Web应用的页面计数器就可以用单例模式实现计数值的保存。
缺点
单例模式一般没有接口，扩展的话除了修改代码基本上没有其他途径。