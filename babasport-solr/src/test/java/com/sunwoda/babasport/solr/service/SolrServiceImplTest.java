package com.sunwoda.babasport.solr.service;

import com.sunwoda.babasport.service.SolrService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.AppendedSolrParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.MultiMapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月21日 19:40.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})
public class SolrServiceImplTest {
    @Autowired
    @Qualifier("solrServer")
    private SolrServer solrServer;

    @Test
    public void findByKeyword() throws Exception {
        SolrQuery solrQuery = new SolrQuery("tian_name:最新运动");
        QueryResponse query = this.solrServer.query(solrQuery);
        SolrDocumentList documents = query.getResults();
        System.out.println("开始打印查询结果");
        for (SolrDocument document:documents) {
            System.out.println("---"+document);
        }
        System.out.println("开始打印查询结果");
    }

}