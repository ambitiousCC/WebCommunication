package beans;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class UserMsg extends CommentMain {
    private Long to_user_id;

    public UserMsg(Long to_user_id) {
        this.to_user_id = to_user_id;
    }

    public UserMsg(Long id, Long article_id, Long user_id, String comment, Date comment_main_time, String user_ico, String nickname, BigInteger like, List<Comment> commentList, Like_keep like_keep, Long to_user_id) {
        super(id, article_id, user_id, comment, comment_main_time, user_ico, nickname, like, commentList, like_keep);
        this.to_user_id = to_user_id;
    }

    public UserMsg() {

    }

    @Override
    public String toString() {
        return "UserMsg{" +
                "to_user_id=" + to_user_id +
                '}';
    }

    public Long getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Long to_user_id) {
        this.to_user_id = to_user_id;
    }
}
