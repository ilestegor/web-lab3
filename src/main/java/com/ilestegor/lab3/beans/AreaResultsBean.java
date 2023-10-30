package com.ilestegor.lab3.beans;

import com.ilestegor.lab3.db.DAOFactory;
import com.ilestegor.lab3.utils.AreaChecker;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.DecimalFormat;
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
    private DecimalFormat decimalFormat;

    @PostConstruct
    private void initialize() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        decimalFormat = new DecimalFormat("#.###");
        curResult = new LinkedList<>();
    }

    public void addNewResult() {
        long startExec = System.nanoTime();
        ResultBean newResult = new ResultBean();
        newResult.setHitType(AreaChecker.checkArea(resultBean.getX(), resultBean.getY(), resultBean.getR()));
        long endExec = System.nanoTime();
        long executionTime = endExec - startExec;
        newResult.setExecutionTime(executionTime);
        String requestTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        newResult.setCurrentTime(requestTime);
        newResult.setX(Double.parseDouble(decimalFormat.format(resultBean.getX())));
        newResult.setY(Double.parseDouble(decimalFormat.format(resultBean.getY())));
        newResult.setR(Double.parseDouble(decimalFormat.format(resultBean.getR())));
        DAOFactory.getDaoFactory().getDao().addResult(newResult);
        curResult.add(newResult);
    }
    public void clearResult(){
        curResult.clear();
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
