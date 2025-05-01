package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicMarkableReferenceFactory {

  def atomicMarkableReference(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicMarkableReference",
    atomicMethods());

  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("attemptMark","(Ljava/lang/Object;Z)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("getReference","()Ljava/lang/Object;", Read()  ),
    AtomicNonBlockingMethod("get","([Z)Ljava/lang/Object;", Read()   ),
    AtomicNonBlockingMethod("set","(Ljava/lang/Object;Z)V", Write()  ),
    AtomicNonBlockingMethod("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("isMarked","()Z", Read()   ),
    AtomicNonBlockingMethod("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z",  NotYetImplemented()  )
  );

}
