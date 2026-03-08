package com.anarsoft.race.detection.report.element.runelementtype

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.{BarrierKeyVisitor, CountDownLatchKey, FutureKey, PhaserKey}


class BarrierKeyTextBuilder extends BarrierKeyVisitor {
  private var text = ""

  override def visit(futureKey: FutureKey): Unit = {
    text = "Future"
  }

  override def visit(phaserKey: PhaserKey): Unit = {
    text = "Phaser"
  }

  override def visit(countDownLatchKey: CountDownLatchKey): Unit = {
    text = "CountDownLatch"
  }

  def build: String = text
}

