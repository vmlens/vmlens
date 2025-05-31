package com.vmlens.nottraced.agent;

public class LoadClassesAtStart {
    public void loadClasses() throws ClassNotFoundException {
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
        this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton");
        this.getClass().getClassLoader().loadClass("org.objectweb.asm.ConstantDynamic");
        this.getClass().getClassLoader().loadClass("java.util.concurrent.locks.LockSupport");

    }
}
