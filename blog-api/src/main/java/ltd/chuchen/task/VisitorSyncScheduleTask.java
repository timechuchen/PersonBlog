package ltd.chuchen.task;

import com.alibaba.fastjson.JSON;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.VisitLog;
import ltd.chuchen.mapper.ExceptionLogMapper;
import ltd.chuchen.mapper.VisitLogMapper;
import ltd.chuchen.service.ExceptionLogService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访客统计相关定时任务
 */
@Component
public class VisitorSyncScheduleTask {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private VisitLogMapper visitLogMapper;
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    /**
     * 1、拿到 redis 中的所有访问信息存储到数据库中，并且删除redis中的标识
     */

    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨一点执行一次
    public void syncVisitInfoToDatabase() {
        String redisKey = RedisKeyConstant.VISIT_LOG;
        Object o = redisUtil.lGet(redisKey,0,-1);
        if(o == null) {
            return;
        }
        List<VisitLog> visitLogs = JSON.parseArray(JSON.toJSONString(o), VisitLog.class);
        visitLogs.forEach((v)-> {
            visitLogMapper.insert(v);
        });
        //为了能够拿到准确的信息，这是要在数据库中拿，不能在 redis 中拿
        redisUtil.set(RedisKeyConstant.VISIT_LOG_LIST,visitLogs);
        redisUtil.del(redisKey);
    }

    /**
     * 删除数据库的 浏览日志信息
     */
    @Scheduled(cron = "0 0 1 ? * 5") //每周星期四凌晨1点执行一次
    public void delVisitLogInfo() {
        visitLogMapper.emptyVisitorLog();
    }

    /**
     * 清空数据库异常日志
     */
    @Scheduled(cron = "0 0 2 ? * 5") //每周星期四凌晨2点执行一次
    public void emptyExceptionLog() {
        exceptionLogMapper.emptyExceptionLog();
    }
}
