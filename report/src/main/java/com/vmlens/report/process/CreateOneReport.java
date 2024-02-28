package com.vmlens.report.process;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;

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

    public void create(List uiElements, Writer writer) {
        Map<String, Object> context = new HashMap<>();
        context.put("loops", uiElements);
        template.execute(writer, context);
    }

}
