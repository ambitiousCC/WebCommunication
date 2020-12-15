package beans;

import java.math.BigInteger;
import java.util.Date;

public class Comment {
    private Long id;
    private Long from_user;
    private Long to_user;
    private String comment;
    private Long article_id;
    private Date comment_time;
    private String user_ico;
    private String from_nickname;
    private String to_nickname;
    private BigInteger like;
    private Long comment_main_id;

    private Like_keep like_keep;

    public Comment() {}


    public Comment(Long id, Long from_user, Long to_user, String comment, Long article_id, Date comment_time, String user_ico, String from_nickname, String to_nickname, BigInteger like, Long comment_main_id, Like_keep like_keep) {
        this.id = id;
        this.from_user = from_user;
        this.to_user = to_user;
        this.comment = comment;
        this.article_id = article_id;
        this.comment_time = comment_time;
        this.user_ico = user_ico;
        this.from_nickname = from_nickname;
        this.to_nickname = to_nickname;
        this.like = like;
        this.comment_main_id = comment_main_id;
        this.like_keep = like_keep;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", from_user=" + from_user +
                ", to_user=" + to_user +
                ", comment='" + comment + '\'' +
                ", article_id=" + article_id +
                ", comment_time=" + comment_time +
                '}';
    }


    public Long getComment_main_id() {
        return comment_main_id;
    }

    public void setComment_main_id(Long comment_main_id) {
        this.comment_main_id = comment_main_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrom_user() {
        return from_user;
    }

    public void setFrom_user(Long from_user) {
        this.from_user = from_user;
    }

    public Long getTo_user() {
        return to_user;
    }

    public void setTo_user(Long to_user) {
        this.to_user = to_user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public String getUser_ico() {
        return user_ico;
    }

    public void setUser_ico(String user_ico) {
        this.user_ico = user_ico;
    }

    public String getFrom_nickname() {
        return from_nickname;
    }

    public void setFrom_nickname(String from_nickname) {
        this.from_nickname = from_nickname;
    }

    public String getTo_nickname() {
        return to_nickname;
    }

    public void setTo_nickname(String to_nickname) {
        this.to_nickname = to_nickname;
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
}
