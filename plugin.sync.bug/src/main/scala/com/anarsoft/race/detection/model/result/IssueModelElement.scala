package com.anarsoft.race.detection.model.result



import com.vmlens.api.Icon


/**
 * nur für race und dedlock haupt element und ModelFacadeAll
 * 
 * 
 * 
 */


trait IssueModelElement {
  
  def titlePrefix() : String;
  
  
  def name( modelFacade : ModelFacadeAll) : String;
  def icon(modelFacade : ModelFacadeAll)  : Icon;
  
  def children( modelFacade : ModelFacadeAll)        : Seq[IssuePartElement];
  

//  def compare(other :IssueModelElement,  modelFacade : ModelFacadeAll )  : Int;
  
  def showDetailsTill(modelFacade : ModelFacadeAll) : Int;
    
  
   def searchData(modelFacade : ModelFacadeAll) : Option[SearchData]
  
   
  def name4Yaml( modelFacade : ModelFacadeAll) : String;
  def title4Yaml(position : Int) : Option[String]
   
   
}