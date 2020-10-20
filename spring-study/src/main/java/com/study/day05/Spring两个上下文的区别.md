### Spring两个上下文的区别?

#### 1. ClassPathXMLApplicationContext结构图

![](Spring两个上下文的区别.assets/1603198174(1).jpg)

#### 2. AnnotationConfigApplicationContext结构图

![](Spring两个上下文的区别.assets/1603198195(1).jpg)

总结: 

- 相同点

  1. 在抽象层AbstractApplicationContext以上的功能是一样的
  2. 公用refresh()提供的模板

- 不同点

  1. 比如refresh()的第二个方法obtainFreshBeanFactory().refreshBeanFactory()走的流程完全不一样!

     一个是走AbstractRefreshableApplicationContext实现的refreshBeanFactory()

     另一个是GenericApplicationContext实现的refreshBeanFactory(), 这就意味着BeanDefinition的生成有不同的实现.

  2. ClassPathXMLApplicationContext通过AbstractRefreshableApplicationContext实现的refreshBeanFactory()生成BeanDefinition, 它是`通过refreshBeanFactory()解析xml生成的BeanDefinition`,没有register()注册方法

  3. AnnotationConfigApplicationContext`通过ac.register()进行注册封装成BeanDefinition`

