package com.vmlens.trace.agent.bootstrap;



import gnu.trove.map.hash.TObjectIntHashMap;


public class AtomicClassRepo {

	private static final TObjectIntHashMap<String> className2Id = new TObjectIntHashMap<String>();
	private static int maxId;

	public static synchronized int getId4AtomicClass(String className) {
		if (className2Id.contains(className)) {
			return className2Id.get(className);
		}

		int temp = maxId;

		maxId++;

		className2Id.put(className, temp);
	
		return temp;
	}

	public static synchronized int getIdOrMinusOne4AtomicClass(String className) {
		if (className2Id.contains(className)) {
			return className2Id.get(className);
		}

	
		return -1;
	}

}
