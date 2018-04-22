/*
 * mark urun 4/21/2018
 *
 * */
package com.murun.fstats.model;

public class Host {

    private String hostName;

    private double min;
    private double max;
    private double average;

    public Host(String hostName, double min, double max, double average) {
        this.hostName = hostName;

        this.min = min;
        this.max = max;
        this.average = average;
    }

    public String getHostName() {
        return hostName;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostName='" + hostName + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", average=" + average +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Host host = (Host) o;

        return getHostName().equals(host.getHostName());
    }

    @Override
    public int hashCode() {
        return getHostName().hashCode();
    }
}
