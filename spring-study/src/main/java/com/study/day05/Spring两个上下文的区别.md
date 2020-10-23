### Spring上下文

#### 1. 为什么会有几种ApplicationContext上下文？

- ClassPathXMLApplicationContext结构图

![](Spring两个上下文的区别.assets/1603198174(1).jpg)

- AnnotationConfigApplicationContext结构图

![](Spring两个上下文的区别.assets/1603198195(1).jpg)

#### 2. 两者的异同？

- 相同点

  1. 在抽象层AbstractApplicationContext以上的功能是一样的
  2. 也就是说共用AbstractApplicationContext.refresh()提供的模板     - 模板方法模式

- 不同点

  1. 比如refresh()的第二个方法obtainFreshBeanFactory().refreshBeanFactory()走的流程完全不一样!

     - 一个是走AbstractRefreshableApplicationContext实现的refreshBeanFactory()

     - 一个是GenericApplicationContext实现的refreshBeanFactory(), 这就意味着BeanDefinition的生成有不同的实现.

  2. ClassPathXMLApplicationContext通过AbstractRefreshableApplicationContext实现的refreshBeanFactory()生成BeanDefinition, 它是`通过refreshBeanFactory()解析xml生成的BeanDefinition`,没有register(??)注册方法, 但有还是有注册概念，只是方法名字一样！

     ```java
     // 关键流程
     int count = registerBeanDefinitions(doc, resource); //这个方法进行注册，xml被解析成doc
     ....
     BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry()); // ☆向ioc容器注册解析得到的beanDefinition的地方
     ```
  
  3. AnnotationConfigApplicationContext`通过ac.register(xxx.class)进行注册封装成BeanDefinition`

#### 3. 注解驱动和xml配置BeanDefinition生成的时机?

1. 注解配置

  ```java
  new AnnotationConfigApplicationContext().register(HteEntity.class);
  // 两个核心的解析类
  public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {
  // 通过解析各种注解注册(比如@Component) -> BeanDefinition
  private final AnnotatedBeanDefinitionReader reader;
  // 通过扫描包批量注册(比如：@ComponentScan) -> BeanDefinition
  private final ClassPathBeanDefinitionScanner scanner;
  
  this.reader.register(componentClasses);
  registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());//注册完成
  }
  ```

  

2. xml配置

    ```java
    new ClassPathXmlApplicationContext("applicationContext.xml"); ->> refresh();方法
    // debug流程
    obtainFreshBeanFactory();
    refreshBeanFactory();
    loadBeanDefinitions(beanFactory); 
    loadBeanDefinitions(beanDefinitionReader);
    reader.loadBeanDefinitions(configLocations);
    count += loadBeanDefinitions(location);
    doLoadBeanDefinitions(inputSource, encodedResource.getResource());
    int count = registerBeanDefinitions(doc, resource);
    documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
    parseBeanDefinitions(root, this.delegate);  // ☆ 参数: 根节点, 解析器
    	1. parseDefaultElement(ele, delegate);// 解析spring的命名空间,比如beans,bean标签
    	2. delegate.parseCustomElement(ele);// ☆解析其他命名空间标签,比如我们可以扩展自己定义的标签
    ```

3. 总结

   总之，经过下面两个方法已经有了生产bean的原料(BeanDefinition)，并且已经注册到beanFactory

<img src="Spring两个上下文的区别.assets/1603432430(1).jpg" style="zoom:50%;" align="left"/>

<img src="Spring两个上下文的区别.assets/1603435650(1).jpg" style="zoom: 50%;" align="left"/>