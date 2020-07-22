package com.anarsoft.race.detection.model.field

import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.description._;
import com.vmlens.trace.agent.bootstrap.util.Constants

class ArrayAndFieldOrdinalMap(val  fieldId2Ordinal : HashMap[Int,Int]
    ,val fieldOrdinal2FieldModelDesc : Array[FieldModel] , 
    val arrayAggregateId2Ordinal : Array[Int] ,
    val arrayOrdinal2Model : Array[ArrayModel]

 ) {
  
 
  
  def setFieldIds4TemplateClasses()
  {
    /*
     * 	public static final int FIELD_ID_JAVA_UTIL_HASH_MAP = 1;
	public static final int FIELD_ID_JAVA_UTIL_IDENTITY_HASH_MAP = 2;
	public static final int FIELD_ID_JAVA_UTIL_LINKED_HASH_MAP = 3;
	public static final int FIELD_ID_JAVA_UTIL_HASH_SET = 4;
     */
    ArrayAndFieldOrdinalMap.setTemplateFields(setFieldId4TemplateClass)
    
    
   
    
    
  }
  
  
  def setFieldId4TemplateClass(fieldId : Int, template : FieldModel4Template)
  {
     fieldId2Ordinal.get(fieldId) match
    {
      
      case None =>
        {
          
        }
      
      case Some(x) =>
        {
          
         
          
         
          fieldOrdinal2FieldModelDesc(x) = template;
         }
      
    }
  }
  
  
  
  
  def setFieldModelFromClassDesc(fieldId : Int, fieldModelFromClassDesc  : FieldModelFromClassDesc)
  {
    
    
    
    fieldId2Ordinal.get(fieldId) match
    {
      
      case None =>
        {
          
        }
      
      case Some(x) =>
        {
          
         
          
         
          fieldOrdinal2FieldModelDesc(x) = fieldModelFromClassDesc;
         }
      
    }
  }
  
  
  
  def setFieldModelFromAccess(fieldId : Int, fieldModelFromAccess : FieldModelFromAccess) =
  {
    fieldId2Ordinal.get(fieldId) match
    {
      
      case None =>
        {
          None
        }
      
      case Some(x) =>
        {
           
           if( fieldOrdinal2FieldModelDesc(x) == null)
             {
                
             
                fieldOrdinal2FieldModelDesc(x) = fieldModelFromAccess;
                
             }
           
           Some(x);
         }
      
    }
  }
  
  
//  def getByFieldId( fieldId : Int ) =
//  {
//     fieldId2Ordinal.get(fieldId);
//  }
  
  
}

object ArrayAndFieldOrdinalMap
{
  def setTemplateFields( setFieldId4TemplateClass :   (  Int ,  FieldModel4Template ) => Unit )
  {
    setFieldId4TemplateClass(  Constants.FIELD_ID_JAVA_UTIL_HASH_MAP ,  new  FieldModel4Template("java.util" , "HashMap") );
    setFieldId4TemplateClass(  Constants.FIELD_ID_JAVA_UTIL_IDENTITY_HASH_MAP ,  new  FieldModel4Template("java.util" , "IdentityHashMap") );
    setFieldId4TemplateClass(  Constants.FIELD_ID_JAVA_UTIL_LINKED_HASH_MAP ,  new  FieldModel4Template("java.util" , "LinkedHashMap") );
    setFieldId4TemplateClass(  Constants.FIELD_ID_JAVA_UTIL_HASH_SET ,  new  FieldModel4Template("java.util" , "HashSet") );
  }
  
  
  
  
}





