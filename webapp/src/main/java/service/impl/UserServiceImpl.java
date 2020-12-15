package service.impl;


import beans.Article;
import beans.SetArt;
import beans.User;
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
    String webLocation = "https://bbs.ambitiouscc.com";
    private UserDAO userDAO = new UserDAOImpl();
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

    /**
     * 注册功能完成
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        //设置账号创建时间
        user.setCreate_user_time(new Date());
        userDAO.addUser(user);
        String email = user.getEmail();
        System.out.println(email+"这个是获取到的邮箱");
        //邮箱！！！！！
        String content="<a href='"+webLocation+"/user/activeAccount?code="+user.getCode()+"'>点击激活您在论坛的账号</a>";
        return MailUtils.sendMail(user.getUsername(),email,content,"测试邮件");
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
}
