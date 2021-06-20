package goct.query.demo.util;

import net.sf.json.JSONObject;
import org.apache.commons.beanutils.Converter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class EportalLogin {

        private static void httpURLGETCase() {
            String methodUrl = "http://127.0.0.1:8099/select_by_id?userid=1";
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String line = null;
            try {
                URL url = new URL(methodUrl);
                connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
                connection.setRequestMethod("GET");// 默认GET请求
                connection.connect();// 建立TCP连接
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
                    StringBuilder result = new StringBuilder();
                    // 循环读取流
                    while ((line = reader.readLine()) != null) {
                        result.append(line).append(System.getProperty("line.separator"));// "\n"
                    }
                    System.out.println(result.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                connection.disconnect();
            }
        }






    /***　　sendUrl    （远程请求的URL）
     * *　　param    （远程请求参数）
     * *　　JSONObject    （远程请求返回的JSON）
     * */
    public static boolean sendPostUrl(String url, String param){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            return Boolean.parseBoolean(result);
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
