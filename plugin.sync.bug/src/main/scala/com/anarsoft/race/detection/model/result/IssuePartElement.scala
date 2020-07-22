package com.anarsoft.race.detection.model.result

import com.vmlens.api.Icon


trait IssuePartElement {
 
  def nameWithHtml( modelFacade : ModelFacade) : String;
  
  def name( modelFacade : ModelFacade) : String;
  def icon(modelFacade : ModelFacade)  : Icon;
  
  def children( modelFacade : ModelFacade)        : Seq[IssuePartElement];
  
  
  def name4Yaml( modelFacade : ModelFacade) : String;
  def title4Yaml(position : Int) : Option[String]
  
  
  
    def searchData(modelFacade : ModelFacade) : Option[SearchData]
  
}