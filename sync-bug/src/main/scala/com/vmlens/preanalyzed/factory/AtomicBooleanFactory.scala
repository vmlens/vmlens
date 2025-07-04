package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.classmodel.AtomicNonBlocking
import com.vmlens.preanalyzed.model.{AtomicClassNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicBooleanFactory {

  def atomicBoolean(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicBoolean",
    atomicMethods());


  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("weakCompareAndSetAcquire","(ZZ)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("getPlain","()Z",   NotYetImplemented() ),
    AtomicClassNonBlockingMethod("compareAndExchangeAcquire","(ZZ)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("getAcquire","()Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("setPlain","(Z)V",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("get","()Z", Read()  ),
    AtomicClassNonBlockingMethod("compareAndExchange","(ZZ)Z",  ReadWrite()  ),
    AtomicClassNonBlockingMethod("setRelease","(Z)V",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("compareAndExchangeRelease","(ZZ)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("weakCompareAndSetVolatile","(ZZ)Z", NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("lazySet","(Z)V",   NotYetImplemented() ),
    AtomicClassNonBlockingMethod("compareAndSet","(ZZ)Z",   ReadWrite() ),
    AtomicClassNonBlockingMethod("setOpaque","(Z)V",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("weakCompareAndSetPlain","(ZZ)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("weakCompareAndSet","(ZZ)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;",  Read()  ),
    AtomicClassNonBlockingMethod("set","(Z)V", Write()  ),
    AtomicClassNonBlockingMethod("weakCompareAndSetRelease","(ZZ)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("getOpaque","()Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("getAndSet","(Z)Z",  ReadWrite()  )
  );

}
