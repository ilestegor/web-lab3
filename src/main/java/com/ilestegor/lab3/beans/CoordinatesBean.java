package com.ilestegor.lab3.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ApplicationScoped
public class CoordinatesBean implements Serializable {
    private Long id;
    private Double x;
    private Double y;
    private Double r;

    public CoordinatesBean() {
    }

    public CoordinatesBean(CoordinatesBean coordinatesBean) {
        this.x = coordinatesBean.getX();
        this.y = coordinatesBean.getY();
        this.r = coordinatesBean.getR();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
}
