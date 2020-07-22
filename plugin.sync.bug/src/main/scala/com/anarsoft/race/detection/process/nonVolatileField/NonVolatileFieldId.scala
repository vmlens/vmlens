package com.anarsoft.race.detection.process.nonVolatileField

case class NonVolatileFieldId(val objectHashCode : Long,val fieldId : Int) {
 
  def compare( other : NonVolatileFieldId) =
  {
    if(objectHashCode != other.objectHashCode)
    {
       java.lang.Long.compare( objectHashCode , other.objectHashCode )    
    }
    else
    {
      java.lang.Integer.compare(  fieldId , other.fieldId)
    }
  }
  
  
  
  
}