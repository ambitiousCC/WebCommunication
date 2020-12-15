package service;

import beans.Article;
import beans.Page;

public interface GlobeService {
    /**
     * 按照类别进行分页
     * @param tid,pid
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Article> pageQuery(int tid,int pid, int currentPage, int pageSize,String title);

}
