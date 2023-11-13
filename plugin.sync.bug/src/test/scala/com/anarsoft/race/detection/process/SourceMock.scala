package com.anarsoft.race.detection.process

class SourceMock(val publish: () => Unit) extends Source {
  override def start(): Unit = {
    publish();
  }
}
