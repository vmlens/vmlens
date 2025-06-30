package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.factory.AtomicReferenceFactory.atomicMethods
import com.vmlens.preanalyzed.model.classmodel.AtomicNonBlocking
import com.vmlens.preanalyzed.model.{AtomicArrayNonBlockingMethod, AtomicClassNonBlockingMethod, AtomicNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

object AtomicReferenceArrayFactory {

  def atomicReferenceArray(): AtomicNonBlocking = AtomicNonBlocking("java/util/concurrent/atomic/AtomicReferenceArray", atomicMethods());


  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicArrayNonBlockingMethod("setOpaque","(ILjava/lang/Object;)V",   NotYetImplemented() ),
    AtomicArrayNonBlockingMethod("setRelease","(ILjava/lang/Object;)V",  NotYetImplemented()   ),
    AtomicArrayNonBlockingMethod("compareAndExchange","(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()  ),
    AtomicArrayNonBlockingMethod("getPlain","(I)Ljava/lang/Object;",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("weakCompareAndSetRelease","(ILjava/lang/Object;Ljava/lang/Object;)Z",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("getAcquire","(I)Ljava/lang/Object;",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("lazySet","(ILjava/lang/Object;)V",   NotYetImplemented() ),
    AtomicArrayNonBlockingMethod("getAndSet","(ILjava/lang/Object;)Ljava/lang/Object;", ReadWrite()  ),
    AtomicArrayNonBlockingMethod("get","(I)Ljava/lang/Object;", Read()  ),
    AtomicArrayNonBlockingMethod("weakCompareAndSetAcquire","(ILjava/lang/Object;Ljava/lang/Object;)Z",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("getAndAccumulate","(ILjava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;", ReadWrite()  ),
    AtomicArrayNonBlockingMethod("setPlain","(ILjava/lang/Object;)V",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("compareAndExchangeAcquire","(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("compareAndExchangeRelease","(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",   NotYetImplemented() ),
    AtomicArrayNonBlockingMethod("weakCompareAndSetVolatile","(ILjava/lang/Object;Ljava/lang/Object;)Z", NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("getAndUpdate","(ILjava/util/function/UnaryOperator;)Ljava/lang/Object;", ReadWrite()  ),
    AtomicArrayNonBlockingMethod("updateAndGet","(ILjava/util/function/UnaryOperator;)Ljava/lang/Object;", ReadWrite()  ),
    AtomicArrayNonBlockingMethod("getOpaque","(I)Ljava/lang/Object;",  NotYetImplemented()  ),
    AtomicArrayNonBlockingMethod("set","(ILjava/lang/Object;)V", Write()  ),
    AtomicArrayNonBlockingMethod("weakCompareAndSetPlain","(ILjava/lang/Object;Ljava/lang/Object;)Z",  NotYetImplemented()  ),
    AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;", Read() ),
    AtomicArrayNonBlockingMethod("compareAndSet","(ILjava/lang/Object;Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicArrayNonBlockingMethod("accumulateAndGet","(ILjava/lang/Object;Ljava/util/function/BinaryOperator;)Ljava/lang/Object;",  ReadWrite()  ),
    AtomicClassNonBlockingMethod("length","()I", Read()  ),
    AtomicArrayNonBlockingMethod("weakCompareAndSet","(ILjava/lang/Object;Ljava/lang/Object;)Z",  NotYetImplemented()  )
  )
  
}
