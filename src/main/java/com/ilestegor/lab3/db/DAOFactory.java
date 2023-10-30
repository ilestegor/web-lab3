package com.ilestegor.lab3.db;

import com.ilestegor.lab3.beans.ResultBean;

public class DAOFactory {
    private static DAO<ResultBean> dao;
    private static DAOFactory daoFactory;

    public static DAOFactory getDaoFactory(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public DAO<ResultBean> getDao(){
        if (dao == null){
            dao = new ResultsDaoImpl();
        }
        return dao;
    }
}
