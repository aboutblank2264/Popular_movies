package com.aboutblank.popular_movies.utils;

import android.content.Context;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;
import com.squareup.picasso.Picasso;

public class ImageUtils {

    public static void loadImageInto(Context context, ImageView view, String movieUrl) {
        Picasso.with(context).load(getImageUrl(movieUrl)).into(view);
    }

    private static String getImageUrl(String movieUrl) {
        StringBuilder builder = new StringBuilder();
        builder.append(R.string.image_url_start);
        builder.append(R.string.default_image_size);
        builder.append(movieUrl);

        return builder.toString();
    }

}
