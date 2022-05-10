package ltd.chuchen.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class FilesUtil {


    //获取端口名
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
        String fileName = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\" + local +"\\" + phone + postfix;
        try {
            FileUtil.writeBytes(file.getBytes(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return "文件过大,最大只能上传 3M 照片";
        }
        return ip+":"+ port + "/api/util/files/" +local + "/" +  phone;
    }

    /**
     * 文件的获取，可以理解为下载
     * @param response 请求信息
     */
    public void getFiles(String file,HttpServletResponse response,String local) {
        OutputStream os;
        String basePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\"+local+"\\";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        //找到跟参数一致的文件
        String fileName = fileNames.stream().filter(name -> name.contains(file)).findAny().orElse("");
        try {
            if(StrUtil.isNotEmpty(fileName))  {
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath+fileName); //通过文件的路径读取文件字节流
                os = response.getOutputStream(); //通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //博客图片的上传
}
