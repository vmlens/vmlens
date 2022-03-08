package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import com.vmlens.test.SameList
import com.vmlens.trace.agent.bootstrap.interleave.Position
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CreateCalculatedRunForOrder
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.nullValue
import org.junit.Test
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.Dsl.{ao, l, lp, order, p}

class TestCreateCalculatedRunForOrder {

  // Fixme
  def testTransitive() = {

  }

  // Fixme
  def testLargeCircle() = {

  }


  @Test
  def testZeroOrder(): Unit = {
    val expected = new TLinkedList[TLinkableWrapper[Position]]();
    expected.add(lp(1, 0));
    expected.add(lp(0, 0));
    expected.add(lp(0, 1));
    expected.add(lp(0, 2));


    val create = new CreateCalculatedRunForOrder(order(), Array(3, 1));
    val result = create.create();

    assertThat(result, new SameList(expected));
  }


  @Test
  def testOneOrder(): Unit = {
    val expected = new TLinkedList[TLinkableWrapper[Position]]();
    expected.add(lp(0, 0));
    expected.add(lp(0, 1));
    expected.add(lp(0, 2));
    expected.add(lp(1, 0));

    val create = new CreateCalculatedRunForOrder( order(l(p(0, 2), p(1, 0)))
      , Array(3, 1));
    val result = create.create();

    assertThat(result, new SameList(expected));
  }

  @Test
  def testCycle() = {
    val create = new CreateCalculatedRunForOrder(
      order(l(p(0, 2), p(1, 0)),
       l(p(1, 2), p(0, 0))
      )
      , Array(3, 3));
    val result = create.create();

    assertThat(result, nullValue());
  }

  def lp(index: Int, position: Int) = {
    new TLinkableWrapper(new Position(index, position));
  }


  @Test
  def testOrderAtSamePosition(): Unit = {
    val create = new CreateCalculatedRunForOrder(
      order(l(p(0,1),p(1,0)),
        l(p(1,2),p(2,0)),
        l(p(0,0),p(1,1)),
        l(p(1,0),p(2,0)),
        l(p(0,0),p(2,1)))
      , Array(2, 3 , 2));
    val result = create.create();

    println(result);
  }


}
