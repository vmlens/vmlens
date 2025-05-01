package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model._

private class AtomicIntegerOrLongFactory(val className : String,
                                 val desc : String,
                                 val prefix : String) {

  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods() );


  private def atomicMethods(): List[AtomicNonBlockingMethod] =
    List[AtomicNonBlockingMethod](AtomicNonBlockingMethod("addAndGet", s"($desc)$desc", ReadWrite()),
      AtomicNonBlockingMethod("getAndIncrement", s"()$desc", ReadWrite()),
      AtomicNonBlockingMethod("weakCompareAndSet", s"($desc$desc)Z", NotYetImplemented()),
      AtomicNonBlockingMethod("setPlain", s"($desc)V", NotYetImplemented()),
      AtomicNonBlockingMethod("decrementAndGet", s"()$desc", ReadWrite()),
      AtomicNonBlockingMethod("compareAndSet", s"($desc$desc)Z", ReadWrite()),
      AtomicNonBlockingMethod("weakCompareAndSetRelease", s"($desc$desc)Z", NotYetImplemented()),
      AtomicNonBlockingMethod("intValue", s"()I", Read()),
      AtomicNonBlockingMethod("weakCompareAndSetAcquire", s"($desc$desc)Z", NotYetImplemented()),
      AtomicNonBlockingMethod("weakCompareAndSetPlain", s"($desc$desc)Z", NotYetImplemented()),
      AtomicNonBlockingMethod("weakCompareAndSetVolatile", s"($desc$desc)Z", NotYetImplemented()),
      AtomicNonBlockingMethod("shortValue", "()S", Read()),
      AtomicNonBlockingMethod("compareAndExchange", s"($desc$desc)$desc", ReadWrite()),
      AtomicNonBlockingMethod("incrementAndGet", s"()$desc", ReadWrite()),
      AtomicNonBlockingMethod("setRelease", s"($desc)V", NotYetImplemented()),
      AtomicNonBlockingMethod("compareAndExchangeRelease", s"($desc$desc)$desc", NotYetImplemented()),
      AtomicNonBlockingMethod("doubleValue", "()D", Read()),
      AtomicNonBlockingMethod("getAndAccumulate", s"(${desc}Ljava/util/function/${prefix}BinaryOperator;)${desc}", ReadWrite()),
      AtomicNonBlockingMethod("getAcquire", s"()${desc}", NotYetImplemented()),
      AtomicNonBlockingMethod("byteValue", "()B", Read()),
      AtomicNonBlockingMethod("getAndUpdate", s"(Ljava/util/function/${prefix}UnaryOperator;)${desc}", ReadWrite()),
      AtomicNonBlockingMethod("getAndSet", s"(${desc})${desc}", ReadWrite()),
      AtomicNonBlockingMethod("setOpaque", s"(${desc})V", NotYetImplemented()),
      AtomicNonBlockingMethod("getPlain", s"()${desc}", NotYetImplemented()),
      AtomicNonBlockingMethod("floatValue", "()F", Read()),
      AtomicNonBlockingMethod("set", s"(${desc})V", Write()),
      AtomicNonBlockingMethod("getAndAdd", s"(${desc})${desc}", ReadWrite()),
      AtomicNonBlockingMethod("accumulateAndGet", s"(${desc}Ljava/util/function/${prefix}BinaryOperator;)${desc}", ReadWrite()),
      AtomicNonBlockingMethod("updateAndGet", s"(Ljava/util/function/${prefix}UnaryOperator;)${desc}", ReadWrite()),
      AtomicNonBlockingMethod("getOpaque", s"()${desc}", NotYetImplemented()),
      AtomicNonBlockingMethod("getAndDecrement", s"()${desc}", ReadWrite()),
      AtomicNonBlockingMethod("compareAndExchangeAcquire", s"(${desc}${desc})${desc}", NotYetImplemented()),
      AtomicNonBlockingMethod("get", s"()${desc}", Read()),
      AtomicNonBlockingMethod("toString", "()Ljava/lang/String;", Read()),
      AtomicNonBlockingMethod("longValue", "()J", Read()),
      AtomicNonBlockingMethod("lazySet", s"(${desc})V", NotYetImplemented())
    )

}

object AtomicIntegerOrLongFactory {

  def atomicInteger() : AtomicNonBlocking =
    new AtomicIntegerOrLongFactory("AtomicInteger","I","Int").atomic();

  def atomicLong(): AtomicNonBlocking =
    new AtomicIntegerOrLongFactory("AtomicLong", "J", "Long").atomic();



}
