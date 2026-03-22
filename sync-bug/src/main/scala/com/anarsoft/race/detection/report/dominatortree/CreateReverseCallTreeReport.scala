package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.dominatortree.VertexState
import com.anarsoft.race.detection.report.dominatortree.CallTreeTraversal


class CreateReverseCallTreeReport {
  
  def createReport(node : VertexState, 
                   dominatorTreeTraversalContext : DominatorTreeTraversalContext,
                   fileName : String): Unit = {
    
    val traversal = new CallTreeTraversal(dominatorTreeTraversalContext.dominatorTree.callGraph,
      dominatorTreeTraversalContext.dominatorTree.root , dominatorTreeTraversalContext.descriptionContext);


    traversal.traverse(node);

    dominatorTreeTraversalContext.createHtmlReport.createReverseCallTree(traversal.result,
      node.getLabel(dominatorTreeTraversalContext.descriptionContext) ,fileName, 
      dominatorTreeTraversalContext.runName ,dominatorTreeTraversalContext.runFileName );
    
  }
  

}
