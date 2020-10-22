package com.study.day05;

import com.study.day02.config.HteEntity;
import com.study.day03.AClass;
import com.study.day03.Config03;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * day05: ClassPathXMLApplicationContext和AnnotationConfigApplicationContext的区别?
 * BeanFactory原料有何而来？(注解驱动和xml配置BeanDefinition生成的时机- BeanDefinitionNames 和 BeanDefinitionMap)
 */
public class TestDay05 {

	@Test
	public void test01(){
		//AbstractApplicationContext.refresh().obtainFreshBeanFactory()
		// -> AbstractRefreshableApplicationContext.loadBeanDefinitions(beanFactory); 生成BeanDefinitionMap有值
		ClassPathXmlApplicationContext xmlAC = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(xmlAC.getBean("person"));
	}

	@Test
	public void test02(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(/*HteEntity.class*/);
		ac.register(HteEntity.class);// 生成BeanDefinitionMap有值
		ac.refresh();
		System.out.println(ac.getBean(HteEntity.class));
	}
}
