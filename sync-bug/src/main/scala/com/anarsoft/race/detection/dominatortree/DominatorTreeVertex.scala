package com.anarsoft.race.detection.dominatortree

/**
 * either RootVertex, StackTraceVertex, VolatileFieldVertex
 * 
 * Multiple Volatile Field Events needs to be combined in one VolatileFieldVertex
 * 
 * We need to filter Volatile Field so that only fields which are added
 * multiple times are shown
 * 
 * We need also remove methods which do not contain a field access
 * 
 * 
 */
trait DominatorTreeVertex {

}
