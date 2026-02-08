package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ThreadIndices
import com.vmlens.trace.agent.bootstrap.MemoryAccessType

import scala.collection.mutable



class DominatorMemoryAccessKeyToOperation {

  final private val hashCodeToOperation = new mutable.HashMap[DominatorMemoryAccessKey, Int]

  def add(objectHashCode : DominatorMemoryAccessKey, operation : Int): Unit = {
    val newValue =
    hashCodeToOperation.get(objectHashCode) match {
      case None =>  operation;
      case Some(x) => x | operation;
    }
    hashCodeToOperation.put(objectHashCode,newValue);
  }

  def isReadOnly(objectHashCode : DominatorMemoryAccessKey) : Boolean  = {
    hashCodeToOperation.get(objectHashCode) match {
      case None => true;
      case Some(x) => x == MemoryAccessType.IS_READ;
    }
  }


}
