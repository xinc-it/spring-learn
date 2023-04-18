package com.xinc.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Date：2023/4/19
 * Description：TODO
 *
 * @author neilCao
 * @version 1.0
 */
@Component
public class Component2 {

    private static final Logger log = LoggerFactory.getLogger(Component2.class);

    //监听事件
    @EventListener
    public void listen(Object event) {
        log.info("用户监听对象：{}", event);
        log.info("发送短信");
    }
}
