package com.hezaijin.advance.utils;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class HttpUtils {

    private static final int TIMEOUT_IN_MILLIONS = 5000;

    public interface CallBack {
        void onRequestComplete(HttpResponseObject result);
    }


    public static class HttpResponseObject {
        public boolean isSucceed = false;
        public int code;
        public String content;
    }

    /**
     * @param urlStr
     * @param callBack
     */
    public static void doGetAsyn(final String urlStr, final CallBack callBack) {
        new Thread() {
            public void run() {
                try {
                    HttpResponseObject result = doGet(urlStr);
                    if (callBack != null) {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    /**
     * @param urlStr
     * @param postParameters
     * @param callBack
     * @throws Exception
     */
    public static void doPostAsyn(final String urlStr,final List<NameValuePair> postParameters,
                                  final CallBack callBack)  {
        new Thread() {
            public void run() {
                try {
                    HttpResponseObject result = doPost(urlStr, postParameters);
                    if (callBack != null) {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void doPostAsyn(final String urlStr,final String content,
                                  final CallBack callBack)  {
        new Thread() {
            public void run() {
                try {
                    HttpResponseObject result = postHttpContent(urlStr, content);
                    if (callBack != null) {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static HttpResponseObject doGet(String urlStr) {
        URL url = null;
        HttpResponseObject object = new HttpResponseObject();
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            object.code = conn.getResponseCode();
            if (conn.getResponseCode() == 200) {
                object.isSucceed = true;
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                object.content = baos.toString();
                return object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
            }
            conn.disconnect();
        }

        return object;

    }

    /**
     * @param url
     * @param postParameters
     * @return
     */
    public static HttpResponseObject doPost(String url, List<NameValuePair> postParameters) {
        HttpResponseObject result = new HttpResponseObject();
        BufferedReader reader = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                    postParameters, "utf-8");
            request.setEntity(formEntity);

            HttpResponse response = client.execute(request);
            result.code = response.getStatusLine().getStatusCode();
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer strBuffer = new StringBuffer("");
            String line;
            while ((line = reader.readLine()) != null) {
                strBuffer.append(line);
            }
            result.content = strBuffer.toString();
            result.isSucceed = true;
        } catch (Exception e) {
            e.printStackTrace();
            result.isSucceed = false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    reader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static HttpResponseObject postHttpContent(String strurl,String content){
        HttpResponseObject result = new HttpResponseObject();

        long ibegin = System.currentTimeMillis();
        try {

            URL url = new URL(strurl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Charset", "UTF-8");
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(content.getBytes("utf-8"));
            // 刷新、关闭
            out.flush();
            out.close();

            result.code=con.getResponseCode();
            InputStream is =null;
            if(con.getResponseCode()<400){
                is = con.getInputStream();
                result.isSucceed=true;
            }else{
                is = con.getErrorStream();
            }


            byte[] buffer = new byte[1024*16];

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int c = -1;
            while ((c=is.read(buffer))>0) {
                bos.write(buffer, 0, c);
            }
            con.disconnect();
            con = null;
            result.content=bos.toString("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
