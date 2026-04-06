package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.callkey.CallKey;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CallKeyListSet {

    private final Set<List<CallKey>> callKeySet = new HashSet<>();

    public void add(List<CallKey> callKeys) {
        callKeySet.add(callKeys);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CallKeyListSet that = (CallKeyListSet) o;
        return Objects.equals(callKeySet, that.callKeySet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(callKeySet);
    }
}
