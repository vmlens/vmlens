package com.vmlens.api.internal

import scala.collection.mutable.ArrayBuffer;
import  com.vmlens.api._;
import org.fusesource.scalate.ssp.ElseFragment
import scala.collection.mutable.HashSet
import com.vmlens.api.SingleIcon
import com.vmlens.api.Icon
import com.vmlens.api.CompositeIcon


object IconRepository {
  
  val TOP_LEFT = 0;

	/**
	 * Constant for the top right quadrant.
	 */
	val TOP_RIGHT = 1;

	/**
	 * Constant for the bottom left quadrant.
	 */
	val BOTTOM_LEFT = 2;

	/**
	 * Constant for the bottom right quadrant.
	 */
	val BOTTOM_RIGHT = 3;
	
	
	val iconList = new ArrayBuffer[Icon]();

	

  
  
 
  
  val EXPAND_ALL = singleIcon("icons/elcl16/expandall.gif");
  val COLLAPSE_ALL = singleIcon("icons/elcl16/collapseall.gif");

  val FILTER = singleIcon("icons/elcl16/filter_16.gif");

  val READ = singleIcon("icons/obj16/read_obj.png")
  val READ_WRITE = singleIcon("icons/obj16/readwrite_obj.png");
  val WRITE = singleIcon("icons/obj16/write_obj.png");
  
  val ATOMIC_ENTER = singleIcon("icons/obj16/atomic_enter.png");
  val ATOMIC_EXIT = singleIcon("icons/obj16/atomic_exit.png");
  
  val CALLBACK_ENTER = singleIcon("icons/obj16/callback_enter.png");
  val CALLBACK_EXIT = singleIcon("icons/obj16/callback_exit.png");
  
  val METHOD_ENTER = singleIcon("icons/obj16/method_enter.png");
  val METHOD_EXIT = singleIcon("icons/obj16/method_exit.png");
  
  
  
   val THREAD = singleIcon("icons/obj16/thread_obj.png");
  
  


  val MONITOR = singleIcon("icons/obj16/monitor_obj.png");
  val MONITOR_IN_THREAD = singleIcon("icons/obj16/thread_and_monitor_obj.png");

  //val LOCK = singleIcon("icons/obj16/eclipse_schloss.png");
 

 // val PRIVATE_METHOD = singleIcon("icons/obj16/methpri_obj.png");

  
  

    val ARRAY = singleIcon("icons/obj16/arraypartition_obj.png");
    
    val CLASS = singleIcon("icons/obj16/class_obj.png");
    
    val PACKAGE = singleIcon("icons/obj16/package_obj.png");
    
  
 // val PROTECTED_METHOD = singleIcon("icons/obj16/methpro_obj.png");

  //    val deadlock_overlay = "
  //    val error = "";
  //    val volatile = "";
  //    val warning = "";
  //    val feature = "";
  //val OVERLAY_DEADLOCK = singleIcon("icons/ovr16/deadlock_ovr.gif");
  val OVERLAY_ERROR = singleIcon("icons/ovr16/error_co.png");
  val OVERLAY_VOLATILE = singleIcon("icons/ovr16/volatile_co.png");
  val OVERLAY_WARNING = singleIcon("icons/ovr16/warning_co.png");
  val OVERLAY_SUCCESS = singleIcon("icons/ovr16/success_ovr.png");
//  val OVERLAY_CONSTRUCTOR = singleIcon("icons/ovr16/constr_ovr.png");
//  val OVERLAY_SYNCHRONIZED = singleIcon("icons/ovr16/synch_co.png");
  val OVERLAY_STATIC = singleIcon("icons/ovr16/static_co.png");
  //val OVERLAY_CONFLICT = singleIcon("icons/ovr16/confchg_ov.gif");
  val OVERLAY_MONITOR =  singleIcon("icons/ovr16/monitor_co.png");
  
  val WARNING = singleIcon("icons/obj16/warning.png"); // for html warnings
  
  
  val BREAKPOINT = singleIcon("icons/obj16/brkp_obj.png");
  
  
  val OVERLAY_EXIT = singleIcon("icons/ovr16/exit_ovr.png");
  val OVERLAY_ENTRY = singleIcon("icons/ovr16/entry_ovr.png");
  
  val METHOD = singleIcon("icons/obj16/jmeth_obj.png");
  
  
   val OVERLAY_OWNS_MONITOR = singleIcon("icons/ovr16/ownsmonitor_ovr.png");
//  val OVERLAY_EXIT_MONITOR = singleIcon("icons/ovr16/deadlock_ovr.png");
  
  
  
  
  val LOCK_ENTER = singleIcon("icons/obj16/lock_enter.png");
  val LOCK_EXIT =  singleIcon("icons/obj16/lock_exit.png");
  
  
  
  
  

