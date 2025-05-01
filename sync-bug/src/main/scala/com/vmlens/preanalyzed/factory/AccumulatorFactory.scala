package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, Read, ReadWrite, Write}

private class AccumulatorFactory(val className : String,val desc : String) {

  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods());

  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("doubleValue","()D", Read()  ),
    AtomicNonBlockingMethod("intValue","()I", Read()  ),
    AtomicNonBlockingMethod("reset","()V", Write()  ),
    AtomicNonBlockingMethod("byteValue","()B", Read()  ),
    AtomicNonBlockingMethod("shortValue","()S", Read()  ),
    AtomicNonBlockingMethod("toString","()Ljava/lang/String;", Read() ),
    AtomicNonBlockingMethod("accumulate",s"($desc)V", Write()  ),
    AtomicNonBlockingMethod("getThenReset",s"()$desc", ReadWrite() ),
    AtomicNonBlockingMethod("get",s"()$desc", Read() ),
    AtomicNonBlockingMethod("longValue","()J", Read() ),
    AtomicNonBlockingMethod("floatValue","()F", Read() )
  );

}

object AccumulatorFactory {

  def doubleAccumulator(): AtomicNonBlocking =
    new AccumulatorFactory("DoubleAccumulator", "D").atomic();

  def longAccumulator(): AtomicNonBlocking =
    new AccumulatorFactory("LongAccumulator" , "J").atomic();

}
