package com.sunwoda.babasport.solr.service.activemq;

import com.sunwoda.babasport.cms.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月24日 20:18.
 */
@Component("myTopicReceiver")
public class MyTopicReceiver implements MessageListener {
    @Autowired
    @Qualifier("solrService")
    private SolrService solrService;


    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            Integer isShow = (Integer)mapMessage.getObject("isShow");
            System.err.println("--123-"+isShow);

            List<Object> values = (List)mapMessage.getObject("ids");
            Long[] ids = new Long[values.size()];
            if(values!=null){
                for(int i = 0;i<values.size();i++){
                    ids[i] = (Long)values.get(i);
                }
            }
            this.solrService.addOrDelProductSolr(ids,isShow);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
