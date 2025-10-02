package com.vmlens.api;

/**
 * 
 * The class AllInterleavings lets you test all thread interleavings for your test. Enclose your test in a while loop
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
 */
public class AllInterleavings implements AutoCloseable {

	private final static String ERROR_MESSAGE_PART_1 = "The vmlens java agent is not configured.";
	private final static String ERROR_MESSAGE_PART_2 = "See https://vmlens.com/docs/ for configuring the vmlens java agent.";
	private final boolean throwExceptionWhenNoAgent;

	public final int maximumIterations;
	public final int maximumAlternatingOrders;
	public final int synchronizationActionsLoopThreshold;
	public final int unsynchronizedOperationsLoopThreshold;

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
	public AllInterleavings(String name) {
		this(name,false);
	}

	public AllInterleavings(String name, boolean throwExceptionWhenNoAgent) {
		this(name,throwExceptionWhenNoAgent, 20 , 10 , 500 , 5000);
	}


	AllInterleavings(String name,
					 boolean throwExceptionWhenNoAgent,
					 int maximumIterations,
					 int maximumAlternatingOrders,
					 int synchronizationActionsLoopThreshold,
					 int unsynchronizedOperationsLoopThreshold) {
        this.name = name;
		this.throwExceptionWhenNoAgent = throwExceptionWhenNoAgent;
        this.maximumIterations = maximumIterations;
        this.maximumAlternatingOrders = maximumAlternatingOrders;
        this.synchronizationActionsLoopThreshold = synchronizationActionsLoopThreshold;
        this.unsynchronizedOperationsLoopThreshold = unsynchronizedOperationsLoopThreshold;
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
	 */
	public void close()  {
		close(this);
	}

	private boolean hasNext(Object object) {
		if(throwExceptionWhenNoAgent) {
			throw new RuntimeException(ERROR_MESSAGE_PART_1 + " " + ERROR_MESSAGE_PART_2);
		}
		System.err.println(ERROR_MESSAGE_PART_1);
		System.err.println(ERROR_MESSAGE_PART_2);
		return false;
	}

	private void close(Object obj) {
		
	}

}
