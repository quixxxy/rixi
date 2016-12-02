package com.rixi.rest.model;

public class UserStatistic {

    private String[] labels;
    private String[] series;
    private long[][] data;

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

    public long[][] getData() {
        return data;
    }

    public void setData(long[][] data) {
        this.data = data;
    }
}
