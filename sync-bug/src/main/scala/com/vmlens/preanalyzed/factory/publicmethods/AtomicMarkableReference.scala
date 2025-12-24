package com.vmlens.preanalyzed.factory.publicmethods

import com.vmlens.preanalyzed.model.{AtomicClassNonBlockingMethod, MethodToMethodType, NotYetImplemented, Read, ReadWrite, Write}
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_READ, ATOMIC_FIELD_READ_WRITE, ATOMIC_FIELD_WRITE}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.{NonBlockingMethod, NotYetImplementedMethod}

object AtomicMarkableReference {

  def atomicMarkableReference(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicMarkableReference", methods(), List())
  }
  
  
  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("attemptMark","(Ljava/lang/Object;Z)Z",  NonBlockingMethod.NON_BLOCKING_READ_WRITE ),
    new MethodToMethodType("getReference","()Ljava/lang/Object;", NonBlockingMethod.NON_BLOCKING_READ ),
    new MethodToMethodType("get","([Z)Ljava/lang/Object;", NonBlockingMethod.NON_BLOCKING_READ   ),
    new MethodToMethodType("set","(Ljava/lang/Object;Z)V", NonBlockingMethod.NON_BLOCKING_WRITE  ),
    new MethodToMethodType("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z",NonBlockingMethod.NON_BLOCKING_READ_WRITE  ),
    new MethodToMethodType("isMarked","()Z", NonBlockingMethod.NON_BLOCKING_READ   ),
    new MethodToMethodType("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z",   NotYetImplementedMethod.SINGLETON  )
  );
  

}
