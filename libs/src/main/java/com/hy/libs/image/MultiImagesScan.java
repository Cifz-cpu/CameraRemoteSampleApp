package com.hy.libs.image;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 多图片选择浏览
 */
public class MultiImagesScan {

    private static final String TAG = "MultiImagesScan";

    private static MultiImagesScan instance;

    private Context mContext;

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths;

    private static final int SCAN_IMAGE_FLODER = 0X100;

    private static final int SCAN_IMAGE_FLODER_IMAGES = 0X110;

    public synchronized static MultiImagesScan getInstance(Context context) {

        if (instance == null) {
            instance = new MultiImagesScan(context);
        }
        return instance;
    }

    private MultiImagesScan(Context context) {

        this.mContext = context;
    }

    /**
     * 查看图片文件夹
     */
    public void scanImageFloders(final MultiImgsScListener misScListener) {

        final Handler mHandler = builderHandler(misScListener);
        final List<ImageFloder> mImageFlodersT = new ArrayList<ImageFloder>();
        if (!checkSDKISAvailable()) {
            if (misScListener != null) {
                misScListener.scanImageFolder(mImageFlodersT);
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDirPaths = new HashSet<String>();
                Cursor mCursor = getImagesCursor();
                while (mCursor.moveToNext()) {
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        Log.i(TAG, "dirPath :" + dirPath);
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }
                    imageFloder.setCount(caculateFloderCount(parentFile));
                    mImageFlodersT.add(imageFloder);
                }
                closeImageCursor(mCursor);
                sendMessage(mHandler, mImageFlodersT, SCAN_IMAGE_FLODER);
                mDirPaths = null;
            }
        }).start();
    }

    /**
     * 查看单个文件下的图片
     *
     * @param imageFloder
     */
    public void scanImagesOfAFloder(MultiImgsScListener misScListener, final ImageFloder imageFloder) {

        final Handler mHandler = builderHandler(misScListener);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                File mImgDir = new File(imageFloder.getDir());
                List<String> mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg")) {
                            return true;
                        }
                        return false;
                    }
                }));
                sendMessage(mHandler, mImgs, SCAN_IMAGE_FLODER_IMAGES);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 构建Handler,回调到主线程
     *
     * @param misScListener
     * @return
     */
    private Handler builderHandler(final MultiImgsScListener misScListener) {
        Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == SCAN_IMAGE_FLODER) {
                    if (misScListener != null) {
                        misScListener.scanImageFolder((List<ImageFloder>) msg.obj);
                    }
                } else if (msg.what == SCAN_IMAGE_FLODER_IMAGES) {
                    if (misScListener != null) {
                        misScListener.scanImagesOfAFolder((List<String>) msg.obj);
                    }
                }
            }

            ;
        };
        return mHandler;
    }

    /**
     * 外部存储设备是否可用
     *
     * @return
     */
    private boolean checkSDKISAvailable() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    /**
     * 获取图片游标
     *
     * @return
     */
    private Cursor getImagesCursor() {

        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = mContext.getContentResolver();
        Cursor mCursor = mContentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
        return mCursor;
    }

    /**
     * 关闭游标
     *
     * @param mCursor
     */
    private void closeImageCursor(Cursor mCursor) {

        if (mCursor != null) {
            mCursor.close();
        }

    }

    /**
     * 传送消息
     *
     * @param mHandler
     * @param obj
     */
    private void sendMessage(Handler mHandler, Object obj, int what) {

        Message msg = mHandler.obtainMessage(what);
        msg.obj = obj;
        mHandler.sendMessage(msg);

    }

    /**
     * 计算图片文件夹下图片的数目
     *
     * @param fileDir
     * @return
     */
    private int caculateFloderCount(File fileDir) {
        int picSize = fileDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        }).length;
        return picSize;
    }
}
