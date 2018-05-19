/**
 * 
 */
package com.spring.controllers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author sumitsamson
 *
 */
public class ContextProvider implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// TODO Auto-generated method stub
		CONTEXT = context;

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
