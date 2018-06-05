package com.aboutblank.popular_movies.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.data.ImageCache;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class ImageUtils {

    private final static String YOUTUBE_IMAGE_STRING = "http://img.youtube.com/vi/[ID]/0.jpg";
    private final static String ID_TAG = "[ID]";

    public static void loadImageInto(Context context, ImageView view, String movieUrl) {
        RequestBuilder<Bitmap> requestBuilder = Glide.with(context)
                .asBitmap()
                .load(getImageUrl(context.getResources(), movieUrl));
        getAndCacheImage(requestBuilder, view, movieUrl);
    }

    public static void loadBackdropImageInto(Context context, ImageView view, String movieUrl) {
        RequestBuilder<Bitmap> requestBuilder = Glide.with(context)
                .asBitmap()
                .load(getImageUrl(context.getResources(),
                        context.getResources().getString(R.string.default_backdrop_size),
                        movieUrl));

        getAndCacheImage(requestBuilder, view, movieUrl);
    }

    public static void loadYoutubeIcon(@NonNull Context context, @NonNull ImageView view, @NonNull String videoId) {
        RequestBuilder<Bitmap> requestBuilder = Glide.with(context)
                .asBitmap()
                .load(YOUTUBE_IMAGE_STRING.replace(ID_TAG, videoId));
        getAndCacheImage(requestBuilder, view, videoId);
    }

    private static void getAndCacheImage(@NonNull RequestBuilder<Bitmap> requestBuilder, @NonNull final ImageView view, @NonNull final String key) {
        final ImageCache imageCache = ImageCache.getInstance();

        final Bitmap[] imageBitMap = {imageCache.getBitMap(key)};
        if (imageBitMap[0] == null) {
            requestBuilder.into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    imageBitMap[0] = resource;

                    imageCache.addBitMap(key, imageBitMap[0]);

                    view.setImageBitmap(imageBitMap[0]);
                }
            });
        } else {
            view.setImageBitmap(imageBitMap[0]);
        }

    }

    private static String getImageUrl(Resources resources, String movieUrl) {
        return getImageUrl(resources, resources.getString(R.string.default_image_size), movieUrl);
    }

    private static String getImageUrl(Resources resources, String imageSize, String movieUrl) {
        return resources.getString(R.string.image_url_start) +
                imageSize +
                movieUrl;
    }
}
