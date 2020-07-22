package com.vmlens.api.internal.reports

import  com.vmlens.api.Icon
import com.anarsoft.race.detection.model.result.SearchData;

trait Element4TreeViewer {
  
  def getChildrens() : Array[Object];
  

   
  def hasChildren() : Boolean;
    
  
  /**
   * 
   * Ist für den root knoten null
   * 
   * 
   */
  
  
  def getParent() : Element4TreeViewer;
 
  def getText() : String;
 
  
  /**
   * 
   * null wenn kein icon angezeigt werden soll
   * 
   */
  
  def getIcon() : Icon;
  

  
  
  def searchData() : Option[SearchData]
  
  
  // Yaml start
  
  
  def children4Yaml() : Seq[Element4TreeViewer];
  
  
  def name4Yaml() :  String; 
  
       
 def title4Yaml(position : Int) : Option[String]
      
  
  // Yaml end
  
  
  
  
}