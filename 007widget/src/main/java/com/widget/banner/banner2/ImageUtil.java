package com.widget.banner.banner2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.bumptech.glide.GenericTransitionOptions.withNoTransition;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ImageUtil {

    public static void loadImage(String url, @NonNull ImageView imageView,
                                 @DrawableRes int holderDrawable, @DrawableRes int errorDrawable) {
        loadImage(url, imageView, holderDrawable, errorDrawable, null);
    }


    public static void loadImage(String url, @NonNull ImageView imageView,
                                 @DrawableRes int holderDrawable, @DrawableRes int errorDrawable,
                                 RequestListener listener) {
        Context context = imageView.getContext();

        // 注意有占位图/错误图的时候不要用动画 否则导致第一次加载不出来
        Glide.with(context).load(url).apply(new RequestOptions()
                .placeholder(holderDrawable)
                .error(errorDrawable).dontAnimate())
                .listener(listener).into(imageView);
    }

    public static void loadImage(String url, final ImageView imageView, boolean webpEnable) {
        loadImage(url, imageView, GifDrawable.LOOP_FOREVER, webpEnable);
    }

    public static void loadImage(String url, final ImageView imageView, final int gifMaxLoopCount, boolean webpEnable) {
        loadImage(url, imageView, gifMaxLoopCount, true, webpEnable);
    }


    public static void loadImage(String url, final ImageView imageView, final int gifMaxLoopCount, boolean anim, boolean webpEnable) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Context context = imageView.getContext();

        if (gifMaxLoopCount != GifDrawable.LOOP_FOREVER) {
            Glide.with(context).load(url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
                                            boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                               DataSource dataSource, boolean isFirstResource) {

                    if (resource instanceof GifDrawable) {
                        ((GifDrawable) resource).setLoopCount(gifMaxLoopCount);
                    }
                    return false;
                }
            }).into(imageView);
        } else {
            if (anim) {
                Glide.with(context).load(url).transition(withCrossFade()).into(imageView);
            } else {
                Glide.with(context).load(url).transition(withNoTransition()).into(imageView);
            }
        }
    }

}
