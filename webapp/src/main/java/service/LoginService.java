package service;

import beans.User;

public interface LoginService {
    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param username,password
     * @return
     */
    boolean isEmptyUser(String username);//通过用户名判断是否为空
    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param user
     * @return
     */
    User LoginUser(User user);//通过用户名密码注册
}
