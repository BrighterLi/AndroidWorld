package com.widget.aaaview.custom_view.gif;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.widget.banner.banner2.ImageUtil;

import java.io.File;
import java.lang.ref.WeakReference;

import androidx.annotation.RequiresApi;
import pl.droidsonroids.gif.GifImageView;

public class CustomGifImageView extends GifImageView {
    private Object mUrlTag;

    private RoundAngleHelper mRoundAngleHelper;
    private boolean isCompleteCircle;

    public CustomGifImageView(Context context) {
        this(context, null);
    }

    public CustomGifImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGifImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mRoundAngleHelper = new RoundAngleHelper();
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomGifImageView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
    }

    public Object getUrlTag() {
        return mUrlTag;
    }

    public void setImageUriWithTag(Uri uri, Object urlTag) {
        super.setImageURI(uri);
        mUrlTag = urlTag;
    }

    public void setImageDrawableWithTag(Drawable d, Object urlTag) {
        super.setImageDrawable(d);
        mUrlTag = urlTag;
    }

    public boolean hasDrawableWithTag(Object tag) {
        return getDrawable() != null
                && mUrlTag != null && mUrlTag.equals(tag);
    }

    public void loadImage(String normalUrl) {
        ImageUtil.loadImage(normalUrl, this);
    }

    public void loadImage(String normalUrl, boolean webpEnable) {
        ImageUtil.loadImage(normalUrl, this, webpEnable);
    }

    public void loadBitmap(String staticUrl) {
        loadStaticImage(this, staticUrl);
    }

    private static void loadStaticImage(CustomGifImageView giv, final String staticUrl) {
        if (giv == null) {
            return;
        }
        if (TextUtils.isEmpty(staticUrl)) {
            giv.setImageDrawableWithTag(null, null);
            return;
        }
        giv.setImageDrawableWithTag(null, staticUrl);
        final WeakReference<CustomGifImageView> weakRef = new WeakReference<>(giv);
        ImageUtil.getBitmap(giv.getContext(), staticUrl, new ImageUtil.OnBitmapLoad() {
            @Override
            public void onBitmapReady(Bitmap bitmap) {
                CustomGifImageView gifImageView = weakRef.get();
                if (gifImageView != null) {
                    if (staticUrl.equals(gifImageView.getUrlTag())) {
                        gifImageView.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

    public void loadGifImage(String gifUrl) {
        loadGif(this, gifUrl, null);
    }

    public void loadCompatImage(final String staticUrl, final String gifUrl) {
        loadCompatImage(staticUrl, gifUrl, true, null);
    }

    public void loadCompatImage(final String staticUrl, final String gifUrl,
                                final boolean checkEnv, final GifListener listener) {
        if (TextUtils.isEmpty(gifUrl)) {
            loadImage(staticUrl, true);
            return;
        }
        if (TextUtils.isEmpty(staticUrl)) {
            loadGifCompat(this, null, gifUrl, false, listener);
            return;
        }
        setImageDrawableWithTag(null, staticUrl);
        ImageUtil.getBitmap(this.getContext(), staticUrl, new ImageUtil.OnBitmapLoad() {
            @Override
            public void onBitmapReady(Bitmap bitmap) {
                if (staticUrl.equals(getUrlTag())) {
                    loadGifCompat(CustomGifImageView.this, bitmap, gifUrl, checkEnv, listener);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private static void loadGifCompat(CustomGifImageView giv, Bitmap bitmap, String gifUrl,
                                      boolean checkEnv, final GifListener listener) {
        if (giv == null) {
            return;
        }
        boolean useGif = !TextUtils.isEmpty(gifUrl);
        if (bitmap == null || bitmap.isRecycled()) {
            giv.setImageDrawable(null);
        } else {
            giv.setImageBitmap(bitmap);
        }
        if (useGif) {
            loadGif(giv, gifUrl, listener);
        }
    }


    private static void loadGif(CustomGifImageView giv, final String url, final GifListener listener) {
        if (giv == null) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            giv.setImageDrawableWithTag(null, null);
            return;
        }
        giv.setImageDrawableWithTag(null, url);
        final WeakReference<CustomGifImageView> weakRef = new WeakReference<>(giv);
        ImageUtil.getFile(giv.getContext(), url, new ImageUtil.OnFileLoad() {

            @Override
            public void onFileReady(File file) {
                CustomGifImageView gifImageView = weakRef.get();
                if (gifImageView != null) {
                    if (url.equals(gifImageView.getUrlTag())) {
                        try {
                            gifImageView.setImageUriWithTag(Uri.fromFile(file), url);
                        } catch (Throwable e) {
                        }
                    }
                }
                if (listener != null) {
                    listener.onGifFileReady(file);
                }
            }

            @Override
            public void onFileLoadFail() {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isCompleteCircle) {
            float v = Math.max(getWidth(), getHeight()) / 2f;
            float half = (float) Math.ceil(v);
            setRadius(half);
        }
        mRoundAngleHelper.beforeDispatchDraw(this, canvas);
        super.onDraw(canvas);
        mRoundAngleHelper.afterDispatchDraw(this, canvas);
    }


    /**
     * 设置四个角的圆角半径为同一个值
     */
    public void setRadius(float radius) {
        mRoundAngleHelper.setRadius(radius);
    }
    public void setCompleteCircle(boolean circle) {
        isCompleteCircle = circle;
    }

    public interface GifListener {

        void onGifFileReady(File file);

    }



}
