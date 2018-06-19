package com.example.shenbagampalanisamy.linechart2;

import java.util.ArrayList;

public class BeanClass {
    public int width;
    public int height;
    public int xaxies;
    public int yaxies;
    public ArrayList Xaxis = new ArrayList();
    public ArrayList Yaxis = new ArrayList();

    public BeanClass(int width, int height, int xaxies, int yaxies, ArrayList xaxis, ArrayList yaxis) {
        this.width = width;
        this.height = height;
        this.xaxies = xaxies;
        this.yaxies = yaxies;
        Xaxis = xaxis;
        Yaxis = yaxis;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getXaxies() {
        return xaxies;
    }

    public void setXaxies(int xaxies) {
        this.xaxies = xaxies;
    }

    public int getYaxies() {
        return yaxies;
    }

    public void setYaxies(int yaxies) {
        this.yaxies = yaxies;
    }

    public ArrayList getXaxis() {
        return Xaxis;
    }

    public void setXaxis(ArrayList xaxis) {
        Xaxis = xaxis;
    }

    public ArrayList getYaxis() {
        return Yaxis;
    }

    public void setYaxis(ArrayList yaxis) {
        Yaxis = yaxis;
    }
}
