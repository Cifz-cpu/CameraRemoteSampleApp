package com.example.sony.cameraremote.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.sony.cameraremote.contant.Command;
import com.example.sony.cameraremote.event.ChangeState;
import com.example.sony.cameraremote.event.ChangeWifi;
import com.example.sony.cameraremote.event.CommandEvent;
import com.example.sony.cameraremote.event.WIFINameState;
import com.hy.libs.utils.Logs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc: 后台socket连接服务
 * Author:cifz
 * time:2018/5/7 16:20
 * e_mail:wangzhen1798@gmail.com
 */

public class CameraService extends Service {

    private Socket mSocket;
    // 线程池
    // 为了方便展示,此处直接采用线程池进行线程管理,而没有一个个开线程
    private ExecutorService mThreadPool;
    // 输入流对象
    InputStream is;
    // 输出流对象
    OutputStream outputStream;
    /**
     * socket 是否成功连接
     */
    private boolean isLinkSuccess = false;
    ChangeState changeState = new ChangeState();

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        Logs.e("onCreat");
        try {
            //创建线程池
            mThreadPool = Executors.newCachedThreadPool();
            // 利用线程池直接开启一个线程 & 执行该线程
            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 创建Socket对象 & 指定服务端的IP 及 端口号
                        mSocket = new Socket("172.16.1.90", 10000);
                        // 判断客户端和服务器是否连接成功
                        isLinkSuccess = mSocket.isConnected();
                        Logs.e(isLinkSuccess + "");
                        if (isLinkSuccess) {
                            changeState.SOCKET_STATE = 1;
                            EventBus.getDefault().post(changeState);
                            outputStream = mSocket.getOutputStream();
                            is = mSocket.getInputStream();
                            mThreadPool.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while (true) {
                                        try {
                                            outputStream.write(new byte[]{(byte) 0xff});
                                            outputStream.flush();
                                            Thread.sleep(10000);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        } else {
                            changeState.SOCKET_STATE = 0;
                            EventBus.getDefault().post(changeState);
                        }
                    } catch (IOException e) {
                        Logs.e("false");
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onReceiveMessage(final CommandEvent commandEvent) {
        try {
            outputStream.write(Command.ALL_CLOSE);
            outputStream.flush();
            Thread.sleep(500);
            int length = 0;
            Logs.e(commandEvent.scenes.size() + "");
            while (length < commandEvent.scenes.size()) {
                outputStream.write(commandEvent.scenes.get(length));
                outputStream.flush();
                Logs.e(commandEvent.scenes.get(length).toString());
                length++;
                Thread.sleep(500);
            }

            Thread.sleep(1000);
            ChangeWifi changeWifi = new ChangeWifi();
            changeWifi.isFlag = true;
            EventBus.getDefault().post(changeWifi);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WIFISSID(final WIFINameState wifiNameState) {
        //创建线程池
        mThreadPool = Executors.newCachedThreadPool();
        // 利用线程池直接开启一个线程 & 执行该线程
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                if (wifiNameState.WIFI_SSID.equals("\"test_wago\"")) {
                    try {
                        // 创建Socket对象 & 指定服务端的IP 及 端口号
                        mSocket = new Socket("172.16.1.90", 10000);
                        // 判断客户端和服务器是否连接成功
                        isLinkSuccess = mSocket.isConnected();
                        Logs.e(isLinkSuccess + "");
                        if (isLinkSuccess) {
                            changeState.SOCKET_STATE = 1;
                            EventBus.getDefault().post(changeState);
                            outputStream = mSocket.getOutputStream();
                            is = mSocket.getInputStream();
                            mThreadPool.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while (true) {
                                        try {
                                            outputStream.write(new byte[]{(byte) 0xff});
                                            outputStream.flush();
                                            Thread.sleep(10000);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            });
                        } else {
                            changeState.SOCKET_STATE = 0;
                            EventBus.getDefault().post(changeState);
                        }
                    } catch (IOException e) {
                        Logs.e("false");
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Logs.e("onDestroy");
        changeState.SOCKET_STATE = 2;
        EventBus.getDefault().post(changeState);
        try {
            mSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
