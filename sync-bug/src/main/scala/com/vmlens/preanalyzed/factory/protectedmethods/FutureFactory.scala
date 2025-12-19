package com.vmlens.preanalyzed.factory.protectedmethods

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{FUTURE_GET_STATE, FUTURE_GET, FUTURE_SET, METHOD_ENTER_EXIT}


object FutureFactory {

  def futureTask(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/FutureTask", methods(),List("done", "toString"))
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "()Ljava/lang/Object;", FUTURE_GET),
    new MethodToMethodType("get", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", FUTURE_GET),
    new MethodToMethodType("set", "(Ljava/lang/Object;)V", FUTURE_SET),
    new MethodToMethodType( "run", "()V", METHOD_ENTER_EXIT),
    new MethodToMethodType("setException", "(Ljava/lang/Throwable;)V", FUTURE_SET   ),
    new MethodToMethodType("exceptionNow", "()Ljava/lang/Throwable;", FUTURE_SET   ),
    new MethodToMethodType("runAndReset", "()Z", FUTURE_SET   ),
    new MethodToMethodType("cancel", "(Z)Z",  FUTURE_SET  ),
    new MethodToMethodType("state", "()Ljava/util/concurrent/Future$State;",  FUTURE_GET_STATE  ),
    new MethodToMethodType("resultNow", "()Ljava/lang/Object;",  FUTURE_GET_STATE  ),
    new MethodToMethodType("isDone", "()Z",  FUTURE_GET_STATE  ),
    new MethodToMethodType("isCancelled", "()Z",   FUTURE_GET_STATE )
   
  );
  
}
