package ltd.chuchen.controller.admin;

import ltd.chuchen.entity.HotSpot;
import ltd.chuchen.model.dto.HotTagInfo;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.HotSpotServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 热点标签设置后台管理
 * @Author: Chuchen
 * @Date: 2020-08-09
 */
@Controller
@RequestMapping("/api/admin")
public class PageController {

    @Autowired
    private HotSpotServe hotSpotServe;

    /**
     * 更新热点信息
     * @param map 前端传回的 JSON 对象
     * @return Result
     */
    @PostMapping("/page/updateSite")
    @ResponseBody
    public Result updateSite(@RequestBody Map<String, ArrayList<HotTagInfo>> map) {
        ArrayList<HotTagInfo> hotTag = map.get("hotTag");
        System.out.println(hotTag);
        System.out.println(hotTag.getClass());
        if(hotSpotServe.updateHotSpot(hotTag)){
            return Result.ok("更新成功");
        }else {
            return Result.error("更新失败");
        }
    }

    /**
     * 查找所有热点信息
     * @return Result
     */
    @PostMapping("/page/getAllSite")
    @ResponseBody
    public Result getAllSite() {
        List<HotSpot> allHotSpot = hotSpotServe.getAllHotSpot();
        if(allHotSpot.size() != 0 ){
            return Result.ok("查找成功", allHotSpot);
        }else {
            return Result.error("查找失败");
        }
    }
}
