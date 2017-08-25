package com.sunwoda.babasport.cms.service.activemq;

import com.sunwoda.babasport.cms.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月24日 20:18.
 */
@Component("myQueueReceiver")
public class MyQueueReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;

    }
}
