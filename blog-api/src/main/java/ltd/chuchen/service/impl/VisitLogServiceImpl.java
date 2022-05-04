package ltd.chuchen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ltd.chuchen.constants.RedisKeyConstant;
import ltd.chuchen.entity.VisitLog;
import ltd.chuchen.mapper.VisitLogMapper;
import ltd.chuchen.model.vo.VisitorLogView;
import ltd.chuchen.service.VisitLogService;
import ltd.chuchen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description 访问日志业务层实现
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    VisitLogMapper visitLogMapper;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 将每天的访问日志先保存到 redis 中，然后每晚上12点执行一次定时任务将其存到数据库汇总并且清空redis
     * 的数据
     * @param visitLog 日志信息
     */
    @Override
    public void saveVisitLog(VisitLog visitLog) {
        String redisKey = RedisKeyConstant.VISIT_LOG;
        //将访问日志信息保存到 redis 中
        redisUtil.lSet(redisKey,visitLog);
    }

    /**
     * 通过 id 删除访问日志
     * @param id 日志id
     */
    @Override
    public boolean deleteVisitLogById(Long id) {
        int i = visitLogMapper.deleteById(id);
        if(i == 1) {
            updateVisitLogListOfRedis();
            return true;
        }
        return false;
    }

    @Override
    public List<VisitorLogView> getVisitLogList() {
        String redisKey = RedisKeyConstant.VISIT_LOG_LIST;
        Object o = redisUtil.get(redisKey);
        if(o == null) {
            return updateVisitLogListOfRedis();
        }
        List<VisitorLogView> visitorLogViews = JSON.parseArray(JSON.toJSONString(o),VisitorLogView.class);

        if(visitorLogViews != null) {
            return visitorLogViews;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        return updateVisitLogListOfRedis();
    }

    protected List<VisitorLogView> updateVisitLogListOfRedis() {
        String redisKey = RedisKeyConstant.VISIT_LOG_LIST;
        QueryWrapper<VisitLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<VisitLog> visitLogs = visitLogMapper.selectList(queryWrapper);
        List<VisitorLogView> result = new LinkedList<>();
        visitLogs.forEach((visitLog)->{
            result.add(new VisitorLogView()
                    .setId(visitLog.getId())
                    .setUri(visitLog.getUri())
                    .setMethod(visitLog.getMethod())
                    .setBehavior(visitLog.getBehavior())
                    .setContent(visitLog.getContent())
                    .setCreateTime(visitLog.getCreateTime())
                    .setUuid(visitLog.getUuid())
                    .setTimes(visitLog.getTimes())
                    .setRemark(visitLog.getRemark()));
        });
        redisUtil.set(redisKey,result);
        return result;
    }
}
