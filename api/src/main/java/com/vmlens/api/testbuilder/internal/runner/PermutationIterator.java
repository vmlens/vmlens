package com.vmlens.api.testbuilder.internal.runner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * create all iterations for a list of elements
 */

public class PermutationIterator<E> extends AbstractIterator<List<E>>  {

    final List<E> list;
    final int[] c;
    final int[] o;
    int j;

    PermutationIterator(List<E> list) {
        this.list = new ArrayList<>(list);
        int n = list.size();
        c = new int[n];
        o = new int[n];
        Arrays.fill(c, 0);
        Arrays.fill(o, 1);
        j = Integer.MAX_VALUE;
    }

    @Override
    protected  List<E> computeNext() {
        if (j <= 0) {
            return endOfData();
        }
        List<E> next = new ArrayList<>(list);
        calculateNextPermutation();
        return next;
    }

    void calculateNextPermutation() {
        j = list.size() - 1;
        int s = 0;

        // Handle the special case of an empty list. Skip the calculation of the
        // next permutation.
        if (j == -1) {
            return;
        }

        while (true) {
            int q = c[j] + o[j];
            if (q < 0) {
                switchDirection();
                continue;
            }
            if (q == j + 1) {
                if (j == 0) {
                    break;
                }
                s++;
                switchDirection();
                continue;
            }

            Collections.swap(list, j - c[j] + s, j - q + s);
            c[j] = q;
            break;
        }
    }

    void switchDirection() {
        o[j] = -o[j];
        j--;
    }

}
