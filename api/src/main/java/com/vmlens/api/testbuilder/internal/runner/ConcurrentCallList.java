package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;

import java.util.List;

public class ConcurrentCallList<CLASS_UNDER_TEST> {

    private final List<ConcurrentCall<CLASS_UNDER_TEST>>  concurrentCallList;

    public ConcurrentCallList(List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList) {
        this.concurrentCallList = concurrentCallList;
    }

    public List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList() {
        return concurrentCallList;
    }
}
