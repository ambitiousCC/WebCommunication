package dao;

import beans.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ContentDAO {
    /**
     * 查询一篇文章的完整信息
     * @param article_id
     * @return
     */
    Article findOneArt(Long article_id);

    /**
     * 删除文章
     * @param article_id
     * @return
     */
    boolean removeArticle(Long article_id);

    /**
     * 查询文章附带的详细文本
     * @param content_id
     * @return
     */
    Content findOneContent(Long content_id);

    /**
     * 查询一篇文章的所有评论
     * @param article_id
     * @return
     */
    List<CommentMain> findOneComment(Long article_id);

    /**
     * 查询一篇文章主评论表下的的所有评论
     * @param comment_main_id
     * @return
     */
    List<Comment> findDetailComment(Long comment_main_id);

    /**
     * 查询某一个评论以及它的子评论
     * @param comment_main_id
     * @return
     */
    CommentMain findCommentLists(Long comment_main_id);

    /**
     * 通过文章id查找图片们
     * @param article_id
     * @return
     */
    List<Img> findOneImgs(Long article_id);

    /**
     * 保存主评论
     * @param commentMain
     * @return
     */
    boolean saveCommentMains(CommentMain commentMain);

    /**
     * 删除主评论
     * @param comment_main_id
     * @return
     */
    boolean delCommentMains(Long comment_main_id);

    /**
     * 保存子评论
     * @param comment
     * @return
     */
    boolean saveComments(Comment comment);

    /**
     * 删除子评论
     * @param comment_id
     * @return
     */
    boolean deleteComments(Long comment_id);

    /**
     * 通过文章id查询点赞记录
     * @param article_id
     * @return
     */
    BigInteger findLikeCountByAid(Long article_id);

    /**
     * 通过文章id查询收藏记录
     * @param article_id
     * @return
     */
    BigInteger findKeepCountByAid(Long article_id);

    /**
     * 查询文章主评论的点赞数
     * @param comment_main_id
     * @return
     */
    BigInteger findLikeCountByCMid(Long comment_main_id);

    /**
     * 查询文章副评论的点赞数
     * @param comment_id
     * @return
     */
    BigInteger findLikeCountByCid(Long comment_id);

    /**
     * 增加文章点赞记录
     * @param article_id
     * @param user_id
     * @return
     */
    boolean addArtLike(Long article_id, Long user_id);

    /**
     * 增加文章收藏记录
     * @param article_id
     * @param user_id
     * @return
     */
    boolean addArtKeep(Long article_id, Long user_id);

    /**
     * 取消点赞
     * @param article_id
     * @param user_id
     * @return
     */
    boolean removeArtLike(Long article_id, Long user_id);

    /**
     * 取消收藏
     * @param article_id
     * @param user_id
     * @return
     */
    boolean removeArtKeep(Long article_id, Long user_id);

    /**
     * 给主评论点赞
     * @param article_id
     * @param user_id
     * @param comment_main_id
     * @return
     */
    boolean addCommentMainLike(Long article_id,Long user_id,Long comment_main_id);

    /**
     * 取消赞
     * @param comment_main_id
     * @param user_id
     * @return
     */
    boolean removeCommentMainLike(Long comment_main_id,Long user_id);

    /**
     * 给主评论点赞
     * @param article_id
     * @param user_id
     * @param comment_id
     * @return
     */
    boolean addCommentLike(Long article_id,Long user_id,Long comment_id);

    /**
     * 取消赞
     * @param comment_id
     * @param user_id
     * @return
     */
    boolean removeCommentLike(Long comment_id,Long user_id);

    /**
     * 保存文章内容
     * @param content
     * @return
     */
    boolean saveContent(Content content);

    /**
     * 接连上面操作，查询content_id标识
     * @param key
     * @return
     */
    Long findContentId(String key);

    /**
     * 保存文章
     * @param article
     * @return
     */
    boolean saveArticle(Article article);
}
