package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal.IconRepository
import com.vmlens.api.Icon
import com.anarsoft.race.detection.model.description.FieldModel

abstract class SharedState {
  
  def classOrPackageName : String;

  
  def name : String;
  
  def sortBy : String;
  
  def image : Icon;
  
  def memoryAggregateSet : Set[Int];
  
}

case class SharedStateArrayAccess(val className : String, val name : String,val memoryAggregateSet : Set[Int] )   extends SharedState with SharedStateWithClassName  {
 
  
    def classOrPackageName  = className;
  
   def sortBy = className;
   
   
   def image = IconRepository.ARRAY;
   
}

case class SharedStateFieldAccess(val fieldModel : FieldModel, val isVolatile : Boolean,val isStatic : Boolean,val memoryAggregateSet : Set[Int]) extends SharedState with SharedStateWithClassName {
 
  
  def className = fieldModel.getClassName();
  
  def classOrPackageName  = className;
  
  
  def name = fieldModel.getDisplayName();
  
  
    def sortBy = fieldModel.getClassName();
    
    
    def image = IconRepository.getImageForFieldWithoutAccess (isStatic  , isVolatile );
  
}



case class SharedStateClassAccess(val className : String,val memoryAggregateSet : Set[Int]) extends SharedState with SharedStateWithClassName {
 
  

  
  def classOrPackageName  = className;
  
  
  def name =className;
  
  
    def sortBy = className;
    
    
    def image = IconRepository.CLASS;
  
}


















case class SharedStatePackage(val packageName : String,val memoryAggregateSet : Set[Int])  extends SharedState {
  
   def classOrPackageName = packageName;
  
  def name =  packageName;
   
   def sortBy = packageName;
   
     def image = IconRepository.PACKAGE
   
   
}