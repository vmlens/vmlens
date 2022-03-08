package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import com.vmlens.test.TestLogger
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.Dsl.{ao, o, p}
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.is
import org.junit.Test


class TestAlternatingOrder {

  val logger = new TestLogger(false);

  @Test
  def testSort(): Unit = {
    // Given
    val unsorted = o(
      ao(p(0, 3), p(4, 0)),
      ao(p(0, 1), p(2, 2)),
      ao(p(0, 3), p(2, 2)));
    val expected = o(
      ao(p(0, 1), p(2, 2)),
      ao(p(0, 3), p(2, 2)),
      ao(p(0, 3), p(4, 0)));
    // When
    AlternatingOrderContainer.sort(unsorted);
    // Then
    assertThat(unsorted, is(expected));
  }

  @Test
  def testEquals() = {
    // Given
    val unsorted = o(
      ao(p(0, 3), p(4, 0)),
      ao(p(0, 3), p(2, 2)));
    val sorted = o(
      ao(p(0, 3), p(2, 2)),
      ao(p(0, 3), p(4, 0)));
    val first = new AlternatingOrderContainer(logger,new FixedAndAlternatingOrder(Array.ofDim[TLinkableWrapper[LeftBeforeRight]]( 0) ,unsorted), Array(4, 9, 2));
    val second = new AlternatingOrderContainer(logger,new FixedAndAlternatingOrder(Array.ofDim[TLinkableWrapper[LeftBeforeRight]](  0), sorted), Array(4, 9, 2));
    // Then
    assert( first.equals(second), is(true));
  }

  // Fixme test für nicht gleich und fixed

}
