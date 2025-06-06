package com.vmlens.test.maven.plugin.json;

import com.google.gson.Gson;
import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;


public class TestGson {

    private Gson gson = new Gson();

    /**
     *
     * This is an example of a classloader lock in action:
     *
     * "main" #1 prio=5 os_prio=0 cpu=3875.00ms elapsed=210.64s tid=0x0000023769971270 nid=0x4890 waiting on condition  [0x00000082efbfb000]
     *    java.lang.Thread.State: TIMED_WAITING (parking)
     *         at jdk.internal.misc.Unsafe.park(java.base@16/Native Method)
     *         - parking to wait for  <0x000000070bae6090> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
     *         at java.util.concurrent.locks.LockSupport.parkNanos(java.base@16/LockSupport.java:252)
     *         at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@16/AbstractQueuedSynchronizer.java:1746)
     *         at com.vmlens.trace.agent.bootstrap.parallelize.run.impl.WaitNotifyStrategyImpl.notifyAndWaitTillActive(WaitNotifyStrategyImpl.java:27)
     *         at com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl.after(RunImpl.java:42)
     *         at com.vmlens.trace.agent.bootstrap.callback.threadlocal.RunAdapter.after(RunAdapter.java:17)
     *         at com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteRunAfter.execute(ExecuteRunAfter.java:26)
     *         at com.vmlens.trace.agent.bootstrap.callback.callbackaction.OnAfterMethodCall.execute(OnAfterMethodCall.java:28)
     *         at com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl.process(ThreadLocalWhenInTestAdapterImpl.java:33)
     *         at com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl.afterFieldAccess(FieldCallbackImpl.java:48)
     *         at com.vmlens.trace.agent.bootstrap.callback.FieldCallback.afterFieldAccess(FieldCallback.java:68)
     *         at com.google.gson.stream.JsonReader.<clinit>(JsonReader.java:1859)
     *         at com.google.gson.Gson.newJsonReader(Gson.java:1078)
     *         at com.google.gson.Gson.fromJson(Gson.java:1259)
     *         at com.google.gson.Gson.fromJson(Gson.java:1170)
     *         at com.google.gson.Gson.fromJson(Gson.java:1107)
     *         at com.vmlens.examples.json.TestGson.parseGson(TestGson.java:35)
     *         at com.vmlens.examples.json.TestGson.testParseGsonFile(TestGson.java:26)
     *         at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(java.base@16/Native Method)
     *         at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(java.base@16/NativeMethodAccessorImpl.java:78)
     *         at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(java.base@16/DelegatingMethodAccessorImpl.java:43)
     *         at java.lang.reflect.Method.invoke(java.base@16/Method.java:567)
     *
     *   "Thread-1" #21 prio=5 os_prio=0 cpu=0.00ms elapsed=205.46s tid=0x000002377e58a3b0 nid=0x5aa4 in Object.wait()  [0x00000082f1bfe000]
     *    java.lang.Thread.State: RUNNABLE
     *         at com.google.gson.Gson.newJsonReader(Gson.java:1078)
     *         - waiting on the Class initialization monitor for com.google.gson.stream.JsonReader
     *         at com.google.gson.Gson.fromJson(Gson.java:1259)
     *         at com.google.gson.Gson.fromJson(Gson.java:1170)
     *         at com.google.gson.Gson.fromJson(Gson.java:1107)
     *         at com.vmlens.examples.json.TestGson.parseGson(TestGson.java:35)
     *         at com.vmlens.examples.json.TestGson.access$000(TestGson.java:10)
     *         at com.vmlens.examples.json.TestGson$1.run(TestGson.java:22)
     *
     */

    @Test
    public void testParseGsonFile() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testGson")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        parseGson();
                    }
                };
                first.start();
                parseGson();
                first.join();
            }
        }
    }

    private void parseGson() {
        String json = "{\"name\":\"Alice\", \"age\":30}";
        Gson gson = new Gson();
        gson.fromJson(json, Person.class);
    }
    
}
