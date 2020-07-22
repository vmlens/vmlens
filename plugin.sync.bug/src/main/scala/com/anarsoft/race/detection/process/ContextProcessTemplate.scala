package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.result._;
import com.anarsoft.race.detection.process.method.ContextMethodData



trait ContextProcessTemplate extends  ContextReadThreadNames 
   with ContextMethodData
   with ContextStackTraceGraphBuilder
   with ContextCreateStackTraceGraph
   with ContextAddStacktraceIdsAndMissingLink
   with ContextReadAgentLog
{
  
}