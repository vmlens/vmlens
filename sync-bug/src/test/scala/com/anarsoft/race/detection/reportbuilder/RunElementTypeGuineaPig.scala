package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.report.runelementtype.RunElementType
import com.vmlens.report.runelementtype.objecthashcodemap.ObjectHashCodeMap

class RunElementTypeGuineaPig extends RunElementType {

  override def operation(): String = "test"

  override def element(context: DescriptionContext): String = "element"

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {

  }

  override def `object`(context : DescriptionContext): String = "";

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {}
}
