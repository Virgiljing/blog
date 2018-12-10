package com.aisat.hkgott.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringUtil
 *
 * @author virgilin
 * @date 2018/12/4
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    /**
     * 以下这几种方法都可以通过
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Object object= null;
        try {
            if(applicationContext!=null) object=applicationContext.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 注入的方法，applicationContext.xml中配置
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        SpringUtil.applicationContext = arg0;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
