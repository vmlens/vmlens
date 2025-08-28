package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.factory.AccumulatorFactory.{doubleAccumulator, longAccumulator}
import com.vmlens.preanalyzed.factory.AdderFactory.{doubleAdder, longAdder}
import com.vmlens.preanalyzed.factory.AtomicBooleanFactory.atomicBoolean
import com.vmlens.preanalyzed.factory.AtomicIntegerOrLongArrayFactory.{atomicIntegerArray, atomicLongArray}
import com.vmlens.preanalyzed.model.*
import com.vmlens.preanalyzed.model.classmodel.*
import com.vmlens.preanalyzed.factory.ConcurrentHashMapFactory.concurrentHashMap
import com.vmlens.preanalyzed.factory.AtomicIntegerOrLongFactory.{atomicInteger, atomicLong}
import com.vmlens.preanalyzed.factory.AtomicMarkableReferenceFactory.atomicMarkableReference
import com.vmlens.preanalyzed.factory.AtomicReferenceArrayFactory.atomicReferenceArray
import com.vmlens.preanalyzed.factory.ConditionFactory.condition
import com.vmlens.preanalyzed.factory.AtomicReferenceFactory.atomicReference
import com.vmlens.preanalyzed.factory.AtomicStampedReferenceFactory.atomicStampedReference
import com.vmlens.preanalyzed.factory.ConcurrentLinkedDequeFactory.concurrentLinkedDeque
import com.vmlens.preanalyzed.factory.ConcurrentLinkedQueueFactory.concurrentLinkedQueue
import com.vmlens.preanalyzed.factory.ConcurrentSkipListMapFactory.concurrentSkipListMap
import com.vmlens.preanalyzed.factory.ForGuineaPig.forGuineaPig
import com.vmlens.preanalyzed.factory.FutureFactory.futureTask
import com.vmlens.preanalyzed.model.lockoperation.{LockEnter, LockExit, NewCondition}
import com.vmlens.preanalyzed.model.classmodel.NotYetImplementedClass

import java.lang.invoke.MethodType;

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class PreAnalyzedFactory {


  def create(): List[ClassModel] = flatten(createWithLists());


  private def loadNotYetImplemented(): PreAnalyzedList = {
    val result = new ArrayBuffer[ClassModel]();
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

      DoNotTraceIn("java/lang/ClassLoader"),

      /* To avoid testing concurrent hash map inside varhandle:
at com.vmlens.trace.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:46)
at java.util.Objects.equals(Objects.java:64)
at java.util.Arrays.equals(Arrays.java:2978)
at java.lang.invoke.MethodType.equals(MethodType.java:861)
at java.lang.invoke.MethodType.equals(MethodType.java:853)
at java.util.concurrent.ConcurrentHashMap.get(ConcurrentHashMap.java:940)
at java.lang.invoke.MethodType$ConcurrentWeakInternSet.get(MethodType.java:1370)
at java.lang.invoke.MethodType.makeImpl(MethodType.java:343)
at java.lang.invoke.MethodTypeForm.canonicalize(MethodTypeForm.java:257)
at java.lang.invoke.MethodTypeForm.findForm(MethodTypeForm.java:219)
at java.lang.invoke.MethodType.makeImpl(MethodType.java:358)
at java.lang.invoke.MethodHandleNatives.findMethodHandleType(MethodHandleNatives.java:399)
*/
      DoNotTraceIn("java/lang/invoke/MethodType"),

      DoNotTraceIn("com/vmlens/test/guineapig/DoNotTraceIn"),

      loadNotYetImplemented(),

      forGuineaPig(),

      ThreadPool("com/vmlens/test/guineapig/ThreadPoolExecutorGuineaPig",List(
        ThreadStart( "execute", "(Ljava/lang/Runnable;)V"),
        JoinAll( "shutdown", "()V" ))),

      ThreadPool("java/util/concurrent/ThreadPoolExecutor",List(
        ThreadStart( "execute", "(Ljava/lang/Runnable;)V"),
        JoinAll( "shutdown", "()V" ))),
      
      GetReadWriteLock("java/util/concurrent/locks/ReentrantReadWriteLock"),
      Lock("java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock", ReadLock(), lockMethods()),
      Lock("java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock", WriteLock(), lockMethods()),
      Lock("java/util/concurrent/locks/ReentrantLock", ReentrantLock(), lockMethods()),

      condition(),

      Include("java/util/concurrent/PriorityBlockingQueue"),
      Filter("java/util/concurrent/PriorityBlockingQueue$"),

      Include("java/util/concurrent/LinkedBlockingQueue"),
      Include("java/util/concurrent/LinkedBlockingDeque"),
      Include("java/util/concurrent/DelayQueue"),
      Include("java/util/concurrent/Delayed"),
      Include("java/util/concurrent/CopyOnWriteArrayList"),
      Include("java/util/concurrent/CopyOnWriteArraySet"),

      Include("java/util/concurrent/ArrayBlockingQueue"),

      Include("java/util/concurrent/ConcurrentSkipListSet"),

      concurrentLinkedQueue(),
      concurrentLinkedDeque(),
      
      concurrentHashMap(),
      concurrentSkipListMap(),
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

      atomicIntegerArray(),
      atomicLongArray(),
      atomicReferenceArray(),

      ThreadModel("java/lang/Thread"),
      
      // Not sure if I need this,
      // anonymous classes are called
      // using the containing class name not the class they extend
      FilterInnerIncludeAnonymousClass("java/lang/Thread$"),

      VMLensApi("com/vmlens/api/AllInterleavings"),
      
      futureTask(),
      
      Include("java/util/concurrent/FutureTask"),

      Include("java/util/concurrent/Executors"),
      Include("java/util/concurrent/AbstractExecutorService"),
      Filter("java/util/concurrent"),
      Filter("java/util/stream"),
      Filter("java/util/Arrays"),

      Include("java/util/"),
      Include("java/text/"),

      Include("java/io/"),

      // Best is to use pre analyzed for field access
      //Include("java/lang/reflect"),

      Filter("java"),
      Filter("sun"),
      Filter("jdk"),

      Filter("net/bytebuddy"),
      Filter("org/objenesis"),

      Include(""),
    );
  }
  
  private def flatten(orig: List[PreAnalyzedOrList]): List[ClassModel] = {
    val result = new ArrayBuffer[ClassModel]
    for (elem <- orig) {
      for (preAnalyzed <- elem.asList()) {
        result.append(preAnalyzed);
      }
    }
    result.toList;
  }

  private def lockMethods(): List[LockMethod] =
    List[LockMethod](LockMethod("lock", "()V", LockEnter()),
      LockMethod("tryLock", "()Z", LockEnter()),
      LockMethod("tryLock", "(JLjava/util/concurrent/TimeUnit;)Z ", LockEnter()),
      LockMethod("unlock", "()V", LockExit()),
      LockMethod("newCondition", "()Ljava/util/concurrent/locks/Condition;", NewCondition()));

}
