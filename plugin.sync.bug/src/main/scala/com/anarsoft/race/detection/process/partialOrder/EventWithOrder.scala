package com.anarsoft.race.detection.process.partialOrder

import com.anarsoft.race.detection.model.WithStatementPosition;


trait EventWithOrder[ID_PER_OBJECT] extends WithStatementPosition {
  
 def idPerMemoryLocation       : ID_PER_OBJECT;
 def compareIdPerMemoryLocation( other : ID_PER_OBJECT ) : Int;
  
  
  def order : Int;
  
  
}