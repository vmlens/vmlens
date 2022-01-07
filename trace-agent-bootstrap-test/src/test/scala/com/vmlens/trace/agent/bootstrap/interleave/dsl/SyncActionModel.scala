package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.interleave.domain.SyncAction

import scala.collection.mutable.ArrayBuffer

trait SyncActionModel {
  def add(intermediate: ArrayBuffer[SyncAction]): Unit;

}
