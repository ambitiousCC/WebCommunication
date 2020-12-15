package beans;

public class Image extends Img {
    private int error;
    private String url;

    public Image() {
    }

    public Image(Long id, Long img_id, String bigPic, String smallPic, int error, String url) {
        super(id, img_id, bigPic, smallPic);
        this.error = error;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "error='" + error + '\'' +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
