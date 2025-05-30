package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicArrayNonBlockingMethod, AtomicNonBlocking, AtomicNonBlockingMethod, Read}

object ForGuineaPig {

  def forGuineaPig(): AtomicNonBlocking = AtomicNonBlocking("com/vmlens/test/guineapig/MethodWithIntParam", atomicMethods());


  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicArrayNonBlockingMethod("call", "(ILjava/lang/Object;)V", Read())
  );

}
