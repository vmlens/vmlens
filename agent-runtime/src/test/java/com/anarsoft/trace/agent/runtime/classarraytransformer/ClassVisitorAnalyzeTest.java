package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.ApplyAfterOperationMethodCallTarget;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlanBuilder;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.PlanElement;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassVisitorAnalyzeTest {

    @Test
    public void threadStart() throws IOException {
        // Expected
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();
        int calledMethodId = methodCallIdMap.asInt(new MethodCallId("java/lang/Thread", "start", "()V"));

        MethodId expectedMethodId = new MethodId(1, "call", "()V", null, null);
        TLinkedList<TLinkableWrapper<PlanElement>> expectedPlanElementList = new TLinkedList<>();
        PlanElement transformed = new PlanElement();
        transformed.addApplyAfterOperation(new ApplyAfterOperationMethodCallTarget(calledMethodId));
        expectedPlanElementList.add(TLinkableWrapper.<PlanElement>wrap(transformed));
        expectedPlanElementList.add(TLinkableWrapper.<PlanElement>wrap(new PlanElement()));

        // Given

        THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan =
                new THashMap<>();

        // When
        analyze(methodIdToPlan, methodCallIdMap, "com.vmlens.test.guineaPig.ThreadStart");

        // Then
        assertThat(methodIdToPlan.contains(expectedMethodId), is(true));
        MethodTransformPlanBuilder planBuilder = methodIdToPlan.get(expectedMethodId);
        TLinkedList<TLinkableWrapper<PlanElement>> actual = planBuilder.planElementList();
        assertThat(actual, is(expectedPlanElementList));
    }

    private void analyze(THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan,
                         MethodCallIdMap methodCallIdMap, String className) throws IOException {
        byte[] classArray = new LoadClassArray().load(className);
        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodIdToPlan, methodCallIdMap);
        ClassReader reader = new ClassReader(classArray);
        reader.accept(classVisitorAnalyze, 0);
    }


}