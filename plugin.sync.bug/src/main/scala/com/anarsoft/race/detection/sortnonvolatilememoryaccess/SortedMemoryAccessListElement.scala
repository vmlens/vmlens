package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.reportbuilder.NonVolatileEventForReport

private class SortedMemoryAccessListElement(var isDataRace: Boolean,
                                            val event: NonVolatileEventForReport) {

  def positionInRun = event.runPosition;

}
