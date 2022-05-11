package com.vmlens.trace.agent.bootstrap.interleave

import com.vmlens.test.TestLogger
import com.vmlens.trace.agent.bootstrap.interleave.dsl.Dsl.*
import com.vmlens.trace.agent.bootstrap.interleave.facade.InterleaveContainer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.{contains, hasItem, hasItems}
import org.hamcrest.CoreMatchers.is
import org.junit.Test

import java.util

class TestOneVolatileField {

  val logger = new TestLogger(false);

  @Test
  def testSingleReadWrite(): Unit = {
    // Given
    val givenActualRun = run(
      t(
        read()
      ),
      t(
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
      count = count + 1;
    }
    assertThat(count, is(2));
    assertThat(result, contains("1,0" , "0,1"));
  }

  @Test
  def testIncrement(): Unit = {
    // Given
    val givenActualRun = run(
      t(
        read(),
        write()
      ),
      t(
        read(),
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
      result.add(current.toString())
      count = count + 1;
    }
    assertThat(count,is(3))
    assertThat(result, hasItem("1,1,0,0" ));
    assertThat(result, hasItem("0,0,1,1" ));
    assertThat(result, hasItem("1,0,1,0" ));
  }


  // Fixme
  def testOneReadAndThreeWrites(): Unit = {

  }


}
