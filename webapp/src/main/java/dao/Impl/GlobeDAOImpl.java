package dao.Impl;

import beans.Article;
import dao.GlobeDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import web.utils.JDBCUtilsPlus;

import java.util.ArrayList;
import java.util.List;

public class GlobeDAOImpl implements GlobeDAO {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtilsPlus.getDataSource());
    /**
     * 按照文章类别查询
     * @param tid
     * @param pid
     * @return
     */
    @Override
    public int findTotalCount(int tid,int pid, String title) {
        //定义模板
        String sql = "SELECT COUNT(*) FROM article WHERE 1=1";
        //拼接语句
        StringBuilder sB = new StringBuilder(sql);

        List params = new ArrayList();
        //判断是否有值
        if (0 != pid) {
            sB.append(" AND pid = ? ");

            params.add(pid);
        }
        if (0 != tid) {
            sB.append(" AND tid = ? ");

            params.add(tid);
        }
        if (null != title && title.length() > 0 && !"".equals(title) && !"null".equals(title)) {
            sB.append(" AND (title LIKE ? OR des LIKE ?)");

            params.add("%"+title+"%");
            params.add("%"+title+"%");
        }
        sql = sB.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }
    /**
     * 通过类别分页查询
     * @param tid
     * @param pid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> findPage(int tid,int pid, int start, int pageSize, String title) {
        String sql = "SELECT * FROM article WHERE 1=1 ";
        //拼接语句
        StringBuilder sB = new StringBuilder(sql);

        List params = new ArrayList();
        //判断是否有值
        if (0 != tid) {
            sB.append(" AND tid = ? ");

            params.add(tid);
        }
        //判断是否有值
        if (0 != pid) {
            sB.append(" AND pid = ? ");

            params.add(pid);
        }
        if (null != title && title.length() > 0 && !"null".equals(title)) {
            sB.append(" AND (title LIKE ? OR des LIKE ?)" );

            params.add("%"+title+"%");
            params.add("%"+title+"%");
        }
        sB.append(" LIMIT ?,? ");
        sql = sB.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), params.toArray());
    }

}
