package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import com.vmlens.trace.agent.bootstrap.interleave.domain.{AlternatingOrderElement, Position}
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper

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


}
