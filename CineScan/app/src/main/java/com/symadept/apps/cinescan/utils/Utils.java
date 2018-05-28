package com.symadept.apps.cinescan.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.symadept.apps.cinescan.R;

import static android.content.ContentValues.TAG;

public class Utils {
    public static Uri getImageUriForPath(Context context, String path) {
        Uri.Builder builder = new Uri.Builder();

        //If the imagePath already comes Prefixed with "/" then remove it since builder will add by default
        if(path.startsWith("/"))
            path = path.replaceFirst("/", "");

        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath(context.getResources().getString(R.string.image_url_width_factor))
                .appendEncodedPath(path);

        Uri imageUri = builder.build();
        Log.d(TAG, "getImageUriForPath: " + imageUri.toString());
        return imageUri;
    }
}
