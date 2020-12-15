package service;

import beans.Like_keep;

public interface FavoriteService {
    /**
     * 判断是否点赞
     * @param article_id
     * @param user_id
     * @return
     */
    boolean isLike(Long article_id, Long user_id);

    /**
     * 判断是否收藏
     * @param article_id
     * @param user_id
     * @return
     */
    boolean isKeep(Long article_id, Long user_id);

    /**
     * 判断主评论是否被赞
     * @param CMid
     * @param user_id
     * @return
     */
    boolean isCMLike(Long CMid, Long user_id);
    /**
     * 判断副评论是否被赞
     * @param Cid
     * @param user_id
     * @return
     */
    boolean isCLike(Long Cid, Long user_id);
}
