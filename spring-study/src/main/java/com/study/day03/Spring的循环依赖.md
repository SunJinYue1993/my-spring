## Spring的循环依赖是如何解决?
### 前置概念
在Spring里, 对象和Bean是两个不同概念, 官方文档也是有相关说明!
1. 对象: 就是java里简单new出来的对象
2. Bean: Bean在Spring里是经历过完成的生命周期的对象,最终放到SingtalonObjectes单例池里.BeanFactory注释说明了一个Bean的完整生命周期

### 什么是循环依赖?
A依赖B, B依赖A. 当A成为一个Bean, A的成员变量B有一个引用地址指向B. B也同样如此
需要注意的是必须是 单例对象
### Spring如何解决循环依赖问题?
事前想一下,如果我们写会是怎样的逻辑:
当创建对象A, 依赖注入B, 发现没有B,那就去new B再set进去.

