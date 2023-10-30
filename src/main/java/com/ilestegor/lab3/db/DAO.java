package com.ilestegor.lab3.db;

public interface DAO<T>{
    void addResult(T t);
    void clearResults(T t);
    void getAllResults(T t);
}
