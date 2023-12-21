package com.anarsoft.race.detection.event.load

import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.util.List;

class LoadOneFilePosition {

  def load(stream: RandomAccessFile, filePosition: FilePosition): ByteBuffer = {
    stream.seek(filePosition.startPosition);
    val array = Array.ofDim[Byte](filePosition.size);
    stream.read(array);
    ByteBuffer.wrap(array);
  }

}
