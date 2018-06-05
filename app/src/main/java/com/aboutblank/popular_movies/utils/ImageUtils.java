package com.aboutblank.popular_movies.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;
import com.squareup.picasso.Picasso;

public class ImageUtils {

    private final static String YOUTUBE_IMAGE_STRING = "http://img.youtube.com/vi/[ID]/0.jpg";
    private final static String ID_TAG = "[ID]";

    public static void loadImageInto(Context context, ImageView view, String movieUrl) {
        Picasso.with(context)
                .load(getImageUrl(context.getResources(), movieUrl))
                .into(view);
    }

    public static void loadBackdropImageInto(Context context, ImageView view, String movieUrl) {
        Picasso.with(context)
                .load(getImageUrl(context.getResources(),
                        context.getResources().getString(R.string.default_backdrop_size),
                        movieUrl))
                .into(view);
    }

    public static void loadYoutubeIcon(@NonNull Context context, @NonNull ImageView imageView, @NonNull String videoId) {
        Picasso.with(context)
                .load(YOUTUBE_IMAGE_STRING.replace(ID_TAG, videoId))
                .into(imageView);
    }

    private static String getImageUrl(Resources resources, String movieUrl) {
        return getImageUrl(resources, resources.getString(R.string.default_image_size), movieUrl);
    }

    private static String getImageUrl(Resources resources, String imageSize, String movieUrl) {
        String string = resources.getString(R.string.image_url_start) +
                imageSize +
                movieUrl;

        return string;
    }
}
