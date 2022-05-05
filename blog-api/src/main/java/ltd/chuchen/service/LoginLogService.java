package ltd.chuchen.service;

import ltd.chuchen.entity.LoginLog;
import ltd.chuchen.model.vo.LoginLogView;

import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description TODO
 */
public interface LoginLogService {

    void saveLoginLog(LoginLog log);

    boolean deleteLoginLogById(Long id);

    List<LoginLogView> getLoginLogList();
}
