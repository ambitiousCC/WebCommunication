package beans;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class CommentMain {
    private Long id;
    private Long article_id;
    private Long user_id;
    private String comment;
    private Date comment_main_time;
    private String user_ico;
    private String nickname;
    private BigInteger like;

    private List<Comment> commentList;
    private Like_keep like_keep;

    public CommentMain() {}

    @Override
    public String toString() {
        return "CommentMain{" +
                "id=" + id +
                ", article_id=" + article_id +
                ", user_id=" + user_id +
                ", comment='" + comment + '\'' +
                ", comment_main_time=" + comment_main_time +
                ", user_ico='" + user_ico + '\'' +
                ", nickname='" + nickname + '\'' +
                ", commentList=" + commentList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_main_time() {
        return comment_main_time;
    }

    public void setComment_main_time(Date comment_main_time) {
        this.comment_main_time = comment_main_time;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getUser_ico() {
        return user_ico;
    }

    public void setUser_ico(String user_ico) {
        this.user_ico = user_ico;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigInteger getLike() {
        return like;
    }

    public void setLike(BigInteger like) {
        this.like = like;
    }

    public Like_keep getLike_keep() {
        return like_keep;
    }

    public void setLike_keep(Like_keep like_keep) {
        this.like_keep = like_keep;
    }

    public CommentMain(Long id, Long article_id, Long user_id, String comment, Date comment_main_time, String user_ico, String nickname, BigInteger like, List<Comment> commentList, Like_keep like_keep) {
        this.id = id;
        this.article_id = article_id;
        this.user_id = user_id;
        this.comment = comment;
        this.comment_main_time = comment_main_time;
        this.user_ico = user_ico;
        this.nickname = nickname;
        this.like = like;
        this.commentList = commentList;
        this.like_keep = like_keep;
    }
}
