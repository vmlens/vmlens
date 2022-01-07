package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder

import org.hamcrest.CoreMatchers.{equalTo, is}
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

import scala.collection.mutable.{HashSet, Set}

class TestPermutationIterator {

  def runTest(length: Int, expectedSet: Set[List[Boolean]], expectedCount: Int): Unit = {
    val iterator = new PermutationIterator(length);
    val actual = new HashSet[List[Boolean]]();
    var count = 0;
    while (iterator.hasNext) {
      var list = List[Boolean]()
      for (i <- 0 until length) {
        list = iterator.at(i) +: list;
      }
      actual += list
      iterator.advance();
      count = count + 1;
    }
    assertThat(actual, equalTo(expectedSet))
    assertThat(count, is(expectedCount))
  }


  @Test
  def testSizeZero() = {
    val expected = new HashSet[List[Boolean]]();
    runTest(0, expected, 0)
  }

  @Test
  def testSizeOne() = {
    val expected = new HashSet[List[Boolean]]();
    expected += List(false);
    expected += List(true);
    runTest(1, expected, 2)
  }

  @Test
  def testSizeTwo() = {
    val expected = new HashSet[List[Boolean]]();
    expected += List(false, false);
    expected += List(true, false);
    expected += List(false, true);
    expected += List(true, true);
    runTest(2, expected, 4)
  }

}
