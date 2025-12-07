package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.CONDITION_AWAIT

object ConditionFactory {

  def condition(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject", methods(),List())
  }

  private def methods() : List[MethodToMethodType] = List(
    new MethodToMethodType("await", "()V" , CONDITION_AWAIT) ,
    new MethodToMethodType("await", "(JLjava/util/concurrent/TimeUnit;)Z" , CONDITION_AWAIT),
    new MethodToMethodType("awaitUntil", "(Ljava/util/Date;)Z" , CONDITION_AWAIT),
    new MethodToMethodType("awaitNanos", "(J)J" , CONDITION_AWAIT),
    new MethodToMethodType("awaitUninterruptibly", "()V" , CONDITION_AWAIT));
  //  new MethodToMethodType("signalAll", "()V" , CONDITION_AWAIT),
  //  new MethodToMethodType("signal",  "()V", CONDITION_AWAIT));

}
