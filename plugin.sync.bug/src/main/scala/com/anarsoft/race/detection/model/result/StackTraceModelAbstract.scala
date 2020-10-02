package com.anarsoft.race.detection.model.result

abstract class StackTraceModelAbstract extends IssuePartElement  {
 
   def  ordinal : StackTraceOrdinal;
  
    def name(viewTyp : ModelFacadeAll)  = nameInternal(viewTyp) + ordinal.lineNumber(viewTyp.stackTraceGraph);
    
     def nameWithHtml(viewTyp : ModelFacadeAll)  = nameWithHtmlInternal(viewTyp) + ordinal.lineNumber(viewTyp.stackTraceGraph);
    
     def nameWithHtmlInternal(viewTyp : ModelFacadeAll) : String
   
     def nameInternal(viewTyp : ModelFacadeAll)  : String;
  
    def searchData(ModelFacadeAll : ModelFacadeAll)  =
    {
      ModelFacadeAll.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(ordinal).searchData();
    }
    
    
       def name4Yaml( ModelFacadeAll : ModelFacadeAll) =   "- " + name(ModelFacadeAll);
     def title4Yaml(position : Int) = None;
    
  
}