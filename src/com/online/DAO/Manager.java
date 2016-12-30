package com.online.DAO;

import com.online.Model.ManagerVO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by macbookpro on 2016/12/4.
 *
 */
public interface Manager {

    boolean isHave(String name , String password) throws SQLException;

}
