package beans;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class SetArt {

    private List<Article> articles;

    public SetArt() {
    }

    public SetArt(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
