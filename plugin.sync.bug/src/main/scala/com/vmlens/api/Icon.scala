package com.vmlens.api

abstract sealed  class Icon {
  
   def getId() : String;
   def getName()  : String; 
   def isObj() : Boolean;
  
}


case class SingleIcon(val path : String)     extends Icon
{
  
  def getId() = path;
  
  def getName() =
  {
    
    val start = path.lastIndexOf("/");
    
    val end = path.lastIndexOf(".");
    
    getId().substring(start + 1, end)
    
    
    
    
    
  }
  
  
  def isObj() = getId().contains("obj16");
  
  
  
}

case class CompositeIcon(val baseIcon : Icon ,val overlayIcon : Icon,val position : Int)  extends Icon
{
  def getId() = baseIcon.getId()  + overlayIcon.getId();
  
  
    def getName()   = overlayIcon.getName() + "_" + baseIcon.getName();
    
    
    def isObj() = true;
  
}
