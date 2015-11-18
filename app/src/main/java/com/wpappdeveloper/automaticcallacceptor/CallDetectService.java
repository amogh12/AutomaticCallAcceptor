package com.wpappdeveloper.automaticcallacceptor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by aphadke on 6/9/2015.
 */
public class CallDetectService extends Service {
    private CallHelper callHelper;
    private static final String tag = CallDetectService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        callHelper = new CallHelper(this);

        int res = super.onStartCommand(intent, flags, startId);
        Log.i(tag, "res value is :" + res);
        callHelper.start();
        return res;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callHelper.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // not supporting binding
        return null;
    }
}
