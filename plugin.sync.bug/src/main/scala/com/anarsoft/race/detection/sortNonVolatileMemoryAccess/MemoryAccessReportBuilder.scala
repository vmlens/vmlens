package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.vmlens.report.dataView.MemoryAccessView

trait MemoryAccessReportBuilder {

  def addMemoryAccess(event: MemoryAccessView): Unit;

  def addDataRace(event: MemoryAccessView): Unit;

}
