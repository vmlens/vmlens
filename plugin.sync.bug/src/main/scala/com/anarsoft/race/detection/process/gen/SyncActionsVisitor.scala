package com.anarsoft.race.detection.process.gen;


trait SyncActionsVisitor
{  
       
       
def visit( in :  VolatileAccessEventStaticGen);
       
       
def visit( in :  VolatileAccessEventGen);
       
       
def visit( in :  VolatileArrayAccessEventGen);
       
       
def visit( in :  LockEnterEventGen);


  def visit(in: LockExitEventGen);


  def visit(in: StampedLockEnterEventGen);


  def visit(in: StampedLockExitEventGen);


  def visit(in: ThreadStartEventGen);


  def visit(in: ThreadJoinedEventGen);


}