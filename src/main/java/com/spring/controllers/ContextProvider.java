/**
 * 
 */
package com.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author sumitsamson
 *
 */
public class ContextProvider implements ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(ContextProvider.class);

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// TODO Auto-generated method stub
		CONTEXT = context;

	}
	
	
	public static void printAllBeansLoaded(){
		 String[] beanNames = CONTEXT.getBeanDefinitionNames();

	        for (String beanName : beanNames) {

	        	logger.info(beanName + " : " + CONTEXT.getBean(beanName).getClass().toString());
	        	
	        }
		
	}

	/**
	 * Get a Spring bean by type.
	 **/
	public static <T> T getBean(Class<T> beanClass) {
		return CONTEXT.getBean(beanClass);
	}

	/**
	 * Get a Spring bean by name.
	 **/
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}

}
