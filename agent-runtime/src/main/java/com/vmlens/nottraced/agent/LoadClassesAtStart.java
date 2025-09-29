package com.vmlens.nottraced.agent;

import com.vmlens.transformed.agent.bootstrap.lock.ReadWriteLockMap;

public class LoadClassesAtStart {
    public void loadClasses() throws ClassNotFoundException {

        final ReadWriteLockMap instance = ReadWriteLockMap.INSTANCE;

        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkableAdapter");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.THashSet");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkable");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.THashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TIntObjectHashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.THashSet");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.THashSet");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.THashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.TIntHashSet");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TIntObjectHashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.TIntHashSet");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkable");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton");
        this.getClass().getClassLoader().loadClass("org.objectweb.asm.ConstantDynamic");
        this.getClass().getClassLoader().loadClass("java.util.concurrent.locks.LockSupport");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.ArrayCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.DoNotTraceCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.FieldCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.MethodCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.MonitorCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.PreAnalyzedCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.ThreadPoolCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.VmlensApiCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.WaitNotifyProxy");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CheckIsThreadRun");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.callbackaction.ThreadLocalForCallbackAction");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProviderImpl");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CheckIsThreadRun");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.parallelize.facade.ParallelizeFacade");
        this.getClass().getClassLoader().loadClass("com.vmlens.transformed.agent.bootstrap.event.queue.EventQueueSingleton");

        new StackTraceElement( "declaringClass", "methodName", "fileName", 0);
    }
}
