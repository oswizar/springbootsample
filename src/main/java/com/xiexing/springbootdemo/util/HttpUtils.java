package com.xiexing.springbootdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 发送Http请求
     *
     * @param url   请求地址
     * @param parms 请求参数（map格式）
     * @return
     * @throws Exception
     */
    public static String requestForm(String url, Map<String, String> parms) throws Exception {
        if (parms.isEmpty()) {
            return "参数不能为空！";
        }
        String result = "";
        StringBuilder postParms = new StringBuilder();
        int postItemTotal = parms.keySet().size();
        int itemp = 0;
        for (String key : parms.keySet()) {
            postParms.append(key).append("=").append(parms.get(key));
            itemp++;
            if (itemp < postItemTotal) {
                postParms.append("&");
            }
        }
        try {
            result = senPost(url, postParms.toString());
        } catch (Exception e) {
            throw e;
        }
        return result;

    }

    /**
     * @param url
     * @param param
     * @return String
     * @Description(功能描述) : 发送Post请求（返回字符串信息）
     */
    public static String senPostOne(String url, String param) {
        PrintWriter writer = null;
        URL sendUrl;
        BufferedReader in = null;
        String result = "";
        try {
            sendUrl = new URL(url);
            //打开连接
            URLConnection connect = sendUrl.openConnection();
            //设置请求属性
            connect.setRequestProperty("accept", "*/*");
            connect.setRequestProperty("connection", "Keep-Alive");
            connect.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            // 发送POST请求必须设置如下两行
            connect.setDoOutput(true);
            connect.setDoInput(true);
            //设置连接超时，读取超时
            connect.setConnectTimeout(1000 * 6000);
            connect.setReadTimeout(1000 * 6000);
            //创建输出流(UTF-8)
            writer = new PrintWriter(new OutputStreamWriter(connect.getOutputStream(), "UTF-8"));
            writer.print(param);
            writer.flush();
            //定义BufferedReader获取Url响应信息
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("post请求出现错误！", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("post请求出现错误！", e);
            }
        }
        return result;
    }

    /**
     * @param url
     * @param param
     * @return String
     * @Description(功能描述) : 发送Post请求（返回字符串信息）
     */
    public static String senPost(String url, String param) {
        PrintWriter writer = null;
        URL sendUrl;
        BufferedReader in = null;
        String result = "";
        try {
            sendUrl = new URL(url);
            //打开连接
            URLConnection connect = sendUrl.openConnection();
            //设置请求属性
            connect.setRequestProperty("accept", "*/*");
            connect.setRequestProperty("connection", "Keep-Alive");
            connect.setRequestProperty("content-type", "application/json");
            // 发送POST请求必须设置如下两行
            connect.setDoOutput(true);
            connect.setDoInput(true);
            //设置连接超时，读取超时
            connect.setConnectTimeout(1000 * 6000);
            connect.setReadTimeout(1000 * 6000);
            //创建输出流(UTF-8)
            writer = new PrintWriter(new OutputStreamWriter(connect.getOutputStream(), "UTF-8"));
            writer.print(param);
            writer.flush();
            //定义BufferedReader获取Url响应信息
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("post请求出现错误！", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("post请求出现错误！", e);
            }
        }
        return result;
    }


    public static String sendPost(String url, String param) {
        PrintWriter writer = null;
        URL sendUrl;
        BufferedReader in = null;
        String result = "";
        try {
            sendUrl = new URL(url);
            //打开连接
            URLConnection connect = sendUrl.openConnection();
            //设置请求属性
            connect.setRequestProperty("accept", "*/*");
            connect.setRequestProperty("connection", "Keep-Alive");
            connect.setRequestProperty("content-type", "application/json");//aplication/json  aplication/xml
            // 发送POST请求必须设置如下两行
            connect.setDoOutput(true);
            connect.setDoInput(true);
            //设置连接超时，读取超时
            connect.setConnectTimeout(1000 * 900);
            connect.setReadTimeout(1000 * 900);
            //创建输出流(UTF-8)
            writer = new PrintWriter(new OutputStreamWriter(connect.getOutputStream(), "UTF-8"));
            writer.print(param);
            writer.flush();
            //定义BufferedReader获取Url响应信息
            in = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("post请求出现错误！", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("post请求出现错误！", e);
            }
        }
        return result;
    }


    public static String getBodyMessage(HttpServletRequest request) {
        BufferedReader reader = null;
        String line = "";
        String xmlString = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            StringBuffer inputString = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            xmlString = inputString.toString();
            reader.close();

        } catch (IOException e) {

        }
        return xmlString;
    }

    public static String httpURLConnection(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(1000 * 60);
            conn.setReadTimeout(1000 * 60);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String postHtml(String url, String postData) {
        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postData);
            out.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            StringBuffer response = new StringBuffer();

            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String sendPostHttl(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(1000 * 100);
            conn.setReadTimeout(1000 * 100);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String senPostParmaStr(String url, String param) {
        PrintWriter writer = null;
        URL send_url;
        BufferedReader in = null;
        String result = "";
        try {
            send_url = new URL(url);
            //打开连接
            URLConnection connect = send_url.openConnection();
            //设置请求属性
            connect.setRequestProperty("accept", "*/*");
            connect.setRequestProperty("connection", "Keep-Alive");
            // 发送POST请求必须设置如下两行
            connect.setDoOutput(true);
            connect.setDoInput(true);
            //设置连接超时，读取超时
            connect.setConnectTimeout(1000 * 60);
            connect.setReadTimeout(1000 * 60);
            //创建输出流(UTF-8)
            writer = new PrintWriter(new OutputStreamWriter(connect.getOutputStream(), "UTF-8"));
            writer.print(param);
            writer.flush();
            //定义BufferedReader获取Url响应信息
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("post请求出现错误！", e);
//	       throw new BusinessException("发送POST请求出现异常！");
//	       throw new BusinessException(RspCodeEnum.ERR99999);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("post请求出现错误！", e);
//	              throw new BusinessException("发送POST请求出现异常！");
//	              throw new BusinessException(RspCodeEnum.ERR99999);
            }
        }
        return result;
    }

    public static String sendGetTemp(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送GET请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            URL connURL = new URL(full_url);
            // 打开URL连接(建立了一个与服务器的tcp连接,并没有实际发送http请求！)
            URLConnection urlConnection = connURL.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
            // 设置通用请求属性(如果已存在具有该关键字的属性，则用新值改写其值。)
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接(远程对象变为可用。远程对象的头字段和内容变为可访问)
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
