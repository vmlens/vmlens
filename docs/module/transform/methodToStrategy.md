# Problem

selection based only on method name or method name and call parameter

# Current

MethodToMethodType -> PreAnalyzedMethod -> MethodType

PreAnalyzedMethod:add
public void add(String className, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
MethodTypeContext context = new MethodTypeContext(className,name, desc, methodBuilder);
methodType.add(context);
}



public interface FactoryCollectionPreAnalyzedFactoryBuilder {

    void addNonBlockingArrayMethod(String name, String desc, int operation);

    void addPreAnalyzedMethod(String name, String desc, StrategyPreAnalyzed strategyPreAnalyzed);

    void noOpWhenMethodNotFound();

    void setThreadPoolStart(String name, String desc);

    void addThreadPoolJoin(String name, String desc);

}

interseting: addPreAnalyzedMethod

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(),context.desc(),new MethodWithLockStrategy(lockType));
    }

problem
className,name, desc,
 is given to context and than used again in methodType.add(context);
better new class with is... isThreadPoolStart
or isNonBlockingArrayMethod ...

which contains className,name, desc, or later the filter


# Solution

