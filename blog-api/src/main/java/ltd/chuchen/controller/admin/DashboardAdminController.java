package ltd.chuchen.controller.admin;

import com.alibaba.fastjson.JSON;
import ltd.chuchen.annotation.VisitLogger;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.VisitLog;
import ltd.chuchen.enums.VisitBehavior;
import ltd.chuchen.model.vo.Result;
import ltd.chuchen.service.DashboardService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/5/16
 * @Description 后台管理仪表盘
 */
@Controller
@RequestMapping("/api/admin")
public class DashboardAdminController {

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private RedisUtil redisUtil;

    @VisitLogger(VisitBehavior.DASHBOARD)
    @GetMapping("/dashboard")
    @ResponseBody
    public Result dashboard() {
        Object o = redisUtil.lGet(RedisKeyConstant.VISIT_LOG,0,-1);
        List<VisitLog> visitLogs = JSON.parseArray(JSON.toJSONString(o), VisitLog.class);
        int todayPV = visitLogs.size();
        long todayUV = redisUtil.sGetSetSize(RedisKeyConstant.IDENTIFICATION_SET);
        int blogCount = dashboardService.getBlogCount();
        int commentCount = dashboardService.getCommentCount();
        Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();
        Map<String, List> tagBlogCountMap = dashboardService.getTagBlogCountMap();
        Map<String, List> visitRecordMap = dashboardService.getVisitRecordMap();
        Map<String, Object> map = new HashMap<>();
        map.put("pv", todayPV);
        map.put("uv", todayUV);
        map.put("blogCount", blogCount);
        map.put("commentCount", commentCount);
        map.put("category", categoryBlogCountMap);
        map.put("tag", tagBlogCountMap);
        map.put("visitRecord", visitRecordMap);
        return Result.ok("获取成功", map);
    }
}
