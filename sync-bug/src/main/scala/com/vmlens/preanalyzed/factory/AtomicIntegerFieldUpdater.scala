package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ_WRITE, NEW_UPDATER}

/*
 * it seams as all methods except the static method newUpdater are overridden by AtomicIntegerFieldUpdaterImpl
 * might be a problem on other jvms?
 */
object AtomicIntegerFieldUpdater {

  def atomicIntegerFieldUpdater(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicIntegerFieldUpdater", methods(),List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("newUpdater", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", NEW_UPDATER),
    new MethodToMethodType("getAndUpdate", "(Ljava/lang/Object;Ljava/util/function/IntUnaryOperator;)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("getAndAccumulate", "(Ljava/lang/Object;ILjava/util/function/IntBinaryOperator;)I", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("accumulateAndGet", "(Ljava/lang/Object;ILjava/util/function/IntBinaryOperator;)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("updateAndGet", "(Ljava/lang/Object;Ljava/util/function/IntUnaryOperator;)I",  ATOMIC_FIELD_READ_WRITE  )

  );


}
