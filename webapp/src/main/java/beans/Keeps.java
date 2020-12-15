package beans;

import java.util.Date;

public class Keeps {
    private Long id;
    private Long article_id;
    private Long user_id;
    private Date keep_time;

    public Keeps() {
    }

    public Keeps(Long id, Long article_id, Long user_id, Date keep_time) {
        this.id = id;
        this.article_id = article_id;
        this.user_id = user_id;
        this.keep_time = keep_time;
    }

    @Override
    public String toString() {
        return "Keeps{" +
                "id=" + id +
                ", article_id=" + article_id +
                ", user_id=" + user_id +
                ", keep_time=" + keep_time +
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

    public Date getKeep_time() {
        return keep_time;
    }

    public void setKeep_time(Date keep_time) {
        this.keep_time = keep_time;
    }
}
