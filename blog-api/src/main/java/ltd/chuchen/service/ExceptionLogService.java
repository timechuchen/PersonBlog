package ltd.chuchen.service;

import ltd.chuchen.entity.ExceptionLog;
import ltd.chuchen.model.vo.ExceptionLogView;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 异常服务接口
 */
public interface ExceptionLogService {

    boolean saveExceptionLog(ExceptionLog log);

    boolean emptyExceptionLog();

    List<ExceptionLogView> getExceptionLogList();

    boolean deleteExceptionLogById(Long id);
}
