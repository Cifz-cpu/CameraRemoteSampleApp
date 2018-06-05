/*
 * Copyright 2014 Sony Corporation
 */

package com.example.sony.cameraremote;

import android.app.Application;
import android.content.Context;

import com.example.sony.cameraremote.contant.Command;
import com.hy.libs.app.AppManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Application class for the sample application.
 */
public class SampleApplication extends Application {

    private ServerDevice mTargetDevice;

    private SimpleRemoteApi mRemoteApi;

    private SimpleCameraEventObserver mEventObserver;

    private Set<String> mSupportedApiSet;

    public static List<byte[]> lightByte = new ArrayList<>();
    private static SampleApplication instance;

    /**
     * App Activity 自定义栈管理
     */
    public AppManager appManager;

    public SampleApplication() {
        super();
        instance = this;
    }

    public static SampleApplication getInstance() {
        if (instance == null)
            throw new IllegalStateException();
        return instance;
    }

    public static Context getAppContext() {
        if (instance == null)
            throw new IllegalStateException();
        return instance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appManager = AppManager.getAppManager();
        initData();
    }

    /**
     * Sets a target ServerDevice object.
     * 
     * @param device
     */
    public void setTargetServerDevice(ServerDevice device) {
        mTargetDevice = device;
    }

    /**
     * Returns a target ServerDevice object.
     * 
     * @return return ServiceDevice
     */
    public ServerDevice getTargetServerDevice() {
        return mTargetDevice;
    }

    /**
     * Sets a SimpleRemoteApi object to transmit to Activity.
     * 
     * @param remoteApi
     */
    public void setRemoteApi(SimpleRemoteApi remoteApi) {
        mRemoteApi = remoteApi;
    }

    /**
     * Returns a SimpleRemoteApi object.
     * 
     * @return return SimpleRemoteApi
     */
    public SimpleRemoteApi getRemoteApi() {
        return mRemoteApi;
    }

    /**
     * Sets a List of supported APIs.
     * 
     * @param apiList
     */
    public void setSupportedApiList(Set<String> apiList) {
        mSupportedApiSet = apiList;
    }

    /**
     * Returns a list of supported APIs.
     * 
     * @return Returns a list of supported APIs.
     */
    public Set<String> getSupportedApiList() {
        return mSupportedApiSet;
    }

    /**
     * Sets a SimpleCameraEventObserver object to transmit to Activity.
     *
     * @param observer
     */
    public void setCameraEventObserver(SimpleCameraEventObserver observer) {
        mEventObserver = observer;
    }

    /**
     * Returns a SimpleCameraEventObserver object.
     *
     * @return return SimpleCameraEventObserver
     */
    public SimpleCameraEventObserver getCameraEventObserver() {
        return mEventObserver;
    }

    private void initData() {
        lightByte.add(Command.ONE_ONE);
        lightByte.add(Command.ONE_TWO);
        lightByte.add(Command.ONE_THREE);
        lightByte.add(Command.ONE_FOUR);
        lightByte.add(Command.ONE_FIVE);
        lightByte.add(Command.ONE_SIX);
        lightByte.add(Command.ONE_SEVEN);
        lightByte.add(Command.ONE_EHGIT);

        lightByte.add(Command.TWO_ONE);
        lightByte.add(Command.TWO_TWO);
        lightByte.add(Command.TWO_THREE);
        lightByte.add(Command.TWO_FOUR);
        lightByte.add(Command.TWO_FIVE);
        lightByte.add(Command.TWO_SIX);
        lightByte.add(Command.TWO_SEVEN);
        lightByte.add(Command.TWO_EHGIT);
    }

}
