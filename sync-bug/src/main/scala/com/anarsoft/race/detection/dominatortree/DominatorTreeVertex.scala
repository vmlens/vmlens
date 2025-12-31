package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.run.LevelToCSS
import com.vmlens.report.dominatortree.UIDominatorTreeElement

import java.util

/**
 * either VertexRoot , VertexMethod , VertexVolatileField
 * 
 * Multiple Volatile Field Events for the same hash code can be combined in one VolatileFieldVertex
 * 
 * We need to filter Volatile Field so that only fields which are accessed
 * multiple times are shown
 * 
 * We need also remove methods which do not contain a field access
 * 
 * 
 */
trait DominatorTreeVertex {
  
  def addToReport(parent: Option[UIDominatorTreeElement] , 
                  level : Int,
                  descriptionContext: DescriptionContext,
                  levelToCSS : LevelToCSS,
                  result : util.LinkedList[UIDominatorTreeElement]): UIDominatorTreeElement
  def addToNeedsDescription(needsDescriptionCallback : NeedsDescriptionCallback) : Unit;
  
  

}
