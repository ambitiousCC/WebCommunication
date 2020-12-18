package service.impl;


import beans.Article;
import beans.SetArt;
import beans.User;
import beans.Visitor;
import dao.Impl.UserDAOImpl;
import dao.UserDAO;
import service.UserService;
import web.utils.MailUtils;
import web.utils.UuidUtil;

import java.util.Date;
import java.util.List;

/**
 * 对用户信息的增删改查
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    MailUtils mailUtils = new MailUtils();
    /**
     * 传入别人的user_id
     * @param user_id
     * @return
     */
    @Override
    public User findOtherUser(Long user_id) {
        return userDAO.findUser(user_id);
    }
    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param user
     * @return
     */
    @Override
    public boolean FilterUser(User user) {
        return userDAO.FilterUser(user);
    }

    @Override
    public boolean removeUser(Long user_id) {
        return userDAO.removeUser(user_id);
    }

    @Override
    public boolean checkPassword(User user) {
        user.setLast_login(new Date());
        return userDAO.checkPassword(user);
    }

    @Override
    public boolean changePassword(User user) {
        //在这里修改右键
        return userDAO.changePassword(user);
    }

    /**
     * 注册功能完成
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user,String webLocation) {
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        //设置账号创建时间
        user.setCreate_user_time(new Date());
        userDAO.addUser(user);
        //邮箱！！！！！
        String url = webLocation+"/user/activeAccount?code="+user.getCode();
        return mailUtils.sendMail(mailUtils.mailOfActive(user,url),user.getEmail(),"请激活账号您在编程爱好者交流平台的账号");
    }
    /**
     * 传入注册名称，判断是否已经存在用户
     * @param name
     * @return 如果存在就返回true，否则就是false
     */
    @Override
    public boolean isEmptyUserFindByUsername(String name) {
        return userDAO.isEmptyUserFindByUsername(name);
    }
    @Override
    public boolean isEmptyUserFindByEmail(String email) {
        return userDAO.isEmptyUserFindByEmail(email);
    }

    @Override
    public boolean activeAccount(String code) {
        //查询code
        User user = userDAO.isEmptyUserFindByCode(code);
        if (null != user) {
            //调用dao中的方法激活状态
            return userDAO.updateStatus(user);
        } else {
            return false;
        }
    }

    /**
     * 保存用户头像
     * @param url
     * @param user_id
     */
    public boolean saveUserImg(String url,Long user_id) {
        return userDAO.saveUserImg(url,user_id);
    }

    /**
     * 修改资料
     * @param user
     * @return
     */
    public boolean updateUserProfile(User user) {
        return userDAO.updateUserProfile(user);
    }

    @Override
    public List<Article> findAllUserArts(Long user_id) {
        return userDAO.findAllUserArts(user_id);
    }

    @Override
    public String findUserCodeByEmail(String email) {
        return userDAO.findUserCodeByEmail(email);
    }

    @Override
    public boolean sendCodeEmail(String email, Visitor visitor, String webLocation) {
        //在这里发送邮件处理
        String url = webLocation + "/sendEmail/resolve";
        return mailUtils.sendMail(mailUtils.mailOfPassword(url,visitor),email,"重置密码");
    }
}
