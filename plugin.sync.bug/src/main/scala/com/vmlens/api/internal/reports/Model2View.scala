package com.vmlens.api.internal.reports

import org.apache.commons.lang.StringEscapeUtils

object Model2View
{
  val headerIssue = "Race Conditions, Deadlocks";
  val headerMemoryAndMonitorAccess = "Memory, Monitor Access";
//  val headerParallelize = "Parallelized";
  
  
  val PATH_INDEX = "issues.html";
//  val PATH_PARALLIZED =  "parallelized.html";
//  val PATH_SHARED_STATE = "sharedState.html";
  
  
  
  val BREAK = "<wbr>" //"&#x200b;"   // ".&shy;" über br und css könnte man es auch versuchen
  
  
  def makeBreakable(in : String) =
  {
     addBreak ( StringEscapeUtils.escapeHtml( in) )
  }
  
  def addBreak( in : String ) =
  {
    in.replace( ".", "." + BREAK).replace( "("  ,  BREAK +  "(");
  }
  
 
  
  
//  
//  
//  
//    def sortIssueList(  modelFacade : ModelFacadeAll  , seq : Seq[IssueModelElement]     ) = 
//    seq.sortWith(  ( left , right ) =>  left.compare(right, modelFacade) > 0  )
//  
//  
//  def createFileBased(modelFacade : ModelFacadeAll   ,  reportDir : File ,  reportElementLastRunAndCountList : Seq[ReportElementLastRunAndCount] ) =
//  {
//     
//    
//     create( modelFacade  , new Model2ViewResultFactory4HtmlFiles(reportDir) , reportElementLastRunAndCountList );
//     
//     
//    
//  }
//  
//    
//   def createOnline(modelFacade : ModelFacadeAll   ) =
//  {
//     
//    
//     create( modelFacade  , new Model2ViewResultFactory4HtmlOnline() , Nil);
//     
//     
//    
//  }
//    
//    
//    
//    
//    
//   def createTest(modelFacade : ModelFacadeAll   , reportElementLastRunAndCountList : Seq[ReportElementLastRunAndCount] ) =   create( modelFacade  , new Model2ViewResultFactory4Test() , reportElementLastRunAndCountList);
//    
//    
//    
//    
//    def create[RESULT](modelFacade : ModelFacadeAll ,  facade : Model2ViewResultFactory[RESULT] , reportElementLastRunAndCountList : Seq[ReportElementLastRunAndCount] ) =
//    {
//      
//    
//      
//      
//      
//      
//  
//      
//      
//       val seq = sortIssueList( modelFacade ,  (modelFacade.races ++ modelFacade.deadlocks).toSeq);
//       
//       facade.createResult( seq.map(  x =>   new ReportElementIssue(x,modelFacade ) ).sortBy( x => x.name   )  ,reportElementLastRunAndCountList  )
//       
//       
//    }
//    
//    
 
    
    
    
  
}
