package com.flyingideal.estest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanchao on 2016/11/1.
 * Elasticsearch version : 5.0.0
 * Questions :如何删除某一个文档里的某一个属性
 */
public class ESTest {

    /**
     * 获取TransportClient对象
     * @return
     * @throws Exception
     */
    public TransportClient getESClient() {
        int port = 9300;
        Settings settings = Settings.builder()
                .put("cluster.name", "flyingideal")     //设置集群名字，如果集群名字为默认值（elasticsearch）,可以不用设置
                .put("client.transport.sniff", true).build();   //嗅探集群的其他部分
        InetAddress address = null;
        try {
           address = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(
                        address, port));  //可能是配置问题，直接配置本机实际IP不好使
        return client;
    }

    /**
     * 将Bean转化为json
     * ①使用jackson将bean转换为JSON
     * ②使用Elasticsearch帮助类
     */
    @Test
    public void getJsonByDifferentWay() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", "yanchao");
        map.put("postDate", new Date());
        map.put("message", "trying out Elasticsearch");
        //①使用jackson将bean转换为JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(map);
            System.out.println("jackson => " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //②使用Elasticsearch帮助类
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject().field("user", "yanchao")
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch")
                    .endObject();
            String json = builder.string();
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJson(int i) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject().field("user", "yanchao" + i)
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch" + i)
                    .endObject();
            return builder.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 索引文档,相当于 POST/PUT /index/type/id {"index jsonString"}
     * 将JSON文档索引为一个名字为“twitter”，类型为“tweet”，id值为1的索引
     * @throws Exception
     */
    @Test
    public void indexResponse() throws Exception {
        IndexResponse response = getESClient()
                .prepareIndex(
                        "twitter",  //index
                        "tweet",    //type
                        "3"         //id,可选参数，如果不提供，ES将自动生成一个22位的ID
                ).setSource(getJson(0))     //getJson()获取Json文档
                .get();     //ES 5.0 API提供了get()方法，实际也是调用.execute().actionGet();
        System.out.println(response.toString());
        //output(created):IndexResponse[index=twitter,type=tweet,id=3,version=1,result=created,shards={"_shards":{"total":2,"successful":1,"failed":0}}]
        //output(updated):IndexResponse[index=twitter,type=tweet,id=1,version=2,result=updated,shards={"_shards":{"total":2,"successful":1,"failed":0}}]
        System.out.println(response);
    }

    /**
     * GetResponse测试（获取API）,相当于 GET /idnex/type/id
     * @throws Exception
     */
    @Test
    public void getResponse() throws Exception {
        TransportClient client = getESClient();
        GetResponse response = client.prepareGet(
                "twitter",  //index
                "tweet",    //type
                "3")        //id
                .get();
        System.out.println(response.toString());
        //output:{"_index":"twitter","_type":"tweet","_id":"1","_version":2,"found":true,"_source":{"user":"yanchao","postDate":"2016-11-02T03:06:15.493Z","message":"trying out Elasticsearch"}}
    }

    public void getResponseNotForTest(String id, String... indexAndType) throws Exception {
        TransportClient client = getESClient();
        String index = null;
        String type = null;
        if (indexAndType.length >= 2) {
            index = StringUtils.isEmpty(indexAndType[0]) ? "twitter" : indexAndType[0];
            type = StringUtils.isEmpty(indexAndType[1]) ? "tweet" : indexAndType[1];
        } else {
            index = "twitter";
            type = "tweet";
        }

        GetResponse response = client.prepareGet(index, type, id).get();
        System.out.println(response.toString());
    }

    /**
     * DeleteResponse测试，相当于 DELETE /index/type/id
     * @throws Exception
     */
    @Test
    public void deleteResponse() {
        try {
            DeleteResponse response = getESClient().prepareDelete(
                    "twitter",
                    "tweet",
                    "4").get();
            //System.out.println(response.toString()); //json转换会异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * UpdateResponse Test
     * @throws Exception
     */
    @Test
    public void updateRequest() throws Exception {
        TransportClient client = getESClient();
        String json = XContentFactory.jsonBuilder().startObject()
                .field("gender", "fm").endObject().string();
        //①通过创建updateRequest，然后发送给client调用update()
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("3");
        updateRequest.doc(XContentFactory.jsonBuilder().startObject()
                        .field("gender", "male").endObject());
        //UpdateResponse response = client.update(updateRequest).get();
        getResponseNotForTest("3");

        //②update by merging document
        client.prepareUpdate("twitter", "tweet", "3")
                .setDoc(json).get();
        getResponseNotForTest("3");

        //③update by script
        client.prepareUpdate("twitter", "tweet", "3")
                .setScript(new Script("ctx._source.gender=\"m\"",
                        ScriptService.ScriptType.INLINE, null, null))
                .get();
        getResponseNotForTest("3");

        //④同③类似，使用UpdateRequest(index, type, id)构造方法
        UpdateRequest request = new UpdateRequest("twitter", "tweet", "3")
                .script(new Script("ctx._source.gender = \"f\""));
        client.update(request).get();
        getResponseNotForTest("3");

        //⑤Upsert
    }

    /**
     * Multi Get API（5.0新增），可用来一次获取多个文档
     * @throws Exception
     */
    @Test
    public void multiGetItemResponse() throws Exception {
        String index = "twitter", type = "tweet";
        MultiGetResponse multiGetResponses = getESClient().prepareMultiGet()
                .add(index, type, "1")
                .add(index, type, "2", "3").get();
        for (MultiGetItemResponse itemResponse : multiGetResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }
    }

    /**
     * Bulk API，用于批量索引和删除文档
     * @throws Exception
     */
    @Test
    public void bulk() throws Exception {
        TransportClient client = getESClient();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        bulkRequestBuilder.add(client.prepareIndex("twitter", "tweet", "5")
                .setSource(getJson(5)));
        bulkRequestBuilder.add(client.prepareIndex("twitter", "tweet", "6")
                .setSource(getJson(6)));
        BulkResponse bulkResponse = bulkRequestBuilder.get();
        if (bulkResponse.hasFailures()) {
            System.out.println("索引数据出现异常");
        } else {
            System.out.println("索引数据完成");
            getResponseNotForTest("5");
            getResponseNotForTest("6");
        }
    }

    /**
     * Bulk Processsor，在Bulk批量执行任务的基础上添加了监听事件及任务的控制，类似于AOP的前置通知、后置通知等
     * @throws Exception
     */
    @Test
    public void bulkProcessor() throws Exception {
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                getESClient(),
                new BulkProcessor.Listener() {
                    //This method is called just before bulk is executed.
                    // You can for example see the numberOfActions with request.numberOfActions()
                    @Override
                    public void beforeBulk(long l, BulkRequest bulkRequest) {
                        System.out.println("准备开始任务，任务量 : " + bulkRequest.numberOfActions());
                    }

                    //This method is called after bulk execution.
                    // You can for example check if there was some failing requests with response.hasFailures()
                    @Override
                    public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                        if (bulkResponse.hasFailures()) {
                            System.out.println("fail");
                        } else {
                            System.out.println("success");
                            try {
                                getResponseNotForTest("6");
                                getResponseNotForTest("7");
                                getResponseNotForTest("8");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    //This method is called when the bulk failed and raised a Throwable
                    @Override
                    public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                        System.out.println("throws Exception!");
                    }
                }
        )
                .setBulkActions(10000)   //execute the bulk every 10 000 requests, default 1000
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))     //flush the bulk every 1gb, default 5mb
                .setFlushInterval(TimeValue.timeValueSeconds(5))    //flush the bulk every 5 seconds whatever the number of requests, default does not set flushInterval
                .setConcurrentRequests(1)   //Set the number of concurrent requests. A value of 0 means that only a single request will be allowed to be executed.
                                            // A value of 1 means 1 concurrent request is allowed to be executed while accumulating new bulk requests. Default 1
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                            //Set a custom backoff policy which will initially wait for 100ms, increase exponentially and retries up to three times
                            //By default, sets backoffPolicy to an exponential backoff with 8 retries and a start delay of 50ms. The total wait time is roughly 5.1 seconds.
                .build();

        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "6"));
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "6").source(getJson(6)));
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "7").source(getJson(7)));
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "8").source(getJson(8)));

        //When all documents are loaded to the BulkProcessor it can be closed by using awaitClose or close methods
        bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
        //bulkProcessor.close();
    }

    //--------------------Search API---------

    /**
     * Search API，可跨索引跨类型执行查询，最简形式：SearchResponse res = getESClient().prepareSearch().get(); ==> GET /_search
     * 代码中设置相当于：GET /twitter,us/tweet/_search?q=user:yanchao
     *  https://www.elastic.co/guide/en/elasticsearch/reference/5.0/search-search.html
     */
    @Test
    public void searchResponse() {
        SearchResponse response = getESClient()
                .prepareSearch("twitter", "us")     //不设置参数的话，查询所有的indices
                .setTypes("tweet")      //不设置的话查询所有type
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("user", "yanchao"))     // Query
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60)
                //.setExplain(true)
                .get();
        System.out.println(response.toString());
    }

    /**
     * Scroll Test，Scroll不是用于实时请求，而是用于处理大量数据的
     * 一个滚屏搜索允许我们做一个初始阶段搜索并且持续批量从Elasticsearch里拉取结果直到没有结果剩下。这有点像传统数据库里的cursors（游标）。
     * 滚屏搜索会及时制作快照。这个快照不会包含任何在初始阶段搜索请求后对index做的修改。
     * 它通过将旧的数据文件保存在手边，所以可以保护index的样子看起来像搜索开始时的样子。
     */
    @Test
    public void scroll() {
        QueryBuilder qb = QueryBuilders.termQuery("user", "yanchao");
        SearchResponse scrollResponse = getESClient().prepareSearch()
                .addSort("postDate", SortOrder.DESC)
                .setScroll(new TimeValue(60000))    //设置scroll持续时间为1分钟
                .setQuery(qb)   //设置查询条件
                .setSize(2)     //每次返回的条数，数据量较小，设置为2用于观察效果
                .get();
        do {
            System.out.println("-------------another request---------");
            for (SearchHit hit : scrollResponse.getHits().getHits()) {
                System.out.println(hit.getSource());
            }
            //第一次请求的时候不会返回任何的hits，但会返回一个scrollId，第二次请求的时候需带上这个Id
            scrollResponse = getESClient().prepareSearchScroll(
                    scrollResponse.getScrollId())       //设置scrollId,在第一次获取到scrollId后，第二次请求无需设置index和type
                    .setScroll(new TimeValue(60000))    //告诉Elasticsearch重新设置scroll持续时间为1分钟
                    .get();
        } while(scrollResponse.getHits().getHits().length > 0);
    }

    //MultiSearch
    @Test
    public void multiSearch() {
        TransportClient client = getESClient();
        SearchRequestBuilder srb1 = client.prepareSearch()
                .setQuery(QueryBuilders.queryStringQuery("Elasticsearch"))
                .setSize(1);
        SearchRequestBuilder srb2 = client.prepareSearch()
                .setQuery(QueryBuilders.matchQuery("user", "yanchao"))
                .setSize(1);

        MultiSearchResponse msr = client.prepareMultiSearch()
                .add(srb1).add(srb2).get();

        long nbHits = 0;
        for (MultiSearchResponse.Item item : msr.getResponses()) {
            SearchResponse response = item.getResponse();
            System.out.println(nbHits + "----" + response.toString());
            nbHits += response.getHits().getTotalHits();
        }
    }

    //Terminate After
    @Test
    public void terminateAfter() {
        SearchResponse response = getESClient().prepareSearch()
                .setTerminateAfter(100000)
                .get();
        System.out.println(response.isTerminatedEarly());
    }

    //----------Query DSL-----
    //Match All Query:相当于 GET /_search {"query" : { "march_all": {}}}
    /**
     *
     */
    @Test
    public void matchAllQuery() {
        QueryBuilder qb = QueryBuilders.matchAllQuery();
        SearchResponse response = getESClient().prepareSearch()
                .setQuery(qb).get();
        System.out.println(response.getHits().getTotalHits());
    }

    //Match Query == GET /_search {"query" : {"match": {"user": "yanchao"}}}
    /**
     *官方说明： The standard query for performing full text queries, including fuzzy matching and phrase or proximity queries.
     *翻译：用于执行全文查询的标准查询，包括模糊匹配、词组或邻近查询
     *说明：Match Query 在执行搜索之前会对被搜索内容进行分词，然后执行查询，部分分词匹配上的document也会被返回
     */
    @Test
    public void matchQuery() {
        QueryBuilder qb = QueryBuilders.matchQuery("user", "yanchao");
        SearchResponse response = getESClient().prepareSearch()
                .setQuery(qb).get();
        System.out.println(response.toString());
    }

    //Multi Match Query == GET /_search {"query":{"multi_match": {"query":"yanchao", "fields":["user","message"]}}}
    /**
     *官方说明：The multi-field version of the match query.
     *翻译：Match Query 的多字段查询版本
     *说明：可以指定多个字段针对某一内容进行查询
     */
    @Test
    public void multiMatchQuery() {
        QueryBuilder qb = QueryBuilders.multiMatchQuery(
                "yanchao",          //text
                "user", "message"   //field
        );
        SearchResponse response = getESClient().prepareSearch()
                .setQuery(qb).get();
        System.out.println(response.getHits().getTotalHits());
    }


    //common terms query: 这个查询的最有趣的属性是它自动适应域特定的停用词
    @Test
    public void commonTermsQuery() {
        QueryBuilder qb = QueryBuilders.commonTermsQuery("message", "多少");
        SearchResponse response = getESClient().prepareSearch()
                .setQuery(qb).get();
        System.out.println(response.toString());
    }
}
