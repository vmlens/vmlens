package com.anarsoft.race.detection.sortnonvolatilememoryaccess


import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder

import scala.collection.mutable.ArrayBuffer

private class SortedMemoryAccessList[EVENT] {

  private val list = new ArrayBuffer[SortedMemoryAccessListElement[EVENT]]();

  def add(memoryAccess: NonVolatileMemoryAccessEvent[EVENT]): Unit = {
    list.append(new SortedMemoryAccessListElement[EVENT](false, memoryAccess));
  }

  def addDataRace(alreadyAdded: NonVolatileMemoryAccessEvent[EVENT],
                  newMemoryAccess: NonVolatileMemoryAccessEvent[EVENT]): Unit = {
    for (elem <- list) {
      if (elem.positionInRun == alreadyAdded.runPosition) {
        elem.isDataRace = true;
      }
    }
    list.append(new SortedMemoryAccessListElement[EVENT](true, newMemoryAccess));
  }

  def buildResult(accessReportBuilder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    for (elem <- list) {
      if (elem.isDataRace) {
        elem.event.addAsDataRace(accessReportBuilder);
      } else {
        elem.event.add(accessReportBuilder)
      }
    }
  }
}
