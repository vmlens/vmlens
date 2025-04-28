package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model._

object AtomicIntegerFactory {

  def atomicInteger() : AtomicNonBlocking =  AtomicNonBlocking("java/util/concurrent/atomic/AtomicInteger", atomicIntegerMethods());

  private def atomicIntegerMethods(): List[AtomicNonBlockingMethod] =
    List[AtomicNonBlockingMethod](AtomicNonBlockingMethod("addAndGet","(I)I", ReadWrite()  ),
      AtomicNonBlockingMethod("getAndIncrement","()I", ReadWrite()  ),
      AtomicNonBlockingMethod("weakCompareAndSet","(II)Z", NotYetImplemented()   ),
      AtomicNonBlockingMethod("setPlain","(I)V", NotYetImplemented() ),
      AtomicNonBlockingMethod("decrementAndGet","()I",  ReadWrite() ),
      AtomicNonBlockingMethod("compareAndSet","(II)Z",  ReadWrite() ),
      AtomicNonBlockingMethod("weakCompareAndSetRelease","(II)Z", NotYetImplemented()   ),
      AtomicNonBlockingMethod("intValue","()I", Read()  ),
      AtomicNonBlockingMethod("weakCompareAndSetAcquire","(II)Z", NotYetImplemented()  ),
      AtomicNonBlockingMethod("weakCompareAndSetPlain","(II)Z",  NotYetImplemented() ),
      AtomicNonBlockingMethod("weakCompareAndSetVolatile","(II)Z", NotYetImplemented()  ),
      AtomicNonBlockingMethod("shortValue","()S",  Read()  ),
      AtomicNonBlockingMethod("compareAndExchange","(II)I", ReadWrite()  ),
      AtomicNonBlockingMethod("incrementAndGet","()I",  ReadWrite() ),
      AtomicNonBlockingMethod("setRelease","(I)V",  NotYetImplemented() ),
      AtomicNonBlockingMethod("compareAndExchangeRelease","(II)I", NotYetImplemented()  ),
      AtomicNonBlockingMethod("doubleValue","()D", Read()  ),
      AtomicNonBlockingMethod("getAndAccumulate","(ILjava/util/function/IntBinaryOperator;)I", ReadWrite()  ),
      AtomicNonBlockingMethod("getAcquire","()I", NotYetImplemented()  ),
      AtomicNonBlockingMethod("byteValue","()B", Read()  ),
      AtomicNonBlockingMethod("getAndUpdate","(Ljava/util/function/IntUnaryOperator;)I", ReadWrite()  ),
      AtomicNonBlockingMethod("getAndSet","(I)I",  ReadWrite()  ),
      AtomicNonBlockingMethod("setOpaque","(I)V", NotYetImplemented()  ),
      AtomicNonBlockingMethod("getPlain","()I", NotYetImplemented()  ),
      AtomicNonBlockingMethod("floatValue","()F", Read()  ),
      AtomicNonBlockingMethod("set","(I)V", Write()  ),
      AtomicNonBlockingMethod("getAndAdd","(I)I",  ReadWrite()  ),
      AtomicNonBlockingMethod("accumulateAndGet","(ILjava/util/function/IntBinaryOperator;)I",  ReadWrite()  ),
      AtomicNonBlockingMethod("updateAndGet","(Ljava/util/function/IntUnaryOperator;)I",  ReadWrite()  ),
      AtomicNonBlockingMethod("getOpaque","()I", NotYetImplemented()   ),
      AtomicNonBlockingMethod("getAndDecrement","()I",  ReadWrite() ),
      AtomicNonBlockingMethod("compareAndExchangeAcquire","(II)I",  NotYetImplemented()  ),
      AtomicNonBlockingMethod("get","()I", Read()   ),
      AtomicNonBlockingMethod("toString","()Ljava/lang/String;", Read()   ),
      AtomicNonBlockingMethod("longValue","()J",  Read()  ),
      AtomicNonBlockingMethod("lazySet","(I)V",  NotYetImplemented()  )
    )


}
