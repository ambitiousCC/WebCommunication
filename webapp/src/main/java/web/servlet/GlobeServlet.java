package web.servlet;

import beans.Article;
import beans.Page;
import service.GlobeService;
import service.impl.GlobeServiceImpl;
import web.utils.WriteUitl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class GlobeServlet extends HttpServlet {

    private GlobeService globeService = null;
    private Page<Article> pg = null;
    {
        globeService = new GlobeServiceImpl();
        pg = new Page<>();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * 实际上，最关键的是传进来的cid的值，是按照cid来查询不同类型的文章
     * 社区的简略查询，包括社区的文章等等，主要是社区有不同种类的内容
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (Objects.equals("/globe/article", path)) {
            articleQuery(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/error/404.html").forward(req, resp);
        }
    }

    /**
     * 社区的简单查询
     * @param req
     * @param resp
     * @throws IOException
     */
    private void articleQuery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //接受对象
        String tidStr = req.getParameter("tid");
        String pidStr = req.getParameter("pid");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        //接受新的对象
        String title = req.getParameter("title");

        //参数转换
        int currentPage = 0;
        if (null != currentPageStr && currentPageStr.length() > 0 && !"null".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 0;
        if (null != pageSizeStr && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 2;
        }

        int tid = 0;
        if (null != tidStr && tidStr.length() > 0) {
            tid = Integer.parseInt(tidStr);
        }
        int pid = 0;
        if (null != pidStr && pidStr.length() > 0) {
            pid = Integer.parseInt(pidStr);
        }

        //servlet
        pg = globeService.pageQuery(tid,pid,currentPage,pageSize,title);
        //打包返回
        req.setAttribute("title", title);
        WriteUitl.writeValue(pg, resp);
    }

}
