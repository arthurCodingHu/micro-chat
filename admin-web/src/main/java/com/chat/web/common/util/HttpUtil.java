package com.chat.web.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * auhtor:ranger
 * date:2017-06-09
 */
public class HttpUtil {

    public static Logger logger = Logger.getLogger(HttpUtil.class);

    public static String doPost(String url, Map<String, Object> paramsMap){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);

        String userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.0)";
        httpPost.setHeader("User-Agent",userAgent);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }
        String strResult = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            logger.info("httpPost ===**********===>>> " + EntityUtils.toString(httpPost.getEntity()));
            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(response.getEntity());
            }else{
                logger.info("Error Response: "+response.getStatusLine().toString());
            }
        } catch (Exception e) {
            logger.error("post failure :caused by-->",e);
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("post failure :IOException caused by-->",e);
                }
            }
        }
        return strResult;
    }


    public static String doGet(String url){
        CloseableHttpClient httpCilent2 = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet2 = new HttpGet(url);
        httpGet2.setConfig(requestConfig);
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpCilent2.execute(httpGet2);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
            }else {
                logger.info("Error Response: " + httpResponse.getStatusLine().toString());
            }
        } catch (IOException e) {
            logger.error("Error IOException1: " ,e);
        }finally {
            try {
                httpCilent2.close();
            } catch (IOException e) {
                logger.error("Error IOException2: " ,e);
            }
        }

        return  srtResult;
    }

    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPostJson(String url, String json){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
//         StringEntity s = new StringEntity(json);
            StringEntity s=new StringEntity(json,"utf-8");//解决中文乱码
            post.setEntity(s);
//         post.setHeader("Content-Type","application/json;charset=utf-8");
            s.setContentType("application/json;charset=utf-8");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    public static String doPostDubbox(String url, String raw){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        if(StringUtils.isBlank(raw)){
            raw="{}";
        }
        String strResult = "";
        try {
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(new StringEntity(raw));
            logger.info("httpPost ===**********===>>> " + EntityUtils.toString(httpPost.getEntity()));
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(response.getEntity());
            }else{
                logger.info("Error Response: "+response.getStatusLine().toString());
            }
        } catch (Exception e) {
            logger.error("post failure :caused by-->",e);
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("post failure :IOException caused by-->",e);
                }
            }
        }
        return strResult;
    }

    public static JSONObject doPostByJsonString(String url,JSONObject jsonObject, Map<String, String> headerMap) {
        PostMethod post = null;
        BufferedReader rd = null;
        try {
            HttpClient client = new HttpClient();
            post = new PostMethod(url);

            post.setRequestEntity(new StringRequestEntity(jsonObject.toString(), "json/html", "UTF-8"));
            //设置头部信息
            if (null != headerMap) {
                for(String key : headerMap.keySet()) {
                    post.setRequestHeader(key, headerMap.get(key));
                }
            }
            // 设置 Http 连接超时为5秒
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

            client.executeMethod(post);
            int status = post.getStatusCode();
            if (status == HttpStatus.SC_OK) {
                rd = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                if (result.length() > 0) {
//                    return result.toString();
                    return JSON.parseObject(result.toString());
                }
            }
        } catch (Exception e) {
            logger.error("http调用失败,url:" + url, e);
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return null;
    }

}
