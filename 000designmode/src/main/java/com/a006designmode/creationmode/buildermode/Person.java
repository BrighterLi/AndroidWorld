package com.a006designmode.creationmode.buildermode;


//Android模式- Builder模式:https://blog.csdn.net/wuxidemo/article/details/53886014
public class Person {
    private String name;
    private String age;
    private String title;

    //Person类的构造方法最好是外部类不可调用的，因为我们提供了Builder的方式让外部实现了对Person类的初始化，
    // 那么我们就没必要让外部直接通过构造方法的方式创建，所以最好设置为private
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.title = builder.title;
    }

    @Override
    public String toString() {
        return "name:" + name + "age:" + age + "title:" + title;
    }

    // 静态内部类
    //首先Builder是一个静态内部类，之所以需要静态是因为Builder类需要和外部类Person剥离关系，
    // 否则就会耦合在一起，这里的Builder相当于一个独立文件的类一样的效果
    static class Builder {
        private String name;
        private String age;
        private String title;

        //Builder类中的每一个方法都需要返回该构造者，因为这样，
        // 我们才能在每一次设置时都是针对同一个Builder实例进行实例
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        //在build方法才进行对Person类进行初始化，这样做的好处就是可以避免过早地对Person类初始化，
        // 当然你也是可以在Builder的构造函数里就对Person就进行初始化
        public Person build() {
            return new Person(this);
        }
    }
}
