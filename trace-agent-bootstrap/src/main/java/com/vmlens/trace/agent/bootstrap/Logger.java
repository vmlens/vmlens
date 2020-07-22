package com.vmlens.trace.agent.bootstrap;

import java.io.PrintStream;

public class Logger {
	
	
	private static final PrintStream stream;
	
	static{
		
		String logFileName = System.getProperty("vmlensBootstrapLogFile");
		
		if(logFileName != null)
		{
			  PrintStream temp  = null;
			
			try{
				temp = new PrintStream(logFileName);
			
			}
			catch(Exception e)
			{
			
			}
			
			stream  = temp;
		}
		else
		{
			stream = null;
		}
		
		
		
	}

	
	
	public static void info(String message)
	{
		if( stream != null)
		{
			stream.println(message);
			stream.flush();
		}
	}
	
	
	
	
	public static void throwable(Throwable throwable)
	{
		if( stream != null)
		{
			throwable.printStackTrace(stream);
			stream.flush();
		}
	}
	
	
	
	
	
	
	
	
}
