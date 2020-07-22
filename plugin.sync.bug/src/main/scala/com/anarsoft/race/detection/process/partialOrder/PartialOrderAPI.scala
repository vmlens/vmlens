package com.anarsoft.race.detection.process.partialOrder

import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field._
import com.anarsoft.race.detection.process.detectRaceConditions._



trait PartialOrderAPI {
 
  
           def isLeftBeforeRight(left : WithStatementPosition , right : WithStatementPosition) : Boolean;
           def isLeftBeforeRightTransetive(left : WithStatementPosition , right : WithStatementPosition) : Boolean;
  
  
  
}