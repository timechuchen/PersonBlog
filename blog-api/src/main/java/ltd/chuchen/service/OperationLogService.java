package ltd.chuchen.service;

import ltd.chuchen.entity.OperationLog;
import ltd.chuchen.model.vo.OperationLogView;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description TODO
 */
public interface OperationLogService {

    boolean saveOperationLog(OperationLog operationLog);

    boolean deleteOperationLogById(Long id);

    List<OperationLogView> getOperationLogList();
}
