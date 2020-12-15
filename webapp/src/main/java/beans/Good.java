package beans;

import java.util.Date;

public class Good {
    private Long id;
    private String cn;
    private String en;
    private Date create_time;
    private String author;

    public Good(Long id, String cn, String en, Date create_time, String author) {
        this.id = id;
        this.cn = cn;
        this.en = en;
        this.create_time = create_time;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", cn='" + cn + '\'' +
                ", en='" + en + '\'' +
                ", create_time=" + create_time +
                ", author='" + author + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
