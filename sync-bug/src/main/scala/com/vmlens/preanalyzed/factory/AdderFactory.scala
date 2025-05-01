package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, Read, ReadWrite, Write}

private class AdderFactory(val className : String, val desc : String, val additional :  List[AtomicNonBlockingMethod]) {
  private def atomic(): AtomicNonBlocking = AtomicNonBlocking(s"java/util/concurrent/atomic/$className",
    atomicMethods() ++ additional);

  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("doubleValue","()D",  Read()  ),
    AtomicNonBlockingMethod("intValue","()I", Read()   ),
    AtomicNonBlockingMethod("reset","()V", Write()  ),
    AtomicNonBlockingMethod("byteValue","()B",  Read()   ),
    AtomicNonBlockingMethod("shortValue","()S",   Read()  ),
    AtomicNonBlockingMethod("toString","()Ljava/lang/String;", Read()   ),
    AtomicNonBlockingMethod("longValue","()J", Read()   ),
    AtomicNonBlockingMethod("floatValue","()F",  Read()  ),
    AtomicNonBlockingMethod("add",s"($desc)V", Write()  ),
    AtomicNonBlockingMethod("sum",s"()$desc", Read()  ),
    AtomicNonBlockingMethod("sumThenReset",s"()$desc", ReadWrite() )
  );

}

object AdderFactory {

  def doubleAdder(): AtomicNonBlocking =
    new AdderFactory("DoubleAdder", "D", List()).atomic();

  def longAdder(): AtomicNonBlocking =
    new AdderFactory("LongAdder", "J", List(
      AtomicNonBlockingMethod("decrement","()V",  Write()  ),
      AtomicNonBlockingMethod("increment","()V",  Write()) )).atomic();
}
