package beans;

import java.io.Serializable;
import java.util.Date;

public class Content implements Serializable {
    //注意这里的content_id是和article中的content_id保持一致的
    private Long content_id;
    private Long user_id;
    private Date content_time;
    private String key;
    private String content;


    public Content(){}

    public Content(Long content_id,Long user_id, Date content_time, String key, String content) {
        this.content_id = content_id;
        this.user_id = user_id;
        this.content_time = content_time;
        this.key = key;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "content_id=" + content_id +
                ", content='" + content + '\'' +
                ", user_id=" + user_id +
                ", content_time=" + content_time +
                ", key=" + key +
                '}';
    }

    public Long getContent_id() {
        return content_id;
    }

    public void setContent_id(Long content_id) {
        this.content_id = content_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getContent_time() {
        return content_time;
    }

    public void setContent_time(Date content_time) {
        this.content_time = content_time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
