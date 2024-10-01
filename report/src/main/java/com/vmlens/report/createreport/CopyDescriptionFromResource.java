package com.vmlens.report.createreport;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;

public class CopyDescriptionFromResource {

    public CopyDescription[] load() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream("/copy/copy.json"));
        CopyDescription[] array = new Gson().fromJson(inputStreamReader, CopyDescription[].class);
        inputStreamReader.close();
        return array;
    }


}
