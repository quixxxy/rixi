package com.rixi.rest.model;

public class UserStatistic {

    private String[] labels;
    private String[] series;
    private int[][] data;

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String[] getSeries() {
        return series;
    }

    public void setSeries(String[] series) {
        this.series = series;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }
}
