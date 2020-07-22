package com.anarsoft.race.detection.model

import com.anarsoft.race.detection.model.description.ThreadNames

//import com.anarsoft.race.detection.model.query._;

trait WithStatementPosition {
  
  def programCounter : Int;
  def threadId : Long;
  
  //def getStatementId() = new Map2ListIdImpl(threadId , programCounter);
 // def getThreadName(statementRepo : ThreadNames) = statementRepo.getThreadName(threadId);

}

case class WithStatementPositionImpl(val threadId : Long ,  val programCounter : Int) extends WithStatementPosition {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.model.WithStatementPositionImpl]
  }

 
  
}