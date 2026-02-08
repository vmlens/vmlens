package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}

class VertexRoot extends InternalNode {



  override def getLabel(descriptionContext: DescriptionContext): String = "root"

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
  }


  private def canEqual(other: Any): Boolean = other.isInstanceOf[VertexRoot]

  override def equals(other: Any): Boolean =
    this.canEqual(other)


  override def hashCode(): Int = {
   74
  }

  override def isDominatorTreeLeaf: Boolean = false
}
