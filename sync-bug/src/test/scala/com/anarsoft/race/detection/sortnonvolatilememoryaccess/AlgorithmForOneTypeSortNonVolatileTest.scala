package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AlgorithmForOneTypeSortNonVolatileTest extends AnyFlatSpec with Matchers {

  "AlgorithmForOneTypeSortNonVolatile" should "addMethodEvents events with the same thread index to the list" in {
    // Given
    val memoryAccessEventBuilder = new MemoryAccessEventBuilder();
    val algorithm = new AlgorithmForOneTypeSortNonVolatile[NonVolatileMemoryAccessEventGuineaPig](null, null);

    // When
    algorithm.prozess(memoryAccessEventBuilder.read());
    algorithm.prozess(memoryAccessEventBuilder.write());

    // Then
    algorithm.sortedMemoryAccessList.list.size should be(2)

  }
}
