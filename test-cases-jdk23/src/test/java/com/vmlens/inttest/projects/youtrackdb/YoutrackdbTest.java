package com.vmlens.inttest.projects.youtrackdb;

import com.jetbrains.youtrackdb.api.DatabaseType;
import com.jetbrains.youtrackdb.api.YourTracks;
import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.junit.jupiter.api.Test;

public class YoutrackdbTest {

    @Test
    public void addVertex() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withMaximumAlternatingOrders(5)
                .withMaximumIterations(1)
                .build("youtrackdbTest")) {
            try (var ytdb = YourTracks.instance("./target/data")) {
                ytdb.create("tg", DatabaseType.MEMORY, "superuser", "adminpwd", "admin");
                //and then open the YTDBGraph instance
                var newGraph = ytdb.openGraph("tg", "superuser", "adminpwd");
            while (allInterleavings.hasNext()) {
                 newGraph.computeInTx( tx ->  tx.addV("person").property("name", "marko").property("age", 29).next() );
                    Thread first = new Thread(() -> newGraph.addVertex("person" , "test"));
                    first.start();
                     newGraph.addVertex("person" , "test");
                    first.join();
                }
            }
        }
    }



}
