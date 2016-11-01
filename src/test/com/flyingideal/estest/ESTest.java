package com.flyingideal.estest;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ESTest {
    @Test
    public void test() {
        System.out.println("Hello world");
    }

    public TransportClient getEsClient() throws Exception {
        int port = 9300;

        Settings settings = Settings.builder()
                .put("cluster.name", "flyingideal")
                .put("client.transport.sniff", true).build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), port));
        return client;
    }

    @Test
    public void getFromES() throws Exception {
        TransportClient client = getEsClient();
        GetResponse response = client.prepareGet("us", "tweet", "14").get();
        System.out.println(response.toString());
    }

}
