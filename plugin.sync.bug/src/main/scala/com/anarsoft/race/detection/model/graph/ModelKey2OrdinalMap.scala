package com.anarsoft.race.detection.model.graph

import scala.collection.mutable.HashMap;

class ModelKey2OrdinalMap[MODEL_KEY](   val modelKey2Ordinal  :  HashMap[MODEL_KEY,Int] ,  var currentOrdinalId :  Int ) {
  
//  val modelKey2Ordinal = new HashMap[MODEL_KEY,Int]();
//  var currentStackTraceOrdinalId = 0;
 
  def getOrAddOrdinal(mode_key : MODEL_KEY) =
  {
    
  
    
    
      
    modelKey2Ordinal.get(mode_key) match
    {
      
      case None =>
        {
     
          
          currentOrdinalId = currentOrdinalId + 1;
          modelKey2Ordinal.put(mode_key , currentOrdinalId );
          
          

          
          
          currentOrdinalId;
          
          
          
        }
      
      case Some(x) => x; 
      
      
    }
  }
  
  
  def getOrdinal(mode_key : MODEL_KEY) = modelKey2Ordinal.get(mode_key) ;
  
  
  
}

object ModelKey2OrdinalMap
{
  
  def apply[MODEL_KEY]() =
  {
    new ModelKey2OrdinalMap[MODEL_KEY](  new  HashMap[MODEL_KEY,Int] (), -1 );
  }
    
  
    def createWithStartOrdinal[MODEL_KEY](startOrdinal : Int) =
  {
    new ModelKey2OrdinalMap[MODEL_KEY](  new  HashMap[MODEL_KEY,Int] (), startOrdinal - 1 );
  }
  
  
  
  def apply[MODEL_KEY](firstKey : MODEL_KEY) =
  {
    val map = new  HashMap[MODEL_KEY,Int]();
    map.put(firstKey, 0);
    
    
    
    new ModelKey2OrdinalMap[MODEL_KEY](  map , 0 );
  }
  
  
  
  
  
}