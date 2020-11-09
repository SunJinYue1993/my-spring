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

	@Test
	public void debug03() {
		// springboot核心思想,基于starter来自动配置应用
		// springboot底层也是用spring框架的ConfigurationClassPostProcessor这个类进行扩展，来实现自动配置，核心在@Import注解
		// 基本步骤：获得启动类上的注解，由于注解的继承，ConfigurationClassPostProcessor会递归处理所有的注解，直到解析@Import
		// 反射实例化@Import的类，这个类将获得所有自动配置的配置类，通过starter进行一次过滤，那么这些剩下的类就是自动配置类。
		AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(ColorConfig.class);
		Green green = annotation.getBean(Green.class);
		System.out.println("bean = " + green);
		System.out.println("debug03() over...");
	}

}
