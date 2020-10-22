### Spring两个上下文

#### 1. 为什么会有各种各样的ApplicationContext上下文？



#### 2. ClassPathXMLApplicationContext和AnnotationConfigApplicationContext

- ClassPathXMLApplicationContext结构图

![](Spring两个上下文的区别.assets/1603198174(1).jpg)

- AnnotationConfigApplicationContext结构图

![](Spring两个上下文的区别.assets/1603198195(1).jpg)

总结: 

- 相同点

  1. 在抽象层AbstractApplicationContext以上的功能是一样的
  2. 也就是说共用AbstractApplicationContext.refresh()提供的模板     - 模板方法模式

- 不同点

  1. 比如refresh()的第二个方法obtainFreshBeanFactory().refreshBeanFactory()走的流程完全不一样!

     一个是走AbstractRefreshableApplicationContext实现的refreshBeanFactory()

     另一个是GenericApplicationContext实现的refreshBeanFactory(), 这就意味着BeanDefinition的生成有不同的实现.

  2. ClassPathXMLApplicationContext通过AbstractRefreshableApplicationContext实现的refreshBeanFactory()生成BeanDefinition, 它是`通过refreshBeanFactory()解析xml生成的BeanDefinition`,没有register(??)注册方法, 但有还是有注册概念，只是方法名字一样！

     ```java
     // 关键流程
     int count = registerBeanDefinitions(doc, resource); //这个方法进行注册，xml被解析成doc
     ....
     BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry()); // ☆向ioc容器注册解析得到的beanDefinition的地方
     ```
  
  3. AnnotationConfigApplicationContext`通过ac.register(???)进行注册封装成BeanDefinition`

#### 3. 注解驱动和xml配置BeanDefinition生成的时机?

待补充: 简而言之 
1. 注解驱动
注解时注册完成后就封装成了BeanDefinition,new AnnotationConfigApplicationContext().register(HteEntity.class);
两个核心的解析类
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {
	// 通过注解注册 -> BeanDefinition
	private final AnnotatedBeanDefinitionReader reader;
	// 通过扫描包批量注册 -> BeanDefinition
	private final ClassPathBeanDefinitionScanner scanner;
	
	registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());//注册完成
}

2. xml配置
    new ClassPathXmlApplicationContext("applicationContext.xml"); ->> refresh();方法

```java
refresh(){
obtainFreshBeanFactory().refreshBeanFactory().loadBeanDefinitions(beanFactory).loadBeanDefinitions(beanDefinitionReader).....
解析资源成 Document 对象, Document -> 用Document写逻辑来封装成 BeanDefinition -> BeanDefinitionHolder -> put BeanDefinitionMap中
结束....
}
```
