package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}

class VertexRoot extends InternalNode {

  override def getLabel(descriptionContext: DescriptionContext): String = "root"

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
  }
}
