package dao;

import beans.Keeps;
import beans.Likes;

public interface FavoriteDAO {
    /**
     * 是否点赞
     * @param article_id
     * @param user_id
     * @return
     */
    Likes isLikeFindByAU(Long article_id, Long user_id);

    /**
     * 是否收藏
     * @param article_id
     * @param user_id
     * @return
     */
    Keeps isKeepFindByAU(Long article_id, Long user_id);

    /**
     * 判断主评论是否被赞
     * @param CMid
     * @param user_id
     * @return
     */
    Likes isLikeFindByCMU(Long CMid,Long user_id);
    /**
     * 判断副评论是否被赞
     * @param Cid
     * @param user_id
     * @return
     */
    Likes isLikeFindByCU(Long Cid,Long user_id);
}
