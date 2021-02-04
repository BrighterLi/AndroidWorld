1 lifecycle
Android Lifecycle结合RxJava&Retrofit实现安全的网络回调:https://cloud.tencent.com/developer/article/1327880
生命周期感知组件可以响应另一个组件生命周期的变化（例如Activity和Fragment的生命周期状态更改）。 这些(实现了Lifecycle的)组件可帮助你构建组织性更好、更轻、更易于维护的代码。
Lifecycle 是具有生命周期感知能力的组件，也就是说，我们能在 Activity 或者 Fragment 的生命周期发生变化的时候得到通知。我们往往会在 Activity 的各种生命中周期方法里执行特定的方法