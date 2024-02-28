package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.anarsoft.race.detection.processEventByType.{AlgorithmForEvent, AlgorithmForOneType}
import com.vmlens.report.dataView.MemoryAccessReportBuilder

private class AlgorithmForEventSortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(val partialOrder: PartialOrder, val sortedListBuilder: MemoryAccessReportBuilder)
  extends AlgorithmForEvent[EVENT] {

  var previous: Option[SortedMemoryAccessList[EVENT]] = None;

  override def create(event: EVENT): AlgorithmForOneType[EVENT] = {
    previous.foreach(builder => builder.buildResult(sortedListBuilder));

    val builder = new SortedMemoryAccessList[EVENT]();
    previous = Some(builder);
    new AlgorithmForOneTypeSortNonVolatile[EVENT](partialOrder, builder);
  }

  def done(): Unit = {
    previous.foreach(builder => builder.buildResult(sortedListBuilder));
  }

}
