### XML -> BeanDefinition解析过程

#### 1. 总览

```java
// Tell the subclass to refresh the internal bean factory.
// 创建容器对象：DefaultListableBeanFactory
// 加载xml配置文件的属性值到当前工厂中，最重要的就是BeanDefinition
ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
此时BeanFactory中属性BeanDefinitionMap有值,说明解析成功
```

#### 2. 具体流程

```markdown
大体流程就是day05的注册流程,写不动,太费劲了
重点解释注解的BeanDefinition的生成
```






