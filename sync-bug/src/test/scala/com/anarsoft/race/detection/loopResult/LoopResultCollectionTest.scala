package com.anarsoft.race.detection.loopResult

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LoopResultCollectionTest extends AnyFlatSpec with Matchers {

  "failure" should "should never be overridden" in {
    // Given
    val failure = new RunResultMock(true, 0 , 0 , Set());
    val dataRace = new RunResultMock(false, 1, 1, Set());

    val loopResultCollection = new LoopResultCollection();

    // When
    loopResultCollection.put(failure);
    loopResultCollection.put(dataRace);

    // Then
    loopResultCollection.build().head.runResult.runId should be(0)

  }

  "failure" should "should always override" in {
    // Given
    val dataRace = new RunResultMock(false, 1, 0, Set(6));
    val failure = new RunResultMock(true, 0, 1, Set());

    val loopResultCollection = new LoopResultCollection();

    // When
    loopResultCollection.put(dataRace);
    loopResultCollection.put(failure);
    
    // Then
    loopResultCollection.build().head.runResult.runId should be(1)
  }

}
