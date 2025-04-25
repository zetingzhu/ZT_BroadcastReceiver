package com.zzt.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

/**
 * @author: zeting
 * @date: 2025/1/17
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver-br";
    public static final String ACTION_RECEIVE = "com.RECEIVE";
    public static final String ACTION_DATA = "com.DATA";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ACTION_RECEIVE)) {
            String data = intent.getStringExtra(ACTION_DATA);
            // Do something with the data, for example send it to a data repository:
            if (data != null) {
                Log.d(TAG, "MyReceiver：" + data);
                Toast.makeText(context, "MyReceiver：" + data, Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("MyReceiver：" + data));
            }
        }
    }
}
