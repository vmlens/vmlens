package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex


class TreeTestBuilder(val createGraphStack : CreateGraphStack) {

  def method(id : Int, inside : () => Unit  ): Unit = {
    val vertex = createGraphStack.methodEnter(id);
    inside();
    createGraphStack.addAllElementsOfStackToGraph()
    createGraphStack.methodExit();
  }

  def syncBlock(id : Int, inside : () => Unit  ): DominatorTreeVertex = {
    val vertex = createGraphStack.monitorEnter(id);
    inside();
    createGraphStack.monitorExit();
    vertex;
  }

}

object TreeTestBuilder {
  
  def apply(context : CreateGraphStackContext, threadId : Int) : TreeTestBuilder = 
    new TreeTestBuilder(context.createGraphStack(threadId));
  
  
}
