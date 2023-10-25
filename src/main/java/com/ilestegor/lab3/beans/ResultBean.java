package com.ilestegor.lab3.beans;

import com.ilestegor.lab3.utils.HitType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ApplicationScoped
public class ResultBean implements Serializable {
    @Inject
    private CoordinatesBean coordinatesBean;
    private String currentTime;
    private String executionTime;
    private HitType hitType;

    public ResultBean() {
    }

    public ResultBean(ResultBean resultBean){
        coordinatesBean = resultBean.getCoordinates();
        CoordinatesBean curCords = new CoordinatesBean(coordinatesBean);
        resultBean.setCoordinates(curCords);
    }

    public CoordinatesBean getCoordinates() {
        return coordinatesBean;
    }

    public void setCoordinates(CoordinatesBean coordinatesBean) {
        this.coordinatesBean = coordinatesBean;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public HitType getHitType() {
        return hitType;
    }

    public void setHitType(HitType hitType) {
        this.hitType = hitType;
    }
}
