package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.DoNotTraceType;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryAll;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddTryFinallyBlock;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory.addEnterExitTransform;
import static com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper.wrap;

public class FactoryCollectionDoNotTrace implements FactoryCollection {

    private final DoNotTraceType doNotTraceType;

    public FactoryCollectionDoNotTrace(DoNotTraceType doNotTraceType) {
        this.doNotTraceType = doNotTraceType;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();

        /*
        to avoid:

        000000016fded000]
   java.lang.Thread.State: WAITING (parking)
	at jdk.internal.misc.Unsafe.park(java.base@25.0.2/Native Method)
	- parking to wait for  <0x0000000788300118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(java.base@25.0.2/LockSupport.java:369)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(java.base@25.0.2/AbstractQueuedSynchronizer.java:520)
	at java.util.concurrent.ForkJoinPool.unmanagedBlock(java.base@25.0.2/ForkJoinPool.java:4364)
	at java.util.concurrent.ForkJoinPool.managedBlock(java.base@25.0.2/ForkJoinPool.java:4310)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@25.0.2/AbstractQueuedSynchronizer.java:1752)
	at java.lang.ProcessImpl.waitFor(java.base@25.0.2/ProcessImpl.java:423)
	at net.bytebuddy.agent.ByteBuddyAgent.installExternal(ByteBuddyAgent.java:671)
	at net.bytebuddy.agent.ByteBuddyAgent.install(ByteBuddyAgent.java:599)
	at net.bytebuddy.agent.ByteBuddyAgent.install(ByteBuddyAgent.java:579)
	- locked <0x0000000788300040> (a java.lang.Class for net.bytebuddy.agent.ByteBuddyAgent)
	at net.bytebuddy.agent.ByteBuddyAgent.install(ByteBuddyAgent.java:531)
	at net.bytebuddy.agent.ByteBuddyAgent.install(ByteBuddyAgent.java:508)
	at org.mockito.internal.PremainAttachAccess.getInstrumentation(PremainAttachAccess.java:80)
	at org.mockito.internal.creation.bytebuddy.InlineDelegateByteBuddyMockMaker.<clinit>(InlineDelegateByteBuddyMockMaker.java:138)
	at org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker.<init>(InlineByteBuddyMockMaker.java:23)
	at java.lang.invoke.DirectMethodHandle$Holder.newInvokeSpecial(java.base@25.0.2/DirectMethodHandle$Holder)
	at java.lang.invoke.Invokers$Holder.invokeExact_MT(java.base@25.0.2/Invokers$Holder)
	at jdk.internal.reflect.DirectConstructorHandleAccessor.invokeImpl(java.base@25.0.2/DirectConstructorHandleAccessor.java:86)
	at jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(java.base@25.0.2/DirectConstructorHandleAccessor.java:62)
	at java.lang.reflect.Constructor.newInstanceWithCaller(java.base@25.0.2/Constructor.java:499)
	at java.lang.reflect.Constructor.newInstance(java.base@25.0.2/Constructor.java:483)
	at org.mockito.internal.configuration.plugins.DefaultMockitoPlugins.create(DefaultMockitoPlugins.java:103)
	at org.mockito.internal.configuration.plugins.DefaultMockitoPlugins.getDefaultPlugin(DefaultMockitoPlugins.java:79)
	at org.mockito.internal.configuration.plugins.PluginLoader.loadPlugin(PluginLoader.java:75)
	at org.mockito.internal.configuration.plugins.PluginLoader.loadPlugin(PluginLoader.java:49)
	at org.mockito.internal.configuration.plugins.PluginRegistry.<init>(PluginRegistry.java:29)
	at org.mockito.internal.configuration.plugins.Plugins.<clinit>(Plugins.java:26)
	at org.mockito.internal.MockitoCore.<clinit>(MockitoCore.java:71)
	at org.mockito.Mockito.<clinit>(Mockito.java:1777)
	at com.vmlens.inttest.projects.spring.batch.JdbcExecutionContextDaoTest.setJdbcTemplate(JdbcExecutionContextDaoTest.java:22)
         */


        DoNotTraceType useType = doNotTraceType;
        if(context.nameAndDescriptor().name().equals("<clinit>")) {
            useType = DoNotTraceType.EVERYWHERE;
        }

        addEnterExitTransform(new MethodCallbackFactoryFactoryDoNotTrace(useType),result);
        result.add(wrap(AddTryFinallyBlock.factory(new MethodCallbackFactoryFactoryDoNotTrace(useType))));


        return result;
    }



    @Override
    public boolean computeFrames() {
        return false;
    }

}
