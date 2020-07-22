package com.anarsoft.trace.agent.scheduling;

public abstract interface DeSerializeSyncActionInfoCallback
{
  public abstract void classWithMethodsContainingSyncAction(String name);
 

  public abstract void synchronizedMethod(String methodName, String desc);
  
  
  public abstract void methodWithSyncblock(String methodName, String desc);
  
 
  public abstract void methodExit(String methodName, String desc);
  
  
  public abstract void volatileAccess(String owner, String fieldName,int typ);
}
