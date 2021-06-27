package com.xiaoming.a008project.fenle.tool;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.Nullable;


public class ImageUtil {

    public static void getBitmap(Context context, String url, final OnBitmapLoad onBitmapLoad) {
        if (onBitmapLoad == null || isActivityDestroyed(context)) {
            return;
        }
        Glide.with(context).asBitmap().load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        if (resource != null && !resource.isRecycled()) {
                            onBitmapLoad.onBitmapReady(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        onBitmapLoad.onLoadFailed();
                    }
                });
    }

    public static boolean isActivityDestroyed(Context context) {
        if (context == null) {
            return true;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return true;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed()) {
                    return true;
                }
            }
        }
        return false;
    }


    public static abstract class OnBitmapLoad {
        public abstract void onBitmapReady(Bitmap bitmap);

        public void onLoadFailed() {
        }
    }
}
