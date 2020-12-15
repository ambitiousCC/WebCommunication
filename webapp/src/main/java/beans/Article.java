package beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {
    private Long article_id;//必须
    //从user对象中获取userid
    private Long user_id;//作者id
    private Long content_id;//内容id
    private String title;//必填
    private String des;//描述
    private Date create_time;//创建日期
    private BigInteger like;//喜欢
    private BigInteger keep;//收藏
    private int tid;//文章种类
    private int pid;//文章属性类
    private String article_img;//文章缩略图
    private String article_author;

    //关联对象
    private Like_keep like_keep;
    private List<Img> articleImgList;
    private Content content;
    private List<CommentMain> commentMains;
    private User user;

    public Article(){}

    public Article(Long article_id, Long user_id, Long content_id, String title, String des, Date create_time, BigInteger like, BigInteger keep, int pid,int tid, String article_img, String article_author, List<Img> articleImgList, Content content, List<CommentMain> commentMains, User user) {
        this.article_id = article_id;
        this.user_id = user_id;
        this.content_id = content_id;
        this.title = title;
        this.des = des;
        this.create_time = create_time;
        this.like = like;
        this.keep = keep;
        this.tid = tid;
        this.pid = pid;
        this.article_img = article_img;
        this.article_author = article_author;
        this.articleImgList = articleImgList;
        this.content = content;
        this.commentMains = commentMains;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", user_id=" + user_id +
                ", content_id=" + content_id +
                ", title='" + title + '\'' +
                ", des='" + des + '\'' +
                ", create_time=" + create_time +
                ", like=" + like +
                ", keep=" + keep +
                ", pid=" + pid +
                ", tid=" + tid +
                ", article_img='" + article_img + '\'' +
                ", article_author='" + article_author + '\'' +
                ", articleImgList=" + articleImgList +
                ", content=" + content +
                ", commentMains=" + commentMains +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getContent_id() {
        return content_id;
    }

    public void setContent_id(Long content_id) {
        this.content_id = content_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getArticle_img() {
        return article_img;
    }

    public BigInteger getLike() {
        return like;
    }

    public void setLike(BigInteger like) {
        this.like = like;
    }

    public BigInteger getKeep() {
        return keep;
    }

    public void setKeep(BigInteger keep) {
        this.keep = keep;
    }

    public void setArticle_img(String article_img) {
        this.article_img = article_img;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public List<Img> getArticleImgList() {
        return articleImgList;
    }

    public void setArticleImgList(List<Img> articleImgList) {
        this.articleImgList = articleImgList;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<CommentMain> getCommentMains() {
        return commentMains;
    }

    public void setCommentMains(List<CommentMain> commentMains) {
        this.commentMains = commentMains;
    }

    public Like_keep getLike_keep() {
        return like_keep;
    }

    public void setLike_keep(Like_keep like_keep) {
        this.like_keep = like_keep;
    }
}
