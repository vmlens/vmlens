package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import org.mockito.Mockito.mock
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder

class AlgorithmForOneTypeSortNonVolatileTest extends AnyFlatSpec with Matchers  {

  "AlgorithmForOneTypeSortNonVolatile" should "addMethodEvents events with the same thread index to the list" in {
    // Given
    val memoryAccessEventBuilder = new MemoryAccessEventBuilder(1).threadId(5);
    val algorithm = new AlgorithmForOneTypeSortNonVolatile[NonVolatileMemoryAccessEventGuineaPig](null, null);

    // When
    algorithm.prozess(memoryAccessEventBuilder.read());
    algorithm.prozess(memoryAccessEventBuilder.write());

    // Then
    algorithm.sortedMemoryAccessList.list.size should be(2)

  }

  "AlgorithmForOneTypeSortNonVolatile" should " detect a data race for two writes " in {
    // Given
    val partialOrder = mock(classOf[PartialOrder]);
    val algorithm = new AlgorithmForOneTypeSortNonVolatile[NonVolatileMemoryAccessEventGuineaPig](partialOrder, null);
    val memoryAccessEventBuilder = new MemoryAccessEventBuilder(1);

    // When
    algorithm.prozess(memoryAccessEventBuilder.threadId(0).write());
    algorithm.prozess(memoryAccessEventBuilder.threadId(1).write());

    // Then
    algorithm.sortedMemoryAccessList.dataRaces.isEmpty should be(false)
  }

  "AlgorithmForOneTypeSortNonVolatile" should " detect a data race for read before write " in {
    // Given
    val partialOrder = mock(classOf[PartialOrder]);
    val algorithm = new AlgorithmForOneTypeSortNonVolatile[NonVolatileMemoryAccessEventGuineaPig](partialOrder, null);
    val memoryAccessEventBuilder = new MemoryAccessEventBuilder(1);

    // When
    algorithm.prozess(memoryAccessEventBuilder.threadId(0).read());
    algorithm.prozess(memoryAccessEventBuilder.threadId(1).write());

    // Then
    algorithm.sortedMemoryAccessList.dataRaces.isEmpty should be(false)
  }
}
