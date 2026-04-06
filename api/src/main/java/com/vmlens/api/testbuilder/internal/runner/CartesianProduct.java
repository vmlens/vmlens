package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.recording.RecordUpdateFactory;
import com.vmlens.nottraced.util.MixedRadixIterator;

import java.util.LinkedList;
import java.util.List;

public class CartesianProduct<CLASS_UNDER_TEST> {

    public List<List<RecordUpdateFactory<CLASS_UNDER_TEST>>> create(List<List<RecordUpdateFactory<CLASS_UNDER_TEST>>> listOfList) {
        int[] bases = new int[listOfList.size()];
        int index = 0;
         for(List<RecordUpdateFactory<CLASS_UNDER_TEST>> list : listOfList) {
             bases[index] = list.size();
             index++;
        }
        List<List<RecordUpdateFactory<CLASS_UNDER_TEST>>> result = new LinkedList<>();
        MixedRadixIterator iterator = new MixedRadixIterator(bases);
        while(iterator.hasNext()) {
            int[] current = iterator.next();
            List<RecordUpdateFactory<CLASS_UNDER_TEST>> permutation = new LinkedList<>();
            index = 0;
            for(List<RecordUpdateFactory<CLASS_UNDER_TEST>> list : listOfList) {
                permutation.add(listOfList.get(index).get(current[index]));
                index++;
            }
            result.add(permutation);
        }
        return result;
    }

}
