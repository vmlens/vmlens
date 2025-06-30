package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.classmodel.AtomicNonBlocking
import com.vmlens.preanalyzed.model.{AtomicClassNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicMarkableReferenceFactory {

  def atomicMarkableReference(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicMarkableReference",
    atomicMethods());

  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("attemptMark","(Ljava/lang/Object;Z)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("getReference","()Ljava/lang/Object;", Read()  ),
    AtomicClassNonBlockingMethod("get","([Z)Ljava/lang/Object;", Read()   ),
    AtomicClassNonBlockingMethod("set","(Ljava/lang/Object;Z)V", Write()  ),
    AtomicClassNonBlockingMethod("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("isMarked","()Z", Read()   ),
    AtomicClassNonBlockingMethod("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z",  NotYetImplemented()  )
  );

}
