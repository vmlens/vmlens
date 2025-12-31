package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.run.LevelToCSS
import com.vmlens.report.dominatortree.UIDominatorTreeElement

import java.util

trait InternalNode extends DominatorTreeVertex {

  override def addToReport(parent: Option[UIDominatorTreeElement], 
                           level: Int, 
                           descriptionContext: DescriptionContext, 
                           levelToCSS: LevelToCSS, 
                           result: util.LinkedList[UIDominatorTreeElement]): UIDominatorTreeElement = {
    val element = new UIDominatorTreeElement( getLabel(descriptionContext) , levelToCSS.getCss(level) )
    result.add(element)
    element;
  }

  def getLabel(descriptionContext: DescriptionContext): String


}
