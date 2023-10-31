package com.ilestegor.lab3.db;

import com.ilestegor.lab3.beans.ResultBean;

/**
 * Factory utility class for getting dao instance
 */
public class DAOFactory {
    private DAO<ResultBean> dao;
    private static DAOFactory daoFactory;

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public DAO<ResultBean> getDao() {
        if (dao == null) {
            dao = new ResultsDaoImpl();
        }
        return dao;
    }
}
