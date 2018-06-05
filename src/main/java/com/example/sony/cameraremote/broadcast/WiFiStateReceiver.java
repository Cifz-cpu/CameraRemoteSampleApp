package com.example.sony.cameraremote.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;


import com.example.sony.cameraremote.contant.Command;
import com.example.sony.cameraremote.event.WIFINameState;
import com.hy.libs.utils.Logs;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cifz on 2018/5/29.
 */
public class WiFiStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                Log.e("WiFiStateReceiver", "wifi断开");
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //获取当前wifi名称
                Logs.e("连接到网络 " + wifiInfo.getSSID());
                WIFINameState wifiNameState = new WIFINameState();
                wifiNameState.WIFI_SSID = wifiInfo.getSSID();
                EventBus.getDefault().post(wifiNameState);
            }
        }
    }
}
