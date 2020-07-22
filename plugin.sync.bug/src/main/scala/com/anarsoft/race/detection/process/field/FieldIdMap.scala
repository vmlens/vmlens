package com.anarsoft.race.detection.process.field

import scala.collection.mutable.HashMap


class FieldIdMap{
  
  var currentFieldOrdinal = 0;
  val fieldId2Ordinal = new HashMap[Int,Int]
  
  
  def getOrCreate( fieldId : Int ) =
  {
    fieldId2Ordinal.get(fieldId) match
    {
      
      case Some(x) => {
        x;
      }
      
      case None =>
        {
             val temp = currentFieldOrdinal;
          fieldId2Ordinal.put( fieldId , temp  );
          currentFieldOrdinal = currentFieldOrdinal + 1; 
          temp;
        }
          
      
   
      
    }
  }
  
  
}