package com.ilestegor.lab3.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.LinkedList;

@Named
@ApplicationScoped
public class AreaResultsBean implements Serializable {
    @Inject
    private ResultBean resultBean;
    private LinkedList<ResultBean> curResult;


    @PostConstruct
    private void initialize(){
        curResult = new LinkedList<>();
    }

    public void addNewResult(){
        ResultBean newResult = new ResultBean(resultBean);
        curResult.addLast(newResult);
    }


    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

    public LinkedList<ResultBean> getCurResult() {
        return curResult;
    }

    public void setCurResult(LinkedList<ResultBean> curResult) {
        this.curResult = curResult;
    }
}
