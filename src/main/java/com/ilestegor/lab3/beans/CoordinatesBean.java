package com.ilestegor.lab3.beans;

import java.io.Serializable;

@Deprecated
public class CoordinatesBean implements Serializable {

    private Long id;
    private Double x;
    private Double y;
    private Double r;

    public CoordinatesBean() {
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