  //  val PRIVATE_CONSTRUCTOR = compositeIcon( PRIVATE_METHOD ,  OVERLAY_CONSTRUCTOR , IDecoration.TOP_RIGHT  );
  //  val PUBLIC_CONSTRUCTOR = compositeIcon( PUBLIC_METHOD ,  OVERLAY_CONSTRUCTOR , IDecoration.TOP_RIGHT  );
  //  val PROTECTED_CONSTRUCTOR = compositeIcon( PROTECTED_METHOD ,  OVERLAY_CONSTRUCTOR , IDecoration.TOP_RIGHT  );

 // val MONITOR_WARNING = compositeIcon(MONITOR, OVERLAY_WARNING, IDecoration.BOTTOM_LEFT);
 // val MONITOR_IN_THREAD_WARNING = compositeIcon(MONITOR_IN_THREAD, OVERLAY_WARNING, IDecoration.BOTTOM_LEFT);
  //val MONITOR_CONFLICT = compositeIcon(MONITOR, OVERLAY_CONFLICT, BOTTOM_LEFT);

  
  val MONITOR_ENTER = compositeIcon(MONITOR, OVERLAY_OWNS_MONITOR, BOTTOM_RIGHT);
  val MONITOR_EXIT = compositeIcon(MONITOR, OVERLAY_EXIT, BOTTOM_RIGHT);
 
  val DEADLOCK = compositeIcon(MONITOR, OVERLAY_ERROR, BOTTOM_LEFT);

 
  
  
  val EXTERNALIZE = singleIcon("icons/elcl16/externalize.gif");
  val INTERNALIZE = singleIcon("icons/elcl16/internalize.gif");
  
  
//  val PATTERN_ERROR =  compositeIcon(READ_WRITE, OVERLAY_ERROR, BOTTOM_RIGHT);
//  val PATTERN_WARNING =  compositeIcon(READ_WRITE, OVERLAY_WARNING, BOTTOM_RIGHT);
//  val PATTERN_OK =  compositeIcon(READ_WRITE, OVERLAY_FEATURE, BOTTOM_RIGHT);
  
  val LAUNCH_TAB = singleIcon("icons/eview16/logo_start_view.png");
  
  
 // val START_IMG = singleIcon("icons/img/start.png");
  
  
  val PREVIOUS = singleIcon("icons/obj16/previous.png");
  val NEXT = singleIcon("icons/obj16/next.png");
  
  val PARALLEL = singleIcon("icons/obj16/parallel.png");
 
  val EMPTY = singleIcon("icons/obj16/empty.png");
  
  val EMPTY_TWO = singleIcon("icons/obj16/empty_two.png");
  
   
  val FIELD = singleIcon("icons/obj16/field_public_obj.png");
   
   
  val BACK = singleIcon("icons/elcl16/nav_backward.png");
  val FORWARD = singleIcon("icons/elcl16/nav_forward.png");
  val HOME = singleIcon("icons/elcl16/nav_home.png");
  
  
  val LOGO_RED =  singleIcon("icons/eview16/logo_red.png");
  
  
 val STACK_FRAME = singleIcon("icons/obj16/stackframe.png");
  
 
  val STATELESS = singleIcon("icons/obj16/stateless.png");
  val OWNER = singleIcon("icons/obj16/owner.png");
 
  
 //val NOT_STATELESS =  compositeIcon(STATELESS, OVERLAY_ERROR, BOTTOM_LEFT);
 val INCONSISTEN_OWNER =  compositeIcon(OWNER, OVERLAY_ERROR, BOTTOM_LEFT);
 
 val INTERLEAVE_OK = singleIcon("icons/obj16/testok.png"); 
 val INTERLEAVE_ERROR = singleIcon("icons/obj16/testerr.png"); 
 
 val INTERLEAVE =  singleIcon("icons/obj16/test.png"); 
 
 val INTERLEAVE_WARN =  compositeIcon(INTERLEAVE,OVERLAY_WARNING, BOTTOM_LEFT);
 
 
 singleIcon("icons/obj16/test.png"); 
 
  
  def singleIcon(path : String) =
  {
    val icon = new SingleIcon(path);
    iconList.append( icon );
    icon;
  }
  
  
  
  def compositeIcon(  baseIcon : Icon , overlayIcon : Icon, position : Int  ) =
  {
     val icon = new CompositeIcon(baseIcon,overlayIcon,position);
    iconList.append( icon );
    icon;
  }
  
  
  
  
  def foreachIcon( temp : Icon => Unit )
  {
    
     
     
      val alreadyVisited = new HashSet[String]
    
    val f = applyWhenNotCalled( alreadyVisited , temp ) (_);
    
   
      f(INTERLEAVE_OK);
      f(INTERLEAVE_ERROR);
      
     f(WARNING);
     f(PREVIOUS);
     f(NEXT);
     f(PARALLEL)
     
     f(EMPTY);
     f(EMPTY_TWO)
     
     
     f(ARRAY)
     f(PACKAGE)
     f(CLASS)
     
     f(LOGO_RED);
     
//     f(STATELESS);
//     f(OWNER);
     

     
     /*
      * als erstes müssen die overlay ins image registry geladen werden!
      * 
      */
     
     
      iconList.foreach (  x =>
       {
         
         if( ! x.isObj() )
         {
           f(x);
         }
         
         
       }
       );
     
     
    
     
    
     iconList.foreach (  x =>
       {
         
         if(  x.isObj() )
         {
           f(x);
         }
         
         
       }
       );
    
    // f(NOT_STATELESS)
     f(INCONSISTEN_OWNER) 
     
     
    executeOnEachIcon( f );
    
  }
  
  
  
