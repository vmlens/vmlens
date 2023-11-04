package com.anarsoft.race.detection.event.load

import java.nio.ByteBuffer

class SourceImpl[EVENT, CONTEXT <: LoadedEventContext[EVENT]]
(val context: CONTEXT, val deserializeStrategy: DeSerializeStrategy[EVENT]) {

  def deSerialize(byteBuffer: ByteBuffer): EVENT = {
    deserializeStrategy.deSerializeJavaEvent(byteBuffer);
  }


  def distributeLoadedEvent(event: EVENT): Unit = {
    context.addLoadedEvent(event);
  }

}
