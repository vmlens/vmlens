package com.vmlens.preanalyzed.model.lockoperation

import com.vmlens.preanalyzed.model.LockType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.AbstractMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.LockMethod.NEW_CONDITION;

class NewCondition extends LockOperation {

  override def create(lockType: LockType): AbstractMethodType = {
    NEW_CONDITION;
  }
}
