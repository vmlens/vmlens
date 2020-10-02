package com.vmlens.api.internal.reports


import java.io.File;
import com.vmlens.api.internal.reports.element._;


object View2X {
  
  // val titleIndex: String, val titleElements: String, val fileNameElements : String
  
  
  def addViews( viewResult : ViewResult[ReportElement[ContextReport]] , reportBuilderHtmlFile : ReportFactory[ContextReport] , context : ContextReport , elementTemplate : String, elementDetailTemplate : String ,
      elementTitle : String, titleIndex : String, noElementsTemplate : String)
  {
    
    // "templates/htmlNothingInterleaved.mustache" 
           
//         if(viewResult.isSingleThreaded)
//         {
//                 reportBuilderHtmlFile.addView(  Model2View.PATH_INDEX ,  new ViewProvider( "templates/htmlOnlyOneThread.mustache" , Model2View.PATH_INDEX  ,
//          new   ViewData(    viewResult.issues , Nil  ,  titleIndex , "" , context ) ) )
//          
//              reportBuilderHtmlFile.addView( context.fileNameElements , new ViewProvider("templates/htmlOnlyOneThread.mustache"   ,  context.fileNameElements ,
//          new   ViewData(    viewResult.elements  , Nil  ,  elementTitle , "" , context ) ) ) 
//          
//         }
//         else
      //   {
             reportBuilderHtmlFile.addView(  Model2View.PATH_INDEX ,  new ViewProvider( "templates/htmlIndex.mustache" , Model2View.PATH_INDEX  ,
          new   ViewData(    viewResult.issues , Nil  ,  titleIndex , None , "" , context ) ) )
    
    
   
          if( viewResult.elements.isEmpty )
          {
                       reportBuilderHtmlFile.addView( context.fileNameElements , new ViewProvider(  noElementsTemplate,  context.fileNameElements ,
          new   ViewData(    viewResult.elements  , Nil  ,  elementTitle , None , "" , context ) ) ) 
          }
          else
          {
                   reportBuilderHtmlFile.addView( context.fileNameElements , new ViewProvider(elementTemplate   ,  context.fileNameElements ,
          new   ViewData(    viewResult.elements  , Nil  ,  elementTitle , None ,  "" , context ) ) ) 
          }
     
       //  }
    
    
        
       

          

          

  }
    
  
  
  
  def save2File(viewResult : ViewResult[ReportElement[ContextReport]], reportDir : File, elementTemplate : String, elementDetailTemplate : String , elementTitle : String ,  noElementsTemplate : String)
  {
     val reportBuilderHtmlFile = new ResultHtmlFiles[ContextReport](reportDir );
     val contextReportBuilder = new ContextReportBuilder(reportBuilderHtmlFile,elementDetailTemplate,"Issues" ,elementTitle );
     val context = contextReportBuilder.createContext(true,true,false);
      
     addViews( viewResult  , reportBuilderHtmlFile ,
         context , elementTemplate , elementDetailTemplate  , elementTitle  , "Issues" ,  noElementsTemplate )
     
     
   reportBuilderHtmlFile.create();
  }
  
  
  def createOnlineMap(viewResult : ViewResult[ReportElement[ContextReport]], elementTemplate : String, elementDetailTemplate : String , elementTitle : String ,  noElementsTemplate : String ) =
  {
        val resultHtmlOnline = new ResultHtmlOnline();
        val contextReportBuilder = new ContextReportBuilder(resultHtmlOnline,elementDetailTemplate,"Issues" ,elementTitle  );
            val context = contextReportBuilder.createContext(true,false,true);
           
            
            addViews( viewResult  , resultHtmlOnline ,
         context , elementTemplate , elementDetailTemplate  , elementTitle  , "Issues" , noElementsTemplate)
       
         
         
         resultHtmlOnline;  
         
  }
  
  
  
}