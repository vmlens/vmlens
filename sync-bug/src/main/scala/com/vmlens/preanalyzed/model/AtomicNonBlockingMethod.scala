package com.vmlens.preanalyzed.model

case class AtomicNonBlockingMethod(name : String, desc : String, methodType : AtomicNonBlockingMethodType)
  extends AtomicMethod
