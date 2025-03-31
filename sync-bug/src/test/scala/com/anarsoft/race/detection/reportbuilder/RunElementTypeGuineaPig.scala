package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.report.runelementtype.RunElementType

class RunElementTypeGuineaPig extends RunElementType {
  override def asString(context: DescriptionContext): String = "test"

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {

  }
}
