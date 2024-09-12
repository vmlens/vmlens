package com.anarsoft.trace.agent.runtime;

public class LoadClassesAtStart {
    public void loadClasses() throws ClassNotFoundException {
        this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.getstate.Class2GetStateMap");

        this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.getstate.CreateGetState");
        this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.FieldAccessCallback");
        this.getClass().getClassLoader()
                .loadClass("com.vmlens.trace.agent.bootstrap.callback.SynchronizedStatementCallback");
        this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.ArrayAccessCallback");

        this.getClass().getClassLoader()
                .loadClass("com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject");
        this.getClass().getClassLoader()
                .loadClass("com.vmlens.trace.agent.bootstrap.callback.field.CallbackStatic");

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
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.TIntHashSet");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkable");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
        this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");
        this.getClass().getClassLoader().loadClass("org.objectweb.asm.ConstantDynamic");
        this.getClass().getClassLoader().loadClass("java.util.concurrent.locks.LockSupport");
    }
}
