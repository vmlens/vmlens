package com.anarsoft.trace.agent.scheduling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SerializeSyncActionInfo
{
  public void deserialize(DeSerializeSyncActionInfoCallback callback, DataInputStream in)
    throws IOException
  {
    int length = in.readInt();
    for (int i = 0; i < length; i++)
    {
      String name = in.readUTF();
      
      callback.classWithMethodsContainingSyncAction(name);
      
      
      int methodArrayLength = in.readInt();
      int methodsWithSyncblockLength =  in.readInt();
      int methodExitWithMonitorLength = in.readInt();
      
      for (int j = 0; j < methodArrayLength; j++)
      {
        String methodName = in.readUTF();
        String desc = in.readUTF();
        
        callback.synchronizedMethod(methodName, desc);
        

      }
     
     
      
      
      for (int j = 0; j < methodsWithSyncblockLength; j++)
      {
        String methodName = in.readUTF();
        String desc = in.readUTF();
        
        callback.methodWithSyncblock(methodName, desc);
        

      }
      
      
      for (int j = 0; j < methodExitWithMonitorLength; j++)
      {
        String methodName = in.readUTF();
        String desc = in.readUTF();
        
        callback.methodExit(methodName, desc);
        

      }
      
    
    }
    
    
    int volatileAccessArrayLength = in.readInt();
    for (int k = 0; k < volatileAccessArrayLength; k++)
    {
      String owner = in.readUTF();
      String fieldName = in.readUTF();
      int typ = in.readInt();
      
      callback.volatileAccess(owner, fieldName,typ);
    }
    
    
    
    
    
  }
  
  public void serializeCount(int length, DataOutputStream out)
    throws IOException
  {
    out.writeInt(length);
  }
  
  public void serializeClassWithMethodsContainingSyncAction(String name, int methodArrayLength, int methodsWithSyncblockLength , int methodExitWithMonitorLength, DataOutputStream out)
    throws IOException
  {
    out.writeUTF(name);
    out.writeInt(methodArrayLength);
    out.writeInt(methodsWithSyncblockLength);
    out.writeInt(methodExitWithMonitorLength);
    
  }
  
  public void serializeMethod(String name, String desc , DataOutputStream out)
    throws IOException
  {
    out.writeUTF(name);
    out.writeUTF(desc);

  }
  
  public void serializeVolatileAccess(String owner, String name, int typ , DataOutputStream out)
    throws IOException
  {
    out.writeUTF(owner);
    out.writeUTF(name);
    out.writeInt(typ);
  }
}
