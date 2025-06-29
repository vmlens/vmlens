package com.anarsoft.race.detection.event.load

import java.io.DataInputStream

trait DeserializeStrategy[EVENT] {

  def deSerializeJavaEvent(data: DataInputStream): EVENT;

}
