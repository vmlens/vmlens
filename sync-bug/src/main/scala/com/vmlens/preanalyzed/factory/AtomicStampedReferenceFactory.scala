package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicStampedReferenceFactory {

  def atomicStampedReference(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicStampedReference",
    atomicMethods());

  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("getReference","()Ljava/lang/Object;",  Read()  ),
    AtomicNonBlockingMethod("get","([I)Ljava/lang/Object;",  Read()  ),
    AtomicNonBlockingMethod("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;II)Z",   ReadWrite()  ),
    AtomicNonBlockingMethod("attemptStamp","(Ljava/lang/Object;I)Z",  ReadWrite()   ),
    AtomicNonBlockingMethod("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;II)Z", NotYetImplemented()  ),
    AtomicNonBlockingMethod("set","(Ljava/lang/Object;I)V",  Write()  ),
    AtomicNonBlockingMethod("getStamp","()I", Read() )
  );

}
