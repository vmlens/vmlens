package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.factory.AccumulatorFactory.{doubleAccumulator, longAccumulator}
import com.vmlens.preanalyzed.factory.AdderFactory.{doubleAdder,longAdder}
import com.vmlens.preanalyzed.factory.AtomicBooleanFactory.atomicBoolean
import com.vmlens.preanalyzed.model.*
import com.vmlens.preanalyzed.factory.ConcurrentHashMapFactory.concurrentHashMap
import com.vmlens.preanalyzed.factory.AtomicIntegerOrLongFactory.{atomicInteger, atomicLong}
import com.vmlens.preanalyzed.factory.AtomicMarkableReferenceFactory.atomicMarkableReference
import com.vmlens.preanalyzed.factory.AtomicReferenceFactory.atomicReference
import com.vmlens.preanalyzed.factory.AtomicStampedReferenceFactory.atomicStampedReference

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class PreAnalyzedFactory {


  def create(): List[PreAnalyzed] = flatten(createWithLists());


  def loadNotYetImplemented(): PreAnalyzedList = {
    val result = new ArrayBuffer[PreAnalyzed]();
    val source = Source.fromResource("notYetImplementedClasses.txt")

    try {
      for (line <- source.getLines()) {
        result.append(NotYetImplementedClass(line));
      }
    } finally {
      source.close()
    }
    PreAnalyzedList(result.toList);
  }
  
  private def createWithLists(): List[PreAnalyzedOrList] = {

    List[PreAnalyzedOrList](

      loadNotYetImplemented(),

      GetReadWriteLock("java/util/concurrent/locks/ReentrantReadWriteLock"),
      Lock("java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock", ReadLock(), lockMethods()),
      Lock("java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock", WriteLock(), lockMethods()),
      Lock("java/util/concurrent/locks/ReentrantLock", ReentrantLock(), lockMethods()),

      Include("java/util/concurrent/PriorityBlockingQueue"),
      Filter("java/util/concurrent/PriorityBlockingQueue$"),

      Include("java/util/concurrent/LinkedBlockingQueue"),
      Include("java/util/concurrent/LinkedBlockingDeque"),
      Include("java/util/concurrent/DelayQueue"),
      Include("java/util/concurrent/Delayed"),
      Include("java/util/concurrent/CopyOnWriteArrayList"),
      Include("java/util/concurrent/CopyOnWriteArraySet"),

      Include("java/util/concurrent/ArrayBlockingQueue"),

      concurrentHashMap(),
      atomicInteger(),
      atomicLong(),

      atomicStampedReference(),
      atomicReference(),
      atomicMarkableReference(),
      atomicBoolean(),
      doubleAccumulator(),
      longAccumulator(),
      doubleAdder(),
      longAdder(),

      ThreadModel("java/lang/Thread"),
      VMLensApi("com/vmlens/api/AllInterleaving"),

      Include("java/lang/Thread$"),

      Filter("java/lang/Thread"),
      Filter("java/util/concurrent"),
      Filter("java/util/stream"),
      Filter("java/util/Arrays"),

      Include("java/util/"),

      Filter("java"),
      Filter("sun"),
      Filter("jdk"),

      Include(""),
    );
  }
  
  private def flatten(orig: List[PreAnalyzedOrList]): List[PreAnalyzed] = {
    val result = new ArrayBuffer[PreAnalyzed]
    for (elem <- orig) {
      for (preAnalyzed <- elem.asList()) {
        result.append(preAnalyzed);
      }
    }
    result.toList;
  }

  private def lockMethods(): List[LockMethod] =
    List[LockMethod](LockMethod("lock", "()V", LockEnter()),
      LockMethod("tryLock", "()V", LockEnter()),
      LockMethod("unlock", "()V", LockExit()));



 

}
