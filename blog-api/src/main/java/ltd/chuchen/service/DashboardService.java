package ltd.chuchen.service;

import java.util.List;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/5/16
 * @Description 后台仪表数据服务层
 */
public interface DashboardService {

    int countVisitLogByToday();

    int getBlogCount();

    int getCommentCount();

    Map<String, List> getCategoryBlogCountMap();

    Map<String, List> getTagBlogCountMap();

    Map<String, List> getVisitRecordMap();
}
