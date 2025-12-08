package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE, NEW_UPDATER}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod

object AtomicLongFieldUpdater {

  def atomicLongFieldUpdater(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicLongFieldUpdater", methods(), List())
  }

  private def methods(): List[MethodToMethodType] = List(
  new MethodToMethodType("accumulateAndGet", "(Ljava/lang/Object;JLjava/util/function/LongBinaryOperator;)J",  ATOMIC_FIELD_READ_WRITE  ),
  new MethodToMethodType("newUpdater", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;",  NEW_UPDATER  ),
  new MethodToMethodType("addAndGet", "(Ljava/lang/Object;J)J", ATOMIC_FIELD_READ_WRITE   ),
  new MethodToMethodType("getAndSet", "(Ljava/lang/Object;J)J", ATOMIC_FIELD_READ_WRITE   ),
  new MethodToMethodType("getAndAccumulate", "(Ljava/lang/Object;JLjava/util/function/LongBinaryOperator;)J", ATOMIC_FIELD_READ_WRITE   ),
  new MethodToMethodType("getAndDecrement", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
  new MethodToMethodType("updateAndGet", "(Ljava/lang/Object;Ljava/util/function/LongUnaryOperator;)J",  ATOMIC_FIELD_READ_WRITE  ),
  new MethodToMethodType("incrementAndGet", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
  new MethodToMethodType("decrementAndGet", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
  new MethodToMethodType("getAndAdd", "(Ljava/lang/Object;J)J",  ATOMIC_FIELD_READ_WRITE  ),
  new MethodToMethodType("getAndUpdate", "(Ljava/lang/Object;Ljava/util/function/LongUnaryOperator;)J", ATOMIC_FIELD_READ_WRITE   ),
  new MethodToMethodType("getAndIncrement", "(Ljava/lang/Object;)J", ATOMIC_FIELD_READ_WRITE   )
  );
  
  
}
