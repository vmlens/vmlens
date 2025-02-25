package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.serialize.DeserializePackageOrClass;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.io.*;

public class LoadPreAnalyzed {

    private static final String PRE_ANALYZED_PATH = "/preanalyzed.vmlens";

    public TLinkedList<TLinkableWrapper<PackageOrClass>> load(String libPath) throws IOException {

        File resourceFile = new File(libPath + PRE_ANALYZED_PATH);
        if (resourceFile.exists()) {
            InputStream in = new FileInputStream(resourceFile);
            DataInputStream dataInputStream = new DataInputStream(in);
            TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed = new DeserializePackageOrClass().deserialize(dataInputStream);
            dataInputStream.close();
            return preAnalyzed;
        }
        return loadFromClasspath();
    }


    public TLinkedList<TLinkableWrapper<PackageOrClass>> loadFromClasspath() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream(PRE_ANALYZED_PATH);
        DataInputStream dataInputStream = new DataInputStream(stream);
        TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed
                = new DeserializePackageOrClass().deserialize(dataInputStream);
        dataInputStream.close();
        return preAnalyzed;
    }

}
