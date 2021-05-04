package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import java.util.BitSet;

/**
 * 
 * next hasNext
 * 
 * 
 * 
 * calculate max index isSet(index)
 * 
 * @author thomas
 *
 */

public class PotentialOrderIndex {

	private PotentialOrderIndexState current;

	public PotentialOrderIndex(int length) {
		current = new PotentialOrderIndexState(length);
	}

	public boolean hasNext() {
		return current.hasNext();
	}

	public PotentialOrderIndexState next() {
		PotentialOrderIndexState temp = current;
		current = current.next();
		return temp;
	}

	public final static class PotentialOrderIndexState {
		private final long index;
		private final BitSet bitSet;
		private final long maxIndex;

		private static BitSet createBitSet(long index) {
			long[] array = new long[1];
			array[0] = index;
			return BitSet.valueOf(array);
		}

		public PotentialOrderIndexState(int length) {
			super();
			this.index = 0;
			this.maxIndex = (long) Math.pow(2, length);
			this.bitSet = new BitSet();
		}

		private PotentialOrderIndexState(long index, long maxIndex) {
			super();
			this.index = index;
			this.maxIndex = maxIndex;
			this.bitSet = createBitSet(index);
		}

		public boolean hasNext() {
			return index < maxIndex;
		}

		public PotentialOrderIndexState next() {
			return new PotentialOrderIndexState(index + 1, maxIndex);
		}

		public boolean isSet(int position) {
			return bitSet.get(position);
		}

	}

}
