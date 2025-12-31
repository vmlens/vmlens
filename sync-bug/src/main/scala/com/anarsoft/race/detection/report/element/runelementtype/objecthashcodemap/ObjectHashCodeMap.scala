package com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap

import java.util.HashMap


class ObjectHashCodeMap {
  private var maxCode = 0
  final private val hashCodeToThreadIndices = new HashMap[Long, ThreadIndices]

  def add(objectHashCode: Long, threadIndex: Int): Unit = {
    val indices = hashCodeToThreadIndices.get(objectHashCode)
    if (indices == null) hashCodeToThreadIndices.put(objectHashCode, new OneThreadIndex(threadIndex))
    else hashCodeToThreadIndices.put(objectHashCode, indices.addThreadIndex(threadIndex, this))
  }

  def asUiString(objectHashCode: Long): String = hashCodeToThreadIndices.get(objectHashCode).asUiString
  
  
  def isSingleThreaded(objectHashCode: Long) : Boolean =  hashCodeToThreadIndices.get(objectHashCode).isSingleThreaded
  
  def id(objectHashCode: Long): Option[Int] = {
    hashCodeToThreadIndices.get(objectHashCode).id;
  }

  def newCode: Int = {
    maxCode += 1
    maxCode
  }
}
