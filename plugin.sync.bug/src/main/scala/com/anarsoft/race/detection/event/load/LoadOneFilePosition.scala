package com.anarsoft.race.detection.event.load

import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.util.List;

class LoadOneFilePosition(val stream: RandomAccessFile) {

  def load(filePosition: FilePosition): ByteBuffer = {
    stream.seek(filePosition.startPosition);
    val array = Array.ofDim[Byte](filePosition.size);
    stream.read(array);
    ByteBuffer.wrap(array);
  }

  def close(): Unit = {
    stream.close();
  }

}
