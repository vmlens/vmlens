package com.vmlens.test.maven.plugin.json;


import com.vmlens.api.AllInterleavings;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;

public class TestJsonB {

    private final Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void testParseJsonFile() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testJsonB")) {
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
        Person person = jsonb.fromJson(json, Person.class);
    }

}
