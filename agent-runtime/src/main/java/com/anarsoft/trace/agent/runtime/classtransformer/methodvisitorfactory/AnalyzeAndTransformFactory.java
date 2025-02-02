package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

public class AnalyzeAndTransformFactory {

    private final AnalyzeFactory analyzeFactory;
    private final TransformFactory transformFactory;

    public AnalyzeAndTransformFactory(AnalyzeFactory analyzeFactory,
                                      TransformFactory transformFactory) {
        this.analyzeFactory = analyzeFactory;
        this.transformFactory = transformFactory;
    }

    public AnalyzeFactory analyzeFactory() {
        return analyzeFactory;
    }

    public TransformFactory transformFactory() {
        return transformFactory;
    }
}
