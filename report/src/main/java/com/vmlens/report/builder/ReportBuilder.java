package com.vmlens.report.builder;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.TestLoopDescription;
import com.anarsoft.trace.agent.description.ThreadDescription;
import com.vmlens.report.element.FieldContainer;
import com.vmlens.report.element.LoopRunAndThreadIndex;
import com.vmlens.report.element.MethodContainer;

import java.util.HashMap;
import java.util.Map;



public class ReportBuilder {

    private final Map<Integer, FieldContainer> idToFieldContainer = new HashMap<>();
    private final Map<Integer, MethodContainer> idToMethodContainer = new HashMap<>();
    private final Map<LoopRunAndThreadIndex, ThreadDescription> indexToThreadDescription = new HashMap<>();
    private final Map<Integer, TestLoopDescription> idToTestLoopDescription = new HashMap<>();


    public void addClassDescription(ClassDescription classDescription) {

    }


}
