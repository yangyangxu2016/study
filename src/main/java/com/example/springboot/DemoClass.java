package com.example.springboot;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DemoClass implements InitializingBean, DisposableBean, BeanPostProcessor {


    /**
     * 开始初始化操作
     *
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public java.lang.Object postProcessBeforeInitialization(java.lang.Object bean, java.lang.String beanName) {
        return bean;
    }


    /**
     * 中间的初始化操作 InitializingBean
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("demoClass initializingBean.afterPropertiesSet");
        //init-method
    }

    /**
     * 最后初始化操作
     *
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public java.lang.Object postProcessAfterInitialization(java.lang.Object bean, java.lang.String beanName) {

        return bean;
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DemoClass DisposableBean.destory");
//        destory-method

    }
}
