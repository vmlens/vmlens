package com.vmlens.report.description;

import com.vmlens.report.container.ContainerForMethod;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContainerMapAdapterTest {

    @Test
    public void notFound() {
        // Given
        Map<Integer, ContainerForMethod> idToMethodContainer = new HashMap<>();
        ContainerMapAdapter<Integer, ContainerForMethod> containerMapAdapter = new ContainerMapAdapter<>(idToMethodContainer);

        // When
        String name = containerMapAdapter.getName(1);

        // Then
        assertThat(name, is("not found (1)"));
    }

    @Test
    public void emptyContainer() {
        // Given
        Map<Integer, ContainerForMethod> idToMethodContainer = new HashMap<>();
        idToMethodContainer.put(1, new ContainerForMethod());
        ContainerMapAdapter<Integer, ContainerForMethod> containerMapAdapter = new ContainerMapAdapter<>(idToMethodContainer);

        // When
        String name = containerMapAdapter.getName(1);

        // Then
        assertThat(name, is("not found (1)"));

    }


}
