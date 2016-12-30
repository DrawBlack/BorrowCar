package com.online.DAO;

import com.online.Model.NewsVO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by macbookpro on 2016/12/2.
 *
 */
public interface News {

    boolean addNews(NewsVO o) throws SQLException;

    boolean deleteNews(int id) throws SQLException;

    boolean updateNews(NewsVO newsVO , int id) throws SQLException;

    NewsVO selectNews(int id) throws SQLException;

    List<NewsVO> selectManyNewsVO(String sql) throws SQLException;

    List<NewsVO> selectAllNewsVO() throws SQLException;

}
