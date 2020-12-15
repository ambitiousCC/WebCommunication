package beans;

import java.util.Date;

public class Likes {
    private Long id;
    private Date like_time;
    private Date comment_main_time;
    private Date comment_time;
    private Long user_id;
    private Long article_id;
    private Long comment_main_id;
    private Long comment_id;

    public Likes() {
    }

    public Likes(Long id, Date like_time, Date comment_main_time, Date comment_time, Long user_id, Long article_id, Long comment_main_id, Long comment_id) {
        this.id = id;
        this.like_time = like_time;
        this.comment_main_time = comment_main_time;
        this.comment_time = comment_time;
        this.user_id = user_id;
        this.article_id = article_id;
        this.comment_main_id = comment_main_id;
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", like_time=" + like_time +
                ", comment_main_time=" + comment_main_time +
                ", comment_time=" + comment_time +
                ", user_id=" + user_id +
                ", article_id=" + article_id +
                ", comment_main_id=" + comment_main_id +
                ", comment_id=" + comment_id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLike_time() {
        return like_time;
    }

    public void setLike_time(Date like_time) {
        this.like_time = like_time;
    }

    public Date getComment_main_time() {
        return comment_main_time;
    }

    public void setComment_main_time(Date comment_main_time) {
        this.comment_main_time = comment_main_time;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getComment_main_id() {
        return comment_main_id;
    }

    public void setComment_main_id(Long comment_main_id) {
        this.comment_main_id = comment_main_id;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }
}
