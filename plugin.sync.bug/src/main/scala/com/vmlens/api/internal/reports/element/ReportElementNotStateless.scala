package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._;
import com.vmlens.api.internal.reports._;
import com.anarsoft.race.detection.process.sharedState._;

class ReportElementNotStateless(val element : NotStateless, val modelFacade : ModelFacadeState)  extends ReportElement  with ReportWithName  {
  
   
   def name = element.name(modelFacade);
  
  
   var link : Option[String] = None;
   def imagePath=
   {
   
     val icon = element.icon(modelFacade);
    
  
    if( icon == null )
    {
      null;
    }
    else
    {
      "img/" + icon.getName() + ".png" 
    }
  
   }
     
   
   
  
   
   
   def initialize( contextReport : ContextReport) 
   {
    
            link = contextReport.issueDetailLinks.createLink(  
           
         new ViewData(  element.children(modelFacade).map(  x =>  new ReportElementIssuePart(x,modelFacade)   )  , Nil , name , None ,   "../" , contextReport  ) , None
       
       );
     
//      for( x <- element.memoryAggregateSet(modelFacade) )
//      {
//           val a =  modelFacade.fieldAndArrayPerStackTraceFacade.stacktraceAggreagateOrdinal2LocationInClass(x);
//           println( a.owned + " " + a.sortable + " " + a.location.getName(modelFacade.fieldAndArrayFacade,modelFacade.stackTraceGraph) );
//      }
//     
//     
//     
//      val tuple = CreateSharedStateGroupListFacade.create4NotStateless(element.stackTraceOrdinalSet.map(  x =>x.ordinal ),
//         modelFacade.stackTraceGraph , modelFacade.fieldAndArrayPerStackTraceFacade);
//     
//     
//         val child = 
//    new ReportElementList("Calling Method:" , tuple._2.map( x =>  new ReportMethodWithSharedState( x,  modelFacade , true)   ).sorted.take(20)  );
//      
//         
//       val list =   List(  new  ReportParentChild(new ReportElementList("" , Nil), child , new ReportElementList("" , Nil) , new ReportElementList("" , Nil))   )  
//      
//      
//               link = contextReport.issueDetailLinks.createLink4Template(  
//        new ViewData(list , Nil ,  element.methodOrdinal.fullName( modelFacade )  ,   "../" , contextReport  ) , None , "templates/htmlSharedStateDetail.mustache" )
//     
     
//          link = contextReport.issueDetailLinks.createLink4Template(  
//           
//         new ViewData(  element.partList.map(  x =>  new ReportElementInconsistentOwnerStateField(x,modelFacade)   )  , Nil , name ,   "../" , contextReport  ) , None , "templates/htmlIndex.mustache"
//       
//       );
   }
  
  
  
  
}