package com.anarsoft.race.detection.partialorder

import com.anarsoft.race.detection.partialorder.WithPositionImpl.pos
import com.vmlens.report.assertion.OnDescriptionAndLeftBeforeRightNoOp
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PartialOrderImplTest extends AnyFlatSpec with Matchers {

  "PartialOrderImpl" should "give the direct order" in {
    // Given
    val partialOrderContainer = new PartialOrderContainer(new OnDescriptionAndLeftBeforeRightNoOp());
    partialOrderContainer.addLeftBeforeRight(pos(10, 1), pos(15, 10));
    partialOrderContainer.addLeftBeforeRight(pos(5, 1), pos(20, 10));
    val partialOrderImpl = new PartialOrderImpl(partialOrderContainer);

    // Then
    partialOrderImpl.isLeftBeforeRight(pos(3, 1), pos(16, 10)) should be(true)
    partialOrderImpl.isLeftBeforeRight(pos(3, 1), pos(12, 10)) should be(false)
  }
  
}
