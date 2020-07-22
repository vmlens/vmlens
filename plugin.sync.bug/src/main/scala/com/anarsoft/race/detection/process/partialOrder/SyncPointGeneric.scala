package com.anarsoft.race.detection.process.partialOrder


import java.util.ArrayList;




trait SyncPointGeneric[ID_PER_OBJECT] extends  EventWithOrder[ID_PER_OBJECT]   {
  
  

 
  
//   def isParallized : Boolean;
//   def createParallizedStatement( prevoius : Option[SyncPointGeneric[ID_PER_OBJECT,STATEMENT_REFERENCE]], next : Option[SyncPointGeneric[ID_PER_OBJECT,STATEMENT_REFERENCE]] ) : Statement;
// 
//   def createReference() : STATEMENT_REFERENCE;
   
   def createSyncStatement() = None; // : Option[SyncStatement]
  
  
  def eventStartsHappensBeforeRelation() : Boolean;
  def eventEndsHappensBeforeRelation() : Boolean;
  
  
  def addToPrevoiusList(previousList : ArrayList[SyncPointGeneric[ID_PER_OBJECT]])
  {
    previousList.add( this );
  }
  
  
  def getEvent() = this;
  
  //def isOldSyncPoint = false;
  
  
}