package com.xinc.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * Date：${DATE}
 * Description：TODO
 *
 * @author ${USER}
 * @version 1.0
 */
@SpringBootApplication
public class Main {
    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, IOException {

        /**
         * BeanFactory是什么
         * 1. 是ApplicationContext的父接口
         * 2. 是Spring的核心容器，也是ApplicationContext的成员属性
         */
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        /**
         * BeanFactory有哪些功能
         * 1. 获取Bean
         * 2.通过其实现类，实现控制反转、依赖注入功能
         */
        //1. 获取单例成员属性信息
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        //获取beanfactory对象实例中对应singletonObjects属性值
        Map<String, Object> beans = ((Map<String, Object>) singletonObjects.get(beanFactory));
        beans.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));


        /**
         * 国际化的实现
         * 需要在配置文件指定多余文件目录名
         */

        System.out.println(context.getMessage("hi", null, Locale.getDefault()));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
        System.out.println(context.getMessage("hi", null, Locale.SIMPLIFIED_CHINESE));


        /**
         * 环境信息的获取
         * 1. 可以获取本地机器环境信息
         * 2. 可以获取application.properties的配置信息
         */
        //1. 获取本地环境信息
        System.out.println(context.getEnvironment().getProperty("path"));
        //获取配置文件信息
        System.out.println(context.getEnvironment().getProperty("spring.messages.basename"));
        System.out.println(context.getEnvironment().getProperty("classpath"));

        /**
         * 事件发送和监听
         */
        context.getBean("component1", Component1.class).register(beanFactory);

        /**
         * 通过资源路径信息获取对应资源路径
         */
        Resource[] resources = context.getResources("classpath:application.properties");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
        //获取各个包类路径下的资源文件
        resources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources) {
            System.out.println(resource);
        }

    }
}