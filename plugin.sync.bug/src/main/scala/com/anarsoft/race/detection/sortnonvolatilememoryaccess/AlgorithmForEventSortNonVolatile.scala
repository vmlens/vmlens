package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.{AlgorithmForEvent, AlgorithmForOneType}
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder


private class AlgorithmForEventSortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(val partialOrder: PartialOrder, val sortedListBuilder: RunReportForNonVolatileMemoryAccessBuilder)
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
