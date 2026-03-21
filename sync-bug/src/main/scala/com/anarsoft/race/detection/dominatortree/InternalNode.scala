package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.DominatorTreeTraversalContext
import com.vmlens.report.dominatortree.UIDominatorTreeElement

import java.util

trait InternalNode extends DominatorTreeVertex {

  def getLabel(descriptionContext: DescriptionContext): String

}
