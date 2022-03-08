package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.interleave.Position
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.{BlockFactory, SyncAction}

class BlockFactoryModel(val blockFactory : SyncAction) extends ParallelizeOperationModel {

      def create(activeThreadCount : Int,position : Position) = {
        new BlockFactory( position,activeThreadCount, blockFactory)
      }
}
