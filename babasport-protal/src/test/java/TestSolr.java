import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * 描述:
 * 创建者:田海波 于 2017年08月20日 22:24.
 */
public class TestSolr {
    public static void main(String[] args) throws IOException, SolrServerException {
        String url = "http://192.168.57.104:8080/solr";
        HttpSolrServer server = new HttpSolrServer(url);
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id","4");
        doc.addField("title","hunan");
        doc.addField("local_t","上海市浦东新区");
        server.add(doc);
        server.commit();
    }
}
