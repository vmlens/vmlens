package com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic

import com.anarsoft.race.detection.event.interleave.WithLockEvent

trait WithLockEventTestBuilder {

  def enter(lockType: Int, threadIndex: Int): WithLockEvent;
  def exit(lockType: Int, threadIndex: Int): WithLockEvent;

}
