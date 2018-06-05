package com.aboutblank.popular_movies.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.LruCache;

/**
 * LRU reference: https://developer.android.com/topic/performance/graphics/cache-bitmap
 */
public class ImageCache {
    private static ImageCache instance;

    private LruCache<String, Bitmap> cache;

    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private final int cacheSize = maxMemory / 8;

    private ImageCache() {
        cache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap){
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static ImageCache getInstance() {
        if(instance == null) {
            instance = new ImageCache();
        }

        return instance;
    }

    public void addBitMap(@NonNull String key, @NonNull Bitmap bitmap) {
        cache.put(key, bitmap);
    }

    public Bitmap getBitMap(@NonNull String key) {
        return cache.get(key);
    }
}
