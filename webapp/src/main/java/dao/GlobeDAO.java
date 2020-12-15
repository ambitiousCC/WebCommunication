package dao;

import beans.Article;

import java.util.List;

public interface GlobeDAO {
    /**
     * 分类查询
     * @param tid,pid,title
     * @return 返回符合条件的总数
     */
    int findTotalCount(int tid,int pid,String title);

    /**
     * 通过分类查询
     * @param tid,pid
     * @param start//开始
     * @param pageSize
     * @return 返回符合条件的页面集合
     */
    List<Article> findPage(int tid,int pid, int start, int pageSize, String title);

}
