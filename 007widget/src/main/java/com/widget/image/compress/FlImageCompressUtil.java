package com.widget.image.compress;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FlImageCompressUtil {

    private static boolean sImageExifEnable = true;

    public static class MyBitmap {
        public Bitmap bitmap;
        public HashMap<String, String> exifInfo;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static String bitmapToBase64(MyBitmap myBitmap, int quality, Activity sContext) {
        byte[] bytes = bitmapToBytes(sContext, myBitmap, quality);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return Base64.encodeToString(bytes, 0);
    }

    public static byte[] bitmapToBytes(Activity sContext, MyBitmap myBitmap, int quality) {
        if (myBitmap == null) {
            return null;
        }

        Bitmap bitmap = myBitmap.bitmap;
        byte[] bitmapBytes = null;
        ByteArrayOutputStream baos = null;
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            if (bitmap != null) {
                if (myBitmap.exifInfo != null && myBitmap.exifInfo.size() > 0) {

                    File cacheDir = sContext.getExternalCacheDir();
                    if (cacheDir == null) {
                        cacheDir = sContext.getCacheDir();
                    }

                    String filePath = cacheDir.getAbsolutePath() + File.separator + "fenqile" +
                            File.separator + "photo" + File.separator;
                    String fileName = filePath + System.currentTimeMillis() + ".jpg";
                    File tempFile = new File(fileName);
                    if (!tempFile.getParentFile().exists()) {
                        File parentFile = new File(filePath);
                        parentFile.mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(tempFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    setExif(fileName, myBitmap.exifInfo);
                    fileInputStream = new FileInputStream(fileName);
                    baos = new ByteArrayOutputStream();
                    byte[] re = new byte[24];
                    while ((fileInputStream.read(re) != -1)) {
                        baos.write(re);
                    }
                    fileInputStream.close();
                } else {
                    baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
                }

                baos.flush();
                baos.close();
                bitmapBytes = baos.toByteArray();
            }
        } catch (OutOfMemoryError var16) {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            bitmapBytes = baos.toByteArray();
        } catch (Throwable var15) {

        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException var14) {

            }

        }

        return bitmapBytes;
    }


    public static void setExif(String filePath, HashMap<String, String> exif) {
        if (!sImageExifEnable || filePath == null || exif == null || exif.size() <= 0) {
            return;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            for (Map.Entry<String, String> entry : exif.entrySet()) {
                exifInterface.setAttribute(entry.getKey(), entry.getValue());
            }
            exifInterface.saveAttributes();
        } catch (Throwable throwable) {
        }
    }

    //大小压缩/长宽
    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image, int quality) {

        if (image == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 1024) { // 循环判断如果压缩后图片是否大1024kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减10
            if (options <= 0) {
                options = 0;
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);
                break;
            }
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int maxLength = 1024;
        // 现在主流手机比较多是1920*1280分辨率，所以高和宽我们设置为
        float hh = 1920f;
        float ww = 1080f;
        if (quality >= 75 && quality < 100) {
            hh = 1280;
            ww = 720;
            maxLength = 700;
        } else if (quality > 50 && quality < 75) {
            hh = 720;
            ww = 480;
            maxLength = 400;
        } else if (quality <= 50) {
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565
            hh = 480;
            ww = 320;
            maxLength = 100;
        }
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = Math.round(newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = Math.round(newOpts.outHeight / hh);
        }

        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be; // 设置缩放比例

        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap, maxLength);// 压缩好比例大小后再进行质量压缩
    }

    //质量压缩
    public static Bitmap compressImage(Bitmap image, int maxLength) {
        if (image == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > maxLength) { // 循环判断如果压缩后图片是否大1024kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减10
            if (options <= 0) {
                options = 0;
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);
                break;
            }
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
