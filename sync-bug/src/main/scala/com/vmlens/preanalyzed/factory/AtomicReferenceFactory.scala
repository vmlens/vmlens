package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicReferenceFactory {

  def atomicReference() : AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicReference",atomicMethods());


  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
      AtomicNonBlockingMethod("setRelease","(Ljava/lang/Object;)V", NotYetImplemented()  ),
      AtomicNonBlockingMethod("getPlain","()Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicNonBlockingMethod("weakCompareAndSetVolatile","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicNonBlockingMethod("compareAndExchangeRelease","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicNonBlockingMethod("getAndSet","(Ljava/lang/Object;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicNonBlockingMethod("get","()Ljava/lang/Object;",  Read()  ),
      AtomicNonBlockingMethod("getAndUpdate","(Ljava/util/function/UnaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicNonBlockingMethod("getAcquire","()Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicNonBlockingMethod("updateAndGet","(Ljava/util/function/UnaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicNonBlockingMethod("weakCompareAndSetPlain","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicNonBlockingMethod("set","(Ljava/lang/Object;)V",  Write()  ),
      AtomicNonBlockingMethod("accumulateAndGet","(Ljava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicNonBlockingMethod("getAndAccumulate","(Ljava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
      AtomicNonBlockingMethod("weakCompareAndSetRelease","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicNonBlockingMethod("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
      AtomicNonBlockingMethod("getOpaque","()Ljava/lang/Object;", NotYetImplemented()  ),
      AtomicNonBlockingMethod("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;)Z",  ReadWrite()  ),
      AtomicNonBlockingMethod("setPlain","(Ljava/lang/Object;)V", NotYetImplemented()  ),
      AtomicNonBlockingMethod("lazySet","(Ljava/lang/Object;)V", NotYetImplemented()  ),
      AtomicNonBlockingMethod("setOpaque","(Ljava/lang/Object;)V",  NotYetImplemented() ),
      AtomicNonBlockingMethod("toString","()Ljava/lang/String;",  Read()  ),
      AtomicNonBlockingMethod("weakCompareAndSetAcquire","(Ljava/lang/Object;Ljava/lang/Object;)Z",  NotYetImplemented()  ),
      AtomicNonBlockingMethod("compareAndExchange","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",  ReadWrite()   ),
      AtomicNonBlockingMethod("compareAndExchangeAcquire","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", NotYetImplemented()  ),
    );



}
