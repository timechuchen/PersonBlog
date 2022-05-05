package ltd.chuchen.service.impl;

import ltd.chuchen.entity.ExceptionLog;
import ltd.chuchen.mapper.ExceptionLogMapper;
import ltd.chuchen.model.vo.ExceptionLogView;
import ltd.chuchen.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 异常日志服务层
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    public boolean saveExceptionLog(ExceptionLog log) {
        int insert = exceptionLogMapper.insert(log);
        return insert == 1;
    }

    public boolean emptyExceptionLog() {
        try {
            exceptionLogMapper.emptyExceptionLog();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ExceptionLogView> getExceptionLogList() {
        List<ExceptionLog> exceptionLogs = exceptionLogMapper.selectList(null);
        List<ExceptionLogView> result = new LinkedList<>();
        exceptionLogs.forEach((o)->{
            result.add(new ExceptionLogView()
            .setId(o.getId())
            .setCreateTime(o.getCreateTime())
            .setDescription(o.getDescription())
            .setUri(o.getUri())
            .setError(o.getError())
            .setMethod(o.getMethod()));
        });
        return result;
    }

    @Override
    public boolean deleteExceptionLogById(Long id) {
        int i = exceptionLogMapper.deleteById(id);
        return i == 1;
    }
}
