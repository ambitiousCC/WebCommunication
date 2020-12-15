package beans;

public class Art_count {
    private Long id;
    private int click;
    private int comment;
    private Long article_id;

    public Art_count(Long id, int click, int comment, Long article_id) {
        this.id = id;
        this.click = click;
        this.comment = comment;
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "Art_count{" +
                "id=" + id +
                ", click=" + click +
                ", comment=" + comment +
                ", article_id=" + article_id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }
}
