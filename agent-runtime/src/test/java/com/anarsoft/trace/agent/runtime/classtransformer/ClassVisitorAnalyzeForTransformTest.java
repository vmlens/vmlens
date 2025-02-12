package com.anarsoft.trace.agent.runtime.classtransformer;

public class ClassVisitorAnalyzeForTransformTest {

/*
    @Test
    public void threadStart() throws IOException {
        // Expected
        MethodCallIdMap methodCallIdMap = new MethodRepository();
        int calledMethodId = methodCallIdMap.asInt(new MethodCallId("java/lang/Thread", "start", "()V"));

        int expectedMethodId = methodCallIdMap.asInt(
                new MethodCallId("com.vmlens.test.guineaPig.ThreadStart", "call", "()V"));
        TLinkedList<TLinkableWrapper<PlanElement>> expectedPlanElementList = new TLinkedList<>();
        PlanElement transformed = new PlanElement();
        transformed.addApplyAfterOperation(new ApplyAfterOperationMethodCallTarget(calledMethodId));
        expectedPlanElementList.add(TLinkableWrapper.wrap(transformed));
        expectedPlanElementList.add(TLinkableWrapper.wrap(new PlanElement()));

        // Given
        MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory = new MethodVisitorAnalyzeAndTransformFactoryMap();

        // When
        analyze(methodIdToFactory, methodCallIdMap, "com.vmlens.test.guineaPig.ThreadStart");

        // Then
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> result =
                methodIdToFactory.get(expectedMethodId);
        MethodCallAnalyzeAndTransformFactory factory = (MethodCallAnalyzeAndTransformFactory) result.get(0).element();
        MethodTransformPlanBuilder planBuilder = factory.methodTransformPlanBuilder();
        TLinkedList<TLinkableWrapper<PlanElement>> actual = planBuilder.planElementList();
        assertThat(actual, is(expectedPlanElementList));
    }

    private void analyze(MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                         MethodCallIdMap methodCallIdMap, String className) throws IOException {
        byte[] classArray = new LoadClassArray().load(className);


        MethodCallAnalyzeAndTransformFactoryFactory methodCallFactoryFactory = new MethodCallAnalyzeAndTransformFactoryFactory();

        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>> analyzeList = new TLinkedList<>();
        analyzeList.add(wrap(methodCallFactoryFactory));

        ClassVisitor classVisitorForTransform = createAnalyze(null,
                className, methodIdToFactory, analyzeList, methodCallIdMap, new MethodFilterTakeAll());
        ClassReader reader = new ClassReader(classArray);
        reader.accept(classVisitorForTransform, 0);
    }
*/

}