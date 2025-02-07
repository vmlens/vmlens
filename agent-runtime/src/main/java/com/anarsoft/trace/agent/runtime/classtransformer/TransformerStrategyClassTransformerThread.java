package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilterForThread;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepositoryForAnalyze;

/**
 * methods of thread are used inside threadlocal for example:
 * agent started, Build: 1.1.7-SNAPSHOT
 * /C:/git-repo/vmlens/test-vmlens-maven-plugin/target/vmlens-agent/agent.jar
 * writing events to C:\git-repo\vmlens\test-vmlens-maven-plugin\target\vmlens-agent\vmlens
 * java.lang.StackOverflowError
 * at java.lang.String.hashCode(String.java:1466)
 * at java.util.Objects.hashCode(Objects.java:98)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId.hashCode(MethodCallId.java:28)
 * at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.hash(TObjectHash.java:482)
 * at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.index(TObjectHash.java:168)
 * at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.contains(TObjectHash.java:153)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze.methodEnterStrategy(MethodCallIdToStrategyFromAnalyze.java:21)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyDefaultValues.methodEnterStrategy(MethodCallIdToStrategyDefaultValues.java:53)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository.methodEnterStrategy(MethodRepository.java:60)
 * at com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl.methodEnter(MethodCallbackImpl.java:39)
 * at com.vmlens.trace.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:28)
 * at java.lang.Thread.getId(Thread.java)
 * at com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton$1.initialValue(ThreadLocalForParallelizeSingleton.java:8)
 * at com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton$1.initialValue(ThreadLocalForParallelizeSingleton.java:4)
 * at java.lang.ThreadLocal.setInitialValue(ThreadLocal.java:180)
 * at java.lang.ThreadLocal.get(ThreadLocal.java:170)agent started, Build: 1.1.7-SNAPSHOT
 * /C:/git-repo/vmlens/test-vmlens-maven-plugin/target/vmlens-agent/agent.jar
 * writing events to C:\git-repo\vmlens\test-vmlens-maven-plugin\target\vmlens-agent\vmlens
 * java.lang.StackOverflowError
 * at java.lang.String.hashCode(String.java:1466)
 * at java.util.Objects.hashCode(Objects.java:98)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId.hashCode(MethodCallId.java:28)
 * at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.hash(TObjectHash.java:482)
 * at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.index(TObjectHash.java:168)
 * at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.contains(TObjectHash.java:153)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze.methodEnterStrategy(MethodCallIdToStrategyFromAnalyze.java:21)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyDefaultValues.methodEnterStrategy(MethodCallIdToStrategyDefaultValues.java:53)
 * at com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository.methodEnterStrategy(MethodRepository.java:60)
 * at com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl.methodEnter(MethodCallbackImpl.java:39)
 * at com.vmlens.trace.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:28)
 * at java.lang.Thread.getId(Thread.java)
 * at com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton$1.initialValue(ThreadLocalForParallelizeSingleton.java:8)
 * at com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton$1.initialValue(ThreadLocalForParallelizeSingleton.java:4)
 * at java.lang.ThreadLocal.setInitialValue(ThreadLocal.java:180)
 * at java.lang.ThreadLocal.get(ThreadLocal.java:170)
 * <p>
 * we therefore only trace start and join
 */


public class TransformerStrategyClassTransformerThread implements TransformerStrategy {

    private final ClassTransformerFactory classTransformerFactory;


    public TransformerStrategyClassTransformerThread(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                                                     FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
                                                     WriteClassDescriptionAndWarning writeClassDescription) {
        this.classTransformerFactory = new ClassTransformerFactoryPreAnalyzed(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze, writeClassDescription);
    }

    @Override
    public byte[] transform(TransformerContext context) {
        ClassTransformer classTransformer = classTransformerFactory.create(new MethodFilterForThread());
        return classTransformer.transform(context.classfileBuffer(), context.name());
    }
}
