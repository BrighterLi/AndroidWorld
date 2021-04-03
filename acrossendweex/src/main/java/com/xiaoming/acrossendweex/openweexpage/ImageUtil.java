package com.xiaoming.acrossendweex.openweexpage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

public class ImageUtil {

    public static abstract class OnBitmapLoad {
        public abstract void onBitmapReady(Bitmap bitmap);

        public void onLoadFailed() {
        }
    }

    public interface OnDrawableLoad {
        void onDrawableReady(Drawable drawable);
    }

    public interface OnFileLoad {
        void onFileReady(File file);

        void onFileLoadFail();
    }

    public interface LoadListener {
        void onLoadFailed(@Nullable Drawable errorDrawable);

        void onResourceReady(Drawable resource);
    }



    //以Bitmap形式下载图片
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

    //以Drawable形式下载图片
    public static void getDrawable(Context context, String url, final OnDrawableLoad onDrawableLoad) {
        if (onDrawableLoad == null || isActivityDestroyed(context)) {
            return;
        }
        Glide.with(context).asDrawable().load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (resource != null) {
                    onDrawableLoad.onDrawableReady(resource);
                }
            }
        });
    }

    //以File形式下载图片
    public static void getFile(Context context, String url, final OnFileLoad onFileLoad) {
        if (onFileLoad == null || isActivityDestroyed(context)) {
            return;
        }
        Glide.with(context)
                .downloadOnly()
                .load(url)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        if (resource != null && resource.isFile() && resource.exists()) {
                            onFileLoad.onFileReady(resource);
                        } else {
                            onFileLoad.onFileLoadFail();
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        onFileLoad.onFileLoadFail();
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

    public static void loadImage(String url, ImageView imageView, final LoadListener loadListener) {
        Context context = imageView.getContext();
        if (isActivityDestroyed(context)) {
            return;
        }
        Glide.with(context)
                .load(url)
                //.optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new CenterCrop()))
                .into(new DrawableImageViewTarget(imageView) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (loadListener != null) {
                            loadListener.onLoadFailed(errorDrawable);
                        }
                    }

                    @Override
                    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        if (loadListener != null) {
                            loadListener.onResourceReady(resource);
                        }
                    }
                });
    }

}
