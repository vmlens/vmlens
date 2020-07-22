package com.anarsoft.race.detection.process.read

import java.util.concurrent._;
import scala.collection.mutable.ArrayBuffer;
import java.io._


class ReadTask (val stream :  RandomAccessFile ,val slidingWindowId : Int , val list : ArrayBuffer[FilePosition] ) extends Callable[ReadAheadResult] {
  
  
  def call() = new ReadAheadResult(slidingWindowId , ReadArray.read(stream, list));
  
  
}