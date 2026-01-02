package com.vmlens.report.stacktrace;

import java.util.Objects;

public class UIStacktraceElement {

    private final String text;

    public UIStacktraceElement(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UIStacktraceElement that = (UIStacktraceElement) o;

        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
