package com.anarsoft.trace.agent.runtime.classtransformerall;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethodcall.MethodCallAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethodcall.MethodCallFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.plan.ApplyAfterOperationMethodCallTarget;
import com.anarsoft.trace.agent.runtime.classtransformerall.plan.MethodTransformPlanBuilder;
import com.anarsoft.trace.agent.runtime.classtransformerall.plan.PlanElement;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassVisitorAnalyzeForTransformTest {


    @Test
    public void threadStart() throws IOException {
        // Expected
        MethodCallIdMap methodCallIdMap = new MethodRepository();
        int calledMethodId = methodCallIdMap.asInt(new MethodCallId("java/lang/Thread", "start", "()V"));

        MethodId expectedMethodId = new MethodId(1, "call", "()V", null, null);
        TLinkedList<TLinkableWrapper<PlanElement>> expectedPlanElementList = new TLinkedList<>();
        PlanElement transformed = new PlanElement();
        transformed.addApplyAfterOperation(new ApplyAfterOperationMethodCallTarget(calledMethodId));
        expectedPlanElementList.add(TLinkableWrapper.<PlanElement>wrap(transformed));
        expectedPlanElementList.add(TLinkableWrapper.<PlanElement>wrap(new PlanElement()));

        // Given
        MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory = new MethodVisitorAnalyzeAndTransformFactoryMap();

        // When
        analyze(methodIdToFactory, methodCallIdMap, "com.vmlens.test.guineaPig.ThreadStart");

        // Then
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> result =
                methodIdToFactory.get(expectedMethodId);
        MethodCallAnalyzeAndTransformFactory factory = (MethodCallAnalyzeAndTransformFactory) result.get(0).element;
        MethodTransformPlanBuilder planBuilder = factory.methodTransformPlanBuilder();
        TLinkedList<TLinkableWrapper<PlanElement>> actual = planBuilder.planElementList();
        assertThat(actual, is(expectedPlanElementList));
    }

    private void analyze(MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                         MethodCallIdMap methodCallIdMap, String className) throws IOException {
        byte[] classArray = new LoadClassArray().load(className);


        MethodCallFactoryFactory methodCallFactoryFactory = new MethodCallFactoryFactory();

        TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>> analyzeList = new TLinkedList<>();
        analyzeList.add(wrap(methodCallFactoryFactory));

        ClassVisitorAnalyzeForTransform classVisitorForTransform = new ClassVisitorAnalyzeForTransform(methodCallIdMap,
                className, analyzeList, methodIdToFactory, null);
        ClassReader reader = new ClassReader(classArray);
        reader.accept(classVisitorForTransform, 0);
    }


}