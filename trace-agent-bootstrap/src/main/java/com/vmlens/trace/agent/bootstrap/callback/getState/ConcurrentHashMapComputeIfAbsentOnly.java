package com.vmlens.trace.agent.bootstrap.callback.getState;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

/*
 * 		
 * 
 * 
 * 
     nur getOrCreate, kein remove kein update keine iteratoren
     
     
     
     State modell:
			null -> Filled
			null -> MovedNull
			

			
			
		current
		prevoius array
		
		wenn prevoius gefüllt resize im gange.
		
		resize prozess:
				prevous array auf current
				current neu erzeugen
				erstmal alle null auf resize in previuos
				previuos muss wert behalten offen ob das benötigt wird für existierende werte
	
		bei resize darauf achten dass keine werte doppelt oder nirgends vorhanden sind
		updaten immer Filled

		erstmal Filled -> Move dann null -> Filled in neuer map
		offen update unterstützen?

		für resize misses und maximale länge bei miss zählen
		
		
		resize:
			prevoius = current
			current neu erzeugen
			in previous null werte füllen
			übertragen
			
			resize unter syncblock
			als erstes prüfen ob sich länge geändert hat (resize lief schon)
			
		https://en.wikipedia.org/wiki/Open_addressing
		
		
 * 	WithoutRemove
 * 
 * 
 */

public class ConcurrentHashMapComputeIfAbsentOnly {

	
	/**
	 * The largest possible table capacity. This value must be exactly 1<<30 to stay
	 * within Java array allocation and indexing bounds for power of two table
	 * sizes, and is further required because the top two bits of 32bit hash fields
	 * are used for control purposes.
	 */
	
	private static final int MAXIMUM_CAPACITY = 1 << 30;
	private static final Object MOVED_NULL_KEY = new Object();
	private static final KeyValue MOVED_KEY_VALUE = new KeyValue(MOVED_NULL_KEY, null);
	private final Object LOCK = new Object();
//	private static final sun.misc.Unsafe U;
//	private static final long ABASE;
//	private static final int ASHIFT;
//
//	static {
//		try {
//			Constructor<sun.misc.Unsafe> unsafeConstructor = sun.misc.Unsafe.class.getDeclaredConstructor();
//			unsafeConstructor.setAccessible(true);
//			U = unsafeConstructor.newInstance();
//
//			Class<?> ak = KeyValue[].class;
//			ABASE = U.arrayBaseOffset(ak);
//			int scale = U.arrayIndexScale(ak);
//			if ((scale & (scale - 1)) != 0)
//				throw new Error("data type scale not a power of two");
//			ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
//		} catch (Exception e) {
//			throw new Error(e);
//		}
//	}

	private static final KeyValue tabAt(AtomicReferenceArray<KeyValue>  tab, int i) {
		return    tab.get(i);  //(KeyValue) U.getObjectVolatile(tab, ((long) i << ASHIFT) + ABASE);
	}

	private static final boolean casTabAt(AtomicReferenceArray<KeyValue>  tab, int i, KeyValue newValue) {
		// tab[i] = newValue; //U.compareAndSwapObject(tab, ((long) i << ASHIFT) + ABASE, null, newValue);
	     return tab.compareAndSet(i, null, newValue);
	}

	/**
	 * Returns a power of two table size for the given desired capacity. See Hackers
	 * Delight, sec 3.2
	 */
	private static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
	
	
	/*
	 * package visibility for tests
	 */
	
	volatile AtomicReferenceArray<KeyValue> currentArray;
	private volatile boolean resizeRunning;

	public ConcurrentHashMapComputeIfAbsentOnly(int size) {
		super();
		int mod2Size = tableSizeFor(size);
		currentArray = new AtomicReferenceArray<KeyValue>(mod2Size); 
	}

