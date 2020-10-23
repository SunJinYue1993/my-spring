package com.study.day03;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDay03 {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Config03.class);
		ac.refresh();
		System.out.println(ac.getBean(AClass.class));
	}
}
