package com.anarsoft.race.detection.inttest

import com.vmlens.report.assertion.LeftBeforeRight
import com.vmlens.report.assertion.OnEventNoOp
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.{MonitorKey, ReentrantLockKey}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.anarsoft.race.detection.process.run.{ProcessRunContextBuilder, ProcessRunImpl}

import scala.collection.mutable

class ConditionWaitIntTest  extends AnyFlatSpec with Matchers{
  /**
   * wait enter has the same effect as lock exit
   * so enter to exit should lead to a partial order 
   * while exit to enter not
   */
  "wait enter exit" should "lead to a partial order" in {
    // Given
    val runTestBuilder = new RunDataTestBuilder(1,1); 
    val firstThread = runTestBuilder.createEventTestBuilder(0);
    val secondThread =  runTestBuilder.createEventTestBuilder(1);
    val lockKey = new ReentrantLockKey(100L);
    
    val enter0 = firstThread.conditionWaitEnter(lockKey)
    val exit1 = secondThread.conditionWaitExit(lockKey)

    // expected
    val expected = new mutable.HashSet[LeftBeforeRight]();
    expected.add(new LeftBeforeRight(enter0, exit1))
    
    // When
    val collected = new mutable.HashSet[LeftBeforeRight]();
    val data = runTestBuilder.build();
    new ProcessRunImpl(new ProcessRunContextBuilder()
      .withOnDescriptionAndLeftBeforeRight(new CollectLeftBeforeRight(collected))
      .build()).process(data);
    
    // Then
    collected should be(expected)
  }
  
}
