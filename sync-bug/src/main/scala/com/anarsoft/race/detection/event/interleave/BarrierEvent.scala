package com.anarsoft.race.detection.event.interleave

/**
 * Actions prior to calling CyclicBarrier.await and Phaser.awaitAdvance (as well as its variants)
 * happen-before actions performed by the barrier action, and actions performed by the barrier
 * action happen-before actions subsequent to a successful return from the corresponding await in other threads.
 *
 * see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html
 */

trait BarrierEvent extends LoadedInterleaveActionEvent {

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
  
}
