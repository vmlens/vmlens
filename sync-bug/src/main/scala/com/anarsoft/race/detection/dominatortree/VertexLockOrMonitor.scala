package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import com.anarsoft.race.detection.dominatortree.key.VertexKeyLockOrMonitor


class VertexLockOrMonitor(val key : VertexKeyLockOrMonitor) extends DefaultVertex  {


  override def getLabel(descriptionContext: DescriptionContext): String =  key.getLabel

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
  }

  override def isMethodCall: Boolean = false

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement =
    reportCallback.withReverseCallTree(this, parent, level)
  
}