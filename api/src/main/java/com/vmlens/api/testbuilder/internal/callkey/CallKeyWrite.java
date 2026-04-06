package com.vmlens.api.testbuilder.internal.callkey;

import java.util.Objects;

import static com.vmlens.api.testbuilder.internal.AutomaticTestTypes.WRITE;
import static com.vmlens.api.testbuilder.internal.wrapper.CreateLabel.createLabel;

public class CallKeyWrite implements CallKey {

    private final int position;

    public CallKeyWrite(int position) {
        this.position = position;
    }

    @Override
    public String getLabel() {
        return createLabel("addWrite" , position);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CallKeyWrite that = (CallKeyWrite) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public int automaticTestType() {
        return WRITE;
    }
}
