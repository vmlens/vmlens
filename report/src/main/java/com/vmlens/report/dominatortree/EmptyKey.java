package com.vmlens.report.dominatortree;

public class EmptyKey implements UIStateElementSortKey{
    @Override
    public String idLabel() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 908;
    }

}
