package ltd.chuchen.service;

import ltd.chuchen.entity.User;

public interface UserService {
    /**
     * 是否登陆成功
     * @param email 邮箱
     * @param password 密码
     * @return 是否登陆成功
     */
    boolean login(String email,String password);

    /**
     * 注册信息
     * @param username 用户名
     * @param password 密码
     * @param email 电话
     * @return 0：登陆成功 1：已有用户名 2：其他原因
     */
    int sigin(String username,String password,String email,String code,String imageUrl);

    /**
     * 查找用户信息
     * @param email 邮箱
     * @return 用户信息
     */
    User findUserByEmail(String email);
}
