package com.anarsoft.race.detection.util

import scala.collection.mutable.ArrayBuffer

class Stack[A] {

  private val buffer = ArrayBuffer.empty[A]

  def push(elem: A): Unit =
    buffer += elem

  def removeFirst(elem: A): Boolean = {
    // Start from the top (end of the buffer) and go backwards
    val index = buffer.lastIndexWhere(_ == elem)
    if (index >= 0) {
      buffer.remove(index)
      true
    } else {
      false
    }
  }
  
  
  def pop(): A = {
    if (buffer.isEmpty)
      throw new NoSuchElementException("pop on empty stack")
    buffer.remove(buffer.length - 1)
  }

  def top: A = {
    if (buffer.isEmpty)
      throw new NoSuchElementException("peek on empty stack")
    buffer(buffer.length - 1)
  }

  def isEmpty: Boolean =
    buffer.isEmpty

  def nonEmpty: Boolean = ! isEmpty
  

  def size: Int =
    buffer.size
  
  def foreach[U](f: A => U): Unit = buffer.foreach(f)
  
}
