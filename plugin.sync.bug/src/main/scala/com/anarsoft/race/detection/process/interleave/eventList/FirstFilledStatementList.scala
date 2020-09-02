package com.anarsoft.race.detection.process.interleave.eventList

import java.util.ArrayList
import com.anarsoft.race.detection.process.interleave._

class FirstFilledStatementList(val runId : Int , var runPosition : Int  ) {
  
  val statementList = new ArrayList[InterleaveEventStatement]
  
  
}