package com.udacity.sandwichclub.utils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static List<String> toStringList(JSONArray array) {
        List<String> listdata = new ArrayList<>();
        if (array != null) {
            for (int i=0;i<array.length();i++){
                listdata.add(array.optString(i));
            }
        }
        return listdata;
    }
}
