package com.anarsoft.plugin.view

import scala.collection.mutable.HashMap
import  com.vmlens.api.internal.ReportData4Plugin
import com.vmlens.api.internal.reports.ResultHtmlOnline
import com.anarsoft.plugin.sync.bug._;
import com.vmlens.api.internal.reports.Element4TreeViewer
import  com.vmlens.api.internal.ProcessFacade

class ViewManager {
  
  
   @volatile var viewState = getIdleViewSate();  //new State4AllViews(ViewStartData() , ViewStartData() , new ResultHtmlOnline()  );
  
//  @volatile var issueViewData : ViewData = ViewStartData() ;
//  @volatile var elementViewData :  ViewData = ViewStartData() ;
//  @volatile var onlineData = new ResultHtmlOnline();
   
   
   
   def getIdleViewSate() =
   { 
     val defaultUrl = "http://localhost:"  + HttpServer.instance.port + "/index.html";
     val urlData=   new ViewUrlData(   defaultUrl )
     
     
  
            new State4AllViews(urlData , urlData  , ProcessFacade. createHtmlProviderStart()  )
        
     
   }
   
   
   
  
  
  val id2ViewPartTemplate = new HashMap[String,ViewPartTemplate]
  
  
  
  
  def put(view : ViewPartTemplate)
  {
    this.synchronized
    {
        id2ViewPartTemplate.put(  view.id , view  );
    }
  
  }
  
  
  def remove(view : ViewPartTemplate)
  {
     this.synchronized
    {
        id2ViewPartTemplate.remove( view.id )
     }
  }
  
  
  
  private def updateLicenseOk(reportData4Plugin : ReportData4Plugin)
  {
    
    
    
     val issueViewData = 
     reportData4Plugin.issues match
     {
       case  Left(x)  =>
         {
            new ViewTreeData( x );
         }
       
       case Right(x) =>
         {
              new ViewUrlData(  "http://localhost:"  + HttpServer.instance.port + "/" + x );
         }
       
       
       
     }
    
    
    viewState = new State4AllViews(issueViewData , 
        new ViewUrlData(  "http://localhost:"  + HttpServer.instance.port + "/" + reportData4Plugin.elements ) , reportData4Plugin.resultHtmlOnline )
   
   
   
     this.synchronized
    {
    for( view <- id2ViewPartTemplate )
    {
      
      view._2.refresh();
    }
    }
  
  
         
    
    
  }
  
  
  def update(reportData4Plugin : ReportData4Plugin)
  {
   
      val defaultUrl = "http://localhost:"  + HttpServer.instance.port + "/index.html";
     val urlData=   new ViewUrlData(   defaultUrl )
    
    
   
           updateLicenseOk(reportData4Plugin);
     
     
      Activator.plugin.showView(Activator.ELEMENT_VIEW_ID)
     Activator.plugin.showView(Activator.ISSUE_VIEW_ID)
     
     
   }
    
    
  
  
  
}