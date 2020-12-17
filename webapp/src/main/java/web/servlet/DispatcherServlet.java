package web.servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DispatcherServlet extends HttpServlet {

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
        String path = req.getServletPath();
        System.out.println("能传送");
//        登录注册
        if (Objects.equals("/index", path)) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (Objects.equals("/sign", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/sign.html").forward(req, resp);
        }
//        首页index.html
        else if (Objects.equals("/main", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/main.html").forward(req, resp);
        }
//        其实这个是友情链接可以当做好友/关注页面
        else if (Objects.equals("/friend", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/friend.html").forward(req, resp);
        }
//        阅读墙可以留言可以爱咋咋地
        else if (Objects.equals("/readWall", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/readWall.html").forward(req, resp);
        }
//        标签就是关键字
        else if (Objects.equals("/set", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/set.html").forward(req, resp);
        }
//        个人资料不解释
        else if (Objects.equals("/about", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/about.html").forward(req, resp);
        }
//        文章模板
        else if (Objects.equals("/content", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/content.html").forward(req, resp);
        }
//        社区帖子
        else if (Objects.equals("/forum", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/forum.html").forward(req, resp);
        }
//        社区文章
        else if (Objects.equals("/globe", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/globe.html").forward(req, resp);
        }
//        写文章
        else if (Objects.equals("/write", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/write.html").forward(req, resp);
        }
//        钟表
        else if (Objects.equals("/clock", path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/clock.html").forward(req, resp);
        }
        else if (Objects.equals("/forget",path)) {
            req.getRequestDispatcher("/WEB-INF/views/biz/forgot.html").forward(req, resp);
        }
//        404 话说500咋整
        else {
            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
        }
    }
}
