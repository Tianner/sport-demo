package com.sunwoda.babasport.cms.service.activemq;

import com.sunwoda.babasport.pojo.SuperPojo;
import com.sunwoda.babasport.cms.service.ProductService;
import com.sunwoda.babasport.cms.service.StaticPageService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.List;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月24日 20:18.
 */
@Component("myTopicReceiver")
public class MyTopicReceiver implements MessageListener {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @Autowired
    @Qualifier("staticPageService")
    private StaticPageService staticPageService;


    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            List<Object> values = (List) mapMessage.getObject("ids");
            Integer isShow = (Integer)mapMessage.getObject("isShow");
            if (values != null) {
                Long id= null;
                for (int i = 0; i < values.size(); i++) {
                    id = (Long) values.get(i);
                    if(isShow == 1){
                        SuperPojo superPojo = this.productService.findProductDetailById(id);
                        try {
                            this.staticPageService.createStaticPageOfProduct(id,superPojo);
                        } catch (TemplateException|IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        this.staticPageService.deleteStaticPageOfProduct(id);
                    }
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
