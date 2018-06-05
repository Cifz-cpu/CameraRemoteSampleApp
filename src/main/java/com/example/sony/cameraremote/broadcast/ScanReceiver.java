package com.example.sony.cameraremote.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

/**
 * desc: wifi搜索结果的广播通知
 * Author:cifz
 * time:2018/5/28 13:51
 * e_mail:wangzhen1798@gmail.com
 */

public class ScanReceiver extends BroadcastReceiver {

    private ResultOnListener resultOnListener;

    public void setResultOnListener(ResultOnListener resultOnListener) {
        this.resultOnListener = resultOnListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            boolean isScanned = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, true);
            if (isScanned) {
                resultOnListener.click();
            }
        }
    }

    public interface ResultOnListener{
        void click();
    }

}
