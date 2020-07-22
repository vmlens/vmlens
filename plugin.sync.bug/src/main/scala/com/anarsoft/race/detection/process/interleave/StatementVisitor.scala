package com.anarsoft.race.detection.process.interleave

import com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import com.anarsoft.race.detection.process.syncAction.SyncActionLockEnterInterleave
import com.anarsoft.race.detection.process.syncAction.SyncActionLockExitInterleave
import com.anarsoft.race.detection.process.monitor.MonitorEnterEventInterleave
import com.anarsoft.race.detection.process.monitor.MonitorExitEventInterleave
import com.anarsoft.race.detection.process.scheduler.MethodAtomicEnterEvent

import com.anarsoft.race.detection.process.scheduler.MethodAtomicExitEvent
import com.anarsoft.race.detection.process.scheduler.MethodCallbackEnterEvent
import com.anarsoft.race.detection.process.scheduler.MethodCallbackExitEvent

import com.anarsoft.race.detection.process.volatileField.VolatileAccessEventInterleave
import com.anarsoft.race.detection.process.volatileField.VolatileAccessEventStaticInterleave
import com.anarsoft.race.detection.process.volatileField.VolatileArrayAccessEventInterleave

import com.anarsoft.race.detection.process.syncAction.StampedLockExitInterleave
import com.anarsoft.race.detection.process.syncAction.StampedLockEnterInterleave

trait StatementVisitor {

  def visit(statement: StampedLockExitInterleave);
  def visit(statement: StampedLockEnterInterleave);
  
  
  def visit(statement: InterleaveEventNonVolatileAccess);
  def visit(statement: SyncActionLockEnterInterleave);
  def visit(statement: SyncActionLockExitInterleave);
  def visit(statement: MonitorEnterEventInterleave);
  def visit(statement: MonitorExitEventInterleave);

  def visit(statement: MethodAtomicEnterEvent);
  def visit(statement: MethodAtomicExitEvent);

  def visit(statement: MethodCallbackEnterEvent);
  def visit(statement: MethodCallbackExitEvent);
  
  def visit(statement: VolatileAccessEventInterleave);
  def visit(statement: VolatileAccessEventStaticInterleave);
  def visit(statement: VolatileArrayAccessEventInterleave);

  
  
  

}