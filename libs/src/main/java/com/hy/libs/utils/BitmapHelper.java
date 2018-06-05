package com.hy.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Bitmap操作工具类
 *
 * @author mao
 */
public class BitmapHelper {

    private Context context;

    private static BitmapHelper instance = null;

    public static synchronized BitmapHelper getInstance(Context c) {
        if (null == instance) {
            instance = new BitmapHelper(c);
        }
        return instance;
    }

    private BitmapHelper(Context c) {
        context = c;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);// 压缩位图
            byte[] bytes = baos.toByteArray();// 创建分配字节数组
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            if (null != baos) {
                try {
                    baos.flush();
                    baos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * @return
     * @throws IOException
     * @Description 上传服务器前调用该方法进行压缩
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap revitionImage(String path) throws IOException {
        if (null == path || TextUtils.isEmpty(path) || !new File(path).exists())
            return null;
        BufferedInputStream in = null;
        try {
            int degree = readPictureDegree(path);
            in = new BufferedInputStream(new FileInputStream(new File(path)));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            options.inSampleSize = calculateInSampleSize(options, 400, 600);
            in.close();
            in = new BufferedInputStream(new FileInputStream(new File(path)));
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeStream(in, null, options);
            Bitmap newbitmap = rotaingImageView(degree, bitmap);
            return newbitmap;
        } catch (Exception e) {
            return null;
        } finally {
            if (null != in) {
                in.close();
                in = null;
            }
        }
    }

    public static String getImagePathFromUri(Uri uri, Context context) {
        // 如果是file，直接拿
        if (uri.getScheme().equalsIgnoreCase("file")) {
            return uri.getPath();
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndex(projection[0]);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        if (null == bitmap) {
            return null;
        }
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 加载本地Bitmap，避免内存溢出
     *
     * @param filePath
     * @return
     */
    public static Bitmap getBitmapByFileDe(String filePath) {

        BitmapFactory.Options bfOptions = new BitmapFactory.Options();

        bfOptions.inDither = false;
        bfOptions.inPurgeable = true;
        bfOptions.inInputShareable = true;
        bfOptions.inTempStorage = new byte[32 * 1024];

        File file = new File(filePath);
        FileInputStream fs = null;
        Bitmap bm = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (fs != null) {
                bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }

    /**
     * 调用系统拍照
     *
     * @param filePath
     * @param requestCode
     * @param activity
     */
    public static void takePhoto(String filePath, int requestCode, Activity activity) {

        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
        activity.startActivityForResult(cameraintent, requestCode);
    }


    /**
     * 查看系统图片
     *
     * @param requestCode
     * @param activity
     */
    public static void viewPhoto(int requestCode, Activity activity) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");// 相片类型
        activity.startActivityForResult(intent, requestCode);

    }

    /**
     * 按照时间获取图片名字
     *
     * @return
     */
    public static String getPhotoFileName() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }


    /**
     * Bitmap对象保存味图片文件
     *
     * @param bitmap
     */
    public void saveBitmapFile(Bitmap bitmap, String path) throws IOException {

        File file = new File(path);//将要保存图片的路径
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();

    }
}
