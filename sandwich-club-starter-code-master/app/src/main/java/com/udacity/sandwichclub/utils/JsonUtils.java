package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if(json == null) return null;
        Sandwich sandwich = null;
        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject nameObject = sandwichObject.getJSONObject("name");
            sandwich = new Sandwich();

            if(null != nameObject) {
                sandwich.setMainName(nameObject.getString("mainName"));

                List<String> alsoKnownAs = toStringList(nameObject.getJSONArray("alsoKnownAs"));
                sandwich.setAlsoKnownAs(alsoKnownAs);
            }

            sandwich.setPlaceOfOrigin(sandwichObject.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichObject.getString("description"));
            sandwich.setImage(sandwichObject.getString("image"));

            List<String> ingredients = toStringList(sandwichObject.getJSONArray("ingredients"));
            sandwich.setIngredients(ingredients);

        } catch (final JSONException e) {
            System.out.println(e.getMessage());
        }

        return sandwich;
    }

    private static List<String> toStringList(JSONArray array) throws JSONException{
        List<String> listdata = new ArrayList<String>();
        if (array != null) {
            for (int i=0;i<array.length();i++){
                listdata.add(array.getString(i));
            }
        }
        return listdata;
    }
}
