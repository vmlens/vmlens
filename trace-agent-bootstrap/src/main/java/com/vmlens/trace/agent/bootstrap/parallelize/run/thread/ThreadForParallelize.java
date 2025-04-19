package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import static java.lang.Thread.State.*;

public class ThreadForParallelize {

    private final Thread thread;

    public ThreadForParallelize(Thread thread) {
        this.thread = thread;
    }

    /*
       java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@16/Native Method)
        - waiting on <0x00000007137508f8> (a com.vmlens.test.maven.plugin.TestVolatileField$1)
        at java.lang.Thread.join(java.base@16/Thread.java:1301)
        - locked <0x00000007137508f8> (a com.vmlens.test.maven.plugin.TestVolatileField$1)
        at java.lang.Thread.join(java.base@16/Thread.java:1369)
        at com.vmlens.test.maven.plugin.TestVolatileField.testUpdate(TestVolatileField.java:24)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(java.base@16/Native Method)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(java.base@16/NativeMethodAccessorImpl.java:78)
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(java.base@16/DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(java.base@16/Method.java:567)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:316)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeWithRerun(JUnit4Provider.java:240)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:214)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:155)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)

        "Thread-7" #26 prio=5 os_prio=0 cpu=93.75ms elapsed=16.74s tid=0x00000281a8150820 nid=0x5ebc waiting on condition  [0x000000c81bafe000]
         java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@16/Native Method)
        - parking to wait for  <0x00000007137505b0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@16/LockSupport.java:252)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@16/AbstractQueuedSynchronizer.java:1746)
        at com.vmlens.trace.agent.bootstrap.parallelize.run.impl.WaitNotifyStrategyImpl.notifyAndWaitTillActive(WaitNotifyStrategyImpl.java:23)
        at com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl.endAtomicAction(RunImpl.java:69)
        at com.vmlens.trace.agent.bootstrap.callback.threadlocal.RunAdapter.endAtomicOperation(RunAdapter.java:40)
        at com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteRunEndAtomicAction.execute(ExecuteRunEndAtomicAction.java:22)
        at com.vmlens.trace.agent.bootstrap.callback.callbackaction.OnAfterMethodCall.execute(OnAfterMethodCall.java:26)
        at com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl.process(ThreadLocalWhenInTestAdapterImpl.java:38)
        at com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl.afterFieldAccess(FieldCallbackImpl.java:45)
        at com.vmlens.trace.agent.bootstrap.callback.FieldCallback.afterFieldAccess(FieldCallback.java:31)
        at com.vmlens.test.maven.plugin.TestVolatileField.access$008(TestVolatileField.java:7)
        at com.vmlens.test.maven.plugin.TestVolatileField$1.run(TestVolatileField.java:19)

     */
    public boolean isBlocked() {
        Thread.State state = thread.getState();
        if(state == NEW || state == RUNNABLE) {
            return false;
        }

        for(StackTraceElement element : thread.getStackTrace()) {
            if(element.getClassName().startsWith("com.vmlens.trace.agent.bootstrap")) {
                return false;
            }
        }
        return true;
    }

    public long getId() {
        return thread.getId();
    }

    public String getName() {
        return thread.getName();
    }

}
