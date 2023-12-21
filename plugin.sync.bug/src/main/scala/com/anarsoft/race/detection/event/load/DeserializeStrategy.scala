package com.anarsoft.race.detection.event.load

import java.nio.ByteBuffer

trait DeserializeStrategy[EVENT] {

  def deSerializeJavaEvent(data: ByteBuffer): EVENT;

}
