package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.report.dominatortree.{CreateReverseCallTreeReport, DominatorTreeTraversalContext}
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import com.vmlens.report.dominatortree.UIStateElement
import com.vmlens.report.dominatortree.UIStateElementSortKey

import java.util

trait LeafNode extends DominatorTreeVertex {
  
  def getLabel(descriptionContext: DescriptionContext): String
  def getUIStateElementSortKey : UIStateElementSortKey;
  
}
