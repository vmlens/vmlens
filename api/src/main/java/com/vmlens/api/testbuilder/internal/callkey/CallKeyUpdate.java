package com.vmlens.api.testbuilder.internal.callkey;

import java.util.Objects;

import static com.vmlens.api.testbuilder.internal.AutomaticTestTypes.READ;
import static com.vmlens.api.testbuilder.internal.AutomaticTestTypes.WRITE;
import static com.vmlens.api.testbuilder.internal.wrapper.CreateLabel.createLabel;

public class CallKeyUpdate implements CallKey  {

    private final int position;

    public CallKeyUpdate(int position) {
        this.position = position;
    }

    @Override
    public String getLabel() {
        return createLabel("addUpdate" , position);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CallKeyUpdate that = (CallKeyUpdate) o;
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
