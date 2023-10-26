package com.ilestegor.lab3.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@Named
@ApplicationScoped
public class AreaResultsBean implements Serializable {
    @Inject
    private ResultBean resultBean;
    private LinkedList<ResultBean> curResult;
    private SimpleDateFormat simpleDateFormat;


    @PostConstruct
    private void initialize() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        curResult = new LinkedList<>();
    }

    public void addNewResult() {
        long startExec  = System.nanoTime();
        ResultBean newResult = new ResultBean(resultBean);
        long endExec = System.nanoTime();
        long executionTime = endExec - startExec;
        newResult.setExecutionTime(executionTime);
        String requestTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        newResult.setCurrentTime(requestTime);
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
