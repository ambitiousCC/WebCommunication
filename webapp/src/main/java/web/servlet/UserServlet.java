package web.servlet;

import beans.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import service.LoginService;
import service.UserService;
import service.impl.LoginServiceImpl;
import service.impl.UserServiceImpl;
import web.utils.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserServlet extends HttpServlet {
    private UserService userService;
    private LoginService loginService;
    private ObjectMapper mapper;
    {
        userService = new UserServiceImpl();
        loginService = new LoginServiceImpl();
        mapper = new ObjectMapper();//jackson的核心对象哦
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("visitor", new Visitor(getClientIp.getIpaddr(req),
                getClientIp.getBrowserName(req),
                getClientIp.getBrowserVersion(req),
                getClientIp.getOsName(req),
                AddressUtils.getAddress(getClientIp.getIpaddr(req))));
//        String path = req.getRequestURI();
//        System.out.println(path);
//        String params = path.substring(path.indexOf("/",1)+1,path.length());
//        System.out.println(params);
//
//
//        String out  = null;
//        try {
//            out = AesEncryptUtil.desEncrypt(params).trim();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(out);
//        path = "/user/"+out;
        String path = req.getServletPath();

        if (Objects.equals("/user/login", path)) {
            login(req, resp);
        }
        else if (Objects.equals("/user/regist", path)) {
            try {
                regist(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (Objects.equals("/user/exist", path)) {
            exist(req, resp);
        }
        else if (Objects.equals("/user/check.do",path)) {
            try {
                checkPassword(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (Objects.equals("/user/findUser.do", path)) {
            System.out.println("normal");
            findOneUser(req, resp);
        }
        else if (Objects.equals("/user/about/findOtherUser", path)) {
            findOtherUsers(req,resp);
        }
        else if (Objects.equals("/user/activeAccount", path)) {
            activeAccount(req, resp);
        }
        else if (Objects.equals("/user/sendEmail",path)) {
            System.out.println("a");
            sendEmail(req,resp);
        }
        else if (Objects.equals("/user/save/password.do",path)) {
            try {
                changePassword(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (Objects.equals("/user/save/profile.do", path)) {
            try {
                updateProfile(req, resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if (Objects.equals("/user/save/userImg.do", path)) {
            saveUserImg(req, resp);
        }
        else if (Objects.equals("/user/save/update.do", path)) {
            //销毁
            req.getSession().invalidate();
            //重定向
            resp.sendRedirect("/sign");
        } else if (Objects.equals("/user/about/saveComment.do", path)) { //待完成前端
            saveMsg(req, resp);
        } else if (Objects.equals("/user/about/arts.do",path)) {
            setUserArts(req,resp);
        }

        else {
            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
        }
    }

    private void sendEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("b");
        Visitor visitor = (Visitor) req.getSession().getAttribute("visitor");
        System.out.println("visitor:"+visitor.toString());
//        String email = req.getParameter("email");
//        //通过邮箱查找是否注册成功
//        boolean flag = userService.isEmptyUserFindByEmail(email);
//        if(!flag) {
//            WriteUitl.writeValue(flag,resp);
//            return ;
//        }
//        //查找激活码
//        String code;
//
//        //发送带有激活码的邮件
//
//        //然后重新弄一个处理器处理修改密码的

    }

    /**
     * 登录
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            //1获取用户名和密码
            Map<String,String[]> map = req.getParameterMap();
            //2封装
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        //调用查询
            boolean flag = userService.isEmptyUserFindByUsername(user.getUsername());
            //序列化JSON数据
            ResultInfo info = new ResultInfo();
            if (flag) {
                //判断是否登录成功
                //数据库对密码进行二次加密
                try {
                    user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
                } catch (Exception e) {
                    System.out.println("紧急！加密方法失效");
                    e.printStackTrace();
                }
                User u = loginService.LoginUser(user);
                if (null != u) {
                    //用户是否激活
                    if ("Y".equals(u.getStatus())) {
                        System.out.println("登陆成功");
                        //登录成功
                        User safeUser = new User(u.getUser_id(), u.getNickname(), u.getEmail(), u.getBirthday(), u.getAge(),
                                u.getSex(), u.getCreate_user_time(), u.getUser_img(), u.getUser_ico(),u.getUser_des(),u.getPhone(),u.getLast_login());
                        req.getSession().setAttribute("user", safeUser);
                        req.setAttribute("user", safeUser);
                        info.setFlag(true);
                    } else {
                        //删除冗余信息
                        boolean deleteFlag = userService.removeUser(u.getUser_id());
                        if(deleteFlag) {
                            System.out.println("删除冗余用户"+u.getUsername()+"成功");
                        } else {
                            System.out.println("注销失败");
                        }
                        info.setFlag(false);
                        info.setErrorMsg("您的账号未在规定时间激活，请重新注册");
                    }
                    //返回地址：应该是首页？需要后端给位置
                } else {
                    System.out.println("用户名或密码错误");
                    info.setErrorMsg("用户或密码错误");
                    info.setFlag(false);
                }
            } else {
                System.out.println("用户不存在");
                //提醒用户注册
                info.setFlag(false);
                info.setErrorMsg("用户不存在");
            }

            //向前端响应
            resp.setContentType("application/json;charset=utf-8");
            mapper.writeValue(resp.getOutputStream(), info);
    }

    /**
     * 注册
     */
    private void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1获取前台用户输入信息
        Map<String,String[]> map = req.getParameterMap();
        //2封装数据
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("封装数据不成功");
            e.printStackTrace();
        }
        //md5二次加密
        try {
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            System.out.println("紧急！加密方法失效");
            e.printStackTrace();
        }
        //3调用完成注册
        //序列化JSON数据
        String json;
        ResultInfo info = new ResultInfo();
        //先判断邮箱地址
        boolean flagEmail = userService.isEmptyUserFindByEmail(user.getEmail());
        if(!flagEmail) {
            //先判断用户名是否已经存在
            boolean flagName = userService.isEmptyUserFindByUsername(user.getUsername());
            if (!flagName) {
                //默认昵称和用户名一致
                user.setNickname(user.getUsername());
                String weblocation = req.getScheme() + "://" + req.getServerName()
                        + ":" + req.getServerPort();
                //进一步注册
                boolean flag = userService.regist(user,weblocation);
                //4向前台响应
                if (flag) {
                    //成功注册
                    info.setFlag(true);
                } else {
                    info.setFlag(false);
                    info.setErrorMsg("注册失败");
                }
            } else {
                //判断用户名
                info.setFlag(false);
                info.setErrorMsg("该用户名已经被注册");
            }
        } else {
            //判断邮箱
            info.setFlag(false);
            info.setErrorMsg("邮箱已被注册或未激活");
        }
        json = mapper.writeValueAsString(info);

        //设置回流的数据类型
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    private void checkPassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = (User)req.getSession().getAttribute("user");
        String password = req.getParameter("password");
        user.setPassword(Md5Util.encodeByMd5(password));
        boolean flag = userService.checkPassword(user);
        WriteUitl.writeValue(flag,resp);
    }

    /**
     * 修改密码
     */
    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws  Exception {
        User user = (User)req.getSession().getAttribute("user");
        String password = req.getParameter("password");
        user.setPassword(Md5Util.encodeByMd5(password));
        System.out.println(user.toString());
        boolean flag = userService.changePassword(user);
        WriteUitl.writeValue(flag,resp);
    }
    /**
     * 退出
     */
    private void exist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //销毁
            req.getSession().invalidate();
            //重定向
            resp.sendRedirect("/main");
    }

    /**
     * 注销
     */
    private void removeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * 查找一个用户
     */
    private void findOneUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中获取登录用户
        User user = (User)req.getSession().getAttribute("user");
        //将user写回客户端
        String json = mapper.writeValueAsString(user);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    private void findOtherUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //找自己或者其他人的
        String user_idStr = req.getParameter("user_id");

        Long user_id = null;
        if (isEmptyStr.isNotEmpty(user_idStr)) {
            user_id = Long.valueOf(user_idStr);
        }

        User user = (User) req.getSession().getAttribute("user");

        if (user_id == null && user == null) {
            //非法访问
            return ;
        } else if (user_id == null) {
            //查看自己的资料
            String json = mapper.writeValueAsString(user);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return ;
        }

        //查看别人的资料
        User otherUser = userService.findOtherUser(user_id);

        //封装安全对象
        User safeUser = new User();
        safeUser.setNickname(otherUser.getNickname());
        safeUser.setUser_des(otherUser.getUser_des());
        safeUser.setSex(otherUser.getSex());
        safeUser.setUser_comments(otherUser.getUser_comments());
        safeUser.setUser_ico(otherUser.getUser_ico());

        //返回数据
        String json = mapper.writeValueAsString(safeUser);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
    /**
     * 激活账户
     */
    private void activeAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取激活码
        String queryString = req.getQueryString();
        System.out.println(queryString);
        String code = req.getParameter("code");
        System.out.println(code);
        if (code != null) {
            //确定code是否正确
            boolean flag = userService.activeAccount(code);
            String msg = null;
            if (flag) {
                msg = "激活成功，请<a href='/sign'>登录</a>您的账号";
            } else {
                msg = "激活失败，请检查您的网络或联系<a href='http://github.com/ambitiousCC'>管理员</a>";
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
    }

    private void saveUserImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean flag = false;
        String imgStr = req.getParameter("img");
        String savePath = "/images/users/ico";
        User user = (User)req.getSession().getAttribute("user");
        if (null == user) {
            //返回数据
            WriteUitl.writeValue(flag,resp);
            return ;
        }
        String path = this.getServletContext().getRealPath(savePath);
        String url = req.getScheme() + "://" + req.getServerName()
                + ":" + req.getServerPort() + "/images/users/ico/" + EncodingUtils.GenerateImage(imgStr, path);
        flag = userService.saveUserImg(url,user.getUser_id());

        //更新后修改缓存
        if(flag) {
            req.getSession().invalidate(); //先删除
            user.setUser_img(url);
            req.getSession().setAttribute("user", user);
            req.setAttribute("user", user);
        }

        //返回数据
        WriteUitl.writeValue(flag,resp);
    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException {
        String nickname = req.getParameter("nickname");
        String des = req.getParameter("des");
        String sex = req.getParameter("sex");
        String birthdayStr = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        //简单判断用户
        User u = (User)req.getSession().getAttribute("user");
        if (null == u) {
            return ;
        }

        //封装对象
        User user = new User();
        user.setUser_id(u.getUser_id());
        if (isEmptyStr.isNotEmpty(nickname)) {
            user.setNickname(nickname);
        } else {
            user.setNickname(u.getNickname());
        }
        if (isEmptyStr.isNotEmpty(des)) {
            user.setUser_des(des);
        }
        if (null != sex && !"".equals(sex) && !"null".equals(sex)) {
            if ("1".equals(sex)) {
                sex = "男";
            } else {
                sex = "女";
            }
        } else {
            sex = "保密";
        }
        user.setSex(sex);
        if (isEmptyStr.isNotEmpty(birthdayStr)) {
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
            user.setBirthday(birthday);
        }
        if (isEmptyStr.isNotEmpty(phone)) {
            user.setPhone(phone);
        }
        if (isEmptyStr.isNotEmpty(address)) {
            user.setAddress(address);
        }

        //更新信息
        boolean flag = userService.updateUserProfile(user);

        //更新信息之后销毁原来的存储对象，然后更新
        if(flag) {
            req.getSession().invalidate(); //先删除
            req.getSession().setAttribute("user", user);
            req.setAttribute("user", user);
        }

        WriteUitl.writeValue(flag, resp);
    }

    /**
     * 这里本来想做一个给用户留言的控制器，但是觉得不好，就暂时放下了
     * 给用户留言的控制器，待完成
     */
    private void saveMsg(HttpServletRequest req, HttpServletResponse resp) {
        //获取用户id
        String user_idStr = req.getParameter("user_id");
        String comment = req.getParameter("comment");
        Long user_id = null;
        if (isEmptyStr.isNotEmpty(user_idStr)) {
            user_id = Long.valueOf(user_idStr);
        }

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return ;
        }

        //封装评论
        UserMsg userMsg = new UserMsg();
        userMsg.setComment(comment);
    }

    /**
     * 查找所有文章
     */
    private void setUserArts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取该用户的id
        String user_idStr = req.getParameter("user_id");
        //数据转换
        Long user_id = null;
        if (isEmptyStr.isNotEmpty(user_idStr)) {
            user_id = Long.valueOf(user_idStr);
        }

        SetArt art = new SetArt();

        //数据库查询
        List<Article> setArtList = userService.findAllUserArts(user_id);
        System.out.println(setArtList);
        art.setArticles(setArtList);
        Map<String, String> msg = new HashMap<>();
        msg.put("flag","error");
        msg.put("msg","无内容");
        if(setArtList.size() > 0) {
            WriteUitl.writeValue(art, resp);
        } else {
            WriteUitl.writeValue(msg,resp);
        }
    }
}

