package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}

case class VertexMonitor(id : Int) extends InternalNode {

  override def getLabel(descriptionContext: DescriptionContext): String = "monitor(" + id + ")";

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
   
  }
}
