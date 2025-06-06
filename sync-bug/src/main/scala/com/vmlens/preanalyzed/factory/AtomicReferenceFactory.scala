package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicClassNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicReferenceFactory {

  def atomicReference() : AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicReference",atomicMethods());


  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
      AtomicClassNonBlockingMethod("setRelease","(Ljava/lang/Object;)V", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("getPlain","()Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("weakCompareAndSetVolatile","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("compareAndExchangeRelease","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("getAndSet","(Ljava/lang/Object;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicClassNonBlockingMethod("get","()Ljava/lang/Object;",  Read()  ),
      AtomicClassNonBlockingMethod("getAndUpdate","(Ljava/util/function/UnaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicClassNonBlockingMethod("getAcquire","()Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("updateAndGet","(Ljava/util/function/UnaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicClassNonBlockingMethod("weakCompareAndSetPlain","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("set","(Ljava/lang/Object;)V",  Write()  ),
      AtomicClassNonBlockingMethod("accumulateAndGet","(Ljava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicClassNonBlockingMethod("getAndAccumulate","(Ljava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicClassNonBlockingMethod("weakCompareAndSetRelease","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("getOpaque","()Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;)Z",  ReadWrite()  ),
      AtomicClassNonBlockingMethod("setPlain","(Ljava/lang/Object;)V", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("lazySet","(Ljava/lang/Object;)V", NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("setOpaque","(Ljava/lang/Object;)V",  NotYetImplemented() ),
      AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;",  Read()  ),
      AtomicClassNonBlockingMethod("weakCompareAndSetAcquire","(Ljava/lang/Object;Ljava/lang/Object;)Z",  NotYetImplemented()  ),
      AtomicClassNonBlockingMethod("compareAndExchange","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",  ReadWrite()   ),
      AtomicClassNonBlockingMethod("compareAndExchangeAcquire","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", NotYetImplemented()  ),
    );



}
