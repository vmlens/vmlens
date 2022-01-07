package com.vmlens.trace.agent.bootstrap.interleave.dsl

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType.{IS_READ, IS_WRITE}
import com.vmlens.trace.agent.bootstrap.interleave.domain.SyncActionAndPosition
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList

object Dsl {

  var currentThreadIndex = 0;

  def run(thread: ThreadModel*) = {
    val result = new TLinkedList[TLinkableWrapper[SyncActionAndPosition]]();
    for (model <- thread) {
      model.add(result);
    }
    result;
  }

  def t(syncAction: SyncActionModel*) = {
    val result = new ThreadModel(currentThreadIndex, syncAction);
    currentThreadIndex = currentThreadIndex + 1;
    result;
  }

  def m(id: Int)(syncAction: SyncActionModel*) = {

  }

  def read() = {
    new VolatileFieldAccessModel(1, IS_READ);
  }

  def write() = {
    new VolatileFieldAccessModel(1, IS_WRITE);
  }


}
