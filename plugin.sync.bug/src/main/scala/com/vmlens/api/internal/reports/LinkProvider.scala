package com.vmlens.api.internal.reports

class LinkProvider(val prefix : String, val templateName : String, val context : ReportFactory[ContextReport]) {
  
  
   context.addPrefix(prefix)
  
   var linkIndex = 0;
   val LOCK = new Object();
    
  
   
   def createLink4Template( viewData : ViewData[ContextReport], rootOption : Option[String], useThisTemplateName : String) =
   {
     
       LOCK.synchronized
      {
      val linkText =  prefix + linkIndex + ".html";
      
    // ( val elements : Seq[ReportElement]  , val warnings : Seq[String],val title : String ,val fileName : String ,val filePrefix : String, val templateName : String  )
     // val view = new ReportView( elements , warnings , title ,linkText   ,  templateName ,"../" , contextReport  );
      
      context.addView(linkText, new ViewProvider( useThisTemplateName , linkText , viewData )  );
      
      
      linkIndex = linkIndex + 1;
      
      
      rootOption match
      {
        case None =>
          {
            linkText;

          }
          
        case Some(x) =>
          {
             viewData.root + linkText;
          }
         
      }  
      
      
            }
   }
   
  
  
  
    def createLink(  viewData : ViewData[ContextReport], rootOption : Option[String])  =
    {
      createLink4Template(viewData , rootOption , templateName);
    }
  
//  def createLinkFromChild(viewData : ViewData  ) : Option[String] =
//{
//     if( linkIndex > 100 )
//     {
//       None
//     }
//     else{
//       
//   
//    
//    LOCK.synchronized
//      {
////    val linkText =  prefix + linkIndex + ".html";
//    
//    val fileName = prefix + linkIndex + ".html"
//    
//      
//    // ( val elements : Seq[ReportElement]  , val warnings : Seq[String],val title : String ,val fileName : String ,val filePrefix : String, val templateName : String  )
//    //   val view = new ReportView( elements , Nil , title ,fileName   ,  templateName ,"../" , contextReport  );
//      
//      context.addView(fileName,new ViewProvider( templateName , fileName ,  viewData ) );
//      
//      
//      linkIndex = linkIndex + 1;
//      
//      
//      Some(viewData.root + fileName);
//      }
//      }
}
  
  
