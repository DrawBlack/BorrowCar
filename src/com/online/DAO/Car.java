package com.online.DAO;

import com.online.Model.CarVO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by macbookpro on 2016/12/2.
 *
 */


public interface Car {

    boolean addCar(CarVO o) throws SQLException;

    boolean deleteCar(int id) throws SQLException;

    boolean updateCar(CarVO o , int id ) throws  SQLException;

    CarVO selectCar(int id) throws SQLException;

    List<CarVO> selectAllCar() throws SQLException;

    List<CarVO> selectManyCar(String sql) throws SQLException;
}
