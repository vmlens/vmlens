package com.anarsoft.plugin.view.factory

import com.vmlens.api.internal.reports._;

class TestElement4TreeViewer extends Element4TreeViewer {
  
   def getChildrens() = null;
  

   
  def hasChildren() = false;
    
  
  /**
   * 
   * Ist für den root knoten null
   * 
   * 
   */
  
  
  def getParent() = null;;
 
  def getText() = "test";
 
  
  /**
   * 
   * null wenn kein icon angezeigt werden soll
   * 
   */
  
  def getIcon()= null;
  
  
   def searchData() = None;
  
    def children4Yaml() = Nil;
    
    
    def name4Yaml() = "test";
    
    
   def title4Yaml(position: Int) = None;  
   
   
}