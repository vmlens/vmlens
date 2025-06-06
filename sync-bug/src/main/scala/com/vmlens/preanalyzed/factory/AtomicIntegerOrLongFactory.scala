package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model._

private class AtomicIntegerOrLongFactory(val className : String,
                                 val desc : String,
                                 val prefix : String) {

  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods() );


  private def atomicMethods(): List[AtomicClassNonBlockingMethod] =
    List[AtomicClassNonBlockingMethod](AtomicClassNonBlockingMethod("addAndGet", s"($desc)$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("getAndIncrement", s"()$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("weakCompareAndSet", s"($desc$desc)Z", NotYetImplemented()),
      AtomicClassNonBlockingMethod("setPlain", s"($desc)V", NotYetImplemented()),
      AtomicClassNonBlockingMethod("decrementAndGet", s"()$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("compareAndSet", s"($desc$desc)Z", ReadWrite()),
      AtomicClassNonBlockingMethod("weakCompareAndSetRelease", s"($desc$desc)Z", NotYetImplemented()),
      AtomicClassNonBlockingMethod("intValue", s"()I", Read()),
      AtomicClassNonBlockingMethod("weakCompareAndSetAcquire", s"($desc$desc)Z", NotYetImplemented()),
      AtomicClassNonBlockingMethod("weakCompareAndSetPlain", s"($desc$desc)Z", NotYetImplemented()),
      AtomicClassNonBlockingMethod("weakCompareAndSetVolatile", s"($desc$desc)Z", NotYetImplemented()),
      AtomicClassNonBlockingMethod("shortValue", "()S", Read()),
      AtomicClassNonBlockingMethod("compareAndExchange", s"($desc$desc)$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("incrementAndGet", s"()$desc", ReadWrite()),
      AtomicClassNonBlockingMethod("setRelease", s"($desc)V", NotYetImplemented()),
      AtomicClassNonBlockingMethod("compareAndExchangeRelease", s"($desc$desc)$desc", NotYetImplemented()),
      AtomicClassNonBlockingMethod("doubleValue", "()D", Read()),
      AtomicClassNonBlockingMethod("getAndAccumulate", s"(${desc}Ljava/util/function/${prefix}BinaryOperator;)${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("getAcquire", s"()${desc}", NotYetImplemented()),
      AtomicClassNonBlockingMethod("byteValue", "()B", Read()),
      AtomicClassNonBlockingMethod("getAndUpdate", s"(Ljava/util/function/${prefix}UnaryOperator;)${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("getAndSet", s"(${desc})${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("setOpaque", s"(${desc})V", NotYetImplemented()),
      AtomicClassNonBlockingMethod("getPlain", s"()${desc}", NotYetImplemented()),
      AtomicClassNonBlockingMethod("floatValue", "()F", Read()),
      AtomicClassNonBlockingMethod("set", s"(${desc})V", Write()),
      AtomicClassNonBlockingMethod("getAndAdd", s"(${desc})${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("accumulateAndGet", s"(${desc}Ljava/util/function/${prefix}BinaryOperator;)${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("updateAndGet", s"(Ljava/util/function/${prefix}UnaryOperator;)${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("getOpaque", s"()${desc}", NotYetImplemented()),
      AtomicClassNonBlockingMethod("getAndDecrement", s"()${desc}", ReadWrite()),
      AtomicClassNonBlockingMethod("compareAndExchangeAcquire", s"(${desc}${desc})${desc}", NotYetImplemented()),
      AtomicClassNonBlockingMethod("get", s"()${desc}", Read()),
      AtomicClassNonBlockingMethod("toString", "()Ljava/lang/String;", Read()),
      AtomicClassNonBlockingMethod("longValue", "()J", Read()),
      AtomicClassNonBlockingMethod("lazySet", s"(${desc})V", NotYetImplemented())
    )

}

object AtomicIntegerOrLongFactory {

  def atomicInteger() : AtomicNonBlocking =
    new AtomicIntegerOrLongFactory("AtomicInteger","I","Int").atomic();

  def atomicLong(): AtomicNonBlocking =
    new AtomicIntegerOrLongFactory("AtomicLong", "J", "Long").atomic();



}
