package com.ilestegor.lab3.db;

import com.ilestegor.lab3.beans.ResultBean;

/**
 * Factory utility class for getting dao instance
 */
public class DAOFactory {
    private final DAO<ResultBean> dao = new ResultsDaoImpl();
    private static volatile DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        DAOFactory localInstance = daoFactory;
        if (localInstance == null){
            synchronized (DAOFactory.class){
                localInstance = daoFactory;
                if (localInstance == null){
                    daoFactory = localInstance = new DAOFactory();
                }
            }
        }
        return localInstance;
    }

    public DAO<ResultBean> getDao() {
        return dao;
    }
}
