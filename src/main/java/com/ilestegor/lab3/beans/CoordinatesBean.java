package com.ilestegor.lab3.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Deprecated
@Entity
@Table(name = "web_coordinates")
@Named
@ApplicationScoped
public class CoordinatesBean implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "x", nullable = false)
    private Double x;
    @Column(name = "y", nullable = false)
    private Double y;
    @Column(name = "r", nullable = false)
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
