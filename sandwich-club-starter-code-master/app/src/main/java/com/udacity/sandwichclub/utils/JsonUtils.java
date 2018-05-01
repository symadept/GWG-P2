package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.udacity.sandwichclub.utils.StringUtils.toStringList;

public class JsonUtils {
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        if(json == null) return null;
        Sandwich sandwich = null;
        JSONObject sandwichObject = null;

        try {
            //Throws exception
            sandwichObject = new JSONObject(json);

        } catch (final JSONException e) {
            System.out.println(e.getMessage());

        } finally {
            System.out.println("Finally method");
            if(sandwichObject == null) return sandwich;

            JSONObject nameObject = sandwichObject.optJSONObject(KEY_NAME);
            sandwich = new Sandwich();

            if(null != nameObject) {
                sandwich.setMainName(nameObject.optString(KEY_MAIN_NAME));

                List<String> alsoKnownAs = toStringList(nameObject.optJSONArray(KEY_ALSO_KNOWN_AS));
                sandwich.setAlsoKnownAs(alsoKnownAs);
            }

            sandwich.setPlaceOfOrigin(sandwichObject.optString(KEY_PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichObject.optString(KEY_DESCRIPTION));
            sandwich.setImage(sandwichObject.optString(KEY_IMAGE));

            List<String> ingredients = toStringList(sandwichObject.optJSONArray(KEY_INGREDIENTS));
            sandwich.setIngredients(ingredients);

        }

        return sandwich;
    }
}
