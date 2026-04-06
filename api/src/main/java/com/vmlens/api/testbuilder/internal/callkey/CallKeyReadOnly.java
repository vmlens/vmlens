package com.vmlens.api.testbuilder.internal.callkey;

import java.util.Objects;

import static com.vmlens.api.testbuilder.internal.AutomaticTestTypes.READ;
import static com.vmlens.api.testbuilder.internal.wrapper.CreateLabel.createLabel;

public class CallKeyReadOnly implements CallKey {

    private final int position;

    public CallKeyReadOnly(int position) {
        this.position = position;
    }

    @Override
    public String getLabel() {
        return createLabel("addReadOnly" , position);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CallKeyReadOnly that = (CallKeyReadOnly) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public int automaticTestType() {
        return READ;
    }
}
