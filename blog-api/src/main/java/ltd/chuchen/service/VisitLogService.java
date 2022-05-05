package ltd.chuchen.service;

import ltd.chuchen.entity.VisitLog;
import ltd.chuchen.model.vo.VisitorLogView;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/4
 * @Description TODO
 */
public interface VisitLogService {

    @Async
    void saveVisitLog(VisitLog visitLog);

    boolean deleteVisitLogById(Long id);

    List<VisitorLogView> getVisitLogList();
}