	private Object insertDuringResize(Object key, FunctionForJDK7 compute) {
		synchronized (LOCK) {
			while (resizeRunning) {
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return computeIfAbsent(key, compute);
	}
	

	
	

	private void resize(int checkedLength) {
		/*
		 * 
		 * check if current was already changed if not resize flag = true and all null
		 * fields sperren
		 * 
		 * neues array anlegen und rüberkopieren wenn abgeschlossen neu setzen resize
		 * flag neu setzen alle warten threads benachrichtigen
		 * 
		 * 
		 * 
		 */

		synchronized (LOCK) {

			if (currentArray.length() > checkedLength) {
				return;
			}

			resizeRunning = true;

			for (int i = 0; i < currentArray.length(); i++) {
				if (tabAt(currentArray, i) == null) {
					casTabAt(currentArray, i, MOVED_KEY_VALUE);
				}
			}

			AtomicReferenceArray<KeyValue> newArray = new AtomicReferenceArray<KeyValue>
			(2 * currentArray.length());

			for (int i = 0; i < currentArray.length(); i++) {
				KeyValue current = tabAt(currentArray, i);

				if (current != MOVED_KEY_VALUE) {
					int hashCode = current.key.hashCode();
					int index = (newArray.length() - 1) & hashCode;

					while (newArray.get(index) != null) {
						index++;
						
						if( index== newArray.length() )
						{
							index = 0;
						}
						
					}

					newArray.set(index, current);

				}

			}

			currentArray = newArray;
			resizeRunning = false;

			LOCK.notifyAll();

		}
	}

	public Object computeIfAbsent(Object key, FunctionForJDK7 compute) {
		AtomicReferenceArray<KeyValue> local = currentArray;
		int hashCode = key.hashCode();
		int index = (local.length() - 1) & hashCode;
		int start = index;

		KeyValue created = null;
		KeyValue current = tabAt(local, index);
		
		if (current != null) {
			if (current.key == key) {
				return current.value;
			} else if (current.key == MOVED_NULL_KEY) {
				return insertDuringResize(key, compute);
			}

		}

		while (true) {	
			if (current == null)  {
				if (created == null) {
					created = new KeyValue(key, compute.apply(key));
				}

				if (casTabAt(local, index, created)) {
					if ( ( (index - start) << 3 ) > local.length()) {
						resize(local.length());
					}
					return created.value;
				}

				current = tabAt(local, index);

				if (current.key == key) {
					return current.value;
				} else if (current.key == MOVED_NULL_KEY) {
					return insertDuringResize(key, compute);
				}

			}

			index++;
			
			if( index == currentArray.length() )
			{
				index = 0;
			}
			
			if( index == start )
			{
				resize(local.length());

				return computeIfAbsent(key, compute);
			}
			
			
			current = tabAt(local, index);
			if (current != null) {
				if (current.key.equals(key)) {
					return current.value;
				} else if (current.key == MOVED_NULL_KEY) {
					return insertDuringResize(key, compute);
				}

			}
			
			

		}

	

	}
	
	
	
	
	
	
//	
//	public Object get(Object key) {
//		KeyValue[] local = currentArray;
//		int hashCode = key.hashCode();
//		int index = (local.length - 1) & hashCode;
//		int start = index;
//
//		KeyValue created = null;
//		KeyValue current = tabAt(local, index);
//		
//		if (current != null) {
//			if (current.key.equals(key)) {
//				return current.value;
//			} else if (current.key == MOVED_NULL_KEY) {
//				return getDuringResize(key);
//			}
//
//		}
//
//		while (true) {	
//			if (current == null)  {
//				return null;
//			}
//
//			index++;
//			
//			if( index == currentArray.length )
//			{
//				index = 0;
//			}
//			
//			if( index == start )
//			{
//				return null;
//			}
//			
//			
//			current = tabAt(local, index);
//			if (current != null) {
//				if (current.key.equals(key)) {
//					return current.value;
//				} else if (current.key == MOVED_NULL_KEY) {
//					return getDuringResize(key);
//				}
//
//			}
//			
//			
//
//		}
//
//	
//
//	}
//	
//	
//	
//	
//	
	
	
	
	
	
	
	
	
	
	
}
