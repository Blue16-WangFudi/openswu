package cn.blue16.provider.zflocal.nativeInterface;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NativeImp {
    private CloseableHttpClient httpClient;
    private String stuNum;
    private final Path imageStoragePath = Paths.get("src/main/resources/public/images").toAbsolutePath().normalize();


    private static final String LOGIN_URL = "http://jw.swu.edu.cn/sso/zllogin";
    private static final String POST_URL = "https://uaaap.swu.edu.cn/cas/login?service=http%3A%2F%2Fjw.swu.edu.cn%2Fsso%2Fzllogin";
    public void closeConnection(){
        try {
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean login(String username, String password) {
        CookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        try {
            // 发送GET请求获取lt参数
            HttpGet get = new HttpGet(LOGIN_URL);
            setNormalHeaders(get);
            CloseableHttpResponse response = client.execute(get, context);
            String html = EntityUtils.toString(response.getEntity());
            String lt = extractLt(html);
            // 到这里是没问题的

            // 发送POST请求进行登录
            HttpPost post = new HttpPost(POST_URL);
            setPostHeaders(post);
            HttpEntity entity = buildLoginParams(username, password, lt);

            post.setEntity(entity);
            response = client.execute(post, context);

            // 处理登录响应
            Header header =  response.getFirstHeader("Location");
            if (header == null) {
                return false;
            }
            String location =header.getValue();
            // 保存httpClient
            this.httpClient=client;

            // 跳转到指定页面
            HttpGet redirect = new HttpGet(location);
            setNextHeaders(redirect);
            response = client.execute(redirect, context);

            // 提取用户信息
            String num = extractNum(response);
            if (num != null) {
                this.stuNum =num;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 设置请求头的方法
    private void setNormalHeaders(HttpGet get) {
        String[] headers = {
                "Connection", "keep-alive",
                "Upgrade-Insecure-Requests", "1",
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36",
                "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
                "Accept-Encoding", "gzip,deflate",
                "Accept-Language", "zh-CN,zh;q=0.9"
        };

        for (int i = 0; i < headers.length; i += 2) {
            get.setHeader(headers[i], headers[i + 1]);
        }
    }

    private void setPostHeaders(HttpPost post) {
        String[] headers = {
                "Connection", "keep-alive",
                "Cache-Control", "max-age=0",
                "sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"",
                "sec-ch-ua-mobile", "?0",
                "sec-ch-ua-platform", "\"Windows\"",
                "Upgrade-Insecure-Requests", "1",
                "Origin", "https://uaaap.swu.edu.cn",
                "Content-Type", "application/x-www-form-urlencoded",
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36",
                "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
                "Sec-Fetch-Site", "same-origin",
                "Sec-Fetch-Mode", "navigate",
                "Sec-Fetch-User", "?1",
                "Sec-Fetch-Dest", "document",
                "Referer", "https://uaaap.swu.edu.cn/cas/login?service=http%3A%2F%2Fjw.swu.edu.cn%2Fsso%2Fzllogin",
                "Accept-Encoding", "gzip, deflate, br",
                "Accept-Language", "zh-CN,zh;q=0.9"
        };

        for (int i = 0; i < headers.length; i += 2) {
            post.setHeader(headers[i], headers[i + 1]);
        }
    }

    private void setNextHeaders(HttpGet get) {
        String[] headers = {
                "Host", "jw.swu.edu.cn",
                "Connection", "keep-alive",
                "Cache-Control", "max-age=0",
                "Upgrade-Insecure-Requests", "1",
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36",
                "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.9",
                "Accept-Encoding", "gzip, deflate",
                "Accept-Language", "zh-CN,zh;q=0.9"
        };

        for (int i = 0; i < headers.length; i += 2) {
            get.setHeader(headers[i], headers[i + 1]);
        }
    }

    // 提取lt参数的方法
    private String extractLt(String html) {
        Pattern pattern = Pattern.compile("<input type=\"hidden\" name=\"lt\" value=\"(.*?)\"/>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // 构建登录请求参数的方法
    private HttpEntity buildLoginParams(String username, String password, String lt) {
        // 构建表单参数
        List<BasicNameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", username)); // 替换为实际的用户名
        urlParameters.add(new BasicNameValuePair("password", password)); // 替换为实际的密码
        urlParameters.add(new BasicNameValuePair("lt", lt)); // 替换为实际的lt值
        urlParameters.add(new BasicNameValuePair("execution", "e1s1"));
        urlParameters.add(new BasicNameValuePair("_eventId", "submit"));
        urlParameters.add(new BasicNameValuePair("isQrSubmit", "false"));
        urlParameters.add(new BasicNameValuePair("qrValue", ""));
        // 将表单参数设置为请求实体
        try {
            return new UrlEncodedFormEntity(urlParameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 提取学号信息的方法
    public String extractNum(CloseableHttpResponse response) {
        try {
            // 获取响应体的内容
            String responseBody = EntityUtils.toString(response.getEntity());

            // 定义正则表达式来匹配隐藏字段
            Pattern pattern = Pattern.compile("<input type=\"hidden\" id=\"sessionUserKey\" value=\"(.*?)\" />");

            // 创建Matcher对象来查找匹配
            Matcher matcher = pattern.matcher(responseBody);

            // 查找匹配项
            if (matcher.find()) {
                // 返回匹配到的值
                return matcher.group(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 如果没有找到匹配项，则返回null
        return null;
    }



    public String[] getInfo() {
        CloseableHttpClient session=this.httpClient;
        String url = "http://jw.swu.edu.cn/jwglxt/xtgl/index_cxYhxxIndex.html?xt=jw&localeKey=zh_CN&_=" + String.valueOf(System.currentTimeMillis()) + "&gnmkdm=index&su=" + this.stuNum;
        HttpGet get = new HttpGet(url);
        setInfoHeaders(get);

        try (CloseableHttpResponse response = session.execute(get)) {
            String html = EntityUtils.toString(response.getEntity());
            String classInfo = extractClassInfo(html);
            String[] temp1=classInfo.split(" ");
            String name = extractName(html);
            String[] temp2 = name.split("&nbsp;&nbsp;");
            return new String[]{temp1[0], temp1[1],temp2[0],temp2[1]};
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void getPic(String ID) {

        // 构造获取照片的URL
        String photoUrl = "http://jw.swu.edu.cn/jwglxt/xtgl/photo_cxXszp.html?xh_id=" + this.stuNum + "&zplx=rxhzp";

        // 发送GET请求来获取照片数据
        HttpGet photoGet = new HttpGet(photoUrl);
        setInfoHeaders(photoGet);

        try (CloseableHttpResponse photoResponse = httpClient.execute(photoGet)) {
            HttpEntity photoEntity = photoResponse.getEntity();
            if (photoEntity != null) {
                // 获取照片的二进制数据
                InputStream photoInputStream = photoEntity.getContent();

                // 保存照片到指定路径
                String filePath = imageStoragePath +"\\"+ID+".png";
                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    // 读取照片数据并写入文件
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = photoInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 设置请求头的方法
    private void setInfoHeaders(HttpGet get) {
        String[] headers = {
                "Host", "jw.swu.edu.cn",
                "Connection", "keep-alive",
                "Accept", "text/html, */*; q=0.01",
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36",
                "X-Requested-With", "XMLHttpRequest",
                "Accept-Encoding", "gzip, deflate",
                "Accept-Language", "zh-CN,zh;q=0.9"
        };

        for (int i = 0; i < headers.length; i += 2) {
            get.setHeader(headers[i], headers[i + 1]);
        }
    }

    // 提取班级信息的正则表达式方法
    private String extractClassInfo(String html) {
        Pattern pattern = Pattern.compile("<p>(.*?)</p>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // 提取姓名信息的正则表达式方法
    private String extractName(String html) {
        Pattern pattern = Pattern.compile("<h4 class=\"media-heading\">(.*?)</h4>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}


