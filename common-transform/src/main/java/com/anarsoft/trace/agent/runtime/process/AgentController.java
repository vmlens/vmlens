package com.anarsoft.trace.agent.runtime.process;


import java.util.zip.Adler32;
import java.util.zip.Checksum;
import java.io.*;
import java.nio.ByteBuffer;

/**
 * 
 * wir schreiben (überschreiben) in den agentstate den aktuellen Zusatnd + UUID 
 * + checksum + zeitstempel
 * 
 * im plugin speicern wir das letzte prozessierte element
 * beim hochfahren speichern wir das damit das nicht zweimal prozessiert wird
 * 
 * Commands machen wir als Files (start/stop)
 * 
 * 
 * 
 * 
 * @author thomas
 *
 */


public class AgentController  {
	
     public static final int STATE_WAITING = 1;
     public static final int STATE_RUNNING = 2;
     public static final int STATE_STOPPED = 3;
	
	
	
	
	private final File agentStateFile;
	private final File agentStateFileCopy;
    private final long startTimestamp;
    private final Checksum checksumGenerator;
	
    
    
    
    
    private synchronized  void writeCurrentState(int state , int slidingWindowId) throws Exception
	{
		final int arraySize = 2*8 + 2 * 4;
		
		ByteBuffer buffer = ByteBuffer.allocate(arraySize);
		
		buffer.putLong(startTimestamp );
		buffer.putLong(System.currentTimeMillis());
		buffer.putInt(state);
		buffer.putInt(slidingWindowId);
		
		
		checksumGenerator.reset();
		checksumGenerator.update(buffer.array(), 0, arraySize);
		
		writeToFile( buffer , arraySize ,  checksumGenerator.getValue() ,  agentStateFile);
		writeToFile( buffer , arraySize ,  checksumGenerator.getValue() ,  agentStateFileCopy);
	
		
		
	}
    
    
    private void writeToFile(  ByteBuffer buffer, int arraySize, long checkSum , File file) throws IOException
    {
    	DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
		
		stream.write(buffer.array(), 0, arraySize);
		stream.writeLong(checkSum);
		
		
		stream.close();	
    }
    
    
    
    public synchronized void writeRunningState( int slidingWindowId) throws Exception
    {
    	writeCurrentState(STATE_RUNNING,slidingWindowId);
    }
	
	
	
	
	public synchronized void writeStopState( int slidingWindowId) throws Exception 
	{
		writeCurrentState(STATE_STOPPED, slidingWindowId);
	}
	
	
	
	
	
	
	
	
	
	private AgentController(File agentStateFile, File agentStateFileCopy) {
		super();

		this.agentStateFile = agentStateFile;
		this.agentStateFileCopy = agentStateFileCopy;
		this.startTimestamp = System.currentTimeMillis();
		this.checksumGenerator = new Adler32();

	}



	public static AgentController create(String eventDir)
	{
		AgentController agentController = new AgentController(new File(eventDir + "/agent.state" ), new File(eventDir + "/agentCopy.state"));
		return agentController;
	}
	
	

	


	
	

}
