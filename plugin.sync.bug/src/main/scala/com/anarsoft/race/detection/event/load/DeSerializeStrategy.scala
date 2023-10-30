package com.anarsoft.race.detection.event.load

import java.nio.ByteBuffer

trait DeSerializeStrategy[EVENT] {

  def deSerializeJavaEvent(data: ByteBuffer): EVENT;

}
