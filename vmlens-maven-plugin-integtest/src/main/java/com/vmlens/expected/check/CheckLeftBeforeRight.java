package com.vmlens.expected.check;


import com.vmlens.expected.domain.TestCase;
import com.vmlens.expected.domain.TestCaseCollection;
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRight;

import com.vmlens.trace.agent.bootstrap.description.*;
import com.vmlens.report.assertion.LeftBeforeRight;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class CheckLeftBeforeRight implements OnDescriptionAndLeftBeforeRight, ThreadOrLoopDescriptionVisitor {

    private final TestCaseCollection testCaseCollection;
    private final  Map<String,Integer> loopNameToId;
    private final Map<Integer, List<LeftBeforeRight>> map = new HashMap<>();
    private List<LeftBeforeRight> currentList;

    public CheckLeftBeforeRight(TestCaseCollection testCaseCollection,
                                Map<String, Integer> loopNameToId) {
        this.testCaseCollection = testCaseCollection;
        this.loopNameToId = loopNameToId;
    }

    @Override
    public void startRun(int loopId,int runId) {
        if( !map.containsKey(loopId)) {
            map.put(loopId,new LinkedList<>());
        }
        currentList = map.get(loopId);
    }

    @Override
    public void onLeftBeforeRight(LeftBeforeRight leftBeforeRight) {
        currentList.add(leftBeforeRight);
    }




    @Override
    public void onThreadOrLoopDescription(ThreadOrLoopDescription threadOrLoopDescription) {
        threadOrLoopDescription.accept(this);
    }

    @Override
    public void visit(ThreadDescription threadDescription) {

    }

    @Override
    public void visit(TestLoopDescription testLoopDescription) {
        TestCase testCase = testCaseCollection.get(testLoopDescription.name());
        List<LeftBeforeRight> list = map.get(testLoopDescription.loopId());
        for(LeftBeforeRight leftBeforeRight : list) {
            testCase.found(leftBeforeRight);
        }
        testCase.check(testLoopDescription.name());
        loopNameToId.put(testLoopDescription.name(),testLoopDescription.loopId());
    }

    @Override
    public void onClassDescription(ClassDescription classDescription) {

    }
}
