package com.widget.banner.banner2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.widget.aaaview.custom_view.gif.GifDemoActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        if (isActivityDestroyed(context)) {
            return;
        }

        if (gifMaxLoopCount != GifDrawable.LOOP_FOREVER) {
            Glide.with(context).load(url).skipMemoryCache(true).listener(new RequestListener<Drawable>() {
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
                Glide.with(context).load(url).skipMemoryCache(true).transition(withCrossFade()).into(imageView);
            } else {
                Glide.with(context).load(url).skipMemoryCache(true).transition(withNoTransition()).into(imageView);
            }
        }
    }


    public static void getBitmap(Context context, String url, final OnBitmapLoad onBitmapLoad) {
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


    public static abstract class OnBitmapLoad {
        public abstract void onBitmapReady(Bitmap bitmap);

        public void onLoadFailed() {
        }
    }


    public static void getFile(Context context, String url, final OnFileLoad onFileLoad) {
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

    public static void loadImage(String url, final ImageView imageView) {
        loadImage(url, imageView, false);
    }


    public interface OnFileLoad {
        void onFileReady(File file);

        void onFileLoadFail();
    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete();

        void gifPlayError();
    }


    public static void loadOneTimeGif(String url, final ImageView imageView, final GifListener gifListener) {
        RequestBuilder<GifDrawable> gifDrawableRequestBuilder = Glide.with(imageView.getContext()).asGif().load(url);
        loadOneTimeGif(gifDrawableRequestBuilder, imageView, gifListener);
    }


    private static void loadOneTimeGif(RequestBuilder<GifDrawable> gifDrawableRequestBuilder, final ImageView imageView, final GifListener gifListener) {
        Log.i("ImageUtil", "bright8#Banner#loadOneTimeGif");
        if (gifDrawableRequestBuilder == null) {
            return;
        }
        gifDrawableRequestBuilder.listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                Log.i("ImageUtil", "bright8#Banner#onLoadFailed#e:" +e.toString());
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                //imageView.setVisibility(View.VISIBLE);
                //imageView.setLayoutParams(layoutParams);
                try {
                    Log.i("ImageUtil", "bright8#Banner#onResourceReady");
                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
                    gifStateField.setAccessible(true);
                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                    gifFrameLoaderField.setAccessible(true);
                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                    gifDecoderField.setAccessible(true);
                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                    getDelayMethod.setAccessible(true);
                    //设置只播放一次
                    resource.setLoopCount(1);
                    //获得总帧数
                    int count = resource.getFrameCount();
                    int delay = 0;
                    for (int i = 0; i < count; i++) {
                        //计算每一帧所需要的时间进行累加
                        delay += (int) getDelayMethod.invoke(gifDecoder, i);
                    }
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (gifListener != null) {
                                gifListener.gifPlayComplete();
                            }
                        }
                    }, delay);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (gifListener != null) {
                        gifListener.gifPlayError();
                    }
                }
                return false;
            }
        }).skipMemoryCache(true).into(imageView);
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


    /**
     * 通过这个方法可以兼容显示jpg/png/webp静图 以及 gif动图 webp动图
     */
    public static void loadCompat(final ImageView imageView, String url, @Nullable Transformation<Bitmap> transformation) {
        Transformation<Bitmap> transform = transformation != null ? transformation : new CenterCrop();
        Glide.with(imageView.getContext())
                .load(url)
                .optionalTransform(transform)
                //.optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(transform))
                .into(imageView);
    }

}
