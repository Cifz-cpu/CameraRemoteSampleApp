package com.example.sony.cameraremote.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hy.libs.utils.Logs;

/**
 * desc:
 * Author:cifz
 * time:2018/5/16 13:39
 * e_mail:wangzhen1798@gmail.com
 */

public class CameraBindService extends Service {

    private LocalBinder binder = new LocalBinder();
    private int count;
    private boolean quit;
    private Thread thread;


    public class LocalBinder extends Binder{
        public CameraBindService getService(){
            return CameraBindService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logs.e("onCreate");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 每间隔一秒count加1 ，直到quit为true。
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        });
        thread.start();
    }

    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logs.e("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Logs.e("onDestroy");
    }

}
