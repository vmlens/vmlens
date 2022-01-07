package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.interleave.domain.{Position, SyncActionAndPosition, SyncAction}
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList

import scala.collection.mutable.ArrayBuffer

class ThreadModel(val threadIndex : Int,val syncActionModelList : Seq[SyncActionModel] ) {

  def add(result: TLinkedList[TLinkableWrapper[SyncActionAndPosition]]) = {
    val intermediate = new  ArrayBuffer[SyncAction]
    for( model <- syncActionModelList ) {
      model.add(intermediate);
    }
    var position = 0;
    for( fromRun <-  intermediate ) {
      result.add(new TLinkableWrapper(new SyncActionAndPosition(2,new Position(threadIndex,position) , fromRun)))
      position = position + 1;
    }

  }

}
