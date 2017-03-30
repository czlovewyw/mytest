package elasticsearch;


import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * 嵌入式节点客户端
 * 目前有报错，尚未解决
 * Created by chenzhe8 on 2017/3/22.
 */
public class Test {
    public static void main(String[] args) {
        Node node = NodeBuilder.nodeBuilder().node();
        Client client = node.client();
    }
}
