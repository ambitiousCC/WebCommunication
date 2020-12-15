package beans;

public class Like_keep {
    private boolean like;
    private boolean keep;

    public Like_keep() {
    }

    public Like_keep(boolean like, boolean keep) {
        this.like = like;
        this.keep = keep;
    }

    @Override
    public String toString() {
        return "Like_keep{" +
                "like=" + like +
                ", keep=" + keep +
                '}';
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }
}
