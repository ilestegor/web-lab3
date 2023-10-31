package com.ilestegor.lab3.db;

import com.ilestegor.lab3.beans.ResultBean;
import com.ilestegor.lab3.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of DAO interface
 */
public class ResultsDaoImpl implements DAO<ResultBean> {
    private static final String TABLE = "result";

    /**
     * Adds new instance of ResultBean to database
     *
     * @param resultBean accepts result bean instance
     * @throws SQLException
     */
    @Override
    public void addResult(ResultBean resultBean) throws SQLException {
        Session session = null;
        Transaction transaction;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            transaction = session.getTransaction();
            session.beginTransaction();
            session.persist(resultBean);
            transaction.commit();
        } catch (Exception ex) {
            throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    /**
     * Clear whole table
     * @throws SQLException
     */
    @Override
    public void clearResults() throws SQLException {
        Session session = null;
        Transaction transaction;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            transaction = session.getTransaction();
            session.beginTransaction();
            String deleteTable = "delete from " + TABLE;
            session.createNativeQuery(deleteTable).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Fetching all data from table
     * @return List of result bean objects
     * @throws SQLException
     */
    @Override
    public List<ResultBean> getAllResults() throws SQLException {
        Session session = null;
        List<ResultBean> res;
        try {
            session = HibernateUtils.buildSessionFactory().openSession();
            CriteriaQuery<ResultBean> criteriaBuilder = session.getCriteriaBuilder().createQuery(ResultBean.class);
            criteriaBuilder.from(ResultBean.class);
            res = session.createQuery(criteriaBuilder).getResultList();
        } catch (Exception ex) {
            throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }
}
