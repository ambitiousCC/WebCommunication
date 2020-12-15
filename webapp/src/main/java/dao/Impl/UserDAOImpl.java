package dao.Impl;

import beans.Article;
import beans.SetArt;
import beans.User;
import dao.UserDAO;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import web.utils.JDBCUtils;
import web.utils.JDBCUtilsPlus;
import web.utils.isEmptyStr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //注册插入,注册表单包含的数据有限，只能插入个别数据
            String sql = "INSERT INTO user(username,nickname,password,email,create_user_time,status,code) VALUES (?,?,?,?,?,?,?)";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setTimestamp(5, new Timestamp(user.getCreate_user_time().getTime()));
            pstmt.setString(6,user.getStatus());
            pstmt.setString(7, user.getCode());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println("注册失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.releaseCP(conn,pstmt);
        }
        return true;
    }
    /**
     * 判断用户是否存在:
     * @param username
     * @return 如果存在就返回true，否则就是false
     */
    @Override
    public boolean isEmptyUserFindByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            System.out.println("登录时检索用户名失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
    }
    /**
     * 判断用户是否存在:
     * @param email
     * @return 如果存在就返回true，否则就是false
     */
    @Override
    public boolean isEmptyUserFindByEmail(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE email=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(rs);
                System.out.println("能检索到邮箱");
               flag = true;
            }
            return flag;
        } catch (Exception e) {
            System.out.println("登录时检索用户名失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
    }
    /**
     * 通过CODE查询对象
     * @param code
     * @return 返回user对象
     */
    @Override
    public User isEmptyUserFindByCode(String code) {
        //值得考虑：如果用了别人的激活码怎么办呢？当然前提是知道其他人的激活码
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = getUser(rs);
            }
            return user;
        } catch (Exception e) {
            System.out.println("登录时检索用户名失败");
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
    }
    /**
     * 通过id查对象
     * @param user_id
     * @return 返回user对象
     */
    @Override
    public User findUser(Long user_id) {
        //值得考虑：如果用了别人的激活码怎么办呢？当然前提是知道其他人的激活码
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = getUser(rs);
            }
            return user;
        } catch (Exception e) {
            System.out.println("登录时检索用户名失败");
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
    }
    /**
     * 传入登录的user,判断是否登录成功--登录查询
     * @param username,password
     * @return
     */
    @Override
    public User LoginUser(String username,String password) {
        System.out.println(password);
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (Objects.equals(password,rs.getString("password"))) {
                    user = getUser(rs);
                    return user;
                }
            }
            return null;
        } catch (NullPointerException e) {
            System.out.println("密码错误");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库错误");
            return null;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
    }

    public User getUser(ResultSet rs) throws SQLException {
        User user;
        user = new User(rs.getLong("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("nickname"),
                rs.getTimestamp("birthday"),
                rs.getInt("age"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("sex"),
                rs.getTimestamp("create_user_time"),
                rs.getString("status"),
                rs.getString("code"),
                rs.getString("user_img"),
                rs.getString("user_ico"),
                rs.getString("user_des"),
                rs.getString("user_comments"));
        return user;
    }

    /**
     * 过滤器过滤
     * @param user
     * @return
     */
    @Override
    public boolean FilterUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user.getUser_id());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (Objects.equals(rs.getTimestamp("create_user_time"),user.getCreate_user_time())) {
                    flag = true;
                }
            }
            return flag;
        } catch (Exception e) {
            System.out.println("查询失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
    }
    /**
     * 通过user中的id更新激活状态
     * @param user
     * @return
     */
    @Override
    public boolean updateStatus(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //id不能修改
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE user SET status=? WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"Y");
            pstmt.setLong(2, user.getUser_id());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println("获取失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
        return true;
    }

    /**
     * 保存用户头像
     * @param url
     * @param user_id
     * @return
     */
    public boolean saveUserImg(String url,Long user_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //id不能修改
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE user SET user_ico=?,user_img=? WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,url);
            pstmt.setString(2,url);
            pstmt.setLong(3, user_id);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println("修改失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(conn,pstmt,rs);
        }
        return true;
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

        List params = new ArrayList();
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
}
