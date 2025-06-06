package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicClassNonBlockingMethod, Read, ReadWrite, Write}

private class AdderFactory(val className : String, val desc : String, val additional :  List[AtomicClassNonBlockingMethod]) {
  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods() ++ additional);

  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("doubleValue","()D",  Read()  ),
    AtomicClassNonBlockingMethod("intValue","()I", Read()   ),
    AtomicClassNonBlockingMethod("reset","()V", Write()  ),
    AtomicClassNonBlockingMethod("byteValue","()B",  Read()   ),
    AtomicClassNonBlockingMethod("shortValue","()S",   Read()  ),
    AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;", Read()   ),
    AtomicClassNonBlockingMethod("longValue","()J", Read()   ),
    AtomicClassNonBlockingMethod("floatValue","()F",  Read()  ),
    AtomicClassNonBlockingMethod("add",s"($desc)V", Write()  ),
    AtomicClassNonBlockingMethod("sum",s"()$desc", Read()  ),
    AtomicClassNonBlockingMethod("sumThenReset",s"()$desc", ReadWrite() )
  );

}

object AdderFactory {

  def doubleAdder(): AtomicNonBlocking =
    new AdderFactory("DoubleAdder", "D", List()).atomic();

  def longAdder(): AtomicNonBlocking =
    new AdderFactory("LongAdder", "J", List(
      AtomicClassNonBlockingMethod("decrement","()V",  Write()  ),
      AtomicClassNonBlockingMethod("increment","()V",  Write()) )).atomic();
}
