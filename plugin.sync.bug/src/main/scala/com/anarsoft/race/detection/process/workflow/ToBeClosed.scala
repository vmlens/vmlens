package com.anarsoft.race.detection.process.workflow

import scala.collection.mutable.HashMap;

abstract class ToBeClosed(val listForFinally : HashMap[Object,ToBeClosed]) {
  
  
   listForFinally.put( this,  this);
  
  
  
  def close()
  {
    closeInternal();
    
    
    listForFinally.remove( this );
    
    
  }
  
  
  
  def closeInternal();
  
  
}