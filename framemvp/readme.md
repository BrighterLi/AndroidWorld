Android MVP:https://www.jianshu.com/p/f5e13706ae52

MVP，即Model(模型)、View(视图)、Presenter(主持人)。MVP是从经典的MVC演变而来。把数据处理，界面显示，逻辑处理分离开来。
界面和数据的所有通信都是通过P层来实现。是一个将后台任务和activities/views/fragment分离的方法，让它们独立于绝大多数跟生命周期相关的事件。

理解MVP：
View：与用户交互，响应用户的操作，显示数据，在Android中，通常是Activity、Fragment，View等；
Presenter：控制层，也负责处理后台任务；
Model：数据层，比如数据库接口，网络数据等；

例子：User现在要从读取一个Jack的名字并设置
MVP特点：
1、模型与视图完全分离，我们可以修改视图而不影响模型
2、可以更高效地使用模型，因为所有的交互都发生在一个地方——Presenter内部
3、我们可以将一个Presenter用于多个视图，而不需要改变Presenter的逻辑。这个特性非常的有用，因为视图的变化总是比模型的变化频繁。
4、如果我们把逻辑放在Presenter中，那么我们就可以脱离用户接口来测试这些逻辑（单元测试）
