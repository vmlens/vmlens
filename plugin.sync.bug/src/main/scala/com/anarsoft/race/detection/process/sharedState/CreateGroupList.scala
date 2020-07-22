package com.anarsoft.race.detection.process.sharedState

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.Stack
import com.anarsoft.race.detection.model.result._

abstract class CreateGroupList[BUILDER <: MethodWithXBuilder[BUILDER]] {

  def stackTraceGraphPackageAnnotation : StackTraceGraphPackageAnnotation;
  def createBuilder(methodOrdinal : MethodOrdinal, group : Int,stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph) : BUILDER;
  
  def processChilds(parent : BUILDER) : Boolean;
  
  
  def alwaysProcessRoot : Boolean;
  
  // new MethodWithSharedStateBuilder(methodOrdinal, group)
  
  def getOrUpdate(group: Int, methodOrdinal: MethodOrdinal,
    stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph, 
    group2MethodOrdinal2SharedState: ArrayBuffer[HashMap[MethodOrdinal, BUILDER]]) =
    {
      while (group >= group2MethodOrdinal2SharedState.length) {
        group2MethodOrdinal2SharedState.append(new HashMap[MethodOrdinal, BUILDER]);
      }

      val map = group2MethodOrdinal2SharedState(group);

      val st = map.getOrElseUpdate(methodOrdinal,createBuilder(methodOrdinal,group,stackTraceOrdinal,stackTraceGraph) )
      st.stackTraceOrdinalSet.add(stackTraceOrdinal);

      st;

    }

 
  
  def take( stacktraceOrdinal : StackTraceOrdinal   ) =
  {
    var doTake = false;
    
    
    stackTraceGraphPackageAnnotation.foreachRootPackageName( stacktraceOrdinal  , name =>
      {
         if(! name.equals("sun") && ! name.equals("java")  )
         {
           doTake = true;
         }
           
    
    
      } )
      
      doTake;
  }
  
  
//  def takeSet( stackTraceOrdinalSet : HashSet[StackTraceOrdinal]) =
//  {
//    var doTake = false;
//    
//    for( s <- stackTraceOrdinalSet )
//    {
//      doTake = doTake | take(s);
//    }
//    
//    
//    doTake
//    
//  }
//  
  
  
  
  def createChild(stackTraceOrdinalSet: Set[Int], stackTraceGraph: StackTraceGraph) =
    {

      val group2MethodOrdinal2SharedState = new ArrayBuffer[HashMap[MethodOrdinal, BUILDER]]
      val stack = new Stack[StackNode[BUILDER]]
      val alreadyVisited = new HashSet[StackTraceOrdinal]

      val startIndex = 1

      for (r <- stackTraceOrdinalSet) {

        val mo = stackTraceGraph.getMethodModelOrdinalForStackTraceNodeOrdinal(new StackTraceOrdinal(r))
       
        val builder = getOrUpdate(0, mo, new StackTraceOrdinal(r), stackTraceGraph, group2MethodOrdinal2SharedState);
           val st = Some(builder)
        
        
        if( alwaysProcessRoot || (  processChilds(builder.builder()) ) )
        {
          stackTraceGraph.foreachStackTraceNodeChild(new StackTraceOrdinal(r), child => {

          if( take(child) )
          {
          if (!alreadyVisited.contains(child)) {
            val childMo = stackTraceGraph.getMethodModelOrdinalForStackTraceNodeOrdinal(child)
            val childSate = getOrUpdate(startIndex, childMo, child, stackTraceGraph, group2MethodOrdinal2SharedState)

        

            stack.push(new StackNode(startIndex, childSate))
            alreadyVisited.add(child);
          }
          }
        })
        }
        
        
     

        

      }

      while (!stack.isEmpty) {
        val current = stack.pop();
        
        if( processChilds( current.sharedState.builder() )  )
        {
          for (r <- current.sharedState.stackTraceOrdinalSet) {
          stackTraceGraph.foreachStackTraceNodeChild(r, child => {

          if( take(child) )
          {
             if (!alreadyVisited.contains(child)) {
              val childMo = stackTraceGraph.getMethodModelOrdinalForStackTraceNodeOrdinal(child)
              val childSate = getOrUpdate(current.group + 1, childMo, child, stackTraceGraph, group2MethodOrdinal2SharedState)
             
            
              stack.push(new StackNode(current.group + 1, childSate))
              alreadyVisited.add(child);
            }
             }  
        })
        
           
        }

        }
        
        
        
      }

      val temp = new ArrayBuffer[BUILDER]

      for (group <- group2MethodOrdinal2SharedState) {
        for (elem <- group) {
          temp.append(elem._2);

        }

      }


//      for (x <- sharedStateCollection.reverse) {
//
//        x.fill(stackTraceGraph);
//      }

      temp.sortBy(x => x.group);

    }

}