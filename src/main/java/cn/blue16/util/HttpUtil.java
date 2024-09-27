package cn.blue16.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    /**
     * 发送POST请求并解析响应为指定类型的对象(表单形式)
     *
     * @param url          请求的URL
     * @param parameters   请求的参数
     * @param responseType 响应对象的类型
     * @param <T>          响应对象的类型
     * @return 解析后的响应对象
     * @throws IOException 如果发生I/O错误
     */
    public static <T> T sendPostRequest_Form(String url, Map<String, String> parameters, Type responseType) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // 添加表单参数
        List<NameValuePair> urlParameters = new ArrayList<>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        // 将表单参数编码并添加到HttpPost对象
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            // 使用Gson解析JSON字符串
            Gson gson = new Gson();
            return gson.fromJson(result, responseType);
        }
    }
}