package com.vmlens.trace.agent.bootstrap.event;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import gnu.trove.list.linked.TLinkedList;

public class StreamWrapperWithoutSlidingWindow  extends AbstractStreamWrapper  {
	
	

	
	protected DataOutputStream stream;
	protected final String eventDir;
	protected final String name;
	protected final int writerNumber;
	
	
	public DataOutputStream getStream() throws Exception
	{
		if( stream == null)
		{
			stream =  new DataOutputStream(new BufferedOutputStream(new FileOutputStream(eventDir + "/" +  name + "_" +writerNumber + ".vmlens" )));
			
			//streamStatistic  =  new DataOutputStream(new  BufferedOutputStream(new FileOutputStream(eventDir + "/" +  name + "Statistic_" +writerNumber + ".vmlens" )));
		}
		
		return stream;
	}
	
	

	public void close() throws Exception
	{
		if( stream != null)
		{
			 stream.close();
			
			// streamStatistic.close();
		}
	}


	
	public void flush()
	{
		
	}
	







	public StreamWrapperWithoutSlidingWindow(
			String eventDir, String name, int writerNumber,TLinkedList<AbstractStreamWrapper> list) {
		super(list);
		
		this.eventDir = eventDir;
		this.name = name;
		this.writerNumber = writerNumber;
	}



	
	
	
	
	


	
	
	
	
	

}