   def generateMethodOrFieldImage(baseImage: Icon, overlay: Icon, position: Int,  f : Icon => Unit ) =
   {  
        val icon = new CompositeIcon(baseImage,overlay,position);
        
        f(icon)
        
        icon;
        
   }

   def applyWhenNotCalled( set : HashSet[String] ,  f : Icon => Unit )( icon : Icon ) 
   {
     if( ! set.contains( icon.getId() ) )
     {
       set.add( icon.getId() )
       f(icon)
       
     }
     
     
   }
   
   
  def executeOnEachIcon( f : Icon => Unit ) {

    
   f( getImageForFieldWithoutAccess(  false , false ) )
   f( getImageForFieldWithoutAccess(  true ,  false ) )
   f( getImageForFieldWithoutAccess(  false , true ) )
   f( getImageForFieldWithoutAccess(  true ,  true ) )
    
    for( i <- 1 to 5 )
    {
      
        f(  getImageForField(new  MemoryAccessType(i) , false, false , false)  );
        f(  getImageForField(new  MemoryAccessType(i) , true, false , false)  );
      
        
        f(  getImageForField(new  MemoryAccessType(i) , false, true , false)  );
        f(  getImageForField(new  MemoryAccessType(i) , true, true , false)  );
        
        
        f(  getImageForField(new  MemoryAccessType( i ) , false, false , true)  );
        f(  getImageForField(new  MemoryAccessType( i ) , true, false , true)  );
    }
    
    
    
 
   
   
   
  }
  
  
  
  
  
 def getImageForFieldWithoutAccess(isStatic : Boolean, isVolatile : Boolean) =
 {
    var id : Icon = FIELD;
       
            
      
        if(  isStatic )
        {
          id = generateMethodOrFieldImage( id , OVERLAY_STATIC, BOTTOM_RIGHT, x => {} );
        }

        if(  isVolatile )
        {
          id = generateMethodOrFieldImage( id ,  OVERLAY_VOLATILE , TOP_RIGHT, x => {} );
        }
        
        id;
 }
  
  
  
  
  
  
  
  
  
  
  
  def main(args : Array[String])
  {
  
  
    executeOnEachIcon( icon => println( icon.getId() ) );
    
    
     
    
  }
  
  
  
  
  

  
  def getIconForOperationAndHasRace(memoryAccessType : MemoryAccessType , hasRace : Boolean) =
  {
    val opIcon = getIconForOperation( memoryAccessType  );
    
    if(hasRace)
    {
      generateMethodOrFieldImage( opIcon , OVERLAY_ERROR, BOTTOM_LEFT, x => {} );
    }
    else
    {
      opIcon
    }
    
    
  }
  
  
  def getIconForOperation(memoryAccessType : MemoryAccessType) =
  {
  
    
     if (   memoryAccessType. isReadOnly()  )
        {
            READ
        }
        else if (    memoryAccessType.isWriteOnly()  )
        {
            WRITE
        } 
//        else if (    memoryAccessType.isAtomic()  )
//        {
//            ATOMIC
//        }
        else
        {
          READ_WRITE;
        }
    
    
    
    
  }
  
  
  
  

 
   
  
  
  
  
  def getImageForField(accessTypByte : MemoryAccessType, isStatic : Boolean, isVolatile : Boolean, isRace : Boolean) =
    {
      var id : Icon =getIconForOperation(accessTypByte);
       
            
      
        if(  isStatic )
        {
          id = generateMethodOrFieldImage( id , OVERLAY_STATIC, BOTTOM_RIGHT, x => {} );
        }

        if(  isVolatile )
        {
          id = generateMethodOrFieldImage( id ,  OVERLAY_VOLATILE , TOP_RIGHT, x => {} );
        }
        
        
        if( isRace )
        {
          
          id = generateMethodOrFieldImage(  getIconForOperation(   accessTypByte ) , OVERLAY_ERROR, BOTTOM_LEFT, x => {} );
            
            
          
          
        }
        
       
   
 

      id;

    }
  
  
  

  
     def imagePath(icon: Icon) = "img/" + icon.getName() + ".png"
  
  
  
  
  
  
  
}