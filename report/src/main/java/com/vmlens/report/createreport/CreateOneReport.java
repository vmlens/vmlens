package com.vmlens.report.createreport;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.vmlens.report.dominatortree.UIDominatorTreeElement;
import com.vmlens.report.dominatortree.UIReverseCallTree;
import com.vmlens.report.overview.UITestLoopOrWarning;
import com.vmlens.report.stacktrace.UIStacktraceElement;
import com.vmlens.report.summary.UISummaryElement;
import com.vmlens.report.trace.UIRunElement;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public void createDominatorTree(List<UIDominatorTreeElement> uiElements,  String runName,Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("runName", runName);
        context.put("elements", uiElements);
        template.execute(writer, context);
    }

    public void createReverseCallTree(List<UIReverseCallTree> uiElements, String runName, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("runName", runName);
        context.put("elements", uiElements);
        template.execute(writer, context);
    }

    public void create( Map<String, Object> context, Path file) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(file));
        template.execute(writer, context);
        writer.close();
    }

    
}
