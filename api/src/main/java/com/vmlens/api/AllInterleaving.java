package com.vmlens.api;

/**
 * 
 * The class AllInterleaving let you test all thread interleaving for your test. Enclose your test in a while loop
 * to iterate through all thread interleaving like in the following example:
 * 
 * <pre>{@code 
 * try (AllInterleavings allInterleavings = 
    new AllInterleavings("ConcurrencyTestUniqueId");) {
    while (allInterleavings.hasNext()) {
        firstId  = 0L;
        secondId = 0L;
        UniqueId uniqueId = new UniqueId();
        Thread first = new Thread(() -> {
            firstId = uniqueId.nextId();
        });
        first.start();
        secondId = uniqueId.nextId();
        first.join();
        assertTrue(firstId != secondId);
    }
  }
 * 
 * }</pre>
 *
 *
 */

public class AllInterleaving implements AutoCloseable {

	/**
     * The name shown in the report.
	 * 
	 */
	public final String name;
	
	

	/**
	 * Creates a new AllInterleaving instance.
	 * 
	 *
     * @param name The name shown in the report.
	 */
	public AllInterleaving(String name) {
		super();
		this.name = name;
	}


	/**
	 * Return true if there are still thread interleaving to be executed and select the next thread interleaving,
	 * otherwise returns false.
	 * 
	 * @return true if  there are still thread interleaving to be executed otherwise false.
	 */

	public  boolean hasNext() {
		return hasNext(this);
	}

	
	/**
	 * closes this instance
	 * 
	 */
	
	public void close()  {
		close(this);
	}
	
	
	
	private static boolean hasNext(Object object) {
		System.err.println("The vmlens java agent is not configured.");
		System.err.println("See http://vmlens/documentation for configuring the vmlens java agent.");
		return false;
	}


	private static void close(Object obj) {
		
	}
	
	
	
	
	
}
