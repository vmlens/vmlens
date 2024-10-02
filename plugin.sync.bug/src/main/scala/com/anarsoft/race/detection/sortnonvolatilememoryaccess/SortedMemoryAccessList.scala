package com.anarsoft.race.detection.sortnonvolatilememoryaccess


import com.anarsoft.race.detection.reportbuilder.{EventForRunElement, NonVolatileEventForReport, StaticMemoryAccessId}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class SortedMemoryAccessList {

  // Visible for test
  val list = new ArrayBuffer[SortedMemoryAccessListElement]();

  val dataRaces = new mutable.HashSet[StaticMemoryAccessId]();

  def add(memoryAccess: NonVolatileEventForReport): Unit = {
    list.append(new SortedMemoryAccessListElement(false, memoryAccess));
  }

  def addDataRace(memoryAccess: NonVolatileEventForReport): Unit = {
    list.append(new SortedMemoryAccessListElement(true, memoryAccess));
    dataRaces.add(memoryAccess.staticMemoryAccessId())
  }

  def setDataRace(alreadyAdded: NonVolatileEventForReport): Unit = {
    dataRaces.add(alreadyAdded.staticMemoryAccessId())
    for (elem <- list) {
      if (elem.runPosition == alreadyAdded.runPosition) {
        elem.isDataRace = true;
      }
    }
  }

  def foreach(f: (EventForRunElement) => Unit): Unit = {

  }
  
}
