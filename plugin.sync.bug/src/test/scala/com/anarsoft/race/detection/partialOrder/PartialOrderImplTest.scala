package com.anarsoft.race.detection.partialOrder

import com.anarsoft.race.detection.partialOrder.WithPositionImpl.pos
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PartialOrderImplTest extends AnyFlatSpec with Matchers {

  "PartialOrderImpl" should "give the direct order" in {
    // Given
    val partialOrderContainer = new PartialOrderContainer();
    partialOrderContainer.addLeftBeforeRight(pos(10, 1L), pos(15, 10L));
    partialOrderContainer.addLeftBeforeRight(pos(5, 1L), pos(20, 10L));
    val partialOrderImpl = new PartialOrderImpl(partialOrderContainer);

    // Then
    partialOrderImpl.isLeftBeforeRight(pos(3, 1L), pos(16, 10L)) should be(true)
    partialOrderImpl.isLeftBeforeRight(pos(3, 1L), pos(12, 10L)) should be(false)
  }

}
