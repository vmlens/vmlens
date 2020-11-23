package com.anarsoft.race.detection.process.filter

import com.vmlens.api._;
import scala.collection.mutable.HashMap


class FilterMap () {
  

  /*
   *     // Cached field accessor created without override
    private FieldAccessor fieldAccessor;
    // Cached field accessor created with override
    private FieldAccessor overrideFieldAccessor;
   */
  
  val benignRaces = Set(
      
       "com.google.common.collect.ImmutableMap.entrySet" ,
       "com.google.common.collect.ImmutableSet.asList",
       "java.math.BigDecimal.stringCache" ,
       "sun.reflect.NativeMethodAccessorImpl.numInvocations",
       "sun.reflect.NativeConstructorAccessorImpl.numInvocations",
       "java.util.LinkedHashMap.values",
       "java.util.LinkedHashMap.keySet",
       "java.util.LinkedHashMap.entrySet",
       
       "java.util.TreeMap.navigableKeySet",
       "java.util.TreeMap.keySet",
       "java.util.TreeMap.entrySet",
       "java.util.TreeMap.values",
       
       "java.util.Collections$UnmodifiableMap.keySet",
       "java.util.Collections$UnmodifiableMap.entrySet",
       "java.util.Collections$UnmodifiableMap.values",
       "sun.reflect.DelegatingMethodAccessorImpl.delegate",
       "sun.reflect.DelegatingConstructorAccessorImpl.delegate",
       
       "java.util.concurrent.ConcurrentHashMap.keySet",
       "java.util.concurrent.ConcurrentHashMap.values",
       "java.util.concurrent.ConcurrentHashMap.entrySet",
       
       "java.util.concurrent.ConcurrentSkipListMap.keySet",
       "java.util.concurrent.ConcurrentSkipListMap.entrySet",
       "java.util.concurrent.ConcurrentSkipListMap.values",
       "java.util.concurrent.ConcurrentSkipListMap.descendingMap",
    
       
       "java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.heapIndex",
       "java.util.concurrent.ConcurrentHashMap$CounterCell[]",
       "java.util.concurrent.atomic.Striped64$Cell[]",
       
       "java.lang.reflect.Field.fieldAccessor",
       "java.lang.reflect.Field.overrideFieldAccessor",
       
       "org.apache.tomcat.util.buf.StringCache.accessCount",
       "org.apache.tomcat.util.buf.StringCache.hitCount",
      
       "java.util.concurrent.LinkedBlockingQueue$Node.next",
       
          "java.util.TimeZone.ID"
  );
  
 
  
  
  
  def take(name : String) =
  {
    if(benignRaces.contains(name))
    {
      false
    }
    else
    {
      true
    }
  }
  
  
  /*
   * offen:
   * "java.lang.invoke"
   * "java.lang.reflect"
   * "sun.reflect"
   * 
   */
  
  
  
  
  
  
  
  
 
  
  
}

object FilterMap
{
  
  def apply() =
  {
   new FilterMap();
  }
  
  
}