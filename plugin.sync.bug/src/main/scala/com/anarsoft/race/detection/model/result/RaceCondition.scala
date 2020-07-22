package com.anarsoft.race.detection.model.result


import com.anarsoft.integration._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import java.io.PrintStream
import com.vmlens.api._
import com.vmlens.api.internal._


class RaceCondition  (val reading : RaceConditionElement , val writing : RaceConditionElement, val otherRacesWithSameLocation : Int )  extends Equals with IssueModelElement {
  
   def titlePrefix() = "Data race:";
  
   def showDetailsTill(modelFacade : ModelFacadeAll) =
     {
     if(   otherRacesWithSameLocation > 1)
     {
       3;
     }
     else 
     {
         reading.locationInClass match
         {
           case f : FieldInClass => 
             {
               
               if( f.isFromTemplate(modelFacade.fieldAndArrayFacade) )
               {
                 1;
               }
               else if( f.getName(modelFacade.fieldAndArrayFacade, modelFacade.stackTraceGraph).startsWith("java.util") )
               {
                 3;
               }
               else
               {
                 0;
               }
               
               
             }
           
           case _ : ArrayInClass => 1;
           
           case _ : ClassAccess => 1;
           
           
           
         }
       
       
     }
}
     
  
  
  
  
  def getName(fieldAndArrayFacade : FieldAndArrayPerMethodFacade,stackTraceGraph : StackTraceGraph) = fieldAndArrayFacade.getQualifiedFieldName(reading.locationInClass,stackTraceGraph)
  
  
  def name(modelFacade : ModelFacadeAll) =  getName(modelFacade.fieldAndArrayFacade,modelFacade.stackTraceGraph);   //modelFacade.fieldAndArrayFacade.getQualifiedFieldName(reading.locationInClass,modelFacade);
  
  def icon(modelFacade : ModelFacadeAll)  = 
    
       IconRepository.getImageForField(   new  MemoryAccessType(reading.operation | writing.operation) , reading.locationInClass.isStatic , false , true  )
   
    
    
   
   // MemoryOperationType(new  MemoryAccessType( MemoryAccessType.IS_READ_WRITE ) ,  new MemoryAccessAttributes(  reading.locationInClass.isStatic , true )    )   
  
 // def getText(viewTyp : ModelFacade)  = reading.getQualifiedFieldName(viewTyp);
  
    def children( modelFacade : ModelFacadeAll)     =
        {
    val list = new ArrayBuffer[IssuePartElement]();
    
    list.append(  reading  );
    list.append(  writing  );
      
    list;
    
  }
    
  
  
  
   def compare(other :IssueModelElement,  modelFacade : ModelFacadeAll ) =
   {
     
     other match{
       
       case r : RaceCondition =>
         {
            name(modelFacade).compareTo( r.name(modelFacade) ) 
         }
       
       case d : Deadlock =>  
         {
           -1;
         }
       
     }
     
     
   }
  
  
  
  
  
  
  
  
  
  
  
  
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.model.result.RaceCondition]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.model.result.RaceCondition => that.canEqual(RaceCondition.this) && reading == that.reading && writing == that.writing
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + reading.hashCode) + writing.hashCode
  }
  
  
  
  
    def searchData(modelFacade : ModelFacadeAll)  =
    {
      reading.locationInClass match
      {
        
          case  FieldInClass( ordinal : Int,  isVolatile : Boolean,  isStatic : Boolean ) =>
         {
           
               val model = modelFacade.fieldAndArrayFacade.fieldOrdinal2FieldModelDesc(ordinal);
               
              model.getSearchData();
           
         }
       
       
       case ArrayInClass( methodOrdinalAndPosition , classId : Int) =>
         {
           None;
         }
        
        
      }
    }
  
  
    def title4Yaml(position : Int) = None;
  
   
  def name4Yaml( modelFacade : ModelFacadeAll) =   "- variable: " + name(modelFacade);
  
  
  
  

  
}


