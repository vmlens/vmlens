package com.vmlens.nottraced.agent;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.io.*;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ClassFilterFromFile {

    private final TLinkedList<TLinkableWrapper<String>> filterList;

    private ClassFilterFromFile(TLinkedList<TLinkableWrapper<String>> filterList) {
        this.filterList = filterList;
    }

    public static ClassFilterFromFile create(String fileName) {
        try {
            File filterFile = new File(fileName);
            if (!filterFile.exists()) {
                return ClassFilterFromFile.createEmpty();
            }
            Reader reader = new FileReader(fileName);
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return create(bufferedReader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClassFilterFromFile create(BufferedReader bufferedReader) throws IOException {
        TLinkedList<TLinkableWrapper<String>> filterList = new TLinkedList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            String trimmed = line.trim();
            if(! trimmed.equals("")) {
                filterList.add(wrap(trimmed));
            }
            line = bufferedReader.readLine();
        }
        return new ClassFilterFromFile(filterList);
    }

    public static ClassFilterFromFile createEmpty() {
        return new ClassFilterFromFile(new TLinkedList<>());
    }

    public boolean filter(String name) {
        for (TLinkableWrapper<String> element : filterList) {
            if (name.startsWith(element.element())) {
                return true;
            }
        }
        return false;
    }


}
