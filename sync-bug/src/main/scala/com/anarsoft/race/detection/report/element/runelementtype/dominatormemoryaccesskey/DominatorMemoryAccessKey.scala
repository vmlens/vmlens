package com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


trait DominatorMemoryAccessKey {

  def asString(context: DescriptionContext): String
  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit

}
