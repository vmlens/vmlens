package com.vmlens.trace.agent.bootstrap.interleave

import com.vmlens.test.TestLogger
import com.vmlens.trace.agent.bootstrap.interleave.dsl.Dsl.{end, join, read, run, start, t, threadBegin, write}
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test

import java.util

class TestThreadJoinAndEnd {

  val logger = new TestLogger(true);
  
  @Test
  def testThreadStartBeginCreatesFixedOrder(): Unit = {
    // Given
    val givenActualRun = run(
      t(
        read(),
        end()
      ),
      t(
        join(0),
        write()
      )
    );
    // When
    val sut = new InterleaveContainer(logger);
    sut.addActualRun(givenActualRun);
    // Then
    val result = new util.LinkedList[String]()
    var count = 0;
    while (sut.hasNext) {
      val current = sut.next();
      result.add(current.toString);
      println(current)
      count = count + 1;
    }
    assertThat(count, is(1));
    assertThat(result, contains("0,0,1,1"));
  }

}
