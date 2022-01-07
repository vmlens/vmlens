package com.vmlens.trace.agent.bootstrap.interleave.dsl
import com.vmlens.trace.agent.bootstrap.interleave.domain.SyncAction
import com.vmlens.trace.agent.bootstrap.interleave.domain.syncAction.VolatileFieldAccess

import scala.collection.mutable.ArrayBuffer

class VolatileFieldAccessModel(val fieldId : Int, val operation : Int) extends SyncActionModel {
  override def add(intermediate: ArrayBuffer[SyncAction]): Unit = {
    intermediate += new VolatileFieldAccess(fieldId,operation);
  }
}
