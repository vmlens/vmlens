package com.anarsoft.race.detection.process.detectRaceConditions

import com.anarsoft.race.detection.model.WithStatementPosition;



trait EventDetectRaceConditions[ID_PER_OBJECT] extends EventWrapperDetectRaceConditions {
  
  def idPerMemoryLocation       : ID_PER_OBJECT;
  def compareIdPerMemoryLocation( other : ID_PER_OBJECT ) : Int;

  
 // var isSortable = true;
  


}