package com.anarsoft.race.detection.event.load

import java.nio.ByteBuffer
import java.util.{List, ArrayList}

class DeserializeEvents[EVENT] {

  def deserialize(byteBuffer: ByteBuffer, deserializeStrategy: DeserializeStrategy[EVENT]): List[EVENT] = {
    val result = new ArrayList[EVENT]();
    while (byteBuffer.hasRemaining) {
      result.add(deserializeStrategy.deSerializeJavaEvent(byteBuffer));
    }
    result
  }

}
