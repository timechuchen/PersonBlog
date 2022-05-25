package ltd.chuchen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Component
public class FilesUtil {

    @Autowired
    private QiniuUtils qiniuUtils;

    @Value("${server.port}")
    private String port;
    private static final String ip = "http://localhost";

    /**
     * @param file 要上传的文件
     * @param phone 文件的标号
     */
    public String uploadHead(MultipartFile file, String phone,String local) {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String postfix = originalFilename.substring(originalFilename.length() - 4);
        String fileName =  "files/"+local +"/" + phone + postfix;
        boolean upload = qiniuUtils.upload(file, fileName);
        if(upload) {
            return ip+":"+ port + "/api/util/files/" +local + "/" +  phone + postfix;
        }else {
            return "上传失败";
        }
    }

    /**
     * 文件的获取，可以理解为下载
     * @param response 请求信息
     */
    public void getFiles(String file,HttpServletResponse response,String local) {
        String basePath = "http://rbx53i4an.hd-bkt.clouddn.com"+"/files/"+local+"/"+file;
        InputStream inputStream = QiniuUtils.getInputStream(basePath);
        QiniuUtils.writeFile(response,inputStream);
    }
}
