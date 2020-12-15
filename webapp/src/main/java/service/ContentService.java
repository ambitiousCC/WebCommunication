package service;

import beans.*;
import org.apache.commons.fileupload.FileItem;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ContentService {
    /**
     * 通过文章id查询文章
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
     * 返回一楼评论以及它的的所有子评论
     * @param comment_main_id
     * @return
     */
    CommentMain findCommentLists(Long comment_main_id);

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
     * 增加点赞记录
     * @param article_id
     * @param user_id
     * @return
     */
    boolean addArtLike(Long article_id, Long user_id);

    /**
     * 增加收藏记录
     * @param article_id
     * @param user_id
     * @return
     */
    boolean addArtKeep(Long article_id, Long user_id);
    /**
     * 文章取消点赞
     * @param article_id
     * @param user_id
     * @return
     */
    boolean removeArtLike(Long article_id, Long user_id);

    /**
     * 文章取消收藏
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
     * 删除主评论的赞
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
     * 删除主评论的赞
     * @param comment_id
     * @param user_id
     * @return
     */
    boolean removeCommentLike(Long comment_id,Long user_id);

    /**
     * 保存文章的主要内容，返回内容表的id
     * @param content
     * @return
     */
    Long saveContent(Content content);

    /**
     * 保存文章，返回是否成功
     * @param article
     * @return
     */
    boolean saveArtcle(Article article);
}
