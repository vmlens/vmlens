package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.CONDITION_AWAIT

object ConditionFactory {

  def condition(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject", methods())
  }

  private def methods() : List[MethodToMethodType] = List(new MethodToMethodType("await", "()V" , CONDITION_AWAIT));
  
  
}
