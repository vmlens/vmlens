package com.anarsoft.race.detection.model.result

import com.vmlens.api.Icon


trait IssueModelElementMonitor {
  
  
  
  
  def name( modelFacade : ModelFacadeMonitor) : String;
  def icon(modelFacade : ModelFacadeMonitor)  : Icon;
  
  def children( modelFacade : ModelFacadeMonitor)        : Seq[IssuePartElement];
  
  
 // def showDetailsInOverview() : Boolean;
  
  def name4Yaml( modelFacade : ModelFacadeMonitor) : String;
  def title4Yaml(position : Int) : Option[String]
  
}