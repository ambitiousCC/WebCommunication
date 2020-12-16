package service;

import beans.Article;
import beans.SetArt;
import beans.User;

import java.util.List;

public interface UserService {

    /**
     * 传入别人的user_id
     * @param user_id 用户id
     * @return 用户对象
     */
    User findOtherUser(Long user_id);

    /**
     * 注销用户
     * @param user_id 用户对象
     * @return 判断是否成功
     */
    boolean removeUser(Long user_id);

    /**
     * 修改密码
     * @param user 用户
     * @return
     */
    boolean changePassword(User user);

    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param user
     * @return
     */
    boolean FilterUser(User user);//过滤器过滤用户
    /**
     * 注册功能完成
     * @param user
     * @param weblocation 主机位置
     * @return
     */
    boolean regist(User user, String weblocation);
    /**
     * 判断用户名是否已经存在
     * @param username
     * @return 如果存在就返回true，否则就是false
     */
    boolean isEmptyUserFindByUsername(String username);
    /**
     * 找邮箱
     * @param email
     * @return 如果存在就返回true，否则就是false
     */
    boolean isEmptyUserFindByEmail(String email);
    /**
     * 激活用户
     * @param code
     * @return 如果是true代表激活成功
     */
    boolean activeAccount(String code);

    /**
     * 保存用户头像
     * @param url
     * @param user_id
     * @return
     */
    boolean saveUserImg(String url,Long user_id);

    /**
     * 修改资料
     * @param user
     * @return
     */
    boolean updateUserProfile(User user);

    /**
     * 查询用户所有文章
     * @param user_id
     * @return
     */
    List<Article> findAllUserArts(Long user_id);
}
