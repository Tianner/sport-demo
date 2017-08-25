package com.sunwoda.babasport.product.service.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Map;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月24日 19:30.
 */
@Component("myQueueSender")
public class MyQueueSender {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    /**
     * 发送字符串消息到队列中
     * @param queueName
     * @param message
     */
    public void sendStrMessage(String queueName,final String message){
        this.jmsTopicTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    /**
     * 将map数据发送到指定队列中去
     * @param queueName
     * @param mapMessage
     */
    public void sendMapMessage(String queueName,final Map<String,Object> mapMessage){
        this.jmsTopicTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                for (String key:mapMessage.keySet()) {
                    message.setObject(key,mapMessage.get(key));
                }
                return message;
            }
        });
    }
}
