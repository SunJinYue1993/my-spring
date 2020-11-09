### Spring学习手册
#### 源码阅读原则
1. 不能debug不读
2. 没有问题不读

so,以问题为驱动逐步学习Spring源码, 问题的解答以最直观的源码来解答
所有资料在spring-study模块,包名day01-day999
- main是一个问题的技术积累
- test是一个问题的调试入口

#### 所有问题
1. day01: BeanFactoryPostProcessor接口在bean的生命周期中的作用？
2. day02: @Component@ComponentScan@Configuration等注解注册和增强过程?
3. day03: Spring循环依赖如何将解决?
4. day04: spring-framework框架环境搭建?
5. day05: 
    - 为什么会有各种各样的ApplicationContext上下文？
    - ClassPathXMLApplicationContext和AnnotationConfigApplicationContext的区别?
    - BeanFactory原料(BeanDefinition)由何而来？(注解驱动和xml配置BeanDefinition生成的时机- BeanDefinitionNames 和 BeanDefinitionMap)
6. day06: Spring有几个BeanDefinitionReader, 干什么的？
7. day07: 从xml配置 -> BeanDefinition的过程？如何自定义xml标签扩展(经常在源码包中看到的spring.handlers,spring.schemas,spring.factories都是什么玩意)？
8. day08: 从注解配置 -> BeanDefinition的过程？与springboot的基于约定开发的关系?ConfigurationClassPostProcessor类?
9. day09: Spring顶层设计BeanFactory接口？
10. day10: BeanFactory的各个子接口作用？ 
11. day11: Spring顶层容器模板AbstractApplicationContext？
12. day12: Spring的AbstractApplicationContext各个子类的作用？
13. day13: Spring的BeanDefinition有几种?
☆ 不在github仓库上为每行代码更新注解，实在是费事。截止2020-11-09 







l