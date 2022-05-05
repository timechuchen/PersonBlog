package ltd.chuchen.service;

import ltd.chuchen.entity.Admin;
import ltd.chuchen.model.vo.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chuchen
 * @Date 2022/3/20
 * @Description TODO
 */
public interface AdminService {

    Admin findAdminByUsernameAndPassword(String username, String password);

    Admin findAdminById(Long id);

    Result login(Admin admin, HttpServletRequest request);

    Result logout();
}
