package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicClassNonBlockingMethod, Read, ReadWrite, Write}

private class AccumulatorFactory(val className : String,val desc : String) {

  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods());

  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("doubleValue","()D", Read()  ),
    AtomicClassNonBlockingMethod("intValue","()I", Read()  ),
    AtomicClassNonBlockingMethod("reset","()V", Write()  ),
    AtomicClassNonBlockingMethod("byteValue","()B", Read()  ),
    AtomicClassNonBlockingMethod("shortValue","()S", Read()  ),
    AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;", Read() ),
    AtomicClassNonBlockingMethod("accumulate",s"($desc)V", Write()  ),
    AtomicClassNonBlockingMethod("getThenReset",s"()$desc", ReadWrite() ),
    AtomicClassNonBlockingMethod("get",s"()$desc", Read() ),
    AtomicClassNonBlockingMethod("longValue","()J", Read() ),
    AtomicClassNonBlockingMethod("floatValue","()F", Read() )
  );

}

object AccumulatorFactory {

  def doubleAccumulator(): AtomicNonBlocking =
    new AccumulatorFactory("DoubleAccumulator", "D").atomic();

  def longAccumulator(): AtomicNonBlocking =
    new AccumulatorFactory("LongAccumulator" , "J").atomic();

}
