package service.impl;


import beans.User;
import dao.Impl.UserDAOImpl;
import dao.UserDAO;
import service.LoginService;

public class LoginServiceImpl implements LoginService {

    private UserDAO userDAO = new UserDAOImpl();
    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param username,password
     * @return
     */
    public boolean isEmptyUser(String username) {
        return userDAO.isEmptyUserFindByUsername(username);
    }

    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param user
     * @return
     */
    public User LoginUser(User user) {
        return userDAO.LoginUser(user.getUsername(), user.getPassword());
    }


}
