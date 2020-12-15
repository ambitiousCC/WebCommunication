package dao.Impl;

import beans.Keeps;
import beans.Likes;
import dao.FavoriteDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import web.utils.JDBCUtilsPlus;

public class FavoriteDAOImpl implements FavoriteDAO {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtilsPlus.getDataSource());

    @Override
    public Likes isLikeFindByAU(Long article_id, Long user_id) {
        Likes likes = null;
        try {
            String sql = "SELECT * FROM art_likes WHERE article_id=? AND user_id=? ";
            likes = template.queryForObject(sql, new BeanPropertyRowMapper<Likes>(Likes.class),article_id, user_id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return likes;
    }
    @Override
    public Keeps isKeepFindByAU(Long article_id, Long user_id) {
        Keeps keeps = null;
        try {
            String sql = "SELECT * FROM art_keeps WHERE article_id=? AND user_id=? ";
            keeps = template.queryForObject(sql, new BeanPropertyRowMapper<Keeps>(Keeps.class),article_id, user_id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return keeps;
    }

    @Override
    public Likes isLikeFindByCMU(Long CMid,Long user_id) {
        Likes likes = null;
        try {
            String sql = "SELECT * FROM art_comment_main_likes WHERE comment_main_id=? AND user_id=? ";
            likes = template.queryForObject(sql, new BeanPropertyRowMapper<Likes>(Likes.class),CMid, user_id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return likes;
    }
    @Override
    public Likes isLikeFindByCU(Long Cid,Long user_id) {
        Likes likes = null;
        try {
            String sql = "SELECT * FROM art_comment_likes WHERE comment_id=? AND user_id=? ";
            likes = template.queryForObject(sql, new BeanPropertyRowMapper<Likes>(Likes.class),Cid, user_id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return likes;
    }
}
