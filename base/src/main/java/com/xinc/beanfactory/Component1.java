package com.xinc.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * Date：2023/4/18
 * Description：TODO
 *
 * @author neilCao
 * @version 1.0
 */
@Component
public class Component1 {

    public static final Logger log= LoggerFactory.getLogger(Component1.class);

    @Autowired
    private ApplicationContext context ;

    public void register(Object eventListener){
        log.info("用户注册");
        //发送事件
        context.publishEvent(this);
    }
}
