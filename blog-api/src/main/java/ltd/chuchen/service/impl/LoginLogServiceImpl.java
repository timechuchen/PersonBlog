package ltd.chuchen.service.impl;

import ltd.chuchen.entity.LoginLog;
import ltd.chuchen.mapper.LoginLogMapper;
import ltd.chuchen.model.vo.LoginLogView;
import ltd.chuchen.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author chuchen
 * @Date 2022/5/5
 * @Description 登陆日志服务层
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void saveLoginLog(LoginLog log) {
        loginLogMapper.insert(log);
    }

    @Override
    public boolean deleteLoginLogById(Long id) {
        int i = loginLogMapper.deleteById(id);
        return i == 1;
    }

    @Override
    public List<LoginLogView> getLoginLogList() {
        List<LoginLog> loginLogs = loginLogMapper.selectList(null);
        List<LoginLogView> result = new LinkedList<>();
        loginLogs.forEach((o)->{
            result.add(new LoginLogView()
            .setId(o.getId())
            .setUsername(o.getUsername())
            .setStatus(o.getStatus())
            .setCreateTime(o.getCreateTime())
            .setDescription(o.getDescription()));
        });
        return result;
    }


}
