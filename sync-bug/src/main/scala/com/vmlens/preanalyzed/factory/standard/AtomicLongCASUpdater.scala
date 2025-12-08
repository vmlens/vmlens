package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ, ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod


object AtomicLongCASUpdater {

  def atomicLongCASUpdater(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicLongFieldUpdater$CASUpdater", methods(), List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "(Ljava/lang/Object;)J",   ATOMIC_FIELD_READ ),
    new MethodToMethodType("addAndGet", "(Ljava/lang/Object;J)J",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("weakCompareAndSet", "(Ljava/lang/Object;JJ)Z",   NotYetImplementedMethod.SINGLETON  ),
    new MethodToMethodType("getAndSet", "(Ljava/lang/Object;J)J", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("compareAndSet", "(Ljava/lang/Object;JJ)Z", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("getAndDecrement", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("incrementAndGet", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("decrementAndGet", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("getAndAdd", "(Ljava/lang/Object;J)J", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("getAndIncrement", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("lazySet", "(Ljava/lang/Object;J)V",   NotYetImplementedMethod.SINGLETON  ),
    new MethodToMethodType("set", "(Ljava/lang/Object;J)V",  ATOMIC_FIELD_WRITE  )
  );
  
}
