package com.fairy.base.common.utils;

import com.alibaba.fastjson.JSON;
import com.fairy.base.common.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanglulu
 * @version 1.0
 * @date 2022/4/2 20:54
 */
@Slf4j
public class HttpUtil {


    public static String getResponseString(CloseableHttpResponse httpResponse) {
        try {
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get请求 header
     *
     * @param url
     * @param headermap
     * @return CloseableHttpResponse
     */
    public static CloseableHttpResponse HttpGetWithHead(String url, Map<String, String> headermap) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        for (Map.Entry<String, String> entry : headermap.entrySet()) {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpclient.execute(httpget);
            httpclient.close();
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        } finally {
            httpclient.close();
        }
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @param pairs
     * @return
     */
    public static CloseableHttpResponse HttpGetWithParams(String url, List<BasicNameValuePair> pairs) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            for (int i = 0; i < pairs.size(); i++) {
                uriBuilder.setParameters((NameValuePair) pairs.get(i));
            }
            HttpGet httpget = new HttpGet(uriBuilder.toString());
            //执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
            httpResponse = httpclient.execute(httpget);

            return httpResponse;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        } finally {
            httpclient.close();
        }
    }


    /**
     * 参数在map里
     *
     * @param url
     * @param paramsmap
     * @param headermap
     * @return
     */
    public static CloseableHttpResponse HttpPost(String url, HashMap<String, Object> paramsmap, HashMap<String, String> headermap) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        String body = JSON.toJSONString(paramsmap);
        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httppost.setEntity(entity);

        for (Map.Entry<String, String> entry : headermap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpclient.execute(httppost);
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        } finally {
            httpclient.close();
        }
    }

    /**
     * 直接请求数据是json数据
     *
     * @param url url
     * @param jsonObject 数据
     * @return CloseableHttpResponse
     */
    public static CloseableHttpResponse HttpPostDefault(String url, String jsonObject) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonObject, ContentType.APPLICATION_JSON);
        httppost.setEntity(entity);
        try {
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * put请求
     *
     * @param url
     * @param entityString
     * @param headerMap
     * @return
     */
    public static CloseableHttpResponse HttpPut(String url, String entityString, HashMap<String, String> headerMap) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpput.setEntity(new StringEntity(entityString));
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpput.addHeader(entry.getKey(), entry.getValue());
            }
            httpResponse = httpclient.execute(httpput);
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        }
    }

    /**
     * delete请求
     *
     * @param url
     * @return
     */
    public static CloseableHttpResponse HttpDelete(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpdel = new HttpDelete(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpclient.execute(httpdel);
        } catch (IOException e) {
            e.printStackTrace();
            throw ResultException.create(e.getMessage());
        } finally {
            httpclient.close();
        }
        return httpResponse;
    }
}
