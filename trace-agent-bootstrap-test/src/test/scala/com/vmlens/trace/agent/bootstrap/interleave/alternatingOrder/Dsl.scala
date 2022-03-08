package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement
import com.vmlens.trace.agent.bootstrap.interleave.{LeftBeforeRight, Position}
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList

object Dsl {

  def o(alternatingOrderElements: AlternatingOrderElement*) = {
    val order =  Array.ofDim[TLinkableWrapper[AlternatingOrderElement]]( alternatingOrderElements.length);
    var index = 0;
    for( elem <-  alternatingOrderElements ) {
      order(index) = new TLinkableWrapper(elem);
      index = index + 1;
    }
    order;
  }

  def ao(left: Position, right: Position) = {
    AlternatingOrderElement.of(left, right);
  }

  def p(threadIndex: Int, positionInThread: Int) = {
    new Position(threadIndex, positionInThread);
  }

  def lp(threadIndex: Int, positionInThread: Int) = {
    new TLinkableWrapper(new Position(threadIndex, positionInThread));
  }

  def l(left: Position, right: Position) = {
    new LeftBeforeRight(left, right);
  }

  def order(leftBeforeRight: LeftBeforeRight*) = {
    val order = new TLinkedList[TLinkableWrapper[LeftBeforeRight]]();
    for( elem <-  leftBeforeRight ) {
      order.add( new TLinkableWrapper(elem));
    }
    order;
  }


}
