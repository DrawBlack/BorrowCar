package com.online.DAO;

import com.online.Model.CommentVO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by macbookpro on 2016/12/6.
 */
public interface Comment {

    boolean insertComment(CommentVO commentVO) throws SQLException;

    boolean deleteComment(String username , int carID) throws SQLException;

    boolean updateComment(String username , int carID , CommentVO commentVO) throws SQLException;

    CommentVO selectComment(String username , int carID) throws SQLException;

    List<CommentVO> selectManyComment(String sql) throws SQLException;

    List<CommentVO> selecfAllComment() throws SQLException;
}
