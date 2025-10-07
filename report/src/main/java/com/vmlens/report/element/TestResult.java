package com.vmlens.report.element;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.uielement.UIWarning;

import java.util.LinkedList;
import java.util.List;

public class TestResult {
    
    private String text;
    private final List<LargeWarning> largeWarningList = new LinkedList<>();
    
    public TestResult(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public void addSmallWarningText(String warning) {
        text += ", " +  warning;
    }

    public void addLargeWarning(LargeWarning largeWarning) {
        largeWarningList.add(largeWarning);
    }

    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        for(LargeWarning warning : largeWarningList) {
            warning.addToNeedsDescription(callback);
        }
    }

    public List<UIWarning> toUIWarningList(DescriptionContext descriptionContext) {
        List<UIWarning> result = new LinkedList<>();
        for(LargeWarning warning : largeWarningList) {
            result.add(warning.toUIWarning(descriptionContext));
        }
        return result;
    }


}
