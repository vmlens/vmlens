package com.vmlens.test.maven.plugin.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestJackson {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @Disabled
    public void testParseJsonFile() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testJackson")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        parseJson();
                    }
                };
                first.start();
                parseJson();
                first.join();
            }
        }
    }

    private void parseJson() {
        String json = "{\"name\":\"Alice\", \"age\":30}";
        try {
            Person person = mapper.readValue(json, Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Disabled
    public void testWithReader() throws InterruptedException {
        ObjectReader reader = mapper.readerFor(Person.class);
        try(AllInterleavings allInterleavings = new AllInterleavings("testJacksonWithReader")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        parseJsonWithReader(reader);
                    }
                };
                first.start();
                parseJsonWithReader(reader);
                first.join();
            }
        }
    }

    private void parseJsonWithReader(ObjectReader reader) {
        String json = "{\"name\":\"Alice\", \"age\":30}";
        try {
            Person person = reader.readValue(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
