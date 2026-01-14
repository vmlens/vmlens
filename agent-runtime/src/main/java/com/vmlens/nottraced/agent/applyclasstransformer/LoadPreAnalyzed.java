package com.vmlens.nottraced.agent.applyclasstransformer;

import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.serialize.DeserializePackageOrClass;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import java.io.*;

public class LoadPreAnalyzed {

    private static final String PRE_ANALYZED_PATH = "/preanalyzed.vmlens";

    public TLinkedList<TLinkableWrapper<PackageOrClass>> load(String libPath) throws IOException {
        TLinkedList<TLinkableWrapper<PackageOrClass>> result = new TLinkedList<>();
        File resourceFile = new File(libPath + PRE_ANALYZED_PATH);
        if (resourceFile.exists()) {
            loadFromFile(resourceFile,result);
            return result;
        }

        File[] files = new File(libPath).listFiles();
        if (files != null) {
            for (File f : files) {
                if(f.getName().endsWith(".vmlens")) {
                    System.err.println("loaded:" + f.getName());
                    loadFromFile(f,result);
                }
            }
        }

        loadFromClasspath(result);
        return result;
    }


    private void loadFromFile(File resourceFile,TLinkedList<TLinkableWrapper<PackageOrClass>> result) throws IOException  {
        InputStream in = new FileInputStream(resourceFile);
        DataInputStream dataInputStream = new DataInputStream(in);
        new DeserializePackageOrClass().deserialize(dataInputStream,result);
        dataInputStream.close();

    }


    public void loadFromClasspath(TLinkedList<TLinkableWrapper<PackageOrClass>> result) throws IOException {
        InputStream stream = this.getClass().getResourceAsStream(PRE_ANALYZED_PATH);
        DataInputStream dataInputStream = new DataInputStream(stream);
        new DeserializePackageOrClass().deserialize(dataInputStream,result);
        dataInputStream.close();
    }

}
