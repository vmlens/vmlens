package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ, ATOMIC_FIELD_WRITE};


object AtomicIntegerFieldUpdaterImpl {


  def atomicIntegerFieldUpdaterImpl(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicIntegerFieldUpdater$AtomicIntegerFieldUpdaterImpl", methods())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "(Ljava/lang/Object;)I", ATOMIC_FIELD_READ),
    new MethodToMethodType("set", "(Ljava/lang/Object;I)V", ATOMIC_FIELD_WRITE)
  );
}
