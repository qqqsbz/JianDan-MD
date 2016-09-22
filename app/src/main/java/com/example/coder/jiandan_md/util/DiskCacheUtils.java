package com.example.coder.jiandan_md.util;

import com.nostra13.universalimageloader.cache.disc.DiskCache;

import java.io.File;

/**
 * Created by coder on 16/9/20.
 */
public class DiskCacheUtils {

    private DiskCacheUtils() {
    }

    public static File findInCache(String imageUri, DiskCache diskCache) {
        File image = diskCache.get(imageUri);
        return image != null && image.exists()?image:null;
    }

    public static boolean removeFromCache(String imageUri, DiskCache diskCache) {
        File image = diskCache.get(imageUri);
        return image != null && image.exists() && image.delete();
    }
}
