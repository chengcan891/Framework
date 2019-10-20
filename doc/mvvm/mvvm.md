https://blog.csdn.net/xuehuayous/article/details/82777022



![](./img/mvvm.jpg)





1. **View** 只处理用户的即时交互；
2. **ViewModel** 只处理业务逻辑；
3. **Model** 只处理数据存储与获取。

> 平时写的时候没有Data Repository都是直接在ViewModel中直接引用数据源，这是个错误的做法

