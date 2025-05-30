package com.vmlens.preanalyzed.model


sealed trait AtomicNonBlockingMethod extends AtomicMethod;

case class AtomicArrayNonBlockingMethod(name: String, desc: String, methodType: AtomicNonBlockingMethodType)
  extends AtomicNonBlockingMethod

case class AtomicClassNonBlockingMethod(name : String, desc : String, methodType : AtomicNonBlockingMethodType)
  extends AtomicNonBlockingMethod
