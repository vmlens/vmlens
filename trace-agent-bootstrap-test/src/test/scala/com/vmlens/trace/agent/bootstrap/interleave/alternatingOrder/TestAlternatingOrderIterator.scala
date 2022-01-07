package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import com.vmlens.trace.agent.bootstrap.interleave.domain.LeftBeforeRight.of
import com.vmlens.trace.agent.bootstrap.interleave.domain.{AlternatingOrderElement, LeftBeforeRight, Position}
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import org.hamcrest.CoreMatchers.{equalTo, is}
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TestAlternatingOrderIterator {

  @Test
  def testOneAlternatingOrder() = {
    // given
    val optionalAlternatingOrderElements =
      new Array[TLinkableWrapper[AlternatingOrderElement]](1)
    optionalAlternatingOrderElements(0) = l(p(0, 0), p(0, 1))
    // when
    val iterator = new AlternatingOrderIterator(optionalAlternatingOrderElements);
    // then
    assertThat(iterator.hasNext, is(true));
    val first = iterator.next();
    assertThat(first(0), equalTo(of(p(0, 1), p(0, 0))))
    assertThat(iterator.hasNext, is(true));
    val second = iterator.next();
    assertThat(second(0), equalTo(of(p(0, 0), p(0, 1))))
    assertThat(iterator.hasNext, is(false));
  }

  def p(thread: Int, position: Int) = {
    new Position(thread, position);
  }

  def l(left: Position, right: Position) = {
    new TLinkableWrapper(new AlternatingOrderElement(new LeftBeforeRight(left, right)));
  }

}
