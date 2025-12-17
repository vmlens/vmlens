package com.anarsoft.race.detection.report.element.runelementtype

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKeyVisitor
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.FutureKey
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.PhaserKey


class BarrierKeyTextBuilder extends BarrierKeyVisitor {
  private var text = ""

  override def visit(futureKey: FutureKey): Unit = {
    text = "Future"
  }

  override def visit(phaserKey: PhaserKey): Unit = {
    text = "Phaser"
  }

  def build: String = text
}

