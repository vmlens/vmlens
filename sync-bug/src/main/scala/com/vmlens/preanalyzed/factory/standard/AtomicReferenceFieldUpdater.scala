package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE, NEW_REFERENCE_UPDATER}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod

object AtomicReferenceFieldUpdater {
  
  def atomicReferenceFieldUpdater(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicReferenceFieldUpdater", methods(), List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("getAndAccumulate", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("getAndSet", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",   ATOMIC_FIELD_READ_WRITE ),
    new MethodToMethodType("getAndUpdate", "(Ljava/lang/Object;Ljava/util/function/UnaryOperator;)Ljava/lang/Object;",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("newUpdater", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", NEW_REFERENCE_UPDATER   ),
    new MethodToMethodType("updateAndGet", "(Ljava/lang/Object;Ljava/util/function/UnaryOperator;)Ljava/lang/Object;",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("accumulateAndGet", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;", ATOMIC_FIELD_READ_WRITE   )
  );
}
