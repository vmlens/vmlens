package com.vmlens.report.createreport;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.vmlens.report.uielement.UIRunElement;
import com.vmlens.report.uielement.UIStacktraceElement;
import com.vmlens.report.uielement.UITestLoop;

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

    public void createUITestLoop(List<UITestLoop> uiElements, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("loops", uiElements);
        template.execute(writer, context);
    }

    public void createUIRun(List<UIRunElement> uiElements, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("elements", uiElements);
        template.execute(writer, context);
    }


    public void createUIStacktraceElement(List<UIStacktraceElement> uiElements, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("elements", uiElements);
        template.execute(writer, context);
    }

}
