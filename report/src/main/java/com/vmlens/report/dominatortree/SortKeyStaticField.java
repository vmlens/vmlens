package com.vmlens.report.dominatortree;

import java.util.Objects;

public class SortKeyStaticField implements UIStateElementSortKey {



    public SortKeyStaticField() {

    }

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
        return 45;
    }
}
