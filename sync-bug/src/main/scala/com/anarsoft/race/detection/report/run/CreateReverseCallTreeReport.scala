package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.LeafNode




class CreateReverseCallTreeReport {
  
  def createReport(node : LeafNode, 
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
