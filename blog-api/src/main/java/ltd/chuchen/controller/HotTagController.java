package ltd.chuchen.controller;

import ltd.chuchen.model.dto.HotTagInfo;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HotTagController {

    @Autowired
    private HotSpotService hotSpotServe;

    @ResponseBody
    @GetMapping("/loadHotTag")
    public Result loadHotTag() {
        List<HotTagInfo> hotTageInfo = hotSpotServe.getHotTageInfo();
        if(hotTageInfo.size() != 0){
            return Result.ok("数据加载成功",hotTageInfo);
        }else {
            return Result.error("数据加载失败!");
        }
    }
}
