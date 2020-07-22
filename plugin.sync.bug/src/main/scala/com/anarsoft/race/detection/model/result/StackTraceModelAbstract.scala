package com.anarsoft.race.detection.model.result

abstract class StackTraceModelAbstract extends IssuePartElement  {
 
   def  ordinal : StackTraceOrdinal;
  
    def name(viewTyp : ModelFacade)  = nameInternal(viewTyp) + ordinal.lineNumber(viewTyp.stackTraceGraph);
    
     def nameWithHtml(viewTyp : ModelFacade)  = nameWithHtmlInternal(viewTyp) + ordinal.lineNumber(viewTyp.stackTraceGraph);
    
     def nameWithHtmlInternal(viewTyp : ModelFacade) : String
   
     def nameInternal(viewTyp : ModelFacade)  : String;
  
    def searchData(modelFacade : ModelFacade)  =
    {
      modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(ordinal).searchData();
    }
    
    
       def name4Yaml( modelFacade : ModelFacade) =   "- " + name(modelFacade);
     def title4Yaml(position : Int) = None;
    
  
}