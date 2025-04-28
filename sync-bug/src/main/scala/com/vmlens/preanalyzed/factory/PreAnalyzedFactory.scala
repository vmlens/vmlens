package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model._

import com.vmlens.preanalyzed.factory.ConcurrentHashMapFactory.concurrentHashMap;
import com.vmlens.preanalyzed.factory.AtomicIntegerFactory.atomicInteger;
import scala.collection.mutable.ArrayBuffer

class PreAnalyzedFactory {


  def create(): List[PreAnalyzed] = flatten(createWithLists());
  
  
  private def createWithLists(): List[PreAnalyzedOrList] = {

    List[PreAnalyzedOrList](

      GetReadWriteLock("java/util/concurrent/locks/ReentrantReadWriteLock"),
      Lock("java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock", ReadLock(), lockMethods()),
      Lock("java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock", WriteLock(), lockMethods()),
      Lock("java/util/concurrent/locks/ReentrantLock", ReentrantLock(), lockMethods()),

      concurrentHashMap(),
      atomicInteger(),
     
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
