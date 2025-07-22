package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.AlgorithmForOneType
import com.anarsoft.race.detection.sortutil.{NonVolatileEventContainer, ThreadIdToLastSortableEvent}

import scala.collection.mutable.ArrayBuffer

private class AlgorithmForOneTypeSortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(private val partialOrder: PartialOrder, val result: ArrayBuffer[SortedMemoryAccessList])
  extends AlgorithmForOneType[EVENT] {

  // visible for test 
  val sortedMemoryAccessList = new SortedMemoryAccessList();
  
  private val threadIdToLastSortableEvent = new ThreadIdToLastSortableEvent[EVENT](
    (event) => NonVolatileEventContainer[EVENT](event));

  override def prozess(event: EVENT): Unit = {
    var sortable = true;
    threadIdToLastSortableEvent.foreachOpposite(event, previous => {
      if (!partialOrder.isLeftBeforeRight(previous, event)) {
        sortable = false;
        sortedMemoryAccessList.setDataRace(previous)
      }
    });
    threadIdToLastSortableEvent.put(event);
    if (sortable) {
      sortedMemoryAccessList.add(event)
    } else {
      sortedMemoryAccessList.addDataRace(event)
    }
  }

  override def stop(): Unit = {
    if (sortedMemoryAccessList.dataRaces.nonEmpty) {
      result.append(sortedMemoryAccessList);
    }
    
  }
}
