package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.{BarrierContainer, EventContainer, MonitorContainer}
import com.vmlens.report.runelementtype.{BarrierOperation, RunElementType}
import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyTypeCollection
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey

/**
 * Actions prior to calling CyclicBarrier.await and Phaser.awaitAdvance (as well as its variants)
 * happen-before actions performed by the barrier action, and actions performed by the barrier
 * action happen-before actions subsequent to a successful return from the corresponding await in other threads.
 *
 * see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html
 *
 * Memory consistency effects: Actions taken by the asynchronous computation happen-before actions following the
 * corresponding Future.get() in another thread.
 *
 *
 * Fixme clarify should by await an event with waitNotify or should it depend on the key
 * How to model phaser?
 *
 * With Future we have
 *
 * wait enter
 *          notify
 * wait exit
 *
 * wait enter does nothing
 *
 * notify creates relation to wait exit
 *
 * for cyclic barrier we have
 *
 * wait enter and notify
 *
 * wait exit
 *
 * I suppose that Phaser awaitAdvance is the sam as wait notify
 * and Phaser advance the same as notify
 *
 */

trait BarrierEvent extends LoadedInterleaveActionEvent
  with EventForReportElement
  with WithSetStacktraceNode
  with SyncActionEventWithCompareType[BarrierEvent] {

  def barrierKeyType: Int;
  def objectHashCode: Long;

  override def compareType(other: BarrierEvent): Int = {
    getBarrierKey.compareTo(other.getBarrierKey)
  }

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.barrierEvents.add(this);
  }

  def update(container : EventContainer[BarrierEvent]) : EventContainer[BarrierEvent];
  def onNotify( barrierNotify: Option[BarrierNotifyEvent],f: BarrierEvent => Unit) : Unit;
  def create() : EventContainer[BarrierEvent];

  def getBarrierKey: BarrierKey =
    BarrierKeyTypeCollection.SINGLETON.fromId(barrierKeyType).create(objectHashCode);

}
