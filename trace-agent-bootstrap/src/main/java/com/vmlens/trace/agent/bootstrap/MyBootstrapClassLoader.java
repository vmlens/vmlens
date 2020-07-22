package com.vmlens.trace.agent.bootstrap;

public class MyBootstrapClassLoader extends ClassLoader {

	
	
	public MyBootstrapClassLoader() {
		super(null);
	}

	
	
	
	public  Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		
		return super.loadClass(name, resolve);
	}
	
	
	
	
	public  Class<?> loadClass(String name) throws ClassNotFoundException {


		return super.loadClass(name);
	}

}
