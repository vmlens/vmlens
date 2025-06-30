package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.classmodel.AtomicNonBlocking
import com.vmlens.preanalyzed.model.{AtomicArrayNonBlockingMethod, AtomicClassNonBlockingMethod, AtomicNonBlockingMethod, NotYetImplemented, Read, ReadWrite, Write}

private class AtomicIntegerOrLongArrayFactory(val className: String,
                                         val desc: String,
                                         val prefix: String) {

  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods());


  private def atomicMethods(): List[AtomicNonBlockingMethod] =
    List[AtomicNonBlockingMethod](
      AtomicArrayNonBlockingMethod("set", s"(I$desc)V", Write()),
      AtomicArrayNonBlockingMethod("weakCompareAndSetVolatile", s"(I$desc$desc)Z", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("lazySet", s"(I$desc)V",  NotYetImplemented()),
      AtomicArrayNonBlockingMethod("compareAndExchangeAcquire", s"(I$desc$desc)$desc",  NotYetImplemented()),
      AtomicArrayNonBlockingMethod("getAndSet", s"(I$desc)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("setOpaque", s"(I$desc)V",  NotYetImplemented()),
      AtomicArrayNonBlockingMethod("setRelease", s"(I$desc)V", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("getAndIncrement", s"(I)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("addAndGet", s"(I$desc)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("get", s"(I)$desc", Read()),
      AtomicArrayNonBlockingMethod("updateAndGet", s"(ILjava/util/function/${prefix}UnaryOperator;)$desc",ReadWrite()),
      AtomicArrayNonBlockingMethod("compareAndExchange", s"(I$desc$desc)$desc", ReadWrite()  ),
      AtomicArrayNonBlockingMethod("compareAndExchangeRelease", s"(I$desc$desc)$desc", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("weakCompareAndSetAcquire", s"(I$desc$desc)Z", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("weakCompareAndSet", s"(I$desc$desc)Z", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("incrementAndGet", s"(I)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("accumulateAndGet", s"(I${desc}Ljava/util/function/${prefix}BinaryOperator;)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("getOpaque", s"(I)$desc", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("getAndDecrement", s"(I)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("getPlain", s"(I)$desc",  NotYetImplemented()),
      AtomicArrayNonBlockingMethod("weakCompareAndSetRelease", s"(I$desc$desc)Z", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("setPlain", s"(I$desc)V", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("weakCompareAndSetPlain", s"(I$desc$desc)Z", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("getAcquire", s"(I)$desc", NotYetImplemented()),
      AtomicArrayNonBlockingMethod("decrementAndGet", s"(I)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("getAndAdd", s"(I$desc)$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;", Read()),
      AtomicArrayNonBlockingMethod("compareAndSet", s"(I$desc$desc)Z", ReadWrite()),
      AtomicArrayNonBlockingMethod("getAndUpdate", s"(ILjava/util/function/${prefix}UnaryOperator;)$desc", ReadWrite()),
      AtomicArrayNonBlockingMethod("getAndAccumulate",s"(I${desc}Ljava/util/function/${prefix}BinaryOperator;)$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("length","()I", Read()))

}

object AtomicIntegerOrLongArrayFactory {

  def atomicIntegerArray(): AtomicNonBlocking =
    new AtomicIntegerOrLongArrayFactory("AtomicIntegerArray", "I", "Int").atomic();

  def atomicLongArray(): AtomicNonBlocking =
    new AtomicIntegerOrLongArrayFactory("AtomicLongArray", "J", "Long").atomic();
  
  
}
