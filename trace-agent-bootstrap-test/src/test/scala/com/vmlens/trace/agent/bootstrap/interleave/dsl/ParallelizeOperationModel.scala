package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.interleave.Position
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.BlockFactory


trait ParallelizeOperationModel {
  def create(activeThreadCount : Int,position : Position): BlockFactory;

}
