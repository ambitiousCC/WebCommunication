package service.impl;

import beans.Article;
import beans.Page;
import dao.GlobeDAO;
import dao.Impl.GlobeDAOImpl;
import service.GlobeService;

public class GlobeServiceImpl implements GlobeService {
    private GlobeDAO globeDAO = new GlobeDAOImpl();

    /**
     * 通过类别查询分页
     * @param tid,pid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page<Article> pageQuery(int tid,int pid, int currentPage, int pageSize, String title) {
        //封装对象
        Page<Article> pg = new Page<Article>();
        //设置页码
        pg.setCurrentPage(currentPage);
        //设置每页显示条数
        pg.setPageSize(pageSize);
        //设置总记录数
        int totalCount = globeDAO.findTotalCount(tid,pid,title);
        pg.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage-1)*pageSize;//第一页的开始
        pg.setList(globeDAO.findPage(tid,pid,start,pageSize,title));
        //设置总页数
        int totalPage = totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pg.setTotalPage(totalPage);
        //返回对象
        return pg;
    }

}
