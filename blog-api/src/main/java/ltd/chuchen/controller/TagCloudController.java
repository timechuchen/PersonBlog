package ltd.chuchen.controller;

import ltd.chuchen.model.vo.Result;
import ltd.chuchen.model.vo.TagCloudView;
import ltd.chuchen.service.TagCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/17
 * @Description 标签云控制层
 */
@Controller
@RequestMapping("/api")
public class TagCloudController {

    @Autowired
    private TagCloudService tagCloudService;

    @GetMapping("/tagClouds")
    @ResponseBody
    public Result getAllTagCloud() {
        List<TagCloudView> allTagCloud = tagCloudService.getAllTagCloud();
        return Result.ok("ok",allTagCloud);
    }
}
