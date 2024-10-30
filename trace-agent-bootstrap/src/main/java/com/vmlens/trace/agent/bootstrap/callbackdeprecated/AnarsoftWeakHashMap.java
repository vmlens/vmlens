package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;



public class AnarsoftWeakHashMap<V> {

	
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    /**
     * The number of key-value mappings contained in this weak hash map.
     */
    private int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    private int threshold;

    /**
     * The load factor for the hash table.
     */
    private final float loadFactor;

    /**
     * Reference queue for cleared WeakEntries
     */
    private final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

    
    
    
    @SuppressWarnings("unchecked")
    private Entry<V>[] newTable(int n) {
        return (Entry<V>[]) new Entry<?>[n];
    }

    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     */
 private Entry[] table;
    
    
    private static class Entry<V> extends WeakReference<Object>  {
        V value;
        final int hash;
        Entry<V>  next;

        /**
         * Creates new entry.
         */
        Entry(Object key, V value,
              ReferenceQueue<Object> queue,
              int hash, Entry<V> next) {
            super(key, queue);
            this.value = value;
            this.hash  = hash;
            this.next  = next;
        }

        @SuppressWarnings("unchecked")
        public Object getKey() {
            return (Object) AnarsoftWeakHashMap.unmaskNull(get());
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

       
    }
    
    public AnarsoftWeakHashMap() {    
    	table = newTable(DEFAULT_INITIAL_CAPACITY);      
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int)(DEFAULT_INITIAL_CAPACITY * loadFactor);
    }
	
	
    private static final Object NULL_KEY = new Object();

    /**
     * Use NULL_KEY for key if it is null.
     */
    private static Object maskNull(Object key) {
        return (key == null) ? NULL_KEY : key;
    }

    /**
     * Returns internal representation of null key back to caller as null.
     */
    static Object unmaskNull(Object key) {
        return (key == NULL_KEY) ? null : key;
    }
    
    
    /**
     * Checks for equality of non-null reference x and possibly-null y.  By
     * default uses Object.equals.
     */
    private static boolean eq(Object x, Object y) {
        return x == y;
    }

    /**
     * Retrieve object hash code and applies a supplemental hash function to the
     * result hash, which defends against poor quality hash functions.  This is
     * critical because HashMap uses power-of-two length hash tables, that
     * otherwise encounter collisions for hashCodes that do not differ
     * in lower bits.
     */
    final int hash(Object k) {
        int h =  System.identityHashCode( k );

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
	
    /*
    * Returns index for hash code h.
    */
   private static int indexFor(int h, int length) {
       return h & (length-1);
   }

   /**
    * Expunges stale entries from the table.
    */
   private void expungeStaleEntries() {
       for (Object x; (x = queue.poll()) != null; ) {
           synchronized (queue) {
               @SuppressWarnings("unchecked")
                   Entry<V> e = (Entry<V>) x;
               int i = indexFor(e.hash, table.length);

               Entry<V> prev = table[i];
               Entry<V> p = prev;
               while (p != null) {
                   Entry<V> next = p.next;
                   if (p == e) {
                       if (prev == e)
                           table[i] = next;
                       else
                           prev.next = next;
                       // Must not null out e.next;
                       // stale entries may be in use by a HashIterator
                       e.value = null; // Help GC
                       size--;
                       break;
                   }
                   prev = p;
                   p = next;
               }
           }
       }
   }
   
   
   public V get(Object key) {
       Object k = maskNull(key);
       int h = hash(k);
       Entry<V>[] tab = table;
       int index = indexFor(h, tab.length);
       Entry<V> e = tab[index];
       while (e != null) {
           if (e.hash == h && eq(k, e.get()))
               return e.value;
           e = e.next;
       }
       return null;
   }
	
	
   
   public V put(Object key, V value) {
       Object k = maskNull(key);
       int h = hash(k);
       Entry<V>[] tab = getTable();
       int i = indexFor(h, tab.length);

       for (Entry<V> e = tab[i]; e != null; e = e.next) {
           if (h == e.hash && eq(k, e.get())) {
               V oldValue = e.value;
               if (value != oldValue)
                   e.value = value;
               return oldValue;
           }
       }

      
       Entry<V> e = tab[i];
       tab[i] = new Entry<V>(k, value, queue, h, e);
       if (++size >= threshold)
           resize(tab.length * 2);
       return null;
   }
   
   void resize(int newCapacity) {
       Entry<V>[] oldTable = getTable();
       int oldCapacity = oldTable.length;
       if (oldCapacity == MAXIMUM_CAPACITY) {
           threshold = Integer.MAX_VALUE;
           return;
       }

       Entry<V>[] newTable = newTable(newCapacity);
       transfer(oldTable, newTable);
       table = newTable;

       /*
        * If ignoring null elements and processing ref queue caused massive
        * shrinkage, then restore old table.  This should be rare, but avoids
        * unbounded expansion of garbage-filled tables.
        */
       if (size >= threshold / 2) {
           threshold = (int)(newCapacity * loadFactor);
       } else {
           expungeStaleEntries();
           transfer(newTable, oldTable);
           table = oldTable;
       }
   }

   /** Transfers all entries from src to dest tables */
   private void transfer(Entry<V>[] src, Entry<V>[] dest) {
       for (int j = 0; j < src.length; ++j) {
           Entry<V> e = src[j];
           src[j] = null;
           while (e != null) {
               Entry<V> next = e.next;
               Object key = e.get();
               if (key == null) {
                   e.next = null;  // Help GC
                   e.value = null; //  "   "
                   size--;
               } else {
                   int i = indexFor(e.hash, dest.length);
                   e.next = dest[i];
                   dest[i] = e;
               }
               e = next;
           }
       }
   }
   
   private Entry<V>[] getTable() {
       expungeStaleEntries();
       return table;
   }
	
	
}
