package beans;

import java.io.Serializable;

public class Img implements Serializable {
    private Long id;
    private Long img_id;//与文章的id相关联
    private String bigPic;
    private String smallPic;

    public Img() {}

    public Img(Long id, Long img_id, String bigPic, String smallPic) {
        this.id = id;
        this.img_id = img_id;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImg_id() {
        return img_id;
    }

    public void setImg_id(Long img_id) {
        this.img_id = img_id;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
