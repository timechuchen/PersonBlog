package ltd.chuchen.controller.util;

import ltd.chuchen.model.vo.Result;
import ltd.chuchen.utils.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/api/util/files")
public class FileController {

    final static String PIC_PATH = "files/blog/"; //图片存放的相对于项目的相对位置

    @Autowired
    private FilesUtil filesUtil;

    /**
     * 相当于头像上传接口
     * @param file 要上上传的文件
     * @return Result
     */
    @PostMapping("/head/{phone}")
    @ResponseBody
    public Result uploadHead(@RequestBody MultipartFile file, @PathVariable String phone) {
        String imgUrl = filesUtil.uploadHead(file, phone,"head");
        return Result.ok("上传成功",imgUrl);
    }

    /**
     * 相当于头像上传接口
     * @param file 要上上传的文件
     * @return Result
     */
    @PostMapping("/blogImg/{blogId}")
    @ResponseBody
    public Result uploadBlogImg(@RequestBody MultipartFile file,  @PathVariable String blogId) {
        String imgUrl = filesUtil.uploadHead(file, blogId,"blog");
        return Result.ok("上传成功",imgUrl);
    }

    /**
     *上传图片
     */
    @PostMapping("/upload")
    public Result uploadPic(MultipartHttpServletRequest multiRequest, HttpServletRequest request){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //生成日期格式
        String datePrefix = dateFormat.format(new Date()); //生成当前日期作为前缀
        String savePath = "src/main/resources/" + PIC_PATH; // 存储路径

        File folder = new File(savePath+datePrefix); //生成带当前日期的文件路径

        if(!folder.isDirectory()){
            folder.mkdirs();
        }

        String randomName = Objects.requireNonNull(multiRequest.getFile("image")).getOriginalFilename(); //获取图片名
        //生成随机数确保唯一性，并加上图片后缀
        assert randomName != null;
        String saveName = UUID.randomUUID() + randomName.substring(randomName.lastIndexOf("."),randomName.length());
        String absolutePath = folder.getAbsolutePath(); //转换成绝对路径

        try {
            File fileToSave = new File(absolutePath + File.separator + saveName);
            Objects.requireNonNull(multiRequest.getFile("image")).transferTo(fileToSave); //图片存储到服务端
            String returnPath = request.getScheme() + "://"
                    + request.getServerName()+":"+request.getServerPort()
                    + "/img/" + datePrefix +"/"+ saveName;

            return Result.ok("上传成功",returnPath);

        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("上传失败");
    }

    /**
     * 相当于下载接口
     * @param flag 文件标识
     * @param response 请求头
     */
    @GetMapping("/{local}/{flag}")
    @ResponseBody
    public void getFiles(@PathVariable String flag,@PathVariable String local, HttpServletResponse response) {
        filesUtil.getFiles(flag,response,local);
    }
}
