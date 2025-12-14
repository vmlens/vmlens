package com.vmlens.report.createreport;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.vmlens.report.output.UIRunElement;
import com.vmlens.report.output.UIStacktraceElement;
import com.vmlens.report.output.UISummaryElement;
import com.vmlens.report.output.UITestLoopOrWarning;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOneReport {

    private final Mustache template;

    public CreateOneReport(String templateName) {
        this.template = new DefaultMustacheFactory()
                .compile("templates/" + templateName + ".mustache");
    }

    public void createUITestLoop(List<UITestLoopOrWarning> uiElements, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("loops", uiElements);
        template.execute(writer, context);
    }

    public void createUIRun(List<UIRunElement> uiElements, String runName, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("runName", runName);
        context.put("elements", uiElements);
        template.execute(writer, context);
    }

    public void createUISummary(List<UISummaryElement> uiElements, String runName, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("runName", runName);
        context.put("elements", uiElements);
        template.execute(writer, context);
    }

    public void createUIStacktraceElement(List<UIStacktraceElement> uiElements, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("elements", uiElements);
        template.execute(writer, context);
    }
}
