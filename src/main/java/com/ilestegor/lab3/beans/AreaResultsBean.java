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
import java.util.LinkedList;

/**
 * Class for managing data from bean, adding, clearing and fetching data from database
 */
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
        decimalFormat = new DecimalFormat("#.#####");
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
        newResult.setX(Double.parseDouble(decimalFormat.format(resultBean.getX()).replace(",", ".")));
        newResult.setY(Double.parseDouble(decimalFormat.format(resultBean.getY()).replace(",", ".")));
        newResult.setR(Double.parseDouble(decimalFormat.format(resultBean.getR()).replace(",", ".")));
        if (curResult != null && curResult.add(newResult)) {
            try {
                DAOFactory.getDaoFactory().getDao().addResult(newResult);
            } catch (SQLException e) {
                System.out.println("Something went wrong while adding new result");
            }
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

    public LinkedList<ResultBean> getCurResult() {
        return curResult;
    }

    public void setCurResult(LinkedList<ResultBean> curResult) {
        this.curResult = curResult;
    }
}