//object RaceCondition
//{
//  
//  /*
//   
//   
// - variable: <package.class.name1>
//  reading :
//    thread: <threadname1r>
//    stack :
//      - <package/class.method>
//      - <package/class.method>
//      - <package/class.method>
//      - ......................
//  writing:
//    thread: <threadname1w>
//    stack :
//      - <package/class.method>
//      - <package/class.method>
//      - ......................
//- variable: <package.class.name2>
//  reading :
//  (und so weiter)
//
//Rq1: I used two spaces as indentation value
//Rq2: - ................. at end of stack means "stack truncated"
//       (due to the vmlens param "Max stack trace depth) if feasible.
//   * 
//   */
//  
//  
//  def writeAsYamlPart(operation : String ,meraceConditionElement : RaceConditionElement , viewTyp : ModelFacade , stream : PrintStream)
//  {
//    stream.println("  " + operation + ":");
//    stream.println("    thread: " + meraceConditionElement.getText(viewTyp));
//    stream.println("    stack:");
//    
//    meraceConditionElement.createReverseStackTrace(viewTyp.stackTraceGraph, 
//        
//     e =>
//       {
//         
//         stream.println("     - " + e.getText(viewTyp));
//         
//         
//       }
//    
//    
//    
//    );
//    
//    
//  }
//  
//  
//  
////  def writeAsYaml(races : HashSet[RaceCondition] , viewTyp : ModelFacade , stream : PrintStream)
////  {
////    for(r <- races )
////    {
////     // ToDo same as name Extractor below
////     // stream.println("- variable: " + r.getText(viewTyp));
////    
////      writeAsYamlPart( "reading" ,  r.reading , viewTyp , stream  );
////      writeAsYamlPart( "writing" ,  r.writing , viewTyp , stream  );
////      
////      
////
////      
////      
////      
////    }
////  }
//  
//  
//  
//  
//  
//  def create(meRaceCondition: RaceCondition, modelFacade: ModelFacade) =
//    {
//      val data = new ContainerData[RaceCondition]();
//
//      data.nameExtractor = (race, modelFacade) =>
//        {
//          Extractors.extractNameFromMemoryAccessOrdinal(race.reading.locationInClass  , modelFacade);
//        };
//
//      data.idExtractor =  Extractors.createIdFromNameAndParent;
//
//      data.childrenExtractor = (meRaceCondition : RaceCondition, parent : Element , modelFacade : ModelFacade ) =>
//  {
//    val list = new LinkedList[Element]();
//    
//    list.add(  RaceConditionElement.create( meRaceCondition.reading , parent, modelFacade) );
//    list.add(  RaceConditionElement.create( meRaceCondition.writing , parent, modelFacade) );
//      
//    list;
//    
//  }
//  
//  
//   data.commandExtractor = (r : RaceCondition  , e : Element , m : ModelFacade) =>
//     {
//       
//       val list = new LinkedList[Command]();
//       
//         m.fieldAndArrayFacade.addOpenDeclarationCommand(r.reading.locationInClass, list);
//         
//         
//         
//         if( r.issueValue ==  Issue.RACE_CONDITION_BUG)
//         {
//           list.add(   new DeleteRaceCondition(r)  );
//         }
//         
//         
//         
//       
//       list;
//     }
//  
//  
//   PropertyExtractorFunction.add(  PropertyId.ISSUE , (x : RaceCondition) => x.issue(modelFacade) , data.id2PropertyExtractor );
//   // Todo
//   PropertyExtractorFunction.add(  PropertyId.IS_STATIC , ( x : RaceCondition ) =>   modelFacade.fieldAndArrayFacade.isStatic(x.reading.locationInClass) , data.id2PropertyExtractor );
//  
// 
//  
//
//        ModelElementContainer.create(data, meRaceCondition, ElementType.RACE_CONDITION, None , modelFacade);
//
//    }
//  
//  
//  
//  
//  
//  
//  
//  
//  
//  
//  
//  
//  
//}
//
