package com.vmlens.api

class MemoryAccessType(val operation : Int) {
  
  def isReadOnly()  = MemoryAccessType.isReadOnly(operation)
	def isWriteOnly() =  MemoryAccessType.isWriteOnly(operation)
	def isAtomic() = MemoryAccessType.isAtomic(operation);
	def hasReadAndWrite() = MemoryAccessType.containsWrite(operation) && MemoryAccessType.containsRead(operation)

  
  
  
}

object MemoryAccessType
{
  val IS_READ = 1;
	val IS_WRITE = 2;
	val IS_ATOMIC = 4;  
	val IS_READ_WRITE = 3;
	
	
	
	
	
	def isReadOnly(operation : Int) = operation == IS_READ;
	def isWriteOnly(operation : Int) = operation == IS_WRITE;
	def isAtomic(operation : Int) =  ( operation & IS_ATOMIC) == IS_ATOMIC ;;
	def containsWrite(operation : Int )    =   ( (operation & IS_WRITE) == IS_WRITE ) || ( ( operation & IS_ATOMIC) == IS_ATOMIC );
	def containsRead(operation : Int) =   ( (operation & IS_READ) == IS_READ );   
	
	
	def getName( operation : Int  ) =
	{
	    if(  operation > 4 )
	  {
	    "compare and swap"
	  }
	  else
	  operation match
	  {
	    case 1 => "read";
	    case 2 => "write";
	    case 3 => "read and write"
	    case 4 => "compare and swap"
	    
	    
	    
	  }
	  
	  
	  
	  
	}
	
	
	
	
		def getYaml( operation : Int  ) =
	{
	    if(  operation > 4 )
	  {
	    "compareAndSwap"
	  }
	  else
	  operation match
	  {
	    case 1 => "read";
	    case 2 => "write";
	    case 3 => "readAndWrite"
	    case 4 => "compareAndSwap"
	    
	    
	    
	  }
	  
	  
	  
	  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	def getDisplayName( hasRace : Boolean , operation : Int) =
	{
	  
	  if(hasRace)
	  {
	      "race condition"
	  }
	  else	  
	  if(  operation > 4 )
	  {
	    "compare and swap"
	  }
	  else
	  {
	    operation match
	  {
	    case 0 => "none";
	    case 1 => "read";
	    case 2 => "write";
	    case 3 => "read and write"
	    case 4 => "compare and swap"
	  }
	  }
	  
	  
	}
	
	
  
  
}