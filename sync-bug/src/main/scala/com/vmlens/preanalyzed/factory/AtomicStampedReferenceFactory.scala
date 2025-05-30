package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicClassNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicStampedReferenceFactory {

  def atomicStampedReference(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicStampedReference",
    atomicMethods());

  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("getReference","()Ljava/lang/Object;",  Read()  ),
    AtomicClassNonBlockingMethod("get","([I)Ljava/lang/Object;",  Read()  ),
    AtomicClassNonBlockingMethod("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;II)Z",   ReadWrite()  ),
    AtomicClassNonBlockingMethod("attemptStamp","(Ljava/lang/Object;I)Z",  ReadWrite()   ),
    AtomicClassNonBlockingMethod("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;II)Z", NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("set","(Ljava/lang/Object;I)V",  Write()  ),
    AtomicClassNonBlockingMethod("getStamp","()I", Read() )
  );

}
