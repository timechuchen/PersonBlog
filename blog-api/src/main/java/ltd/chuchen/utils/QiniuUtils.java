package ltd.chuchen.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author chuchen
 * @Date 2022/5/15
 * @Description 七牛云工具类
 */
@Component
public class QiniuUtils {
    public static  final String url = "http://rbx53i4an.hd-bkt.clouddn.com/";  //七牛云图片服务器域名（有效1个月）

    private static final  String accessKey = "Dr4xvQhLvxyLytM5fAb8hmSiw8hFs1e8B-m8vQHt";      //类似账号，在七牛云个人空间---密钥管理获取
    private static final   String accessSecretKey = "cjd88enmXFmp6goS4Z1qLR6qYREkwslqIARQnnr7";   //类似密码

    public boolean upload(MultipartFile file, String fileName){

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadong());  //根据自己的对象空间的地址选（华为）
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传 (个人存储空间名字)
        String bucket = "timechuchen";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 获取网络文件的文件流
     * @param urlPath 网络 url
     * @return 输入流
     */
    public static InputStream getInputStream(String urlPath) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urlPath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 将流文件读取到页面
     * @param resp 响应对象
     * @param inputStream 输入流
     */
    public static void writeFile(HttpServletResponse resp, InputStream inputStream) {
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
