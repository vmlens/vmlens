package com.anarsoft.race.detection.model.result

import com.anarsoft.race.detection.model.description.ArrayModel
import com.anarsoft.race.detection.model.description.FieldModel4Template

abstract sealed class LocationInClass {
  
  def getName(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) : String;
  
  
  def isVolatile : Boolean;
  def isStatic   : Boolean;
  
  
  def toSharedState( modelFacade : ModelFacadeState , aggregateId : Int ) : Option[SharedState];
  
  
   def getNameWithBold(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph)  : String;
   
   
  def compare( other : LocationInClass )  : Int;
   
   
  
}

case class FieldInClass(val ordinal : Int, val isVolatile : Boolean, val isStatic : Boolean ) extends LocationInClass
{
  
  
  def isFromTemplate(facade : FieldAndArrayPerMethodFacade) =
  {
      val fieldModel = facade.fieldOrdinal2FieldModelDesc(ordinal);
    
    if( fieldModel == null )
    {
      false;
    }
    else
    {
        fieldModel.isInstanceOf[FieldModel4Template];
    }
    
  }
    
  
  
   def compare( other : LocationInClass ) =
   {
     other match
     {
       case ArrayInClass(arrayLocationId,classOrdinal) =>
         {
           -1;
         }
       
       case    FieldInClass( otherOrdinal ,  isVolatile ,  isStatic) =>
         {
           java.lang.Integer.compare(  ordinal ,otherOrdinal  )
         }
       
     }
     
     
     
   }
  
  
  
  
  def getName(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) =
  {
    val fieldModel = facade.fieldOrdinal2FieldModelDesc(ordinal);
    
    if( fieldModel == null )
    {
      "unknown"
    }
    else
    {
        fieldModel.getDisplayName();
    }
    
    
    
  
  }
  
  
  
  def getNameWithBold(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) =
  {
     val fieldModel = facade.fieldOrdinal2FieldModelDesc(ordinal);
    
    if( fieldModel == null )
    {
      "unknown"
    }
    else
    {
      
      fieldModel.getNameWithBold();
      
        
    }
    
  }
  
  
  
  
  
  def toSharedState( modelFacade : ModelFacadeState , aggregateId : Int)=  
    {
    
     val fieldModel = modelFacade.fieldAndArrayFacade.fieldOrdinal2FieldModelDesc(ordinal);
    
    if( fieldModel == null )
    {
      None;
    }
    else
    {
       Some(SharedStateFieldAccess(   fieldModel , isVolatile , isStatic , Set(aggregateId)));
    }
    
    
    
   
    }
  
  
  
  
  
  
  
  
}


case class ArrayInClass(val arrayLocationId : Int,val classOrdinal : Int) extends LocationInClass
{
  
    def getNameWithBold(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) = getName(facade,stackTraceGraph)
  
    
    
      def compare( other : LocationInClass ) =
   {
     other match
     {
       case ArrayInClass(otherArrayLocationId,otherClassOrdinal) =>
         {
           if(otherArrayLocationId != arrayLocationId)
           {
              java.lang.Integer.compare(  arrayLocationId ,otherArrayLocationId  )
           }
           else
           {
              java.lang.Integer.compare(  classOrdinal ,otherClassOrdinal  )
           }
           
           
           
         
         }
       
       case    FieldInClass( otherOrdinal ,  isVolatile ,  isStatic) =>
         {
           1;
         }
       
     }
     
     
     
   }
  
  
  def getName(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) = 
  {

    facade.getClassName(classOrdinal)  // + posString;

   
    
  }
  
  
  
  
    def toSharedState( modelFacade : ModelFacadeState , aggregateId : Int)=  
    {
      val model = modelFacade.fieldAndArrayFacade.getArrayModel(arrayLocationId)
      val method = model.getMinMethodOrdinalAndPosition(modelFacade.stackTraceGraph);
      
      method match
      {
        case None =>
          {
            None
          }
        
        case Some(x) =>
          {
             Some( SharedStateArrayAccess(    modelFacade.fieldAndArrayFacade.getClassName(classOrdinal)  ,   
               modelFacade.fieldAndArrayFacade.getClassName(classOrdinal)   ,  Set(aggregateId)) );
          }
        
        
        
      }
      
      
      
    }
  
  
  
  
  
  
  def isVolatile = false;
  def isStatic   = false;
  
  
}









case class ClassAccess(val ordinal : Int ) extends LocationInClass
{
  
   def compare( other : LocationInClass ) =
   {
     other match
     {
       
       case ClassAccess(otherOrdinal) =>
         {
              java.lang.Integer.compare(  ordinal ,otherOrdinal  )
         }
       
       
       case ArrayInClass(arrayLocationId,classOrdinal) =>
         {
           -1;
         }
       
       case    FieldInClass( otherOrdinal ,  isVolatile ,  isStatic) =>
         {
           1;
         }
       
     }
     
     
     
   }
  
  
  
  
  def getName(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) =
  {
    facade.getClassName(ordinal)
    
    
    
  
  }
  
  
  
  def getNameWithBold(facade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) =
  {
   facade.getClassName(ordinal)
    
  }

   def isVolatile = false;
  def isStatic   = false;
  
  
    def toSharedState( modelFacade : ModelFacadeState , aggregateId : Int)=   Some(new SharedStateClassAccess(   modelFacade.fieldAndArrayFacade.getClassName(ordinal)  ,  Set(aggregateId)))
  

}



