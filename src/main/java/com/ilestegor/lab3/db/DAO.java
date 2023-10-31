package com.ilestegor.lab3.db;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao interface which describes methods for communicating with database
 *
 * @param <T>
 */
public interface DAO<T> {
    void addResult(T t) throws SQLException;

    void clearResults() throws SQLException;

    List<T> getAllResults() throws SQLException;
}
