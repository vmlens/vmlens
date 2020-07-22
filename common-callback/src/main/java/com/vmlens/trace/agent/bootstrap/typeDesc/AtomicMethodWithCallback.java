package com.vmlens.trace.agent.bootstrap.typeDesc;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;


public class AtomicMethodWithCallback {

	
	
	private final String methodName;
	private final String desc;
	
	
	private final String[] callbackClassName;
//	private final String callbackMethodName;
//	private final String callbackDesc;
	
	
	public AtomicMethodWithCallback(DataInputStream stream) throws IOException	{
					
					this.methodName =  stream.readUTF();
					this.desc =  stream.readUTF();
					
					int length = stream.readInt();
					
					callbackClassName = new String[length];
							
					for( int i = 0; i <length ; i++ )
					{
						callbackClassName[i] = 		 stream.readUTF();
					}
					
				
//					this.callbackMethodName =  stream.readUTF();
//					this.callbackDesc =  stream.readUTF();
			}
	
	
	
	public AtomicMethodWithCallback(String methodName, String desc, String[] callbackClassName) {
		super();
	
		this.methodName = methodName;
		this.desc = desc;
		this.callbackClassName = callbackClassName;
//		this.callbackMethodName = callbackMethodName;
//		this.callbackDesc = callbackdesc;
	}
	
	
	
	public void serialize(DataOutputStream stream) throws IOException {
		
		
		
		stream.writeUTF(methodName);
		stream.writeUTF(desc);
		
		
		stream.writeInt(callbackClassName.length);
		
		for( String n : callbackClassName )
		{
			stream.writeUTF(n);
		}
		
		
//		stream.writeUTF(callbackMethodName);
//		stream.writeUTF(callbackDesc);
		
		
	}






	



	@Override
	public String toString() {
		return "AtomicMethodWithCallback [methodName=" + methodName + ", desc=" + desc + ", callbackClassName="
				+ Arrays.toString(callbackClassName) + "]";
	}



	public String getMethodName() {
		return methodName;
	}



	public String getDesc() {
		return desc;
	}



	public String[] getCallbackClassName() {
		return callbackClassName;
	}



	
	
	
	
}
