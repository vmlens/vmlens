package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.event.interleave.barrier.{BarrierEventType, BarrierEventTypeBuilder}
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.{BarrierContainer, MonitorContainer}
import com.vmlens.report.runelementtype.{BarrierOperation, RunElementType}
import com.vmlens.trace.agent.bootstrap.barriertype.{BarrierKeyTypeCollection, BarrierType, BarrierTypeCollection}
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey

/**
 * Actions prior to calling CyclicBarrier.await and Phaser.awaitAdvance (as well as its variants)
 * happen-before actions performed by the barrier action, and actions performed by the barrier
 * action happen-before actions subsequent to a successful return from the corresponding await in other threads.
 *
 * see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html
 */

trait BarrierEvent extends LoadedInterleaveActionEvent  with EventForReportElement
  with WithSetStacktraceNode
  with SyncActionEventWithCompareType[BarrierEvent] {

  
  def barrierType: Int;
  def barrierKeyType: Int;
  def objectHashCode: Long;



  def create(): BarrierContainer = getBarrierEventType.create(this)

  def update(barrierContainer: BarrierContainer): BarrierContainer =
    getBarrierEventType.update(this,barrierContainer);

  def foreachOpposite(barrierContainer: BarrierContainer, f: BarrierEvent => Unit): Unit =
    getBarrierEventType.foreachOpposite(this,barrierContainer, f);

  
  override def compareType(other: BarrierEvent): Int = {
    getBarrierKey.compareTo(other.getBarrierKey)
  }

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.barrierEvents.add(this);
  }

  override def runElementType: RunElementType =
    new BarrierOperation( getBarrierEventType.getOperationType , getBarrierKey );

  private def getBarrierEventType: BarrierEventType = {
    val builder = new BarrierEventTypeBuilder();
    BarrierTypeCollection.SINGLETON.fromId(barrierType).accept(builder);
    builder.build();
  };

  private def getBarrierKey: BarrierKey =
    BarrierKeyTypeCollection.SINGLETON.fromId(barrierKeyType).create(objectHashCode);
  
}
