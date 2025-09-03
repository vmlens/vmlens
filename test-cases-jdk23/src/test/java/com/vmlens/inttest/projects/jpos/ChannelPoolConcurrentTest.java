package com.vmlens.inttest.projects.jpos;


import com.vmlens.api.AllInterleavings;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.channel.ChannelPool;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class ChannelPoolConcurrentTest {

    @Test
    public void testAddChannel1() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("channelPoolConcurrentTest")) {
            while (allInterleavings.hasNext()) {
                ChannelPool channelPool = new ChannelPool();
                ISOChannel channel = mock(ISOChannel.class);
                channelPool.addChannel(channel);

                Thread first = new Thread() {
                    @Override
                    public void run() {
                        try {
                            channel.disconnect();
                        } catch (IOException  e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                first.start();
                try {
                    channel.send(new byte[0]);
                } catch (IOException | ISOException e) {
                    throw new RuntimeException(e);
                }
                first.join();
            }
        }
    }

}
