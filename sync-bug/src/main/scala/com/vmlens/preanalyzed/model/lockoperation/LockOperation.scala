package com.vmlens.preanalyzed.model.lockoperation

import com.vmlens.preanalyzed.model.LockType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.AbstractMethodType

trait LockOperation {

  def create(lockType: LockType) : AbstractMethodType;

}

