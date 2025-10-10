package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.Counter;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import java.util.Arrays;
import java.util.Map;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class DetectPattern {

    public static final int MIMiMUM_PATTERN_OCCURRENCE = 5;
    private static final long BASE = 257;
    private static final long MOD = 1_000_000_007L;

    private final InterleaveAction[] interleaveActions;

    public DetectPattern(InterleaveAction[] interleaveActions) {
        this.interleaveActions = interleaveActions;
    }

    public static boolean matches(InterleaveAction[] interleaveActions ,
                                  int firstStart,
                                  int secondStart,
                                  int length ) {
        for(int i = 0; i < length; i++ ) {
            if( ! interleaveActions[i+firstStart].equals(interleaveActions[i+secondStart])) {
                return false;
            }
        }
        return true;
    }

    public PatternKeyAndCount[] detect() {
        TLinkedList<TLinkableWrapper<PatternKeyAndCount>> result = new TLinkedList<>();
        THashMap<PatternKey,Counter> map =  createMap();
        for(Map.Entry<PatternKey,Counter>  entry : map.entrySet() ) {
            if(entry.getValue().count() > MIMiMUM_PATTERN_OCCURRENCE) {
                result.add(wrap(new PatternKeyAndCount(entry.getKey(),entry.getValue().count())));
            }
        }
        PatternKeyAndCount[] array = toArray(PatternKeyAndCount.class, result);
        Arrays.sort(array, new PatternKeyAndCountComparator());
        return array;
    }

    private THashMap<PatternKey, Counter> createMap() {
        int n = interleaveActions.length;
        long[] prefixHash = new long[n + 1];
        long[] pow = new long[n + 1];
        THashMap<PatternKey, Counter> result = new THashMap<>();

        // Precompute prefix hashes and powers of BASE
        pow[0] = 1;
        for (int i = 0; i < n; i++) {
            prefixHash[i + 1] = (prefixHash[i] * BASE + interleaveActions[i].hashCode()) % MOD;
            pow[i + 1] = (pow[i] * BASE) % MOD;
        }

        // Try all possible substring lengths
        for (int len = 1; len <= n / 2; len++) {
            for (int i = 0; i + 2 * len <= n; i++) {
                // Compute hash of substring [i, i+len)
                long hash1 = substringHash(prefixHash, pow, i, i + len);
                long hash2 = substringHash(prefixHash, pow, i + len, i + 2 * len);

                if (hash1 == hash2) {
                    if (matches(i, i + len, len)) {
                        // Count how many times pattern repeats consecutively
                        PatternKey key = asKey(hash1,i , len);
                        Counter counter = result.putIfAbsent(key, new Counter());
                        if(counter != null) {
                            counter.increment();
                        }
                    }
                }
            }
        }
        return result;
    }

    private PatternKey asKey(long hash, int start , int length) {
        return new PatternKey(interleaveActions, Long.hashCode(hash) , start , length );
    }

    private boolean matches(int firstStart,int secondStart, int length ) {
        return matches(interleaveActions, firstStart, secondStart, length);
    }

    private long substringHash(long[] prefixHash, long[] pow, int l, int r) {
        return  (prefixHash[r] - (prefixHash[l] * pow[r - l]) % MOD + MOD) % MOD;
    }

}
