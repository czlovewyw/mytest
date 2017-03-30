package elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过连接的客户端
 * Created by chenzhe8 on 2017/3/22.
 */
public class Test2 {
    public static void main(String[] args) throws UnknownHostException {
        Map<String,Object> mapping = new HashMap<>();
        mapping.put("cluster.name","my-application");
        mapping.put("client.transport.sniff",true);
//        用map要求map是<string,string>型
//        Settings settings = Settings.settingsBuilder().put(mapping).build();
        Settings settings = Settings.settingsBuilder().put("cluster.name","my-application").put("client.transport.sniff",true).build();
        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
//        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject("setting").field("number_of_shards",5).field("number_of_replicas",1).endObject().startObject().startObject("test_index").startObject("properties")
        getDoc(client);
    }

    public static void updateDoc(Client client){
//        IndexResponse response = client.prepareIndex();
    }

    public static void getDoc(Client client){
        GetResponse response = client.prepareGet("secisland","sectype","1").get();
        String source = response.getSource().toString();
        long version = response.getVersion();
        String indexName = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        System.out.println("index:"+indexName+"\ntype:"+type+"\nid:"+id+"\nversion:"+version+"\nsource:"+source);
    }
}
