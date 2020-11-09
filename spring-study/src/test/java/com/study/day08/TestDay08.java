package com.study.day08;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1. 从注解配置 -> BeanDefinition的过程?
 * 2. 与springboot的基于约定开发的关系?
 * 3. ConfigurationClassPostProcessor类?
 */
public class TestDay08 {

    @Test
    public void debug01() {
        // register(ColorConfig.class); 注册到BeanFactory; 生成ColorConfig的BeanDefinition
        // invokeBeanFactoryPostProcessors(beanFactory); 进行增强时解析ColorConfig的注解,然后对这些注解进行解析
        // 在springboot中这个ColorConfig写死spring.factories中,基于约定大于配置的思想封装大量默认配置,方便了我们的开发
        AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(ColorConfig.class);
        ColorConfig bean = annotation.getBean(ColorConfig.class);
        System.out.println("bean = " + bean);
        System.out.println("debug01() over...");
    }

    @Test
    public void debug02() {
        // 如果去掉ColorConfig类的@ComponentScan, 能否在工厂拿到Red.class;
        // 不能,因为spring在怎么神,代码也是写死的! 第一: Red没有register; 第二: 解析ColorConfig没有发现@ComponentScan,自然不能parse Red类
        AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(ColorConfig.class);
        Red red = annotation.getBean(Red.class);
        System.out.println("bean = " + red);
        System.out.println("debug02() over...");
    }

}
