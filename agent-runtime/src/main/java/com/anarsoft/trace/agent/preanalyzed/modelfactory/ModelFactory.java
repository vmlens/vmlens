package com.anarsoft.trace.agent.preanalyzed.modelfactory;


import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeAllStartWith;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeFilter;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeVmlensApi;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedEqualNoOp;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodTypeThreadStart;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ModelFactory {

    public TLinkedList<TLinkableWrapper<PackageOrClass>> create() {
        TLinkedList<TLinkableWrapper<PackageOrClass>> result = new TLinkedList<>();



        /*  Thread.getId is used inside threadlocal:

            java.lang.StackOverflowError
            at java.lang.String.hashCode(String.java:1466)
            at java.util.Objects.hashCode(Objects.java:98)
            at com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId.hashCode(MethodCallId.java:28)
            at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.hash(TObjectHash.java:482)
            at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.index(TObjectHash.java:168)
            at com.vmlens.shaded.gnu.trove.impl.hash.TObjectHash.contains(TObjectHash.java:153)
            at com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze.methodEnterStrategy(MethodCallIdToStrategyFromAnalyze.java:21)
            at com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyDefaultValues.methodEnterStrategy(MethodCallIdToStrategyDefaultValues.java:53)
            at com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository.methodEnterStrategy(MethodRepository.java:60)
            at com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl.methodEnter(MethodCallbackImpl.java:39)
            at com.vmlens.trace.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:28)
            at java.lang.Thread.getId(Thread.java)
            at com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton$1.initialValue(ThreadLocalForParallelizeSingleton.java:8)
            at com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton$1.initialValue(ThreadLocalForParallelizeSingleton.java:4)
            at java.lang.ThreadLocal.setInitialValue(ThreadLocal.java:180)
            at java.lang.ThreadLocal.get(ThreadLocal.java:170)

            we therefore only trace start and join
        */
        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[1];
        methods[0] = new PreAnalyzedMethod("start", "()V", MethodTypeThreadStart.SINGLETON);
        result.add(wrap(new PackageOrClass("java/lang/Thread", PreAnalyzedEqualNoOp.SINGLETON, methods)));

        result.add(wrap(new PackageOrClass("com/vmlens/api/AllInterleavingsBuilder", ClassTypeFilter.SINGLETON, new PreAnalyzedMethod[0])));
        result.add(wrap(new PackageOrClass("com/vmlens/api/AllInterleavings", ClassTypeVmlensApi.SINGLETON, new PreAnalyzedMethod[0])));

        result.add(wrap(new PackageOrClass("com/vmlens", ClassTypeAllStartWith.SINGLETON, new PreAnalyzedMethod[0])));


        return result;
    }
}
