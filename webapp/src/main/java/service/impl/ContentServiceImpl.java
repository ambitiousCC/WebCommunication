package service.impl;

import beans.*;
import dao.ContentDAO;
import dao.Impl.ContentDAOImpl;
import dao.Impl.UserDAOImpl;
import dao.UserDAO;
import service.ContentService;

import java.math.BigInteger;
import java.util.List;

public class ContentServiceImpl implements ContentService {
    private BigInteger commentMainLikes;
    private ContentDAO contentDAO = null;
    private UserDAO userDAO = null;
    {
        contentDAO = new ContentDAOImpl();
        userDAO = new UserDAOImpl();
    }

    /**
     * 查找文章
     * @param article_id
     * @return
     */
    public Article findOneArt(Long article_id) {
        //查文章主要信息
        Article article = contentDAO.findOneArt(article_id);

        //查询文章所有主评论
        List<CommentMain> commentMains = contentDAO.findOneComment(article_id);
        for (int i = 0; i < commentMains.size(); i++) {
            //主评论的点赞记录
            commentMainLikes = contentDAO.findLikeCountByCMid(commentMains.get(i).getId());
            commentMains.get(i).setLike(commentMainLikes);
        }

        //查文章的所有图片
        List<Img> imgs = contentDAO.findOneImgs(article_id);

        //查文章的所有内容
        Content content = contentDAO.findOneContent(article.getContent_id());

        //查询文章的作者
        User u = userDAO.findUser(article.getUser_id());

        //文章的点赞和收藏记录
        BigInteger likes = contentDAO.findLikeCountByAid(article_id);
        BigInteger keeps = contentDAO.findKeepCountByAid(article_id);

        User user = new User();
        user.setUser_id(u.getUser_id());
        user.setNickname(u.getNickname());
        user.setUser_ico(u.getUser_ico());
        user.setUser_des(u.getUser_des());

        //设置到文章主体
        article.setCommentMains(commentMains);
        article.setArticleImgList(imgs);
        article.setContent(content);
        article.setUser(user);
        article.setLike(likes);
        article.setKeep(keeps);

        return article;
    }

    public boolean removeArticle(Long article_id) {
        return contentDAO.removeArticle(article_id);
    }

    /**
     * 查询子评论
     * @param comment_main_id
     * @return
     */
    public CommentMain findCommentLists(Long comment_main_id) {
        CommentMain commentMains = contentDAO.findCommentLists(comment_main_id);

        List<Comment> comments = contentDAO.findDetailComment(comment_main_id);
        for (int i = 0; i < comments.size(); i++) {
            comments.get(i).setLike(contentDAO.findLikeCountByCid(comments.get(i).getId()));
        }

        commentMainLikes = contentDAO.findLikeCountByCMid(commentMains.getId());
        commentMains.setLike(commentMainLikes);

        commentMains.setCommentList(comments);

        return commentMains;
    }

    /**
     * 保存主评论
     * @param commentMain
     * @return
     */
    public boolean saveCommentMains(CommentMain commentMain) {
        return contentDAO.saveCommentMains(commentMain);
    }


    /**
     * 删除主评论
     * @param comment_main_id
     * @return
     */
    public boolean delCommentMains(Long comment_main_id) {
        return contentDAO.delCommentMains(comment_main_id);
    }

    /**
     * 保存子评论
     * @param comment
     * @return
     */
    public boolean saveComments(Comment comment) {
        return contentDAO.saveComments(comment);
    }

    /**
     * 删除子评论只要id就可以啦
     * @param comment_id
     * @return
     */
    public boolean deleteComments(Long comment_id) {
        return contentDAO.deleteComments(comment_id);
    }

    /**
     * 给文章增加点赞记录
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean addArtLike(Long article_id, Long user_id) {
        return contentDAO.addArtLike(article_id, user_id);
    }

    /**
     * 给文章增加收藏记录
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean addArtKeep(Long article_id, Long user_id) {
        return contentDAO.addArtKeep(article_id, user_id);
    }

    /**
     * 文章取消点赞
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean removeArtLike(Long article_id, Long user_id) {
        return contentDAO.removeArtLike(article_id, user_id);
    }

    /**
     * 文章取消收藏
     * @param article_id
     * @param user_id
     * @return
     */
    public boolean removeArtKeep(Long article_id, Long user_id) {
        return contentDAO.removeArtKeep(article_id, user_id);
    }

    /**
     * 给主评论点赞
     * @param article_id
     * @param user_id
     * @param comment_main_id
     * @return
     */
    public boolean addCommentMainLike(Long article_id,Long user_id,Long comment_main_id) {
        return contentDAO.addCommentMainLike(article_id,user_id,comment_main_id);
    }

    /**
     * 给主评论点赞
     * @param comment_main_id
     * @param user_id
     * @return
     */
    public boolean removeCommentMainLike(Long comment_main_id,Long user_id) {
        return contentDAO.removeCommentMainLike(comment_main_id,user_id);
    }

    /**
     * 给主评论点赞
     * @param article_id
     * @param user_id
     * @param comment_id
     * @return
     */
    public boolean addCommentLike(Long article_id,Long user_id,Long comment_id) {
        return contentDAO.addCommentLike(article_id,user_id,comment_id);
    }

    /**
     * 给主评论点赞
     * @param comment_id
     * @param user_id
     * @return
     */
    public boolean removeCommentLike(Long comment_id,Long user_id) {
        return contentDAO.removeCommentLike(comment_id,user_id);
    }

    @Override
    public Long saveContent(Content content) {
        boolean result = contentDAO.saveContent(content);
        if (result) {
            return contentDAO.findContentId(content.getKey());
        } else {
            return null;
        }
    }

    @Override
    public boolean saveArtcle(Article article) {
        return contentDAO.saveArticle(article);
    }
}
