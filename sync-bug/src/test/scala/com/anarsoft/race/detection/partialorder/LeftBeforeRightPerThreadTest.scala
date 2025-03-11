package com.anarsoft.race.detection.partialorder

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LeftBeforeRightPerThreadTest extends AnyFlatSpec with Matchers {

  "LeftBeforeRightPerThread" should "give the maximum left position" in {
    // Given
    val leftBeforeRightPerThread = new LeftBeforeRightPerThread();

    // When
    leftBeforeRightPerThread.addLeftBeforeRight(50, 100);
    leftBeforeRightPerThread.addLeftBeforeRight(20, 100);
    leftBeforeRightPerThread.addLeftBeforeRight(10, 120);

    //Then
    leftBeforeRightPerThread.maxPositionForKeyBefore(110) should be(Option(50))
    leftBeforeRightPerThread.maxPositionForKeyBefore(120) should be(Option(50))
    leftBeforeRightPerThread.maxPositionForKeyBefore(90) should be(empty)
  }

}
