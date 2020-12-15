package dao.Impl;

import beans.*;
import dao.ContentDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import web.utils.JDBCUtilsPlus;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ContentDAOImpl implements ContentDAO {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtilsPlus.getDataSource());

    /**
     * 查询所有文章信息
     * @param article_id
     * @return
     */
    @Override
    public Article findOneArt(Long article_id) {
        String sql = "SELECT * FROM article WHERE article_id=? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Article>(Article.class), article_id);
    }

    /**
     * 查询一篇文章的内容
     * @param content_id
     * @return
     */
    @Override
    public Content findOneContent(Long content_id) {
        String sql = "SELECT * FROM content WHERE content_id=? ";
        System.out.println("查询文章内容");
        return template.queryForObject(sql, new BeanPropertyRowMapper<Content>(Content.class), content_id);
    }

    @Override
    public boolean removeArticle(Long article_id) {
        System.out.println("删除文章查询语句");
        String sql = "DELETE FROM article WHERE article_id=? ";
        return template.update(sql,article_id) > 0;
    }

    /**
     * 查找一个文章的主评论表
     * @param article_id
     * @return
     */
    @Override
    public List<CommentMain> findOneComment(Long article_id) {
        String sql = "SELECT * FROM comment_main WHERE article_id=? ORDER BY comment_main_time";
        return template.query(sql, new BeanPropertyRowMapper<CommentMain>(CommentMain.class), article_id);
    }

    /**
     * 查询一篇文章主评论表下的的所有评论
     * @param comment_main_id
     * @return
     */
    @Override
    public List<Comment> findDetailComment(Long comment_main_id) {
        String sql = "SELECT * FROM comment WHERE comment_main_id=? ORDER BY comment_time";
        return template.query(sql, new BeanPropertyRowMapper<Comment>(Comment.class),comment_main_id);
    }

    /**
     * 通过文章id查找图片们
     * @param article_id
     * @return
     * 注意这里的数据类型Long
     */
    @Override
    public List<Img> findOneImgs(Long article_id) {
        String sql = "SELECT * FROM img WHERE article_id=? ";
        System.out.println("查询文章图片");
        return template.query(sql, new BeanPropertyRowMapper<Img>(Img.class), article_id);
    }

    /**
     * 查询主评论
     * @param id
     * @return
     */
    @Override
    public CommentMain findCommentLists(Long id){
        String sql = "SELECT * FROM comment_main WHERE id=? ORDER BY comment_main_time";
        return template.queryForObject(sql, new BeanPropertyRowMapper<CommentMain>(CommentMain.class),id);
    }

    /**
     * 保存父评论
     * @param commentMain
     * @return
     */
    @Override
    public boolean saveCommentMains(CommentMain commentMain) {
        //1.定义sql
        String sql = "INSERT INTO comment_main(article_id,user_id,comment,comment_main_time,user_ico,nickname) values(?,?,?,?,?,?)";
        //2.执行sql
        System.out.println(commentMain.toString());
        System.out.println(commentMain.getArticle_id());
        return template.update(sql,commentMain.getArticle_id(), commentMain.getUser_id(), commentMain.getComment(), commentMain.getComment_main_time(), commentMain.getUser_ico(), commentMain.getNickname()) > 0;
    }

    /**
     * 删除主评论
     * @param comment_main_id
     * @return
     */
    public boolean delCommentMains(Long comment_main_id) {
        //1定义
        String sql = "DELETE FROM comment_main WHERE id=? ";
        //执行
        return  template.update(sql,comment_main_id) > 0;
    }

    /**
     * 保存子评论
     * @param comment
     * @return
     */
    @Override
    public boolean saveComments(Comment comment) {
        //1.定义sql
        String sql = "INSERT INTO comment(comment,article_id,comment_time,from_user,to_user,user_ico,from_nickname,to_nickname,comment_main_id) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        int rows = template.update(sql, comment.getComment(), comment.getArticle_id(), comment.getComment_time(), comment.getFrom_user(), comment.getTo_user(), comment.getUser_ico(), comment.getFrom_nickname(), comment.getTo_nickname(), comment.getComment_main_id());
        //返回结果
        if (rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除子评论
     * @param id
     * @return
     */
    @Override
    public boolean deleteComments(Long id) {
        //1定义
        String sql = "DELETE FROM comment WHERE id=? ";
        //执行
        return  template.update(sql,id) > 0;
    }

    /**
     * 通过文章id查询点赞记录
     * @param article_id
     * @return
     */
    @Override
    public BigInteger findLikeCountByAid(Long article_id){
        String sql = "SELECT COUNT(*) FROM art_likes WHERE article_id=? ";

        return template.queryForObject(sql, BigInteger.class, article_id);
    }

    /**
     * 通过文章id查询收藏记录
     * @param article_id
     * @return
     */
    @Override
    public BigInteger findKeepCountByAid(Long article_id) {
        String sql = "SELECT COUNT(*) FROM art_keeps WHERE article_id=? ";

        return template.queryForObject(sql, BigInteger.class, article_id);
    }

    /**
     * 查询文章主评论的点赞数
     * @param CMid
     * @return
     */
    @Override
    public BigInteger findLikeCountByCMid(Long CMid) {
        String sql = "SELECT COUNT(*) FROM art_comment_main_likes WHERE comment_main_id=? ";

        return template.queryForObject(sql, BigInteger.class, CMid);
    }
    /**
     * 查询文章副评论的点赞数
     * @param Cid
     * @return
     */
    @Override
    public BigInteger findLikeCountByCid(Long Cid) {
        String sql = "SELECT COUNT(*) FROM art_comment_likes WHERE comment_id=? ";

        return template.queryForObject(sql, BigInteger.class, Cid);
    }

    /**
     * 增加文章点赞记录
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean addArtLike(Long article_id, Long user_id) {
        String sql = "INSERT INTO art_likes(article_id,user_id,like_time) VALUES(?,?,?)";

        return template.update(sql, article_id, user_id, new Date()) > 0;
    }

    /**
     * 增加文章收藏记录
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean addArtKeep(Long article_id, Long user_id) {
        String sql = "INSERT INTO art_keeps(article_id,user_id,keep_time) VALUES(?,?,?)";

        return template.update(sql, article_id, user_id, new Date()) > 0;
    }

    /**
     * 取消文章点赞记录
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean removeArtLike(Long article_id, Long user_id) {
        String sql = "DELETE FROM art_likes WHERE article_id=? AND user_id=? ";

        return template.update(sql, article_id, user_id) > 0;
    }

    /**
     * 增加文章收藏记录
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean removeArtKeep(Long article_id, Long user_id) {
        String sql = "DELETE FROM art_keeps WHERE article_id=? AND user_id=? ";

        return template.update(sql, article_id, user_id) > 0;
    }

    /**
     * 给主评论点赞
     * @param article_id
     * @param user_id
     * @param comment_main_id
     * @return
     */
    public boolean addCommentMainLike(Long article_id,Long user_id,Long comment_main_id) {
        String sql = "INSERT INTO art_comment_main_likes(article_id,user_id,like_time,comment_main_id) VALUES(?,?,?,?)";

        return template.update(sql, article_id,user_id, new Date(), comment_main_id) > 0;
    }

    /**
     * 取消赞
     * @param comment_main_id
     * @param user_id
     * @return
     */
    public boolean removeCommentMainLike(Long comment_main_id,Long user_id) {
        String sql = "DELETE FROM art_comment_main_likes WHERE comment_main_id=? AND user_id=? ";

        return template.update(sql, comment_main_id,user_id) > 0;
    }

    /**
     * 给主评论点赞
     * @param article_id
     * @param user_id
     * @param comment_id
     * @return
     */
    public boolean addCommentLike(Long article_id,Long user_id,Long comment_id) {
        String sql = "INSERT INTO art_comment_likes(article_id,user_id,like_time,comment_id) VALUES(?,?,?,?)";

        return template.update(sql, article_id,user_id, new Date(), comment_id) > 0;
    }

    /**
     * 取消赞
     * @param comment_id
     * @param user_id
     * @return
     */
    public boolean removeCommentLike(Long comment_id,Long user_id) {
        String sql = "DELETE FROM art_comment_likes WHERE comment_id=? AND user_id=? ";

        return template.update(sql, comment_id,user_id) > 0;
    }

    /**
     * 保存文章内容
     * @param content
     * @return
     */
    public boolean saveContent(Content content) {
        String sql = " INSERT INTO content(user_id,content_time,content,content_key) VALUES(?,?,?,?) ";

        return template.update(sql, content.getUser_id(),content.getContent_time(), content.getContent(),content.getKey()) > 0;
    }

    /**
     * 接连上面操作，查询content_id标识
     * @param key
     * @return
     */
    public Long findContentId(String key) {
        String sql = "SELECT content_id FROM content WHERE content_key=? ";
        Long content_id = null;
        try {
            content_id = template.queryForObject(sql, Long.class, key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return content_id;
    }

    /**
     * 保存文章
     * @param article
     * @return
     */
    public boolean saveArticle(Article article) {
        System.out.println("能正常插入数据库");
        String sql = "INSERT INTO article(user_id,content_id,title,des,create_time,article_img,article_author,tid,pid) VALUES(?,?,?,?,?,?,?,?,?)";

        return template.update(sql, article.getUser_id(), article.getContent_id(), article.getTitle(), article.getDes(),
                new Date(), article.getArticle_img(), article.getArticle_author(), article.getTid(), article.getPid()) > 0;
    }

}
