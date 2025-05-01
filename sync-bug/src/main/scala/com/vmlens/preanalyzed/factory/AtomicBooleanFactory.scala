package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicBooleanFactory {

  def atomicBoolean(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicBoolean",
    atomicMethods());


  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("weakCompareAndSetAcquire","(ZZ)Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("getPlain","()Z",   NotYetImplemented() ),
    AtomicNonBlockingMethod("compareAndExchangeAcquire","(ZZ)Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("getAcquire","()Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("setPlain","(Z)V",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("get","()Z", Read()  ),
    AtomicNonBlockingMethod("compareAndExchange","(ZZ)Z",  ReadWrite()  ),
    AtomicNonBlockingMethod("setRelease","(Z)V",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("compareAndExchangeRelease","(ZZ)Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("weakCompareAndSetVolatile","(ZZ)Z", NotYetImplemented()  ),
    AtomicNonBlockingMethod("lazySet","(Z)V",   NotYetImplemented() ),
    AtomicNonBlockingMethod("compareAndSet","(ZZ)Z",   ReadWrite() ),
    AtomicNonBlockingMethod("setOpaque","(Z)V",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("weakCompareAndSetPlain","(ZZ)Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("weakCompareAndSet","(ZZ)Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("toString","()Ljava/lang/String;",  Read()  ),
    AtomicNonBlockingMethod("set","(Z)V", Write()  ),
    AtomicNonBlockingMethod("weakCompareAndSetRelease","(ZZ)Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("getOpaque","()Z",  NotYetImplemented()  ),
    AtomicNonBlockingMethod("getAndSet","(Z)Z",  ReadWrite()  )
  );

}
