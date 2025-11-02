# Problem

Normal Method Enter, Exit
PreAnalyzed Without Any

normally method enter exit gets the object
the following special normal enter methods are needed:
    with first object Param:
        reflection field
            Integer value = (Integer) field.get(this);
            field.set(this,value + 1);
        AtomicReferenceFieldUpdater
            get(T obj)
            set ...

    with first int param
        AtomicReferenceArray

    best test through gienea pig similar to ThreadPoolExecutorGuineaPig
    we can check that the callbah contains the value


    the following special method exit methods are needed:
        static <U> AtomicIntegerFieldUpdater<U>	newUpdater(Class<U> tclass, String fieldName)
           similar to this:
    @Override
    public void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,
                                                 MethodCallbackFactory methodCallbackFactory,
                                                 int methodId,
                                                 String className) {
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodCallbackFactory.methodExitWithObjectReturn(methodId);
    }
            only that we need aload 1 also
            strategy based?
            how many strategies do we need?



    


Static
Constructor

# Solution

variation through 
MethodCallbackFactory: 
MethodCallbackFactoryPreAnalyzed, MethodCallbackFactoryAll, MethodCallbackFactoryDoNotTrace

and MethodEnterStrategy:
MethodEnterStrategyWithIntParam
MethodEnterStrategyWithoutIntParam
+ further variations

## Creation

MethodCallbackFactoryFactory: 
MethodCallbackFactoryFactoryAll, MethodCallbackFactoryFactoryDoNotTrace, MethodCallbackFactoryFactoryPreAnalyzed

FactoryCollectionPreAnalyzed -> MethodCallbackFactoryFactoryPreAnalyzed MethodEnterStrategy selection based on preanalyzed strategy
public class SelectMethodEnterStrategy {

    private final THashSet<NameAndDescriptor> useWithInParam = new THashSet<>();

    public void addToUseWithInParam(NameAndDescriptor nameAndDescriptor) {
        useWithInParam.add(nameAndDescriptor);
    }

    public boolean useWithInParam(NameAndDescriptor nameAndDescriptor) {
        return useWithInParam.contains(nameAndDescriptor);
    }

}

build in FactoryCollectionPreAnalyzedFactoryBuilderImpl

    @Override
    public void addNonBlockingArrayMethod(String name, String desc, int operation) {
        NameAndDescriptor nameAndDescriptor = new NameAndDescriptor(name, desc);
        preAnalyzedStrategy.addToUseWithInParam(nameAndDescriptor);
        methodToStrategy.put(nameAndDescriptor, new NonBlockingStrategy(operation));
    }

best interface for MethodCallbackFactoryFactoryPreAnalyzed and strategy and interface to bootstrap
map String name, String desc, to strategy

