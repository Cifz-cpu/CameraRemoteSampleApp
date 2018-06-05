package com.hy.libs.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.hy.libs.utils.BitmapHelper;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片压缩操作工具类
 *
 * @author mao
 */
public class BitmapRevition {

    private static final String LOG_TAG = "BitmapCompress";

    private static BitmapRevition instance;

    private Context mContext;

    private BitmapRevition(Context context) {

        this.mContext = context;
    }

    public static BitmapRevition getInstance(Context context) {
        if (instance == null) {
            instance = new BitmapRevition(context);
        }
        return instance;
    }

    public void startCompress(String originalPath, String newFilePath,
                              BitmapRevitionListener mBitmapCompressListener) {

        BitmapCoAysTask coAysTask = new BitmapCoAysTask(mBitmapCompressListener);
        coAysTask.execute(new String[]{originalPath, newFilePath});

    }

    class BitmapCoAysTask extends AsyncTask<String[], String, String> {

        private BitmapRevitionListener mBitmapCompressListener;

        public BitmapCoAysTask(BitmapRevitionListener bitmapCompressListener) {

            this.mBitmapCompressListener = bitmapCompressListener;
        }

        /**
         * 图片处理之前
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (BitmapRevitionConfig.BITMAP_LOGOUT) {
                Log.d(LOG_TAG, "into onPreExecute()");
            }
            if (mBitmapCompressListener != null) {
                mBitmapCompressListener.beforeBitmapRevition();
            }
        }

        /**
         * 异步执行中，进行图片处理
         */
        @Override
        protected String doInBackground(String[]... params) {
            if (BitmapRevitionConfig.BITMAP_LOGOUT) {
                Log.d(LOG_TAG, "into doInBackground()");
                Log.d(LOG_TAG, "原文件路径：" + params[0][0]);
                Log.d(LOG_TAG, "新文件路径：" + params[0][1]);
            }
            String compBitmapPath = "";
            if (!new File((params[0][0])).exists()) {
                throw new UnsupportedOperationException("原文件不存在，无法进行压缩...");
            } else {
                Bitmap resultBitmap = BitmapHelper.getBitmapByFileDe(params[0][0]);
                String fileName = new File(params[0][0]).getName();
                try {
                    // 对图片进行压缩和旋转处理
                    resultBitmap = BitmapHelper.revitionImage(params[0][0]);
                    // 将处理后的图片保存到本地
                    compBitmapPath = saveImg(resultBitmap, fileName, params[0][1]);
                    if (BitmapRevitionConfig.BITMAP_LOGOUT) {
                        Log.d(LOG_TAG, "原文件大小：" + new File(params[0][0]).length() / 1024 + "KB");
                        Log.d(LOG_TAG, "新文件大小：" + new File(params[0][1]).length() / 1024 + "KB");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return compBitmapPath;
        }

        /**
         * 异步执行后,返回压缩后的图片保存路径
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (mBitmapCompressListener != null) {
                mBitmapCompressListener.afterBitmapRevition(result);
            }
        }
    }

    /**
     * 将处理 后的图片保存到本地
     *
     * @param b
     * @param name
     * @param newFilePath
     * @return
     * @throws Exception
     */
    private String saveImg(Bitmap b, String name, String newFilePath) throws Exception {
        if (BitmapRevitionConfig.BITMAP_LOGOUT) {
            Log.d(LOG_TAG, "into saveImg()");
            Log.d(LOG_TAG, "新文件路径：" + newFilePath);
        }
        File mediaFile = new File(newFilePath);
        if (mediaFile != null && mediaFile.exists() && mediaFile.isFile()) {
            FileOutputStream fos = new FileOutputStream(mediaFile);
            b.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            fos.flush();
            fos.close();
            b.recycle();
            b = null;
            return mediaFile.getAbsolutePath();
        } else {
            return "";
        }
    }
}
