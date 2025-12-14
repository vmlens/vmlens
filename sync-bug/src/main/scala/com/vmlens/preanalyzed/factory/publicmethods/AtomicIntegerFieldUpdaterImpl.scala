package com.vmlens.preanalyzed.factory.publicmethods

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ, ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod;

object AtomicIntegerFieldUpdaterImpl {

  def atomicIntegerFieldUpdaterImpl(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicIntegerFieldUpdater$AtomicIntegerFieldUpdaterImpl", methods(),List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "(Ljava/lang/Object;)I", ATOMIC_FIELD_READ),
    new MethodToMethodType("set", "(Ljava/lang/Object;I)V", ATOMIC_FIELD_WRITE),
    new MethodToMethodType("getAndIncrement", "(Ljava/lang/Object;)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("addAndGet", "(Ljava/lang/Object;I)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("getAndSet", "(Ljava/lang/Object;I)I", ATOMIC_FIELD_READ_WRITE   ),
    new MethodToMethodType("incrementAndGet", "(Ljava/lang/Object;)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("decrementAndGet", "(Ljava/lang/Object;)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("getAndDecrement", "(Ljava/lang/Object;)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("getAndAdd", "(Ljava/lang/Object;I)I",  ATOMIC_FIELD_READ_WRITE  ),
    new MethodToMethodType("compareAndSet", "(Ljava/lang/Object;II)Z", NotYetImplementedMethod.SINGLETON ),
    new MethodToMethodType("lazySet", "(Ljava/lang/Object;I)V",    NotYetImplementedMethod.SINGLETON ),
    new MethodToMethodType("weakCompareAndSet", "(Ljava/lang/Object;II)Z",    NotYetImplementedMethod.SINGLETON )
  );

}

