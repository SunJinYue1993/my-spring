package com.study.day01.config;

import com.study.day01.service.TwoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition oneServiceBeanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition("oneService");
		oneServiceBeanDefinition.setBeanClass(TwoService.class);
	}

}
