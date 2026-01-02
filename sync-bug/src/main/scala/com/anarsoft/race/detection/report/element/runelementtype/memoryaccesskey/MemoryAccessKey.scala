package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


trait MemoryAccessKey {
  
  def asString(context: DescriptionContext): String
  def objectHashCodeOption : Option[Long]
  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit
  
}