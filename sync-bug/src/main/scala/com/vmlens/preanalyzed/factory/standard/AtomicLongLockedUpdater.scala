package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ, ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod

object AtomicLongLockedUpdater {

  def atomicLockedUpdater(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicLongFieldUpdater$LockedUpdater", methods(), List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "(Ljava/lang/Object;)J",  ATOMIC_FIELD_READ  ),
    new MethodToMethodType("weakCompareAndSet", "(Ljava/lang/Object;JJ)Z", NotYetImplementedMethod.SINGLETON    ),
    new MethodToMethodType("compareAndSet", "(Ljava/lang/Object;JJ)Z", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("lazySet", "(Ljava/lang/Object;J)V",  NotYetImplementedMethod.SINGLETON   ),
    new MethodToMethodType("set", "(Ljava/lang/Object;J)V",  ATOMIC_FIELD_WRITE  )
  );
  
  

}
