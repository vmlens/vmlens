package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType

case class VertexLock(lockType : ReportLockType, id : Int) extends InternalNode {

  override def getLabel(descriptionContext: DescriptionContext): String = lockType.text +  "(" + id + ")";

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
  }
  
}