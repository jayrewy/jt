package com.jt.test;

import com.jt.util.ObjectMapperUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpClient {
    /**
     *   1，利用HttpClient访问百度服务器
     *   2，实现步骤：定义请求的网址-定义HttpClient工具api对象-定义请求的对象
     *      --发起请求获取返回值的结果-校验返回值-获取返回值结果数据\
     *   万能的方案
     */
    @Test
    public void testGet() throws IOException {
        String url = "http://www.baidu.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);//不能保证网络一定正确 抛异常

        //发起请求之后需要庞大返回值结果是否正确，一般条件下判断相应状态码信息为200
        //404 400 提交参数异常   406接收参数异常    500服务器异常  504请求超时
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == 200) {
            //说明请求正确 获取返回值的实体对象
            HttpEntity httpEntity = response.getEntity();
            //将远程服务器放的信息，转化为字符串，方便调用   1.json  2.html代码片段
            String result = EntityUtils.toString(httpEntity,"UTF-8");
            System.out.println(result);
        }
    }
}
