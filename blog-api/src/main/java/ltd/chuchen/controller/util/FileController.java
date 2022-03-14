package ltd.chuchen.controller.util;

import ltd.chuchen.model.vo.Result;
import ltd.chuchen.utils.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/util/files")
public class FileController {

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
