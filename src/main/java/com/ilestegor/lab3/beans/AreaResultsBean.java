package com.ilestegor.lab3.beans;

import com.ilestegor.lab3.db.DAOFactory;
import com.ilestegor.lab3.utils.AreaChecker;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for managing data from bean, adding, clearing and fetching data from database
 */
@Named
@ApplicationScoped
public class AreaResultsBean implements Serializable {
    @Inject
    private ResultBean resultBean;
    private List<ResultBean> curResult;
    private SimpleDateFormat simpleDateFormat;
    private final short NUMBER_OF_DECIMAL_PLACE = 3;
    private final double PRECISION = Math.pow(10, NUMBER_OF_DECIMAL_PLACE);

    @PostConstruct
    private void initialize() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        curResult = new LinkedList<>();
        try {
            curResult = new LinkedList<>(DAOFactory.getDaoFactory().getDao().getAllResults());
        } catch (SQLException ex) {
            System.out.println("Something went wrong while fetching the data");
        }
    }

    /**
     * Adds new result to local collection and to database
     */
    public void addNewResult() {
        long startExec = System.nanoTime();
        ResultBean newResult = new ResultBean();
        newResult.setHitType(AreaChecker.checkArea(resultBean.getX(), resultBean.getY(), resultBean.getR()));
        long endExec = System.nanoTime();
        long executionTime = endExec - startExec;
        newResult.setExecutionTime(executionTime);
        String requestTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        newResult.setCurrentTime(requestTime);
        newResult.setX((resultBean.getX() * PRECISION) / PRECISION);
        newResult.setY((resultBean.getY() * PRECISION) / PRECISION);
        newResult.setR((resultBean.getR() * PRECISION) / PRECISION);
        curResult.add(newResult);
        try {
            DAOFactory.getDaoFactory().getDao().addResult(newResult);
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding new result");
        }
    }

    /**
     * Clear local collection and table im database
     */
    public void clearResult() {
        if (curResult != null) {
            curResult.clear();
        }
        try {
            DAOFactory.getDaoFactory().getDao().clearResults();
        } catch (SQLException e) {
            System.out.println("Something went wrong while clearing table");
        }
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

    public List<ResultBean> getCurResult() {
        return curResult;
    }

    public void setCurResult(LinkedList<ResultBean> curResult) {
        this.curResult = curResult;
    }
}
