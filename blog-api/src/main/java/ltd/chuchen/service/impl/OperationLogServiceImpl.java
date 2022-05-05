package ltd.chuchen.service.impl;

import ltd.chuchen.entity.OperationLog;
import ltd.chuchen.mapper.OperationLogMapper;
import ltd.chuchen.model.vo.OperationLogView;
import ltd.chuchen.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description AOP记录操作日志
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public boolean saveOperationLog(OperationLog operationLog) {
        int insert = operationLogMapper.insert(operationLog);
        return insert == 1;
    }

    @Override
    public boolean deleteOperationLogById(Long id) {
        int i = operationLogMapper.deleteById(id);
        return i == 1;
    }

    @Override
    public List<OperationLogView> getOperationLogList() {
        List<OperationLog> operationLogs = operationLogMapper.selectList(null);
        List<OperationLogView> result = new LinkedList<>();
        operationLogs.forEach((o)->{
            result.add(new OperationLogView()
            .setId(o.getId())
            .setUsername(o.getUsername())
            .setMethod(o.getMethod())
            .setContent(o.getDescription())
            .setTimes(o.getTimes())
            .setCreateTime(o.getCreateTime())
            .setUri(o.getUri()));
        });
        return result;
    }
}
