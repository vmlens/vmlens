package com.vmlens.report.createreport;

public class CopyDescription {
    private String path;
    private String dir;
    private String name;

    @Override
    public String toString() {
        return "CopyDescription{" +
                "path='" + path + '\'' +
                ", dir='" + dir + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String path() {
        return path;
    }

    public String dir() {
        return dir;
    }

    public String name() {
        return name;
    }
}
