package com.vmlens.report.dominatortree;

public class SortKeyAtomicObject implements UIStateElementSortKey {

    private final int objectId;
    private final int atomicMethodId;

    public SortKeyAtomicObject(int objectId, int atomicMethodId) {
        this.objectId = objectId;
        this.atomicMethodId = atomicMethodId;
    }

    @Override
    public String idLabel() {
        return "(" +  objectId +  ")";
    }
}
