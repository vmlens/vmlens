package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ, ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE, NEW_UPDATER}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod

object AtomicReferenceFieldUpdaterImpl {

  def atomicReferenceFieldUpdaterImpl(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicReferenceFieldUpdater$AtomicReferenceFieldUpdaterImpl", methods(), List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("set", "(Ljava/lang/Object;Ljava/lang/Object;)V",   ATOMIC_FIELD_READ_WRITE ),
    new MethodToMethodType("weakCompareAndSet", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z",   NotYetImplementedMethod.SINGLETON   ),
    new MethodToMethodType("compareAndSet", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("lazySet", "(Ljava/lang/Object;Ljava/lang/Object;)V",  NotYetImplementedMethod.SINGLETON    ),
    new MethodToMethodType("getAndSet", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("get", "(Ljava/lang/Object;)Ljava/lang/Object;", ATOMIC_FIELD_READ   )
      );
  

}
