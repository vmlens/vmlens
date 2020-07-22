package com.anarsoft.integration



trait  TextRepository
{
  val BACK = "Back to the previous page";
   val FORWARD = "Forward to the next page";
   val HOME = "Home";
  
  
  
  
  
   val TITLE_PROGRESS_MONITOR_DIALOG ="vmlens processing the execution trace"
  
   val TITLE_START_VIEW_LICENCE_SECTION ="License";
  
  
   val START_VIEW_HELP_LINK = "Read more at http://vmlens.com/help/configureAgent.html";
   
   
   val START_VIEW_START_WITH_RUN = """or if you run a java application or junit test inside eclipse use the traced with vmlens shortcut:""";
   
 
  val DO_NOT_TRACE_IN = "Do not trace";
  
  val EXCLUDE_FROM_TRACE = "Exclude from tracing:";
  
  val ONLY_TRACE_IN                     = "Only trace in:"

  
  
  val SHORTEN_STACKTRACE_AFTER_N_EVENTS =  "Number of calls"
  val SHORTENED_STACK_TRACE_DEPTH        = "Shorten to maximum depth"
  
  val PERSPECTIVE_SWITCH = "Switch to the vmlens perspective";
  
  val SHOW_RACE_CALCUALTION_PROGRESS = "Show the vmlens analyze progress bar" 
   
  
  val SHOW_RACE_CALCUALTION_PROGRESS_TOGGLE = "Do not show this dialog again, always run in background.";
  
   
  val TITLE_SHORTEN_STACK_TRACE = "Shorten the stack trace after n method calls";
  
  val MANUAL_START_TITLE = "Manually start tracing";
  val MANUAL_START = "Do you want to start vmlens agent tracing now?"
  
  
  val PREFERENCE_DESCRIPTION = "configure vmlens";
  
  
  val OPEN_PERSPECTIVE_TITLE = "Confirm Perspective Switch";
  val OPEN_PERSPECTIVE_TEXT = "This launch is associated with the vmlens perspective. Do you want to open the vmlens perspective now?";
  
  
  val SOURCE_NOT_FOUND = "Source not found for ";
  
  val EXPAND_ALL = "Expand All"; 
  val COLLAPSE_ALL = "Collapse all";
  
  
  val EXPORT_RACE_CONDITIONS = "Export";
  val EXPORT_AGENT = "Save Agent Files";
  val IMPORT_TRACE_FILES = "Import";
  
  
  val TOGGLE_BREAKPOINT = "Toggle Waitpoint";
  val OPEN_CALL_HIERACHY = "Open Call Hierachy";
  
  val OPEN_DECLARATION = "Open Declaration";
  val SHOW_HELP = "Show Help";
  val SHOW_STACK_TRACE = "Show Stack Trace";
  val FILTER_FIELDS = "Filter Fields";



  val EMPTY_STACK_TRACE_TEXT = """<form><p>Select a field in the <a href="racecondition">Race Condition View</a></p></form>""";
  val EMPTY_STACK_TRACE_TITLE = "Stack Trace View";
  val EMPTY_STACK_TRACE_THREAD = "";
  
  

  
 
  
  
  def WRONG_CLASS_NAME(token : String) =
  {
    
    s"$token is not a valid start of a java class";
    
  }
  
  
  def WRONG_STACK_TRACE_DEPTH(token : String) =
  {
    "The stack trace depth must be empty or a posititive integer";
  }
  
  
}



object TextRepository extends TextRepository {
  
   
  

  
}