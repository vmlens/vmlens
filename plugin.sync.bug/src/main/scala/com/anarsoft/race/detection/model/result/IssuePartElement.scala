package com.anarsoft.race.detection.model.result

import com.vmlens.api.Icon


trait IssuePartElement {
 
  def nameWithHtml( ModelFacadeAll : ModelFacadeAll) : String;
  
  def name( ModelFacadeAll : ModelFacadeAll) : String;
  def icon(ModelFacadeAll : ModelFacadeAll)  : Icon;
  
  def children( ModelFacadeAll : ModelFacadeAll)        : Seq[IssuePartElement];
  
  
  def name4Yaml( ModelFacadeAll : ModelFacadeAll) : String;
  def title4Yaml(position : Int) : Option[String]
  
  
  
    def searchData(ModelFacadeAll : ModelFacadeAll) : Option[SearchData]
  
}