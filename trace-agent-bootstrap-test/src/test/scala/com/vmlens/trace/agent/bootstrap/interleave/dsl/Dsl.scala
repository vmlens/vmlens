package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType.{IS_READ, IS_WRITE}
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.BlockFactory
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.*
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList

/**
 * since this is an object which is used in multiple object it must not contain mutable state
 */
object Dsl {



  def run(thread: ThreadModel*) = {
    val result = new TLinkedList[TLinkableWrapper[BlockFactory]]();
    var currentThreadIndex = 0;
    for (model <- thread) {
      model.add(currentThreadIndex,result);
      currentThreadIndex = currentThreadIndex + 1;
    }
    result;
  }

  def t(syncAction: ParallelizeOperationModel*) = {
    val result = new ThreadModel(syncAction);
    result;
  }

  def m(id: Int)(syncAction: ParallelizeOperationModel*) = {}

  def start(threadIndex : Int) =    new BlockFactoryModel(new ThreadStart(threadIndex));

  def threadBegin() = new BlockFactoryModel(new ThreadBegin());

  def end()  = new BlockFactoryModel(new ThreadEnd());

  def join(threadId : Int)  = new BlockFactoryModel(new ThreadJoin(threadId));

  def read() = {
    new BlockFactoryModel(new VolatileFieldAccess(1, IS_READ));
  }

  def write() = {
    new BlockFactoryModel(new VolatileFieldAccess(1, IS_WRITE));
  }


}
