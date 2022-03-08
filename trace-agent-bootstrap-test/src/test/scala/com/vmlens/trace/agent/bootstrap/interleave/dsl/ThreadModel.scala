package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.interleave.Position
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.BlockFactory
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList

import scala.collection.mutable.ArrayBuffer

class ThreadModel(val syncActionModelList : Seq[ParallelizeOperationModel] ) {

  def add(threadIndex : Int, result: TLinkedList[TLinkableWrapper[BlockFactory]]) = {
   
    var position = 0;
    for( fromRun <-  syncActionModelList ) {
      result.add(new TLinkableWrapper(fromRun.create(2,new Position(threadIndex,position))))
      position = position + 1;
    }

  }

}
