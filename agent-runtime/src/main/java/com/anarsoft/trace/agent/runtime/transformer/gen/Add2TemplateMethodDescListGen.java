package com.anarsoft.trace.agent.runtime.transformer.gen;

import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDescSingleMethod;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import org.objectweb.asm.Opcodes;


public  class Add2TemplateMethodDescListGen   implements Opcodes {
	


public static  void add2TemplateList(TLinkedList<TemplateMethodDesc> templateMethodDescList )
{



			


templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "put" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "putAll" , "(Ljava/util/Map;)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;Ljava/util/Map;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "remove" , "(Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "clear" , "()V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "size" , "()I" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;I)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "isEmpty" , "()Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "get" , "(Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "containsKey" , "(Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "containsValue" , "(Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "entrySet" , "()Ljava/util/Set;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;I)Ljava/util/Set;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "keySet" , "()Ljava/util/Set;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapCallbackGen" , "(Ljava/util/HashMap;I)Ljava/util/Set;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "put" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "putAll" , "(Ljava/util/Map;)V" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;Ljava/util/Map;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "remove" , "(Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "clear" , "()V" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "size" , "()I" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;I)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "isEmpty" , "()Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "get" , "(Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "containsKey" , "(Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "containsValue" , "(Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "entrySet" , "()Ljava/util/Set;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;I)Ljava/util/Set;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "keySet" , "()Ljava/util/Set;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapCallbackGen" , "(Ljava/util/Map;I)Ljava/util/Set;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "add" , "(Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "remove" , "(Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "retainAll" , "(Ljava/util/Collection;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;Ljava/util/Collection;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "removeAll" , "(Ljava/util/Collection;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;Ljava/util/Collection;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "clear" , "()V" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "size" , "()I" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;I)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "isEmpty" , "()Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "contains" , "(Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "toArray" , "()[Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;I)[Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "toArray" , "([Ljava/lang/Object;)[Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;[Ljava/lang/Object;I)[Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "containsAll" , "(Ljava/util/Collection;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;Ljava/util/Collection;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Set" ,    "iterator" , "()Ljava/util/Iterator;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/SetCallbackGen" , "(Ljava/util/Set;I)Ljava/util/Iterator;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "add" , "(Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "remove" , "(Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "retainAll" , "(Ljava/util/Collection;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;Ljava/util/Collection;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "removeAll" , "(Ljava/util/Collection;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;Ljava/util/Collection;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "clear" , "()V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "size" , "()I" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;I)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "isEmpty" , "()Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "contains" , "(Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "toArray" , "()[Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;I)[Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "toArray" , "([Ljava/lang/Object;)[Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;[Ljava/lang/Object;I)[Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "containsAll" , "(Ljava/util/Collection;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;Ljava/util/Collection;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashSet" ,    "iterator" , "()Ljava/util/Iterator;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashSetCallbackGen" , "(Ljava/util/HashSet;I)Ljava/util/Iterator;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "putIfAbsent" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "remove" , "(Ljava/lang/Object;Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "replace" , "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "replace" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "computeIfAbsent" , "(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/util/function/Function;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "computeIfPresent" , "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/util/function/BiFunction;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "compute" , "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/util/function/BiFunction;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "merge" , "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "replaceAll" , "(Ljava/util/function/BiFunction;)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/util/function/BiFunction;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "getOrDefault" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/HashMap" ,    "forEach" , "(Ljava/util/function/BiConsumer;)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/HashMapJDK8CallbackGen" , "(Ljava/util/HashMap;Ljava/util/function/BiConsumer;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "putIfAbsent" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "remove" , "(Ljava/lang/Object;Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "replace" , "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "replace" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "computeIfAbsent" , "(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/util/function/Function;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "computeIfPresent" , "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/util/function/BiFunction;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "compute" , "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/util/function/BiFunction;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "merge" , "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "replaceAll" , "(Ljava/util/function/BiFunction;)V" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/util/function/BiFunction;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "getOrDefault" , "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Map" ,    "forEach" , "(Ljava/util/function/BiConsumer;)V" , INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/gen/MapJDK8CallbackGen" , "(Ljava/util/Map;Ljava/util/function/BiConsumer;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putByteVolatile" , "(Ljava/lang/Object;JB)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JBI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putDoubleVolatile" , "(Ljava/lang/Object;JD)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JDI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putFloatVolatile" , "(Ljava/lang/Object;JF)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JFI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putIntVolatile" , "(Ljava/lang/Object;JI)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JII)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putLongVolatile" , "(Ljava/lang/Object;JJ)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JJI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putCharVolatile" , "(Ljava/lang/Object;JC)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JCI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putShortVolatile" , "(Ljava/lang/Object;JS)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JSI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putBooleanVolatile" , "(Ljava/lang/Object;JZ)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JZI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putObjectVolatile" , "(Ljava/lang/Object;JLjava/lang/Object;)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getByteVolatile" , "(Ljava/lang/Object;J)B" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)B" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getDoubleVolatile" , "(Ljava/lang/Object;J)D" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)D" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getFloatVolatile" , "(Ljava/lang/Object;J)F" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)F" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getIntVolatile" , "(Ljava/lang/Object;J)I" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getLongVolatile" , "(Ljava/lang/Object;J)J" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)J" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getCharVolatile" , "(Ljava/lang/Object;J)C" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)C" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getShortVolatile" , "(Ljava/lang/Object;J)S" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)S" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getBooleanVolatile" , "(Ljava/lang/Object;J)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getObjectVolatile" , "(Ljava/lang/Object;J)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "compareAndSwapInt" , "(Ljava/lang/Object;JII)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JIII)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "compareAndSwapLong" , "(Ljava/lang/Object;JJJ)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JJJI)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "compareAndSwapObject" , "(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;I)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putOrderedInt" , "(Ljava/lang/Object;JI)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JII)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putOrderedLong" , "(Ljava/lang/Object;JJ)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JJI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putOrderedObject" , "(Ljava/lang/Object;JLjava/lang/Object;)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getAndAddInt" , "(Ljava/lang/Object;JI)I" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackJDK8Gen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JII)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getAndAddLong" , "(Ljava/lang/Object;JJ)J" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackJDK8Gen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JJI)J" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getAndSetInt" , "(Ljava/lang/Object;JI)I" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackJDK8Gen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JII)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getAndSetLong" , "(Ljava/lang/Object;JJ)J" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackJDK8Gen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JJI)J" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getAndSetObject" , "(Ljava/lang/Object;JLjava/lang/Object;)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackJDK8Gen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;I)Ljava/lang/Object;" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putByte" , "(Ljava/lang/Object;JB)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JBI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putDouble" , "(Ljava/lang/Object;JD)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JDI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putFloat" , "(Ljava/lang/Object;JF)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JFI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putInt" , "(Ljava/lang/Object;JI)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JII)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putLong" , "(Ljava/lang/Object;JJ)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JJI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putChar" , "(Ljava/lang/Object;JC)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JCI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putShort" , "(Ljava/lang/Object;JS)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JSI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putBoolean" , "(Ljava/lang/Object;JZ)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JZI)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "putObject" , "(Ljava/lang/Object;JLjava/lang/Object;)V" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;I)V" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getByte" , "(Ljava/lang/Object;J)B" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)B" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getDouble" , "(Ljava/lang/Object;J)D" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)D" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getFloat" , "(Ljava/lang/Object;J)F" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)F" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getInt" , "(Ljava/lang/Object;J)I" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)I" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getLong" , "(Ljava/lang/Object;J)J" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)J" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getChar" , "(Ljava/lang/Object;J)C" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)C" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getShort" , "(Ljava/lang/Object;J)S" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)S" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getBoolean" , "(Ljava/lang/Object;J)Z" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)Z" ) ); 



templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "sun/misc/Unsafe" ,    "getObject" , "(Ljava/lang/Object;J)Ljava/lang/Object;" , INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/gen/UnsafeCallbackGen" , "(Lsun/misc/Unsafe;Ljava/lang/Object;JI)Ljava/lang/Object;" ) ); 









}


	
}

