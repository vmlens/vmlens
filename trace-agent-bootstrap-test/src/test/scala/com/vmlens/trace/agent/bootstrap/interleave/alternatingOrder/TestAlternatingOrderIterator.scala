package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.of
import com.vmlens.test.SameList
import com.vmlens.trace.agent.bootstrap.interleave.{LeftBeforeRight, Position}
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.{AlternatingOrderElement, AlternatingOrderIterator}
import com.vmlens.trace.agent.bootstrap.interleave.Position
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList
import org.hamcrest.CoreMatchers.{equalTo, is}
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TestAlternatingOrderIterator {


  @Test
  def testOneAlternatingOrder() = {
    // given
    val expectedFirst = new TLinkedList[TLinkableWrapper[LeftBeforeRight]]();
    expectedFirst.add(new TLinkableWrapper(of(p(0, 1), p(0, 0))));
    val expectedSecond = new TLinkedList[TLinkableWrapper[LeftBeforeRight]]();
    expectedSecond.add(new TLinkableWrapper(of(p(0, 0), p(0, 1))));
    val optionalAlternatingOrderElements =
      new Array[TLinkableWrapper[AlternatingOrderElement]](1)
    optionalAlternatingOrderElements(0) = l(p(0, 0), p(0, 1))
    // when
    val iterator = new AlternatingOrderIterator(  new Array[TLinkableWrapper[LeftBeforeRight]](0),optionalAlternatingOrderElements);
    // then
    assertThat(iterator.hasNext, is(true));
    val first = iterator.next();
    assertThat(first,  new SameList(expectedFirst))
    assertThat(iterator.hasNext, is(true));
    val second = iterator.next();
    assertThat(second, new SameList(expectedSecond))
    assertThat(iterator.hasNext, is(false));
  }

  def p(thread: Int, position: Int) = {
    new Position(thread, position);
  }

  def l(left: Position, right: Position) = {
    new TLinkableWrapper(new AlternatingOrderElement(new LeftBeforeRight(left, right)));
  }

}
