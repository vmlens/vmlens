package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.vmlens.report.dominatortree.UIDominatorTreeElement

case class VertexMonitor(id : Int) extends InternalNode  {

  override def getLabel(descriptionContext: DescriptionContext): String = "monitor(" + id + ")";

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
   
  }

  override def isMethodCall : Boolean = false;

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement =
    reportCallback.withReverseCallTree(this, parent, level)


}
