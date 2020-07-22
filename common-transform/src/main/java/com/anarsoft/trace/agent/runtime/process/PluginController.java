package com.anarsoft.trace.agent.runtime.process;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import java.util.zip.Checksum;



/**
 * 
 *   create:
 *   start aktuell laufender prozess?
 *   ansonsten letzten wert merken und nicht prozessieren
 *   
 * 
 *   Callback
 *      agentIsWaiting
 *      agentIsRunning
 *      prozessResult
 *      
 *      
 *   
 *   
 *   Commands:
 *   	start
 *      stop
 * 
 * 
 * 
 * @author thomas
 *
 */



public  class PluginController {

	
	private PluginControllerState state;
	private final String eventDir;
	private final PluginCallback pluginCallback;
    private static final Checksum checksumGenerator = new Adler32();
	
	
  
    
    
    
    
	public PluginController(String eventDir, PluginCallback pluginCallback) {
		super();
		this.state =  new PluginControllerStateIdle();
		this.eventDir = eventDir;
		this.pluginCallback = pluginCallback;
		
	}



	private static  AgentState loadCurrentStateInternal(File file ) throws Exception
	{
		final int arraySize = 2*8 +2 * 4;
		DataInputStream stream = new DataInputStream( new FileInputStream(file) ); 
		try{
		
		byte[] array = new byte[arraySize];
		
		stream.read(array);
		
		long checkSum = stream.readLong();
		
		checksumGenerator.reset();
		checksumGenerator.update(array, 0, arraySize);
		
		
		if(  checksumGenerator.getValue() !=  checkSum)
		{
			throw new RuntimeException();
		}
		
		ByteBuffer buffer = ByteBuffer.wrap(array);
		
		return new AgentState(buffer.getLong(), buffer.getLong() , buffer.getInt() , buffer.getInt() ) ;
		
		}
		finally{
			stream.close();
		}
		
	
		
		
		
	}
	
	
	
	
	
	
	
	public  static  AgentState loadCurrentState(String eventDir)
	{
		
		
		
		File file = new File(eventDir + "/agent.state");
		File fileCopy = new File(eventDir + "/agentCopy.state");
		
		if( ! file.exists() )
		{
			return null;
		}
		
		
		for( int i = 0 ; i < 10 ; i++)
		{
			try{
			
				return loadCurrentStateInternal(file);
			
		    }
		    catch(Throwable e)
		    {
			  // e.printStackTrace();
			   try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				
			}
		    }
			
			
			
			try{
				
				return loadCurrentStateInternal(fileCopy);
			
		    }
		    catch(Throwable e)
		    {
			  // e.printStackTrace();
			   try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				
			}
		    }
			
			
			
			
			
			
		}
		
		
		return null;
		
	}
	
	
	
	

	
	public static PluginController create(String eventDir,PluginCallback pluginCallback) {
	
		return new PluginController(  eventDir , pluginCallback);
		
		
	}
	


	
	
	
	
	public void execute() throws Exception
	{
    	AgentState agentState = loadCurrentState(eventDir);
    	   	
    	state = state.execute(agentState, pluginCallback);
    	
    	
	}
	
	
	

}
