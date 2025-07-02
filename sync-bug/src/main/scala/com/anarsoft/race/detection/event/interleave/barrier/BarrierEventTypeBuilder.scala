package com.anarsoft.race.detection.event.interleave.barrier

import com.vmlens.trace.agent.bootstrap.barriertype.{BarrierTypeNotify, BarrierTypeVisitor, BarrierTypeWait, BarrierTypeWaitNotify}

class BarrierEventTypeBuilder extends BarrierTypeVisitor {

  private var eventType : BarrierEventType = null;
  
  override def visit(barrierTypeNotify: BarrierTypeNotify): Unit = {
    eventType = new BarrierEventTypeNotify();
  }

  override def visit(barrierTypeWait: BarrierTypeWait): Unit = {
    eventType = new BarrierEventTypeWait();
  }

  override def visit(barrierTypeWaitNotify: BarrierTypeWaitNotify): Unit = {
    
  }
  
  def build(): BarrierEventType  = eventType;
  
}
