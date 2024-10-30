package com.anarsoft.trace.agent.runtime;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class LoadClassArray {

    public byte[] load(String name) throws IOException {
        String fileName = "/" + name.replace('.', '/') + ".class";
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return byteArray;
    }

}
