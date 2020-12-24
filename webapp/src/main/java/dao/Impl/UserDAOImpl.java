package dao.Impl;

import beans.Article;
import beans.User;
import dao.UserDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import web.utils.JDBCUtils;
import web.utils.JDBCUtilsPlus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 对用户数据库进行操作的类
 */
public class UserDAOImpl implements UserDAO {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtilsPlus.getDataSource());

    /**
     * 注册时创建新的user
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        //注册之前首先查询是否有同名
        String sql = "INSERT INTO user(username,nickname,password,email,create_user_time,status,code) VALUES (?,?,?,?,?,?,?)";
        return template.update(sql,user.getUsername(),user.getNickname(),user.getPassword(),user.getEmail(),
                user.getCreate_user_time(),user.getStatus(),user.getCode()) > 0;
    }

    @Override
    public boolean removeUser(Long user_id) {
        String sql = "DELETE FROM user WHERE user_id=? ";
        return template.update(sql,user_id) > 0;
    }

    @Override
    public boolean removeUserByEmail(String email) {
        String sql = "DELETE FROM user WHERE email=? ";
        return template.update(sql,email) > 0;
    }

    @Override
    public boolean checkPassword(User user) {
        String sql = "SELECT user_id FROM user WHERE user_id=? AND password=? ";
        Long end = null;
        try {
            end = template.queryForObject(sql,Long.class,user.getUser_id(),user.getPassword());
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return end!=null;
    }

    @Override
    public boolean changePassword(User user) {
        String sql = "UPDATE user SET password=? WHERE user_id=? ";

        return template.update(sql, user.getPassword(), user.getUser_id())>0;
    }

    /**
     * 判断用户是否存在:
     * @param username
     * @return 如果存在就返回true，否则就是false
     */
    @Override
    public boolean isEmptyUserFindByUsername(String username) {
        System.out.println("开始查询用户");
        String sql = "SELECT user_id FROM user WHERE username=?";
        Long end = null;
        try {
            end = template.queryForObject(sql,Long.class,username);
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return end!=null;
    }
    /**
     * 判断用户是否存在:
     * @param email
     * @return 如果存在就返回true，否则就是false
     */
    @Override
    public boolean isEmptyUserFindByEmail(String email) {
        System.out.println("开始查询用户");
        String sql = "SELECT user_id FROM user WHERE email=?";
        Long end = null;
        try {
            end = template.queryForObject(sql,Long.class,email);
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return end!=null;
    }
    /**
     * 通过CODE查询对象
     * @param code
     * @return 返回user对象
     */
    @Override
    public User isEmptyUserFindByCode(String code) {
        //值得考虑：如果用了别人的激活码怎么办呢？当然前提是知道其他人的激活码
        String sql = "SELECT * FROM user WHERE code=? ";
        User user = null;
        try {
            user =  template.queryForObject(sql,new Object[]{code},new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return user;
    }
    /**
     * 通过id查对象
     * @param user_id
     * @return 返回user对象
     */
    @Override
    public User findUser(Long user_id) {
        String sql = "SELECT * FROM user WHERE user_id=? ";
        User user = null;
        try {
            user =  template.queryForObject(sql,new Object[]{user_id},new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return user;
    }
    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param username,password
     * @return
     */
    @Override
    public User LoginUser(String username,String password) {
        String sql = "SELECT * FROM user WHERE username=? AND password=? ";
        User user = null;
        try {
            user =  template.queryForObject(sql,new Object[]{username,password},new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return user;
    }

    private void setLoginTime(Long user_id) {
        String sql = "UPDATE user SET last_login=? WHERE user_id=? ";
        template.update(sql,new Timestamp(new Date().getTime()),user_id);
    }

    /**
     * 过滤器过滤
     * @param user
     * @return
     */
    @Override
    public boolean FilterUser(User user) {
        String sql = "SELECT create_user_time FROM user WHERE user_id=? ";
        Date end = null;
        try {
            end = template.queryForObject(sql,Date.class,user.getUser_id());
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        if(Objects.equals(end,user.getCreate_user_time()))
            return true;
        return false;
    }
    /**
     * 通过user中的id更新激活状态
     * @param user
     * @return
     */
    @Override
    public boolean updateStatus(User user) {
        String sql = "UPDATE user SET status=? WHERE user_id=?";

        return template.update(sql, "Y", user.getUser_id())>0;
    }

    /**
     * 保存用户头像
     * @param url
     * @param user_id
     * @return
     */
    public boolean saveUserImg(String url,Long user_id) {
        String sql = "UPDATE user SET user_ico=?,user_img=? WHERE user_id=?";

        return template.update(sql, url,url, user_id)>0;
    }

    /**
     * 修改资料
     * @param user
     * @return
     */
    public boolean updateUserProfile(User user) {
        String sql = "UPDATE user set ";
        //拼接语句
        StringBuilder sB = new StringBuilder(sql);

        List<java.io.Serializable> params = new ArrayList<java.io.Serializable>();
        //判断是否有值
        if (null != user.getNickname()) {
            sB.append(" nickname=? ");

            params.add(user.getNickname());
        }
        if (null != user.getUser_des()) {
            sB.append(",user_des=? ");

            params.add(user.getUser_des());
        }
        if (null != user.getSex()) {
            sB.append(",sex=? ");

            params.add(user.getSex());
        }
        if (null != user.getBirthday()) {
            sB.append(",birthday=? ");

            params.add(user.getBirthday());
        }
        if (null != user.getPhone()) {
            sB.append(",phone=? ");

            params.add(user.getPhone());
        }
        if (null != user.getAddress()) {
            sB.append(",address=? ");

            params.add(user.getAddress());
        }
        if (null != user.getUser_comments()) {
            sB.append(",user_comments=? ");

            params.add(user.getAddress());
        }
        sB.append(" WHERE user_id=? ");
        sql = sB.toString();

        params.add(user.getUser_id());

        return template.update(sql, params.toArray())>0;
    }

    @Override
    public List<Article> findAllUserArts(Long user_id) {
        String sql = "SELECT title,create_time,article_id FROM `article` where user_id=?";
        return template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),user_id);
    }

    @Override
    public String findUserCodeByEmail(String email) {
        String sql = "SELECT code FROM user WHERE email=? ";
        return template.queryForObject(sql,String.class,email);
    }

    @Override
    public boolean isEmptyUserFindByEmailAndCode(String email, String code) {
        String sql = "SELECT user_id FROM user WHERE email=? AND code=? ";
        Long end = null;
        try {
            end = template.queryForObject(sql,Long.class,email,code);
        } catch (DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception);
        }
        return end!=null;
    }
}
