package com.vmlens.test

import com.vmlens.trace.agent.bootstrap.Logger

class TestLogger(orderEnabled : Boolean) extends Logger(orderEnabled) {
  override protected def logOrder(order: String): Unit = {
    println(order);
  }
}
