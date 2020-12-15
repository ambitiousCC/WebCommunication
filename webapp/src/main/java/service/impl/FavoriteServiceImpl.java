package service.impl;

import beans.Like_keep;
import dao.FavoriteDAO;
import dao.Impl.FavoriteDAOImpl;
import service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
    @Override
    public boolean isLike(Long article_id, Long user_id) {
        return favoriteDAO.isLikeFindByAU(article_id, user_id)!=null;
    }
    @Override
    public boolean isKeep(Long article_id, Long user_id) {
        return favoriteDAO.isKeepFindByAU(article_id, user_id)!=null;
    }

    /**
     * 判断主评论是否被赞
     * @param CMid
     * @param user_id
     * @return
     */
    @Override
    public boolean isCMLike(Long CMid, Long user_id) {
        return favoriteDAO.isLikeFindByCMU(CMid, user_id)!=null;
    }
    /**
     * 判断主评论是否被赞
     * @param Cid
     * @param user_id
     * @return
     */
    @Override
    public boolean isCLike(Long Cid, Long user_id) {
        return favoriteDAO.isLikeFindByCU(Cid, user_id)!=null;
    }
}
