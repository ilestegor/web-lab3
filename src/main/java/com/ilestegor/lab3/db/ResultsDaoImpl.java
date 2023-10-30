package com.ilestegor.lab3.db;

import com.ilestegor.lab3.beans.ResultBean;
import com.ilestegor.lab3.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ResultsDaoImpl implements DAO<ResultBean>{
    @Override
    public void addResult(ResultBean resultBean) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            transaction = session.getTransaction();
            session.beginTransaction();
            session.persist(resultBean);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void clearResults(ResultBean resultBean) {

    }

    @Override
    public void getAllResults(ResultBean resultBean) {

    }
}
