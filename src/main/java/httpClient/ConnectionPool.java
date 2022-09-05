package httpClient;

import org.apache.catalina.connector.Response;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.io.InputStream;

public class ConnectionPool {


    private static final HttpHost HOST = new HttpHost("http://www.baidu.com");
    private static final HttpGet GET = new HttpGet("http://www.baidu.com");

    public static void main(String[] args) throws IOException {

        HttpClientBuilder clientBuilder = getClientBuilder(); // 会创建连接池

        // 这个client就是带有连接池管理的client，在每次execute时，内部的connection都是从pool中拿的
        CloseableHttpClient client = clientBuilder.build();
        client.execute(GET);
//        client.close();// 把整个连接池都关闭了

        CloseableHttpClient client2 = clientBuilder.build();

        CloseableHttpResponse response = client2.execute(GET);


        InputStream in = response.getEntity().getContent();
        in.close();// 会释放链接，归还到池中，可以让别的请求复用该connection

        // connHolder.close(); ->  cm.releaseConn(reuseable=false)
        // 会真正的关掉当前使用的connection，并且不能被复用，无法达到重复利用的目的
        response.close();


        client.close();// 把整个连接池都关闭了
    }

    private static HttpClientBuilder getClientBuilder() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setSocketConfig(HOST, SocketConfig.custom().setSoKeepAlive(true).build());
        connManager.setDefaultMaxPerRoute(20);
        connManager.setMaxTotal(40);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(2000)
                .setSocketTimeout(2000).build();

        return HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig);
    }

}
