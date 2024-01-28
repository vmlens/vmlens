package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.anarsoft.race.detection.processEventByType.{AlgorithmForEvent, AlgorithmForOneType}

private class AlgorithmForEventSortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(val partialOrder: PartialOrder,
 val sortedListBuilder: MemoryAccessReportBuilder)
  extends AlgorithmForEvent[EVENT] {

  override def create(event: EVENT): AlgorithmForOneType[EVENT] = {
    new AlgorithmForOneTypeSortNonVolatile[EVENT](partialOrder, new SortedMemoryAccessList[EVENT]());
  }

}
