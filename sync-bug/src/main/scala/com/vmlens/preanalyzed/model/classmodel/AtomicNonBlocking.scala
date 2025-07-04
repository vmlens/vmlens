package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.{AtomicArrayNonBlockingMethod, AtomicClassNonBlockingMethod, AtomicNonBlockingMethod, AtomicNonBlockingMethodType, NotYetImplemented, ClassModel}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.{ArrayNonBlockingMethod, NonBlockingMethod, NotYetImplementedMethod}
import com.vmlens.preanalyzed.model._

import scala.collection.mutable.ArrayBuffer


case class AtomicNonBlocking(name : String, methods : List[AtomicNonBlockingMethod]) extends ClassModel {
  def take(className: String) : Boolean = className.equals(name);

  override def create(): PackageOrClass = new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, atomicNonBlockingMethodArray(methods))

  private def atomicNonBlockingMethodArray(methods: List[AtomicNonBlockingMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      addNonBlocking(elem, buffer);
    }
    buffer.toArray;
  }

  private def addNonBlocking(method: AtomicNonBlockingMethod, buffer: ArrayBuffer[PreAnalyzedMethod]): Unit = {
    method match {
      case AtomicArrayNonBlockingMethod(name, desc, methodType) => {
        addAtomicArrayMethod(name, desc, methodType, buffer)
      }
      case AtomicClassNonBlockingMethod(name, desc, methodType) => {
        addAtomicClassMethod(name, desc, methodType, buffer)
      }

    }
  }

  private def addAtomicArrayMethod(name: String, desc: String, methodType: AtomicNonBlockingMethodType,
                                   buffer: ArrayBuffer[PreAnalyzedMethod]): Unit = {
    methodType match {
      case Read() => {
        buffer.append(new PreAnalyzedMethod(name, desc, ArrayNonBlockingMethod.ARRAY_NON_BLOCKING_READ))

      }
      case Write() => {
        buffer.append(new PreAnalyzedMethod(name, desc, ArrayNonBlockingMethod.ARRAY_NON_BLOCKING_WRITE))

      }
      case ReadWrite() => {
        buffer.append(new PreAnalyzedMethod(name, desc, ArrayNonBlockingMethod.ARRAY_NON_BLOCKING_READ_WRITE))
      }

      case
        NotYetImplemented() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NotYetImplementedMethod.SINGLETON))

      }
    }
  }

  private def addAtomicClassMethod(name: String, desc: String, methodType: AtomicNonBlockingMethodType,
                                   buffer: ArrayBuffer[PreAnalyzedMethod]): Unit = {
    methodType match {
      case Read() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NonBlockingMethod.NON_BLOCKING_READ))

      }
      case Write() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NonBlockingMethod.NON_BLOCKING_WRITE))

      }
      case ReadWrite() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NonBlockingMethod.NON_BLOCKING_READ_WRITE))
      }

      case
        NotYetImplemented() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NotYetImplementedMethod.SINGLETON))

      }
    }
  }
}
